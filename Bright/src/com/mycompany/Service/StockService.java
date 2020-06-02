/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.Entities.stock;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author HP
 */
public class StockService {
  ArrayList<stock> listStocks = new ArrayList<>();

  
  public void ajoutStock(stock ev) {
        ConnectionRequest con = new ConnectionRequest();
         String Url = "http://localhost/PI2/web/app_dev.php/stock/tasks/newd"
                +"?type=" + ev.getType()
                 ;
        System.out.println("L'URL est : : :" + Url);
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion
        con.addResponseListener((e) -> {
        String str = new String(con.getResponseData());//Récupération de la réponse du serveur
        System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
  public ArrayList<stock> parseListStockJson(String json) {
        try {
            listStocks=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(json.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
             
                stock t = new stock();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                t.setType(obj.get("type").toString());
                listStocks.add(t);
            }
            
        } catch (IOException ex) {
        }
        return listStocks;
    }
  public ArrayList<stock> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PI2/web/app_dev.php/stock/tasks/all");  
         con.setPost(false);
         con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                listStocks = parseListStockJson(new String(con.getResponseData()));
                con.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listStocks;
    }
 public void modifierStock(stock es) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/PI2/web/app_dev.php/stock/updateComM/"+es.getId()+"?type="+es.getType();// création de l'URL
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
  public void Supprimer(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PI2/web/app_dev.php/stock/"+id+"/deleteS");
        con.setPost(false);
        con.addResponseListener((evt) -> {
            System.out.println(con.getResponseData());

        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }
  
 

   
}
