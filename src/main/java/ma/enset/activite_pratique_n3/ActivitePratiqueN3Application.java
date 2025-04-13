package ma.enset.activite_pratique_n3;

import lombok.Data;
import ma.enset.activite_pratique_n3.entities.Patient;
import ma.enset.activite_pratique_n3.repository.PatientRepository;
import ma.enset.activite_pratique_n3.security.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.Repository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;


import java.sql.DatabaseMetaData;
import java.sql.Date;

@SpringBootApplication
public class ActivitePratiqueN3Application  implements CommandLineRunner {
    @Autowired
    private PatientRepository patientRepository;

    public static void main(String[] args) {

        SpringApplication.run(ActivitePratiqueN3Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
@Bean
    PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }
// jdbc Authentication
   // @Bean
    CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager) {
        PasswordEncoder passwordEncoder = passwordEncoder();
        return args -> {

            UserDetails u1= jdbcUserDetailsManager.loadUserByUsername("user3");
            if(u1==null)
                 jdbcUserDetailsManager.createUser(
                    User.withUsername("user3").password(passwordEncoder.encode("1234")).roles("USER").build());

            UserDetails u3= jdbcUserDetailsManager.loadUserByUsername("user4");
            if(u3==null)
                jdbcUserDetailsManager.createUser(
                    User.withUsername("user4").password(passwordEncoder.encode("1234")).roles("USER").build());

            UserDetails admin= jdbcUserDetailsManager.loadUserByUsername("admin1");
            if(admin==null)
                jdbcUserDetailsManager.createUser(
                    User.withUsername("admin1").password(passwordEncoder.encode("1234")).roles("ADMIN","USER").build());

        };
    }
    // utilisation des methode personaliser
    //@Bean
    CommandLineRunner commandLineRunnerUserDetails(AccountService accountService){
        return args -> {
            accountService.addNewRole("USER");
            accountService.addNewRole("ADMIN");
            accountService.addNewUser("user1Details","1234","user1@gmail.com","1234");
            accountService.addNewUser("user2Details","1234","user2@gmail.com","1234");
            accountService.addRoleToUser("user1Details","USER");
            accountService.addRoleToUser("user2Details","ADMIN");
        };
    }
}
