package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Medecin;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceMedecin {
    

   public ArrayList<Medecin> tasks;
    
    public static ServiceMedecin instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceMedecin() {
         req = new ConnectionRequest();
    }

    public static ServiceMedecin getInstance() {
        if (instance == null) {
            instance = new ServiceMedecin();
        }
        return instance;
    }

    public boolean addTask(Medecin t) {
          String url = Statics.BASE_URL6 + "dash/tasks/newww?nCIN=" + t.getnCIN()+"&nonMedecin=" + t.getNonMedecin()+"&prenomMedecin=" + t.getPrenomMedecin()+"&telephone=" + t.getTelephone()+"&specialite=" +t.getSpecialite()+"&email=" +t.getEmail();
        req.setUrl(url);
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

    public ArrayList<Medecin> parseTasks(String jsonText){
        try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
               Medecin t = new Medecin();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
               
                t.setnCIN(((int)Float.parseFloat(obj.get("nCIN").toString())));
                t.setNonMedecin(obj.get("nonMedecin").toString());
                 t.setPrenomMedecin(obj.get("prenomMedecin").toString());
                 t.setTelephone(obj.get("telephone").toString());
                t.setEmail(obj.get("email").toString());
                
        
    
                tasks.add(t);
            }
          
        } catch (IOException ex) {
            
        }
        return tasks;
    }
    
    public ArrayList<Medecin> getAllTasks(){
        String url = Statics.BASE_URL6+"dash/tasks/allll";
        req.setUrl(url);

        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }

    
    
    public boolean Modifiercl(Medecin t) {
        String url = Statics.BASE_URL6 + "dash/tasks/editRevieww?idReview=" + t.getId()+"&nCIN=" + t.getnCIN()+"&nonMedecin=" + t.getNonMedecin()+"&prenomMedecin=" + t.getPrenomMedecin()+"&telephone=" + t.getTelephone()+"&email=" +t.getEmail();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this); 
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
     public boolean supprimerTask(Medecin t ) {
        String url = Statics.BASE_URL6 + "dash/tasks/deleteRevieww?idReview=" + t.getId()  ;
        req.setUrl(url);
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
 public boolean supprimerCalen(int id ) {
         
        String url = Statics.BASE_URL6 + "dash/tasks/deleteRevieww?idReview=" + id  ;
        req.setUrl(url);
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
   
}
