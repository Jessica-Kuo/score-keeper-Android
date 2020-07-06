package com.example.scorekeeper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int mScore1;
    private int mScore2;
    private int mYellowCard1;
    private int mYellowCard2;
    private int mRedCard1;
    private int mRedCard2;
    private int mBlackCard1;
    private int mBlackCard2;
    private TextView mScoreText1;
    private TextView mScoreText2;
    private Button team1yCard;
    private Button team2yCard;
    private Button team1rCard;
    private Button team2rCard;
    private Button team1bCard;
    private Button team2bCard;
    private SharedPreferences mPreferences;

    // Key for current count
    private final String TEAM_1_KEY = "team_1_score";
    // Key for current color
    private final String TEAM_2_KEY = "team_2_score";
    //Keys for red, yellow, black card
    private final String TEAM_1_Y = "team_1_yellow";
    private final String TEAM_2_Y = "team_2_yellow";
    private final String TEAM_1_R = "team_1_red";
    private final String TEAM_2_R = "team_2_red";
    private final String TEAM_1_B = "team_1_black";
    private final String TEAM_2_B = "team_2_black";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialization
        mScoreText1 = findViewById(R.id.team1Score);
        mScoreText2 = findViewById(R.id.team2Score);
        team1yCard = findViewById(R.id.team1YellowCard);
        team2yCard = findViewById(R.id.team2YellowCard);
        team1rCard = findViewById(R.id.team1RedCard);
        team2rCard = findViewById(R.id.team2RedCard);
        team1bCard = findViewById(R.id.team1BlackCard);
        team2bCard = findViewById(R.id.team2BlackCard);
        String sharedPrefFile = "come.example.android.scorekeeper";
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        //grabbing values from last set
        mScore1 = mPreferences.getInt(TEAM_1_KEY, 0);
        mScore2 = mPreferences.getInt(TEAM_2_KEY, 0);
        mYellowCard1 = mPreferences.getInt(TEAM_1_Y, 0);
        mYellowCard2 = mPreferences.getInt(TEAM_2_Y, 0);
        mRedCard1 = mPreferences.getInt(TEAM_1_R, 0);
        mRedCard2 = mPreferences.getInt(TEAM_2_R, 0);
        mBlackCard1 = mPreferences.getInt(TEAM_1_B, 0);
        mBlackCard2 = mPreferences.getInt(TEAM_2_B, 0);

        //setting those values to respective fields when the activity is loaded
        mScoreText1.setText(String.format("%s", mScore1));
        mScoreText2.setText(String.format("%s", mScore2));
        team1yCard.setText(String.format("%s", mYellowCard1));
        team2yCard.setText(String.format("%s", mYellowCard2));
        team1rCard.setText(String.format("%s", mRedCard1));
        team2rCard.setText(String.format("%s", mRedCard2));
        team1bCard.setText(String.format("%s", mBlackCard1));
        team2bCard.setText(String.format("%s", mBlackCard2));

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.night_mode) {
            // Get the night mode state of the app.
            int nightMode = AppCompatDelegate.getDefaultNightMode();
            //Set the theme mode for the restarted activity
            if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode
                        (AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                AppCompatDelegate.setDefaultNightMode
                        (AppCompatDelegate.MODE_NIGHT_YES);
            }
// Recreate the activity for the theme change to take effect.
            recreate();
        }
        if(item.getItemId() == R.id.reset){
            //reset scores
            mScore1=0; mScore2=0;
            mYellowCard1=0; mYellowCard2=0;
            mRedCard1=0; mRedCard2=0;
            mBlackCard1=0; mBlackCard2=0;
            mScoreText1.setText(String.format("%s", mScore1));
            mScoreText2.setText(String.format("%s", mScore2));
            team1yCard.setText(String.format("%s", mYellowCard1));
            team2yCard.setText(String.format("%s", mYellowCard2));
            team1rCard.setText(String.format("%s", mRedCard1));
            team2rCard.setText(String.format("%s", mRedCard2));
            team1bCard.setText(String.format("%s", mBlackCard1));
            team2bCard.setText(String.format("%s", mBlackCard2));

            //clear preferences
            SharedPreferences.Editor preferencesEditor = mPreferences.edit();
            preferencesEditor.clear();
            preferencesEditor.apply();
        }
            return true;
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        // Change the label of the menu based on the state of the app.
        int nightMode = AppCompatDelegate.getDefaultNightMode();
        if(nightMode == AppCompatDelegate.MODE_NIGHT_YES){
            menu.findItem(R.id.night_mode).setTitle(R.string.day_mode);
        } else{
            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode);
        }
        return true;
    }

    public void decreaseScore(View view) {
        int viewID = view.getId();
        switch(viewID){
            case R.id.minusTeam1:
                mScore1--;
                mScoreText1.setText(String.valueOf(mScore1));
                break;
            case R.id.minusTeam2:
                mScore2--;
                mScoreText2.setText(String.valueOf(mScore2));
                break;
        }
    }

    public void increaseScore(View view) {
        int viewID = view.getId();
        switch(viewID){
            case R.id.addTeam1:
                mScore1++;
                mScoreText1.setText(String.valueOf(mScore1));
                break;
            case R.id.addTeam2:
                mScore2++;
                mScoreText2.setText(String.valueOf(mScore2));
                break;
        }
    }

    public void yellowCard(View view) {
        int viewID = view.getId();
        switch(viewID){
            case R.id.team1YellowCard:
                mYellowCard1++;
                team1yCard.setText(String.valueOf(mYellowCard1));
                break;
            case R.id.team2YellowCard:
                mYellowCard2++;
                team2yCard.setText(String.valueOf(mYellowCard2));
                break;
        }
    }

    public void redCard(View view){
        int viewID = view.getId();
        switch(viewID){
            case R.id.team1RedCard:
                mRedCard1++;
                team1rCard.setText(String.valueOf(mRedCard1));
                break;
            case R.id.team2RedCard:
                mRedCard2++;
                team2rCard.setText(String.valueOf(mRedCard2));
                break;
        }
    }

    public void blackCard(View view){
        int viewID = view.getId();
        switch(viewID){
            case R.id.team1BlackCard:
                mBlackCard1++;
                team1bCard.setText(String.valueOf(mBlackCard1));
                break;
            case R.id.team2BlackCard:
                mBlackCard2++;
                team2bCard.setText(String.valueOf(mBlackCard2));
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putInt(TEAM_1_KEY, mScore1);
        preferencesEditor.putInt(TEAM_2_KEY, mScore2);
        preferencesEditor.putInt(TEAM_1_Y, mYellowCard1);
        preferencesEditor.putInt(TEAM_2_Y, mYellowCard2);
        preferencesEditor.putInt(TEAM_1_R, mRedCard1);
        preferencesEditor.putInt(TEAM_2_R, mRedCard2);
        preferencesEditor.putInt(TEAM_1_B, mBlackCard1);
        preferencesEditor.putInt(TEAM_2_B, mBlackCard2);
        preferencesEditor.apply();
    }
}