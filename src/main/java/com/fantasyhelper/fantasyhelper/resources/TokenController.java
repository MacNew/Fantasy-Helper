package com.fantasyhelper.fantasyhelper.resources;

import com.fantasyhelper.fantasyhelper.config.StaticConstants;
import com.fantasyhelper.fantasyhelper.config.security.JwtGenerator;
import com.fantasyhelper.fantasyhelper.config.modle.JwtUser;
import com.fantasyhelper.fantasyhelper.modle.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("fantasyhelper/")
public class TokenController {
    private JwtGenerator jwtGenerator;
    @Autowired
    JwtToken jwtToken;
    public TokenController(JwtGenerator jwtGenerator) {
        this.jwtGenerator = jwtGenerator;
    }
    @PostMapping("genratetoken")
    public ResponseEntity generate(@RequestBody final JwtUser jwtUser) {
        String role = TokenController.getRole(jwtUser);
        if (role != null) {
            if (TokenController.checkUserNameAndRole(jwtUser, role)) {
                jwtUser.setRole(role);
                jwtToken.setToken(jwtGenerator.generate(jwtUser));
                return new ResponseEntity(jwtToken, HttpStatus.OK);
            }
        }
            return new ResponseEntity("Un authorized",HttpStatus.UNAUTHORIZED);
    }

    public static String getRole(JwtUser jwtUser) {
        String role = null;
        if (jwtUser.getUserName().equalsIgnoreCase("MacNew") && jwtUser.getPassword().equalsIgnoreCase("test")) {
            role = "ROLE_ADMIN";
        } else if (jwtUser.getUserName().equalsIgnoreCase("hari") && jwtUser.getPassword().equalsIgnoreCase("user")) {
            role = "ROLE_USER";
        } else {
            role = null;
        }
        return role;
    }


    public static boolean checkUserNameAndRole(JwtUser jwtUser, String role) {

            JwtUser admin = new JwtUser("MacNew", "test",role);
            JwtUser user = new JwtUser("hari","user",role);
            if (jwtUser.getUserName().equalsIgnoreCase(admin.getUserName())
                    && jwtUser.getPassword().equalsIgnoreCase(admin.getPassword())
                    && role.equalsIgnoreCase(StaticConstants.ROLE_ADMIN))
             {
                return  true;
            } else if (jwtUser.getUserName().equalsIgnoreCase(user.getUserName())
                    && jwtUser.getPassword().equalsIgnoreCase(user.getPassword())
                    && role.equalsIgnoreCase(StaticConstants.ROLE_USER))
             {
                return true;
            }else {
                return false;
            }
    }
}
