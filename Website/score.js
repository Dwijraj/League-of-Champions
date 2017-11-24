/**
 * Created by Navneet Anand on 21-11-2017.
 */
var Team1, Team ,Team2 , batting , bowling ;
var Match , team1 ,team2 , bat , bowl;
//var SentData1 ,SentData2 ;

var firebaseref = firebase.database().ref();

 var table = document.getElementById("customers");
     table.style.visibility = 'hidden';


firebaseref.child("Live").once("value").then(function(Snapshot){


    Match = Snapshot.child("MatchNo").val();
    team1 = Snapshot.child("Team1").val();
    team2 = Snapshot.child("Team2").val();
    bat = Snapshot.child("BattingTeam").val();
    bowl = Snapshot.child("BowlingTeam").val();

 //   window.alert(Match + team1 + team2 + bat + " " );
    table.style.visibility = 'visible';
});


function SelectTeam() {
    Team = document.getElementById("Team1");
    Team1 = Team.options[Team.selectedIndex].text;
    Team = document.getElementById("Team2");
    Team2 = Team.options[Team.selectedIndex].text;

    var matchNo = document.getElementById("MatchNo");
    var MatchNo = matchNo.value;

    firebaseref.child("PlayedFixtures").child(MatchNo).update({Result : "Match In Progress(Click To Get More Info)"});

    var BattingTeam = "<option value='" + Team1 + "'>" + Team1 + "</option>" +
        " <option value='" + Team2 + "'>" + Team2 + "</option>";

    //============================================
    if (Match == MatchNo && team1 == Team1 && team2 == Team2) {
            document.getElementById("team1").innerHTML = team1;
            document.getElementById("team2").innerHTML = team2;
            document.getElementById("matchNo").innerHTML = MatchNo;
            document.getElementById("TeamSubmit").innerHTML = "Done";
              document.getElementById("batting").innerHTML = BattingTeam;
           // document.getElementById("Batting").innerHTML =bat;
           // document.getElementById("BattingSubmit").innerHTML = "done";
                batting= bat;
                bowling= bowl;
                players(batting);



    }
    else {
        Match = MatchNo;
        //============================================
        window.alert("Before");
        firebaseref.child("Scorecard").child(MatchNo).update({
            Team1 : Team1,
            Team2 :Team2
        });
       // firebaseref.child("Scorecard").child(MatchNo).child("Team2").set(Team2);
        window.alert("After Scorecard ");
        firebaseref.child("Live").update({
            Team1: Team1,
            Team2: Team2,
            MatchNo: MatchNo
        }, function (error) {
            if (error) {
                window.alert("Check Internet Connection Then\n --> Try Again <--");
            }
            else {
                // window.alert("data Inserted ");
                document.getElementById("team1").innerHTML = Team1;
                document.getElementById("team2").innerHTML = Team2;
                document.getElementById("matchNo").innerHTML = MatchNo;
                document.getElementById("TeamSubmit").innerHTML = "Done";
                document.getElementById("batting").innerHTML = BattingTeam;


            }

        });


        firebaseref.child("Live").child("Team1Score").update({
                Runs: "0",
                Wickets: "0",
                Overs: "0",
                Extras :"0"
            }
        );
        firebaseref.child("Live").child("Team2Score").update({
                Runs: "0",
                Wickets: "0",
                Overs: "0",
                Extras :"0"
            }
        );


    }


}

function BattingTeam() {
        var Batting = document.getElementById("batting");
         batting = Batting.options[Batting.selectedIndex].text;
     //   SentData1=undefined;
      //  SentData2=undefined ;

        if (batting == Team1)
            bowling = Team2;
        else
            bowling = Team1;

       // window.alert(batting + bowling);
        firebaseref.child("Live").update({
            BattingTeam: batting,
            BowlingTeam: bowling
        }, function (error) {
            if (error) {
                window.alert("Check Internet Connection Then\n --> Try Again <--");
            }
            else {
               //  window.alert(batting);
              /*  document.getElementById("Batting").innerHTML = batting;
                document.getElementById("BattingSubmit").innerHTML = "done";
              */

            }

        });
     //   window.alert("before batsman()");
        var dropdown1 = document.getElementById("bat1");
        var dropdown2 = document.getElementById("bat2");
        var dropdown3 = document.getElementById("bowl");

        dropdown1.innerHTML=players(batting);
        dropdown2.innerHTML=players(batting);
        dropdown3.innerHTML=players(bowling);


}


