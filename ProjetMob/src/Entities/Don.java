/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Med Amir Jday
 */
public class Don {
    
    private int idDon;
    private Double montantDon;
    private String dateDon;
    private int Demande_idDemande;
    private String demande;
    
    public Don(int idDon, Double montantDon, String dateDon) {
        this.idDon = idDon;
        this.montantDon = montantDon;
        this.dateDon = dateDon;
    }
    

    public Don(Double montantDon, int Demande_idDemande) {
        this.montantDon = montantDon;
        this.Demande_idDemande = Demande_idDemande;
    }
    

    public int getDemande_idDemande() {
        return Demande_idDemande;
    }

    public void setDemande_idDemande(int Demande_idDemande) {
        this.Demande_idDemande = Demande_idDemande;
    }
    
    
     public Don(int idDon, Double montantDon) {
        this.idDon = idDon;
        this.montantDon = montantDon;
        
    }
     

    public Don(Double montantDon, String dateDon) {
        this.montantDon = montantDon;
        this.dateDon = dateDon;
    }

    public Don(Double montantDon) {
        this.montantDon = montantDon;
    }
   

    public int getIdDon() {
        return idDon;
    }

    public void setIdDon(int idDon) {
        this.idDon = idDon;
    }

    public Double getMontantDon() {
        return montantDon;
    }

    public void setMontantDon(Double montantDon) {
        this.montantDon = montantDon;
    }

    public String getDateDon() {
        return dateDon;
    }

    public void setDateDon(String dateDon) {
        this.dateDon = dateDon;
    }

    public Don(int idDon, Double montantDon, String dateDon, int Demande_idDemande) {
        this.idDon = idDon;
        this.montantDon = montantDon;
        this.dateDon = dateDon;
        this.Demande_idDemande = Demande_idDemande;
    }

    public String getDemande() {
        return demande;
    }

    public void setDemande(String demande) {
        this.demande = demande;
    }

    public Don(int idDon, Double montantDon, String dateDon, String demande) {
        this.idDon = idDon;
        this.montantDon = montantDon;
        this.dateDon = dateDon;
        this.demande = demande;
    }
    
    
    
}
