/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.Gui;
import com.codename1.components.FloatingHint;
import com.codename1.facebook.User;
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
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.Gui.BaseForm;



/**
 *
 * @author HP
 */
public class SignUpForm extends BaseForm
{
    private TextField username = new TextField("", "Username", 20, TextField.ANY);
    private TextField nom = new TextField("", "Nom", 20, TextField.ANY);
    private TextField prenom = new TextField("", "Prenom", 20, TextField.ANY);
    private TextField email = new TextField("", "E-Mail", 20, TextField.EMAILADDR);
    private TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
    private TextField confirmPassword = new TextField("", "Confirm Password", 20, TextField.PASSWORD);
    private boolean isFormEmpty()
    {
        return !username.getText().equals("")  && !nom.getText().equals("") && !prenom.getText().equals("") && !email.getText().equals("") && !password.getText().equals("") && 
                !confirmPassword.getText().equals("")
               ;
    }
    
    private boolean passwordMatches()
    {
        return password.getText().equals(confirmPassword.getText());
    }
   
    public SignUpForm(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(false);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");
       
        username.setSingleLineTextArea(false);
        nom.setSingleLineTextArea(false);
        prenom.setSingleLineTextArea(false);
        email.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        confirmPassword.setSingleLineTextArea(false);
        Button next = new Button("Next");
        Button signIn = new Button("Sign In");
        signIn.addActionListener(e -> previous.showBack());
        signIn.setUIID("Link");
        Label alreadHaveAnAccount = new Label("Already have an account?");
        Container content = BoxLayout.encloseY(
                new Label("Sign Up", "LogoLabel"),
                new FloatingHint(username),
                createLineSeparator(),
                new FloatingHint(nom),
                createLineSeparator(),
                new FloatingHint(prenom),
                createLineSeparator(),
                new FloatingHint(email),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
                new FloatingHint(confirmPassword),
                createLineSeparator(),
                createLineSeparator()
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                next,
                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
        ));
        next.requestFocus();
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt)
            {
                if(isFormEmpty())
                {
                        if(passwordMatches())
                        {
                            User u = new User();
                           
                            Dialog.show("Registration Successful","Welcome to SmartStart !\n a verification email has been sent,\nYou must provide the verification token in order to confirm your account","ok",null);
                        }
                        else
                            Dialog.show("Password ","Please verify the confirmation password","ok",null);       
                }
                else
                    Dialog.show("Cannot Process request","You must fill all fields before submitting for registration","ok",null);
            }
        });
    }
    
}
