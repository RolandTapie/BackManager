package com.talla.backmanager.Entites.Budgetaire.Depenses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import jakarta.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Imputation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Engagement engagement;

    @ManyToMany (mappedBy = "imputation")
    private List<Ordonnancement> ordonnancements;
    private double montantImput=9884;
    private double montantOrdonnance=546;
    private double montantLiquide=400;
}
