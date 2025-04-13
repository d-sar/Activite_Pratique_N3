# 🏥 Projet de Gestion des Patients - Spring Boot & Thymeleaf
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-green?logo=spring)
![Java](https://img.shields.io/badge/Java-23-blue?logo=java)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-3.1-white?logo=thymeleaf)

## 📌 Sommaire

- [📖 Description](#description)
- [✨ Fonctionnalités](#fonctionnalités)
- [🛠️ Technologies Utilisées](#Technologies-Utilisées)
- [🔐 Authentification JDBC avec UserDetailsService](#authentification-jdbc-avec-userdetailsservice)
- [🚀 Installation](#installation)
- [⚙️ Utilisation](#utilisation)
- [📸 Galerie d'écrans](#galerie-décrans)
- [🗄️ Captures de la base de données](#captures-de-la-base-de-données)
- [📚 Références](#références)


## Description

Une application web complète pour la gestion des patients avec des fonctionnalités CRUD et un système d'authentification en mémoire.

## ✨Fonctionnalités

| Fonctionnalité | Description | Accès |
|----------------|-------------|-------|
| 👥 Liste des patients | Affichage paginé de tous les patients | Tous utilisateurs |
| 🔍 Recherche | Filtrage par nom ou mot-clé | Tous utilisateurs |
| 🗑️ Suppression | Suppression sécurisée des dossiers | Rôle ADMIN |
| ✏️ Édition | Mise à jour des informations patients | Rôle ADMIN |
| ➕ Ajout | Création de nouveaux dossiers | Rôle ADMIN |
| 🔐 Sécurité | Authentification et autorisation | Spring Security |
  
## 🛠️ Technologies-Utilisées

- **Backend**: 
  - ![Spring Boot](https://img.shields.io/badge/-Spring_Boot_3-6DB33F?logo=springboot)
  - ![Spring Security](https://img.shields.io/badge/-Spring_Security-6DB33F?logo=springsecurity)
  - ![JPA/Hibernate](https://img.shields.io/badge/-JPA/Hibernate-59666C?logo=hibernate)

- **Frontend**:
  - ![Thymeleaf](https://img.shields.io/badge/-Thymeleaf-005F0F?logo=thymeleaf)
  - ![Bootstrap](https://img.shields.io/badge/-Bootstrap_5-7952B3?logo=bootstrap)

- **Base de données**:
  - ![H2](https://img.shields.io/badge/-H2_Database-1E6C93?logo=h2)
  - ![MySQL](https://img.shields.io/badge/-MySQL-4479A1?logo=mysql)
  
## 🔐 Authentification en Mémoire

Le système utilise **Spring Security** avec une authentification en mémoire (in-memory) préconfigurée :
     
        @Bean
        public InMemoryUserDetailsManager inMemoryUserDetailsManager(PasswordEncoder passwordEncoder){
          String encodedPassword = passwordEncoder.encode("1234");
          return new InMemoryUserDetailsManager(
              User.withUsername("user1").password(encodedPassword).roles("USER").build(),
              User.withUsername("admin").password(encodedPassword).roles("USER","ADMIN").build()
          );
        }
## 🔐JDBC Authentication

        @Bean
        public JdbcUserDetailsManager JdbcUserDetailsManager(DataSource dataSource) {
            return new JdbcUserDetailsManager(dataSource);
        }

## 🔐 Authentification JDBC avec UserDetailsService
L’application utilise une implémentation personnalisée de `UserDetailsService` pour récupérer les utilisateurs depuis une base de données via JPA :

        @Service
        @AllArgsConstructor
        public class UserDetailServiceImp implements UserDetailsService {
            AccountService accountService;
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                AppUser appUser =accountService.loadUserByUsername(username);
                if (appUser == null) throw new UsernameNotFoundException(String.format("User %s not found", username));
                String[] roles = appUser.getRoles().stream().map(u->u.getRole()).toArray(String[]::new);
                UserDetails userDetails = User
                    .withUsername(appUser.getUsername())
                    .password(appUser.getPassword())
                    .roles(roles).build();
            return userDetails;
            }
        }

## 🚀 Installation

1. Clonez ce dépôt sur votre machine locale :
   ```bash
   git clone https://github.com/d-sar/Activite_Pratique_N3
2. Se rendre dans le dossier du projet
   ```bash
    cd Activite_Pratique_N3
   
## Usage

1. Page d'accueil : L'utilisateur peut consulter la liste des patients.
2. Recherche de patients : Vous pouvez entrer un mot-clé pour rechercher des patients par nom.
3. Actions administratives : L'utilisateur avec le rôle ADMIN peut ajouter ou modifier ou supprimer des patients.

## 📸 Galerie d'écrans

#### Page de connexion
Voici à quoi ressemble la page de connexion :
![img.png](img.png)
#### Liste des patients
Une fois connecté, l'utilisateur peut voir la liste des patients. La recherche par mot-clé peut être effectuée directement depuis cette page.
![img_1.png](img_1.png)
#### L'ajoute d'un patient
Les utilisateurs ayant le rôle ADMIN peuvent ajouter un patient. Cliquez sur le bouton "Nouvelle" dans le menu "Patients".
![img_4.png](img_4.png)
#### Suppression d'un patient
Les utilisateurs ayant le rôle ADMIN peuvent supprimer un patient de la liste. Cliquez sur le bouton "Supprimer" à côté du patient pour effectuer cette action.
![img_2.png](img_2.png)
#### Édition d'un patient
Les utilisateurs ADMIN peuvent également modifier les informations d'un patient en cliquant sur le bouton "EDIT".
![img_5.png](img_5.png)

## 🗄️ Captures de la base de données

Voici un aperçu des tables principales dans MySQL :

####  Table app_user
![img_6.png](img_6.png)

####  Table app_role
![img_7.png](img_7.png)

####  Table app_user_roles (relation plusieurs-à-plusieurs)
![img_8.png](img_8.png)

####  Table patient
![img_9.png](img_9.png)


## 📚 Références
Spring Security - Documentation

Spring Data JPA - Reference Guide

Spring Boot - Official Site

