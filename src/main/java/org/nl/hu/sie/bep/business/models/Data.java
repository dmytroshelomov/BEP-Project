package org.nl.hu.sie.bep.business.models;

import org.bson.Document;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class Data {
    public String Straat;
    public String Type;
    public String Huisnummer;
    public String postcode;
    public String plaats;
    public String BIC;

    public int KlantID;
    public int PersoonID;

    public String Bedrijfsnaam;
    public String Rechtsvorm;
    public String VAT;
    public String BankRek;
    public String Giro;
    public String Bik;
    public String Voornaam;
    public String Tussenvoegsel;
    public String Achternaam;
    public String Telefoon;
    public String Fax;
    public String Geslacht;
    public String note;
    public List<Document> invoiceLines;
    public java.util.Date Date;
    public double InvoiceID;

    public Data(String straat,
                String type,
                String huisnummer,
                String postcode,
                String plaats,
                String BIC,
                int klantID,
                int persoonID,
                String bedrijfsnaam,
                String rechtsvorm,
                String VAT,
                String bankRek,
                String giro,
                String bik,
                String voornaam,
                String tussenvoegsel,
                String achternaam,
                String telefoon,
                String fax,
                String geslacht,
                String note

    ) {
        Straat = straat;
        Type = type;
        Huisnummer = huisnummer;
        this.postcode = postcode;
        this.plaats = plaats;
        this.BIC = BIC;
        KlantID = klantID;
        PersoonID = persoonID;
        Bedrijfsnaam = bedrijfsnaam;
        Rechtsvorm = rechtsvorm;
        this.VAT = VAT;
        BankRek = bankRek;
        Giro = giro;
        Bik = bik;
        Voornaam = voornaam;
        Tussenvoegsel = tussenvoegsel;
        Achternaam = achternaam;
        Telefoon = telefoon;
        Fax = fax;
        Geslacht = geslacht;
        note = note;


    }





    /** Code zet data van database om naar JavaObject
     *
     * @param rs
     * @return
     */
    public static Data fromResultSet(ResultSet rs) {
        try {
            String straat = rs.getString("straat");
            String type = rs.getString("type");
            String huisnummer = rs.getString("huisnummer");
            String postcode = rs.getString("postcode");
            String plaats = rs.getString("plaats");
            String BIC = rs.getString("BIC");
            int klantID = rs.getInt("klantID");
            int persoonID = rs.getInt("persoonID");
            String bedrijfsnaam = rs.getString("bedrijfsnaam");
            String rechtsvorm = rs.getString("rechtsvorm");
            String VAT = rs.getString("VAT");
            String bankRek = rs.getString("bankRek");
            String giro = rs.getString("giro");
            String bik = rs.getString("bik");
            String voornaam = rs.getString("voornaam");
            String tussenvoegsel = rs.getString("tussenvoegsel");
            String achternaam = rs.getString("achternaam");
            String telefoon = rs.getString("telefoon");
            String fax = rs.getString("fax");
            String geslacht = rs.getString("geslacht");

            return new Data(
                    straat,
                    type,
                    huisnummer,
                    postcode,
                    plaats,
                    BIC,
                    klantID,
                    persoonID,
                    bedrijfsnaam,
                    rechtsvorm,
                    VAT,
                    bankRek,
                    giro,
                    bik,
                    voornaam,
                    tussenvoegsel,
                    achternaam,
                    telefoon,
                    fax,
                    geslacht,
                    ""

            );
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        return "Data{" +
                "Straat='" + Straat + '\'' +
                ", Type='" + Type + '\'' +
                ", Huisnummer='" + Huisnummer + '\'' +
                ", postcode='" + postcode + '\'' +
                ", plaats='" + plaats + '\'' +
                ", BIC='" + BIC + '\'' +
                ", KlantID=" + KlantID +
                ", PersoonID=" + PersoonID +
                ", Bedrijfsnaam='" + Bedrijfsnaam + '\'' +
                ", Rechtsvorm='" + Rechtsvorm + '\'' +
                ", VAT='" + VAT + '\'' +
                ", BankRek='" + BankRek + '\'' +
                ", Giro='" + Giro + '\'' +
                ", Bik='" + Bik + '\'' +
                ", Voornaam='" + Voornaam + '\'' +
                ", Tussenvoegsel='" + Tussenvoegsel + '\'' +
                ", Achternaam='" + Achternaam + '\'' +
                ", Telefoon='" + Telefoon + '\'' +
                ", Fax='" + Fax + '\'' +
                ", Geslacht='" + Geslacht + '\'' +
                ", note='" + note + '\'' +
                ", invoiceLines='" + invoiceLines + '\'' +
                ", invoiceID=" + InvoiceID + '\'' +
                ", Date=" + Date + '\'' +
                '}';
    }
}
