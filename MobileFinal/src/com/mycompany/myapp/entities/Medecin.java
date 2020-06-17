/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author DELL
 */
public class Medecin {
    
    int id,nCIN,specialite;
    String nonMedecin,prenomMedecin,telephone,email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getnCIN() {
        return nCIN;
    }

    public void setnCIN(int nCIN) {
        this.nCIN = nCIN;
    }

    public int getSpecialite() {
        return specialite;
    }

    public void setSpecialite(int specialite) {
        this.specialite = specialite;
    }

    public String getNonMedecin() {
        return nonMedecin;
    }

    public void setNonMedecin(String nonMedecin) {
        this.nonMedecin = nonMedecin;
    }

    public String getPrenomMedecin() {
        return prenomMedecin;
    }

    public void setPrenomMedecin(String prenomMedecin) {
        this.prenomMedecin = prenomMedecin;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Medecin(int id, int nCIN, int specialite, String nonMedecin, String prenomMedecin, String telephone, String email) {
        this.id = id;
        this.nCIN = nCIN;
        this.specialite = specialite;
        this.nonMedecin = nonMedecin;
        this.prenomMedecin = prenomMedecin;
        this.telephone = telephone;
        this.email = email;
    }

    public Medecin(int id, int nCIN, String nonMedecin, String prenomMedecin, String telephone, String email) {
        this.id = id;
        this.nCIN = nCIN;
        this.nonMedecin = nonMedecin;
        this.prenomMedecin = prenomMedecin;
        this.telephone = telephone;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Medecin{" + "id=" + id + ", nCIN=" + nCIN + ", specialite=" + specialite + ", nonMedecin=" + nonMedecin + ", prenomMedecin=" + prenomMedecin + ", telephone=" + telephone + ", email=" + email + '}';
    }

    public Medecin() {
    }

    public Medecin(int id) {
        this.id = id;
    }
    
    
    
  
}
