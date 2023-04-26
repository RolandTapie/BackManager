package com.talla.backmanager.Services.Imports.FluxFinanciers.Services;

import com.talla.backmanager.Entites.FluxFinanciers.Beans.Coda;
import com.talla.backmanager.Entites.FluxFinanciers.Beans.Compte;
import com.talla.backmanager.Entites.FluxFinanciers.Beans.Solde;
import com.talla.backmanager.Repositories.FluxFinanciers.CodaRepository;
import com.talla.backmanager.Repositories.FluxFinanciers.CompteRepository;
import com.talla.backmanager.Services.Service.FluxFinanciers.Interfaces.ILectureCoda;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Data
@Slf4j
public class LectureCoda implements ILectureCoda {

    //    @Autowired
    public CompteRepository compteRepository;
    //    @Autowired
    public CodaRepository codaRepository;
    private int nbligne=0;
    private double amount=0;
    private static List<Coda> liste= new ArrayList();
    private static List<Compte> listeCompte = new ArrayList();
    private static List<Solde> listesolde= new ArrayList();
    String chemin = "";

    public LectureCoda(CompteRepository compteRepository, CodaRepository codaRepository) {
        this.compteRepository = compteRepository;
        this.codaRepository = codaRepository;
    }

    public static List<Compte> getListeCompte() {
        return listeCompte;
    }
    public static void setListeCompte(List<Compte> listeCompte) {
        LectureCoda.listeCompte = listeCompte;
    }
    public List<Coda> getListe() {
        return liste;
    }
    public void setListe(List<Coda> liste) {
        this.liste = liste;
    }
    public List<Solde> getListesolde() {
        return listesolde;
    }
    public void setListesolde(List<Solde> listesolde) {
        this.listesolde = listesolde;
    }



    public  void ControleEtImport(){
        System.out.println("  > Contrôle existence des Fichiers Coda");
        //String chemin = "C:\\Users\\Liege\\Downloads\\coda 2020 et 2021" ;
//        String chemin = cheminCoda;
                File path = new File(chemin); //Définition chemin de base vers les fichier CODA
        File [] files = path.listFiles(); //Création d'un tableau de fichier pour stocker la liste des fichier du répertoire

        // On revient voir dans le repertoire s'il existe encore des fichiers CODA

        int nbInjection=0;

        controle: for (int i=0; i< files.length; i++)
        {

            relecture: files = path.listFiles(); //Création d'un tableau de fichier pour stocker la liste des fichier du répertoire

            if ((files[i].getName().indexOf(".CD2") > 0) && i < files.length) //On teste s'il existe un fichier CODA
            {
                ImportCoda();
                break controle;
            }

        }

    }

