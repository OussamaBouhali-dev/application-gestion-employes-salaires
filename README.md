# 💼 Application de Gestion des Employés et des Salaires

Une application de bureau Java Swing pour gérer les employés, les services, les salaires, les pointages et générer des fiches de paie en PDF. Conçue pour faciliter la gestion RH au sein d'une entreprise.

---

## 🛠️ Fonctionnalités

- Authentification sécurisée des utilisateurs
- Tableau de bord administrateur
- Gestion des employés (ajout, modification, suppression)
- Gestion des services
- Gestion des salaires et fiches de paie
- Pointage des employés
- Exportation des fiches de paie au format PDF

---

## 🖥️ Technologies utilisées

- Java (Swing)
- JDBC
- MySQL
- Maven
- iText PDF

---

## 🧪 Structure du projet

```bash
src/
├── controller/       # Logique métier et gestion des vues
├── dao/              # Accès à la base de données
├── model/            # Représentation des entités (Employé, Salaire, etc.)
├── util/             # Classes utilitaires (PDF, Auth, etc.)
└── view/             # Interfaces utilisateur (Java Swing)
