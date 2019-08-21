package com.ml_text_utils.impl.wired_it;

import com.ml_text_utils.ClassLabel;

import java.util.Objects;

public class WiredItArticleClassLabel implements ClassLabel {

    private final String name;

    WiredItArticleClassLabel(String name) {
	this.name = name;
    }

    @Override public String getName() {
	return name;
    }

    @Override public String toString() {
	return "WiredItArticleClassLabel{" +
			"name='" + name + '\'' +
			'}';
    }

    @Override public boolean equals(Object o) {
	if (this == o)
	    return true;
	if (!(o instanceof WiredItArticleClassLabel))
	    return false;
	WiredItArticleClassLabel that = (WiredItArticleClassLabel) o;
	return Objects.equals(name, that.name);
    }

    @Override public int hashCode() {
	return Objects.hash(name);
    }
}
