package ch.unibe.eseteam2.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    AuthSuccessHandler authSuccessHandler;

    @Bean
    public UserDetailsManager userDetailsManager(DataSource ds) {
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager();
        userDetailsManager.setDataSource(ds);
        return userDetailsManager;
    }

    public void configure (WebSecurity web){
        web.ignoring()
                .antMatchers("/css/**", "/images/**", "/js/**");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//        		.antMatchers("/")
//        			.permitAll()
                .antMatchers( "/user/create")
                    .permitAll()
                .regexMatchers(HttpMethod.POST, "/user")
                    .permitAll()
				.antMatchers("/planner/**")
					.hasRole("ADMIN")
                .anyRequest()
                    .authenticated()
                    .and()
                .logout()
                	.logoutUrl("/user/logout")
                	.and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll().successHandler(authSuccessHandler)
                    .and()
                .csrf().disable()
                .httpBasic();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource);
    }
}
