package com.fantasyhelper.fantasyhelper.repository;

import com.fantasyhelper.fantasyhelper.modle.ClubName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubsRepository extends JpaRepository<ClubName, Integer > {
    public ClubName findById(int clubId);
}
