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

package org.uberfire.demo.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.google.gson.Gson;
import org.jboss.errai.bus.server.annotations.Service;
import org.uberfire.backend.server.util.Paths;
import org.uberfire.demo.api.model.Game;
import org.uberfire.demo.api.model.GameInfo;
import org.uberfire.io.IOService;
import org.uberfire.java.nio.file.FileSystemAlreadyExistsException;
import org.uberfire.spaces.Space;
import org.uberfire.spaces.SpacesAPI;
import org.uberfire.java.nio.file.Path;
import org.uberfire.java.nio.file.FileSystem;

@Service
@ApplicationScoped
public class UberfireDemoRegistryServiceImpl implements UberfireDemoRegistryService {

    public static final String STORAGE_PATH = "gamelib";

    public static final String SEPARATOR = "/";

    public static final String EXTENSION = ".game";

    @Inject
    private SpacesAPI spaces;
    private FileSystem fileSystem;

    @Inject
    @Named("configIO")
    private IOService iOService;
    private Gson gson = new Gson();

    List<GameInfo> gameList = new ArrayList<>();

    @PostConstruct
    public void init() {
        initializeFileSystem();
    }

    @Override
    public void add(Game game) {
        Path fsPath = fileSystem.getPath(game.getId() + EXTENSION);
        iOService.write(fsPath, gson.toJson(game));
    }

    @Override
    public List<GameInfo> getList() {
        Path fsPath = fileSystem.getPath("/");
        final List<GameInfo> result = new ArrayList<>();
        iOService.newDirectoryStream(fsPath, entry -> entry.getFileName().toString().endsWith(EXTENSION))
                .forEach(assetPath -> result.add(readObjectFromFile(assetPath)));
        return result;
    }

    @Override
    public void delete(Game game) {
        Path fsPath = fileSystem.getPath(game.getId() + EXTENSION);
        iOService.deleteIfExists(fsPath);
    }

    @Override
    public GameInfo viewGame(org.uberfire.backend.vfs.Path path) {
        Path fspath = Paths.convert(path);
        return readObjectFromFile(fspath);
    }

    public GameInfo readObjectFromFile(Path path) {
        String result = iOService.readAllString(path);
        Game game = gson.fromJson(result, Game.class);
        GameInfo gameInfo = new GameInfo(game);
        gameInfo.setPath(Paths.convert(path));
        return gameInfo;
    }

    protected void initializeFileSystem() {
        final URI fileSystemURI = spaces.resolveFileSystemURI(SpacesAPI.Scheme.GIT, new Space("game-factory"), "game");
        try {
            Map<String, Object> options = new HashMap<>();
            options.put("init", Boolean.TRUE);
            options.put("internal", Boolean.TRUE);

            fileSystem = iOService.newFileSystem(fileSystemURI, options);
        } catch (FileSystemAlreadyExistsException e) {
            fileSystem = iOService.getFileSystem(fileSystemURI);
        }
    }

    @Override
    public void delete(org.uberfire.backend.vfs.Path path, String comment) {
        Path fsPath = Paths.convert(path);
        iOService.deleteIfExists(fsPath);
    }
}
