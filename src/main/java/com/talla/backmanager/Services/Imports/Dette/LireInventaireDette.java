package com.talla.backmanager.Services.Imports.Dette;


import com.talla.backmanager.Entites.Classes.Banque;
import com.talla.backmanager.Entites.Classes.Commun;
import com.talla.backmanager.Entites.Classes.Echeance;
import com.talla.backmanager.Entites.Classes.Emprunt;
import com.talla.backmanager.Repositories.BanqueRepository;
import com.talla.backmanager.Repositories.CommunRepository;
import com.talla.backmanager.Repositories.EcheanceRepository;
import com.talla.backmanager.Repositories.EmpruntRepository;
import com.talla.backmanager.Services.Interfaces.ILireInventaireDette;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@Service
@Slf4j
public class LireInventaireDette implements ILireInventaireDette {

    private List<Commun> listeCom = new ArrayList<Commun>();
    private String organisme = "";
    private Echeance e1 = new Echeance();
    private Emprunt emprunt = new Emprunt();
    private int nbEcheance = 0;
    private int nbFichier = 0;
    int max;
    private final BanqueRepository banqueRepository;
    private final EcheanceRepository echeanceRepository;
    private final CommunRepository communRepository;
    private final EmpruntRepository empruntRepository;

    public LireInventaireDette(BanqueRepository banqueRepository, EcheanceRepository echeanceRepository, CommunRepository communRepository, EmpruntRepository empruntRepository) {
        this.banqueRepository = banqueRepository;
        this.echeanceRepository = echeanceRepository;
        this.communRepository = communRepository;
        this.empruntRepository = empruntRepository;
    }

    public String execution(String cheminDetteInventaire) {

        String chemin = cheminDetteInventaire;
        File path = new File(chemin);
        File[] files = path.listFiles();

        if (files == null)
            return "repertoire vide";

        this.max = files.length;

        for (File fichier : files) {
            if (fichier.isFile() & (fichier.getName().indexOf(".INV") != (-1))
                    | fichier.getName().indexOf(".INV") != (-1)) {
                File file = new File(chemin + "/" + fichier.getName());
                extracted(file);
                nbFichier++;
            }
        }

        System.out.println(" nombre de lignes 7: " + nbEcheance);
        System.out.println("");
        System.out.println("  > Injection des informations dettes dans la base de données...");

        return "extraction OK";
    }

