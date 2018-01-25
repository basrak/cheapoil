package com.example.fcd.cheapoil.model;

import android.util.Log;

import com.example.fcd.cheapoil.controller.Controle;
import com.example.fcd.cheapoil.tools.AccesHTTP;
import com.example.fcd.cheapoil.tools.AsyncResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Admin on 24/12/2017.
 */

public class AccesDistant implements AsyncResponse{

    public final static String ServerAdrr = "http://192.168.56.1/Coach/serveurcoach.php";
    public Controle controle;

    public AccesDistant(){
        this.controle = Controle.getInstance(null);
    }


    @Override
    public void processFinish(String output) {

        Log.d("serveur", "********" + output);
        String[] message = output.split("%");
        ArrayList<Profil> lesProfils = new ArrayList<Profil>();

        if(message.length > 1)
        {
            if(message[0].equals("enreg"))
                Log.d("message=", "******"+message[1]);
            else if(message[0].equals("tous"))
                try {
                    //JSONObject info = new JSONObject(message[1]);
                    JSONArray info = new JSONArray(message[1]);
                    Log.d("jsonarray=", "******"+info);
                    for(int i=0; i < info.length(); i++) {
                        JSONObject obj = new JSONObject("" + info.get(i));
                        Date dateMesure = MesOutils.convertStringToDate(obj.getString("dateMesure"), "yyyy-MM-dd hh:mm:ss");
                        int poids = obj.getInt("poids");
                        int taille = obj.getInt("taille");
                        int age = obj.getInt("age");
                        int sexe = obj.getInt("sexe");
                        Profil profil = new Profil(age, poids, taille, sexe, dateMesure);
                        lesProfils.add(profil);
                    }
                    controle.setLesProfils(lesProfils);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            else if(message[0].equals("Erreur !"))
                Log.d("message=", "******"+message[1]);
        }
    }

    public void envoi(String operation, JSONArray lesDonneesJSON)
    {
        AccesHTTP accesDonnees;
        accesDonnees = new AccesHTTP();
        accesDonnees.delegate = this;

        accesDonnees.addParam("operation", operation);
        accesDonnees.addParam("lesdonnees", lesDonneesJSON.toString());

        accesDonnees.execute(ServerAdrr);
    }
}
