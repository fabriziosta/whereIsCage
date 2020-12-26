package com.supinfo.whereiscage.Activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.supinfo.whereiscage.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class GameActivity extends AppCompatActivity {
    //Image solutions for default resolutions. These results must be converted if resolution change.
    private int[] img1 = new int[]{1400, 1450, 620, 680};
    private int[] img2 = new int[]{1170, 1220, 590, 660};
    private int[] img3 = new int[]{820, 880, 430, 500};
    private int[] img4 = new int[]{600, 640, 630, 680};
    private int[] img5 = new int[]{1130, 1170, 300, 360};

    //Many private declarations.
    private ImageView imgView;
    private Chronometer timer;
    private CountDownTimer countDownChronoMode;
    private CountDownTimer countDownClicks;
    private int startChrono1_timer = 120000;
    private TextView textGameMode;
    private TextView textTimer;
    private List<String> myList = new ArrayList<>();
    private String gameMode;
    private int[] viewCoords = new int[2]; //this will contain image coordinates.
    private Integer clickWidth;
    private Integer clickHeight;
    private int foundCages = 0; //this counter will be used to count how many cage the user foundo in Chrono mode.
    // ---------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //Clear ArrayList and populate it.
        for (int x = 1; x < 6; x++) {myList.add("img" + x);}

        //Get objs by id, ONLY after the creation of the activity, or it will crash.
        textGameMode = (TextView) findViewById(R.id.textGameMode);
        textTimer = (TextView) findViewById(R.id.textViewTimer);
        imgView = (ImageView) findViewById(R.id.imageView);
        timer = (Chronometer) findViewById(R.id.timer);

        //Getting what game mode we need to load.
        gameMode = getIntent().getStringExtra("game");
    }
    // ---------------------------------------------------------------------------------------------
    @Override
    protected void onStart() {
        super.onStart();

        switch (gameMode){
            case "easy":
                imgView.setImageResource(getResources().getIdentifier("img2", "mipmap", getPackageName()));
                imgView.setTag("img2");
                textGameMode.setText(R.string.normal_game1);
                timer.start(); break;
            case "medium":
                imgView.setImageResource(getResources().getIdentifier("img5", "mipmap", getPackageName()));
                imgView.setTag("img5");
                textGameMode.setText(R.string.normal_game2);
                timer.start(); break;
            case "hard":
                imgView.setImageResource(getResources().getIdentifier("img1", "mipmap", getPackageName()));
                imgView.setTag("img1");
                textGameMode.setText(R.string.normal_game3);
                timer.start(); break;
            case "chrono1":
                getRandomImage();
                timer.setVisibility(View.INVISIBLE); //Hide Chronometer because this time I need to create a CountDownTimer!
                createCustomCountDownTimer(startChrono1_timer);
                textGameMode.setText(R.string.chrono1); break;
            default: //"chrono2"
                getRandomImage();
                timer.setVisibility(View.INVISIBLE); //Hide Chronometer because this time I need to create a CountDownTimer!
                createCustomCountDownTimer(300000);
                textGameMode.setText(R.string.chrono2);
        }
        manage_clicks();
    }
    // ---------------------------------------------------------------------------------------------
    @Override
    protected void onStop() {
        super.onStop();
        if(gameMode.equals("chrono1") | gameMode.equals("chrono2")) countDownChronoMode.cancel();
        finish();
    }
    // ---------------------------------------------------------------------------------------------
    public void manage_clicks(){
        imgView.getLocationOnScreen(viewCoords);
        imgView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP && imgView.isClickable() && countDownClicks == null) {
                    //Getting user click coords to check if cage is found.
                    clickWidth = (int) event.getX() - viewCoords[0]; // viewCoords[0] == width
                    clickHeight = (int) event.getY() - viewCoords[1]; // viewCoords[1] == height
                    imgView.setClickable(false);
                    checkCage();

                    countDownClicks = new CountDownTimer(2000, 2000) {
                        @Override
                        public void onTick(long millisUntilFinished) {}
                        @Override
                        public void onFinish() {
                            imgView.setClickable(true);
                            Toast.makeText(GameActivity.this, "You can click again now!", Toast.LENGTH_SHORT).show();
                            countDownClicks = null;
                        }
                    }.start();
                }
                return false;
            } /*end onTouch()*/
        });
    }
    // ---------------------------------------------------------------------------------------------
    public void checkCage() {
        String winningScore;
        int imgWidth = imgView.getWidth();
        int imgHeight = imgView.getHeight();
        boolean win = false;

        if( String.valueOf(imgView.getTag()).equals("img1")
                && clickWidth > (img1[0] * imgWidth / 1947) && clickWidth < (img1[1] * imgWidth / 1947)
                && clickHeight > (img1[2] * imgHeight / 1238) && clickHeight < (img1[3] * imgHeight / 1238) ){
            win = true;
        }
        else if( String.valueOf(imgView.getTag()).equals("img2")
                && clickWidth > (img2[0] * imgWidth / 1500) && clickWidth < (img2[1] * imgWidth / 1500)
                && clickHeight > (img2[2] * imgHeight / 1000) && clickHeight < (img2[3] * imgHeight / 1000) ){
            win = true;
        }
        else if( String.valueOf(imgView.getTag()).equals("img3")
                && clickWidth > (img3[0] * imgWidth / 1200) && clickWidth < (img3[1] * imgWidth / 1200)
                && clickHeight > (img3[2] * imgHeight / 800) && clickHeight < (img3[3] * imgHeight / 800) ){
            win = true;
        }
        else if( String.valueOf(imgView.getTag()).equals("img4")
                && clickWidth > (img4[0] * imgWidth / 1752) && clickWidth < (img4[1] * imgWidth / 1752)
                && clickHeight > (img4[2] * imgHeight / 1168) && clickHeight < (img4[3] * imgHeight / 1168) ){
            win = true;
        }
        else if( String.valueOf(imgView.getTag()).equals("img5")
                && clickWidth > (img5[0] * imgWidth / 2378) && clickWidth < (img5[1] * imgWidth / 2378)
                && clickHeight > (img5[2] * imgHeight / 1391) && clickHeight < (img5[3] * imgHeight / 1391) ){
            win = true;
        }

        if (win && (gameMode.equals("easy") | gameMode.equals("medium") | gameMode.equals("hard"))){
            winningScore = timer.getText().toString();
            startActivity(new Intent(GameActivity.this, AddRecordHallOfFameActivity.class).putExtra("Score", winningScore).putExtra("game",gameMode));
            finish();
        }else if(win && gameMode.equals("chrono1")){
            foundCages += 1;
            getRandomImage();
            startChrono1_timer -= 20000;
            countDownChronoMode.cancel();
            createCustomCountDownTimer(startChrono1_timer);
        }else if(win && gameMode.equals("chrono2")){ //if gamemode == "chrono2"
            foundCages += 1;
            getRandomImage();
        }
    } /*end checkCage()*/
    // ---------------------------------------------------------------------------------------------
    private void getRandomImage() {
        //Load random image in "imageView" for starting the game.
        String lastImage = myList.get(0);
        Collections.shuffle(myList);
        if(lastImage.equals(myList.get(0))) getRandomImage();
        imgView.setImageResource(getResources().getIdentifier(myList.get(0), "mipmap", getPackageName()));
        imgView.setTag(myList.get(0));
    }
    // ---------------------------------------------------------------------------------------------
    private void createCustomCountDownTimer(int TimeToElapse){
        countDownChronoMode = new CountDownTimer(TimeToElapse, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String min = String.format(Locale.ITALY, "%02d", millisUntilFinished/60000);
                int sec = (int)( (millisUntilFinished%60000)/1000);
                textTimer.setText("Countdown: " + min + ":" + String.format(Locale.ITALY, "%02d", sec) + " - Score: " + String.valueOf(foundCages));
            }
            @Override
            public void onFinish() {
                startActivity(new Intent(GameActivity.this, AddRecordHallOfFameActivity.class).putExtra("Score", String.valueOf(foundCages)).putExtra("game", gameMode));
                finish();
            }
        }.start();
    }
} //end activity
