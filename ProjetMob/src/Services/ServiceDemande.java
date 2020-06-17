/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Services;

import Entities.Demande;
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
public class ServiceDemande {
    
    
     public static ServiceDemande instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    public ServiceDemande() {
         req = new ConnectionRequest();
    }

    public static ServiceDemande getInstance() {
        if (instance == null) {
            instance = new ServiceDemande();
        }
        return instance;}
    
      public void ajouterDemande(Demande c) {
      ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/PI9/web/app_dev.php/don/Add?titreDem="+c.getTitreDem()+"&descDem="+c.getDescDem()+"&montantDem="+c.getMontantDem()
                +"&delaiFinal="+c.getDelaiFinal();
        con.setUrl(Url);
         con.setPost(true);
          con.addArgument("titreDem",c.getTitreDem());
          con.addArgument("dscDem",c.getDescDem());
          con.addArgument("montantDem" ,Double.toString(c.getMontantDem()));
         con.addArgument("delaiFinal" ,c.getDelaiFinal());

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
   
//    
public void supprimer(int idDemande) {
        ConnectionRequest con = new ConnectionRequest();
       
        con.setUrl("http://localhost/PI9/web/app_dev.php/don/Supp/"+idDemande+"");
        con.setPost(false);
        con.addResponseListener((evt) -> {
            System.out.println(con.getResponseData());

        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }     
//
public boolean update(Demande c) {
    
                String Url = "http://localhost/PI9/web/app_dev.php/don/Update/"+c.getIdDemande()+"?titreDem="+c.getTitreDem()+"&descDem="+c.getDescDem()+"&montantDem="+c.getMontantDem()
                +"&delaiFinal="+c.getDelaiFinal();  
                
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
    public ArrayList<Demande> parseListClubJson(String json) throws JSONException {
          System.out.println("DEBUG, 48, parseListClubJSON:" + json);

        ArrayList<Demande> listDemande = new ArrayList<>();
       JSONArray jsonArray = new JSONArray(json);
        for (int i = 0; i < jsonArray.length(); i++) {
            listDemande.add(jsonToDemande(jsonArray.getJSONObject(i)));
            
        }
         System.out.println(listDemande);
        return listDemande;

    }
//       
    
    ArrayList<Demande> listDemande = new ArrayList<>();
    
            ArrayList<Demande> listTitreDem = new ArrayList<>();

    

      
    public ArrayList<Demande> getList2(){       
        
       String url ="http://localhost/PI9/web/app_dev.php/don/All";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    listDemande = parseListClubJson(new String(req.getResponseData()));
                } catch (JSONException ex) {
                    
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return listDemande;
 
    }
    
      public ArrayList<Demande> getListTitreDem(){       
        
       String url ="http://localhost/PI9/web/app_dev.php/don/allDemande";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    listTitreDem = parseListClubJson(new String(req.getResponseData()));
                } catch (JSONException ex) {
                    
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return listTitreDem;
   
    }
    
    
 public static void main(String[] args) {
        (new ServiceDemande()).getList2();
    }
    
    
    
 private Demande jsonToDemande(JSONObject jsonObject) throws JSONException {
        Integer IdDemande = jsonObject.getInt("idDemande");
        String titreDem = jsonObject.getString("titreDem");
        String descDem = jsonObject.getString("descDem");
        Double montantDem = jsonObject.getDouble("montantDem");
        String delaiFinal = jsonObject.getString("delaiFinal");
        
        
        
        return  new  Demande(IdDemande,titreDem,descDem,montantDem,delaiFinal);
    }

  
}