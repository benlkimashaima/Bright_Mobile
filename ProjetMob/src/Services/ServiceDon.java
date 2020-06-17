/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Services;

import Entities.Demande;
import Entities.Don;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author hp
 */
public class ServiceDon {
    
    
     public static ServiceDon instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    public ServiceDon() {
         req = new ConnectionRequest();
    }

    public static ServiceDon getInstance() {
        if (instance == null) {
            instance = new ServiceDon();
        }
        return instance;}
    
      public void ajouterDon(Don c) {
      ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/PI9/web/app_dev.php/don/AddDon?montantDon="+c.getMontantDon()+"&Demande_idDemande="+c.getDemande_idDemande();
        con.setUrl(Url);
         con.setPost(true);
         
            con.addArgument("montantDon" ,Double.toString(c.getMontantDon()));
          con.addArgument("dateDon",c.getDateDon());
          con.addArgument("Demande_idDemande",Integer.toString(c.getDemande_idDemande()));
        
     

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
//   
////    
public void supprimer(int idDon) {
        ConnectionRequest con = new ConnectionRequest();
       
        con.setUrl("http://localhost/PI9/web/app_dev.php/don/SuppDon/"+idDon+"");
        con.setPost(false);
        con.addResponseListener((evt) -> {
            System.out.println(con.getResponseData());

        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }     
////
public boolean update(Don c) {
    
                String Url = "http://localhost/PI9/web/app_dev.php/don/UpdateDon/"+c.getIdDon()+"?montantDon="+c.getMontantDon();  
                
        req.setUrl(Url);
        System.out.println(Url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
//
    public ArrayList<Don> parseListClubJson(String json) throws JSONException {
          System.out.println("DEBUG, 48, parseListClubJSON:" + json);

        ArrayList<Don> listDon = new ArrayList<>();
       JSONArray jsonArray = new JSONArray(json);
        for (int i = 0; i < jsonArray.length(); i++) {
            listDon.add(jsonToDon(jsonArray.getJSONObject(i)));
            
        }
         System.out.println(listDon);
        return listDon;

    }
//       
    
    ArrayList<Don> listDon = new ArrayList<>();
    ArrayList<Demande> listTitreDem = new ArrayList<>();

    

      
    public ArrayList<Don> getList2(){       
        
       String url ="http://localhost/PI9/web/app_dev.php/don/AllDon";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    listDon = parseListClubJson(new String(req.getResponseData()));
                } catch (JSONException ex) {
                    
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return listDon;
    }
    
    
    
    
    
    
 public static void main(String[] args) {
        (new ServiceDemande()).getList2();
    }
    
    
    
 private Don jsonToDon(JSONObject jsonObject) throws JSONException {
        Integer idDon = jsonObject.getInt("idDon");

        Double montantDon = jsonObject.getDouble("montantDon");
        String dateDon = jsonObject.getString("dateDon");
       String  Demande_idDemande = jsonObject.getString("Demande_idDemande");
        
        return  new  Don(idDon,montantDon,dateDon,Demande_idDemande);
    }

  
}