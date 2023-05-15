package com.sotdogn.bldogkepd.activties;

import static com.luciada.modids.MyAdZOne.Both_video_show;
import static com.luciada.modids.MyAdZOne.False_Video_Show;
import static com.luciada.modids.MyAdZOne.Privacy_Policy;
import static com.luciada.modids.MyAdZOne.True_Video_Show;
import static com.luciada.modids.MyAdZOne.app_DeveloperOption_Check_Mode;
import static com.luciada.modids.MyAdZOne.app_failData;
import static com.luciada.modids.MyAdZOne.maxvidcount;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sotdogn.bldogkepd.models.MoreApp;
import com.sotdogn.bldogkepd.utility.Connectivity;
import com.sotdogn.bldogkepd.utility.DevModeOptionCheck;
import com.luciada.modids.AESSUtils;
import com.luciada.modids.MyAdZOne;
import com.sotdogn.bldogkepd.R;
import com.sotdogn.bldogkepd.unitlity.TestActivity_1;
import com.sotdogn.bldogkepd.unitlity.TestActivity_10;
import com.sotdogn.bldogkepd.unitlity.TestActivity_2;
import com.sotdogn.bldogkepd.unitlity.TestActivity_3;
import com.sotdogn.bldogkepd.unitlity.TestActivity_4;
import com.sotdogn.bldogkepd.unitlity.TestActivity_5;
import com.sotdogn.bldogkepd.unitlity.TestActivity_6;
import com.sotdogn.bldogkepd.unitlity.TestActivity_7;
import com.sotdogn.bldogkepd.unitlity.TestActivity_8;
import com.sotdogn.bldogkepd.unitlity.TestActivity_9;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainHomeActivity extends AppCompatActivity {

    private List<MoreApp> moreAppList = new ArrayList<>();

    public static int counter = 0;
    public static int crandomcounter = 0;

    LinearLayout call_timer_layout;
    TextView timer;
    CountDownTimer myCountdownTimer;

    public static int min = 1;
    public static int max = 11;
    public static int randomposition = 0;
    public static int LP = 0;
    public static SharedPreferences sh;

    GradientDrawable gd1, gd2, gd3, gd4, gd5;

    String app_failDatads;


    private String mColors[] = {
            "#ffafbd",
            "#ffc3a0",
            "#c25975",
            "#2193b0",
            "#6dd5ed",
            "#cc2b5e",
            "#7d669e",
            "#753a88",
            "#ee9ca7",
            "#ffdde1",
            "#42275a",
            "#734b6d",
            "#bdc3c7",
            "#2c3e50",
            "#de6262",
            "#ffb88c",
            "#06beb6",
            "#48b1bf",
            "#eb3349",
            "#f45c43",
            "#dd5e89",
            "#f7bb97",
            "#56ab2f",
            "#a8e063",
            "#614385",
            "#516395",
            "#eecda3",
            "#ef629f",
            "#eacda3",
            "#d6ae7b",
            "#02aab0",
            "#00cdac",
            "#d66d75",
            "#e29587",
            "#000428",
            "#004e92",
            "#ddd6f3",
            "#faaca8",
            "#7b4397",
            "#dc2430",
            "#43cea2",
            "#185a9d",
            "#ba5370",
            "#ffaf7b",
            "#4ca1af",
            "#c4e0e5",
            "#ff5f6d",
            "#ffc371",
            "#36d1dc",
            "#5b86e5",
            "#c33764",
            "#1d2671",
            "#141e30",
            "#243b55",
            "#ff7e5f",
            "#feb47b",
            "#ed4264",
            "#ffedbc",
            "#2b5876",
            "#4e4376",
            "#ff9966",
            "#ff5e62",
            "#aa076b",
            "#61045f",
            "#FE90AF",
            "#11998E",
            "#38EF7D",
            "#FF5F6D",
            "#FFC371",
            "#FF512F"
    };

    private String mColors2[] = {
            "#f3a183",
            "#ffd89b",
            "#19547b",
            "#3a1c71",
            "#d76d77",
            "#ffaf7b",
            "#4ca1af",
            "#c4e0e5",
            "#ff5f6d",
            "#ffc371",
            "#36d1dc",
            "#5b86e5",
            "#c33764",
            "#1d2671",
            "#141e30",
            "#243b55",
            "#ff7e5f",
            "#feb47b",
            "#ed4264",
            "#ffedbc",
            "#2b5876",
            "#4e4376",
            "#ff9966",
            "#ff5e62",
            "#aa076b",
            "#61045f",
            "#FDFCFB",
            "#E2D1C3",
            "#667EEA",
            "#764BA2",
            "#C2E9FB",
            "#A1C4FD",
            "#FCB69F",
            "#FFECD2",
            "#537895",
            "#09203F",
            "#868F96",
            "#596164",
            "#93A5CF",
            "#E4EfE9",
            "#C33764",
            "#1D2671",
            "#A9F1DF",
            "#FFBBBB",
            "#4E65FF",
            "#92EFFD",
            "#BFF098",
            "#6FD6FF",
            "#FF61D2",
            "#FE9090",
            "#D8B5FF",
            "#1EAE98",
            "#EA8D8D",
            "#A890FE",
            "#C6EA8D",
            "#FE90AF",
            "#11998E",
            "#38EF7D",
            "#FF5F6D",
            "#FFC371",
            "#FF512F"
    };


    private String mColors3[] = {
            "#ffafbd",
            "#ffc3a0",
            "#c25975",
            "#2193b0",
            "#6dd5ed",
            "#cc2b5e",
            "#7d669e",
            "#753a88",
            "#ee9ca7",
            "#ffdde1",
            "#42275a",
            "#734b6d",
            "#bdc3c7",
            "#2c3e50",
            "#de6262",
            "#ffb88c",
            "#06beb6",
            "#48b1bf",
            "#eb3349",
            "#f45c43",
            "#dd5e89",
            "#f7bb97",
            "#56ab2f",
            "#a8e063",
            "#614385",
            "#516395",
            "#eecda3",
            "#ef629f",
            "#eacda3",
            "#d6ae7b",
            "#02aab0",
            "#00cdac",
            "#d66d75",
            "#e29587",
    };


    private String mColors4[] = {
            "#ffb88c",
            "#06beb6",
            "#48b1bf",
            "#eb3349",
            "#f45c43",
            "#dd5e89",
            "#f7bb97",
            "#56ab2f",
            "#a8e063",
            "#614385",
            "#516395",
            "#eecda3",
            "#ef629f",
            "#eacda3",
            "#d6ae7b",
            "#02aab0",
            "#00cdac",
            "#d66d75",
            "#e29587",
            "#000428",
            "#004e92",
            "#ddd6f3",
            "#faaca8",
            "#7b4397",
            "#dc2430",
            "#43cea2",
            "#185a9d",
            "#ba5370",
            "#f4e2d8",
            "#ff512f",
            "#dd2476",
            "#4568dc",
            "#b06ab3",
            "#ec6f66",
            "#f3a183",
            "#ffd89b",
            "#19547b",
            "#3a1c71",
            "#d76d77",
            "#ffaf7b",
            "#4ca1af",
            "#c4e0e5",
            "#ff5f6d",
            "#ffc371",
            "#36d1dc",
            "#5b86e5",
            "#c33764",
            "#1d2671",
            "#141e30",
            "#243b55",
            "#ff7e5f",
            "#feb47b",
            "#ed4264",
            "#ffedbc",
            "#2b5876",
            "#4e4376",
            "#ff9966",
            "#ff5e62",
            "#aa076b",
            "#61045f",
            "#FDFCFB",
            "#E2D1C3",
            "#667EEA",
            "#764BA2",
            "#C2E9FB",
    };


    private String countrylist[] = {

            "Afghanistan",
            "Albania",
            "Algeria",
            "Andorra",
            "Angola",
            "Antigua",
            "Argentina",
            "Armenia",
            "Australia",
            "Austria",
            "Azerbaijan",
            "Bahamas",
            "Bahrain",
            "Bangladesh",
            "Barbados",
            "Belarus",
            "Belgium",
            "Belize",
            "Benin",
            "Bhutan",
            "Bolivia",
            "Bosnia",
            "Botswana",
            "Brazil",
            "Brunei",
            "Bulgaria",
            "Burkina",
            "Burundi",
            "Cambodia",
            "Cameroon",
            "Canada",
            "Cape Verde",
            "Chad",
            "Chile",
            "China",
            "Colombia",
            "Comoros",
            "Congo",
            "Congo",
            "Croatia",
            "Cuba",
            "Cyprus",
            "Czech",
            "Denmark",
            "Djibouti",
            "Dominica",
            "Dominican",
            "East Timor",
            "Ecuador",
            "Egypt",
            "Equatorial",
            "Eritrea",
            "Estonia",
            "Ethiopia",
            "Fiji",
            "Finland",
            "France",
            "Gabon",
            "Gambia",
            "Georgia",
            "Germany",
            "Ghana",
            "Greece",
            "Grenada",
            "Guatemala",
            "Guinea",
            "Guinea",
            "Guyana",
            "Haiti",
            "Honduras",
            "Hungary",
            "Iceland",
            "India",
            "Indonesia",
            "Iran",
            "Iraq",
            "Ireland",
            "Israel",
            "Italy",
            "Ivory",
            "Jamaica",
            "Japan",
            "Jordan",
            "Kazakhstan",
            "Kenya",
            "Kiribati",
            "Korea N",
            "Korea S",
            "Kosovo",
            "Kuwait",
            "Kyrgyzstan",
            "Laos",
            "Latvia",
            "Lebanon",
            "Lesotho",
            "Liberia",
            "Libya",
            "Lithuania",
            "Luxembourg",
            "Macedonia",
            "Madagascar",
            "Malawi",
            "Malaysia",
            "Maldives",
            "Mali",
            "Malta",
            "Marshall",
            "Mauritania",
            "Mauritius",
            "Mexico",
            "Micronesia",
            "Moldova",
            "Monaco",
            "Mongolia",
            "Montenegro",
            "Morocco",
            "Mozambique",
            "Myanmar",
            "Namibia",
            "Nauru",
            "Nepal",
            "Netherlands",
            "NewZealand",
            "Nicaragua",
            "Niger",
            "Nigeria",
            "Norway",
            "Oman",
            "Pakistan",
            "Palau",
            "Panama",
            "Guinea",
            "Paraguay",
            "Peru",
            "Philippines",
            "Poland",
            "Portugal",
            "Qatar",
            "Romania",
            "Russian",
            "Rwanda",
            "St Kitts",
            "St Lucia",
            "Saint",
            "Samoa",
            "San Marino",
            "Sao Tome",
            "Saudi Arabia",
            "Senegal",
            "Serbia",
            "Seychelles",
            "Sierra",
            "Singapore",
            "Slovakia",
            "Slovenia",
            "Solomon",
            "Somalia",
            "S Africa",
            "S Sudan",
            "Spain",
            "Sri Lanka",
            "Sudan",
            "Suriname",
            "Swaziland",
            "Sweden",
            "Switzerland",
            "Syria",
            "Taiwan",
            "Tajikistan",
            "Tanzania",
            "Thailand",
            "Togo",
            "Tonga",
            "Trinidad",
            "Tunisia",
            "Turkey",
            "Turkmenistan",
            "Tuvalu",
            "Uganda",
            "Ukraine",
            "UAE",
            "UK",
            "US",
            "Uruguay",
            "Uzbekistan",
            "Vanuatu",
            "Vatican",
            "Venezuela",
            "Vietnam",
            "Yemen",
            "Zambia",
            "Zimbabwe"
    };


    public int[] getGcolors() {
        String color = "";
        String color2 = "";
        String color3 = "";
        String color4 = "";

        Random randomGenerator = new Random();
        int randomNumber = randomGenerator.nextInt(mColors.length);
        int randomNumber2 = randomGenerator.nextInt(mColors2.length);
        int randomNumber3 = randomGenerator.nextInt(mColors3.length);
        int randomNumber4 = randomGenerator.nextInt(mColors4.length);

        color = mColors[randomNumber];
        color2 = mColors2[randomNumber2];
        color3 = mColors3[randomNumber3];
        color4 = mColors4[randomNumber4];

        int colorAsInt = Color.parseColor(color);
        int colorAsInt2 = Color.parseColor(color2);
        int colorAsInt3 = Color.parseColor(color3);
        int colorAsInt4 = Color.parseColor(color4);

        return new int[]{colorAsInt, colorAsInt2, colorAsInt3, colorAsInt4};
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        if (app_DeveloperOption_Check_Mode.equalsIgnoreCase("true")) {
            DevModeOptionCheck.getInstance(this).DevMode_Check();
        }

        sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        LP = sh.getInt("layout_position", 0);
        randomposition = LP;

        if (randomposition == 0) {
            randomposition = new Random().nextInt((max - min) + 1) + min;
            SharedPreferences.Editor editor = sh.edit();
            editor.putInt("layout_position", randomposition);
            editor.apply();
            LP = randomposition;
        }

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAGS_CHANGED);
        window.setStatusBarColor(Color.WHITE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        }

        call_timer_layout = findViewById(R.id.call_timer_layout);
        timer = findViewById(R.id.timer);

        int[] gColors = getGcolors();
        gd1 = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                new int[]{gColors[0], gColors[1]});
        gd1.setCornerRadius(0f);

        int[] gColors2 = getGcolors();
        gd2 = new GradientDrawable(
                GradientDrawable.Orientation.LEFT_RIGHT,
                new int[]{gColors2[0], gColors2[1]});
        gd2.setCornerRadius(20f);

        int[] gColors3 = getGcolors();
        gd3 = new GradientDrawable(
                GradientDrawable.Orientation.RIGHT_LEFT,
                new int[]{gColors3[0], gColors3[1]});
        gd3.setCornerRadius(20f);

        int[] gColors4 = getGcolors();
        gd4 = new GradientDrawable(
                GradientDrawable.Orientation.BOTTOM_TOP,
                new int[]{gColors4[0], gColors4[1]});
        gd4.setCornerRadius(20f);

        int[] gColors5 = getGcolors();
        gd5 = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                new int[]{gColors5[0], gColors5[1]});
        gd5.setCornerRadius(20f);


        findViewById(R.id.lnrsbg).setBackground(gd1);

        findViewById(R.id.btn_click).setBackground(gd2);
        findViewById(R.id.btn_click2).setBackground(gd3);
        findViewById(R.id.btn_click3).setBackground(gd4);
        findViewById(R.id.btn_click4).setBackground(gd5);

        String[] array = countrylist;
        String randomStr = array[new Random().nextInt(array.length)];

        String[] array2 = countrylist;
        String randomStr2 = array2[new Random().nextInt(array.length)];

        String[] array3 = countrylist;
        String randomStr3 = array3[new Random().nextInt(array.length)];

        String[] array4 = countrylist;
        String randomStr4 = array4[new Random().nextInt(array.length)];

        final int cmin = 1;
        final int cmax = 5;
        final int radmserver1 = new Random().nextInt((cmax - cmin) + 1) + cmin;


        if (radmserver1 == 1) {
            findViewById(R.id.btn_click).setVisibility(View.VISIBLE);
        } else if (radmserver1 == 2) {
            findViewById(R.id.btn_click).setVisibility(View.VISIBLE);
            findViewById(R.id.btn_click2).setVisibility(View.VISIBLE);

        } else if (radmserver1 == 3) {
            findViewById(R.id.btn_click).setVisibility(View.VISIBLE);
            findViewById(R.id.btn_click2).setVisibility(View.VISIBLE);
            findViewById(R.id.btn_click3).setVisibility(View.VISIBLE);

        } else if (radmserver1 == 4) {
            findViewById(R.id.btn_click).setVisibility(View.VISIBLE);
            findViewById(R.id.btn_click2).setVisibility(View.VISIBLE);
            findViewById(R.id.btn_click3).setVisibility(View.VISIBLE);
            findViewById(R.id.btn_click4).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.btn_click2).setVisibility(View.VISIBLE);
            findViewById(R.id.btn_click3).setVisibility(View.VISIBLE);
        }

        TextView server1 = findViewById(R.id.server1);
        server1.setText(randomStr + " Server");

        TextView server2 = findViewById(R.id.server2);
        server2.setText(randomStr2 + " Server");

        TextView server3 = findViewById(R.id.server3);
        server3.setText(randomStr3 + " Server");

        TextView server4 = findViewById(R.id.server4);
        server4.setText(randomStr4 + " Server");

        findViewById(R.id.privacypolicy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Privacy_Policy.contains("blogspot.com")) {
                    startActivity(new Intent("android.intent.action.VIEW").setData(Uri.parse(Privacy_Policy)));
                } else {
                    Intent intent = new Intent(MainHomeActivity.this, PravacyPolicyActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });


        Intent intent = getIntent();
        if (intent.getBooleanExtra("rate", false)) {

            findViewById(R.id.btn_click).setVisibility(View.GONE);
            findViewById(R.id.btn_click2).setVisibility(View.GONE);
            findViewById(R.id.txtConnect).setVisibility(View.GONE);
            call_timer_layout.setVisibility(View.VISIBLE);


            SharedPreferences prefs = getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE);
            Boolean rate_state = prefs.getBoolean("rate_state", false);

            if (rate_state == false) {
                Rate();
            } else {
                myCountdownTimer = new CountDownTimer(7000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        timer.setText("00:0" + millisUntilFinished / 1000);
                    }

                    public void onFinish() {
                        myCountdownTimer.cancel();
                        findViewById(R.id.btn_click).setVisibility(View.VISIBLE);
                        findViewById(R.id.btn_click2).setVisibility(View.VISIBLE);
                        findViewById(R.id.txtConnect).setVisibility(View.VISIBLE);
                        call_timer_layout.setVisibility(View.GONE);
                    }
                }.start();

                myCountdownTimer.start();
            }
        }


        goToMain();

        counter = sh.getInt("counter", 0);
        crandomcounter = sh.getInt("crandomcounter", 0);

        MyAdZOne.getInstance(this).showBanner(findViewById(R.id.banner_container));
        MyAdZOne.getInstance(this).onlyCustInterstitialCallAD(this);
        MyAdZOne.getInstance(this).ads_NativeCall(findViewById(R.id.native_container));

        findViewById(R.id.backs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        findViewById(R.id.btn_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyAdZOne.getInstance(MainHomeActivity.this).showInterstitialAd(MainHomeActivity.this, new MyAdZOne.MyCallback() {
                    public void callbackCall() {
                        Next_Activity();
                    }
                }, MyAdZOne.app_MainClickCntSwAd);
            }
        });

        findViewById(R.id.btn_click2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyAdZOne.getInstance(MainHomeActivity.this).showInterstitialAd(MainHomeActivity.this, new MyAdZOne.MyCallback() {
                    public void callbackCall() {
                        Next_Activity();
                    }
                }, MyAdZOne.app_MainClickCntSwAd);
            }
        });

        findViewById(R.id.btn_click3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyAdZOne.getInstance(MainHomeActivity.this).showInterstitialAd(MainHomeActivity.this, new MyAdZOne.MyCallback() {
                    public void callbackCall() {
                        Next_Activity();
                    }
                }, MyAdZOne.app_MainClickCntSwAd);
            }
        });


        findViewById(R.id.btn_click4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyAdZOne.getInstance(MainHomeActivity.this).showInterstitialAd(MainHomeActivity.this, new MyAdZOne.MyCallback() {
                    public void callbackCall() {
                        Next_Activity();
                    }
                }, MyAdZOne.app_MainClickCntSwAd);
            }
        });


        getListApps();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyAdZOne.getInstance(this).showBanner(findViewById(R.id.banner_container));
        MyAdZOne.getInstance(this).ads_NativeCall(findViewById(R.id.native_container));
    }

    public void Next_Activity() {
        if (False_Video_Show.equals("true")) {
            final int min = 1;
            final int max = maxvidcount;
            final int randomposition = new Random().nextInt((max - min) + 1) + min;
            Intent countrylist = new Intent(MainHomeActivity.this, VideoPlayerActivity.class);
            countrylist.putExtra("title", (moreAppList.get(randomposition).getUrl()));
            startActivity(countrylist);
            finish();
        } else if (Both_video_show.equals("true")) {
            counter++;
            if (crandomcounter == 0) {
                final int cmin = 3;
                final int cmax = 8;
                crandomcounter = new Random().nextInt((cmax - cmin) + 1) + cmin;
                SharedPreferences.Editor Editor = sh.edit();
                Editor.putInt("crandomcounter", crandomcounter);
                Editor.putInt("counter", counter);
                Editor.apply();
                Test_Activity_Lyout(MainHomeActivity.this, LP);

            } else {
                if (crandomcounter == counter) {
                    crandomcounter = 0;
                    counter = 0;
                    SharedPreferences.Editor Editor = sh.edit();
                    Editor.putInt("counter", counter);
                    Editor.putInt("crandomcounter", crandomcounter);
                    Editor.apply();
                    final int min = 1;
                    final int max = maxvidcount;
                    final int randomposition = new Random().nextInt((max - min) + 1) + min;
                    Intent countrylist = new Intent(MainHomeActivity.this, VideoPlayerActivity.class);
                    countrylist.putExtra("title", (moreAppList.get(randomposition).getUrl()));
                    startActivity(countrylist);
                    finish();
                } else {
                    SharedPreferences.Editor Editor = sh.edit();
                    Editor.putInt("counter", counter);
                    Editor.apply();
                    Test_Activity_Lyout(MainHomeActivity.this, LP);
                }
            }


        } else if (True_Video_Show.equals("true")) {
            Test_Activity_Lyout(MainHomeActivity.this, 1);
        } else {
            Test_Activity_Lyout(MainHomeActivity.this, LP);
        }
    }

    public static void Test_Activity_Lyout(Activity activity, int position) {
        switch (position) {
            case 1:
                Intent i1 = new Intent(activity, TestActivity_1.class);
                activity.startActivity(i1);
                activity.finish();
                break;

            case 2:
                Intent i2 = new Intent(activity, TestActivity_2.class);
                activity.startActivity(i2);
                activity.finish();
                break;

            case 3:
                Intent i3 = new Intent(activity, TestActivity_3.class);
                activity.startActivity(i3);
                activity.finish();
                break;

            case 4:
                Intent i4 = new Intent(activity, TestActivity_4.class);
                activity.startActivity(i4);
                activity.finish();
                break;

            case 5:
                Intent i5 = new Intent(activity, TestActivity_5.class);
                activity.startActivity(i5);
                activity.finish();
                break;

            case 6:
                Intent i6 = new Intent(activity, TestActivity_6.class);
                activity.startActivity(i6);
                activity.finish();
                break;

            case 7:
                Intent i7 = new Intent(activity, TestActivity_7.class);
                activity.startActivity(i7);
                activity.finish();
                break;

            case 8:
                Intent i8 = new Intent(activity, TestActivity_8.class);
                activity.startActivity(i8);
                activity.finish();
                break;

            case 9:
                Intent i9 = new Intent(activity, TestActivity_9.class);
                activity.startActivity(i9);
                activity.finish();
                break;

            case 10:
                Intent i10 = new Intent(activity, TestActivity_10.class);
                activity.startActivity(i10);
                activity.finish();
                break;

            default:
                Intent i0 = new Intent(activity, TestActivity_1.class);
                activity.startActivity(i0);
                activity.finish();
                break;
        }

    }


    private void getListApps() {
        if (Connectivity.isConnected(this)) {
            try {
                app_failDatads = AESSUtils.Logd(app_failData);
            } catch (Exception e) {
                e.printStackTrace();
            }
            RequestQueue queue = Volley.newRequestQueue(this);
            StringRequest stringRequest = new StringRequest(Request.Method.GET, app_failDatads, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("message");
                        int size1 = jsonArray.length();
                        for (int i = 0; i < size1; i++) {
                            JSONObject j1 = (JSONObject) jsonArray.get(i);
                            MoreApp categoryDetail = new MoreApp();
                            categoryDetail.setName(j1.getString("id"));
                            categoryDetail.setUrl(j1.getString("link"));
                            moreAppList.add(categoryDetail);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Toast.makeText(MainHomeActivity.this, getString(R.string.err_somethingwentwrong), Toast.LENGTH_LONG).show();
                }
            });

            stringRequest.setRetryPolicy(new DefaultRetryPolicy(8000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            queue.add(stringRequest);

        } else {
            ConnectionEroor();
        }

    }

    public void ConnectionEroor() {
        android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(this).create();
        alertDialog.setTitle("Oops!!! Connection Error!");
        alertDialog.setMessage("Please check your internet connection");
        alertDialog.setIcon(R.drawable.ic_warning);
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        alertDialog.show();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void AppPermissions() {
        if (!allPermissions() || !system_sto() || !statePermissions() || !CAMERA() || !CHANGE_NETWORK_STATE() || !MODIFY_AUDIO_SETTINGS() || !RECORD_AUDIO() || !BLUETOOTH() || !CAPTURE_VIDEO_OUTPUT()) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.CAMERA", "android.permission.SYSTEM_ALERT_WINDOW", "android.permission.CHANGE_NETWORK_STATE", "android.permission.MODIFY_AUDIO_SETTINGS", "android.permission.RECORD_AUDIO", "android.permission.BLUETOOTH", "android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE", "android.permission.CAPTURE_VIDEO_OUTPUT"}, 1);
        }
    }

    private boolean allPermissions() {
        return ContextCompat.checkSelfPermission(MainHomeActivity.this, "android.permission.INTERNET") == 0;
    }

    private boolean statePermissions() {
        return ContextCompat.checkSelfPermission(MainHomeActivity.this, "android.permission.ACCESS_NETWORK_STATE") == 0;
    }

    private boolean CAMERA() {
        return ContextCompat.checkSelfPermission(MainHomeActivity.this, "android.permission.CAMERA") == 0;
    }

    private boolean CHANGE_NETWORK_STATE() {
        return ContextCompat.checkSelfPermission(MainHomeActivity.this, "android.permission.CHANGE_NETWORK_STATE") == 0;
    }

    private boolean MODIFY_AUDIO_SETTINGS() {
        return ContextCompat.checkSelfPermission(MainHomeActivity.this, "android.permission.MODIFY_AUDIO_SETTINGS") == 0;
    }

    private boolean RECORD_AUDIO() {
        return ContextCompat.checkSelfPermission(MainHomeActivity.this, "android.permission.RECORD_AUDIO") == 0;
    }

    private boolean BLUETOOTH() {
        return ContextCompat.checkSelfPermission(MainHomeActivity.this, "android.permission.BLUETOOTH") == 0;
    }

    private boolean system_sto() {
        return ContextCompat.checkSelfPermission(MainHomeActivity.this, "android.permission.SYSTEM_ALERT_WINDOW") == 0;
    }

    private boolean CAPTURE_VIDEO_OUTPUT() {
        return ContextCompat.checkSelfPermission(MainHomeActivity.this, "android.permission.CAPTURE_VIDEO_OUTPUT") == 0;
    }

    public void goToMain() {
        AppPermissions();
    }

    public void Rate() {

        Dialog dialog = new Dialog(this, com.sotdogn.bldogkepd.R.style.Transparent);
        dialog.setContentView(com.sotdogn.bldogkepd.R.layout.rate_dialog);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setCancelable(false);
        RatingBar simpleRatingBar = (RatingBar) dialog.findViewById(R.id.rb_stars);
        ((LinearLayout) dialog.findViewById(com.sotdogn.bldogkepd.R.id.bt_later)).setBackground(gd2);
        ((LinearLayout) dialog.findViewById(com.sotdogn.bldogkepd.R.id.bt_later)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                if (simpleRatingBar.getRating() == 1 || simpleRatingBar.getRating() == 2 || simpleRatingBar.getRating() == 3 || simpleRatingBar.getRating() == 4) {

                    findViewById(R.id.btn_click).setVisibility(View.VISIBLE);
                    findViewById(R.id.btn_click2).setVisibility(View.VISIBLE);
                    findViewById(R.id.txtConnect).setVisibility(View.VISIBLE);

                    call_timer_layout.setVisibility(View.GONE);

                    SharedPreferences.Editor editor = getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE).edit();
                    editor.putBoolean("rate_state", true);
                    editor.apply();

                    Intent email = new Intent(Intent.ACTION_SEND);
                    email.putExtra(Intent.EXTRA_EMAIL, new String[]{"sdenterprise0610@gmail.com"});
                    email.putExtra(Intent.EXTRA_SUBJECT, " User Feedback");
                    email.setType("message/rfc822");
                    startActivity(Intent.createChooser(email, "Choose an Email client :"));
                    dialog.dismiss();

                } else if (simpleRatingBar.getRating() == 5) {
                    findViewById(R.id.btn_click).setVisibility(View.VISIBLE);
                    findViewById(R.id.btn_click2).setVisibility(View.VISIBLE);
                    findViewById(R.id.txtConnect).setVisibility(View.VISIBLE);

                    call_timer_layout.setVisibility(View.GONE);
                    SharedPreferences.Editor editor = getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE).edit();
                    editor.putBoolean("rate_state", true);
                    editor.apply();

                    startActivity(new Intent("android.intent.action.VIEW").setData(Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
                    dialog.dismiss();
                } else {
                    Toast.makeText(MainHomeActivity.this, "Please Select Stars", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ((LinearLayout) dialog.findViewById(com.sotdogn.bldogkepd.R.id.Maybe)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                dialog.dismiss();
                myCountdownTimer = new CountDownTimer(7000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        timer.setText("00:0" + millisUntilFinished / 1000);
                    }

                    public void onFinish() {
                        myCountdownTimer.cancel();
                        findViewById(R.id.btn_click).setVisibility(View.VISIBLE);
                        findViewById(R.id.btn_click2).setVisibility(View.VISIBLE);
                        findViewById(R.id.txtConnect).setVisibility(View.VISIBLE);

                        call_timer_layout.setVisibility(View.GONE);
                    }
                }.start();

                myCountdownTimer.start();
            }
        });
        dialog.show();
    }
}

