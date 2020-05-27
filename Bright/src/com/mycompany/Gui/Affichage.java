       /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.Gui;
import com.codename1.ui.Component;
import com.codename1.components.MultiButton;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
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
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.Entities.stock;
import com.mycompany.Service.StockService;



/**
 *
 * @author HP
 */
public class Affichage extends BaseForm {
    public Affichage(Resources theme) {
         super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Profile");
        getContentPane().setScrollVisible(false);
        super.addSideMenu(theme);
        tb.addSearchCommand(e -> { 
            String text = (String)e.getSource();
            if(text == null || text.length() == 0) {
                for(Component cmp : getContentPane()) {
                    cmp.setHidden(false);
                    cmp.setVisible(true);
                }
                getContentPane().animateLayout(150);
            }
            else {
                 text = text.toLowerCase();
                 for(Component cmp : getContentPane()) {
            MultiButton mb = (MultiButton)cmp;
            String line1 = mb.getTextLine1();
            String line2 = mb.getTextLine2();
            boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1 ||
                    line2 != null && line2.toLowerCase().indexOf(text) > -1;
            mb.setHidden(!show);
            mb.setVisible(show);
        }
       getContentPane().animateLayout(150);
    }
}, 4);
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

        
        
           StockService serviceTask = new StockService();
           for (stock ev : serviceTask.getList2()) {
           Label type = new Label("Type de Stock : " + ev.getType());
           type.getStyle().setFgColor(0x1e3642);
           Font smallPlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
           type.getStyle().setFont(smallPlainSystemFont);
           Button Supprimer =  new Button("Supprimer Type");
           Button modifier = new Button ("Modifier Type");
           Supprimer.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent evt) {
               if (Dialog.show("Confirmation", "Vous Voulez Vraiment Supprimer ce Type ", "Ok", "Annuler")) {
               StockService stock = new StockService();
               stock.Supprimer(ev.getId());
               Dialog.show("Alert", "stock "+ev.getType()+" supprimé", "ok", null);         
               new Affichage(theme).show();;
                    

                    }
                }
          });
        
        modifier.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent ee) {
      TextField  eNom = new TextField("","Type");
      Button b = new Button("Modifier");
      b.getUnselectedStyle().setFgColor(100000);
      b.getAllStyles().setBorder(Border.createRoundBorder(12, 12)); 
      eNom.setText(ev.getType());
      eNom.getStyle().setFgColor(0x1e3642);
      Font smallPlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
      eNom.getStyle().setFont(smallPlainSystemFont);
               add(eNom);
               add(b);
               show();
              b.addActionListener((e)->{
                  
                      if (eNom.getText().isEmpty()) {
              Dialog.show("Information Dialog", " le type  Doit étre Rempli", "OK", "Cancel");  
            }
                 
               else {
             stock s = new stock ();
                    int   id = ev.getId();
                    s.setType(eNom.getText());
                    s.setId(id);
                    StockService ser = new StockService();
                     ser.modifierStock(s);
            Dialog.show("Information Dialog", "le Type  '"+eNom.getText()+"' A été Modifier Avec Succés ", "OK", "Cancel");
       eNom.clear();
       }
                     Affichage h=new Affichage(theme);
                   h.show();
                    });}
            });
  add(BorderLayout.center(type));
  add(Supprimer);                   
  add(modifier); 
  
  
  
              }
    }
}

