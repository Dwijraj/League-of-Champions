package com.leagueofchampions.kiit.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.leagueofchampions.kiit.Adapter.BatsmanRecyclerViewAdapter;
import com.leagueofchampions.kiit.Adapter.BowlerRecyclerViewAdapter;
import com.leagueofchampions.kiit.Constants.Constants;
import com.leagueofchampions.kiit.Model.Batsman;
import com.leagueofchampions.kiit.Model.Bowler;
import com.leagueofchampions.kiit.Model.Score;
import com.leagueofchampions.kiit.R;
import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

import java.util.ArrayList;

public class Scorecard extends AppCompatActivity {

    private TextView  Team1_Name,Team2_Name;
    private ImageView Team1_Icon,Team2_Icon;
    private TextView  Team1_Total_Score,Team2_Total_Score;
    private TextView  Team1_Total_Extras,Team2_Total_Extras;
    private ShimmerTextView ManOfTheMatch;
    private RecyclerView Team1_Batting,Team1_Bowling,Team2_Batting,Team2_Bowling;
    private BatsmanRecyclerViewAdapter Team1BattingAdapter,Team2BattingAdapter;
    private BowlerRecyclerViewAdapter  Team1BowlerAdapter,Team2BowlerAdapter;
    private ArrayList<Batsman> Team1Batsmen,Team2Batsmen;
    private ArrayList<Bowler>  Team1Bowlers,Team2Bowlers;
    private DatabaseReference Team1NameRef,Team2NameRef;
    private DatabaseReference Team1ScoreRef,Team2ScoreRef;
    private DatabaseReference ManofTheMatchRef;
    private DatabaseReference Team1BattingRef,Team2BattingRef,Team1BowlingRef,Team2BowlingRef;
    private ProgressBar Team1ImageProgressBar,Team2ImageProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_scorecard);


        Bundle bundle=getIntent().getExtras();
        String MatchNo=bundle.getString("MatchNo","NO");
        if (MatchNo.equals("NO"))
        {
            Toast.makeText(Scorecard.this,"Something went wrong",Toast.LENGTH_SHORT).show();
            finish();
        }

        DatabaseReference Main_Root= FirebaseDatabase.getInstance().getReference().child("Scorecard").child(MatchNo);
        Team1NameRef=Main_Root.child("Team1");
        Team1ScoreRef=Main_Root.child("Team1Score");
        Team1BattingRef=Main_Root.child("Team1Batting");
        Team1BowlingRef=Main_Root.child("Team1Bowling");

        Team2NameRef=Main_Root.child("Team2");
        Team2ScoreRef=Main_Root.child("Team2Score");
        Team2BattingRef=Main_Root.child("Team2Batting");
        Team2BowlingRef=Main_Root.child("Team2Bowling");

        ManofTheMatchRef=Main_Root.child("ManOfTheMatch");




        ManOfTheMatch=(ShimmerTextView) findViewById(R.id.ManOfTheMatch);

        Shimmer s=new Shimmer();
        s.setDuration(3000);
        s.start(ManOfTheMatch);


        Team1_Name=(TextView) findViewById(R.id.Team1_Name_Scorecard);
        Team1_Icon=(ImageView) findViewById(R.id.Team1_Image_Scorecard);
        Team1_Total_Score=(TextView) findViewById(R.id.Team1_Total_Score);
        Team1_Total_Extras=(TextView) findViewById(R.id.Team1_Total_Extras_Earned);
        Team1_Batting=(RecyclerView) findViewById(R.id.Team1_Batting_Scorecard);
        Team1_Batting.setLayoutManager(new LinearLayoutManager(this));
        Team1_Bowling=(RecyclerView) findViewById(R.id.Team1_Bowling_Scorecard);
        Team1_Bowling.setLayoutManager(new LinearLayoutManager(this));

        Team2_Name=(TextView) findViewById(R.id.Team2_Name_Scorecard);
        Team2_Icon=(ImageView) findViewById(R.id.Team2_Image_Scorecard);
        Team2_Total_Score=(TextView) findViewById(R.id.Team2_Total_Score);
        Team2_Total_Extras=(TextView) findViewById(R.id.Team2_Total_Extras_Earned);
        Team2_Batting=(RecyclerView) findViewById(R.id.Team2_Batting_Scorecard);
        Team2_Batting.setLayoutManager(new LinearLayoutManager(this));
        Team2_Bowling=(RecyclerView) findViewById(R.id.Team2_Bowling_Scorecard);
        Team2_Bowling.setLayoutManager(new LinearLayoutManager(this));


        Team1ImageProgressBar=(ProgressBar) findViewById(R.id.Team1_Image_ProgressBar);
        Team2ImageProgressBar=(ProgressBar) findViewById(R.id.Team2_Image_ProgressBar);

        Team1Batsmen=new ArrayList<>();
        Team2Batsmen=new ArrayList<>();
        Team1Bowlers=new ArrayList<>();
        Team2Bowlers=new ArrayList<>();

        Team1NameRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Team1_Name.setText(dataSnapshot.getValue(String.class));
                Team1_Icon.setImageResource(Constants.NAMES_FLAGS.get(dataSnapshot.getValue(String.class)));
                Team1ImageProgressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Team2NameRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Team2_Name.setText(dataSnapshot.getValue(String.class));
                Team2_Icon.setImageResource(Constants.NAMES_FLAGS.get(dataSnapshot.getValue(String.class)));
                Team2ImageProgressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Team1ScoreRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                try {
                    Score score = dataSnapshot.getValue(Score.class);
                    Team1_Total_Score.setText(score.getRuns() + "/" + score.getWickets() + "(" + score.getOvers() + ")");
                    Team1_Total_Extras.setText(score.getExtras());
                }
                catch (Exception e)
                {}
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Team2ScoreRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                try {
                    Score score = dataSnapshot.getValue(Score.class);
                    Team2_Total_Score.setText(score.getRuns() + "/" + score.getWickets() + "(" + score.getOvers() + ")");
                    Team2_Total_Extras.setText(score.getExtras());
                }
                catch (Exception e)
                {}
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        ManofTheMatchRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                try {
                    if(dataSnapshot.getValue(String.class)!=null)
                        ManOfTheMatch.setText("Man of the Match :-"+dataSnapshot.getValue(String.class));
                    else
                        ManOfTheMatch.setText("Man of the Match :-");

                }
                catch (Exception e)
                {
                    Toast.makeText(Scorecard.this,"Match in progress",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Team1BattingRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Team1Batsmen.clear();
                for (DataSnapshot d : dataSnapshot.getChildren())
                {

                        Batsman batsman = d.getValue(Batsman.class);
                        Team1Batsmen.add(batsman);
                }

                if(Team1Batsmen.size()!=0) {
                    Team1BattingAdapter = new BatsmanRecyclerViewAdapter(Team1Batsmen, Scorecard.this);
                    Team1_Batting.setAdapter(Team1BattingAdapter);
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Team2BattingRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Team2Batsmen.clear();
                for(DataSnapshot d :dataSnapshot.getChildren())
                {

                    Batsman batsman=d.getValue(Batsman.class);
                    Team2Batsmen.add(batsman);
                }

                if(Team2Batsmen.size()!=0) {
                    Team2BattingAdapter = new BatsmanRecyclerViewAdapter(Team2Batsmen, Scorecard.this);
                    Team2_Batting.setAdapter(Team2BattingAdapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Team1BowlingRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Team1Bowlers.clear();
                for(DataSnapshot d :dataSnapshot.getChildren())
                {

                    Bowler bowler=d.getValue(Bowler.class);
                    bowler.setName(d.getKey());
                    Team1Bowlers.add(bowler);
                }

                if(Team1Bowlers.size()!=0) {
                    Team1BowlerAdapter = new BowlerRecyclerViewAdapter(Team1Bowlers, Scorecard.this);
                    Team1_Bowling.setAdapter(Team1BowlerAdapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Team2BowlingRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Team2Bowlers.clear();
                for(DataSnapshot d :dataSnapshot.getChildren())
                {

                    Bowler bowler=d.getValue(Bowler.class);
                    bowler.setName(d.getKey());
                    Team2Bowlers.add(bowler);
                }
                if(Team2Bowlers.size()!=0) {
                    Team2BowlerAdapter = new BowlerRecyclerViewAdapter(Team2Bowlers, Scorecard.this);
                    Team2_Bowling.setAdapter(Team2BowlerAdapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }
}
