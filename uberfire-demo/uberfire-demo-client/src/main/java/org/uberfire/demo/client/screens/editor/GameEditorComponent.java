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

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import elemental2.dom.HTMLElement;
import org.jboss.errai.common.client.api.elemental2.IsElement;
import org.uberfire.client.mvp.PlaceManager;
import org.uberfire.demo.api.model.Game;
import org.uberfire.demo.client.event.NewGameEvent;
import org.uberfire.demo.client.screens.browser.game.GameComponent;

@Dependent
public class GameEditorComponent implements GameEditorComponentView.Presenter, IsElement{

    private GameEditorComponentView view;

    @Inject
    private PlaceManager placeManager;

    @Inject
    private Event<NewGameEvent> event;

    @Inject
    public GameEditorComponent(GameEditorComponentView view) {
        this.view = view;
        view.init(this);
    }

    @Override
    public void createGame() {
        Game game = new Game();
        game.setType(view.getType());
        game.setId(view.getId());
        game.setRating(view.getRating());
        game.setDescription(view.getDescription());
        game.setYear(view.getYear());
        game.setTitle(view.getTitle());
        this.event.fire(new NewGameEvent(game));
        placeManager.closePlace(GameEditorPopUp.IDENTIFIER);
    }

    public void show(GameEditorComponent component) {
        view.show(component);
    }

    @Override
    public HTMLElement getElement() {
        return view.getElement();
    }
}