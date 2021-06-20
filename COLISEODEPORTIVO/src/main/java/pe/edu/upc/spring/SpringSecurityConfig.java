package pe.edu.upc.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import pe.edu.upc.spring.auth.handler.LoginSuccessHandler;
import pe.edu.upc.spring.serviceimpl.JpaUserDetailsService;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JpaUserDetailsService userDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private LoginSuccessHandler successHandler;
	
	protected void configure(HttpSecurity http) throws Exception {
		try {
			http.authorizeRequests()
				//.antMatchers("/role/**").access("hasRole('ROLE_ADMIN')")
				//.antMatchers("/user/**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/sportcenter/**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/sport/**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/schedule/**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/sportfield/**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/fieldschedule/**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/booking/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
				.antMatchers("/welcome/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')").and()
				.formLogin().successHandler(successHandler).loginPage("/login").loginProcessingUrl("/login").defaultSuccessUrl("/welcome/home")
				.permitAll()
				.and()
				.logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout").permitAll().and().exceptionHandling().accessDeniedPage("/error_403");
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception {
		build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}
}
