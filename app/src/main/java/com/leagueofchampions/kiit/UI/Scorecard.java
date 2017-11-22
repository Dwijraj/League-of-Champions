package com.leagueofchampions.kiit.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.leagueofchampions.kiit.R;
import com.romainpiel.shimmer.ShimmerTextView;

public class Scorecard extends AppCompatActivity {

    private TextView  Team1_Name,Team2_Name;
    private ImageView Team1_Icon,Team2_Icon;
    private TextView  Team1_Total_Score,Team2_Total_Score;
    private TextView  Team1_Total_Extras,Team2_Total_Extras;
    private ShimmerTextView ManOfTheMatch;
    private RecyclerView Team1_Batting,Team1_Bowling,Team2_Batting,Team2_Bowling;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_scorecard);

        ManOfTheMatch=(ShimmerTextView) findViewById(R.id.ManOfTheMatch);

        Team1_Name=(TextView) findViewById(R.id.Team1_Name_Scorecard);
        Team1_Icon=(ImageView) findViewById(R.id.Team1_Image_Scorecard);
        Team1_Total_Score=(TextView) findViewById(R.id.Team1_Total_Score);
        Team1_Total_Extras=(TextView) findViewById(R.id.Team1_Total_Extras_Earned);
        Team1_Batting=(RecyclerView) findViewById(R.id.Team1_Batting_Scorecard);
        Team1_Bowling=(RecyclerView) findViewById(R.id.Team1_Bowling_Scorecard);

        Team2_Name=(TextView) findViewById(R.id.Team2_Name_Scorecard);
        Team2_Icon=(ImageView) findViewById(R.id.Team2_Image_Scorecard);
        Team2_Total_Score=(TextView) findViewById(R.id.Team2_Total_Score);
        Team2_Total_Extras=(TextView) findViewById(R.id.Team2_Total_Extras_Earned);
        Team2_Batting=(RecyclerView) findViewById(R.id.Team2_Batting_Scorecard);
        Team2_Bowling=(RecyclerView) findViewById(R.id.Team2_Bowling_Scorecard);




    }
}
