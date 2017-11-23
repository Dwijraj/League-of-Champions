package com.leagueofchampions.kiit.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.leagueofchampions.kiit.Constants.Constants;
import com.leagueofchampions.kiit.Model.Batsman;
import com.leagueofchampions.kiit.Model.Team_Stats;
import com.leagueofchampions.kiit.R;

import java.util.ArrayList;

/**
 * Created by Dwijraj on 09-11-2017.
 */

public class BatsmanRecyclerViewAdapter extends RecyclerView.Adapter<BatsmanRecyclerViewAdapter.Holder> {

    private ArrayList<Batsman> ListData;
    private LayoutInflater inflater;
    private Context Main;


    public BatsmanRecyclerViewAdapter(ArrayList<Batsman> listData, Context c) {
        this.ListData = listData;
        this.inflater=LayoutInflater.from(c);
        Main=c;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view=inflater.inflate(R.layout.batting_scorecard,parent,false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {


        Batsman batsman=ListData.get(position);

        holder.Batsman.setText(batsman.getName());
        holder.StrikeRate.setText(batsman.getStrikeRate());
        holder.Balls.setText(batsman.getBalls());
        holder.Four.setText(batsman.getFour());
        holder.Runs.setText(batsman.getRuns());
        holder.Six.setText(batsman.getSix());



    }

    @Override
    public int getItemCount() {
        return ListData.size();
    }

    class Holder extends RecyclerView.ViewHolder
    {
        private TextView Batsman;
        private TextView Runs;
        private TextView Balls;
        private TextView Four;
        private TextView Six;
        private TextView StrikeRate;

        public Holder(View itemView) {
            super(itemView);

           Batsman=(TextView) itemView.findViewById(R.id.Batsman);
           Runs=(TextView) itemView.findViewById(R.id.Runs);
           Balls=(TextView) itemView.findViewById(R.id.Balls);
           Four=(TextView) itemView.findViewById(R.id.Four);
           Six=(TextView) itemView.findViewById(R.id.Six);
           StrikeRate=(TextView) itemView.findViewById(R.id.StrikeRate);
        }

    }


}