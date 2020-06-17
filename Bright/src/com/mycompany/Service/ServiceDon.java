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
import com.mycompany.Entities.don;
import com.mycompany.Entities.stock;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author HP
 */
public class ServiceDon {
 ArrayList<don> listDon = new ArrayList<>();
 ArrayList<stock> stock = new ArrayList<>();

public void ajouter(don d) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/PI2/web/app_dev.php/stock/add"
                +"?libelle=" + d.getLibelle()
                + "&quantite=" + d.getQuantite()
                + "&Stock_id=" + d.getStock_id()
                 ;
        System.out.println("L'URL est : : :" + Url);
        con.setUrl(Url);
        con.addResponseListener((e) -> {
        String str = new String(con.getResponseData());
         System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
public ArrayList<don> parseListdonJson(String json) {
      try {
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(json.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
             
                don t = new don();
                float reference = Float.parseFloat(obj.get("reference").toString());
                t.setReference((int)reference);
                t.setLibelle(obj.get("libelle").toString());
                float quantite = Float.parseFloat(obj.get("quantite").toString());
                t.setQuantite((int)quantite);
                t.setSto(obj.get("type").toString());

                listDon.add(t);
            }
            
        } catch (IOException ex) {
        }
        return listDon;
    }
public ArrayList<don> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PI2/web/app_dev.php/stock/tasks/alld");  
        con.setPost(false);
       con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                listDon = parseListdonJson(new String(con.getResponseData()));
                con.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listDon;
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
public void modifierdon(don es , int id) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/PI2/web/app_dev.php/stock/modifierRefg/"+id+"?quantite="+es.getQuantite();
        con.setUrl(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
   
            
}



