package com.yeild.ssh.appconfig;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class BaseWebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	@Qualifier("authUserDetailService")
	UserDetailsService userDetailsService;
	
	@Autowired
	DataSource dataSource;
	
	@Autowired  
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
//		.expressionHandler(webSecurityExpressionHandler())
			.antMatchers("/develop**").access("hasRole('ROOT_DEVELOPER')")
//			.antMatchers("/users").access("hasRole('ROLE_USER')")
			.and().exceptionHandling().accessDeniedPage("/access_denied");
		
		http.formLogin().loginPage("/")
			.loginProcessingUrl("/login_action")
			.usernameParameter("username")
			.passwordParameter("password")
			.defaultSuccessUrl("/")
			.failureUrl("/?error")
			.and().rememberMe().rememberMeParameter("rememberme")
			.tokenRepository(persistentTokenRepository()).tokenValiditySeconds(1*24*60*60)
			.and().csrf();
		
		http.logout().logoutUrl("/logout")
			.logoutSuccessUrl("/?logout")
			.invalidateHttpSession(true);
		
		http.sessionManagement().sessionFixation().changeSessionId().maximumSessions(1).expiredUrl("/");
	}
	
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl tokenRepositoryImpl = new JdbcTokenRepositoryImpl();
		tokenRepositoryImpl.setDataSource(dataSource);
		return tokenRepositoryImpl;
	}
	
	@Bean(name = "expressionHandler")  
    public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler() {  
        DefaultWebSecurityExpressionHandler webSecurityExpressionHandler = new DefaultWebSecurityExpressionHandler();  
        return webSecurityExpressionHandler;  
    }
}
