package com.example.vmirzadanie;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SkinActivity extends AppCompatActivity {

    private ImageButton sarny_skin;
    private ImageButton skin1;
    private ImageButton skin2;
    private ImageButton skin3;
    private ImageButton skin4;
    private ImageButton skin5;
    private ImageButton skin6;
    private ImageButton skin7;
    private ImageButton skin8;
    private ImageButton back;

    SharedPreferences sp;
    SharedPreferences coins;
    SharedPreferences haveSarnySkin;
    SharedPreferences haveSkin3;
    SharedPreferences haveSkin4;
    SharedPreferences haveSkin5;
    SharedPreferences haveSkin6;
    SharedPreferences haveSkin7;
    SharedPreferences haveSkin8;

    private TextView money2;
    boolean have_sarny_skin;
    boolean have_skin_3;
    boolean have_skin_4;
    boolean have_skin_5;
    boolean have_skin_6;
    boolean have_skin_7;
    boolean have_skin_8;

    int money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skin);

        sarny_skin = findViewById(R.id.sarny_skinId);
        skin1 = findViewById(R.id.skin1Id);
        skin2 = findViewById(R.id.skin2Id);
        skin3 = findViewById(R.id.skin3Id);
        skin4 = findViewById(R.id.skin4Id);
        skin5 = findViewById(R.id.skin5Id);
        skin6 = findViewById(R.id.skin6Id);
        skin7 = findViewById(R.id.skin7Id);
        skin8 = findViewById(R.id.skin8Id);

        money2 = findViewById(R.id.coinsId);
        back = findViewById(R.id.backBtn);

        sp = getSharedPreferences("skins", 0);
        coins = getSharedPreferences("money", 0);

        haveSarnySkin = getSharedPreferences("haveSarnySkin", 0);
        haveSkin3 = getSharedPreferences("haveSkin3", 0);
        haveSkin4 = getSharedPreferences("haveSkin4", 0);
        haveSkin5 = getSharedPreferences("haveSkin5", 0);
        haveSkin6 = getSharedPreferences("haveSkin6", 0);
        haveSkin7 = getSharedPreferences("haveSkin7", 0);
        haveSkin8 = getSharedPreferences("haveSkin8", 0);



        have_sarny_skin = haveSarnySkin.getBoolean("haveSarnySkin", false);
        have_skin_3 = haveSkin3.getBoolean("haveSkin3",  false);
        have_skin_4 = haveSkin4.getBoolean("haveSkin4", false);
        have_skin_5 = haveSkin5.getBoolean("haveSkin5",  false);
        have_skin_6 = haveSkin6.getBoolean("haveSkin6", false);
        have_skin_7 = haveSkin7.getBoolean("haveSkin7", false);
        have_skin_8 = haveSkin8.getBoolean("haveSkin8", false);

        money = coins.getInt("money", 0);
        money2 = findViewById(R.id.coinsId);
        money2.setText("" + money);

        if(have_sarny_skin == true){
            sarny_skin.clearColorFilter();
        }else{
            sarny_skin.setColorFilter(Color.argb(220, 0, 0, 0));
        }

        if(have_skin_3 == true){
            skin3.clearColorFilter();
        }else{
            skin3.setColorFilter(Color.argb(220, 0, 0, 0));
        }

        if(have_skin_4 == true){
            skin4.clearColorFilter();
        }else{
            skin4.setColorFilter(Color.argb(220, 0, 0, 0));
        }

        if(have_skin_5 == true){
            skin5.clearColorFilter();
        }else{
            skin5.setColorFilter(Color.argb(220, 0, 0, 0));
        }

        if(have_skin_6 == true){
            skin6.clearColorFilter();
        }else{
            skin6.setColorFilter(Color.argb(220, 0, 0, 0));
        }

        if(have_skin_7 == true){
            skin7.clearColorFilter();
        }else{
            skin7.setColorFilter(Color.argb(220, 0, 0, 0));
        }

        if(have_skin_8 == true){
            skin8.clearColorFilter();
        }else{
            skin8.setColorFilter(Color.argb(220, 0, 0, 0));
        }


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainMenuActivity();
            }
        });

        skin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sp.edit();
                editor.putInt( "skin",R.drawable.skin1);
                editor.commit();
                Toast.makeText(SkinActivity.this,"Skin saved!", Toast.LENGTH_SHORT).show();

            }
        });

        skin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sp.edit();
                editor.putInt( "skin",R.drawable.skin2);
                editor.commit();
                Toast.makeText(SkinActivity.this,"Skin saved!", Toast.LENGTH_SHORT).show();
            }
        });

        skin3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                have_skin_3 = haveSkin3.getBoolean("haveSkin3",  false);

                if(have_skin_3 == false){
                    if(money < 200){
                        Toast.makeText(SkinActivity.this, "Not enough coins!", Toast.LENGTH_SHORT).show();
                    }else{
                        money = money - 200;
                        SharedPreferences.Editor editor = coins.edit();
                        editor.putInt( "money", money);
                        editor.commit();
                        money2.setText("" + money);
                        skin3.clearColorFilter();

                        SharedPreferences.Editor editor1 = haveSkin3.edit();
                        editor1.putBoolean("haveSkin3", true);
                        editor1.commit();
                    }
                }
                if(have_skin_3 == true){
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putInt( "skin",R.drawable.skin3);
                    editor.commit();
                    Toast.makeText(SkinActivity.this,"Skin saved!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        skin4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                have_skin_4 = haveSkin4.getBoolean("haveSkin4", false);

                if(have_skin_4 == false){
                    if(money < 200){
                        Toast.makeText(SkinActivity.this, "Not enough coins!", Toast.LENGTH_SHORT).show();
                    }else{
                        money = money - 200;
                        SharedPreferences.Editor editor = coins.edit();
                        editor.putInt( "money", money);
                        editor.commit();
                        money2.setText("" + money);
                        skin4.clearColorFilter();

                        SharedPreferences.Editor editor1 = haveSkin4.edit();
                        editor1.putBoolean("haveSkin4", true);
                        editor1.commit();
                    }
                }
                if(have_skin_4 == true){
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putInt( "skin",R.drawable.skin4);
                    editor.commit();
                    Toast.makeText(SkinActivity.this,"Skin saved!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        skin5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                have_skin_5 = haveSkin5.getBoolean("haveSkin5", false);

                if(have_skin_5 == false){
                    if(money < 400){
                        Toast.makeText(SkinActivity.this, "Not enough coins!", Toast.LENGTH_SHORT).show();
                    }else{
                        money = money - 400;
                        SharedPreferences.Editor editor = coins.edit();
                        editor.putInt( "money", money);
                        editor.commit();
                        money2.setText("" + money);
                        skin5.clearColorFilter();

                        SharedPreferences.Editor editor1 = haveSkin5.edit();
                        editor1.putBoolean("haveSkin5", true);
                        editor1.commit();
                    }
                }
                if(have_skin_5 == true){
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putInt( "skin",R.drawable.skin5);
                    editor.commit();
                    Toast.makeText(SkinActivity.this,"Skin saved!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        skin6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                have_skin_6 = haveSkin6.getBoolean("haveSkin6", false);

                if(have_skin_6 == false){
                    if(money < 1000){
                        Toast.makeText(SkinActivity.this, "Not enough coins!", Toast.LENGTH_SHORT).show();
                    }else{
                        money = money - 1000;
                        SharedPreferences.Editor editor = coins.edit();
                        editor.putInt( "money", money);
                        editor.commit();
                        money2.setText("" + money);
                        skin6.clearColorFilter();

                        SharedPreferences.Editor editor1 = haveSkin6.edit();
                        editor1.putBoolean("haveSkin6", true);
                        editor1.commit();
                    }
                }
                if(have_skin_6 == true){
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putInt( "skin",R.drawable.skin6);
                    editor.commit();
                    Toast.makeText(SkinActivity.this,"Skin saved!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        skin7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                have_skin_7 = haveSkin7.getBoolean("haveSkin7", false);

                if(have_skin_7 == false){
                    if(money < 2000){
                        Toast.makeText(SkinActivity.this, "Not enough coins!", Toast.LENGTH_SHORT).show();
                    }else{
                        money = money - 2000;
                        SharedPreferences.Editor editor = coins.edit();
                        editor.putInt( "money", money);
                        editor.commit();
                        money2.setText("" + money);
                        skin7.clearColorFilter();

                        SharedPreferences.Editor editor1 = haveSkin7.edit();
                        editor1.putBoolean("haveSkin7", true);
                        editor1.commit();
                    }
                }
                if(have_skin_6 == true){
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putInt( "skin",R.drawable.skin7);
                    editor.commit();
                    Toast.makeText(SkinActivity.this,"Skin saved!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        skin8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                have_skin_8 = haveSkin8.getBoolean("haveSkin8", false);

                if(have_skin_8 == false){
                    if(money < 3000){
                        Toast.makeText(SkinActivity.this, "Not enough coins!", Toast.LENGTH_SHORT).show();
                    }else{
                        money = money - 3000;
                        SharedPreferences.Editor editor = coins.edit();
                        editor.putInt( "money", money);
                        editor.commit();
                        money2.setText("" + money);
                        skin8.clearColorFilter();

                        SharedPreferences.Editor editor1 = haveSkin8.edit();
                        editor1.putBoolean("haveSkin8", true);
                        editor1.commit();
                    }
                }
                if(have_skin_8 == true){
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putInt( "skin",R.drawable.skin8);
                    editor.commit();
                    Toast.makeText(SkinActivity.this,"Skin saved!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        sarny_skin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                have_sarny_skin = haveSarnySkin.getBoolean("haveSarnySkin", false);

                if(have_sarny_skin == false){
                    if(money < 1500){
                        Toast.makeText(SkinActivity.this, "Not enough coins!", Toast.LENGTH_SHORT).show();
                    }else{
                        money = money - 1500;
                        SharedPreferences.Editor editor = coins.edit();
                        editor.putInt( "money", money);
                        editor.commit();
                        money2.setText("" + money);
                        sarny_skin.clearColorFilter();

                        SharedPreferences.Editor editor1 = haveSarnySkin.edit();
                        editor1.putBoolean("haveSarnySkin", true);
                        editor1.commit();
                    }
                }
                if(have_sarny_skin == true){
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putInt( "skin",R.drawable.sarny_skin);
                    editor.commit();
                    Toast.makeText(SkinActivity.this,"Skin saved!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void openMainMenuActivity(){
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }
}