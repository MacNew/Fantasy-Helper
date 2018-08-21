package com.fantasyhelper.fantasyhelper.config;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class RoleService {

    public  boolean isUserOrAdmin(final  UserDetails userDetails) {
        return  myCollectionGrantedAuthority(userDetails).contains(simpleGrantedAurity(StaticConstants.ROLE_USER))
                ||
                myCollectionGrantedAuthority(userDetails).contains(simpleGrantedAurity(StaticConstants.ROLE_ADMIN));
    }

    public  boolean isAdmin(final  UserDetails userDetails) {
        return myCollectionGrantedAuthority(userDetails).contains(simpleGrantedAurity(StaticConstants.ROLE_ADMIN));
    }

    public  boolean isUser(final  UserDetails userDetails) {
        return myCollectionGrantedAuthority(userDetails).contains(simpleGrantedAurity(StaticConstants.ROLE_USER));
    }

    public Collection<? extends GrantedAuthority> myCollectionGrantedAuthority(final  UserDetails userDetails) {
        return userDetails.getAuthorities();
    }
    public SimpleGrantedAuthority simpleGrantedAurity(String role) {
        return new SimpleGrantedAuthority(role);
    }

}
