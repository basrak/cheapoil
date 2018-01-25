package com.example.fcd.cheapoil.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.fcd.cheapoil.controller.Controle;
import com.example.fcd.cheapoil.R;

public class MainActivity extends AppCompatActivity {

    private ImageButton btnMenu1;
    private ImageButton btnMenu2;
    private Controle controle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void creerMenu()
    {
        btnMenu1 = (ImageButton) findViewById(R.id.btnMenu1);
        btnMenu2 = (ImageButton) findViewById(R.id.btnMenu2);
        ecouteMenu(btnMenu1, FindStationActivity.class);
        ecouteMenu(btnMenu2, UpdatePriceActivity.class);
    }

    public void ecouteMenu(ImageButton imageButton, final Class classe)
    {

        imageButton.setOnClickListener(new ImageButton.OnClickListener() {
            public void onClick(View v) {

                //Toast.makeText(CalculActivity.this, "test", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, classe);
                startActivity(intent);

            }
        });
    }
}
