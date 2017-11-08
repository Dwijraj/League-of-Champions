package com.leagueofchampions.kiit.UI;

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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.leagueofchampions.kiit.Adapter.RecyclerViewAdapter;
import com.leagueofchampions.kiit.Constants.Constants;
import com.leagueofchampions.kiit.Model.Fixture;
import com.leagueofchampions.kiit.R;
import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

import java.util.ArrayList;
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

    //Live Tab's views
    private ImageView Team1_Image,Team2_Image;
    private TextView  Team1_Name,Team2_Name;
    private TextView  Team1_Score,Team2_Score;
    private TextView  View_Details;
    private LinearLayout Live_Tab,Standings_Tab,Results_Tab,Fixtures_Tab;
    private LinearLayout Tabs[];


    //Fixture Tab's Views
    private RecyclerView Matches;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<Fixture> Match_List;

    private DatabaseReference Fixtures_ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_homescreen);

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

        //Standing's Tab
        Standings_Tab=(LinearLayout) findViewById(R.id.Standings);

        //Result's tab
        Results_Tab=(LinearLayout) findViewById(R.id.Results);

        //Fixture's tab
        Fixtures_Tab=(LinearLayout) findViewById(R.id.Fixtures);
        Fixtures_ref= FirebaseDatabase.getInstance().getReference().child("Fixtures");
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
                Log.v("Dwijraj1","WOrking4");
                recyclerViewAdapter=new RecyclerViewAdapter(Match_List, Homescreen.this);
                Log.v("Dwijraj1","WOrkin51");
                Matches.setAdapter(recyclerViewAdapter);
                Log.v("Dwijraj1","WOrking6");

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

        Tabs=new LinearLayout[4];
        Tabs[0]=Live_Tab;
        Tabs[1]=Standings_Tab;
        Tabs[2]=Results_Tab;
        Tabs[3]=Fixtures_Tab;






        Live=tabLayout.newTab();
        Live.setText("Live");


        Standings=tabLayout.newTab();
        Standings.setText("Standings");

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

                /**
                 Tabs[0]=Live_Tab;
                 Tabs[1]=Standings_Tab;
                 Tabs[2]=Results_Tab;
                 Tabs[3]=Fixtures_Tab;
                 */

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

                startActivity(new Intent(Homescreen.this,Details_activity.class));

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
