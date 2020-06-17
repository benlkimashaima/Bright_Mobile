/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.messaging.Message;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Medecin;
import com.mycompany.myapp.services.ServiceMedecin;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class Lme extends Form{
    
 private Resources theme;
Form current;

    public Lme(Form previous) {
    theme = UIManager.initFirstTheme("/themeee");
                           
                                
                                Toolbar.setGlobalToolbar(true);
Style s = UIManager.getInstance().getComponentStyle("Title");


TextField searchField = new TextField("", "Afficher Medecin"); 
searchField.getHintLabel().setUIID("Title");
searchField.setUIID("Title");
searchField.getAllStyles().setAlignment(Component.LEFT);
getToolbar().setTitleComponent(searchField);
FontImage searchIcon = FontImage.createMaterial(FontImage.MATERIAL_SEARCH, s);
searchField.addDataChangeListener((i1, i2) -> { 
    String t = searchField.getText();
    if(t.length() < 1) {
        for(Component cmp : getContentPane()) {
            cmp.setHidden(false);
            cmp.setVisible(true);
        }
    } else {
        t = t.toLowerCase();
        for(Component cmp : getContentPane()) {
            String val = null;
            if(cmp instanceof Label) {
                val = ((Label)cmp).getText();
            } else {
                if(cmp instanceof TextArea) {
                    val = ((TextArea)cmp).getText();
                } else {
                    val = (String)cmp.getPropertyValue("text");
                }
            }
            boolean show = val != null && val.toLowerCase().indexOf(t) > -1;
            cmp.setHidden(!show); 
            cmp.setVisible(show);
        }
    }
    getContentPane().animateLayout(250);
});
getToolbar().addCommandToRightBar("", searchIcon, (e) -> {
    searchField.startEditingAsync(); 
});
        
        Toolbar.setGlobalToolbar(true);
        
   getToolbar().addMaterialCommandToSideMenu("Santer",FontImage.MATERIAL_IMPORTANT_DEVICES, e -> {});
      getToolbar().addMaterialCommandToSideMenu("HOME", FontImage.MATERIAL_HOME, e -> { new  EmployeHome(current).show();});
   
   getToolbar().addMaterialCommandToSideMenu("Exit", FontImage.MATERIAL_EXIT_TO_APP, e -> {Display.getInstance().exitApplication();}); 
          //ArrayList<Map<String, Object>> data = new ArrayList<>();
          //data.add(createListEntry(ServiceTaskmonta.getInstance().getAllTasks().toString()));
         SpanLabel sp = new SpanLabel();
       //  List<AddTaskForm> sp = new ArrayList<AddTaskForm>();
        sp.setText(ServiceMedecin.getInstance().getAllTasks().toString());
       // add(sp);
        ArrayList<Medecin> ss;
        ss=ServiceMedecin.getInstance().getAllTasks();
        for (int i=0; i < ss.size();i++){
        SpanLabel l = new SpanLabel();    
  
                
           String  ch =   "id:   "+ss.get(i).getId()+" "+
                   "nCIN :  "+ss.get(i).getnCIN()+" "+
                   "nonMedecin :  "+ss.get(i).getNonMedecin()+" "+
                   "PrenomMedecin :  "+ss.get(i).getPrenomMedecin()+" "+
                    "telephone :  "+ss.get(i).getTelephone()+" "+
                   "email :  "+ss.get(i).getEmail()+" ";

                               
                                                              
                            l.setText(ch);
                            
                            this.add(l);
                          
                            Medecin c =   new Medecin(ss.get(i).getId(),ss.get(i).getnCIN(),ss.get(i).getNonMedecin(),ss.get(i).getPrenomMedecin(),ss.get(i).getTelephone(),ss.get(i).getEmail());
                                                
                            l.addPointerReleasedListener(ke -> {
                     new detailMedecin(c).show();
                     });   
                           
                             
                              
        }
        
 
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    
    
}
