package com.ml_text_utils.impl.wired_it;

import com.ml_text_utils.ClassLabel;
import com.ml_text_utils.TextDocument;

import java.util.Objects;
import java.util.regex.Pattern;

import static java.lang.Math.min;

@SuppressWarnings("WeakerAccess") public class WiredItArticle implements TextDocument {

    private static final Pattern RETAIN_NUMBER_AND_ENGLISH_LETTERS = Pattern.compile("[^a-zA-Z0-9]");

    private  String category;
    private  String title;
    private  String url;
    private  String copyright;
    private  String text;

    private static String replaceNonAlphanumericalCharacters(String text) {
	return RETAIN_NUMBER_AND_ENGLISH_LETTERS.matcher(text).replaceAll("_").toLowerCase();
    }

    @SuppressWarnings("unused") private WiredItArticle() {}

    @SuppressWarnings("unused") private WiredItArticle(String category, String title, String url, String copyright, String text) {
	this.category = category;
	this.title = title;
	this.url = url;
	this.copyright = copyright;
	this.text = text;
    }

    @SuppressWarnings("unused") public String getCategory() {
	return category;
    }

    @SuppressWarnings("unused") public String getTitle() {
	return title;
    }

    @SuppressWarnings("unused") public String getUrl() {
	return url;
    }

    @SuppressWarnings("unused") public String getCopyright() {
	return copyright;
    }

    @Override public String getId() {
	String normalizedTitle = replaceNonAlphanumericalCharacters(title);
	return normalizedTitle.substring(0, min(50, normalizedTitle.length()));
    }

    @Override public ClassLabel getClassLabel() {
	return new WiredItArticleClassLabel(category);
    }

    @Override public String getText() {
	return text;
    }

    @Override public String toString() {
	return "WiredItArticle{" +
			"category='" + category + '\'' +
			", title='" + title + '\'' +
			", url='" + url + '\'' +
			", copyright='" + copyright + '\'' +
			", text='" + text + '\'' +
			'}';
    }

    @Override public boolean equals(Object o) {
	if (this == o)
	    return true;
	if (!(o instanceof WiredItArticle))
	    return false;
	WiredItArticle that = (WiredItArticle) o;
	return Objects.equals(category, that.category) &&
			Objects.equals(title, that.title) &&
			Objects.equals(url, that.url) &&
			Objects.equals(copyright, that.copyright) &&
			Objects.equals(text, that.text);
    }

    @Override public int hashCode() {
	return Objects.hash(category, title, url, copyright, text);
    }
}
