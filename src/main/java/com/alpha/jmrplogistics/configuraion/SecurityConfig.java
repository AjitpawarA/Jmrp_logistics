package com.alpha.jmrplogistics.configuraion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.alpha.jmrplogistics.services.MyUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private MyUserDetailService myuserdetailservice;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf(customizer -> customizer.disable())
				.authorizeHttpRequests(request -> request.anyRequest().authenticated())
				.formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults());
//				.sessionManagement(sec -> sec.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		return httpSecurity.build();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		provider.setUserDetailsService(myuserdetailservice);
		return provider;
	}

	// configuration for different users no a default users from
	// application.properties

//		@Bean
//		public UserDetailsService userDetailsService() {
	//
//			UserDetails u1 = User.withDefaultPasswordEncoder()
//					.username("sandeep")
//					.password("12345")
//					.roles("ADMIN")
//					.build();
//			
//			
//			UserDetails u2 = User.withDefaultPasswordEncoder()
//					.username("neha")
//					.password("789")
//					.roles("USER")
//					.build();
	//
//			return new InMemoryUserDetailsManager(u1, u2);
//		}

	// still these are some users created by hardcoding
	// we have to authinticate the user from db itself

}
