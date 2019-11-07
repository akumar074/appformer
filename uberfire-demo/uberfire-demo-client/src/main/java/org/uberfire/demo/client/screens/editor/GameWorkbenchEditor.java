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

import java.util.function.Consumer;
import java.util.function.Supplier;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import com.google.gwt.user.client.ui.IsWidget;
import elemental2.promise.Promise;
import org.jboss.errai.common.client.api.Caller;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.uberfire.backend.vfs.ObservablePath;
import org.uberfire.client.annotations.WorkbenchEditor;
import org.uberfire.client.annotations.WorkbenchMenu;
import org.uberfire.client.annotations.WorkbenchPartTitle;
import org.uberfire.client.annotations.WorkbenchPartTitleDecoration;
import org.uberfire.client.annotations.WorkbenchPartView;
import org.uberfire.client.mvp.PlaceManager;
import org.uberfire.demo.api.model.Game;
import org.uberfire.demo.api.model.GameInfo;
import org.uberfire.demo.client.event.GameDetailEvent;
import org.uberfire.demo.client.event.RefreshBrowserEvent;
import org.uberfire.demo.client.screens.browser.game.DemoGameResourceType;
import org.uberfire.demo.service.UberfireDemoRegistryService;
import org.uberfire.ext.editor.commons.client.BaseEditor;
import org.uberfire.ext.editor.commons.file.DefaultMetadata;
import org.uberfire.ext.editor.commons.service.support.SupportsDelete;
import org.uberfire.lifecycle.OnStartup;
import org.uberfire.mvp.PlaceRequest;
import org.uberfire.workbench.model.menu.Menus;

@Dependent
@WorkbenchEditor(identifier = GameWorkbenchEditor.IDENTIFIER, supportedTypes = DemoGameResourceType.class)
public class GameWorkbenchEditor extends BaseEditor<Game, DefaultMetadata> {

    public static final String IDENTIFIER = "GameEditor";

    private final DemoGameResourceType resourceType;
    private final GameEditor editor;
    private final Caller<UberfireDemoRegistryService> serviceCaller;

    @Inject
    private Event<RefreshBrowserEvent> refreshBrowserEvent;

    @Inject
    private Event<GameDetailEvent> gameDetailEvent;

    @Inject
    private PlaceManager placeManager;

    @Inject
    public GameWorkbenchEditor(final DemoGameResourceType resourceType, final GameEditor editor,
                               final Caller<UberfireDemoRegistryService> serviceCaller) {
        super(editor.getView());
        this.resourceType = resourceType;
        this.editor = editor;
        this.serviceCaller = serviceCaller;
    }

    @Override
    protected void loadContent() {
        serviceCaller.call((RemoteCallback<GameInfo>) response -> {
            editor.showContent(response.getGame());
        }).viewGame(versionRecordManager.getCurrentPath());
        baseView.hideBusyIndicator();
    }

    @OnStartup
    public void onStartup(final ObservablePath path,
                          final PlaceRequest place) {
        init(path,
             place,
             resourceType);
    }

    @Override
    protected Promise<Void> makeMenuBar() {
        menuBuilder.addSave(this::save);
        menuBuilder.addDelete(this::delete);
        return promises.resolve();
    }

    @Override
    protected Supplier<Game> getContentSupplier() {
        return editor::getContent;
    }

    @Override
    protected Caller<? extends SupportsDelete> getDeleteServiceCaller() {
        return serviceCaller;
    }

    private void delete() {
        final Game game = editor.getContent();
        serviceCaller.call((RemoteCallback<Void>) response -> {
            placeManager.closePlace(place);
            refreshBrowserEvent.fire(new RefreshBrowserEvent());
        }).delete(game);
        concurrentUpdateSessionInfo = null;
    }

    @Override
    protected void save() {
        final Game game = editor.getContent();
        serviceCaller.call((RemoteCallback<Void>) response -> {
            placeManager.closePlace(place);
            refreshBrowserEvent.fire(new RefreshBrowserEvent());
        }).add(game);
        concurrentUpdateSessionInfo = null;
    }

    @Override
    @WorkbenchPartTitleDecoration
    public IsWidget getTitle() {
        return super.getTitle();
    }

    @Override
    @WorkbenchPartTitle
    public String getTitleText() {
        return "Demo Game Editor [" + versionRecordManager.getCurrentPath().getFileName() + "]";
    }

    @WorkbenchMenu
    public void getMenus(final Consumer<Menus> menusConsumer) {
        super.getMenus(menusConsumer);
    }

    @WorkbenchPartView
    public IsWidget getView() {
        return editor.getView();
    }
}
