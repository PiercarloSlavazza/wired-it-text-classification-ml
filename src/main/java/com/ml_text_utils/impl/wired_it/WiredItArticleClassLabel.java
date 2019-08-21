/*
 * Copyright  2019 Piercarlo Slavazza
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

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
