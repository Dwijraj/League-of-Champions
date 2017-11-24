package com.leagueofchampions.kiit.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.leagueofchampions.kiit.Constants.Constants;
import com.leagueofchampions.kiit.Model.Bowler;
import com.leagueofchampions.kiit.Model.Team_Stats;
import com.leagueofchampions.kiit.R;

import java.util.ArrayList;

/**
 * Created by Dwijraj on 09-11-2017.
 */

public class BowlerRecyclerViewAdapter extends RecyclerView.Adapter<BowlerRecyclerViewAdapter.Holder> {

    private ArrayList<Bowler> ListData;
    private LayoutInflater inflater;
    private Context Main;


    public BowlerRecyclerViewAdapter(ArrayList<Bowler> listData, Context c) {
        this.ListData = listData;
        this.inflater=LayoutInflater.from(c);
        Main=c;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view=inflater.inflate(R.layout.bowling_scorecard,parent,false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        Bowler bowler=ListData.get(position);

        if(position%2==0)
        {
            holder.Bowling_Root.setBackgroundColor(Color.parseColor("#9c9595"));
        }

        holder.Econ.setText(bowler.getEconomy());
        holder.Wickets.setText(bowler.getWickets());
        holder.Over.setText(bowler.getOvers());
        holder.Bowler.setText(bowler.getName());
        holder.Maiden.setText(bowler.getMaidens());
        holder.Runs.setText(bowler.getRuns());


    }

    @Override
    public int getItemCount() {
        return ListData.size();
    }

    class Holder extends RecyclerView.ViewHolder
    {
        private TextView Bowler;
        private TextView Over;
        private TextView Maiden;
        private TextView Wickets;
        private TextView Runs;
        private TextView Econ;
        private LinearLayout Bowling_Root;

        public Holder(View itemView) {
            super(itemView);

            Bowling_Root=(LinearLayout) itemView.findViewById(R.id.Bowling_Root);
            Bowler=(TextView) itemView.findViewById(R.id.Bowler);
            Over=(TextView) itemView.findViewById(R.id.Over);
            Maiden=(TextView) itemView.findViewById(R.id.Maiden);
            Wickets=(TextView) itemView.findViewById(R.id.Wickets);
            Runs=(TextView) itemView.findViewById(R.id.Runs);
            Econ=(TextView) itemView.findViewById(R.id.Econ);

        }

    }


}