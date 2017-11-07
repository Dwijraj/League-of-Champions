package com.leagueofchampions.kiit;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.leagueofchampions.kiit.Adapter.MyViewPagerAdapter;
import com.leagueofchampions.kiit.Constants.Constants;
import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;

public class Homescreen extends AppCompatActivity {

    private ShimmerTextView Title;
    private TabLayout tabLayout;
    private TabLayout.Tab Live,Standings,Result,Fixtures;
    private ImageButton Left_Button,Right_Button;
    private static int Current_Image;
    private SharedPreferences Favourite_Team;
    private ImageView SET_AS_WALLPAPER;
    private ImageView WALLPAPER;
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


        Favourite_Team=getSharedPreferences(Constants.Shared_Preferance_Name,MODE_PRIVATE);
        Current_Image=Favourite_Team.getInt(Constants.Favourite_Team,0);

        Constants.TEAM_NAMES.put(Constants.FEROCIOUS_KNGIHTS,"Ferocious Knights");
        Constants.TEAM_NAMES.put(Constants.GALACTICOS,"Galacticos");
        Constants.TEAM_NAMES.put(Constants.KAISERS_ELEVEN,"Kaisers Eleven");
        Constants.TEAM_NAMES.put(Constants.MIGHTY_BISONS,"Mighty Bisons");
        Constants.TEAM_NAMES.put(Constants.PHOENIX_PHANTOMS,"Phoenix phantoms");
        Constants.TEAM_NAMES.put(Constants.ROYAL_WARRIORS,"Royal Warriors");

        Constants.TEAM_FLAGS.put(0,R.drawable._h_);
        Constants.TEAM_FLAGS.put(1,Constants.FEROCIOUS_KNGIHTS);
        Constants.TEAM_FLAGS.put(2,Constants.GALACTICOS);
        Constants.TEAM_FLAGS.put(3,Constants.KAISERS_ELEVEN);
        Constants.TEAM_FLAGS.put(4,Constants.MIGHTY_BISONS);
        Constants.TEAM_FLAGS.put(5,Constants.PHOENIX_PHANTOMS);
        Constants.TEAM_FLAGS.put(6,Constants.ROYAL_WARRIORS);





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
