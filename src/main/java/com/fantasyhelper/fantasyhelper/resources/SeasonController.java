package com.fantasyhelper.fantasyhelper.resources;

import com.fantasyhelper.fantasyhelper.Exception.MyCustomException;
import com.fantasyhelper.fantasyhelper.Service.SeasonService;
import com.fantasyhelper.fantasyhelper.config.RoleService;
import com.fantasyhelper.fantasyhelper.modle.Season;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("fantasyhelper/api/")
public class SeasonController {
    @Autowired
    SeasonService seasonService;
    @Autowired
    RoleService roleService;
    @PostMapping("season/save")
    public ResponseEntity saveSeasonInformation(@RequestBody Season season, @AuthenticationPrincipal final UserDetails userDetails) {
        if (roleService.isAdmin(userDetails)) {
            return new ResponseEntity(seasonService.saveSeason(season) != null ? season: new MyCustomException("Can't save your season"), seasonService.saveSeason(season) != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(new MyCustomException("You are not Server Admin or User"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("season/getAll")
    public ResponseEntity getAll(@AuthenticationPrincipal final UserDetails userDetails) {
        if (roleService.isUserOrAdmin(userDetails)) {
            return new ResponseEntity(seasonService.getAll() != null ? seasonService.getAll(): new MyCustomException("Sorry, data is not available"), seasonService.getAll() != null ? HttpStatus.OK: HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(new MyCustomException("You are not Server Admin or User "), HttpStatus.BAD_REQUEST);
        }

    }


}
