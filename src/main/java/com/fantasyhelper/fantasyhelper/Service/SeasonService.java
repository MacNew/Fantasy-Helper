package com.fantasyhelper.fantasyhelper.Service;

import com.fantasyhelper.fantasyhelper.modle.Season;
import com.fantasyhelper.fantasyhelper.repository.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeasonService {
    @Autowired
    SeasonRepository seasonRepository;
    public Season saveSeason(Season season) {
        Season season1 = seasonRepository.save(season);
        if (season1 != null) {
            return season;
        } else {
            return null;
        }
    }
}
