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

import elemental2.dom.HTMLElement;
import elemental2.dom.HTMLHeadingElement;
import org.jboss.errai.common.client.api.elemental2.IsElement;
import org.uberfire.client.annotations.WorkbenchMenu;
import org.uberfire.client.annotations.WorkbenchPartTitle;
import org.uberfire.client.annotations.WorkbenchPartView;
import org.uberfire.client.annotations.WorkbenchScreen;
import org.uberfire.client.mvp.PlaceManager;
import org.uberfire.demo.api.model.Game;
import org.uberfire.demo.client.screens.editor.GameEditorPopUp;
import org.uberfire.workbench.model.menu.MenuFactory;
import org.uberfire.workbench.model.menu.Menus;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@WorkbenchScreen(identifier = BrowserScreen.IDENTIFIER)
public class BrowserScreen implements IsElement {

    public static final String IDENTIFIER = "Browser";

    @Inject
    @Named("h1")
    private HTMLHeadingElement headingElement;

    @Inject
    private BrowserComponent browserComponent;

    @Inject
    private PlaceManager placeManager;

    @PostConstruct
    public void init() {
        headingElement.textContent = "Browser Screen";
        headingElement.style.color = "blue";
//        List<Game> games = new ArrayList<>();
//        Game game1 = new Game();
//        game1.setTitle("Call of Duty");
//        game1.setYear(2001);
//        game1.setType("War");
//        game1.setId("ga001");
//        game1.setDescription("World war game you can play (PG rating: PG13)");
//        game1.setRating(10);
//        Game game2 = new Game();
//        game2.setTitle("God of War");
//        game2.setYear(2006);
//        game2.setType("Fantasy");
//        game2.setId("ga002");
//        game2.setDescription("Fantasy game you can play (PG rating: PG13)");
//        game2.setRating(9);
//        games.add(game1);
//        games.add(game2);

//        browserComponent.show(games);
        browserComponent.load();
    }

    @WorkbenchPartTitle
    public String getTitle() {
        return "Browser Screen";
    }

    @WorkbenchPartView
    public IsElement getView() {
        return this;
    }

    @WorkbenchMenu
    public void getMenu(Consumer<Menus> consumer) {
        consumer.accept(MenuFactory.newTopLevelMenu("New Game").respondsWith(() -> placeManager.goTo(GameEditorPopUp.IDENTIFIER))
                                .endMenu().build());
    }

    @Override
    public HTMLElement getElement() {
        return browserComponent.getElement();
    }
}
