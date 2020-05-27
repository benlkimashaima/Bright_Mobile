/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Gui;

/**
 *
 * @author HP
 */
import com.codename1.ui.TextField;
import com.twilio.type.PhoneNumber;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
public class SMS {
    public static final String ACCOUNT_SID = "ACf8db374d90a328424c04b9c45fabe392";
    public static final String AUTH_TOKEN = "f8f9d45565237058964fa8e343626f97";

    public static void SMS_DON(String libelle) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message.creator(
                new PhoneNumber("+21629618401"),
                new PhoneNumber("+17652323105"),
                "Vous avez ajouté  '"+libelle+"'  en stock").create();
    }
}

