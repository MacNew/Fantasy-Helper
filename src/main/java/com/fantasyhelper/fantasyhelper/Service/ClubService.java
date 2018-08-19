package com.fantasyhelper.fantasyhelper.Service;

import com.fantasyhelper.fantasyhelper.Exception.MyCustomException;
import com.fantasyhelper.fantasyhelper.modle.ClubName;
import com.fantasyhelper.fantasyhelper.repository.ClubsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubService {
    @Autowired
    ClubsRepository clubsRepository;
    public List<ClubName> getCurrentSeasonClubs() {
        return  clubsRepository.findAll();
    }

    public boolean addClubs(ClubName clubname) throws MyCustomException {
        try {
            ClubName instance =  clubsRepository.save(clubname);
            if (instance != null) {
                return  true;
            } else {
                return  false;
            }
        }catch (Exception e) {
            throw  new MyCustomException("This club is alrady exists");
        }
    }
}
