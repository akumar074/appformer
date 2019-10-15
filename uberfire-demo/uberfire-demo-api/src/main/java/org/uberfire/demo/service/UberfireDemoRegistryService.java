package org.uberfire.demo.service;

import java.util.List;

import org.jboss.errai.bus.server.annotations.Remote;
import org.uberfire.demo.api.model.Game;
import org.uberfire.demo.api.model.GameInfo;

@Remote
public interface UberfireDemoRegistryService {

    Game add(Game game);

    List<GameInfo> getList();

    GameInfo delete(GameInfo game);

}
