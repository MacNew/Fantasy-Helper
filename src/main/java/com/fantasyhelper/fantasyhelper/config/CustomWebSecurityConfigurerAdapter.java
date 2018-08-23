package com.fantasyhelper.fantasyhelper.config;
import com.fantasyhelper.fantasyhelper.config.security.JwtAuthenticationProvider;
import com.fantasyhelper.fantasyhelper.config.security.JwtAuthenticationTokenFilter;
import com.fantasyhelper.fantasyhelper.config.security.JwtSucessHandler;
import com.fantasyhelper.fantasyhelper.config.security.MyAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtAuthenticationProvider authenticationProvider;

    @Bean
    public AuthenticationManager authenticationManager () {

        return new ProviderManager(Collections.singletonList(authenticationProvider));
    }

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilter() {
        JwtAuthenticationTokenFilter filter = new JwtAuthenticationTokenFilter();
        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationSuccessHandler(new JwtSucessHandler());
        return filter;
    }

     @Override
     protected void configure(HttpSecurity http) throws Exception {
       http.csrf().disable().authorizeRequests().antMatchers("/fantasyhelper/api/**").authenticated().
               and().
               exceptionHandling().authenticationEntryPoint(new MyAuthenticationEntryPoint())
               .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
       http.addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
       http.headers().cacheControl();
     }

    @SuppressWarnings("deprecation")
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
     }




}
