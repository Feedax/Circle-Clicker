package com.example.vmirzadanie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Leaderboards extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboards);
        mRecyclerView = (RecyclerView) findViewById(R.id.recylcerview_users);


        new FirebaseDatabaseHelper().readUsers(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<User> users, List<String> keys) {
                new RecyclerView_Config().setConfig(mRecyclerView, Leaderboards.this, users, keys);
                Collections.sort(users, new Comparator<User>() {
                    @Override
                    public int compare(User user, User t1) {
                        return user.getScoreSD().compareToIgnoreCase(t1.getScoreSD());
                    }
                });

                Collections.reverse(users);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });
    }
}