package com.example.vmirzadanie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vmirzadanie.databinding.ActivityMainBinding;
import com.example.vmirzadanie.databinding.ActivityMainMenuBinding;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.net.URL;


public class MainMenu extends AppCompatActivity {

    private ActivityMainMenuBinding binding;
    private FirebaseAuth firebaseAuth;

    private LinearLayout playbtn;
    private LinearLayout skinbtn;
    private LinearLayout storebtn;
    private LinearLayout logoutbtn;
    private LinearLayout mapsbtn;
    private LinearLayout lbbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();
        checkUser();


        playbtn = (LinearLayout) findViewById(R.id.playbtn);
        playbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainActivity();
            }
        });

        storebtn = findViewById(R.id.storebtn);
        storebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainMenu.this, "Not implemented yet :/", Toast.LENGTH_SHORT).show();
            }
        });

        logoutbtn = findViewById(R.id.logoutbtn);
        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                checkUser();
            }
        });

        skinbtn = findViewById(R.id.skinbtn);
        skinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSkinActivity();
            }
        });

        mapsbtn = findViewById(R.id.mapsbtn);
        mapsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenu.this, MapsActivity.class));
            }
        });

        lbbtn = findViewById(R.id.lbbtn);
        lbbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenu.this, Leaderboards.class));
            }
        });
    }

    private void checkUser() {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser == null){
            startActivity(new Intent(this, MainActivity.class));
        }else{
            String url = firebaseUser.getPhotoUrl().toString();
            Picasso.get().load(url).into(binding.profilepicsrc);
            String email = firebaseUser.getEmail();
            binding.lmsg.append(email);

        }
    }

    public void openMainActivity(){
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void openSkinActivity(){
        Intent intent = new Intent(this, SkinActivity.class);
        startActivity(intent);
    }

}
