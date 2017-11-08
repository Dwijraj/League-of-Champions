package com.leagueofchampions.kiit.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.leagueofchampions.kiit.Constants.Constants;
import com.leagueofchampions.kiit.Model.Fixture;
import com.leagueofchampions.kiit.R;

import java.util.ArrayList;

/**
 * Created by Dwijraj on 09-11-2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.Holder> {

    private ArrayList<Fixture> ListData;
    private LayoutInflater inflater;
    private Context Main;
    public static String KEY;


    public RecyclerViewAdapter(ArrayList<Fixture> listData, Context c) {
        this.ListData = listData;
        this.inflater=LayoutInflater.from(c);
        Main=c;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view=inflater.inflate(R.layout.fixture_row,parent,false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {


        Fixture Match=ListData.get(position);

        holder.Date_Time.setText("Match:- "+Match.getSerial_Number()+"  Date:-  "+Match.getDate()+"  Time:-  "+Match.getTime());
        holder.Team1.setImageResource(Constants.NAMES_FLAGS.get(Match.getTeam1()));
        holder.Team2.setImageResource(Constants.NAMES_FLAGS.get(Match.getTeam2()));

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

        public Holder(View itemView) {
            super(itemView);

            Team1=(ImageView) itemView.findViewById(R.id.Team1_Fixtures);
            Team2=(ImageView) itemView.findViewById(R.id.Team2_Fixtures);
            Date_Time=(TextView) itemView.findViewById(R.id.Match_Date_Time);


        }

    }


}