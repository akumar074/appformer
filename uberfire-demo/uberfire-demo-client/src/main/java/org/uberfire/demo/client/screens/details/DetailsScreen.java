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
import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;

import elemental2.dom.HTMLElement;
import elemental2.dom.HTMLImageElement;
import org.jboss.errai.common.client.api.elemental2.IsElement;
import org.uberfire.client.annotations.WorkbenchPartTitle;
import org.uberfire.client.annotations.WorkbenchPartView;
import org.uberfire.client.annotations.WorkbenchScreen;
import org.uberfire.client.mvp.PlaceManager;
import org.uberfire.demo.api.model.Game;
import org.uberfire.demo.client.event.GameDetailEvent;
import org.uberfire.demo.client.event.GameEditEvent;
import org.uberfire.demo.client.screens.editor.GameEditorComponent;

@WorkbenchScreen(identifier = DetailsScreen.IDENTIFIER)
@Dependent
public class DetailsScreen implements IsElement {

    public static final String IDENTIFIER = "Details";

    @Inject
    private DetailsComponent detailsComponent;

    @Inject
    private GameEditorComponent gameEditorComponent;

    @Inject
    private DetailsScreenView view;

    @Inject
    private PlaceManager placeManager;

    @PostConstruct
    public void init() {
    }

    public void viewGame(@Observes GameDetailEvent event) {
        view.clear();
        view.add(detailsComponent.getElement());
        detailsComponent.show(event.getGame());
    }

    public void editGame(@Observes GameEditEvent event) {
        view.clear();
        view.add(gameEditorComponent.getElement());
        gameEditorComponent.show(event.getGame());
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
        return view.getElement();
    }
}
