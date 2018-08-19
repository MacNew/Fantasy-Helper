package com.fantasyhelper.fantasyhelper.Service;

import com.fantasyhelper.fantasyhelper.Exception.MyCustomException;
import com.fantasyhelper.fantasyhelper.modle.PlayerList;
import com.fantasyhelper.fantasyhelper.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {
    @Autowired
    PlayerRepository playerRepository;

    public boolean addPlayer(PlayerList player) throws MyCustomException {
        try {
            PlayerList playerList = playerRepository.save(player);
            if (playerList != null) {
                return true;
            } else {
                return false;
            }

        }catch (Exception e) {
           throw new MyCustomException("Player Alrady Exists");
        }
    }
}
