/**
 * Created by Navneet Anand on 09-11-2017.
 */

//window.alert("Insert data");
var da = document.getElementById("date");

//var date =da.value;
var ti = document.getElementById("time");



var matchNo = document.getElementById("matchNo");
function updateDetails()
{

    var e = document.getElementById("Team1");
    var team1 = e.options[e.selectedIndex].text;

    var f = document.getElementById("Team2");
    var team2 = f.options[f.selectedIndex].text;

    var date =da.value.toString();
    var time = ti.value.toString();
  //  window.alert("Inserting data");

   // window.alert(team2);
  //  window.alert(team1);

    var MatchNo = matchNo.value;


    var fbrf = firebase.database().ref();
   // window.alert("hello 2");
    fbrf.child("Fixtures").child(MatchNo).set({
            Date : date,
            Time : time,
           Team1 : team1 ,
            Team2 : team2


    });
    fbrf.child("PlayedFixtures").child(MatchNo).set({
        Date : date,
        Time : time,
        Team1 : team1 ,
        Team2 : team2 ,
        Result : "Not Played"

    });



    window.alert("data inserted ");




}