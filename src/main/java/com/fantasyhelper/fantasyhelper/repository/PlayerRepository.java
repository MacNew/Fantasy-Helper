package com.fantasyhelper.fantasyhelper.repository;

import com.fantasyhelper.fantasyhelper.modle.PlayerList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<PlayerList, Integer > {

}
