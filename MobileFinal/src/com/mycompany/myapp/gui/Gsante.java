/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

/**
 *
 * @author bhk
 */
public class Gsante extends Form{
Form current;
  private Resources themeee;

    public Gsante() {
        
                        themeee = UIManager.initFirstTheme("/themeee");

        
        
        
        current=this;
        setTitle("GESTION SANTER");
        setLayout(BoxLayout.y());
         Toolbar.setGlobalToolbar(true);
        
   getToolbar().addMaterialCommandToSideMenu("Easychool",FontImage.MATERIAL_IMPORTANT_DEVICES, e -> {});
      getToolbar().addMaterialCommandToSideMenu("HOME", FontImage.MATERIAL_HOME, e -> { new EmployeHome(current).show();});
      getToolbar().addMaterialCommandToSideMenu("Deconnecter", FontImage.MATERIAL_DANGEROUS, e -> { new EmployeHome(current).show();});

   getToolbar().addMaterialCommandToSideMenu("Exit", FontImage.MATERIAL_EXIT_TO_APP, e -> {Display.getInstance().exitApplication();}); 
        add(new Label("Vous avez connecter en tant que employer"));
        Button btme = new Button("Gestion Specialite");
        Button btse = new Button("Gestion Medecin");
         getToolbar().addCommandToOverflowMenu("Exit",null, ev->{
        Display.getInstance().exitApplication(); });
        
       // Button btnAddnote = new Button("Ajouter une note");
         
        
       // Button btnListTasks = new Button("List Tasks");
        btme.addActionListener(e-> new GesSpecialite().show());
         btse.addActionListener(e-> new GesMedecin().show());
         // btme.addActionListener(e-> new ModifierEx(current).show());
            //btse.addActionListener(e-> new SupprimerEx(current).show());
       // btnListTasks.addActionListener(e-> new ListTasksForm(current).show());
        addAll(btme,btse);
                        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->  { new EmployeHome(current).show();});

        
        
    }
    
    
}
