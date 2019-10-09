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

package org.uberfire.demo.client.perspective;

import java.util.function.Consumer;

import javax.enterprise.context.ApplicationScoped;

import com.google.gwt.user.client.Window;
import org.uberfire.client.annotations.Perspective;
import org.uberfire.client.annotations.WorkbenchMenu;
import org.uberfire.client.annotations.WorkbenchPerspective;
import org.uberfire.client.workbench.panels.impl.SimpleWorkbenchPanelPresenter;
import org.uberfire.demo.client.screens.browser.BrowserScreen;
import org.uberfire.demo.client.screens.details.DetailsScreen;
import org.uberfire.mvp.impl.DefaultPlaceRequest;
import org.uberfire.workbench.model.CompassPosition;
import org.uberfire.workbench.model.PanelDefinition;
import org.uberfire.workbench.model.PerspectiveDefinition;
import org.uberfire.workbench.model.impl.PanelDefinitionImpl;
import org.uberfire.workbench.model.impl.PartDefinitionImpl;
import org.uberfire.workbench.model.impl.PerspectiveDefinitionImpl;
import org.uberfire.workbench.model.menu.MenuFactory;
import org.uberfire.workbench.model.menu.MenuPosition;
import org.uberfire.workbench.model.menu.Menus;

@ApplicationScoped
@WorkbenchPerspective(identifier = DemoPerspective.IDENTIFIER)
public class DemoPerspective {

    public static final String IDENTIFIER = "DemoPerspective";

    @Perspective
    public PerspectiveDefinition buildPerspective() {
        final PerspectiveDefinition perspective = new PerspectiveDefinitionImpl(SimpleWorkbenchPanelPresenter.class.getName());
        perspective.setName("Demo Perspective");
        perspective.getRoot().addPart(new PartDefinitionImpl(new DefaultPlaceRequest(DetailsScreen.IDENTIFIER)));
        final PanelDefinition west = new PanelDefinitionImpl(SimpleWorkbenchPanelPresenter.class.getName());
        west.setWidth(500);
        west.setMinWidth(500);
        west.addPart(new PartDefinitionImpl(new DefaultPlaceRequest(BrowserScreen.IDENTIFIER)));
        perspective.getRoot().insertChild(CompassPosition.WEST, west);
        return perspective;
    }

    @WorkbenchMenu
    public void getMenus(final Consumer<Menus> consumer) {
        consumer.accept(MenuFactory.newTopLevelMenu("Do Nothing").respondsWith(() -> Window.alert("Hola"))
                                .position(MenuPosition.RIGHT).endMenu().build());
    }
}
