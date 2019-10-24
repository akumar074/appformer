package org.uberfire.demo.service;

import java.util.List;

import org.jboss.errai.bus.server.annotations.Remote;
import org.uberfire.backend.vfs.Path;
import org.uberfire.demo.api.model.Game;
import org.uberfire.demo.api.model.GameInfo;
import org.uberfire.ext.editor.commons.service.support.SupportsDelete;

@Remote
public interface UberfireDemoRegistryService extends SupportsDelete {

    Game add(Game game);

    List<GameInfo> getList();

    GameInfo viewGame(Path path);

    GameInfo delete(GameInfo game);

}
