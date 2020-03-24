package com.sark.android.placement_sit1;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.provider.SyncStateContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.RandomTransitionGenerator;
import com.flaviofaria.kenburnsview.Transition;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.storage.FirebaseStorage;

public class user4 extends AppCompatActivity {
Button userlogin,registerbtn;
EditText useremailet;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference ref;
    FirebaseStorage firebaseStorage;
    int t=0;

    KenBurnsView kenBurnsView;
    private boolean moving=true;

    String arr[]=new String[] {
            "ravikiran.1si15bt011@gmail.com","nanduumi.1si15bt010@gmail.com","akash.1si15bt001@gmail.com","kashish.1si15bt007@gmail.com","hitesh.1si15bt003@gmail.com",
            "sindhoora2016@gmail","ramuindu1997@gmail.com","harshithal187@gmail.com","manikya.1si15bt009@gmail.com","jayashreesbiotech@gmail.com","srujana.1si15bt014@gmail.com",
            "shathakuncha@gmail.com","sushmamus@gmail.com","poojajr07@gmail.com","shruthipk.1si15ch031@gmail.com","anusha27sit@gmail.com","kkavithabc97@gmail.com"
            ,"mohanteja04@gmail.com","gopi krishna.1si15cv040@gmail.com","Ks841496@gmail.com","astikaranoop164@gmail.com","prashant.1si15cv042@gmail.com","mahabalesh05@gmail.com",
            "vishalsingh4010@gmail.com","abhishek.1si15cv003@gmail.com","manuhm22@gmail.com","rashmibckm@gmail.com","karthikm9200@gmail.com","shreyanka.1si15cv056@gmail.com"
            ,"sushmitha.1si15cv062@gmail.com","vardhinid1997@gmail.com","sushmitha1si15cv061@gmail.com","mohammedminhaaj97@gmail.com","navanit.1si14cs069@gmail.com","thanunandeesh@gmail.com",
            "sampritha.lk@gmail.com","channa.1si15cs027@gmail.com","saurabh.1si15cs098@saurabhraghuvanshi28@gmail.com","swetasah.1si15cs122@gmail.com","shank9714@gmail.com",
            "anand1si16cs415@gmail.com","krupanc@gmail.com","Madhunj.1si16cs407@gmail.com","bhaskarak.1si16cs402@gmail.com","nakash2011@gmail.com","shubhamkumar.1si15cs110@gmail.com",
            "supriya.1si15cs119@gmail.com","bhagyajyothi065@gmail.com","monishaln1si15cs062@gmail.com","dimple.1si15cs032@gmail.com","rachhurachan797@gmail.com","vishalvr.1si15cs133@gmail.com",
            "shubham.1si15cs109@gmail.com","vsaanchitha91@gmail.com","abhirudhr96@gmail.com","akshaybhat19.1si15cs038@gmail.com","thrijwalms.1si15cs126@gmail.com","harishkumark.1si16cs405@gmail.com",
            "pavankumar.madineni@gmail.com","sridatta.jamadagni@gmail.com","naveenprasan001@gmail.com","divyanshu.1si15cs036@gmail.com","dileepas.1si16cs403@gmail.com","vinodkumar.1si15cs132@gmail.com",
            "balajid.1si15cs020@gmail.com","hvgagan7@gmail.com","neha.1si15cs065@gmail.com","harshitharrajanna@gmail.com","adarsh3697@gmail.com","sujayvshettar@gmail.com","yashavanthareddyg.1si15cs136@gmail.com",
            "niveditham.1si16cs416@gmail.com","harsh.1si15cs042@gmail.com","akash.1si15cs007@gmail.com","pracheekaushik.1si15cs076@gmail.com","nidhisujana@gmail.com","bhanuswaroop100@gmail.com",
            "pratyush.1si15cs078@gmail.com","vasanthig.1si15cs129@gmail.com","rahulkumar6472@gmail.com","sankalp.1si15cs096@gmail.com","manojmahi722@gmail.com","akhil.1si15cs031@gmail.com","indupriya.1si15cs046@gmail.com",
            "sanchay.verma479@gmail.com","Madhusudantn143@gmail.com","deepika11971@gmail.com","sanjaybssanju07@gmail.com","vaishuvaishnavics@gmail.com","rswapna1997@gmail.com","nidhi.1si15cs066@gmail.com","anjangowda.1si15cs011@gmail.com"
            ,"smanoj.a6@gmail.com","kush.1si15cs052@gmail.com","dinesh.1si15cs033@gmail.com","jamunabe97@gmail.com","akhilkv3697@gmail.com","mudaseer.1SI6CS43@gmail.com",
            "rituraj.1si15cs088@gmail.com","chintupreksha@gmail.com","sarathchandra.1si15cs090@gmail.com","meghanamr.1si15cs058@gmail.com","kssaikavya.1si15cs049@gmail.com","swastishreebhat.1si15cs121@gmail.com","vanish848@gmail.com",
            "nischitharaj.1si15cs069@gmail.com","aakanshasingh.1si15cs145@gmail.com","adarshrao.1si15cs005@gmail.com","rakshita.ravi97@gmail.com",
            "rakshita.ravi97@gmail.com","anushreekp.1si15cs142@gmail.com","alkaaprajeeta.1si15cs144@gmail.com","hithyshi.1si15cs045@gmail.com","manasa.1si15cs055@gmail.com",
            "kumaripallavi.1si15cs143@gmail.com","Sagarmanojsag@gmail.com","rakshitha.1si15cs087@gmail.com","ullascl.1si15cs127@gmail.com","sanjanaks299@gmail.com","ohithsiddu10@gmail.com",
            "navya.1si15cs064@gmail.com","gokulkunigal@gmail.com","akanksha.1si15cs138@gmail.com","mayankagrawal.1si15cs141@gmail.com","shruthikv.1si15cs108@gmail.com","nsamitha97@gmail.com",
            "chinmayeets97.1si15cs028@gmail.com","aditya.1si15cs137@gmail.com","shashankha.2015@gmail.com","bhuvan.1si15cs139@gmail.com",
            "shireeshav.1si15cs103@gmail.com","saurabh.1si15cs099@gmail.com","umeshkulmi11@gmail.com","shankar.abhi97@gmail.com","bhoomimanjesh98@gmail.com","Kirankumar1si16ec411@gmail.com","sanjeet.1si15ec087@gmail.com",
            "shifar3571@gmail.com","skushalgowda1si15ec083@gmail.com","farhan.1si15ec031@gmail.com","adithyaj17@gmail.com","ashwinikh.1si16ec404@gmail.com","Mohan.1si15ec057@gmail.com","preethamksjmp@gmail.com","megha.1si15ec055@gmail.com",
            "sksanjiv77@gmail.com","ujwalmp.1si15ec113@gmail.com","sagarchincholi21@gmail.com","prajwal.1si15ec071@gmail.com","dinu.v240697@gmail.com","apeksha.lost97@gmail.com","gopalkrushna.1si16ec407@gmail.com",
            "Jyotsna.1si15ec042@gmail.com","harsha.1si16ec408@gmail.com","mahesh.1si16ec412@gmail.com","lalithashreev27@gmail.com","ankitha9898@gmail.com","pooja.1si15ec067@gmail.com","Shireeshg.1si15ec095@gmail.com",
            "kulkarniamar97@gmail.com","neethanskhadri@gmail.com","faizal.anwar786@gmail.com","sindhugsg01@gmail.com","charunya.agmv58@gmail.com","nnaveen.1si15ec058@gmail.com",
            "pavanarya.1997@gmail.com","ankitraj.1si15ec013@gmail.com","ranjitha.1si15ec077@gmail.com","poojasupari.1si15ec068@gmail.com","abhishek.1si15ec005@gmail.com","manish.1si15ec053@gmail.com","toufeeque.1si15ec111@gmail.com","Kuldeepr.1si15ec049@gmail.com","priyanka.1si15ec072@gmail.com",
            "rishav.1si15ec078@gmail.com","Suhas.1si15ec105@gmail.com","mubashirali.1si15ec056@gmail.com","gurudathbj.1si15ec034@gmail.com","vinayrajesh137@gmail.com","naveenv.1si15ec059@gmail.com","srinidhipatil.1si15ec103@gmail.com","ravit.1si16ec419@gmail.com","sumana.1si15ec073@gmail.com","karthika.1si15ch016@gmail.com",
            "utkarsh.1si15ec114","raghav.1si15ec047@gmail.com","sachinphogat1@gmail.com","sagarsr.1si15ec086@gmail.com","payalverma.1404@gmail.com","ashutosh.1si15ec018@gmail.com","roneet.1si15ec081@gmail.com","pavanl.1si15ec065@gmail.com","charan.1si15ec022@gmail.com","Kamsalaravitheja.1si15ec045@gmail.com",
            "aditi.1si15ec007@gmail.com","saurabh.1si15ec092@gmail.com","sukanya Gowthami.1si15ec106@gmail.com","mahalakshmi.1si15ec052@gmail.com","saurav.1si15ec093@gmail.com","chandranshu.1si15ec021@gmail.com","swati.1si15ec109@gmail.com","subrahmanya.1si15ec104@gmail.com","lavanya.1si15ec121@gmail.com","rohan.1si15ec079@gmail.com"
            ,"rakesh.1si15ec076@gmail.com","himanshu.1si15ec039@gmail.com","harshithabm13@gmail.com","ashisharya.1si15ec016@gmail.com","ashish.1si15ec017@gmail.com","jyothitn.1si15ec120@gmail.com","poornimah.1si15ec069@gmail.com","prajakta.1si15ec070@gmail.com","harshith.1si15ec037@gmail.com","sachidananda.1si15ec084@gmail.com",
            "sneharamaswamy297@gmail.com","vishalashok.1si15ec116@gmail.com","sushmitha.1si15ec108@gmail.com","nishant.1si15ec061@gmail.com","jhancy.371997@gmail.com","sneha.1si15ec101@gmail.com","upavithra97@gmail.com","akshathabakshu@gmail.com","rahul.1si15ee029@gmail.com","sanjay.1si15ee038@gmail.com","yashaswini1si16ee411","ganikarur12@gmail.com",
            "Kamaljeet.1si15ee013@gmail.com","champaba95@gmail.com","vishal.1si15ee051@gmail.com","Rakeshrajrakhi2@gmail.com","shashank19973@gmail.com","manjushree1si15ee019@gmail.com","sunil.1si15ee045@gmail.com","preranatulasi7@gmail.com","nandan.1si16ee403@gmail.com","Subramanya.1si15ee042@gmail.com",
            "anuraj.1si15ee004@gmail.com","arghyadeep.1si15ee005@gmail.com","sulaiman.1si15ee044@gmail.com","rakshithauc6@gmail.com","Nagashreekr1si15ee021@gmail.com","sujeet.1si15ee043@gmail.com","nihal.navale22@gmail.com","savitha1si16ee409@gmail.com","iqbal.syedasif25@gmail.com","pavaanie@gmail.com","Sanguss360@gmail.com","Pooja.1si15ee026@gmail.com",
            "sindhudhalvoy.1si15ee041@gmail.com","Shubham.1si15ee040@gmail.com","mourya8123@gmail.com","yogesh.1si15ee053@gmail.com","divya.1si15ee009@gmail.com","Pratik.1si15ee027@gmail.com","amul.1si15ee003@gmail.com","pushpaja1si16ee407@gmail.com","rakshit.1si15ee032@gmail.com","Divyashree.1si15ee010@gmail.com",
            "chandana111r@gmail.com","kiranmahil.1si15ee014@gmail.com","maheshwari1si15ee018@gmail.com","chaithra1si15ee007@gmail.com","ramyashreem1si15ee034@gmail.com","sunita.1si15ee046@gmail.com","ullas.1si15ee048@gmail.com","lavanya.g.gowda443@gmail.com","nandishnandu24@gmail.com","saharapatil.1si15ee036@gmail.com",
            "Vishnuvardhan.1si15ee052@gmail.com","sarraf_rahul@yahoo.com","veervikram.1si15ee049@gmail.com","ishika.1si15ee012@gmail.com","Harshith.1si15im013@gmail.com","princeakash.1si15im028@gmail.com","anuachar1421997@gmail.com","pavan.1si15im024@gmail.com","sneha.1si14im035@gmail.com","prasanna.1si15im026@gmail.com","sharanabasavarajb.1si15im035@gmail.com",
            "monishkumar.1si15im021@gmail.com","reddy.1si15im038@gmail.com","akshaysalanki108@gmail.com","apoorva.16im402@gmail.com","apoorva.16im402@gmail.com","Preksha.sdm@gmail.com","pruthvirajv.1si15im030@gmail.com","deepika.1si15im010@gmail.com","abhishekgowda.1si15im002@gmail.com","aagarwal.1si15im001@gmail.com","krupa.1si15im017@gmail.com","rashmi.1si15im033@gmail.com",
            "bhanur1998@gmail.com","Kavyas.1si15im016@gmail.com ","arpitha.1si15im006@gmail.com","hemanths.1si15im015@gmail.com","bhanur1998@gmail.com","Kavyas.1si15im016@gmail.com","arpitha.1si15im006@gmail.com","hemanths.1si15im015@gmail.com","ranjithams97@gmail.com","shubhashini.1si15im036@gmail.com","neethumn.1si15im023@gmail.com","monika.1si15im020@gmail.com","Sakshitumkur@gmail.com",
            "supriyats.1si15im037@gmail.com","mythri.1si15im022@gmail.com","vishruthaps.1si15im039@gmail.com","akshab97@gmail.com","Siddaling.1si15is406@gmail.com","Bmsumanth.1si14is053@gmail.com","ashishtram@gmail.com","Kshama1si15is025@gmail.com","nagashree1si16is403@gmail.com","Kumarisimpi.1si15is027@gmail.com","jeevithasmurthy1998@gmail.com","sushilk1r2@gmail.com","rahul.prksh1@gmail.com",
            "rajathjain.p@gmail.com","sagarsb.1si15is047@gmail.com","somilaskumar.ssk58@gmail.com","varshithabv@gmail.com","azhar1si15is031@gmail.com","subhamankit.1si15is054@gmail.com","raghav9886@gmail.com ",
            "akshathashanmuk97@gmail.com","rakshith.1si15is044@gmail.com","chethan.1si15is015@gmail.com","adarsha.1si14is001@gmail.com","prajwalbmshetty123@gmail.com","pragati.1si15is037@gmail.com","sushmithameda97@gmail.com","abhaypoddargumla@gmail.com","priya24gowda@gmail.com","ayesha.1si15is010@gmail.com","sonu.1si15is051@gmail.com",
            "thejashm220198@gmail.com","rishav.1si15is046@gmail.com","harishr.1si15is017@gmail.com","snehassgowdacoorg@gmail.com ","varshitha.1si15is063@gmail.com",
            "nethrasb14@gmail.com","ashish.1si15is070@gmail.com","nithinshastry@gmail.com","manushreemr1997@gmail.com","navya.umashankar@gmail.com","kamakshi.1si15is021@gmail.com","pruthvimaradhya@gmail.com","ajaymercera97@gmail.com",
            "poojalspdbr97@gmail.com","pradhyumnakr@gmail.com","harshaharshu64@gmail.com","bhargavi10997@gmail.com","sanjanagnayak.1si15is048@gmail.com","Varshithabk.1si15is062@gmail.com","rachanahj.1si15is040@gmail.com","nischithakatta@gmail.com","srinidhi.1si15is053@gmail.com","priya.1si15is011@gmail.com","akash.1si15is003@gmail.com","namratha.sanghvi@gmail.com","bharath.1si15is012@gmail.com","deeksha.1si15ei403@gmail.com",
            "chidambar.1si16ei400@gmail.com","Shashwat.993@gmail.com","atul.1si12it012@gmail.com","dhanush.pkumar95@gmail.com","manojks.1si16ei405@gmail.com","pranjal.1si15ei028@gmail.com","Jyothi.1si16ei402@gmail.com","eleyarajgh98@gmail.com","shwetahosure.1si15ei409@gmail.com","joseph1si16ei404@gmail.com","Roopashree.1SI16EI409@gmail.com","varsha.1si16ei411@gmail.com","meghanamguptha26@gmail.com","pramodpammu7797@gmail.com",
            "atulkumar.1si15ei010@gmail.com","Swathivijaysimha.1si15ei041@gmail.com","prema.1si15ei029@gmail.com","neeraja.1si16ei407@gmail.com","sujeethsubramanya@gmail.com","sneha.1si15ei037@gmail.com","shivdhansingh.1si15ei035@gmail.com","zeba.1si15ei046@gmail.com","usha.1si15ei043@gmail.com","Srilakshmi.1si15ei038@gmail.com","deekrishi@gmail.com","arghya.1si15ei008@gmail.com","meghana.1si15ei018@gmail.com","manzoorpashabe.1si15ei017@gmail.com",
            "meghanashetty1998@gmail.com","siddartha.1si15ei036@gmail.com","kusumashree.1si15ei013@gmail.com","mahesh.1si15ei016@gmail.com","mohit.1si15ei021@gmail.com","madhuri.1si15ei014@gmail.com","syashaswini07@gmail.com","chandana.1si13it008@gmail.com","seema.1si15ei033@gmail.com","prakruthi.1si15ei026@gmail.com","swathi.1si15ei040@gmail.com","mohan.1si15ei020@gmail.com","akshaypadmanabh1@gmail.com","rajath.1si15ei030@gmail.com","varshini.1si15ei044.gmail.com",
            "9ai4kshay@gmail.com","ritesh.1si15ei032@gmail.com","pavan.uttarkar@gmail.com","nischaynishu97@gmail.com","sstevejason@gmail.com","mohankumarsn52@gmail.com","akash.1si15me014@gmail.com",
            "rohankr.1si15me137@gmail.com","rahulbhat171197@gmail.com","Santosh.1si15me149@gmail.com","gagan.1si15me043@gmail.com","suhas.1si15me165@gmail.com","abhilash.1si15me005@gmail.com","sanjupatil.1si15me147@gmail.com","devaraj.16me405@gmail.com","powerpathi123@gmail.com","vinay003singh@gmail.com","mustafa.1si15me089@gmail.com","Naveenmudda22@gmail.com","Arjun.1si15me025@gmail.com",
            "rtelang9999@gmail.com","zabimohammed18@gmail.com","musthafeez.1si15me094@gmail.com","mohithnkumar.1si15me092@gmail.com","sahasbagali141@gmail.com","mouneshdukandor1997@gmail.com","chakrianashk18@gmail.com","tharun.1si15me172@gmail.com","shreyas.1si15me156@gmail.com","ritik.1si15me136@gmail.com",
            "sameeralilathiyar.1si16me426@gmail.com","mohamedsabeer33@gmail.com","Narayanasetti.1Si15me097@gmail.com","sheetalmkalasur.1si15me155@gmail.com","harishbabu589@gmail.com","yogeshs.1si15me193@gmail.com","thilakmandy@gmail.com","arjunhiremath7797@gmail.com","amith.1si15me016@gmail.com","kumarswamyhs.1si15me069@gmail.com","vadiraj.1si16me441@gmail.com","deepakborana333@gmail.com","Sagarsb275@gmail.com","tejums.1si16me437@gmail.com","praveenvishnupriya@gmail.com","pratik.1si15me119@gmail.com",
            "raghavraykar45@gmail.com","nag.mohith@gmail.com","yashas.1si15me189@gmail.com","sanjayrathod.1si16me427@gmail.com","maruthidr.1si15me086@gmail.com","crakashvk@gmail.com","arvind.1si15me028@gmail.com","prasad.1si15me115@gmail.com",
            "vishwanathvkolli@gmail.com","nikhilkumar.1si15me102@gmail.com","subham.1si15me163@gmail.com","sankarsh.1si15me148@gmail.com","marnurramesh@gmail.com","kumarshivam.1si15me068@gmail.com","chittaranjankumar160@gmail.com","annukr.1si15me021@gmail.com","nikhil.1si15me033@gmail.com","raghubandigani.1si15me126@gmail.com","vireshtlamani.1si15me184@gmail.com","vedant.1si15me179@gmail.com", "shreekant.1si16me434@gmail.com","anshuman.1si15me022@gmail.com","daulat1si16me404@gmail. com",
            "pratyayamrit.1si15me120@gmail.com","lmanu.1si15me074@gmail.com","omkar.1SI15ME107@gmail.com","rakeshnpatil123@gmail.com","yashawanthsagar@gmail.com","Chandanv.1si16me402@gmail.com","manojn.1si15me084@gmail.com","Kalleshs009@gmail.com","sahil.1si15me142@gmail.com",
            "manigowdas10@gmail.com","kspranav.1si15me114@gmail.com","gagan.1si15me042@gmail.com","snpavan666@gmail.com","thejasm.1si15me173@gmail.com","sandeepg.1si15me144@gmail.com","prajwalm.1si15me112@gmail.com","erannak12@gmail.com","Vinayakdevani@gmail.com ","manjunathabs.1si15me079@gmail.com","prabhakarkamate1@gmail.com","manoj.1si15me082@gmail.com",
            "hemant1si15me058@gmail.com","manoj.1si16me417@gmail.com","mohiths.1si16me419@gmail.com","Mohiths.1si16me419@gmail.com","Kartikkulkarni705@gmail.com","shubhendu.1si15me159@gmail.com","khenedvineet@gmail.com","Ranganathhg.1si15me133@gmail.com","www.santoshp1si16me430@gmail.com","Vishnu04sangshetty@gmail.com ","gnanendrakumar1997@gmail.com","manojlakshmipathi02@gmail.com",
            "aashrithaskiran@gmail.com","harishcp.1si16me408@gmail.com","nishchalkumar.1si15me105@gmail.com","pboodagoli@gmail.com","abdalpasha07@gmail.com","kushi17007@gmail.com","gagangupta1868@gmail.com","ankith.1si15me020@gmail.com","dineshreddy.1si15me164@gmail.com","sukanya.1si15me168@gmail.com","shubham.1si15me157@gmail.com","anupkumar.1si15me023@gmail.com","Punithgs.1si15me124@gmail.com","naveen.1si15me099@gmail.com","gnnaveen4@gmail.com","Anvithkumar25997@gmail.com","shubhamkumar.1si15me158@gmail.com","sumukhacs1234@gmail.com","gaurav1si15me045@gmail.com"
            ,"shashanksp.1si16me432@gmail.com","prasannacpatil@gmail.com","vnaveenreddy0801@gmail.com","Yatharth.1si15me191@gmail.com","kshitijkesarwani@gmail.com","abhyudaybharadwaj 21@gmail.com","gauthamam.2307@gmail.com","nishant2196@gmail.com","rakshith.hr02@gmail.com","sarfarazakhtar.1si15me151@gmail.com","roshankm594@gmail.com","likhithabc9497@gmail.com","manjunathkp12@gmaip.com","prashant.1si15me117@gmail.com","Chanchala.1si14te013@gmail.com","truptinayak1828@gmail.com",
            "pavithrald1998@gmail.com","kunalnew9@gmail.com","apoorva08sep@gmail.com","harish.1si15te017@gmail.com","bsvidya58@gmail.com","sangeetha.s31797@gmail.com","sushmitha290497@gmail.com","sowmyasowmya9898@gmail.com","girishbr1998@gmail.com",
            "rachana.1si15te042@gmail.com","suprithatn@gmail.com","ankit34567@gmail.com","Ameenamu188@gmail.com","tanisha.1si15te052@gmail.com","pooja.1si15te036@gmail.com","hniveditha22@gmail.com","harshithakr.1si15te019@gmail.com",
            "rahulkn.1si15te043@gmail.com","vandanatm1997@gmail.com","chethan.1si15te014@gmail.com","pooja.1si15te035@gmail.com","srinidhi.1si15te048@gmail.com","kavyashree.1si15te024@gmail.com","parvathik.1si15te034@gmail.com","kavana.k46@gmail.com",
            "kaieshaafreen.1si15te022@gmail.com","doolpetmadhura000@gmail.com","chandanhr.1si15te013@gmail.com","lakshmishree.1si15te026@gmail.com","maithrivaidyag.1si15te029@gmail.com","hitha.1si15te020@gmail.com","chaitu.sagara@gmail.com","namrathap1si15te030@gmail.com","nidhi.1si15te032@gmail.com",
            "sudha.h1997@gmail.com"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user4);

