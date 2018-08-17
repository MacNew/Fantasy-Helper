package com.fantasyhelper.fantasyhelper.config;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RoleService {

    public  boolean checkRoleOfUser(final  UserDetails userDetails) {
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        boolean authorized = authorities.contains(new SimpleGrantedAuthority("ROLE_USER"));
        return  authorized;
    }

    public  boolean checkRoleOfAdmin(final  UserDetails userDetails) {
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        boolean authorized = authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return  authorized;
    }

}
