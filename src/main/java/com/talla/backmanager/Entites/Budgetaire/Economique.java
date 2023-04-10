package com.talla.backmanager.Entites.Budgetaire;

import com.talla.backmanager.Entites.Generale.Beans.CG;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Economique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String eco="121-01";
    private String libelle="Test Code Eco";
    private String groupe="";
    private String nature="Test nature";
    private String type="";
    private String service="Ordinaire";
    private String cgd="";
    private String cgc="";

    @ManyToOne
    private CG cgDebit;

    @ManyToOne
    private CG cgCredit;// acceptée ou refusée
}
