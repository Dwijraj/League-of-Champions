package com.leagueofchampions.kiit.Model;

/**
 * Created by Dwijraj on 21-11-2017.
 */

public class Batsman {

    private String Name;
    private String Runs;
    private String Balls;
    private String Four;
    private String Six;
    private String StrikeRate;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getRuns() {
        return Runs;
    }

    public void setRuns(String runs) {
        Runs = runs;
    }

    public String getBalls() {
        return Balls;
    }

    public void setBalls(String balls) {
        Balls = balls;
    }

    public String getFour() {
        return Four;
    }

    public void setFour(String four) {
        Four = four;
    }

    public String getSix() {
        return Six;
    }

    public void setSix(String six) {
        Six = six;
    }

    public String getStrikeRate() {
        return StrikeRate;
    }

    public void setStrikeRate(String strikeRate) {
        StrikeRate = strikeRate;
    }

    public Batsman() {
    }

    public Batsman(String name, String runs, String balls, String four, String six, String strikeRate) {
        Name = name;
        Runs = runs;
        Balls = balls;
        Four = four;
        Six = six;
        StrikeRate = strikeRate;
    }
}
