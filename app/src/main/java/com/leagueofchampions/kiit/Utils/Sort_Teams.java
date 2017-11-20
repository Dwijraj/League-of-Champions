package com.leagueofchampions.kiit.Utils;

import com.leagueofchampions.kiit.Model.Team_Stats;

import java.util.Comparator;

/**
 * Created by Dwijraj on 20-11-2017.
 */

public class Sort_Teams implements Comparator<Team_Stats>
{
    @Override
    public int compare(Team_Stats o1, Team_Stats o2) {

        if(o1.getPoints()!=o2.getPoints())
        {
            return o1.getPoints()-o2.getPoints();
        }
        else if(o1.getNRR()!=o2.getNRR())
        {
            if (o1.getNRR()>o2.getNRR())
                return (int)(o1.getNRR()-o2.getNRR());
            else
                return (int)(o2.getNRR()-o1.getNRR());

        }
        return 0;
    }
}
