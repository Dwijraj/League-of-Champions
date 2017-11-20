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
    private int Wins;
    private float NRR;
    private int Pos;


    public Team_Stats() {
    }

    @Override
    public String toString() {
        return this.Name+this.Matches+this.Loss+this.Draw+this.Points+this.Wins+this.NRR+this.Pos;
    }

    public Team_Stats(String name, int Matches, int loss, int draw, int points, int Wins, float NRR, int Pos) {
        Name = name;
        this.Matches = Matches;
        Loss = loss;
        Draw = draw;
        Points = points;
        this.NRR = NRR;
        this.Wins=Wins;
        this.Pos=Pos;
    }

    public int getPos() {
        return Pos;
    }

    public void setPos(int pos) {
        Pos = pos;
    }

    public int getWins() {
        return Wins;
    }

    public void setWins(int wins) {
        Wins = wins;
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
        this.Matches = Matches;
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
        this.NRR = NRR;
    }
}
