package com.CODEns.BackendAPI.Configurations;

import javax.sql.DataSource;

import com.CODEns.BackendAPI.Security.JwtAuthenticationEntryPoint;
import com.CODEns.BackendAPI.Security.JwtRequestFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
 
@EnableWebSecurity
@ComponentScan(value="com.CODEns")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
 
    @Autowired
	private DataSource dataSource;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;
    
    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
			.jdbcAuthentication()
				.dataSource(dataSource)
				.usersByUsernameQuery(
                        "select username, password, enabled from user where username=?")
                .authoritiesByUsernameQuery(
                        "select username, authority from user where username=?")
                .passwordEncoder(this.passwordEncoder);
    }*/
 
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // We don't need CSRF for this example
        http.cors().and().csrf().disable()
        // dont authenticate this particular request
        .authorizeRequests().antMatchers("/admin/**").hasAuthority("1").and()
        .authorizeRequests().anyRequest().permitAll().and()
        // all other requests need to be authenticated
        // make sure we use stateless session; session won't be used to
        // store user's state.
        .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // Add a filter to validate the tokens with every request
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);




        //http.authorizeRequests().anyRequest().permitAll()
        /*.antMatchers("/movies/**").hasAuthority("1")
        .antMatchers("/login").permitAll()
        .antMatchers("/admin/**").hasRole("ADMIN")
        .antMatchers("/**").hasAnyRole("ADMIN", "USER")
        */
        /*.and().formLogin()
        .and().logout().logoutSuccessUrl("/login").permitAll()
        .and().csrf().disable();*/
    }
}
