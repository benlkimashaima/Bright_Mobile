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
import com.mycompany.myapp.entities.Specialite;
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
public class ServiceSpecialite {
    

   public ArrayList<Specialite> tasks;
    
    public static ServiceSpecialite instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceSpecialite() {
         req = new ConnectionRequest();
    }

    public static ServiceSpecialite getInstance() {
        if (instance == null) {
            instance = new ServiceSpecialite();
        }
        return instance;
    }



    public ArrayList<Specialite> parseTasks(String jsonText){
        try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
               Specialite t = new Specialite();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                            t.setNomSpecialite(obj.get("nomSpecialite").toString());

                
                tasks.add(t);
            }
          
        } catch (IOException ex) {
            
        }
        return tasks;
    }
    
    public ArrayList<Specialite> getAllTasks(){
        String url = Statics.BASE_URL6+"dash/tasks/alll";
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
     
       
    public boolean ModifierNote(Specialite t) {
        String url = Statics.BASE_URL6 + "dash/tasks/editReview?idReview=" + t.getId()+"&nomSpecialite=" +t.getNomSpecialite();
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
     public boolean supprimerTask(Specialite t ) {
        String url = Statics.BASE_URL6 + "dash/tasks/deleteReview?idReview=" + t.getId()  ;
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
     
         public boolean addTask(Specialite t) {
          String url = Statics.BASE_URL6 + "dash/tasks/neww?nomSpecialite=" +t.getNomSpecialite();
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
 public boolean supprimerNote(int id ) {
         
        String url = Statics.BASE_URL6 + "dash/tasks/deleteReview?idReview=" + id  ;
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