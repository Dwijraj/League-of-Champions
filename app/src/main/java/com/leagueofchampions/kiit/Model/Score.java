package com.leagueofchampions.kiit.Model;

/**
 * Created by Dwijraj on 21-11-2017.
 */

public class Score {
    private String Runs;
    private String Wickets;
    private String Overs;

    public Score(String runs, String wickets, String overs) {
        Runs = runs;
        Wickets = wickets;
        Overs = overs;
    }

    public Score() {
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
