package com.leagueofchampions.kiit.Model;

/**
 * Created by Dwijraj on 09-11-2017.
 */

public class Played_Fixture {
    private String Date;
    private String Time;
    private String Team1;
    private String Team2;
    private String Serial_Number;
    private String Result;

    public Played_Fixture()
    {

    }
    public Played_Fixture(String Date, String Time, String Team1, String Team2, String Serial_Number,String Result)
    {
        this.Date=Date;
        this.Time=Time;
        this.Team1=Team1;
        this.Team2=Team2;
        this.Serial_Number=Serial_Number;
        this.Result=Result;
    }

    public String getResult() {
        return Result;
    }

    public void setResult(String result) {
        Result = result;
    }

    public String getSerial_Number() {
        return Serial_Number;
    }

    public void setSerial_Number(String serial_Number) {
        Serial_Number = serial_Number;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getTeam1() {
        return Team1;
    }

    public void setTeam1(String team1) {
        Team1 = team1;
    }

    public String getTeam2() {
        return Team2;
    }

    public void setTeam2(String team2) {
        Team2 = team2;
    }
}
