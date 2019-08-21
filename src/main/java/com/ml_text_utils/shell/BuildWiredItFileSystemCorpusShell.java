package com.ml_text_utils.shell;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lexicalscope.jewel.cli.CliFactory;
import com.lexicalscope.jewel.cli.Option;
import com.ml_text_utils.TextDocumentsStream;
import com.ml_text_utils.corpus.CorpusClassStatistics;
import com.ml_text_utils.corpus.CorpusConstraints;
import com.ml_text_utils.corpus.CorpusStatistics;
import com.ml_text_utils.corpus.FileSystemCorpusBuilder;
import com.ml_text_utils.impl.wired_it.WiredItCreativeCommonsArticlesTextStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

interface BuildWiredItFileSystemCorpusShellConfig {

    @Option
    File getWiredItArticlesJsonFile();

    @Option
    File getSplitCorpusOutputFolder();

    @Option
    Integer getMinDocsPerClass();

    @Option
    Integer getMaxDocsPerClass();
    @SuppressWarnings("unused") boolean isMaxDocsPerClass();

    @Option
    double getTrainingSetRate();

}

public class BuildWiredItFileSystemCorpusShell {

    private final static Logger log = LoggerFactory.getLogger(BuildWiredItFileSystemCorpusShell.class);

    private static final Predicate<CorpusClassStatistics> IS_TRAIN_TEST_SPLIT_VALID = corpusClassStatistics -> {
	if (corpusClassStatistics.getTrainingDocumentsCount() == 0) return false;
	return corpusClassStatistics.getTestDocumentsCount() > 0;
    };

    private static Predicate<CorpusClassStatistics> buildClassesFilter(Set<String> skipClasses) {
	return corpusClassStatistics -> !skipClasses.contains(corpusClassStatistics.getClassLabel().getName()) && IS_TRAIN_TEST_SPLIT_VALID.test(corpusClassStatistics);
    }

    public static void main(String[] args) throws IOException {

	BuildWiredItFileSystemCorpusShellConfig config = CliFactory.parseArguments(BuildWiredItFileSystemCorpusShellConfig.class, args);
	log.info("start|configs" + config.toString());

	assert config.getWiredItArticlesJsonFile().exists();
	assert config.getSplitCorpusOutputFolder().isDirectory();

	TextDocumentsStream textDocumentsStream = WiredItCreativeCommonsArticlesTextStream.createStreamFromJSON(config.getWiredItArticlesJsonFile());

	Predicate<CorpusClassStatistics> classesFilter = buildClassesFilter(Collections.emptySet());

	FileSystemCorpusBuilder fileSystemCorpusBuilder = new FileSystemCorpusBuilder(classesFilter);
	CorpusConstraints corpusConstraints = new CorpusConstraints(config.getMinDocsPerClass(), Optional.ofNullable(config.getMaxDocsPerClass()));
	CorpusStatistics corpusStatistics = fileSystemCorpusBuilder.buildCorpusBySplittingTrainingAndTestSet(textDocumentsStream,
													     config.getSplitCorpusOutputFolder(),
													     config.getTrainingSetRate(),
													     corpusConstraints);

	File corpusStatisticsFile = new File(config.getSplitCorpusOutputFolder().getParentFile().getPath() + File.separator + config.getSplitCorpusOutputFolder().getName() + ".json");
	new ObjectMapper().writerWithDefaultPrettyPrinter().writeValue(corpusStatisticsFile, corpusStatistics);
    }
}
