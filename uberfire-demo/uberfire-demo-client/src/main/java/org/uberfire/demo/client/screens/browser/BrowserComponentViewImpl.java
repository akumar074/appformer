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

import javax.inject.Inject;

import elemental2.dom.HTMLDivElement;
import org.jboss.errai.ui.client.local.api.elemental2.IsElement;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.uberfire.demo.client.screens.browser.game.GameComponent;

@Templated
public class BrowserComponentViewImpl implements BrowserComponentView,
                                                 IsElement {

    @Inject
    @DataField
    private HTMLDivElement gameList;

    private Presenter presenter;

    @Override
    public void show(GameComponent gameComponent) {
        this.gameList.appendChild(gameComponent.getElement());
    }

    @Override
    public void init(Presenter presenter) {
        this.presenter = presenter;
    }
}
