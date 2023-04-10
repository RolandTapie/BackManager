package com.talla.backmanager.Entites.FluxFinanciers.Beans;

import com.talla.backmanager.Entites.Classes.Banque;
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
public class Compte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String compte="";
    @ManyToOne
    private Banque banque;
    private String libelle="Libellé à définir";
    private String devise="Devise  à définir";
    private String CGFinancier="CGFinancier  à définir";
    private String CGEncours="CGEncours  à définir";
    private int groupe=1;
}
