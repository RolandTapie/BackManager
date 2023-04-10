package com.talla.backmanager.Repositories.Budgetaire;

import com.talla.backmanager.Entites.Budgetaire.Economique;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EconomiqueRepository extends JpaRepository<Economique,Integer> {
    public List<Economique> findByEco(String NumEconomique);
}
