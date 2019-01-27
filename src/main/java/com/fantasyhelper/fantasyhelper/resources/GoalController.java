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
}
