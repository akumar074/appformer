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
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.uberfire.client.mvp.PlaceManager;
import org.uberfire.demo.api.model.Game;
import org.uberfire.demo.client.event.GameDeleteEvent;

@Dependent
public class GameEditor implements GameEditorView.Presenter {

    private GameEditorView view;

    @Inject
    public GameEditor(GameEditorView view) {
        this.view = view;
    }

    public void showContent(Game content) {
        view.showContent(content);
    }

    public Game getContent() {
        return view.getContent();
    }

    public GameEditorView getView() {
        return view;
    }
}
