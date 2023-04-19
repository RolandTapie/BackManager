package com.talla.backmanager.Services.Imports.Budgetaire;



import com.talla.backmanager.Repositories.Budgetaire.AllocationRepository;
import com.talla.backmanager.Repositories.Budgetaire.EconomiqueRepository;
import com.talla.backmanager.Repositories.Budgetaire.EngagementRepository;
import com.talla.backmanager.Repositories.Budgetaire.FonctionRepository;
import com.talla.backmanager.Repositories.Generale.CGRepository;
import com.talla.backmanager.Repositories.Tiers.TiersRepository;
import com.talla.backmanager.Services.Imports.Generale.ImportCG;
import com.talla.backmanager.Services.Imports.Tiers.ImportTiers;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;

@Service
public class Imports {
    final CGRepository cgRepository;
    final EngagementRepository engagementRepository;
    final TiersRepository tiersRepository;
    final AllocationRepository allocationRepository;
    final FonctionRepository fonctionRepository;
    final EconomiqueRepository economiqueRepository;
    final ImportCG importCG;
    final ImportFonction importFonction;
    final ImportEconomique importEconomique;
    final ImportTiers importTiers;
    final ImportEngagement importEngagement;
    final ImportAllocation importAllocation;
    public Imports(CGRepository cgRepository, EngagementRepository engagementRepository, TiersRepository tiersRepository, AllocationRepository allocationRepository, FonctionRepository fonctionRepository, EconomiqueRepository economiqueRepository, ImportCG importCG, ImportFonction importFonction, ImportEconomique importEconomique, ImportTiers importTiers, ImportEngagement importEngagement, ImportAllocation importAllocation) {
        this.cgRepository = cgRepository;
        this.engagementRepository = engagementRepository;
        this.tiersRepository = tiersRepository;
        this.allocationRepository = allocationRepository;
        this.fonctionRepository = fonctionRepository;
        this.economiqueRepository = economiqueRepository;
        this.importCG = importCG;
        this.importFonction = importFonction;
        this.importEconomique = importEconomique;
        this.importTiers = importTiers;
        this.importEngagement = importEngagement;
        this.importAllocation = importAllocation;
    }






    public  String source="src/main/java/com/talla/backmanager/Zfiles/";
    
    public void execution() {

        System.out.println("  > Import du fichier de Fonctions");
        try {
            importFonction.execution(source);
        } catch (FileNotFoundException f)
        {
            System.out.println("Fichier d'import de fonctions inexistant");
        }

        System.out.println("  > Import du fichier de CG");
        try {
            this.importCG.Execution(source);
        } catch (FileNotFoundException f)
        {
            System.out.println("Fichier d'import de CG inexistant");
        }

        System.out.println("  > Import du fichier de Economiques");
        try {
            importEconomique.Execution(source);
        } catch (FileNotFoundException f)
        {
            System.out.println("Fichier d'import de fonctions inexistant");
        }

        System.out.println("  > Import du fichier de Allocations");
        System.out.println("  > Validation des articles necessaire à l'import des allocations");
        try {
            importAllocation.Execution(source);
        } catch (FileNotFoundException f)
        {
            System.out.println("Fichier d'import de Allocations inexistant");
        }
        System.out.println("  > Import du fichier de Tiers");
        try {
           importTiers.execution(source);
        } catch (FileNotFoundException f)
        {
            System.out.println("Fichier d'import de fonctions inexistant");
        }

        System.out.println("  > Import du fichier de Engagements");
        try {
            importEngagement.execution(source);
        } catch (FileNotFoundException f)
        {
            System.out.println("Fichier d'import de Engagements inexistant");
        }
    }
}
