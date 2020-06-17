/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

/**
 *
 * @author bhk
 */

public class Acceuil extends Form{
Form current; 
private Resources theme;
//private Resources theme;
    public  Acceuil() {
        
        
        
        
        
        current=this;
        setTitle("Bienvenue !");
        setLayout(BoxLayout.y());
       // ImageViewer img = new ImageViewer();
       // img.setImage(theme.getImage("es.png"));
        //add(new Label("Vous avez connecter en tant que admin"));
        Button btex = new Button("Gestion des Examens");
       
       // Button btnListTasks = new Button("List Tasks");
         //Button btc = new Button("Deconnexion");
        //btcp.addActionListener(e-> new Gcomp().show());
                            theme = UIManager.initFirstTheme("/theme");
            Image im2 = theme.getImage("logo.png");
            ImageViewer img2 = new ImageViewer(im2);
            
       // btnListTasks.addActionListener(e-> new ListTasksForm(current).show());
       // btc.addActionListener(e-> new conx().show());
        addAll(img2,btex);
        
        
    }
    
    
}