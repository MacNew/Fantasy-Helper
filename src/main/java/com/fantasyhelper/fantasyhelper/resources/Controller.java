package com.fantasyhelper.fantasyhelper.resources;
import com.fantasyhelper.fantasyhelper.Service.ClubService;
import com.fantasyhelper.fantasyhelper.modle.Clubname;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fantasyhelper")
public class Controller {
    @Autowired
    ClubService clubService;
    @GetMapping("currentseasson/club")
    public List<Clubname> getAll() {
        return clubService.getCurrentSeasonClubs();
    }

    @PostMapping("insert/clubs")
    public ResponseEntity addClubs(@RequestBody Clubname clubname) {
        System.out.println("isCurrentSeassonPlaying"+ clubname.isCurrentSeasonPlaying());
      if (clubService.addClubs(clubname)) {
          return new ResponseEntity(clubname, HttpStatus.OK);
      } else {
         return new ResponseEntity(clubname.getClubName()+" alrady inserted on database", HttpStatus.BAD_REQUEST);
      }
    }
}