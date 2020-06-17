/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package Mesclasses;

import Entities.Demande;
import Services.ServiceDemande;
import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.cleanmodern.BaseForm;
import com.codename1.uikit.cleanmodern.NewsfeedForm;
import Mesclasses.NewsfeedFormAffichageDemande;



/**
 * Signup UI
 *
 * @author 
 */
public class AjouterDemande extends BaseForm {
  
    private Demande c;

    public AjouterDemande(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");
        
          add(BorderLayout.NORTH, new Label(res.getImage("Logo.png"), "LogoLabel"));
                
        TextField titreDem = new TextField("", "titreDem", 20, TextField.ANY);
        TextField descDem = new TextField("", "descDem", 20, TextField.ANY);
        TextField montantDem = new TextField("", "montantDem", 20, TextField.ANY);
        TextField delaiFinal = new TextField("", "delaiFinal", 20, TextField.ANY);
        

        titreDem.setSingleLineTextArea(false);
        descDem.setSingleLineTextArea(false);
        montantDem.setSingleLineTextArea(false);
        delaiFinal.setSingleLineTextArea(false);
        
        Button Ajouter = new Button("Ajouter");
       
        
  
        
        Container content = BoxLayout.encloseY(
                new Label("Ajouter une demande", "LogoLabel"),
                new FloatingHint(titreDem),
                createLineSeparator(),
                new FloatingHint(descDem),
                createLineSeparator(),
                new FloatingHint(montantDem),
                createLineSeparator(),
                   new FloatingHint(delaiFinal),
                createLineSeparator()
              
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                Ajouter
               
        ));
        
        
        
        Ajouter.requestFocus();
      
                Ajouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ServiceDemande ser = new ServiceDemande();
                Demande c = new Demande( titreDem.getText(),descDem.getText(),Double.valueOf(montantDem.getText()),delaiFinal.getText());
                
                if((titreDem.getText()=="")) {
                    
                    Dialog.show("Information", "veuillez remplir le champs titre demande ", "ok", null);
                    new AjouterDemande(res).show();
                }
                
                
                
                else{
                    ser.ajouterDemande(c);
                    Dialog.show("Information", "La demande "+titreDem.getText()+" est ajoutée", "ok", null);
                    new NewsfeedFormAffichageDemande(res).show();
                }
            }
        });
       
     
      


  
     
      
}
}