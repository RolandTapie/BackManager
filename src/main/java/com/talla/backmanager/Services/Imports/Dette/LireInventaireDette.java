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
import java.util.List;

@Data
@Service
@Slf4j
public class LireInventaireDette implements ILireInventaireDette {
    private final BanqueRepository banqueRepository;

    private final EcheanceRepository echeanceRepository;

    private final  CommunRepository communRepository;

    private final  EmpruntRepository empruntRepository;

    public LireInventaireDette(BanqueRepository banqueRepository, EcheanceRepository echeanceRepository, CommunRepository communRepository, EmpruntRepository empruntRepository) {
        this.banqueRepository = banqueRepository;
        this.echeanceRepository = echeanceRepository;
        this.communRepository = communRepository;
        this.empruntRepository = empruntRepository;
    }

    List<Echeance> listeEch = new ArrayList<Echeance>();
    List<Commun> listeCom = new ArrayList<Commun>();
    String organisme ="";


    Echeance e1= new Echeance();
    Commun com1=new Commun();
    Emprunt emprunt = new Emprunt();


    public void execution(String cheminDetteInventaire) {

        LireInventaireDette liste = null;
        String chemin = cheminDetteInventaire; //"C:\\Users\\Liege\\Downloads\\Inventaire";
        File path = new File(chemin);
        File[] files = path.listFiles();
        int max = files.length;
        int nb_file = 0;

        for (int i = 0; i < max; i++) {
            // On teste s'il s'agit d'un fichier et on vérifie la nature de l'extension du
            // fichier
            if (files[i].isFile() & (files[i].getName().indexOf(".INV") != (-1))
                    | files[i].getName().indexOf(".INV") != (-1)) {
                //System.out.println("Fichier trouvé");
                File file = new File(chemin + "/" + files[i].getName());


                //nb_file++; // On incrémente le compteur de fichier

                try {
                    FileReader fr;
                    fr = new FileReader(file);
                    BufferedReader br = new BufferedReader(fr);
                    String line;
                    Commun com=new Commun();

                    while ((line = br.readLine()) != null && !line.equals("")) {
/////////////////////////////////////////////////////////////////////////////////////////////////////////

                        switch ((line.substring(0, 1))) {

                            case "0": //Commun

                                com.setDateCreation(line.substring(1,9));
                                com.setNumImmatriculation(line.substring(26,36));
                                com.setNomEmprunteur(line.substring(36,62));
                                com.setInsEmprunteur(line.substring(62,67));
                                com.setMatriculeEmprunteur(line.substring(67,79));
                                com.setComptabiliteEmprunteur(line.substring(79,81));
                                com.setDateFichier(line.substring(99,107));
                                com.setDateFinMiseaJour(line.substring(115,123));

                                //System.out.println("HEADER");
                                break;

                            case "1": //Commun
                                com.setNumeroEmprunt(line.substring(26,32));
//                                com.setBanque(line.substring(32,35));
                                com.setDestination(line.substring(35,115));
                                com.setDevise(line.substring(115,118));
                                this.organisme=line.substring(32,35);
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
                                }
                                    else{
                                    com.setBanque(banqueRepository.findByNom(this.organisme).iterator().next());
                                }

                                com.setDestination(line.substring(35,115));
                                com.setDevise(line.substring(115,118));


                                //System.out.println("Copie des propriétés");
//                                BeanUtils.copyProperties(com,sign);
//                                BeanUtils.copyProperties(com,ct);
                                Commun com1=new Commun();
                                BeanUtils.copyProperties(com,com1);

                                communRepository.save(com1);
                                log.info("Enregistrement des données Communes: " + com1.getDateCreation());

                                listeCom.add(com1);

                                //System.out.println("Type1 ajouté à la liste");
                                break;

                            case "2": //Signaletique
                                com.setMontantEmprunt(line.substring(32,47));
                                com.setTypeCredit(line.substring(47,49));
                                com.setDateConseil(line.substring(49,57));
                                com.setDateBelfius(line.substring(57,65));
                                com.setCompteOuvertureCredit(line.substring(77,89));
                                com.setModeRemboursement(line.substring(89,91));
                                com.setCategorieEmprunt(line.substring(91,95));


                                //signaletique sign1=(signaletique)sign.clone();

                                //listeSign.add(sign1);
                                //System.out.println("Tyep2");
                                break;

                            case "3": //Signaletique

                                com.setDureePret(line.substring(110,125));
                                com.setTypePret(line.substring(125,127));

                                //TODO: Controle aussi sur la banque
                                //Signaletique listpret;


                                if (!empruntRepository.findByNumeroEmprunt(com.getNumeroEmprunt()).iterator().hasNext()){
                                    BeanUtils.copyProperties(com,emprunt);
                                    log.info("Enregistrement de l'emprunt': " + emprunt.getNumeroEmprunt());
                                    emprunt.setBanque(com.getBanque());
                                    empruntRepository.save(emprunt);


//                                    listeSign.add(sign2);
                                }
                                else{
                                    emprunt = empruntRepository.findByNumeroEmprunt(com.getNumeroEmprunt()).iterator().next();
                                }

                                break;

                            case "4":
                                com.setDateMiseaDisposition(line.substring(32,47));
                                com.setDate(line.substring(47,55));
                                //System.out.println("Type4");
                                break;

                            case "5":
                                com.setDureeEmprunt(line.substring(32,35));
                                com.setTaux(line.substring(35,43));
                                com.setPeriodiciteInteret(line.substring(51,52));
                                com.setDateInitialeInteret(line.substring(52,60));
                                com.setPeriodiciteRemboursement(line.substring(60,61));
                                com.setDateInitialeRemboursement(line.substring(61,69));
                                com.setNumeroTranche(line.substring(69,72));
                                com.setDateEcheanceFinale(line.substring(75,83));
                                com.setCompteInteret(line.substring(83,95));
                                com.setCompteRemboursement(line.substring(95,107));
                                com.setTauxRemboursement(line.substring(107,115));
                                com.setRevisionTaux(line.substring(123,125));
                                com.setPlanAmortissement(line.substring(125,126));

                                //System.out.println("Type5");
                                break;

                            case "6":
                                com.setConversionOuvertureCredit(line.substring(32,47));
                                //System.out.println("Type6");
                                break;

                            case "D":
                                com.setPouvoirSubsidiant(line.substring(51,52));
                                com.setNatureEmprunt(line.substring(52,59));
                                com.setCodeFonctionnel(line.substring(78,85));
                                break;

                            case "7":
                                com.setTypeContenu(line.substring(32,33));
                                e1.setEmprunt(emprunt);

                                if (com.getTypeContenu().equals("1"))
                                {

                                    nb_file=nb_file+2;

                                    //echeance e1 = new echeance();
                                    com.setNumeroTranche(line.substring(33,36));
                                    com.setDateEcheance(line.substring(36,44));
                                    com.setMontant(Double.parseDouble(line.substring(45,59))/100);
                                    com.setSdr(Double.parseDouble(line.substring(59,74))/100);

                                    BeanUtils.copyProperties(com,e1);
                                    e1.setType("Remboursement");
                                    e1.setDatecreation(LocalDateTime.now());
                                    echeanceRepository.save(e1);

                                    log.info("Enregistrement de la l'échéance : " + " du " + e1.getDateEcheance());
//                                    listeEch.add(e1);

                                    com.setNumeroTranche("");
                                    com.setDateEcheance("");
                                    com.setMontant(0);
                                    com.setSdr(0);

                                    com.setNumeroTranche(line.substring(74,77));
                                    com.setDateEcheance(line.substring(77,85));
                                    com.setMontant(Double.parseDouble(line.substring(85,100))/100);
                                    com.setSdr(Double.parseDouble(line.substring(100,115))/100);

                                    BeanUtils.copyProperties(com,e1);
                                    e1.setType("Remboursement");
                                    echeanceRepository.save(e1);
                                    log.info("Enregistrement de la l'échéance : " + " du " + e1.getDateEcheance());
//                                    listeEch.add(e1);


                                    com.setNumeroTranche("");
                                    com.setDateEcheance("");
                                    com.setMontant(0);
                                    com.setSdr(0);

                                }

                                else if (com.getTypeContenu().equals("2"))
                                {
                                    nb_file=nb_file+4;

                                    com.setDateEcheance(line.substring(33,41));
                                    com.setMontant(Double.parseDouble(line.substring(41,56))/100);

                                    BeanUtils.copyProperties(com,e1);
                                    e1.setType("Intérêts");

                                    echeanceRepository.save(e1);
                                    log.info("Enregistrement de la l'échéance Intérêts: " + " du " + e1.getDateEcheance());
//                                    listeEch.add(e1);

                                    com.setNumeroTranche("");
                                    com.setDateEcheance("");
                                    com.setMontant(0);
                                    com.setSdr(0);

                                    com.setDateEcheance(line.substring(56,64));
                                    com.setMontant(Double.parseDouble(line.substring(64,79))/100);

                                    BeanUtils.copyProperties(com,e1);
                                    e1.setType("Intérêts");
                                    echeanceRepository.save(e1);
                                    log.info("Enregistrement de la l'échéance Intérêts: " + " du " + e1.getDateEcheance());
//                                    listeEch.add(e1);

                                    com.setNumeroTranche("");
                                    com.setDateEcheance("");
                                    com.setMontant(0);
                                    com.setSdr(0);

                                    com.setDateEcheance(line.substring(79,87));
                                    com.setMontant(Double.parseDouble(line.substring(87,102))/100);

                                    BeanUtils.copyProperties(com,e1);
                                    e1.setType("Intérêts");
                                    echeanceRepository.save(e1);
                                    log.info("Enregistrement de la l'échéance Intérêts: " + " du " + e1.getDateEcheance());
//                                    listeEch.add(e1);

                                    com.setNumeroTranche("");
                                    com.setDateEcheance("");
                                    com.setMontant(0);
                                    com.setSdr(0);


                                    com.setDateEcheance(line.substring(102,110));
                                    com.setMontant(Double.parseDouble(line.substring(110,125))/100);

                                    BeanUtils.copyProperties(com,e1);
                                    e1.setType("Intérêts");
                                    echeanceRepository.save(e1);
                                    log.info("Enregistrement de la l'échéance Intérêts: " + " du " + e1.getDateEcheance());
//                                    listeEch.add(e1);

                                    com.setNumeroTranche("");
                                    com.setDateEcheance("");
                                    com.setMontant(0);
                                    com.setSdr(0);

                                }


                                //System.out.println("Type7");
                                break;

                            case "8": //Signaletique
                                //System.out.println("Type8");
                                break;

                            case "A":
                                //System.out.println("TypeA");
                                break;

                            case "B":
                                //System.out.println("TypeB");
                                break;

                            case "C":
                                //System.out.println("TypeC");
                                break;


                            case "E":
                                //System.out.println("TypeE");
                                break;

                            case "9":
                                //System.out.println("Type9");
                                break;
                        }

////////////////////////////////////////////////////////////////////////////////////////////////////////
                    }

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    log.info("Une exception !!!");
                    e.printStackTrace();
                }
                // Créer l'objet BufferedReader

            }
        }

        System.out.println ("  > taille de la liste" + listeEch.size() + " nombre de lignes 7: " + nb_file );
        System.out.println("");
        System.out.println("  > Injection des informations dettes dans la base de données...");

    }

}
