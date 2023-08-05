package com.example.vmirzadanie;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.Locale;
import java.util.Random;


public class GameActivity extends AppCompatActivity {
    int sc;
    int sc2;
    int tempMoney;

    private FirebaseAuth firebaseAuth;

    private static final long START_TIME = 10000;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private TextView timer;
    private ImageButton circle;
    private TextView finale;
    private Button reset;
    private long mTimeLeftInMillis = START_TIME;
    private RelativeLayout rlayout;
    private TextView score;
    private ImageButton backBtn;
    private TextView coin;
    private Button submit;
    String scoreSD;
    String goldGainedSD;
    String emailSD;
    String nameSD;
    String photoUrlSD;

    private DatabaseReference mDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        firebaseAuth = FirebaseAuth.getInstance();
        checkUser();

        mDatabase = FirebaseDatabase.getInstance().getReference();

        rlayout = findViewById(R.id.mainlayout);
        score = findViewById(R.id.scoreId);
        timer = findViewById(R.id.timeId);
        finale = findViewById(R.id.finalId);
        reset = findViewById(R.id.resetId);
        circle = findViewById(R.id.circleId);
        backBtn = findViewById(R.id.backBtn);
        coin = findViewById(R.id.textView7);
        submit = findViewById(R.id.submitId);

        finale.setVisibility(View.INVISIBLE);
        reset.setVisibility(View.INVISIBLE);
        submit.setVisibility(View.INVISIBLE);

        SharedPreferences coins = getSharedPreferences("money", 0);
        int money = coins.getInt("money", 0);
        coin.setText("" + money);

        circle.setBackgroundResource(R.drawable.skin1);
        SharedPreferences sp = getSharedPreferences("skins", 0);
        int skin = sp.getInt("skin", 0);

        circle.setBackgroundResource(skin);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        int max = height - circle.getLayoutParams().height;
        int min = score.getLayoutParams().height + timer.getLayoutParams().height + (circle.getLayoutParams().height / 2);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainMenuActivity();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeNewUser();
            }
        });

        circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (circle.getWidth() > 50){
                    makeSmall();
                }

                if(!mTimerRunning){
                    startTimer();
                }

                if(mTimerRunning){
                    rlayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            sc = sc - 1;
                            sc2 = sc2 + 1;
                            score.setText("Score: " + sc);
                            Random rand = new Random();

                            circle.setX(rand.nextInt(width - circle.getWidth() ));
                            circle.setY(rand.nextInt((max - min) + 1) + min);

                        }
                    });
                }

                Random rand = new Random();
                circle.setX(rand.nextInt(width - circle.getWidth() ));
                circle.setY(rand.nextInt((max - min) + 1) + min);
                sc = sc + 1;
                sc2 = sc2 + 1;

                score.setText("Score: " + sc);
            }
        });

        updateCountdownText();

    }

    private void checkUser() {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser == null){
            startActivity(new Intent(this, MainActivity.class));
        }else{
            emailSD = firebaseUser.getEmail();
            nameSD = firebaseUser.getDisplayName();
            photoUrlSD = firebaseUser.getPhotoUrl().toString();
        }
    }

    private void startTimer(){
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long l) {
                mTimeLeftInMillis = l;
                updateCountdownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                finale.setText("Your final score is: " + sc);

                SharedPreferences coins = getSharedPreferences("money", 0);

                if(sc >= 1 && sc < 5){
                    tempMoney = 0;
                }

                if(sc >= 5 && sc < 10){
                    tempMoney = 5;
                }

                if(sc >= 10 && sc < 15){
                    tempMoney = 10;
                }

                if(sc >= 15 && sc < 20){
                    tempMoney = 15;
                }

                if(sc >= 20){
                    tempMoney = 20;
                }

                int money = coins.getInt("money", 0);
                money = money + tempMoney;

                SharedPreferences.Editor editor = coins.edit();
                editor.putInt( "money", money);
                editor.commit();

                coin.setText("" + money);

                scoreSD = Integer.toString(sc);
                goldGainedSD = Integer.toString(tempMoney);

                score.setVisibility(View.INVISIBLE);
                circle.setVisibility(View.INVISIBLE);
                timer.setVisibility(View.INVISIBLE);
                finale.setVisibility(View.VISIBLE);
                reset.setVisibility(View.VISIBLE);
                submit.setVisibility(View.VISIBLE);
                android.view.ViewGroup.LayoutParams layoutParams = circle.getLayoutParams();
                layoutParams.width = circle.getWidth() + sc2*10;
                layoutParams.height = circle.getHeight() + sc2*10;
                circle.setLayoutParams(layoutParams);
                Toast.makeText(GameActivity.this, "You earned: " + tempMoney + " coins this round!", Toast.LENGTH_SHORT).show();


            }
        }.start();

        mTimerRunning = true;

    }

    private void updateCountdownText(){
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
        String timerLeftFormatted = String.format(Locale.getDefault(),"Time Left: %02d:%02d",minutes, seconds);
        timer.setText(timerLeftFormatted);
    }

    private void makeSmall(){
        android.view.ViewGroup.LayoutParams layoutParams = circle.getLayoutParams();
        layoutParams.width = circle.getWidth() - 5;
        layoutParams.height = circle.getHeight() - 5;
        circle.setLayoutParams(layoutParams);
    }

    public void openMainMenuActivity(){
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }

    public void reset(){
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void sendData(View view){
        writeNewUser();
    }

    public void writeNewUser() {
        User user = new User(scoreSD, emailSD, goldGainedSD, nameSD, photoUrlSD);

        mDatabase.child("users").child(user.getNameSD()).setValue(user);
    }

}