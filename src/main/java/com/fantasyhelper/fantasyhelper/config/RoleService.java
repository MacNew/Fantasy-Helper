package com.fantasyhelper.fantasyhelper.config;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RoleService {

    public  boolean checkRoleOfUser(final  UserDetails userDetails) {
        return  myCollectionGrantedAuthority(userDetails).contains(simpleGrantedAurity("ROLE_USER"));
    }

    public  boolean checkRoleOfAdmin(final  UserDetails userDetails) {
        return myCollectionGrantedAuthority(userDetails).contains(simpleGrantedAurity("ROLE_ADMIN"));
    }

    public Collection<? extends GrantedAuthority> myCollectionGrantedAuthority(final  UserDetails userDetails) {
        return userDetails.getAuthorities();
    }
    public SimpleGrantedAuthority simpleGrantedAurity(String role) {
        return new SimpleGrantedAuthority(role);
    }

}
