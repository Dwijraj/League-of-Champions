package com.leagueofchampions.kiit.Model;

/**
 * Created by Dwijraj on 21-11-2017.
 */

public class Bowler {

    private String Name;
    private String Runs;
    private String Overs;
    private String Wickets;
    private String Maidens;
    private String Economy;

    public Bowler() {
    }

    public Bowler(String name, String runs, String overs, String wickets, String maidens, String economy) {
        Name = name;
        Runs = runs;
        Overs = overs;
        Wickets = wickets;
        Maidens = maidens;
        Economy = economy;
    }

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

    public String getOvers() {
        return Overs;
    }

    public void setOvers(String overs) {
        Overs = overs;
    }

    public String getWickets() {
        return Wickets;
    }

    public void setWickets(String wickets) {
        Wickets = wickets;
    }

    public String getMaidens() {
        return Maidens;
    }

    public void setMaidens(String maidens) {
        Maidens = maidens;
    }

    public String getEconomy() {
        return Economy;
    }

    public void setEconomy(String economy) {
        Economy = economy;
    }
}
