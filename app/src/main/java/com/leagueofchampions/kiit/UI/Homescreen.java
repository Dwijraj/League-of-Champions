package com.leagueofchampions.kiit.UI;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.leagueofchampions.kiit.Adapter.PlayedFixtureRecyclerViewAdapter;
import com.leagueofchampions.kiit.Adapter.RecyclerViewAdapter;
import com.leagueofchampions.kiit.Adapter.StandingsRecyclerViewAdapter;
import com.leagueofchampions.kiit.Constants.Constants;
import com.leagueofchampions.kiit.Model.Fixture;
import com.leagueofchampions.kiit.Model.Played_Fixture;
import com.leagueofchampions.kiit.Model.Score;
import com.leagueofchampions.kiit.Model.Team_Stats_Received;
import com.leagueofchampions.kiit.Utils.Sort_Teams;
import com.leagueofchampions.kiit.Model.Team_Stats;
import com.leagueofchampions.kiit.R;
import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class Homescreen extends AppCompatActivity {

    private ShimmerTextView Title;
    private TabLayout tabLayout;
    private TabLayout.Tab Live,Standings,Result,Fixtures;
    private ImageButton Left_Button,Right_Button;
    private static int Current_Image;
    private SharedPreferences Favourite_Team;
    private ImageView SET_AS_WALLPAPER;
    private ImageView WALLPAPER;
    public static Activity GLOBAL_ACTIVITY;
    private View Live_Tab,Standings_Tab,Results_Tab,Fixtures_Tab;
    private View Tabs[];

    //Live Tab's views
    private ImageView Team1_Image,Team2_Image;
    private TextView  Team1_Name,Team2_Name;
    private TextView  Team1_Score,Team2_Score;
    private TextView  View_Details;
    private DatabaseReference Team1Score,Team2Score,Team1Name,Team2Name;
    private ProgressBar Team1_Image_Progress,Team2_Image_Progress;


    //Fixture Tab's Views
    private RecyclerView Matches;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<Fixture> Match_List;
    private DatabaseReference Fixtures_ref;
    private ProgressBar Fixtures_Progress_Bar;

    //Result Tab's Views
    private RecyclerView Played_Fixtures;
    private PlayedFixtureRecyclerViewAdapter playedFixtureRecyclerViewAdapter;
    private ArrayList<Played_Fixture> Played_Matches;
    private DatabaseReference Played_Fixtures_Referance;
    private ProgressBar Played_Fixtures_progress_Bar;

    //Standing Tab's Views
    private RecyclerView Standings_Recycler_View;
    private StandingsRecyclerViewAdapter standingsRecyclerViewAdapter;
    private ArrayList<Team_Stats> Teams;
    private DatabaseReference Standings_Ref;
    private ProgressBar Standings_Progress_Bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_homescreen);

        GLOBAL_ACTIVITY=this;

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        Title=(ShimmerTextView) findViewById(R.id.Title);
        tabLayout=(TabLayout) findViewById(R.id.Tab_layout);
        SET_AS_WALLPAPER=(ImageView) findViewById(R.id.set_as_wallpaper);
        WALLPAPER=(ImageView) findViewById(R.id.Wallpaper);
        Left_Button=(ImageButton) findViewById(R.id.move_left);
        Right_Button=(ImageButton) findViewById(R.id.move_right);

        //Live Tab's view
        Live_Tab=(LinearLayout) findViewById(R.id.Live_Tab);
        Team1_Image=(ImageView) findViewById(R.id.Team1_Image);
        Team2_Image=(ImageView) findViewById(R.id.Team2_Image);
        Team1_Name=(TextView) findViewById(R.id.Team1_Name);
        Team2_Name=(TextView) findViewById(R.id.Team2_Name);
        Team1_Score=(TextView) findViewById(R.id.Team1_Score);
        Team2_Score=(TextView) findViewById(R.id.Team2_score);
        View_Details=(TextView) findViewById(R.id.View_Details);
        Team1Name=FirebaseDatabase.getInstance().getReference().child("Live").child("Team1");
        Team2Name=FirebaseDatabase.getInstance().getReference().child("Live").child("Team2");
        Team1Score=FirebaseDatabase.getInstance().getReference().child("Live").child("Team1Score");
        Team2Score=FirebaseDatabase.getInstance().getReference().child("Live").child("Team2Score");
        Team1_Image_Progress=(ProgressBar) findViewById(R.id.Team1_Image_Progress);
        Team2_Image_Progress=(ProgressBar) findViewById(R.id.Team2_Image_Progress);


        //Standing's Tab
        Standings_Tab=(FrameLayout) findViewById(R.id.Standings);
        Standings_Recycler_View=(RecyclerView) findViewById(R.id.Standings_Recycler_View);
        Standings_Recycler_View.setLayoutManager(new LinearLayoutManager(this));
        Teams=new ArrayList<>();
        Standings_Ref=FirebaseDatabase.getInstance().getReference().child("Standings");
        Standings_Progress_Bar=(ProgressBar) findViewById(R.id.Standings_ProgressBar);

        Standings_Ref.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {

                Teams.clear();
                for(DataSnapshot D:dataSnapshot.getChildren())
                {
                    Team_Stats_Received Team=D.getValue(Team_Stats_Received.class);
                    Team_Stats Team_=new Team_Stats();

                    Team_.setDraw(Integer.parseInt(Team.getDraw()));
                    Team_.setLoss(Integer.parseInt(Team.getLoss()));

                    Team_.setMatches(Integer.parseInt(Team.getMatches()));
                    Team_.setPoints(Integer.parseInt(Team.getPoints()));
                    Team_.setName(Team.getName());
                    Team_.setWins(Integer.parseInt(Team.getWins()));
                    Team_.setNRR(Float.parseFloat(Team.getNRR()));
                    Teams.add(Team_);


                    Log.v("DwijrajMaina",Team.getMatches()+":"+Team_.getPoints());

                }

                if(Teams.size()!=0) {
                    Collections.sort(Teams, new Sort_Teams());
                    Collections.reverse(Teams);

                    Log.v("DwijrajTeams", Teams.size() + ":" + Teams.get(0).getName());

                    for (int i = 0; i < Teams.size(); i++) {
                        Teams.get(i).setPos(i + 1);
                    }
                    Standings_Progress_Bar.setVisibility(View.INVISIBLE);
                    standingsRecyclerViewAdapter = new StandingsRecyclerViewAdapter(Teams, Homescreen.this);
                    Standings_Recycler_View.setAdapter(standingsRecyclerViewAdapter);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //Result's tab
        Results_Tab=(LinearLayout) findViewById(R.id.Results);
        Played_Fixtures=(RecyclerView) findViewById(R.id.Played_Fixtures_Recycler_View);
        Played_Fixtures.setLayoutManager(new LinearLayoutManager(this));
        Played_Matches=new ArrayList<>();
        Played_Fixtures_Referance=FirebaseDatabase.getInstance().getReference().child("PlayedFixtures");
        Played_Fixtures_progress_Bar=(ProgressBar) findViewById(R.id.Played_Fixtures_Progress_Bar);
        Played_Fixtures_Referance.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {



                Played_Matches.clear();
                try {
                    for (DataSnapshot D : dataSnapshot.getChildren()) {
                        Played_Fixture Match = D.getValue(Played_Fixture.class);
                        Match.setSerial_Number(D.getKey().toString());

                        Log.e("Dwijraj", Match.getDate() + "...");


                        String[] Data = Match.getDate().split("-");


                        Match.setDate(Data[2] + "/" + Data[1] + "/" + Data[0]);
                        Played_Matches.add(Match);

                    }
                }
                catch (Exception e)
                {}
                if(Played_Matches.size()!=0) {
                    Played_Fixtures_progress_Bar.setVisibility(View.INVISIBLE);
                    playedFixtureRecyclerViewAdapter = new PlayedFixtureRecyclerViewAdapter(Played_Matches, Homescreen.this);
                    Played_Fixtures.setAdapter(playedFixtureRecyclerViewAdapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        //Fixture's tab
        Fixtures_Tab=(LinearLayout) findViewById(R.id.Fixtures);
        Fixtures_ref= FirebaseDatabase.getInstance().getReference().child("Fixtures");
        Fixtures_Progress_Bar=(ProgressBar) findViewById(R.id.Fixtures_Progress_Bar);
        Matches=(RecyclerView) findViewById(R.id.Fixtures_Recycler_View);
        Matches.setLayoutManager(new LinearLayoutManager(this));
        Match_List=new ArrayList<>();

        Fixtures_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Match_List.clear();
                Log.v("Dwijraj",dataSnapshot.toString());
                Log.v("Dwijraj1","WOrking1");
                for(DataSnapshot D:dataSnapshot.getChildren())
                {
                    Log.v("Dwijraj1","WOrking2");
                    Fixture Match=D.getValue(Fixture.class);
                    Match.setSerial_Number(D.getKey().toString());
                    String[] Data=Match.getDate().split("-");
                    Match.setDate(Data[2]+"/"+Data[1]+"/"+Data[0]);
                    Log.v("Dwijraj1","WOrking3");
                    Match_List.add(Match);

                }

                if(Match_List.size()!=0) {
                    Log.v("Dwijraj1", "WOrking4");
                    recyclerViewAdapter = new RecyclerViewAdapter(Match_List, Homescreen.this);
                    Log.v("Dwijraj1", "WOrkin51");
                    Fixtures_Progress_Bar.setVisibility(View.INVISIBLE);
                    Matches.setAdapter(recyclerViewAdapter);
                    Log.v("Dwijraj1", "WOrking6");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        Favourite_Team=getSharedPreferences(Constants.Shared_Preferance_Name,MODE_PRIVATE);
        Current_Image=Favourite_Team.getInt(Constants.Favourite_Team,0);

        Constants.TEAM_NAMES.put(Constants.FEROCIOUS_KNGIHTS,"Ferocious Knights");
        Constants.TEAM_NAMES.put(Constants.GALACTICOS,"Galacticos");
        Constants.TEAM_NAMES.put(Constants.KAISERS_ELEVEN,"Kaisers Eleven");
        Constants.TEAM_NAMES.put(Constants.MIGHTY_BISONS,"Mighty Bisons");
        Constants.TEAM_NAMES.put(Constants.PHOENIX_PHANTOMS,"Phoenix phantoms");
        Constants.TEAM_NAMES.put(Constants.ROYAL_WARRIORS,"Royal Warriors");


        for(Map.Entry<Integer,String> a :Constants.TEAM_NAMES.entrySet())
        {
            Constants.NAMES_FLAGS.put(a.getValue(),a.getKey());
        }


        Constants.TEAM_FLAGS.put(0,R.drawable._h_);
        Constants.TEAM_FLAGS.put(1,Constants.FEROCIOUS_KNGIHTS);
        Constants.TEAM_FLAGS.put(2,Constants.GALACTICOS);
        Constants.TEAM_FLAGS.put(3,Constants.KAISERS_ELEVEN);
        Constants.TEAM_FLAGS.put(4,Constants.MIGHTY_BISONS);
        Constants.TEAM_FLAGS.put(5,Constants.PHOENIX_PHANTOMS);
        Constants.TEAM_FLAGS.put(6,Constants.ROYAL_WARRIORS);

        Tabs=new View[4];
        Tabs[0]=Live_Tab;
        Tabs[1]=Standings_Tab;
        Tabs[2]=Results_Tab;
        Tabs[3]=Fixtures_Tab;






        Live=tabLayout.newTab();
        Live.setText("Live");


        Standings=tabLayout.newTab();
        Standings.setText("Table");

        Result=tabLayout.newTab();
        Result.setText("Results");

        Fixtures=tabLayout.newTab();
        Fixtures.setText("Fixtures");

        tabLayout.addTab(Live);
        tabLayout.addTab(Standings);
        tabLayout.addTab(Result);
        tabLayout.addTab(Fixtures);





        Change_Wallpaper(Constants.NONE);

        Left_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Current_Image!=0)
                {
                    Change_Wallpaper(Constants.LEFT);
                }


            }
        });
        Right_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Current_Image!=6)
                {
                    Change_Wallpaper(Constants.RIGHT);
                }

            }
        });


        SET_AS_WALLPAPER.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor Edit_Fav_Team=Favourite_Team.edit();
                Edit_Fav_Team.putInt(Constants.Favourite_Team,Current_Image);
                Edit_Fav_Team.apply();
                Toast.makeText(getApplicationContext(),"Wallpaper saved",Toast.LENGTH_SHORT).show();
            }
        });



        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if(tab.equals(Live))
                {
                    SwitchOnTab(0);
                }
                else if(tab.equals(Standings))
                {
                    SwitchOnTab(1);
                }
                else if(tab.equals(Result))
                {
                    SwitchOnTab(2);
                }
                else if(tab.equals(Fixtures))
                {
                    SwitchOnTab(3);
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //Live Tab's functions
        View_Details=(TextView) findViewById(R.id.View_Details);
        View_Details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    if (!(Details_activity.Team1.equals("N/A") || Details_activity.Team2.equals("N/A")))
                        startActivity(new Intent(Homescreen.this, Details_activity.class));
                    else
                        Toast.makeText(Homescreen.this, "Connect to Internet", Toast.LENGTH_LONG).show();
                }
                catch (Exception e)
                {
                    Toast.makeText(Homescreen.this, "No details to display", Toast.LENGTH_LONG).show();
                }
            }
        });
        Team1Name.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                try {
                    Details_activity.Team1 = dataSnapshot.getValue(String.class);

                    Team1_Image_Progress.setVisibility(View.INVISIBLE);
                    Team1_Name.setText(dataSnapshot.getValue(String.class));
                    //Uncomment latter
                    Team1_Image.setImageResource(Constants.NAMES_FLAGS.get(dataSnapshot.getValue(String.class)));
                }
                catch (Exception e)
                {}
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Team2Name.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                try {
                    Details_activity.Team2 = dataSnapshot.getValue(String.class);

                    Team2_Image_Progress.setVisibility(View.INVISIBLE);
                    Team2_Name.setText(dataSnapshot.getValue(String.class));
                    //Uncomment latter
                    Team2_Image.setImageResource(Constants.NAMES_FLAGS.get(dataSnapshot.getValue(String.class)));
                }
                catch (Exception e)
                {}
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Team1Score.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //for(DataSnapshot d :dataSnapshot.getChildren())
                //    Log.v("ScoreDwijraj",d.getValue()+"/");
                try {
                    Score TeamScore = dataSnapshot.getValue(Score.class);
                    //Uncomment latter
                    Team1_Score.setText(TeamScore.getRuns() + "/" + TeamScore.getWickets() + "(" + TeamScore.getOvers() + ")");
                }
                catch (Exception e)
                {}

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        Team2Score.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                try {
                    Score TeamScore = dataSnapshot.getValue(Score.class);
                    //Uncomment latter
                    Team2_Score.setText(TeamScore.getRuns() + "/" + TeamScore.getWickets() + "(" + TeamScore.getOvers() + ")");
                }
                catch (Exception e)
                {}

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }

    private void SwitchOnTab(int Number) {

        for(int i =0;i<Tabs.length;i++)
        {
            if(i==Number)
            {
                Tabs[i].setVisibility(View.VISIBLE);
            }
            else
            {
                Tabs[i].setVisibility(View.INVISIBLE);
            }
        }


    }

    public void Change_Wallpaper(String Direction)
    {
        if(Direction.equals(Constants.LEFT))
        {
            Current_Image=Current_Image-1;
        }
        else if(Direction.equals(Constants.RIGHT))
        {
            Current_Image=Current_Image+1;
        }
        WALLPAPER.setImageResource(Constants.TEAM_FLAGS.get(Current_Image));

    }


    @Override
    protected void onStart() {
        super.onStart();

        Shimmer s=new Shimmer();
        s.setDuration(5000);
        s.start(Title);

    }
}
