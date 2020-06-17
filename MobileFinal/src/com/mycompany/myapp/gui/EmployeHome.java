/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Session;
import com.mycompany.myapp.entities.User;

/**
 *
 * @author arij_
 */
public class EmployeHome extends Form {
          User User=Session.getCurrentSession();
                      private Form current;
           private Resources themeee;
    public EmployeHome(Form p){
          
        current = this; //Récupération de l'interface(Form) en cours
        setTitle("Espace Employés");
        setLayout(BoxLayout.y());
 Button btr = new Button("Se déconnecter");
              

           btr.addActionListener(e->{
           new Login(p).show();
           });
 themeee = UIManager.initFirstTheme("/themeee");
         
        add(new Label("Veuillez choisir une option"));
     
          final Button bntex = new Button("Gestion des Sante ");
        
 
             bntex.addActionListener(e -> new Gsante().show());
              

        
        addAll(bntex,btr);
  try {
      
                Session.close();
            } catch (Exception ex) {
                ex.getMessage();
            }
    
    }
    
}
