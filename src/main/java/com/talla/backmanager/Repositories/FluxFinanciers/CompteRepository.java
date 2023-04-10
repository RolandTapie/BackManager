package com.talla.backmanager.Repositories.FluxFinanciers;

import com.talla.backmanager.Entites.FluxFinanciers.Beans.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface CompteRepository extends JpaRepository<Compte, Integer> {
    List<Compte> findByCompte(String compte);
}
