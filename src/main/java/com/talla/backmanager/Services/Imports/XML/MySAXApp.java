package com.talla.backmanager.Services.Imports.XML;

import com.talla.backmanager.Services.Service.Communs.AjoutTexte;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class MySAXApp extends DefaultHandler {

    public String idOP;
    public boolean tidOP=false;
    public String idGroup;
    public boolean tidGroup=false;
    public String dateCreation;
    public boolean tdateCreation=false;
    public String NombreTransaction;
    public boolean tNombreTransaction=false;
    public String somme;
    public boolean tsomme=false;
    public String global;
    public boolean tglobal=false;
    public String idPfour;
    public boolean tidPfour=false;
    public String montant;
    public boolean tmontant=false;
    public String bic;
    public boolean tbic=false;
    public String tiers;
    public boolean ttiers=false;
    public String compte;
    public boolean tcompte=false;
    public String communication;
    public boolean tcommunication=false;

    public boolean test = false;
    public String data;
    public String ligne = "";
    public String nomFichier="$";



    public void setNomFichier(String nomFichier) {
        this.nomFichier = nomFichier;
    }

    public void Initialisation() {

        this.idOP = "";
        this.idGroup = "";
        this.dateCreation = "";
        this.NombreTransaction = "";
        this.somme = "";
        this.global = "";
        this.idPfour = "";
        this.montant = "";
        this.bic = "";
        this.tiers = "";
        this.compte = "";
        this.communication = "";
    }

    public void Nouvelleligne() {

        this.idPfour = "";
        this.montant = "";
        this.bic = "";
        this.tiers = "";
        this.compte = "";
        this.communication = "";
    }

    public MySAXApp() {
        super();
    }

    public void startDocument() {

    }

    public void endDocument() {

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("CstmrCdtTrfInitn")) {
            test = true;
            this.Initialisation();
        }

        if (qName.equals("MsgId")) {
            tidOP = true;
            this.idOP = data;

        }
        if (qName.equals("CreDtTm")) {
            tdateCreation = true;
            this.dateCreation = data;

        }
        if (qName.equals("NbOfTxs")) {
            tNombreTransaction = true;
            this.NombreTransaction = data;

        }
        if (qName.equals("CtrlSum")) {
            tsomme = true;
            this.somme = data;
        }
        if (qName.equals("PmtInfId")) {
            tidGroup = true;
            this.idGroup = data;
        }
        if (qName.equals("BtchBookg")) {
            tglobal = true;
            this.global = data;
        }

        if (qName.equals("EndToEndId")) {
            tidPfour = true;
            this.idPfour = data;
        }
        if (qName.equals("InstdAmt")) {
            tmontant = true;
            this.montant = data;
        }
        if (qName.equals("BIC")) {
            tbic = true;
            this.bic = data;
        }
        if (qName.equals("Nm")) {
            ttiers = true;
            this.tiers = data;
        }

        if (qName.equals("IBAN")) {
            tcompte = true;
            this.compte = data;
        }
        if (qName.equals("Ustrd")) {
            tcommunication = true;
            this.communication = data;
        }

    }

    public String ligne()
    {
        return ligne;
    }
    private void InitialisationHeader() {
        this.idOP = "";
        this.idGroup = "";
        this.dateCreation = "";
        this.NombreTransaction = "";
        this.somme = "";
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        test = false;
        if (qName.equals("CdtTrfTxInf")) {
            ligne =nomFichier+ "#" + idOP + "#" + idGroup + "#" + dateCreation + "#" + NombreTransaction + "#" + somme + "#" + global
                    + "#" + idPfour + "#" + montant + "#" + bic + "#" + tiers + "#" + compte + "#" + communication;
            Nouvelleligne();
            //System.out.println(ligne);
            AjoutTexte.append("C:\\Users\\Liege\\Downloads\\XML\\OP.txt" , ligne);
        }

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if(tidOP)
        {
            idOP= new String(ch,start,length);
            tidOP=false;
        }

        if(tdateCreation)
        {
            dateCreation= new String(ch,start,length);
            tdateCreation=false;
        }

        if(tNombreTransaction)
        {
            NombreTransaction= new String(ch,start,length);
            tNombreTransaction=false;
        }

        if(tsomme)
        {
            somme= new String(ch,start,length);
            tsomme=false;
        }

        if(tidGroup)
        {
            idGroup= new String(ch,start,length);
            tidGroup=false;
        }

        if(tglobal)
        {
            global= new String(ch,start,length);
            tglobal=false;
        }

        if(tidPfour)
        {
            idPfour= new String(ch,start,length);
            tidPfour=false;
        }

        if(tmontant)
        {
            montant= new String(ch,start,length);
            tmontant=false;
        }

        if(tbic)
        {
            bic= new String(ch,start,length);
            tbic=false;
        }

        if(ttiers)
        {
            tiers= new String(ch,start,length);
            ttiers=false;
        }

        if(tcompte)
        {
            compte= new String(ch,start,length);
            tcompte=false;
        }

        if(tcommunication)
        {
            communication= new String(ch,start,length);
            tcommunication=false;
        }

    }

}
