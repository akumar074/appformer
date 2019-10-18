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

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.google.gwt.user.client.ui.IsWidget;
import org.uberfire.backend.vfs.Path;
import org.uberfire.client.workbench.type.ClientResourceType;
import org.uberfire.workbench.category.Category;
import org.uberfire.workbench.category.Others;

@ApplicationScoped
public class DemoGameResourceType implements ClientResourceType {

    private Category category;

    public DemoGameResourceType() {
    }

    @Inject
    public DemoGameResourceType(final Others category) {
        this.category = category;
    }

    @Override
    public Category getCategory() {
        return this.category;
    }

    @Override
    public String getShortName() {
        return "Game Asset";
    }

    @Override
    public String getDescription() {
        return "Game Asset";
    }

    @Override
    public IsWidget getIcon() {
        return null;
    }

    @Override
    public String getPrefix() {
        return "";
    }

    @Override
    public String getSuffix() {
        return "game";
    }

    @Override
    public int getPriority() {
        return 1000;
    }

    @Override
    public String getSimpleWildcardPattern() {
        return "*.game";
    }

    @Override
    public boolean accept(final Path path) {
        return path.getFileName().endsWith("" + getSuffix());
    }
}
