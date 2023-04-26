package com.talla.backmanager.Services.Imports.FluxFinanciers.Interfaces;

public interface ILectureCoda {
    public  void ControleEtImport(String cheminCoda);
    public  void ImportCoda(String cheminCoda);

    int getNbligne();
    double getAmount();
}
