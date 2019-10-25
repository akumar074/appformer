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

import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import elemental2.dom.HTMLButtonElement;
import elemental2.dom.HTMLInputElement;
import org.jboss.errai.ui.client.local.api.elemental2.IsElement;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.uberfire.demo.api.model.Game;

@Templated
public class GameEditorComponentViewImpl implements GameEditorComponentView,
                                                    IsElement {

    @Inject
    @DataField
    private HTMLInputElement title;

    @Inject
    @DataField
    private HTMLInputElement year;

    @Inject
    @DataField
    private HTMLInputElement id;

    @Inject
    @DataField
    private HTMLInputElement type;

    @Inject
    @DataField
    private HTMLInputElement description;

    @Inject
    @DataField
    private HTMLInputElement rating;

    @Inject
    @DataField
    private HTMLButtonElement submitGame;

    private Presenter presenter;

    @Override
    public void init(Presenter presenter) {
        this.presenter = presenter;
    }

    @EventHandler("submitGame")
    public void onSubmitGame(ClickEvent event) {
        presenter.createGame();
    }

    @Override
    public void show(GameEditorComponent gameEditorComponent) {
    }

    @Override
    public void show(Game game) {
        title.value = game.getTitle();
        year.value = "" + game.getYear();
        id.value = game.getId();
        type.value = game.getType();
        rating.value = "" + game.getRating();
        description.value = game.getDescription();
        id.disabled = (game != null);
    }

    public String getTitle() {
        return title.value;
    }

    public int getYear() {
        return Integer.parseInt(year.value);
    }

    public String getId() {
        return id.value;
    }

    public String getType() {
        return type.value;
    }

    public String getDescription() {
        return description.value;
    }

    public int getRating() {
        return Integer.parseInt(rating.value);
    }
}
