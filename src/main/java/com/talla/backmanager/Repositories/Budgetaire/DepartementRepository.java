package com.talla.backmanager.Repositories.Budgetaire;

import com.talla.backmanager.Entites.Budgetaire.Departement;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DepartementRepository extends JpaRepository<Departement, Integer> {
    Departement findByNom (String nomDepartement);
}
