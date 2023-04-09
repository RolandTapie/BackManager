package com.talla.backmanager.Repositories;


import com.talla.backmanager.Entites.Classes.Banque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface BanqueRepository extends JpaRepository<Banque,Integer> {
    List<Banque> findByNom(String nom);
    List<Banque> findByImmatriculation(String immatriculation);
    List<Banque> findDistinctByNom(String nom);
    List<Banque> findAll();
}
