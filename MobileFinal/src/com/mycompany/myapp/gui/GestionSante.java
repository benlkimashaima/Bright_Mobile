/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

/**
 *
 * @author arij_
 */
public class GestionSante extends Form{
     private Form current;
           private Resources themeee;
    public GestionSante (Form p ){
     current = this; //Récupération de l'interface(Form) en cours
        setTitle("Espace Employés");
        setLayout(BoxLayout.y());
 
 themeee = UIManager.initFirstTheme("/themeee");
            //Image im = theme.getImage("im age.jpg");
            //ImageViewer img = new ImageViewer(im);
           
         
        add(new Label("Veuillez choisir une option"));
      final  Button btnAddTask = new Button("Espace Employés ");
        final Button btnListTasks = new Button("Espace User");

   

   

        btnAddTask.addActionListener(e -> new Login(current).show());
        btnListTasks.addActionListener(e -> new Login2(current).show());
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> p.showBack());


            addAll(btnAddTask, btnListTasks);

    
    }
}
