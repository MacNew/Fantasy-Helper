package com.fantasyhelper.fantasyhelper.repository;

import com.fantasyhelper.fantasyhelper.modle.PlayerList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<PlayerList, Integer > {
     List<PlayerList> findByClubid(int clubid);
     List<PlayerList> findByPlayerPosition(String position);
}
