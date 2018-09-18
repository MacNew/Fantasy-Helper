package com.fantasyhelper.fantasyhelper.resources;

import com.fantasyhelper.fantasyhelper.Exception.MyCustomException;
import com.fantasyhelper.fantasyhelper.Service.PlayerService;
import com.fantasyhelper.fantasyhelper.config.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("fantasyhelper/api/")
public class Player {
    @Autowired
    PlayerService playerService;
    @Autowired
    RoleService roleService;
    @GetMapping("getplayer/{position}")
    public ResponseEntity getAlltheForwards(@AuthenticationPrincipal final UserDetails userDetails, @PathVariable String position) throws MyCustomException {

        if (roleService.isUserOrAdmin(userDetails)) {
            return new ResponseEntity(playerService.getForwardList(position), HttpStatus.OK);
        } else {
            return new ResponseEntity(new MyCustomException("You are not Server Admin or User"),HttpStatus.BAD_REQUEST);
        }
    }
}
