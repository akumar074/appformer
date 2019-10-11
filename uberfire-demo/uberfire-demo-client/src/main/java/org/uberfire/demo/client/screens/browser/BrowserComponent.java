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

package org.uberfire.demo.client.screens.browser;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import com.google.gwt.user.client.Window;
import elemental2.dom.HTMLElement;
import org.jboss.errai.ioc.client.api.ManagedInstance;
import org.jboss.errai.ui.client.local.api.elemental2.IsElement;
import org.uberfire.demo.api.model.Game;
import org.uberfire.demo.client.event.NewGameEvent;
import org.uberfire.demo.client.screens.browser.game.GameComponent;

@Dependent
public class BrowserComponent implements BrowserComponentView.Presenter,
                                         IsElement {

    private List<Game> gameList;

    private BrowserComponentView view;

    @Inject
    private ManagedInstance<GameComponent> gameComponents;

    @Inject
    public BrowserComponent(BrowserComponentView view, ManagedInstance<GameComponent> gameComponents) {
        this.view = view;
        this.gameComponents = gameComponents;
        view.init(this);
    }

    public void show(List<Game> gameList) {
        this.gameList = gameList;
        this.gameList.forEach((game) -> {
            GameComponent gameComponent = gameComponents.get();
            gameComponent.show(game);
            view.show(gameComponent);
        });
    }

    public void addNewGame(@Observes NewGameEvent event) {
        GameComponent gameComponent = gameComponents.get();
        gameComponent.show(event.getGame());
        view.show(gameComponent);
    }

    @Override
    public void open() {
        Window.alert("Not required");
    }

    @Override
    public HTMLElement getElement() {
        return view.getElement();
    }
}
