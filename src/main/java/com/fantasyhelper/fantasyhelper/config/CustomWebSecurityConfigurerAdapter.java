package com.fantasyhelper.fantasyhelper.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
             auth.inMemoryAuthentication()
            .withUser("MacNew").password("test").roles("ADMIN","USER").and().withUser("hari")
            .password("user").roles("USER");
    }

     @Override
     protected void configure(HttpSecurity httpSecurity) throws Exception {
       httpSecurity.csrf().disable()
       .authorizeRequests().antMatchers("/fantasyhelper/insert/**").
               fullyAuthenticated().and().authorizeRequests().antMatchers("fantasyhelper/currentseason/get/**").fullyAuthenticated().
       and().exceptionHandling().authenticationEntryPoint(new MyAuthenticationEntryPoint()).and().httpBasic();
    }

    @SuppressWarnings("deprecation")
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
     }
}
