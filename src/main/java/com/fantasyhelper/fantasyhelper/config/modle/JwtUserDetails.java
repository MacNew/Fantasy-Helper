package com.fantasyhelper.fantasyhelper.config.modle;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class JwtUserDetails  implements UserDetails {
   private String userName;
   private String token;
   private String password;
   private Collection<? extends GrantedAuthority> authorities;

    public JwtUserDetails(String userName, String password, String token, List<GrantedAuthority> grantedAuthorities) {
     this.userName = userName;
     this.password = password;
     this.token = token;
     this.authorities = grantedAuthorities;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }


    public String getToken() {
        return token;
    }







    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
