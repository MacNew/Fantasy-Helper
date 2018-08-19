package com.fantasyhelper.fantasyhelper.resources;
import com.fantasyhelper.fantasyhelper.Exception.MyCustomException;
import com.fantasyhelper.fantasyhelper.Service.ClubService;
import com.fantasyhelper.fantasyhelper.Service.PlayerService;
import com.fantasyhelper.fantasyhelper.config.RoleService;
import com.fantasyhelper.fantasyhelper.modle.ClubName;
import com.fantasyhelper.fantasyhelper.modle.PlayerList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("fantasyhelper/")
public class Controller {
    @Autowired
    ClubService clubService;
    @Autowired
    PlayerService playerService;
    @Autowired
    RoleService roleService;
    @GetMapping("currentseason/get/clubs")
    public ResponseEntity getAll(@AuthenticationPrincipal final UserDetails userDetails) {
        if (roleService.checkRoleOfUser(userDetails)) {
            return new ResponseEntity(clubService.getCurrentSeasonClubs(), HttpStatus.OK);
        } else {
            return new ResponseEntity("You are not a user", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("insert/clubs")
    public ResponseEntity addClubs(@RequestBody ClubName clubname, @AuthenticationPrincipal final UserDetails userDetails) throws MyCustomException {
        if (roleService.checkRoleOfAdmin(userDetails)) {
            if(clubService.addClubs(clubname)) {
                return new ResponseEntity(clubname, HttpStatus.OK);
            }
        }else {
            throw new MyCustomException("Sorry you Aere not an Admin");
        }
        return new ResponseEntity("Error",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("insert/player")
    public ResponseEntity addPlayers(@RequestBody PlayerList player,
                                      @AuthenticationPrincipal final UserDetails userDetails) throws MyCustomException {
      if (roleService.checkRoleOfAdmin(userDetails)) {
          if (playerService.addPlayer(player)) {
              return new ResponseEntity(player, HttpStatus.OK);
          }
      }
        throw new MyCustomException("Sorry you Aere not an Admin");
    }

}