package com.talla.backmanager.Entites.Classes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Echeance implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String dateEcheance;
    private double montant;
    private double sdr;
    private String type;
    @ManyToOne
    Emprunt emprunt;
    private LocalDateTime datecreation;
}
