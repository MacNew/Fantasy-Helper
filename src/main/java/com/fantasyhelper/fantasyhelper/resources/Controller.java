package com.fantasyhelper.fantasyhelper.resources;
import com.fantasyhelper.fantasyhelper.Service.ClubService;
import com.fantasyhelper.fantasyhelper.config.RoleService;
import com.fantasyhelper.fantasyhelper.modle.Clubname;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class Controller {
    @Autowired
    ClubService clubService;
    @Autowired
    RoleService roleService;
    @GetMapping("fantasyhelper/currentseason/get/clubs")
    public ResponseEntity getAll(@AuthenticationPrincipal final UserDetails userDetails) {
        if (roleService.checkRoleOfUser(userDetails)) {
            return new ResponseEntity(clubService.getCurrentSeasonClubs(), HttpStatus.OK);
        } else {
            return new ResponseEntity("You are not a user", HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("noSecured")
    public String noSecured() {
        return "It is not Secured";
    }

    @RequestMapping( method = RequestMethod.POST , value = "fantasyhelper/insert/clubs")
    public ResponseEntity addClubs(@RequestBody Clubname clubname, @AuthenticationPrincipal final UserDetails userDetails) {

        if (roleService.checkRoleOfAdmin(userDetails)) {
            if (clubService.addClubs(clubname)) {
                return new ResponseEntity(clubname, HttpStatus.OK);
            } else {
                return new ResponseEntity(clubname.getClubName()+" alrady inserted on database", HttpStatus.BAD_REQUEST);
            }
        } else {
            return  new ResponseEntity("You are not a admin", HttpStatus.BAD_REQUEST);
        }
    }
}