        firebaseDatabase= FirebaseDatabase.getInstance();
        firebaseStorage= FirebaseStorage.getInstance();
        ref=firebaseDatabase.getReference("Userlogin");
        useremailet=findViewById(R.id.editText);
        kenBurnsView=(KenBurnsView)findViewById(R.id.image);
        AccelerateDecelerateInterpolator ACCELERATE_DECELERATE = new AccelerateDecelerateInterpolator();
        RandomTransitionGenerator generator = new RandomTransitionGenerator(10000, ACCELERATE_DECELERATE);
        kenBurnsView.setTransitionGenerator(generator);

        kenBurnsView.setTransitionListener(onTransittionListener());

        kenBurnsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(moving){
                    kenBurnsView.pause();
                    moving=false;
                }
                else{
                    kenBurnsView.resume();;
                    moving=true;
                }
            }
        });

       /* if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationManager mNotificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(Constants.CHANNEL_ID, Constants.CHANNEL_NAME, importance);
            mChannel.setDescription(Constants.CHANNEL_DESCRIPTION);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mNotificationManager.createNotificationChannel(mChannel);
        }*/

        userlogin = (Button) findViewById(R.id.userloginbtn);
        userlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String useremail=useremailet.getText().toString();
                if (useremail.isEmpty())
                    Toast.makeText(user4.this, "You have not entered email-id!", Toast.LENGTH_SHORT).show();
                else
                    userlogin();
            }
        });

        registerbtn=findViewById(R.id.register);
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent=new Intent(user4.this,newuser123.class);
                Toast.makeText(user4.this,"Register your email address and wait for verification",Toast.LENGTH_LONG).show();
                startActivity(browserIntent);
            }
        });


    }
    private KenBurnsView.TransitionListener onTransittionListener() {
        return new KenBurnsView.TransitionListener() {

            @Override
            public void onTransitionStart(Transition transition) {

                // Toast.makeText(MainActivity.this, "start", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                // Toast.makeText(MainActivity.this, "end", Toast.LENGTH_SHORT).show();
            }
        };
    }
    private void userlogin() {
        final String uemail = useremailet.getText().toString();

        if(TextUtils.isEmpty(uemail)){
            Toast.makeText(user4.this,"User-email field is empty",Toast.LENGTH_SHORT).show();

        }
        else {

                int count=0;
                for (int i = 0; i < arr.length; i++) {
                    if (uemail.equalsIgnoreCase(arr[i])) {

                        //FirebaseMessaging.getInstance().subscribeToTopic("companies");
                        Intent j = new Intent(user4.this, Retrieveuser.class);
                        startActivity(j);
                        count++;
                        break;
                    }
                }
                if(count==0){
                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Iterable<DataSnapshot> csnap = dataSnapshot.getChildren();

                            for (DataSnapshot user : csnap) {
                                User u = user.getValue(User.class);

                                if (uemail.equalsIgnoreCase(u.getEmailid())) {
                                    //FirebaseMessaging.getInstance().subscribeToTopic("companies");
                                    Intent j = new Intent(user4.this, Retrieveuser.class);
                                    startActivity(j);
                                    t++;
                                    break;

                                }
                            }
                            if(t==0)
                            {
                                final AlertDialog.Builder builder=new AlertDialog.Builder(user4.this);
                                builder.setMessage("This email-address is not Registered. You may request us to verify and register it.\n\nDo you want to register?");
                                builder.setCancelable(true);

                                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent i=new Intent(user4.this,MainActivity.class);
                                        startActivity(i);
                                    }
                                });

                                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent i=new Intent(user4.this,newuser123.class);
                                        startActivity(i);
                                    }
                                });
                                AlertDialog alertdialog=builder.create();
                                alertdialog.show();
                            }
                            else{

                                Toast.makeText(user4.this, "Welcome "+uemail+" to SIT Placement app", Toast.LENGTH_SHORT).show();

                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
            }
        }

}
