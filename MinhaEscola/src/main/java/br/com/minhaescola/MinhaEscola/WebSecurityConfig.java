package br.com.minhaescola.MinhaEscola;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.JdbcUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Autowired
	private DataSource dataSource;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.anyRequest().authenticated()
			.and()
			.formLogin(form -> form
				.loginPage("/login")
				.defaultSuccessUrl("/home", true)
				.permitAll()
			)
			.logout(logout -> logout.logoutUrl("/logout"))
			.csrf().disable();      // isso daqui é pra não tomar um 403 (fobidden), mas é avançado...sei lá pq coloca
		return http.build();
		
/* criei o USERS e AUTHORITIES lá no heidiSQL
		create table users(
				username varchar_ignorecase(50) not null primary key,
				password varchar_ignorecase(500) not null,
				enabled boolean not null
			);

			create table authorities (
				username varchar_ignorecase(50) not null,
				authority varchar_ignorecase(50) not null,
				constraint fk_authorities_users foreign key(username) references users(username)
			);
			create unique index ix_auth_username on authorities (username,authority);
*/

	}
	
//    @Bean
//    public PasswordEncoder encoder() {
//        return new BCryptPasswordEncoder();
//    }
//    
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
	
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();       // encoder para criptografar os dados
//		
//		 User.builder()
//			.username("joao")
//			.password(encoder.encode("joao"))
//			.roles("ADM")
//			.build();
//		
//		auth.jdbcAuthentication()
//			.dataSource(dataSource)
//			.passwordEncoder(encoder);
//	}

	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user =
			 User.withDefaultPasswordEncoder()
				.username("joao")
				.password("joao")
				.roles("ADM")
				.build();

		return new InMemoryUserDetailsManager(user);
	}
//	não faz assim pq estaria criando usuário/senha em memória...não é bom
	
}
