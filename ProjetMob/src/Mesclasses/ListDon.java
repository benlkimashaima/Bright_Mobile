/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Mesclasses;

import Entities.Demande;
import Entities.Don;
import Mesclasses.AjouterDemande;
import Services.ServiceDemande;
import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.cleanmodern.BaseForm;
import Mesclasses.NewsfeedFormAffichageDemande;
import Services.ServiceDon;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author hp
 */
public class ListDon  extends BaseForm{
    
     ArrayList< Don> listcour=ServiceDon.getInstance().getList2();
       int id=0;
   
ServiceDon cour= new  ServiceDon();
    public ListDon(Form previous,Don c, Resources res) throws IOException {
        super("Newsfeed", BoxLayout.y());

               Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Le don selectionné");
        getContentPane().setScrollVisible(false);

      tb.setBackCommand("", e -> previous.showBack());
        setUIID("Activate");
        
          
         
               Container c1 = new Container(BoxLayout.y());
               Label a  = new Label(".");
                Label b  = new Label(".");
                 Label d  = new Label(".");
               Label montant = new Label(" Le montant "+c.getMontantDon());
               Label date = new Label(" La date :" +c.getDateDon() );
               Label Demande = new Label(" La demande affectée :" +c.getDemande() );

              
               
                         Container C = new Container(BoxLayout.y());
                        C.add(a).add(b).add(d).add(montant).add(date).add(Demande);
                         
                   
 
                     
                       Button supp = new Button("Supprimer");
                        Button modifier = new Button("modifier");
                      
//     


         

         

 supp.addActionListener(new ActionListener() {
                   @Override
         public void actionPerformed(ActionEvent evt) {
                ServiceDon cour= new  ServiceDon();
                cour.supprimer(c.getIdDon());
                 Dialog.show("Information", "Le don "+c.getMontantDon()+" a été supprimé ", "ok", null);
          new NewsfeedFormAffichageDon(res).show();
            }} );
 
 
// getToolbar().addCommandToOverflowMenu("Modifier", res.getImage("pencil.png"), ev->{
//            new updatecour(res, P).show();
//        });
      
//            
       modifier.addActionListener(e -> new ModifierDon(res,c).show());
            

                              
add(C).add(supp);
   add(modifier);
    
}
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    } 
    
    
    
}