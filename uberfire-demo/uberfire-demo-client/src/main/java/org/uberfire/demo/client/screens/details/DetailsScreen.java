/*
 * Copyright 2019 Red Hat, Inc. and/or its affiliates.
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

package org.uberfire.demo.client.screens.details;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import elemental2.dom.HTMLElement;
import elemental2.dom.HTMLImageElement;
import org.jboss.errai.common.client.api.elemental2.IsElement;
import org.uberfire.client.annotations.WorkbenchPartTitle;
import org.uberfire.client.annotations.WorkbenchPartView;
import org.uberfire.client.annotations.WorkbenchScreen;

@WorkbenchScreen(identifier = DetailsScreen.IDENTIFIER)
public class DetailsScreen implements IsElement {

    public static final String IDENTIFIER = "Details";

    @Inject
    private HTMLImageElement image;

    @PostConstruct
    public void init() {
        image.alt = "Random Image";
        image.src = "https://picsum.photos/300/300";
        image.height = 300;
        image.width = 300;
    }

    @WorkbenchPartTitle
    public String getTitle() {
        return "Details Screen";
    }

    @WorkbenchPartView
    public IsElement getView() {
        return this;
    }

    @Override
    public HTMLElement getElement() {
        return image;
    }
}
