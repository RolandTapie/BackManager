package com.talla.backmanager.Entites.FluxFinanciers.Beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Coda implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //@Column(name="compte")
    @ManyToOne
    private Compte compte;
    private String numextrait="";
    private String numsequenceflux="";
    private String numdetails="";
    private String numref="";
    private double montantflux=0;
    private Date datevaleur;
    private Date datecomptable;
    private String codeoperation="";
    private String typecommunication="";
    private String communication="";
    private String refclient="";
    private String nomcontrepartie="";
    private String comptecontrepartie="";
    private String mouvement;
    private String adresse="Nulle";
    private String fractionnement="";
    private String affectation="";
    private String cible="";
    private double solde=0;
    private String idjounal="";
    private String groupe="Trésorerie";
}
