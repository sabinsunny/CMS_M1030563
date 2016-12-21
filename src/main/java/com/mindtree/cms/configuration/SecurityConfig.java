/**
 * 
 */
package com.mindtree.cms.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
/**
 * @author M1030563
 *
 */
@Configuration
@EnableWebSecurity
@EnableAspectJAutoProxy
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().
		antMatchers("/secure/**").access("hasRole('ROLE_ADMIN')").
		and().formLogin().  //login configuration
                loginPage("/customLogin").
                loginProcessingUrl("/appLogin").
                usernameParameter("app_username").
                passwordParameter("app_password").
                defaultSuccessUrl("/secure/home").permitAll().	
		and().logout().    //logout configuration
		logoutUrl("/appLogout"). 
		logoutSuccessUrl("/customLogin").permitAll()
		.and().csrf().disable();
	} 
	@Autowired
    private Environment environment;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser(environment.getRequiredProperty("admin.user_name")).password(environment.getRequiredProperty("admin.password")).roles("ADMIN");
	}
}
