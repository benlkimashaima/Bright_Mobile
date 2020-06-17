/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ToastBar;
import com.codename1.io.Util;
import com.codename1.ui.Button;
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
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Specialite;
import com.mycompany.myapp.services.ServiceSpecialite;

/**
 *
 * @author User
 */
public class SupprimerSpecialite extends Form{
    Form current ;
              private Resources themeee;

    public SupprimerSpecialite(Form p){
                                                themeee = UIManager.initFirstTheme("/themeee");

        setTitle("Supprimer une Specialite");
        setLayout(BoxLayout.y());
         Toolbar.setGlobalToolbar(true);
        
   getToolbar().addMaterialCommandToSideMenu("Santer",FontImage.MATERIAL_IMPORTANT_DEVICES, e -> {});
      getToolbar().addMaterialCommandToSideMenu("HOME", FontImage.MATERIAL_HOME, e -> { new EmployeHome(current).show();});
      getToolbar().addMaterialCommandToSideMenu("Affichage", FontImage.MATERIAL_LIST, e -> { new Lse().show(); });
      getToolbar().addMaterialCommandToSideMenu("Gestions", FontImage.MATERIAL_APPS, e -> { new Gsante().show(); });

   getToolbar().addMaterialCommandToSideMenu("Exit", FontImage.MATERIAL_EXIT_TO_APP, e -> {Display.getInstance().exitApplication();}); 
        TextField tfCin = new TextField("","id");
    Button btnsupp = new Button("Supprimer");
     getToolbar().addCommandToOverflowMenu("Exit",null, ev->{
        Display.getInstance().exitApplication(); });
        
    ///////////////////////////////////////////////////////
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
    TextArea ta = new TextArea("Vous voulez vraiment Supprimer cette Specialite");
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
     
btnsupp.addActionListener(e -> {
  
    dlg.showDialog();
       ToastBar.Status status = ToastBar.getInstance().createStatus();
            status.setMessage("Specialite Supprimer ...");
            status.setShowProgressIndicator(true);
            status.setIcon(createIcon(FontImage.MATERIAL_WORK));
            status.show();
            // Now do something that takes a while
                     status.setExpires(1500);

});
    //////////////////////////////////////////////////////
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                         if ((tfCin.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                       Specialite t = new Specialite(Integer.parseInt(tfCin.getText()));
                        if( ServiceSpecialite.getInstance().supprimerTask(t))
                            Dialog.show("Success","Sprcialite Supprimer avec succés !",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                }
                     }});
                addAll(tfCin,btnsupp);
                        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> p.showBack());
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
