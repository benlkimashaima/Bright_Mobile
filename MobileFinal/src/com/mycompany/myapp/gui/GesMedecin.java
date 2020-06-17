/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

/**
 *
 * @author User
 */
public class GesMedecin extends Form{
    
Form current;
  private Resources themeee;

    public GesMedecin() {
        
        
        
        
                themeee = UIManager.initFirstTheme("/themeee");

        current=this;
        setTitle("GESTION DES MEDECIN");
        setLayout(BoxLayout.y());
         Toolbar.setGlobalToolbar(true);
        
      getToolbar().addMaterialCommandToSideMenu("",FontImage.MATERIAL_IMPORTANT_DEVICES, e -> {});

   getToolbar().addMaterialCommandToSideMenu("Sante",FontImage.MATERIAL_IMPORTANT_DEVICES, e -> {});
      
   getToolbar().addMaterialCommandToSideMenu("HOME", FontImage.MATERIAL_HOME, e -> { new EmployeHome(current).show();});
         getToolbar().addMaterialCommandToSideMenu("Affichage", FontImage.MATERIAL_LIST, e -> { new Lme(current).show(); });

   getToolbar().addMaterialCommandToSideMenu("Deconnecter", FontImage.MATERIAL_DANGEROUS, e -> { new EmployeHome(current).show();});

   getToolbar().addMaterialCommandToSideMenu("Exit", FontImage.MATERIAL_EXIT_TO_APP, e -> {Display.getInstance().exitApplication();}); 
  /* getToolbar().addMaterialCommandToSideMenu("Chart", FontImage.MATERIAL_SETTINGS, e -> {
  CalanCharts rc = new CalanCharts();
    rc.createPieChartForm().show();    });    */ 
   add(new Label("Vous avez connecter en tant que employer"));
            Image s = themeee.getImage("chboxv1.png");
Image d = themeee.getImage("cal_left_arrow.png");
        Button btnAddTask = new Button("Ajouter un Medecin",s);
        Button btme = new Button("Modifier un Medecin",s);
        Button btse = new Button("Supprimer un Medecin",s);

         getToolbar().addCommandToOverflowMenu("Exit",null, ev->{
        Display.getInstance().exitApplication(); });
        
       // Button btnAddnote = new Button("Ajouter une note");
         
        
       // Button btnListTasks = new Button("List Tasks");
         
        btnAddTask.addActionListener(e-> new AddMedecin(current).show());
          btme.addActionListener(e-> new ModifierMedecin(current).show());
            btse.addActionListener(e-> new SuppMedecin(current).show());
                      

       // btnListTasks.addActionListener(e-> new ListTasksForm(current).show());
 
               getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> {new Gsante().show();});
        addAll(btnAddTask,btme,btse);
        
        
    }
    
    
}
