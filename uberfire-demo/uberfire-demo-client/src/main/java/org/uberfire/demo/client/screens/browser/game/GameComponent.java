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

package org.uberfire.demo.client.screens.browser.game;

import java.nio.file.Paths;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import elemental2.dom.HTMLElement;
import elemental2.dom.HTMLImageElement;
import org.jboss.errai.common.client.api.elemental2.IsElement;
import org.uberfire.backend.vfs.Path;
import org.uberfire.client.mvp.PlaceManager;
import org.uberfire.demo.api.model.Game;
import org.uberfire.demo.api.model.GameInfo;
import org.uberfire.demo.client.event.GameDetailEvent;
import org.uberfire.demo.client.event.GameEditEvent;
import org.uberfire.demo.client.screens.details.DetailsScreen;
import org.uberfire.demo.client.screens.editor.GameEditorComponent;
import org.uberfire.demo.client.screens.editor.GameEditorPopUp;
import org.uberfire.mvp.Command;
import org.uberfire.mvp.impl.DefaultPlaceRequest;

@Dependent
public class GameComponent implements GameComponentView.Presenter,
                                      IsElement {

    private Game game;

    private GameComponentView view;

    @Inject
    private PlaceManager placeManager;

    @Inject
    private Event<GameDetailEvent> event;

    @Inject
    private Event<GameEditEvent> editEvent;

    private Path path;

    public void show(GameInfo gameInfo) {
        this.game = gameInfo.getGame();
        this.path = gameInfo.getPath();
        view.show(game);
    }

    public void render(Path assetPath) {
        this.path = assetPath;
    }

    @Inject
    public GameComponent(GameComponentView view) {
        this.view = view;
        view.init(this);
    }

    @Override
    public HTMLElement getElement() {
        return view.getElement();
    }

    @Override
    public void open() {
        placeManager.goTo("Details");
        event.fire(new GameDetailEvent(game));
    }

    @Override
    public void edit() {
        editEvent.fire(new GameEditEvent(game));
    }

    @Override
    public void openEditor() {
        placeManager.goTo(path);
    }
}
