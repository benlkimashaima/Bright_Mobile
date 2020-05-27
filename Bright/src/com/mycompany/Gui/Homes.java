/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package com.mycompany.Gui;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.spinner.Picker;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Display;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.Entities.stock;
import com.mycompany.Service.StockService;
/**
 *
 * @author HP
 */
public class Homes extends BaseForm{
    
    
    TextField type;
    Button ajouter,afficher;
    public Homes(Resources theme)  {
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
        
        
       type = new TextField("","Type de Stock...!",20,TextField.ANY);
       type.getUnselectedStyle().setFgColor(621200);
        ajouter = new Button("Ajouter Type de Stock ");
        afficher=new Button("Listes Des Types");
        add(type);
        add(ajouter);
        add(afficher);
         
        ajouter.addActionListener((e) -> {
            
            if (type.getText().equals("")) {
                Dialog.show("ERREUR SAISIE","TYPE de stock VIDE","OK","ANNULER");
            }
              else{
            StockService ser = new StockService();
              try{
            stock ev1 = new stock(type.getText());
            ser.ajoutStock(ev1);
            Dialog.show("felicitation", " votre Type a ete ajoute", "ok", null);            
            }catch(Exception ex){}
            }
        });
       
        afficher.addActionListener((e)->{
        Affichage a;
        
                a = new Affichage(theme);
                   a.show();
         
     
        });
    }

public TextField getType() {
        return type;
    }
public void setType(TextField type) {
        this.type = type;
    }
public Button getBtnajout() {
        return ajouter;
    }
public void setBtnajout(Button btnajout) {
        this.ajouter = btnajout;
    }
public Button getBtnaff() {
        return afficher;
    }
public void setBtnaff(Button btnaff) {
        this.afficher = btnaff;
    }
public boolean isAFloat(String x) {
        try {
            Float.parseFloat(x);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }
public boolean isAEntier(String x) {
        try {
            Integer.parseInt(x);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }
}
