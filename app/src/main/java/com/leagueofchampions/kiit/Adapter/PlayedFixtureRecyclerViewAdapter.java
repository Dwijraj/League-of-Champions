package com.leagueofchampions.kiit.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.leagueofchampions.kiit.Constants.Constants;
import com.leagueofchampions.kiit.Model.Played_Fixture;
import com.leagueofchampions.kiit.R;

import java.util.ArrayList;

/**
 * Created by Dwijraj on 09-11-2017.
 */

public class PlayedFixtureRecyclerViewAdapter extends RecyclerView.Adapter<PlayedFixtureRecyclerViewAdapter.Holder> {

    private ArrayList<Played_Fixture> ListData;
    private LayoutInflater inflater;
    private Context Main;


    public PlayedFixtureRecyclerViewAdapter(ArrayList<Played_Fixture> listData, Context c) {
        this.ListData = listData;
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


        Played_Fixture Match=ListData.get(position);

        holder.Date_Time.setText("Match:- "+Match.getSerial_Number()+"  Date:-  "+Match.getDate()+" "+Match.getTime());
        holder.Team1.setImageResource(Constants.NAMES_FLAGS.get(Match.getTeam1()));
        holder.Team2.setImageResource(Constants.NAMES_FLAGS.get(Match.getTeam2()));

        if(!Match.getResult().equals(Constants.NOT_PLAYED))
        {
            holder.Result.setText(Match.getResult()+"(Click here to get more info)");
            holder.Result.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Show scorecard
                    Log.v("DwijrajBh","Clicked");
                }
            });
        }
        else
        {
            holder.Result.setText(Match.getResult());
        }
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

        public Holder(View itemView) {
            super(itemView);

            Result=(TextView) itemView.findViewById(R.id.Result_String);
            Team1=(ImageView) itemView.findViewById(R.id.Team1_Fixtures);
            Team2=(ImageView) itemView.findViewById(R.id.Team2_Fixtures);
            Date_Time=(TextView) itemView.findViewById(R.id.Match_Date_Time);


        }

    }


}