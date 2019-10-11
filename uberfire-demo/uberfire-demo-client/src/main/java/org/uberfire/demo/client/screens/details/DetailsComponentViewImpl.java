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

import javax.inject.Inject;
import javax.inject.Named;

import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLTableCellElement;
import org.jboss.errai.ui.client.local.api.elemental2.IsElement;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.uberfire.demo.api.model.Game;

@Templated
public class DetailsComponentViewImpl implements DetailsComponentView,
                                                 IsElement {

    @Inject
    @DataField("title")
    @Named("td")
    private HTMLTableCellElement title;

    @Inject
    @DataField("year")
    @Named("td")
    private HTMLTableCellElement year;

    @Inject
    @DataField("id")
    @Named("td")
    private HTMLTableCellElement id;

    @Inject
    @DataField("type")
    @Named("td")
    private HTMLTableCellElement type;

    @Inject
    @DataField("description")
    @Named("td")
    private HTMLTableCellElement description;

    @Inject
    @DataField("rating")
    @Named("td")
    private HTMLTableCellElement rating;

    private Presenter presenter;

    @Override
    public void show(Game game) {
        title.textContent = game.getTitle();
        year.textContent = "" + game.getYear();
        id.textContent = game.getId();
        type.textContent = game.getType();
        description.textContent = game.getDescription();
        rating.textContent = "" + game.getRating();
    }

    @Override
    public void init(Presenter presenter) {
        this.presenter = presenter;
    }
}
