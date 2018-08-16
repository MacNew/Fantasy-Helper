package com.fantasyhelper.fantasyhelper.Service;

import com.fantasyhelper.fantasyhelper.modle.Clubname;
import com.fantasyhelper.fantasyhelper.repository.ClubsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubService {
    @Autowired
    ClubsRepository clubsRepository;
    public List<Clubname> getCurrentSeasonClubs() {
        return  clubsRepository.findAll();
    }

    public boolean addClubs(Clubname clubname) {
        Clubname instance =  clubsRepository.save(clubname);
        if (instance != null) {
           return  true;
        } else {
            return  false;
        }
    }
}
