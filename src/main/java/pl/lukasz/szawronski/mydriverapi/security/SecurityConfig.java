package pl.lukasz.szawronski.mydriverapi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder())
                .withUser("admin")
                .password(passwordEncoder().encode("secret1"))
                .roles("ADMIN");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

//@Configuration
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails userDetail = User.withDefaultPasswordEncoder()
//                .username("admin")
//                .password("admin1234")
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(userDetail);
//    }
//}
