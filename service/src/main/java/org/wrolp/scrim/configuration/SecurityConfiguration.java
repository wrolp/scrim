package org.wrolp.scrim.configuration;

import java.io.Writer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.wrolp.scrim.data.entity.User;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private JpaUserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailsService).passwordEncoder(User.PASSWORD_ENCODER);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
            .authorizeRequests()
                .antMatchers("/static/**", "/logout").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .successHandler((request, response, authentication) -> {
                    Writer writer = response.getWriter();
                    writer.write("{\"success\": true}");
                    writer.flush();
                })
                .failureHandler((request, response, exception) -> {
                    Writer writer = response.getWriter();
                    writer.write("{\"success\": false}");
                    writer.flush();
                })
                .permitAll()
                .and()
            .httpBasic().disable()
//                .and()
            .csrf().disable()
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler((request, response, authentication) -> {
                    Writer writer = response.getWriter();
                    writer.write("{\"success\": true}");
                    writer.flush();
                });
        // @formatter:on
    }

}
