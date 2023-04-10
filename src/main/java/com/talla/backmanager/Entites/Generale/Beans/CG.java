package com.talla.backmanager.Entites.Generale.Beans;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import jakarta.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class CG {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String numeroCg="1700";
    private String libelle="Emprunts à plus d'un an";
    private String catBilan="Passif";
    private String rubrique="VIII.A.I";
}
