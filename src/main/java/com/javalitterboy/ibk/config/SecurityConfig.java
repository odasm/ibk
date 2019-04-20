package com.javalitterboy.ibk.config;

import com.javalitterboy.ibk.handler.AuthenticationHandler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author 14183
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationHandler authenticationHandler;
    private final UserDetailsService userDetailsService;

    public SecurityConfig(AuthenticationHandler authenticationHandler, @Qualifier("userServiceImpl") UserDetailsService userDetailsService) {
        this.authenticationHandler = authenticationHandler;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                // 页面资源
                .antMatchers("/css/**","/help/**","/images/**","/js/**","/upload/**").permitAll()
                // 单页面
                .antMatchers("/index","/about","/product").permitAll()
                .antMatchers("/about/**","/common/**","/passport/**").permitAll()
                //.anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/product")
                .loginProcessingUrl("/passport/login_submit")
                .passwordParameter("pwd")
                .usernameParameter("username")
                .failureHandler(authenticationHandler)
                .successHandler(authenticationHandler)
                .and()
            .csrf().disable()
            .headers()
                .frameOptions()
                .disable();
    }
}
