package com.leagueofchampions.kiit.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.leagueofchampions.kiit.Constants.Constants;
import com.leagueofchampions.kiit.Model.Played_Fixture;
import com.leagueofchampions.kiit.R;
import com.leagueofchampions.kiit.UI.Homescreen;
import com.leagueofchampions.kiit.UI.Scorecard;

import java.util.ArrayList;

/**
 * Created by Dwijraj on 09-11-2017.
 */

public class PlayedFixtureRecyclerViewAdapter extends RecyclerView.Adapter<PlayedFixtureRecyclerViewAdapter.Holder> {

    private ArrayList<Played_Fixture> ListData;
    private LayoutInflater inflater;
    private static DatabaseReference Scorecard;
    private Context Main;


    public PlayedFixtureRecyclerViewAdapter(ArrayList<Played_Fixture> listData, Context c) {
        this.ListData = listData;
        Scorecard= FirebaseDatabase.getInstance().getReference().child("Scorecard");
        this.inflater=LayoutInflater.from(c);
        Main=c;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view=inflater.inflate(R.layout.played_fixture_row,parent,false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {


       final Played_Fixture Match=ListData.get(position);

        holder.Date_Time.setText("Match:- "+Match.getSerial_Number()+"  Date:-  "+Match.getDate()+" "+Match.getTime());
        holder.Team1.setImageResource(Constants.NAMES_FLAGS.get(Match.getTeam1()));
        holder.Team2.setImageResource(Constants.NAMES_FLAGS.get(Match.getTeam2()));
        holder.Result.setText(Match.getResult());

        if(Match.getResult().equals("Not Played") && position!=0 && (!Homescreen.FOUND_NEXT_MATCH))
        {

            Homescreen.NEXT_MATCH=Match;
            Homescreen.FOUND_NEXT_MATCH=true;
        }


        holder.Holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Scorecard.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        // Log.v("Maina",Match.getSerial_Number()+dataSnapshot.hasChild(Match.getSerial_Number()));


                        if(dataSnapshot.hasChild(Match.getSerial_Number()))
                        {
                            Homescreen.GLOBAL_ACTIVITY.startActivity(new Intent(Homescreen.GLOBAL_ACTIVITY, Scorecard.class).putExtra("MatchNo",Match.getSerial_Number()));
                        }
                        else
                        {
                            Toast.makeText(Homescreen.GLOBAL_ACTIVITY,"Scorecard not updated yet",Toast.LENGTH_SHORT).show();
                        }
                          /* for(DataSnapshot d :dataSnapshot.getChildren())
                           {
                               Log.v("Maina",d.getKey()+Match.getSerial_Number()+dataSnapshot.hasChild(Match.getSerial_Number()));

                           }*/

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });
        holder.Result.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Show scorecard

                    Scorecard.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                           // Log.v("Maina",Match.getSerial_Number()+dataSnapshot.hasChild(Match.getSerial_Number()));


                            if(dataSnapshot.hasChild(Match.getSerial_Number()))
                            {
                                Homescreen.GLOBAL_ACTIVITY.startActivity(new Intent(Homescreen.GLOBAL_ACTIVITY, Scorecard.class).putExtra("MatchNo",Match.getSerial_Number()));
                            }
                            else
                            {
                                Toast.makeText(Homescreen.GLOBAL_ACTIVITY,"Scorecard not updated yet",Toast.LENGTH_SHORT).show();
                            }
                          /* for(DataSnapshot d :dataSnapshot.getChildren())
                           {
                               Log.v("Maina",d.getKey()+Match.getSerial_Number()+dataSnapshot.hasChild(Match.getSerial_Number()));

                           }*/

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });


                }
            });

    }

    @Override
    public int getItemCount() {
        return ListData.size();
    }

    class Holder extends RecyclerView.ViewHolder
    {
        private ImageView Team1;
        private ImageView Team2;
        private TextView  Date_Time;
        private TextView  Result;
        private android.support.v7.widget.CardView Holder;

        public Holder(View itemView) {
            super(itemView);

            Holder=(CardView) itemView.findViewById(R.id.Holder);
            Result=(TextView) itemView.findViewById(R.id.Result_String);
            Team1=(ImageView) itemView.findViewById(R.id.Team1_Fixtures);
            Team2=(ImageView) itemView.findViewById(R.id.Team2_Fixtures);
            Date_Time=(TextView) itemView.findViewById(R.id.Match_Date_Time);


        }

    }


}