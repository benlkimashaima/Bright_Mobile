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
import Entities.Don;
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
import Services.ServiceDon;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.plaf.Style;



/**
 * Signup UI
 *
 * @author 
 */
public class AjouterDon extends BaseForm {
  
    
   ComboBox<Demande> comboDem = new  ComboBox<Demande>();
    private Don c;

    public AjouterDon(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        setLayout(BoxLayout.y());
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");
        
        
        Style s3=getAllStyles();
        s3.setBgImage(res.getImage("confirmation-background.jpg"));
        
        comboDem.getAllStyles().setMargin(Component.TOP,30);

        TextField montantDon = new TextField("", "montantDon", 20, TextField.ANY);
  
        montantDon.getAllStyles().setMargin(Component.TOP,30);


        montantDon.setSingleLineTextArea(false);
      
        
        Button Ajouter = new Button("Ajouter");
        
        add(montantDon);
        add(comboDem);
        
   ServiceDemande servicedem =new ServiceDemande();
                 for (Demande ev : servicedem.getList2()) {
                     comboDem.addItem(ev);
                 }
     
        
        add(Ajouter);
        
      
                Ajouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
               
                
                
                ServiceDon ser = new ServiceDon();
                int esp = comboDem.getSelectedItem().getIdDemande();

                Don c = new Don(Double.valueOf(montantDon.getText()),esp);
                
                if((montantDon.getText()=="")) {
                    
                    Dialog.show("Information", "veuillez remplir le champs montant ", "ok", null);
                    new AjouterDon(res).show();
                }
                
                
                
                else{
                    ser.ajouterDon(c);
                    Dialog.show("Information", "Le don "+montantDon.getText()+" est ajouté", "ok", null);
                    new NewsfeedFormAffichageDon(res).show();
                }
            }
        });
       
     
      


  
     
      
}
}