function StrikeRate(Balls , Runs)
{
    //  var Balls = Over.split('.');
    // var count = Balls[0]*6 + Balls[1];
    var SR = Math.floor(((Runs*100/Balls)*100))/100;
    var ret = Math.floor((SR*100))/100;
    return ret+"";
}
function EconomyRate(Over , Runs ) {

    var Balls = Over.split('.');
    var count = Balls[0]*6 + Balls[1];
    //var count = Over*6;
    var ER = Math.floor(((Runs*6/count)*100))/100 ;
    return ER+"";

}

function players(batting)
{
    var batsman = "";

 //   window.alert("inside batsman  "+batting.charAt(0));
    switch(batting.charAt(0))
    {
        case "F" :
            batsman =' '+'<option value ="ABHIJEET DIWAKAR(C)">ABHIJEET DIWAKAR(C)</option>'+
                '<option value ="VAIBHAV VERMA(VC)">VAIBHAV VERMA(VC)</option>'+
                '<option value ="SANJEEV TIWARI">SANJEEV TIWARI</option>'+
                '<option value ="GOPAL DAS">GOPAL DAS</option>'+
                '<option value ="ANKUR KUMAR">ANKUR KUMAR</option>'+
                '<option value ="KSHITIJ SHARMA">KSHITIJ SHARMA </option>'+
                '<option value ="SHAYAN PAL">SHAYAN PAL</option>'+
                '<option value ="VITTESH SINHA">VITTESH SINHA </option>'+
                '<option value ="MIRDUL TRIPATHY">MIRDUL TRIPATHY</option>'+
                '<option value ="SHUBHAM KUMAR">SHUBHAM KUMAR</option>'+
                '<option value ="ANKIT KUMAR">ANKIT KUMAR</option>'+
                '<option value ="RAKESH RANJAN">RAKESH RANJAN</option>'+
                '<option value ="UTKARSH SINHA">UTKARSH SINHA</option>'+
                '<option value ="ADITYA GUPTA">ADITYA GUPTA</option>'+
                '<option value ="ARYAN SINGH">ARYAN SINGH </option>'+
                '<option value ="SHARAT MARUVADA">SHARAT MARUVADA</option>'+
                '<option value ="RISHABH RANGHUVANSHI">RISHABH RANGHUVANSHI </option>';
                break;

        case "P" :
             batsman = "<option value ='NARVEER SINGH(C)'>NARVEER SINGH(C)</option>"+
                 "<option value ='RAJEEV KUMAR(VC)'>RAJEEV KUMAR(VC)</option>"+
                "<option value ='MAYANK RAJ SINGH'>MAYANK RAJ SINGH</option>"+
               "<option value ='NELANSH MALHAN'>NELANSH MALHAN</option>"+
               "<option value ='PIYUSH RANJAN'>PIYUSH RANJAN</option>"+
                 "<option value ='SHUBHAM KUMAR'>SHUBHAM KUMAR</option>"+
               "<option value ='SIDHARTH SINGH'>SIDHARTH SINGH</option>"+
               "<option value ='GAUTAM'>GAUTAM</option>"+
              "<option value ='SOUVIK CHOUDHARY'>SOUVIK CHOUDHARY</option>"+
             "<option value ='KISHAN KRISHNENDU'>KISHAN KRISHNENDU</option>"+
               "<option value ='RAJNIKANT'>RAJNIKANT</option>"+
              "<option value ='KARAN GUPTA'>KARAN GUPTA</option>"+
             "<option value ='NISHANT NIMISH'>NISHANT NIMISH</option>"+
             "<option value ='SHIVKANT VAISH'>SHIVKANT VAISH</option>"+
             "<option value ='NISHANT NIKHIL'>NISHANT NIKHIL</option>"+
             "<option value ='MICKY'>MICKY</option>"+
              "<option value ='SASNKHDEEP MISHRA'>SASNKHDEEP MISHRA</option>";
            break;
        case "M" :
        batsman="<option value ='AYUSH GAUTAM(C)'>AYUSH GAUTAM(C)</option>"+
        "<option value ='ASHUTOSH KUMAR(VC)'>ASHUTOSH KUMAR(VC)</option>"+
        "<option value ='HARSHIT PANDEY'>HARSHIT PANDEY</option>"+
        "<option value ='JOYTIRANJAN MOHANTI'>JOYTIRANJAN MOHANTI</option>"+
        "<option value ='PRASHANT TIWARI'>PRASHANT TIWARI</option>"+
        "<option value ='AAKARSH'>AAKARSH</option>"+
        "<option value ='SHARAD'>SHARAD</option>"+
        "<option value ='PRAMOD KUMAR'>PRAMOD KUMAR</option>"+
        "<option value ='ASHUTOSH PANDEY'>ASHUTOSH PANDEY</option>"+
        "<option value ='RISHABH RAJ'>RISHABH RAJ</option>"+
        "<option value ='AVIRAL ANAND'>AVIRAL ANAND</option>"+
        "<option value ='SATYAM'>SATYAM</option>"+
        "<option value ='SAI VARUDU'>SAI VARUDU</option>"+
        "<option value ='ABHISHEK SINGH'>ABHISHEK SINGH</option>"+
        "<option value ='SAURABH KASHYAP'>SAURABH KASHYAP</option>"+
        "<option value ='RAJAT SOREN'>RAJAT SOREN</option>"+
        "<option value ='AYUSH SHARMA'>AYUSH SHARMA</option>";
            break;

        case "G" :
            batsman="<option value ='SUMAN KUMAR(C)'>SUMAN KUMAR(C)</option>"+
        "<option value ='PIYUSH KUMAR(VC)'>PIYUSH KUMAR(VC)</option>"+
        "<option value ='AYUSH ANAND'>AYUSH ANAND</option>"+
        "<option value ='ROHIT THAPA'>ROHIT THAPA</option>"+
        "<option value ='CHANDAN KUMAR'>CHANDAN KUMAR</option>"+
        "<option value ='SAMEER ZAMAN'>SAMEER ZAMAN</option>"+
        "<option value ='DEEPAK KUMAR'>DEEPAK KUMAR</option>"+
        "<option value ='AKSHAY ANAND'>AKSHAY ANAND</option>"+
        "<option value ='ATUL SINHA'>ATUL SINHA</option>"+
        "<option value ='HARSH SHARMA'>HARSH SHARMA</option>"+
        "<option value ='ANJJAN SAIIKIA'>ANJJAN SAIIKIA</option>"+
        "<option value ='ABHINAV KUMAR'>ABHINAV KUMAR</option>"+
        "<option value ='NITESH KUMAR'>NITESH KUMAR</option>"+
        "<option value ='DEEPAK'>DEEPAK</option>"+
        "<option value ='PRASHANT SINGH'>PRASHANT SINGH</option>";
            break;

        case "R":
        batsman="<option value ='VENKAT PRASHANT(C)'>VENKAT PRASHANT(C)</option>"+
        "<option value ='KINGSHUK ROY(VC)'>KINGSHUK ROY(VC)</option>"+
        "<option value ='ABHINAV TRIPATHI'>ABHINAV TRIPATHI</option>"+
        "<option value ='CHHITIJ KUMAR'>CHHITIJ KUMAR</option>"+
        "<option value ='SAURAV KUMAR'>SAURAV KUMAR</option>"+
        "<option value ='AMAN SINGH'>AMAN SINGH</option>"+
        "<option value ='HIMANSHU SHEKHAR'>HIMANSHU SHEKHAR</option>"+
        "<option value ='PRIYANSHU SHEKHAR'>PRIYANSHU SHEKHAR</option>"+
        "<option value ='ANUBHAV KUMAR'>ANUBHAV KUMAR</option>"+
        "<option value ='SOUMIK CHAKRABORTY'>SOUMIK CHAKRABORTY</option>"+
        "<option value ='DIBYAYAN BANERJEE'>DIBYAYAN BANERJEE</option>"+
        "<option value ='ROHIT RAMAN'>ROHIT RAMAN</option>"+
        "<option value ='ADIL'>ADIL</option>"+
        "<option value ='SHUBHAM TRIPATHI'>SHUBHAM TRIPATHI</option>"+
        "<option value ='RITTIK GAUTAM'>RITTIK GAUTAM</option>"+
        "<option value ='SAYAN PAL'>SAYAN PAL</option>"+
        "<option value ='SOUMIK NASKAR'>SOUMIK NASKAR</option>"+
        "<option value ='DHIRAJ GOSWAMI'>DHIRAJ GOSWAMI</option>";
            break;

        case "K" :
            batsman = "<option value ='VIVEK KUMAR(C)'>VIVEK KUMAR(C)</option>"+
        "<option value ='ANKESH KUMAR(VC)'>ANKESH KUMAR(VC)</option>"+
        "<option value ='SONAL KUMAR'>SONAL KUMAR</option>"+
        "<option value ='ANIMESH MISHRA'>ANIMESH MISHRA</option>"+
        "<option value ='VIVEK RAJ'>VIVEK RAJ</option>"+
        "<option value ='SANJEEV BHARDWAJ'>SANJEEV BHARDWAJ</option>"+
        "<option value ='ANIRUDH KUMAR'>ANIRUDH KUMAR</option>"+
        "<option value ='VISHAL'>VISHAL</option>"+
        "<option value ='ANIMESH'>ANIMESH</option>"+
        "<option value ='SHOBHIT SHUBHAM'>SHOBHIT SHUBHAM</option>"+
        "<option value ='RAHUL KUMAR'>RAHUL KUMAR</option>"+
        "<option value ='RITWIK GIRI'>RITWIK GIRI</option>"+
        "<option value ='RAHUL ANAND'>RAHUL ANAND</option>"+
        "<option value ='KISHAN KUMAR'>KISHAN KUMAR</option>"+
        "<option value ='ANSHUL GAUTAM'>ANSHUL GAUTAM</option>"+
        "<option value ='UTKARSH RAJ'>UTKARSH RAJ</option>"+
        "<option value ='SHUBHAM SHAURAV'>SHUBHAM SHAURAV</option>"+
        "<option value ='GAURAV SANJAY'>GAURAV SANJAY</option>"+
        "<option value ='SHUBHAM RAJ'>SHUBHAM RAJ</option>"+
        "<option value ='PIYUSH RAJ'>PIYUSH RAJ</option>"+
        "<option value ='AMIT KUMAR'>AMIT KUMAR</option>";
                break;

        default :
            batsman+='<option value ="Navneet">Navneet(MasterGoGo)</option>'+
                '<option value ="UTKARSH SINHA">DwijRaj(DoDO)</option>';
            break;




    }
    //  dropdown1.innerHTML=batsman;
    // dropdown2.innerHTML=batsman;
    return batsman;

}



