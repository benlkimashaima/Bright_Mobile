/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.User;
import static com.mycompany.myapp.utils.Statics.BASE_URL6;
import java.io.IOException;
import java.util.Map;

/**
 *
 * @author ferie
 */
public class ServicesUsers {

    public static ServicesUsers instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public User User = new User();

    private ServicesUsers() {
        req = new ConnectionRequest();
    }

    public static ServicesUsers getInstance() {

        if (instance == null) {
            instance = new ServicesUsers();
        }
        return instance;
    }

    public User parseUser(String jsonText) {
   
        User UserL = new User();
        try {
            JSONParser j = new JSONParser();

            Map<String, Object> UserListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            if (UserListJson.get("type").equals("Login succeed")) {

                float id = Float.parseFloat(UserListJson.get("id").toString());
                UserL.setId((int) (id));
                UserL.setFirstname(UserListJson.get("firstname").toString());
                UserL.setLastname(UserListJson.get("lastname").toString());
          
                UserL.setEmail(UserListJson.get("email").toString());

                if (UserListJson.get("role").toString().equals("[ROLE_STUDENT]")) {
                    UserL.setRoles("student");
                } else {
                    UserL.setRoles("teacher");
                }

            } else {
                return null;
            }

        } catch (IOException ex) {
                ex.getMessage();
        }

        return UserL;
    }

    public User Login(String username,String password) {
        String url =BASE_URL6 +"ap/loginMobile/"+username+"/"+password;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                User = parseUser(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return User;
    }

}
