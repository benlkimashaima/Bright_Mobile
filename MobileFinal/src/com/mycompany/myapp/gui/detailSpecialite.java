/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ToastBar;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Specialite;
//import com.mycompany.myapp.entities.Examen;
import com.mycompany.myapp.services.ServiceSpecialite;

/**
 *
 * @author User
 */
public class detailSpecialite extends Form {
    
 Form current ;
                  private Resources themeee;

    public detailSpecialite(Specialite a){
                                                themeee = UIManager.initFirstTheme("/themeee");

        setTitle("Detaile Specialite");
        setLayout(BoxLayout.y());

            //Image im = theme.getImage("im age.jpg");
            //ImageViewer img = new ImageViewer(im);
                                       Label id = new Label();

                          
                         Label coefficient = new Label();
                         
                         TextField tfid= new TextField("","id");
        tfid.getAllStyles().setBgColor(ColorUtil.CYAN);
      
                              
                   TextField tfcoefficient= new TextField("","Nom Specialite");

                         

 

  
   
TextComponent idid = new TextComponent().label("id :");
                         id.setText(String.valueOf(a.getId()));
                         TextComponent coeff = new TextComponent().label("Nom Specialite :");
                         coefficient.setText(String.valueOf(a.getNomSpecialite()));
                         
                           Button btsup = new Button("Supprimer");
                                 Button btmod = new Button("Modifier");
    
                                 
        
    ////////////////////////////////////
              Dialog dlg = new Dialog("Warning");
    Style dlgStyle = dlg.getDialogStyle();
    dlgStyle.setBorder(Border.createEmpty());
    dlgStyle.setBgTransparency(255);
    dlgStyle.setBgColor(0xffffff);

    Label title = dlg.getTitleComponent();
    //title.setIcon(finalDuke.scaledHeight(title.getPreferredH()));
    title.getUnselectedStyle().setFgColor(0xff);
    title.getUnselectedStyle().setAlignment(Component.LEFT);

    dlg.setLayout(BoxLayout.y());
    Label blueLabel = new Label();
    blueLabel.setShowEvenIfBlank(true);
    blueLabel.getUnselectedStyle().setBgColor(0xff);
    blueLabel.getUnselectedStyle().setPadding(1, 1, 1, 1);
    blueLabel.getUnselectedStyle().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
    dlg.add(blueLabel);
    TextArea ta = new TextArea("Vous voulez vraiment supprimer cette Specialite");
    ta.setEditable(false);
    ta.setUIID("DialogBody");
    ta.getAllStyles().setFgColor(0);
    dlg.add(ta);

    Label grayLabel = new Label();
    grayLabel.setShowEvenIfBlank(true);
    grayLabel.getUnselectedStyle().setBgColor(0xcccccc);
    grayLabel.getUnselectedStyle().setPadding(1, 1, 1, 1);
    grayLabel.getUnselectedStyle().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
    dlg.add(grayLabel);

    Button ok = new Button(new Command("OUI"));
    Button non = new Button(new Command("Annuler"));
    ok.getAllStyles().setBorder(Border.createEmpty());
    ok.getAllStyles().setFgColor(0);
    dlg.add(ok);
    non.getAllStyles().setBorder(Border.createEmpty());
    non.getAllStyles().setFgColor(0);
    dlg.add(non);
     
btsup.addActionListener(e -> {
  
    dlg.showDialog();
     ToastBar.Status status = ToastBar.getInstance().createStatus();
            status.setMessage("Specialite Supprimer ...");
            status.setShowProgressIndicator(true);
            status.setIcon(createIcon(FontImage.MATERIAL_WORK));
            status.show();
            // Now do something that takes a while
            Display.getInstance().invokeAndBlock(()->{
                Util.sleep(1000);
            });
});
///////////////////////////////////////////////////////////////////////

                           ok.addActionListener((evt) -> {
                                   try {
                             if( ServiceSpecialite.getInstance().supprimerNote(a.getId()))
                            Dialog.show("Succès","Specialite a été supprimé",new Command("OK"));
                            else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                             } catch (NumberFormatException ex) {
                                 Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                            }
                                                               new Lse().show();

                 });
                                                    Button btnValider = new Button("valider");

                            btmod.addActionListener((evt) -> {
                      tfcoefficient.setText(String.valueOf(a.getNomSpecialite()));

                                 addAll(tfcoefficient,btnValider);
                                refreshTheme();
                            });
                            
                            
                            
                            
/////////////////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////
                 Dialog dlgg = new Dialog("Warning");
    Style dlgStylee = dlgg.getDialogStyle();
    dlgStylee.setBorder(Border.createEmpty());
    dlgStylee.setBgTransparency(255);
    dlgStylee.setBgColor(0xffffff);

    Label titlee = dlgg.getTitleComponent();
    //title.setIcon(finalDuke.scaledHeight(title.getPreferredH()));
    titlee.getUnselectedStyle().setFgColor(0xff);
    titlee.getUnselectedStyle().setAlignment(Component.LEFT);

    dlgg.setLayout(BoxLayout.y());
    Label blueLabell = new Label();
    blueLabell.setShowEvenIfBlank(true);
    blueLabell.getUnselectedStyle().setBgColor(0xff);
    blueLabell.getUnselectedStyle().setPadding(1, 1, 1, 1);
    blueLabell.getUnselectedStyle().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
    dlgg.add(blueLabell);
    TextArea taa = new TextArea("Vous voulez vraiment Modifier cette Specialite");
    taa.setEditable(false);
    taa.setUIID("DialogBody");
    taa.getAllStyles().setFgColor(0);
    dlgg.add(taa);

    Label grayLabell = new Label();
    grayLabell.setShowEvenIfBlank(true);
    grayLabell.getUnselectedStyle().setBgColor(0xcccccc);
    grayLabell.getUnselectedStyle().setPadding(1, 1, 1, 1);
    grayLabell.getUnselectedStyle().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
    dlgg.add(grayLabell);

    Button okk = new Button(new Command("OUI"));
    Button nonn = new Button(new Command("Annuler"));
    okk.getAllStyles().setBorder(Border.createEmpty());
    okk.getAllStyles().setFgColor(0);
    dlgg.add(okk);
    nonn.getAllStyles().setBorder(Border.createEmpty());
    nonn.getAllStyles().setFgColor(0);
    dlgg.add(nonn);
     
btnValider.addActionListener(e -> {
  
    dlgg.showDialog();
    ToastBar.Status statuss = ToastBar.getInstance().createStatus();
            statuss.setMessage("Specialite Modifier ...");
            statuss.setShowProgressIndicator(true);
            statuss.setIcon(createIcon(FontImage.MATERIAL_WORK));
            statuss.show();
            // Now do something that takes a while
            Display.getInstance().invokeAndBlock(()->{
                Util.sleep(1000);
            });
});                           
//////////////////////////////////////////////////////////////////////////////////////////
                           btnValider.addActionListener(new ActionListener() {
                                @Override
                                 public void actionPerformed(ActionEvent evt) {
               
                    try {
                        Specialite t = new Specialite(a.getId(),tfcoefficient.getText());
                        if( ServiceSpecialite.getInstance().ModifierNote(t))
                            Dialog.show("Success","Specialite modifier avec succés!",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Erreur!", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }             
                
                
                 new Lse().show();
            
                   
                 }          });
                           
                         this.addAll(idid,id,coeff,coefficient,btsup,btmod);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> { new Lse().show();});


  }
     private Image createIcon(char charcode) {
         
        int iconWidth = Display.getInstance().convertToPixels(8, true);
        Style iconStyle = new Style();
        Font iconFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
        if (Font.isNativeFontSchemeSupported()) {
            iconFont = Font.createTrueTypeFont("native:MainBold", null).derive((int)(iconWidth * 0.5), Font.STYLE_BOLD);
        }
        iconStyle.setFont(iconFont);
        iconStyle.setFgColor(0xffffff);
        iconStyle.setBgTransparency(0);

        FontImage completeIcon = FontImage.createMaterial(charcode, iconStyle);
        return completeIcon;
    }
    
}

