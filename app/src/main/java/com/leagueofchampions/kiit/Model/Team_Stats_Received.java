package com.leagueofchampions.kiit.Model;

/**
 * Created by Dwijraj on 21-11-2017.
 */

public class Team_Stats_Received {

    private String Name;
    private String Matches;
    private String Loss;
    private String Draw;
    private String Points;
    private String Wins;
    private String NRR;

    public Team_Stats_Received() {
    }

    public Team_Stats_Received(String name, String matches, String loss, String draw, String points, String wins, String NRR) {
        Name = name;
        Matches = matches;
        Loss = loss;
        Draw = draw;
        Points = points;
        Wins = wins;
        this.NRR = NRR;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMatches() {
        return Matches;
    }

    public void setMatches(String matches) {
        Matches = matches;
    }

    public String getLoss() {
        return Loss;
    }

    public void setLoss(String loss) {
        Loss = loss;
    }

    public String getDraw() {
        return Draw;
    }

    public void setDraw(String draw) {
        Draw = draw;
    }

    public String getPoints() {
        return Points;
    }

    public void setPoints(String points) {
        Points = points;
    }

    public String getWins() {
        return Wins;
    }

    public void setWins(String wins) {
        Wins = wins;
    }

    public String getNRR() {
        return NRR;
    }

    public void setNRR(String NRR) {
        this.NRR = NRR;
    }
}
