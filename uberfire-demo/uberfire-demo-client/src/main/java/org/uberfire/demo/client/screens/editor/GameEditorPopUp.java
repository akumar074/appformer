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

package org.uberfire.demo.client.screens.editor;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import elemental2.dom.HTMLElement;
import org.jboss.errai.common.client.api.elemental2.IsElement;
import org.jboss.errai.common.client.dom.elemental2.Elemental2DomUtil;
import org.jboss.errai.common.client.ui.ElementWrapperWidget;
import org.uberfire.client.annotations.WorkbenchPartTitle;
import org.uberfire.client.annotations.WorkbenchPartView;
import org.uberfire.client.annotations.WorkbenchPopup;
import org.uberfire.client.mvp.PlaceManager;
import org.uberfire.client.workbench.PanelManager;
import org.uberfire.mvp.PlaceRequest;

@WorkbenchPopup(identifier = GameEditorPopUp.IDENTIFIER)
public class GameEditorPopUp implements IsElement {

    public static final String IDENTIFIER = "NewGameEditorPopUp";

    @Inject
    private PlaceManager placeManager;
    private PlaceRequest place;

    @Inject
    private GameEditorComponent gameEditorComponent;

    @PostConstruct
    public void setup() { }

    @Override
    public HTMLElement getElement() {
        return gameEditorComponent.getElement();
    }

    @WorkbenchPartTitle
    public String getTitle() {
        return "Game editor form";
    }

    @WorkbenchPartView
    public IsWidget getView() {
        return ElementWrapperWidget.getWidget(gameEditorComponent.getElement());
    }
}
