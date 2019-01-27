package com.fantasyhelper.fantasyhelper.resources;

import com.fantasyhelper.fantasyhelper.Exception.MyCustomException;
import com.fantasyhelper.fantasyhelper.config.RoleService;
import com.fantasyhelper.fantasyhelper.modle.GoalUpdate;
import com.fantasyhelper.fantasyhelper.repository.GoalUpdateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("fantasyhelper/api/")
public class GoalController {
    @Autowired
    GoalUpdateRepository repository;
    @Autowired
    RoleService roleService;

    @PostMapping("insert/goal")
    public ResponseEntity saveGoalInformation(@RequestBody GoalUpdate goalUpdate, @AuthenticationPrincipal final UserDetails userDetails) {
        if (roleService.isAdmin(userDetails)) {
            return new ResponseEntity(repository.save(goalUpdate), HttpStatus.OK);
        } else {
            return new ResponseEntity(new MyCustomException("You are not a server Admin"), HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("getGoalInformation/{clubId}/{seasonId}/{playerId}")
    public ResponseEntity getGoalInformation(@AuthenticationPrincipal final UserDetails userDetails,
                                             @PathVariable String clubId,
                                             @PathVariable String playerId,
                                             @PathVariable String seasonId) {
        if (roleService.isAdmin(userDetails)) {
            return new ResponseEntity(repository.findByClubIdAndSeasonIdAndPlayerId(Integer.parseInt(clubId)
            , Integer.parseInt(seasonId), Integer.parseInt(playerId)
            ), HttpStatus.OK);

        } else {
            return new ResponseEntity(new MyCustomException("Your are not a server Admin"), HttpStatus.FORBIDDEN);
        }
    }
}
