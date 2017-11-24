package com.leagueofchampions.kiit.Utils;

import com.leagueofchampions.kiit.Model.Batsman;

import java.util.Comparator;

/**
 * Created by Dwijraj on 25-11-2017.
 */

public class BatsmanSorting implements Comparator<Batsman> {
    @Override
    public int compare(Batsman o1, Batsman o2) {


        return o1.getNumber()-o2.getNumber();
    }
}