    private String extracted(File file) {
        try {
            FileReader fr;
            fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            Commun com = new Commun();

            while ((line = br.readLine()) != null && !line.equals("")) {
/////////////////////////////////////////////////////////////////////////////////////////////////////////

                switch ((line.substring(0, 1))) {

                    case "0": //Commun
                        Data0(line, com);
                        break;
                    case "1": //Commun
                        Data1(line, com);
                        break;
                    case "2": //Signaletique
                        Data2(line, com);
                        break;
                    case "3": //Signaletique
                        Data3(line, com);
                        break;
                    case "4":
                        Data4(line, com);
                        break;
                    case "5":
                        Data5(line, com);
                        break;
                    case "6":
                        Data6(line, com);
                        break;
                    case "D":
                        DataD(line, com);
                        break;

                    case "7":
                        Data7(line, com);
                        break;

                    case "8": //Signaletique
                        break;

                    case "A":
                        break;

                    case "B":
                        break;

                    case "C":
                        break;


                    case "E":
                        break;

                    case "9":
                        break;
                }

////////////////////////////////////////////////////////////////////////////////////////////////////////
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.info("Une exception !!!");
            e.printStackTrace();
        }
        return "Extraction: " + file.toString();
    }

    private String Data7(String line, Commun com) {
        com.setTypeContenu(line.substring(32, 33));
        e1.setEmprunt(emprunt);

        if (com.getTypeContenu().equals("1")) {

            nbEcheance = nbEcheance + 2;

            //echeance e1 = new echeance();
            com.setNumeroTranche(line.substring(33, 36));
            com.setDateEcheance(line.substring(36, 44));
            com.setMontant(Double.parseDouble(line.substring(45, 59)) / 100);
            com.setSdr(Double.parseDouble(line.substring(59, 74)) / 100);

            BeanUtils.copyProperties(com, e1);
            e1.setType("Remboursement");
            e1.setDatecreation(LocalDateTime.now());
            echeanceRepository.save(e1);

            log.info("Enregistrement de la l'échéance : " + " du " + e1.getDateEcheance());
//                                    listeEch.add(e1);

            com.setNumeroTranche("");
            com.setDateEcheance("");
            com.setMontant(0);
            com.setSdr(0);

            com.setNumeroTranche(line.substring(74, 77));
            com.setDateEcheance(line.substring(77, 85));
            com.setMontant(Double.parseDouble(line.substring(85, 100)) / 100);
            com.setSdr(Double.parseDouble(line.substring(100, 115)) / 100);

            BeanUtils.copyProperties(com, e1);
            e1.setType("Remboursement");
            echeanceRepository.save(e1);
            log.info("Enregistrement de la l'échéance : " + " du " + e1.getDateEcheance());
//                                    listeEch.add(e1);


            com.setNumeroTranche("");
            com.setDateEcheance("");
            com.setMontant(0);
            com.setSdr(0);

        } else if (com.getTypeContenu().equals("2")) {
            nbEcheance = nbEcheance + 4;

            com.setDateEcheance(line.substring(33, 41));
            com.setMontant(Double.parseDouble(line.substring(41, 56)) / 100);

            BeanUtils.copyProperties(com, e1);
            e1.setType("Intérêts");

            echeanceRepository.save(e1);
            log.info("Enregistrement de la l'échéance Intérêts: " + " du " + e1.getDateEcheance());
//                                    listeEch.add(e1);

            com.setNumeroTranche("");
            com.setDateEcheance("");
            com.setMontant(0);
            com.setSdr(0);

            com.setDateEcheance(line.substring(56, 64));
            com.setMontant(Double.parseDouble(line.substring(64, 79)) / 100);

            BeanUtils.copyProperties(com, e1);
            e1.setType("Intérêts");
            echeanceRepository.save(e1);
            log.info("Enregistrement de la l'échéance Intérêts: " + " du " + e1.getDateEcheance());
//                                    listeEch.add(e1);

            com.setNumeroTranche("");
            com.setDateEcheance("");
            com.setMontant(0);
            com.setSdr(0);

            com.setDateEcheance(line.substring(79, 87));
            com.setMontant(Double.parseDouble(line.substring(87, 102)) / 100);

            BeanUtils.copyProperties(com, e1);
            e1.setType("Intérêts");
            echeanceRepository.save(e1);
            log.info("Enregistrement de la l'échéance Intérêts: " + " du " + e1.getDateEcheance());
//                                    listeEch.add(e1);

            com.setNumeroTranche("");
            com.setDateEcheance("");
            com.setMontant(0);
            com.setSdr(0);


            com.setDateEcheance(line.substring(102, 110));
            com.setMontant(Double.parseDouble(line.substring(110, 125)) / 100);

            BeanUtils.copyProperties(com, e1);
            e1.setType("Intérêts");
            echeanceRepository.save(e1);
            log.info("Enregistrement de la l'échéance Intérêts: " + " du " + e1.getDateEcheance());
//                                    listeEch.add(e1);

            com.setNumeroTranche("");
            com.setDateEcheance("");
            com.setMontant(0);
            com.setSdr(0);

        }
        return "Data7";
    }

    private String DataD(String line, Commun com) {
        com.setPouvoirSubsidiant(line.substring(51, 52));
        com.setNatureEmprunt(line.substring(52, 59));
        com.setCodeFonctionnel(line.substring(78, 85));
        return "DataD";
    }

    private String Data6(String line, Commun com) {
        com.setConversionOuvertureCredit(line.substring(32, 47));
        return "Data6";
    }

    private String Data5(String line, Commun com) {
        com.setDureeEmprunt(line.substring(32, 35));
        com.setTaux(line.substring(35, 43));
        com.setPeriodiciteInteret(line.substring(51, 52));
        com.setDateInitialeInteret(line.substring(52, 60));
        com.setPeriodiciteRemboursement(line.substring(60, 61));
        com.setDateInitialeRemboursement(line.substring(61, 69));
        com.setNumeroTranche(line.substring(69, 72));
        com.setDateEcheanceFinale(line.substring(75, 83));
        com.setCompteInteret(line.substring(83, 95));
        com.setCompteRemboursement(line.substring(95, 107));
        com.setTauxRemboursement(line.substring(107, 115));
        com.setRevisionTaux(line.substring(123, 125));
        com.setPlanAmortissement(line.substring(125, 126));
        return "Data5";
    }

    private String Data4(String line, Commun com) {
        com.setDateMiseaDisposition(line.substring(32, 47));
        com.setDate(line.substring(47, 55));
        return "Data4";
    }

    private String Data3(String line, Commun com) {
        com.setDureePret(line.substring(110, 125));
        com.setTypePret(line.substring(125, 127));
        //TODO: Controle aussi sur la banque

        if (!empruntRepository.findByNumeroEmprunt(com.getNumeroEmprunt()).iterator().hasNext()) {
            BeanUtils.copyProperties(com, emprunt);
            log.info("Enregistrement de l'emprunt': " + emprunt.getNumeroEmprunt());
            emprunt.setBanque(com.getBanque());
            empruntRepository.save(emprunt);
        } else {
            emprunt = empruntRepository.findByNumeroEmprunt(com.getNumeroEmprunt()).iterator().next();
        }
        return "Data3";
    }

    private String Data2(String line, Commun com) {
        com.setMontantEmprunt(line.substring(32, 47));
        com.setTypeCredit(line.substring(47, 49));
        com.setDateConseil(line.substring(49, 57));
        com.setDateBelfius(line.substring(57, 65));
        com.setCompteOuvertureCredit(line.substring(77, 89));
        com.setModeRemboursement(line.substring(89, 91));
        com.setCategorieEmprunt(line.substring(91, 95));
        return "Data2";
    }

    private String Data1(String line, Commun com) {
        com.setNumeroEmprunt(line.substring(26, 32));
        com.setDestination(line.substring(35, 115));
        com.setDevise(line.substring(115, 118));
        this.organisme = line.substring(32, 35);
        //TODO: creer une liste de banque et l'initialiser avec un findAll et faire la recherche d'existence dans cette et réduire les acces à la bd

        if (!banqueRepository.findByNom(this.organisme).iterator().hasNext()) {
            //TODO: Creer un bean Banque et faire Injection de dépendance
            Banque banque = new Banque();
            banque.setNom(this.organisme);
            banque.setImmatriculation(com.getNumImmatriculation());
            banque.setNumentreprise("");
            banque.setAdresse("Adresse " + this.organisme);
            banqueRepository.save(banque);
            log.info("Enregistrement de la banque : " + banque.getNom());
            com.setBanque(banque);
        } else {
            com.setBanque(banqueRepository.findByNom(this.organisme).iterator().next());
        }

        com.setDestination(line.substring(35, 115));
        com.setDevise(line.substring(115, 118));


        //System.out.println("Copie des propriétés");
//                                BeanUtils.copyProperties(com,sign);
//                                BeanUtils.copyProperties(com,ct);
        Commun com1 = new Commun();
        BeanUtils.copyProperties(com, com1);

        communRepository.save(com1);
        log.info("Enregistrement des données Communes: " + com1.getDateCreation());

        listeCom.add(com1);

        return "Data1";
    }

    private void Data0(String line, Commun com) {
        com.setDateCreation(line.substring(1, 9));
        com.setNumImmatriculation(line.substring(26, 36));
        com.setNomEmprunteur(line.substring(36, 62));
        com.setInsEmprunteur(line.substring(62, 67));
        com.setMatriculeEmprunteur(line.substring(67, 79));
        com.setComptabiliteEmprunteur(line.substring(79, 81));
        com.setDateFichier(line.substring(99, 107));
        com.setDateFinMiseaJour(line.substring(115, 123));
    }
}
