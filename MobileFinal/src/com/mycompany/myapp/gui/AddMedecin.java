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
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Medecin;
import com.mycompany.myapp.services.ServiceMedecin;
/**
 *
 * @author bhk
 */
public class AddMedecin extends Form{
     private Resources themeee;
Form current;
    public AddMedecin(Form previous) {
                themeee = UIManager.initFirstTheme("/themeee");

        setTitle("Ajouter un Medecin");
        setLayout(BoxLayout.y());
          Toolbar.setGlobalToolbar(true);
        
      getToolbar().addMaterialCommandToSideMenu("HOME", FontImage.MATERIAL_HOME, e -> { new EmployeHome(current).show();});
      getToolbar().addMaterialCommandToSideMenu("Affichage", FontImage.MATERIAL_LIST, e -> { new Lme(previous).show(); });

   getToolbar().addMaterialCommandToSideMenu("Exit", FontImage.MATERIAL_EXIT_TO_APP, e -> {Display.getInstance().exitApplication();}); 
   
                TextField tfcoff= new TextField("", "nCIN");
                TextField tfn= new TextField("", "NomMedecin");
                TextField tfp= new TextField("", "PrenomMedecin");
                TextField tft= new TextField("", "telephone");
                TextField tfe= new TextField("", "email");


        Button btnValider = new Button("Ajouter Medecin");
         getToolbar().addCommandToOverflowMenu("Exit",null, ev->{
        Display.getInstance().exitApplication(); });
        
        ///////////////////////////////////warning/////////////////////////////////
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
    TextArea ta = new TextArea("Vous voulez vraiment ajouter ce Medecin");
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
    //////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////notification/////////////////////////////// 
btnValider.addActionListener(e -> {
     dlg.showDialog();
  ToastBar.Status status = ToastBar.getInstance().createStatus();
            status.setMessage(" Medecin Ajouter ...");
            status.setShowProgressIndicator(true);
            status.setIcon(createIcon(FontImage.MATERIAL_WORK));
            status.show();
            // Now do something that takes a while
                    status.setExpires(1500);

   
});
////////////////////////////////////////////////////

       ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ///////////////////////controle de saisie///////////////////////////////////////
                if ((tfcoff.getText().length()==0)||(tfn.getText().length()==0)||(tfp.getText().length()==0)||(tft.getText().length()==0)||(tfe.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                /////////////////////////////////////////////////
                else
                {
                    try {
                        Medecin t = new Medecin(0, Integer.parseInt(tfcoff.getText()),tfn.getText(),tfp.getText(),tft.getText(),tfe.getText());
                        if( ServiceMedecin.getInstance().addTask(t))
     //////////////////////////////////////message avec succee//////////////////////////////
                            Dialog.show("Success","Medecin ajoutée avec succés!",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Erreur!", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    } 
                
                }
            }
        });   
        addAll(tfcoff,tfn,tfp,tft,tfe,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface précédente
        
        
        

    }
    ////////////////////////////////////////fonction mta3 l notification///////////////////////////////////ww
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