function updatebat1()
{
      var batsman = document.getElementById("bat1");
      var Batsman =  batsman.options[batsman.selectedIndex].text;

      var runs = document.getElementById("Runs1");
      var Runs = runs.value;

      var balls = document.getElementById("Balls1");
      var Balls = balls.value;

      var four = document.getElementById("Four1");
      var Four = four.value;

      var six = document.getElementById("Six1");
      var Six = six.value;

    //  var sr = document.getElementById("SR1");
      var SR = StrikeRate(Balls , Runs);

      var DataToSend = {
          Name : Batsman,
          Runs : Runs,
          Balls : Balls,
          Four : Four,
          Six : Six,
          StrikeRate : SR
      };


   // window.alert("Updatebat1");
      firebaseref.child("Live").child("Batsman").child("Batsman1").update( DataToSend , function(error){
          if(error)
          {
              window.alert("Something went wrong ");
          }

      });

        if(Team1 ==batting) {
            //try{
                firebaseref.child("Scorecard").child(Match).child("Team1Batting").child(DataToSend.Name).update(DataToSend, function (error) {
                    if (error) {
                        window.alert("Something went wrong ");
                    }

                });
           // }
            /*
            catch(err)
            {
                firebaseref.child("Scorecard").child(Match).child("Team1Batting").child(DataToSend.Name).set(DataToSend, function (error) {
                    if (error) {
                        window.alert("Something went wrong ");
                    }

                });

            }*/
        }
        else {
           // try {
                firebaseref.child("Scorecard").child(Match).child("Team2Batting").child(DataToSend.Name).update(DataToSend, function (error) {
                    if (error) {
                        window.alert("Something went wrong ");
                    }

                });
           /* }
            catch (err)
            {
                firebaseref.child("Scorecard").child(Match).child("Team2Batting").child(DataToSend.Name).set(DataToSend, function (error) {
                    if (error) {
                        window.alert("Something went wrong ");
                    }

                });

            }
*/
        }






}


