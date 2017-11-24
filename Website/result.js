/**
 * Created by Navneet Anand on 21-11-2017.
 */

function updateDetails()
{
    var e = document.getElementById("Name");
    var Name = e.options[e.selectedIndex].text;

     e = document.getElementById("Matches");
    var Matches = e.value;

    e = document.getElementById("Loss");
    var Loss = e.value;

    e = document.getElementById("Draw");
    var Draw = e.value;

    e = document.getElementById("Wins");
    var Wins = e.value;

    e = document.getElementById("Points");
    var Points = e.value;

    e = document.getElementById("NRR");
    var NRR = e.value;

    var fbrf = firebase.database().ref();

    fbrf.child("Standings").child(Name).set({
        Name : Name ,
        Matches : Matches,
        Loss : Loss,
        Draw : Draw ,
        Points : Points ,
        Wins : Wins ,
        NRR : NRR

    } , function (error) {
        if(error)
        {
            window.alert("Check Internet Connection Then\n --> Try Again <--");
        }
        else
        {
            window.alert("data Inserted ");

        }

    } );


}

