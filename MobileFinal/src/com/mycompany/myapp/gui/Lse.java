/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.messaging.Message;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.table.DefaultTableModel;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.table.TableModel;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Specialite;
import com.mycompany.myapp.services.ServiceSpecialite;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author User
 */
public class Lse extends Form{
      private Resources theme;
Form current;

    public Lse() {
        
                               theme = UIManager.initFirstTheme("/themeee");
                                
                                /////////////////////////////////////////////////////////////
                               
                                
                                Toolbar.setGlobalToolbar(true);
Style s = UIManager.getInstance().getComponentStyle("Title");


TextField searchField = new TextField("", "Afficher Specialite"); 
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
                                
                                
                                
                           
                                
                                
                                ////////////////////////////////////////////////////////////////////

        
        //setTitle("Afficher Calendriers");
        Toolbar.setGlobalToolbar(true);
        
   getToolbar().addMaterialCommandToSideMenu("Santer",FontImage.MATERIAL_IMPORTANT_DEVICES, e -> {});
      getToolbar().addMaterialCommandToSideMenu("HOME", FontImage.MATERIAL_HOME, e -> { new EmployeHome(current).show();});
  
   getToolbar().addMaterialCommandToSideMenu("Exit", FontImage.MATERIAL_EXIT_TO_APP, e -> {Display.getInstance().exitApplication();}); 
          //ArrayList<Map<String, Object>> data = new ArrayList<>();
          //data.add(createListEntry(ServiceTaskmonta.getInstance().getAllTasks().toString()));
     SpanLabel sp = new SpanLabel();
       //  List<AddTaskForm> sp = new ArrayList<AddTaskForm>();
        sp.setText(ServiceSpecialite.getInstance().getAllTasks().toString());
       // add(sp);
        ArrayList<Specialite> ss;
        ss=ServiceSpecialite.getInstance().getAllTasks();
        for (int i=0; i < ss.size();i++){
        Label l = new Label();    
  
                
           String  ch =   "id:   "+ss.get(i).getId()+" "+
                   "nomSpecialite :  "+ss.get(i).getNomSpecialite()+" ";
                               
                               
                                //  "experienceCondidat:"+s.get(i).getExperience_Candidat();
                               
                            l.setText(ch);
                            
                            this.add(l);
                          
                            Specialite c =   new Specialite(ss.get(i).getId(),ss.get(i).getNomSpecialite());
                        
                                                 l.addPointerReleasedListener(ke -> {
                     new detailSpecialite(c).show();
                     });   
                              
        }
        


        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> { new GesSpecialite().show();});
    }

}
                     
