package Dette.Entites.Classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Commun {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne (fetch = FetchType.LAZY)
    Banque banque;
    private String dateCreation;
    private String numImmatriculation;
    private String nomEmprunteur;
    private String insEmprunteur;
    private String matriculeEmprunteur;
    private String comptabiliteEmprunteur;
    private String dateFichier;
    private String dateFinMiseaJour;
    private String numeroEmprunt;
    private String destination;
    private String devise;
    private String montantEmprunt;
    private String typeCredit;
    private String dateConseil;
    private String dateBelfius;
    private String compteOuvertureCredit;
    private String modeRemboursement;
    private String categorieEmprunt;
    private String dureePret;
    private String typePret;
    private String dateMiseaDisposition;
    private String date;
    private String dureeEmprunt;
    private String taux;
    private String periodiciteInteret;
    private String dateInitialeInteret;
    private String periodiciteRemboursement;
    private String dateInitialeRemboursement;
    private String numeroTranche;
    private String dateEcheanceFinale;
    private String compteInteret;
    private String compteRemboursement;
    private String tauxRemboursement;
    private String revisionTaux;
    private String planAmortissement;
    private String conversionOuvertureCredit;
    private String pouvoirSubsidiant;
    private String natureEmprunt;
    private String codeFonctionnel;
    private String dateEcheance;
    private double montant;
    private double sdr;
    private String typeContenu;

}
