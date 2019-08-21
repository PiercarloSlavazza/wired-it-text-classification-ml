package com.ml_text_utils.impl.wired_it;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ml_text_utils.TextDocument;
import com.ml_text_utils.TextDocumentsStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WiredItCreativeCommonsArticlesTextStream implements TextDocumentsStream {

    private final static Logger log = LoggerFactory.getLogger(WiredItCreativeCommonsArticlesTextStream.class);

    private final List<TextDocument> articles;

    private WiredItCreativeCommonsArticlesTextStream(List<TextDocument> articles) {
	this.articles = articles;
    }

    public static WiredItCreativeCommonsArticlesTextStream createStreamFromJSON(File wiredItArticlesJSONFile) {
	ObjectMapper objectMapper = new ObjectMapper();
	try {
	    log.info("loading articles from|" + wiredItArticlesJSONFile + "|start");
	    List<WiredItArticle> articles = objectMapper.readValue(wiredItArticlesJSONFile, new TypeReference<List<WiredItArticle>>(){});
	    log.info("loading articles|end|loaded #|" + articles.size());

	    List<TextDocument> creativeCommonsArticles = articles.
			    stream().
			    filter(wiredItArticle -> wiredItArticle.getCopyright().startsWith("Creative Commons")).
			    collect(Collectors.toList());
	    return new WiredItCreativeCommonsArticlesTextStream(creativeCommonsArticles);
	} catch (IOException e) {
	    throw new RuntimeException(e);
	}
    }

    @Override public Stream<TextDocument> streamTextDocuments() {
	return articles.stream();
    }

}
