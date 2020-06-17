/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;
import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Session;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceUser;
/**
 *
 * @author ferie
 */
public class Login extends Form {
    Form current;
    private static User User;
Form cuurent;
           private Resources theme;
    public Login(Form p) {
        current = this; //Récupérsation de l'interface(Form) en cours
        setTitle("Bienvenue");
        setLayout(new FlowLayout(Component.CENTER, Component.CENTER));
        Container cnt = new Container(BoxLayout.y());
        TextField username = new TextField(null, "Nom d'utisateur");
        TextField password = new TextField(null, "Mot de passe");
        password.setConstraint(TextField.PASSWORD);
        Button login = new Button("Se connecter");
theme = UIManager.initFirstTheme("/themeee");
           
        cnt.add(username);
        cnt.add(password);
        cnt.add(login);
        add(cnt);
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
            if(username.getText().equals("mahmoud")
                && password.getText().equals("mahmoud"))
        {
    new EmployeHome(current).show();
        }
        
        else{
        Dialog.show("error","login ou pwd invalid","ok","cancel");
        }
            }
        });

     
                        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->  { new GestionSante(current).show();});
    }    
    public Login() {
    }
}