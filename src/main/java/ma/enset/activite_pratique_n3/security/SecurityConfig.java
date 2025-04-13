package ma.enset.activite_pratique_n3.security;

import lombok.AllArgsConstructor;
import ma.enset.activite_pratique_n3.security.service.UserDetailServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private UserDetailServiceImp userDetailServiceImp;

    //JDBC Authentication
    //@Bean
    public JdbcUserDetailsManager JdbcUserDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    //InMemomy Authentication
   // @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(PasswordEncoder passwordEncoder){
    String encodedPassword = passwordEncoder.encode("1234");
    System.out.println(encodedPassword);
    return new InMemoryUserDetailsManager(
            User.withUsername("user1").password(encodedPassword).roles("USER").build(),
            User.withUsername("user2").password(encodedPassword).roles("USER").build(),
            User.withUsername("admin").password(encodedPassword).roles("USER","ADMIN").build()
    );
}

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .formLogin(form -> form
                        .loginPage("/login") // Custom login page URL
                        .permitAll() // Allow everyone to access the login page
                        .defaultSuccessUrl("/user/index", true)
                )
                .authorizeHttpRequests(ar-> ar.requestMatchers("/webjars/**").permitAll())
                .authorizeHttpRequests(ar-> ar.requestMatchers("user/index").permitAll())
                .authorizeHttpRequests(ar -> ar.requestMatchers("/deletePatient/**").hasRole("ADMIN"))
                .authorizeHttpRequests(ar -> ar.requestMatchers("/admin/**").hasRole("ADMIN"))
                .authorizeHttpRequests(ar -> ar.requestMatchers("/user/**").hasRole("USER"))
                .authorizeHttpRequests(ar -> ar.anyRequest().authenticated())
                .exceptionHandling(eh -> eh.accessDeniedPage("/NotAuthorized"))
                .userDetailsService(userDetailServiceImp)
                .build();
    }

}
