package com.leagueofchampions.kiit.Model;

/**
 * Created by Dwijraj on 21-11-2017.
 */

public class Score {
    private String Runs;
    private String Wickets;
    private String Overs;
    private String Extras;

    public Score(String runs, String wickets, String overs,String Extras) {
        Runs = runs;
        Wickets = wickets;
        Overs = overs;
        this.Extras=Extras;
    }

    public Score() {
    }

    public String getExtras() {
        return Extras;
    }

    public void setExtras(String extras) {
        Extras = extras;
    }

    public String getRuns() {
        return Runs;
    }

    public void setRuns(String runs) {
        Runs = runs;
    }

    public String getWickets() {
        return Wickets;
    }

    public void setWickets(String wickets) {
        Wickets = wickets;
    }

    public String getOvers() {
        return Overs;
    }

    public void setOvers(String overs) {
        Overs = overs;
    }
}
