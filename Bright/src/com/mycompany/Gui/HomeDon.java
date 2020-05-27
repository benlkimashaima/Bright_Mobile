/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Gui;


import com.codename1.io.FileSystemStorage;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.spinner.Picker;
import com.codename1.components.ScaleImageLabel;
import com.codename1.io.Util;
import com.codename1.ui.Display;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.l10n.DateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.Entities.don;
import com.mycompany.Entities.stock;
import com.mycompany.Service.ServiceDon;
import com.mycompany.Service.StockService;
import java.util.Date;
/**
 *
 * @author HP
 */
public class HomeDon extends BaseForm{
     
        public HomeDon () {
            
        }

    TextField libelle;
    TextField quantite;
 ComboBox<stock> comboStock = new ComboBox<stock>();
    Button ajouter,afficher,PDF;

    public HomeDon(Resources theme)  {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Profile");
        getContentPane().setScrollVisible(false);
         setLayout(BoxLayout.y());
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
        
        
        
       libelle = new TextField("","Libelle.........!",20,TextField.ANY);
       libelle.getUnselectedStyle().setFgColor(621200);
       quantite = new TextField("","Quantite.....!",20,TextField.ANY);
       quantite.getUnselectedStyle().setFgColor(621200);
       StockService serviceTask = new StockService();
       for (stock ev : serviceTask.getList2()) {
            comboStock.addItem(ev);
        }
      ajouter = new Button("Ajouter Don");
      afficher=new Button("Listes Des Dons");
     PDF = new Button("PDF  des Dons et des Stocks");

      Picker date = new Picker();
      DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
 
      add(libelle);
       add(quantite);
      // add(date);
       add(comboStock);
       add(ajouter);
       add(afficher);
       add(PDF);
    
       
       
         
       
       ajouter.addActionListener(new ActionListener() {
                   @Override
                   public void actionPerformed(ActionEvent e) {
                       if (libelle.getText().equals("")) {
                           
                           Dialog.show("ERREUR SAISIE","libelle VIDE","OK","ANNULER");
                       }
                       
                       else if (quantite.getText().equals("")) {
                           
                           Dialog.show("ERREUR SAISIE","quantite VIDE","OK","ANNULER");
                       }
                       else if (!isAEntier(quantite.getText())) {
                           
                           Dialog.show("ERREUR SAISIE","quantite doit etre un nombre ","OK","ANNULER");
                       }
                       else if (Integer.parseInt(quantite.getText()) < 0) {
                           Dialog.show("ERREUR SAISIE", "quantite  Doit étre Supérieur  à 0", "OK", "ANNULER");}
                       else{
                        
                            int esp = comboStock.getSelectedItem().getId();
                            ServiceDon ser = new ServiceDon();
                            don article = new don(1,libelle.getText(),Integer.parseInt(quantite.getText()),(Date) date.getDate(),esp);
                            SMS.SMS_DON(libelle.getText());
                           ser.ajouter(article);
                           Dialog.show("felicitation", " votre Don a ete ajoute", "ok", null);
                       }          }
               });
       
       afficher.addActionListener((e)->{
       AffichageDon a =new AffichageDon(theme);
        a.show();
        });
        
       PDF.addActionListener((evt) -> {
          String pdf= "http://localhost/PI2/web/app_dev.php/dashboard/pdf";
          FileSystemStorage FS = FileSystemStorage.getInstance();
          String fileName = FS.getAppHomePath() + "DON.pdf";
                     Util.downloadUrlToFile(pdf, fileName, true);
                    Display.getInstance().execute(fileName);
          });
    }


  
   

   
  
    public Button getBtajout() {
        return ajouter;
    }
    public void setBtajout(Button btajout) {
        this.ajouter = btajout;
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
