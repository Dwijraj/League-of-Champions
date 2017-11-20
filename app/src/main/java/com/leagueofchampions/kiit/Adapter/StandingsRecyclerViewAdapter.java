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
import com.leagueofchampions.kiit.Model.Fixture;
import com.leagueofchampions.kiit.Model.Team_Stats;
import com.leagueofchampions.kiit.R;

import java.util.ArrayList;

/**
 * Created by Dwijraj on 09-11-2017.
 */

public class StandingsRecyclerViewAdapter extends RecyclerView.Adapter<StandingsRecyclerViewAdapter.Holder> {

    private ArrayList<Team_Stats> ListData;
    private LayoutInflater inflater;
    private Context Main;
    public static String KEY;


    public StandingsRecyclerViewAdapter(ArrayList<Team_Stats> listData, Context c) {
        this.ListData = listData;
        this.inflater=LayoutInflater.from(c);
        Main=c;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view=inflater.inflate(R.layout.standings,parent,false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {


        Team_Stats Team=ListData.get(position);

        Log.v("DwijrajPoints",Team.getPoints()+"//"+Team.getNRR());


        holder.Matches.setText(Team.getMatches()+"");
        holder.Loss.setText(Team.getLoss()+"");
        holder.Draws.setText(Team.getDraw()+"");
        holder.Wins.setText(Team.getWins()+"");
        holder.Name.setText(Team.getName()+"");
        holder.Points.setText(Team.getPoints()+"");
        holder.NRR.setText(Team.getNRR()+"");
        holder.Team_Icon.setImageResource(Constants.NAMES_FLAGS.get(Team.getName()));
        holder.Points.setText(Team.getPoints()+"");
        holder.Rank.setText(Team.getPos()+"");

    }

    @Override
    public int getItemCount() {
        return ListData.size();
    }

    class Holder extends RecyclerView.ViewHolder
    {
        private TextView  Rank;
        private ImageView Team_Icon;
        private TextView  Name;
        private TextView  Wins;
        private TextView  Loss;
        private TextView  Draws;
        private TextView  Points;
        private TextView  NRR;
        private TextView  Matches;

        public Holder(View itemView) {
            super(itemView);

            Rank=(TextView) itemView.findViewById(R.id.Position);
            Team_Icon=(ImageView) itemView.findViewById(R.id.Icon);
            Name=(TextView) itemView.findViewById(R.id.Team_Name);
            Wins=(TextView) itemView.findViewById(R.id.W);
            Draws=(TextView) itemView.findViewById(R.id.D);
            Loss=(TextView) itemView.findViewById(R.id.L);
            Matches=(TextView) itemView.findViewById(R.id.M);
            Points=(TextView) itemView.findViewById(R.id.P);
            NRR=(TextView) itemView.findViewById(R.id.NRR);
        }

    }


}