package com.example.fcd.cheapoil.controller;

import android.content.Context;

import com.example.fcd.cheapoil.model.AccesDistant;

import org.json.JSONArray;

/**
 * Created by fcd on 25/01/2018.
 */

public final class Controle {

    private static Controle instance = null;
    private static AccesDistant accesDistant;
    private static Context contexte;

    public static final Controle getInstance(Context context) {
        if (Controle.instance == null)
        {
            Controle.instance = new Controle();
            contexte = context;
            accesDistant = new AccesDistant();
            accesDistant.envoi("tous", new JSONArray());
        }
        return Controle.instance;
    }



}
