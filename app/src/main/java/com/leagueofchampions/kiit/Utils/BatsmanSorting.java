package com.leagueofchampions.kiit.Utils;

import com.leagueofchampions.kiit.Model.Batsman;

import java.math.BigInteger;
import java.util.Comparator;

/**
 * Created by Dwijraj on 25-11-2017.
 */

public class BatsmanSorting implements Comparator<Batsman> {
    @Override
    public int compare(Batsman o1, Batsman o2) {

        BigInteger a =o1.getNumber().subtract(o2.getNumber());
        return a.intValue();
    }
}
