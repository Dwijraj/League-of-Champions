package com.leagueofchampions.kiit.UI;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.leagueofchampions.kiit.Constants.Constants;
import com.leagueofchampions.kiit.Model.Batsman;
import com.leagueofchampions.kiit.Model.Bowler;
import com.leagueofchampions.kiit.Model.Score;
import com.leagueofchampions.kiit.R;

public class Details_activity extends AppCompatActivity {

    private TabLayout tabLayout;
    private TabLayout.Tab OnPitch,Scorecard;

    //Scorecard
    private LinearLayout Scorecard_Root;


    //OnPitch
    private LinearLayout OnPitch_Root;
    private ImageView First_Team,Second_Team,Batting_Team,Bowling_Team;
    private TextView Batsman1_Name,Batsman1_Runs,Batsman1_Balls,Batsman1_4,Batsman1_6,Batsman1_StrikeRate;
    private TextView Batsman2_Name,Batsman2_Runs,Batsman2_Balls,Batsman2_4,Batsman2_6,Batsman2_StrikeRate;
    private TextView Bowler_Name,Bowler_Runs,Bowler_Maiden,Bowler_Over,Bowler_wickets,Bowler_Economy;
    private ProgressBar Team1_On_Pitch_Progress_Bar,Team2_On_Pitch_Progress_Bar,Batting_Team_ProgressBar,Bowling_Team_ProgressBar;
    private TextView Score,MatchN;
    private DatabaseReference MatchNo,BattingTeam,BowlingTeam;
    private DatabaseReference Batsman1,Batsman2,Bowler,ScoreRef;
    public static String Team1="N/A";
    public static String Team2="N/A";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_details_activity);

        tabLayout=(TabLayout) findViewById(R.id.Tab_layout_details);
        BattingTeam=FirebaseDatabase.getInstance().getReference().child("Live").child("BattingTeam");
        BowlingTeam=FirebaseDatabase.getInstance().getReference().child("Live").child("BowlingTeam");
        MatchNo=FirebaseDatabase.getInstance().getReference().child("Live").child("MatchNo");
        Batsman1=FirebaseDatabase.getInstance().getReference().child("Live").child("Batsman").child("Batsman1");
        Batsman2=FirebaseDatabase.getInstance().getReference().child("Live").child("Batsman").child("Batsman2");
        Bowler=FirebaseDatabase.getInstance().getReference().child("Live").child("Bowler");
        ScoreRef=FirebaseDatabase.getInstance().getReference().child("Live").child("Score");
        OnPitch=tabLayout.newTab();
        Scorecard=tabLayout.newTab();

        OnPitch.setText("On Pitch");
        Scorecard.setText("Scorecard");

        tabLayout.addTab(OnPitch);
       // tabLayout.addTab(Scorecard);

        //Scorecard
        Scorecard_Root=(LinearLayout) findViewById(R.id.Scorecard);



        //On_Pitch_Attributes

        OnPitch_Root=(LinearLayout) findViewById(R.id.On_Pitch_ID);

        Batting_Team=(ImageView) findViewById(R.id.Batting_Team_Image);
        Bowling_Team=(ImageView) findViewById(R.id.Bowling_Team_Image);
        First_Team=(ImageView) findViewById(R.id.Team1_On_Pitch);
        Second_Team=(ImageView) findViewById(R.id.Team2_On_Pitch);

        Batsman1_Name=(TextView) findViewById(R.id.Batsman1_Name);
        Batsman1_Runs=(TextView) findViewById(R.id.Batsman1_Runs);
        Batsman1_StrikeRate=(TextView) findViewById(R.id.Batsman1_StrikeRate);
        Batsman1_Balls=(TextView) findViewById(R.id.Batsman1_Balls);
        Batsman1_4=(TextView) findViewById(R.id.Batsman1_4s);
        Batsman1_6=(TextView) findViewById(R.id.Batsman1_6s);

        Batsman2_Name=(TextView) findViewById(R.id.Batsman2_Name);
        Batsman2_Runs=(TextView) findViewById(R.id.Batsman2_Runs);
        Batsman2_StrikeRate=(TextView) findViewById(R.id.Batsman2_StrikeRate);
        Batsman2_Balls=(TextView) findViewById(R.id.Batsman2_Balls);
        Batsman2_4=(TextView) findViewById(R.id.Batsman2_4s);
        Batsman2_6=(TextView) findViewById(R.id.Batsman2_6s);

        Bowler_Name=(TextView) findViewById(R.id.Bowler_Name);
        Bowler_Over=(TextView) findViewById(R.id.Bowler_Overs);
        Bowler_Maiden=(TextView) findViewById(R.id.Bowler_Maiden);
        Bowler_Runs=(TextView) findViewById(R.id.Bowler_Runs);
        Bowler_wickets=(TextView) findViewById(R.id.Bowler_Wickets);
        Bowler_Economy=(TextView) findViewById(R.id.Bowler_Economy);

        Score=(TextView) findViewById(R.id.Score_On_Pitch);
        MatchN=(TextView) findViewById(R.id.MatchNo);

        Team1_On_Pitch_Progress_Bar=(ProgressBar) findViewById(R.id.Team1_On_Pitch_Progress_Bar);
        Team2_On_Pitch_Progress_Bar=(ProgressBar) findViewById(R.id.Team2_On_Pitch_Progress_Bar);
        Batting_Team_ProgressBar=(ProgressBar) findViewById(R.id.Batting_Team_Progress_Bar);
        Bowling_Team_ProgressBar=(ProgressBar) findViewById(R.id.Bowling_Team_Progress_Bar);

        First_Team.setImageResource(Constants.NAMES_FLAGS.get(Team1));
        Second_Team.setImageResource(Constants.NAMES_FLAGS.get(Team2));

        Team1_On_Pitch_Progress_Bar.setVisibility(View.INVISIBLE);
        Team2_On_Pitch_Progress_Bar.setVisibility(View.INVISIBLE);



        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if(tab.equals(OnPitch))
                {
                    Scorecard_Root.setVisibility(View.INVISIBLE);
                    OnPitch_Root.setVisibility(View.VISIBLE);
                }
                else if(tab.equals(Scorecard))
                {
                    Scorecard_Root.setVisibility(View.VISIBLE);
                    OnPitch_Root.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        MatchNo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                MatchN.setText("Match number : "+dataSnapshot.getValue(String.class));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        BattingTeam.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                try {
                    Batting_Team_ProgressBar.setVisibility(View.INVISIBLE);
                    Batting_Team.setImageResource(Constants.NAMES_FLAGS.get(dataSnapshot.getValue(String.class)));
                }
                catch (Exception e)
                {}
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        BowlingTeam.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                try {
                    Bowling_Team_ProgressBar.setVisibility(View.INVISIBLE);
                    Bowling_Team.setImageResource(Constants.NAMES_FLAGS.get(dataSnapshot.getValue(String.class)));
                }
                catch (Exception e)
                {}
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Batsman1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                try {
                    Batsman batsman = dataSnapshot.getValue(Batsman.class);
                    Batsman1_Name.setText(batsman.getName());
                    Batsman1_Balls.setText(batsman.getBalls());
                    Batsman1_6.setText(batsman.getSix());
                    Batsman1_4.setText(batsman.getFour());
                    Batsman1_StrikeRate.setText(batsman.getStrikeRate());
                    Batsman1_Runs.setText(batsman.getRuns());
                }
                catch (Exception e)
                {
                    Toast.makeText(Details_activity.this,"Error occurred try latter",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        Batsman2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                try {
                    Batsman batsman = dataSnapshot.getValue(Batsman.class);
                    Batsman2_Name.setText(batsman.getName());
                    Batsman2_Balls.setText(batsman.getBalls());
                    Batsman2_6.setText(batsman.getSix());
                    Batsman2_4.setText(batsman.getFour());
                    Batsman2_StrikeRate.setText(batsman.getStrikeRate());
                    Batsman2_Runs.setText(batsman.getRuns());
                }
                catch (Exception e)
                {
                    Toast.makeText(Details_activity.this,"Error occurred try latter",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Bowler.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                try {
                    com.leagueofchampions.kiit.Model.Bowler bowler = dataSnapshot.getValue(com.leagueofchampions.kiit.Model.Bowler.class);
                    Bowler_Name.setText(bowler.getName());
                    Bowler_Over.setText(bowler.getOvers());
                    Bowler_Runs.setText(bowler.getRuns());
                    Bowler_Maiden.setText(bowler.getMaidens());
                    Bowler_Economy.setText(bowler.getEconomy());
                    Bowler_wickets.setText(bowler.getWickets());
                }
                catch (Exception e)
                {
                    Toast.makeText(Details_activity.this,"Error occurred try latter",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ScoreRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                try {
                    com.leagueofchampions.kiit.Model.Score score = dataSnapshot.getValue(com.leagueofchampions.kiit.Model.Score.class);
                    Score.setText(score.getRuns() + "/" + score.getWickets() + "(" + score.getOvers() + ")");
                }
                catch (Exception e)
                {
                    Toast.makeText(Details_activity.this,"Error occurred try latter ",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
