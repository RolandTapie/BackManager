package com.talla.backmanager.Repositories.Budgetaire;


import com.talla.backmanager.Entites.Budgetaire.Allocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllocationRepository extends JpaRepository<Allocation, Integer> {
}
