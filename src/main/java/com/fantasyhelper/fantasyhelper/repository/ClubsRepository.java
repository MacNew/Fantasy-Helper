package com.fantasyhelper.fantasyhelper.repository;

import com.fantasyhelper.fantasyhelper.modle.ClubName;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface ClubsRepository extends JpaRepository<ClubName, Integer > {
    ClubName findById(int clubid);
    ClubName removeById(int clubid);



}