function updatebat2()
{
    var batsman = document.getElementById("bat2");
    var Batsman =  batsman.options[batsman.selectedIndex].text;

    var runs = document.getElementById("Runs2");
    var Runs = runs.value;

    var balls = document.getElementById("Balls2");
    var Balls = balls.value;

    var four = document.getElementById("Four2");
    var Four = four.value;

    var six = document.getElementById("Six2");
    var Six = six.value;

   // var sr = document.getElementById("SR2");
   // var SR = sr.value;
    var SR = StrikeRate(Balls , Runs);
   // window.alert("Updatebat2");

    var DataToSend = {
        Name : Batsman,
        Runs : Runs,
        Balls : Balls,
        Four : Four,
        Six : Six,
        StrikeRate : SR
    };


    firebaseref.child("Live").child("Batsman").child("Batsman2").update(DataToSend, function(error){
        if(error)
        {
            window.alert("Something went wrong ");
        }


    });


        if(Team1 ==batting) {
            //   try {
            firebaseref.child("Scorecard").child(Match).child("Team1Batting").child(DataToSend.Name).update(DataToSend, function (error) {
                if (error) {
                    window.alert("Something went wrong ");
                }

            });
            /*    }
             catch(err)
             { firebaseref.child("Scorecard").child(Match).child("Team1Batting").child(DataToSend.Name).set(DataToSend, function (error) {
             if (error) {
             window.alert("Something went wrong ");
             }

             });


             }
             */
        }
        else
        {
            //try {
            firebaseref.child("Scorecard").child(Match).child("Team2Batting").child(DataToSend.Name).update(DataToSend, function (error) {
                if (error) {
                    window.alert("Something went wrong ");
                }

            });
       /* }
        catch(err){
            firebaseref.child("Scorecard").child(Match).child("Team2Batting").child(DataToSend.Name).set(DataToSend, function (error) {
                if (error) {
                    window.alert("Something went wrong ");
                }

            });

        }
*/
        }




}

