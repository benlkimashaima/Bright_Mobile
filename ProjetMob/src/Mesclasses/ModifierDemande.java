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



/**
 * Signup UI
 *
 * @author 
 */
public class ModifierDemande extends BaseForm{

    public ModifierDemande(Resources res,Demande c) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");
        
          add(BorderLayout.NORTH, new Label(res.getImage("Logo.png"), "LogoLabel"));
          
        TextField id = new TextField(String.valueOf(c.getIdDemande()), "id", 20, TextField.ANY);    
        TextField Titre = new TextField(c.getTitreDem(), "Titre", 20, TextField.ANY);
        TextField Description = new TextField(c.getDescDem(), "Description " , 20, TextField.ANY);
        TextField Montant = new TextField(String.valueOf(c.getMontantDem()), "Montant" , 20, TextField.ANY);
        TextField Delai= new TextField(String.valueOf(c.getDelaiFinal()), "Delai", 20, TextField.ANY);
        
         id.setSingleLineTextArea(false); 
        Titre.setSingleLineTextArea(false);
        Description.setSingleLineTextArea(false);
        Montant.setSingleLineTextArea(false);
        Delai.setSingleLineTextArea(false);
        
        Button valider = new Button("valider");
        
        
  
        
        Container content = BoxLayout.encloseY(
                new Label("Modifier la demande", "LogoLabel"),
                new FloatingHint(Titre),
                createLineSeparator(),
                new FloatingHint(Description),
                createLineSeparator(),
                new FloatingHint(Montant),
                createLineSeparator(),
                new FloatingHint(Delai),
                createLineSeparator()
              
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                valider
               
        ));
        
        
        valider.requestFocus();
      
                valider.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                ServiceDemande ser = new ServiceDemande();
              
                Demande c = new Demande(Integer.valueOf(id.getText()),Titre.getText(),Description.getText(),Double.valueOf(Montant.getText()),Delai.getText());
                ser.update(c);
                   Dialog.show("Information", "La demande "+Titre.getText()+" est modifiée", "ok", null);
            }
        });
                
                
       
     
         valider.addActionListener(e -> new NewsfeedFormAffichageDemande(res).show());
}


    

    

   
    }
