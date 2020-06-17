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
public class Demande {
    private int IdDemande;

    public Demande(String titreDem, String descDem, Double montantDem, String delaiFinal) {
        this.titreDem = titreDem;
        this.descDem = descDem;
        this.montantDem = montantDem;
        this.delaiFinal = delaiFinal;
    }

    public Demande(int IdDemande, String titreDem, String descDem, Double montantDem, String delaiFinal) {
        this.IdDemande = IdDemande;
        this.titreDem = titreDem;
        this.descDem = descDem;
        this.montantDem = montantDem;
        this.delaiFinal = delaiFinal;
    }

    public int getIdDemande() {
        return IdDemande;
    }

    public void setIdDemande(int IdDemande) {
        this.IdDemande = IdDemande;
    }

    public String getTitreDem() {
        return titreDem;
    }

    public void setTitreDem(String titreDem) {
        this.titreDem = titreDem;
    }

    public String getDescDem() {
        return descDem;
    }

    public void setDescDem(String descDem) {
        this.descDem = descDem;
    }

    public Double getMontantDem() {
        return montantDem;
    }

    public void setMontantDem(Double montantDem) {
        this.montantDem = montantDem;
    }

    public String getDelaiFinal() {
        return delaiFinal;
    }

    public void setDelaiFinal(String delaiFinal) {
        this.delaiFinal = delaiFinal;
    }
    
     
    private String titreDem;
    private String descDem;
    private Double montantDem;
    private String delaiFinal;

   @Override
    public String toString() {
        return  titreDem ;
    
    
    }
    
}
