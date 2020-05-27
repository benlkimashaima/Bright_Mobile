/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Gui;



import com.codename1.components.MediaPlayer;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.plaf.Border;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Font;
import com.codename1.ui.Display;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.Button;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.mycompany.Entities.don;
import com.mycompany.Service.ServiceDon;

/**
 *
 * @author HP
 */
public class AffichageDon extends BaseForm{
     
      
     
       Form f;
 
    public AffichageDon (Resources theme ) {
         super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Profile");
        getContentPane().setScrollVisible(false);
        super.addSideMenu(theme);
        tb.addSearchCommand(e -> {});
        Image img = theme.getImage("profile-background.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                    GridLayout.encloseIn(3, 
                           
                            FlowLayout.encloseCenter(
                                new Label(theme.getImage("logo.jpg"), "PictureWhiteBackgrond"))
                    )
                )
        ));

        
        
        
           ServiceDon serviceTask = new ServiceDon();
                for (don ev : serviceTask.getList2()) {
                Label Libelle = new Label("Libelle: " + ev.getLibelle() );
                Label Quantite = new Label("Quantite: " + ev.getQuantite());
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String strDate = dateFormat.format(ev.getDate()); 
                //Label Date = new Label("Date: " + strDate);
              Label stock = new Label("Type Stock: " + ev.getSto());
              Libelle.getStyle().setFgColor(0x1e3642);
              Font smallPlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
              Libelle.getStyle().setFont(smallPlainSystemFont);
              Quantite.getStyle().setFgColor(0x1e3642);
              Quantite.getStyle().setFont(smallPlainSystemFont);
//            Date.getStyle().setFgColor(0x1e3642);
             //Date.getStyle().setFont(smallPlainSystemFont);
              stock.getStyle().setFgColor(0x1e3642);
              stock.getStyle().setFont(smallPlainSystemFont);
              Button supprimer =  new Button("Supprimer Don");
              Button modifier = new Button ("Modifier Type");

                                
        
    
     supprimer.addActionListener(new ActionListener() {
               @Override
         public void actionPerformed(ActionEvent evt) 
         {          Dialog d = new Dialog();
                    if (Dialog.show("Confirmation", "Vous Voulez Vraiment Supprimer ce Don ", "Ok", "Annuler")) {
                    ServiceDon stock = new ServiceDon();
                    stock.Supprimer(ev.getReference());
                    Dialog.show("Alert", "Libelle "+ev.getLibelle()+" supprimé", "ok", null);
                            new AffichageDon(theme).show();;
    }
         }
              });  
     
     modifier.addActionListener(new ActionListener() {
                @Override
           public void actionPerformed(ActionEvent ee) {
      TextField  Quantite = new TextField("","Quantite");
      Button modifier = new Button("Modifier la Quantite");
      modifier.getUnselectedStyle().setFgColor(100000);
      modifier.getAllStyles().setBorder(Border.createRoundBorder(12, 12));    
      Quantite.setText(String.valueOf(ev.getQuantite()));
      Quantite.getStyle().setFgColor(0x1e3642);
      Font smallPlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
      Quantite.getStyle().setFont(smallPlainSystemFont);
            add(Quantite);
            add(modifier);
            show();
              modifier.addActionListener((e)->{
                       if (Quantite.getText().isEmpty()) {
                         Dialog.show("Information Dialog", "Quantite Doit étre Rempli", "OK", "Cancel");  
                       }
                       else if (Integer.parseInt(Quantite.getText()) < 0) {
                           Dialog.show("ERREUR SAISIE", "Quantite  Doit étre Supérieur  à 0", "OK", "ANNULER");}
                 
                       else {
                         don s = new don ();
                         int   reference = ev.getReference();
                         s.setQuantite(Integer.parseInt(Quantite.getText()));
                         s.setReference(reference);
                         ServiceDon ser = new ServiceDon();
                         ser.modifierdon(s,reference);
                        Dialog.show("Information Dialog", "La Quantite '"+Quantite.getText()+"' A été Modifier Avec Succés ", "OK", "Cancel");
                        Quantite.clear();
                            }
                        AffichageDon h=new AffichageDon(theme);
                        h.show();
                     
                    });}
            });
          

 add(Libelle);
 add(Quantite);
//add(BorderLayout.center(Date));                       
 add(stock);
 add(supprimer);
 add(modifier);                

   
      
        

            
              }
    }
    
}