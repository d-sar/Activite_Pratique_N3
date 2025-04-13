package ma.enset.activite_pratique_n3.security.repo;

import ma.enset.activite_pratique_n3.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole, String> {


}
