package com.talla.backmanager.Services.Service.FluxFinanciers.Interfaces;

public interface ILectureCoda {
    public  void ControleEtImport();
    public  void ImportCoda();

    int getNbligne();
    double getAmount();
}
