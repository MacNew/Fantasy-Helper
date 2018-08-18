package com.fantasyhelper.fantasyhelper.resources;
import com.fantasyhelper.fantasyhelper.Exception.MyCustomException;
import com.fantasyhelper.fantasyhelper.Service.ClubService;
import com.fantasyhelper.fantasyhelper.config.RoleService;
import com.fantasyhelper.fantasyhelper.modle.Clubname;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/")
    public String noSecured() {
        return "It is not Secured";
    }

    @RequestMapping( method = RequestMethod.POST , value = "fantasyhelper/insert/clubs")
    public ResponseEntity addClubs(@RequestBody Clubname clubname, @AuthenticationPrincipal final UserDetails userDetails) throws MyCustomException {
        if (roleService.checkRoleOfAdmin(userDetails)) {
            if(clubService.addClubs(clubname)) {
                return new ResponseEntity(clubname, HttpStatus.OK);
            }
        }else {
            throw new MyCustomException("Sorry you are not an Admin");
        }
        return new ResponseEntity("Error",HttpStatus.INTERNAL_SERVER_ERROR);
    }
}