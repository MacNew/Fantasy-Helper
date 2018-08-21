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
         if (roleService.isUserOrAdmin(userDetails)) {
             return new ResponseEntity(clubService.getCurrentSeasonClubs(), HttpStatus.OK);
         } else {
             return new ResponseEntity("You are not an User", HttpStatus.UNAUTHORIZED);
         }
    }

    @PostMapping("insert/clubs")
    public ResponseEntity addClubs(@RequestBody ClubName clubname, @AuthenticationPrincipal final UserDetails userDetails) throws MyCustomException {
        if (roleService.isAdmin(userDetails)) {
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
      if (roleService.isAdmin(userDetails)) {
          if (playerService.addPlayer(player)) {
              return new ResponseEntity(player, HttpStatus.OK);
          }
      }
        throw new MyCustomException("Sorry you Aere not an Admin");
    }

    @GetMapping("get/player/{club_id}")
    public ResponseEntity getPlayersList(@AuthenticationPrincipal final  UserDetails userDetails, @PathVariable("club_id") Integer clubId) throws MyCustomException{
        if (roleService.isUserOrAdmin(userDetails)) {
            return new ResponseEntity(playerService.getPlayerList(clubId),HttpStatus.OK);
        } else {
            return new ResponseEntity("Sorry you are not an User", HttpStatus.OK);
        }
    }

    @GetMapping("admin")
    public ResponseEntity isAdmin(@AuthenticationPrincipal final  UserDetails userDetails) {
         if (roleService.isAdmin(userDetails)) {
             return new ResponseEntity("Yes you are Admin", HttpStatus.OK);
         }
         return new ResponseEntity("Sorry you are not a admin", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("useroradmin")
    public ResponseEntity isuserOrAdmin(@AuthenticationPrincipal final  UserDetails userDetails) {
        if (roleService.isUserOrAdmin(userDetails)) {
            return new ResponseEntity("Yes you are either user or Admin", HttpStatus.OK);
        }
        return new ResponseEntity("Sorry you are not a user or Admin", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("user")
    public ResponseEntity isUser(@AuthenticationPrincipal final  UserDetails userDetails) {
        if (roleService.isUser(userDetails)) {
            return new ResponseEntity("yes you are user", HttpStatus.OK);
        }
        return new ResponseEntity("Sorry you are not a user ", HttpStatus.UNAUTHORIZED);
    }

}