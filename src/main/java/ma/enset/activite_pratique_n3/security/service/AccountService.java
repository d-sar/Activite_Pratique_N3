package ma.enset.activite_pratique_n3.security.service;

import ma.enset.activite_pratique_n3.security.entities.AppUser;

public interface AccountService {
    AppUser addNewUser(String username, String password, String email,String confirmPassword);
    AppUser addNewRole(String role);
    void addRoleToUser(String username, String role);
    void removeRoleFromUser(String username, String role);
    AppUser loadUserByUsername(String username);

}
