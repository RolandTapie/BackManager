package com.talla.backmanager.DTO.Objects;

import lombok.Data;

@Data
public class EcheanceDTO {
    private int id;
    private String dateEcheance;
    private double montant;
    private double sdr;
    private String type;
    private String numeroEmprunt ;
}
