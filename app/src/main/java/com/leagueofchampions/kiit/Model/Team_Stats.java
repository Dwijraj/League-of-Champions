package com.leagueofchampions.kiit.Model;

import java.util.Comparator;

/**
 * Created by Dwijraj on 10-11-2017.
 */

public class Team_Stats {

    private String Name;
    private int Matches;
    private int Loss;
    private int Draw;
    private int Points;
    private float NRR;

    public Team_Stats() {
    }

    public Team_Stats(String name, int Matches, int loss, int draw, int points, float NRR) {
        Name = name;
        Matches = Matches;
        Loss = loss;
        Draw = draw;
        Points = points;
        NRR = NRR;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getMatches() {
        return Matches;
    }

    public void setMatches(int Matches) {
        Matches = Matches;
    }

    public int getLoss() {
        return Loss;
    }

    public void setLoss(int loss) {
        Loss = loss;
    }

    public int getDraw() {
        return Draw;
    }

    public void setDraw(int draw) {
        Draw = draw;
    }

    public int getPoints() {
        return Points;
    }

    public void setPoints(int points) {
        Points = points;
    }

    public float getNRR() {
        return NRR;
    }

    public void setNRR(float NRR) {
        NRR = NRR;
    }
}
class Sort_Teams implements Comparator<Team_Stats>
{
    @Override
    public int compare(Team_Stats o1, Team_Stats o2) {
        
        if(o1.getPoints()!=o2.getPoints())
        {
            return o1.getPoints()-o2.getPoints();
        }
        else if(o1.getNRR()!=o2.getNRR())
        {
            return (int)(o1.getNRR()-o2.getNRR());
        }
        return 0;
    }
}

