/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Service;


import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.mycompany.Entities.don;
import com.mycompany.Entities.stock;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author HP
 */
public class ServiceDon {
public void ajouter(don d) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/PI2/web/app_dev.php/stock/add"
                +"?libelle=" + d.getLibelle()
                + "&quantite=" + d.getQuantite()
                + "&Stock_id=" + d.getStock_id()
                 ;
        System.out.println("L'URL est : : :" + Url);
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion
        con.addResponseListener((e) -> {
        String str = new String(con.getResponseData());//Récupération de la réponse du serveur
         System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
public ArrayList<don> parseListdonJson(String json) {
        System.out.println("DEBUG, 48, parseListClubJSON:" + json);
        ArrayList<don> listDon = new ArrayList<>();
       JSONArray jsonArray = new JSONArray(json);
        for (int i = 0; i < jsonArray.length(); i++) {
            listDon.add(jsonTodon(jsonArray.getJSONObject(i)));
        }
         System.out.println(listDon);
        return listDon;

    }
ArrayList<don> listDon = new ArrayList<>();
public ArrayList<don> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PI2/web/app_dev.php/stock/tasks/alld");  
        con.setPost(false);
        con.addResponseListener((NetworkEvent evt) -> {
            System.out.println(con.getResponseData());
            listDon = this.parseListdonJson(new String(con.getResponseData()));
        });
      
    
    
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listDon;
    }
public static void main(String[] args) {
        (new ServiceDon()).getList2();
    }
private don jsonTodon(JSONObject jsonObject) {
        Integer reference = jsonObject.getInt("reference");
        String libelle = jsonObject.getString("libelle");
        Integer quantite =jsonObject.getInt("quantite");
        String type = jsonObject.getString("type");
       return  new don(reference,libelle,quantite,type);
    }
public void Supprimer(int reference) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PI2/web/app_dev.php/stock/"+reference+"/deleted");
        con.setPost(false);
        con.addResponseListener((evt) -> {
            System.out.println(con.getResponseData());

        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }
ArrayList<stock> stock = new ArrayList<>();
public void modifierdon(don es , int id) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/PI2/web/app_dev.php/stock/modifierRefg/"+id+"?quantite="+es.getQuantite();// création de l'URL
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
   
            
}