function updatebat()
{
   // window.alert("Updatebat");
    updatebat1();
    updatebat2();

}


function updatebowl()
{
    var name = document.getElementById("bowl");
    var  Bowler = name.options[name.selectedIndex].text;

    var runs =document.getElementById("Runs");
    var Runs = runs.value;

    var overs =document.getElementById("Over");
    var Overs = overs.value;

    var wickets = document.getElementById("Wickets");
    var Wickets= wickets.value;

    var maidens = document.getElementById("Maidens");
    var Maidens= maidens.value;

    var economy = document.getElementById("Economy");
    var Economy= EconomyRate(Overs, Runs);

    var DataToSend = {
        Name : Bowler,
        Runs : Runs,
        Overs : Overs,
        Wickets : Wickets,
        Maidens : Maidens,
        Economy : Economy
    };

    firebaseref.child("Live").child("Bowler").update(DataToSend, function(error){
        if(error)
        {
            window.alert("Something went wrong ");
        }

    });


    if(Team1 ==batting) {
        firebaseref.child("Scorecard").child(Match).child("Team2Bowling").child(DataToSend.Name).update(DataToSend, function (error) {
            if (error) {
                window.alert("Something went wrong ");
            }

        });
    }
    else
    {
        firebaseref.child("Scorecard").child(Match).child("Team1Bowling").child(DataToSend.Name).update(DataToSend, function (error) {
            if (error) {
                window.alert("Something went wrong ");
            }

        });

    }



}
function LiveScore()
{
    var runs = document.getElementById("TeamRuns");
    var Runs =runs.value;

    var over = document.getElementById("TeamOver");
    var Over = over.value;

    var wickets = document.getElementById("TeamWickets");
    var Wickets = wickets.value;

    var extras = document.getElementById("TeamExtras");
    var Extras = extras.value;

    var Data = {
        Runs : Runs,
        Overs : Over,
        Wickets : Wickets,
        Extras: Extras

    };

    firebaseref.child("Live").child("Score").update(Data,function(error){
        if(error)
        {
            window.alert("Something went Wrong /n SORRY ;)");
        }


    });

    if(batting == Team1)
    {
        firebaseref.child("Live").child("Team1Score").update(Data);
        firebaseref.child("Scorecard").child(Match).child("Team1Score").update(Data);

    }
    else
    {
        firebaseref.child("Live").child("Team2Score").update(Data);
        firebaseref.child("Scorecard").child(Match).child("Team2Score").update(Data);
    }


}

//Final Navneet anand Dated : 24.11.2017