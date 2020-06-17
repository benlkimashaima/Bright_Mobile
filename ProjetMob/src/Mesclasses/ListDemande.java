/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Mesclasses;

import Entities.Demande;
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

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author hp
 */
public class ListDemande  extends BaseForm{
    
     ArrayList< Demande> listcour=ServiceDemande.getInstance().getList2();
       int id=0;
   
ServiceDemande cour= new  ServiceDemande();
    public ListDemande(Form previous,Demande c, Resources res) throws IOException {
        super("Newsfeed", BoxLayout.y());

               Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("La demande selectionnée");
        getContentPane().setScrollVisible(false);

      tb.setBackCommand("", e -> previous.showBack());
        setUIID("Activate");
        
          
         
               Container c1 = new Container(BoxLayout.y());
               Label a  = new Label(".");
                Label b  = new Label(".");
                 Label d  = new Label(".");
               Label titre = new Label(" Le titre "+c.getTitreDem());
               Label desc = new Label(" La description :" +c.getDescDem() );
               Label montant = new Label( "Le montant voulu :"+c.getMontantDem());
               Label delai = new Label( "Le delai final:"+c.getDelaiFinal());
               
                         Container C = new Container(BoxLayout.y());
                        C.add(a).add(b).add(d).add(titre).add(desc).add(montant).add(delai);
                         
                   
 
                     
                       Button supp = new Button("Supprimer");
                        Button modifier = new Button("modifier");
                      
//     


         

         

 supp.addActionListener(new ActionListener() {
                   @Override
         public void actionPerformed(ActionEvent evt) {
                ServiceDemande cour= new  ServiceDemande();
                cour.supprimer(c.getIdDemande());
                 Dialog.show("Information", "La demande "+c.getTitreDem()+" a été  supprimé ", "ok", null);
          new NewsfeedFormAffichageDemande(res).show();
            }} );
 
 
// getToolbar().addCommandToOverflowMenu("Modifier", res.getImage("pencil.png"), ev->{
//            new updatecour(res, P).show();
//        });
      
            
           modifier.addActionListener(e -> new ModifierDemande(res,c).show());
            

                              
add(C).add(supp);
   add(modifier);
    
}
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    } 
    
    
    
}