    public  void ImportCoda()
    {


        // TODO Auto-generated constructor stub
        System.out.println("  > Import des fichiers Coda");
        int nb_ligne =0; //Compteur nombre de lignes
        int nb_file =0; //Compteur nombre de fichiers
        boolean nouveau=false;
        int w=0;
        //ArrayList <Coda> liste = new ArrayList<Coda> (); // Creation d'une Collection de type ArrayList pour stocker chaque flux
        try
        {
//            String chemin = "C:\\Users\\Liege\\Downloads\\coda 2020 et 2021" ;
            File path = new File(chemin); //Définition chemin de base vers les fichier CODA
            File [] files = path.listFiles(); //Création d'un tableau de fichier pour stocker la liste des fichier du répertoire
            //On traite en paquet 100 fichiers max
            int max = files.length;
//            if (max > 99)
//            {
//            max = 99;
//            }
            String [] traites = new String [max];
            int k=0;
            for (int i = 0; i < max; i++)
            {
                // On teste s'il s'agit d'un fichier et on vérifie la nature de l'extension du fichier
                if (files[i].isFile() & (files[i].getName().indexOf(".COD")!=(-1))|files[i].getName().indexOf(".CD2")!=(-1))
                {
                    //ok System.out.println(chemin + "/" + files[i].getName());

                    //On construit le chemin vers le fichier testé
                    File file = new File(chemin + "/" + files[i].getName());

                    traites[k]= (chemin + "/" + files[i].getName()).toString();
                    k++;

                    nb_file++; //On incrémente le compteur de fichier

                    FileReader fr = new FileReader(file);
                    // Créer l'objet BufferedReader
                    BufferedReader br = new BufferedReader(fr);

                    String line;

                    //On stocke chaque ligne du fichier dans la variable line et on teste s'il ne s'agit pas d'une ligne vide

                    String ancienneSequence ="";
                    String nouvelleSequence = "0000";
                    String comptesolde="";
                    String datesolde="";
                    double soldedebut=0;
                    double soldefin=0;
                    Coda cod2= new Coda();

                    String fichier="";
                    String compte="";
                    String extrait="";
                    String jour="";
                    String mois="";
                    String annee="";
                    Solde solde=new Solde();
                    //////////////////////////////////////////////////////////////////////////////////////////////

                    while((line = br.readLine()) != null && !line.equals(""))
                    { w=w+1;

                        if ((line.substring(0, 1)).equals("0"))
                        {
                            fichier=line.substring(25, 34);
                        }

                        if ((line.substring(0, 1)).equals("1"))
                        {
                            solde = new Solde();

                            compte=line.substring(5, 42);
                            if (line.substring(42, 43).equals("1"))
                            {
                                soldedebut=(-1)*Double.parseDouble(line.substring(43, 58))/1000;
                            }
                            else
                            {
                                soldedebut=Double.parseDouble(line.substring(43, 58))/1000;
                            }
                            comptesolde=compte;
                            extrait=line.substring(1, 5);
                            solde.setDate("dateTest");
                            //solde.setCompte(compte);
                            solde.setSoldedebut(soldedebut);

                        }

                        if ((line.substring(0, 1)).equals("8"))
                        {
                            if (line.substring(41, 42).equals("1"))
                            {
                                soldefin=(-1)*Double.parseDouble(line.substring(42, 57))/1000;
                            }
                            else
                            {
                                soldefin=Double.parseDouble(line.substring(42, 57))/1000;
                            }
                            solde.setSoldefin(soldefin);
                            listesolde.add(solde);
                        }

                        if ((line.substring(0, 2)).equals("21"))
                        {
                            nouvelleSequence = line.substring(2, 6);
                        }

                        if (!(ancienneSequence.equals("")) && !(ancienneSequence.equals(nouvelleSequence)))
                        {
                            liste.add(cod2);
                        }

                        if ((line.substring(0, 2)).equals("21") && !(ancienneSequence.equals(nouvelleSequence)) | (line.substring(0, 1)).equals("9"))
                        {  //System.out.println(ancienneSequence);

                            ancienneSequence=nouvelleSequence;

                            Coda  cod1 = new Coda();
                            cod2=cod1;

                            //cod2.fluxCoda(line);
                            cod2.setNumextrait(extrait);

                            //cod2.setCompte(compte);
                            Compte cpte= new Compte();
                            List<Compte> compteList = new ArrayList();
                            compteList=compteRepository.findByCompte(compte);

                            if (compteList==null) {
                                cpte.setCompte(compte);
                                compteRepository.save(cpte);
                            }
                                else{
                                Iterator<Compte>  compteIterator = compteList.iterator();
                                if (compteIterator.hasNext())
                                {
                                    cpte=compteIterator.next();
                                }
                                else
                                {
                                    cpte.setCompte(compte);
                                    compteRepository.save(cpte);
                                }

                            }


                            cod2.setCompte(cpte);
                            solde.setCompte(cpte);

                            //cpte.setCompte(compte);
                            listeCompte.add(cpte);

                            cod2.setNumsequenceflux(nouvelleSequence);
                            cod2.setNumdetails(line.substring(6, 10));
                            cod2.setNumref(line.substring(11, 32));


                            if (line.substring(31, 32).equals("1"))
                            {
                                cod2.setMouvement("Dépense");
                            }
                            else
                            {
                                cod2.setMouvement("Recette");
                            }



                            if (cod2.getMouvement().equals("Dépense"))
                            {
                                cod2.setMontantflux((-1)*Double.parseDouble(line.substring(32, 47))/1000);
                                this.amount=this.amount+cod2.getMontantflux();
                                cod2.setSolde(cod2.getMontantflux());
                            }
                            else
                            {
                                cod2.setMontantflux(Double.parseDouble(line.substring(32, 47))/1000);
                                this.amount=this.amount+cod2.getMontantflux();
                                cod2.setSolde(cod2.getMontantflux());
                            }

                            try {
                                jour=line.substring(47, 49)+"/";
                                mois=line.substring(49, 51)+"/";
                                annee="20"+line.substring(51, 53);
                                cod2.setDatevaleur(new SimpleDateFormat("dd/MM/yyyy").parse(jour+mois+annee));
                            } catch (ParseException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                            cod2.setCodeoperation(line.substring(54, 61));

                            if (line.substring(61, 62).equals("1"))
                            {
                                cod2.setCommunication("VCS");
                                cod2.setCommunication(line.substring(65, 78));
                            }
                            else
                            {
                                cod2.setCommunication("Libre");
                                cod2.setCommunication(line.substring(60, 115));

                            }
                            //line.substring(115, 121)
                            jour=line.substring(115, 117)+"/";
                            mois=line.substring(117, 119)+"/";
                            annee="20"+line.substring(119, 121);

                            try {
                                cod2.setDatecomptable(new SimpleDateFormat("dd/MM/yyyy").parse(jour+mois+annee));
                            } catch (ParseException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            cod2.setRefclient(fichier);
                            datesolde=line.substring(115, 121);
                            //cod2.setCompteContrepartie(line.substring(11, 48));


                            nb_ligne++; //On incrémente le compteur de ligne

                            if (nb_ligne==345)
                            {
                                //System.out.println("ok");
                            }

                            //En fonction du type de lignes on sauvegarde les données génériques

                        }
                        else if ((line.substring(0, 2)).equals("21") && (ancienneSequence.equals(nouvelleSequence)))
                        {
                            if ((cod2.getCommunication()).equals("Libre"))
                            {
                                cod2.setCommunication(line.substring(63, 116));
                            }
                        }
                        else if ((line.substring(0, 2)).equals("22"))
                        {
                            if ((cod2.getCommunication()).equals("Libre"))
                            {
                                cod2.setCommunication(line.substring(10, 64));
                            }
                        }
                        else if ((line.substring(0, 2)).equals("23"))
                        {
                            if ((cod2.getCommunication()).equals("Libre"))
                            {
                                cod2.setCommunication(line.substring(83, 126));
                            }

                            cod2.setComptecontrepartie(line.substring(10, 48));
                            cod2.setComptecontrepartie(line.substring(46, 82));
                        }

                        else if ((line.substring(0, 2)).equals("31"))
                        {
                            if ((cod2.getCommunication()).equals("Libre"))
                            {
                                cod2.setCommunication(line.substring(40, 114));
                            }
                        }
                        else if ((line.substring(0, 2)).equals("32"))
                        {
                            //if ((cod2.getTypeCommunication()).equals("Libre"))
                            {
                                cod2.setAdresse(line.substring(10, 116));
                            }
                        }
                    }
                    /////////////////////////////////////////////////////////////////////////////////////////////////

                    liste.add(cod2); // ajout du dernier flux
                    fr.close();  // On ferme le fichier


                    solde.setDate(datesolde);
                    solde.setSoldedebut(soldedebut);
                    solde.setSoldefin(soldefin);
                    listesolde.add(solde);


                }

            }

            this.nbligne=nb_ligne;

            System.out.println("  > Nombre de fichiers traités :" + nb_file + " de type 21 :" + liste.size());

            System.out.println("  > Nombre de ligne de fichiers CODA :" + nb_ligne + " et enregistrements: " + w);

            //nombreLigne=nombreLigne+nb_ligne;

            System.out.println("  > Liste des fichiers " + (k) +" traités à déplacer");

            //Déplacement des fichiers traités
            for (int j=0; j<k ; j++)
            {
                File fichier = new File(traites[j]);
                File dest = new File(fichier.getParent()+"/Traite/"+fichier.getName());
                //OK System.out.println(Paths.get(traites[j]) + " > " + dest);
               // if (fichier.renameTo(dest))
                {
                    // System.out.println("Déplacement effectué");
                }
            }

            log.info("Enregistremnt des flux financiers");
            this.sauvegarde();

            log.info("Enregistremnt des flux financiers terminé");

        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void sauvegarde()
    {
        this.getListe().forEach(coda -> {
            log.info("Enregistrement coda : " + coda.getNumref());
            codaRepository.save(coda);
        });
    }
}
