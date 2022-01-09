package com.myECapplication.sajiiidali.ecsaver;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import java.util.Calendar;
import java.util.TimeZone;

public class AgeCalculator extends AppCompatActivity {

    public static final String FILE_NAME = "my_file" ;
    public static final String date = "my_date" ;
    public static final String month = "my_month" ;
    public static final String year = "my_year" ;
    AutoCompleteTextView editText_day,editText_month,editText_year;
    EditText edittext_to_days,edittext_to_month,edittext_to_years;
    Button      btn_calculate,btn_clear;
    TextView  textView_years,textView_months,textView_days, textView_show_current_date;
    int current_date,current_month,current_year,currentmonth;
    int y = 0, m = 0, d = 0;
    int todays = 0 ,tomonth = 0 ,toyears = 0;
    int Tyears=0, Tmonth=0, Tdays=0;
    View view;
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age_calculator);
        MobileAds.initialize(this, "ca-app-pub-1241237715193709~8967418014");

        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.SMART_BANNER);
        adView.setAdUnitId("ca-app-pub-1241237715193709/5970430028");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
       // mAdView.loadAd(adRequest);

        textView_show_current_date = (TextView)findViewById(R.id.date_header);

        editText_day = (AutoCompleteTextView)findViewById(R.id.edt_day);
        editText_month = (AutoCompleteTextView)findViewById(R.id.edt_month);
        editText_year = (AutoCompleteTextView)findViewById(R.id.edt_year);

        edittext_to_days = (EditText)findViewById(R.id.edtto_day);
        edittext_to_month = (EditText)findViewById(R.id.edtto_month);
        edittext_to_years = (EditText)findViewById(R.id.edtto_year);

        btn_calculate = (Button)findViewById(R.id.button_calculate);
        btn_clear   = (Button)findViewById(R.id.button_clear);

        textView_years = (TextView)findViewById(R.id.text_years);
        textView_months = (TextView)findViewById(R.id.text_month);
        textView_days = (TextView)findViewById(R.id.text_days);

        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        currentmonth = calendar.get(Calendar.MONTH);
        current_month = currentmonth+1;
        current_date = calendar.get(Calendar.DAY_OF_MONTH);
        current_year = calendar.get(Calendar.YEAR);

        SharedPreferences sharedPreferences = getSharedPreferences(FILE_NAME,MODE_PRIVATE);
        String save_preference1 = sharedPreferences.getString(date,"0");
        String save_preference2 = sharedPreferences.getString(month,"0");
        String save_preference3 = sharedPreferences.getString(year,"0");

        editText_day.setText(save_preference1);
        editText_month.setText(save_preference2);
        editText_year.setText(save_preference3);

        textView_show_current_date.setText("Current Date Is :-  "+"  "+current_date+"-"+current_month+"-"+current_year);

        btn_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = 0;
                try {
                    closekeyboard();

                    y = Integer.parseInt(editText_year.getText().toString());
                    m = Integer.parseInt(editText_month.getText().toString());
                    d = Integer.parseInt(editText_day.getText().toString());

                    SharedPreferences.Editor editor = getSharedPreferences(FILE_NAME,MODE_PRIVATE).edit();
                    editor.putString(date,editText_day.getText().toString());
                    editor.putString(month,editText_month.getText().toString());
                    editor.putString(year,editText_year.getText().toString());
                    editor.apply();



                    todays = Integer.parseInt(edittext_to_days.getText().toString());
                    tomonth = Integer.parseInt(edittext_to_month.getText().toString());
                    toyears = Integer.parseInt(edittext_to_years.getText().toString());
                    if (Integer.parseInt(editText_day.getText().toString()) > 31) {
                        editText_day.setError("Only 31 days in month");
                    } else if (Integer.parseInt(editText_month.getText().toString()) > 12) {
                        edittext_to_month.setError("Only 12 month in year");
                    } else if (editText_year.getText().toString().length() != 4) {
                        editText_year.setError("Enter Correct year");
                    } else if (Integer.parseInt(edittext_to_days.getText().toString()) > 31) {
                        edittext_to_days.setError("Only 31 days in month");
                    } else if (Integer.parseInt(edittext_to_month.getText().toString()) > 12) {
                        edittext_to_month.setError("Only 12 month in year");
                    } else if (edittext_to_years.getText().toString().length() != 4) {
                        edittext_to_years.setError("Enter correct year");
                    } else {
                        a = a + 1;
                    }
                    if (a == 1) {
                        if (todays < d) {
                            todays = todays + 30;
                            tomonth--;
                        }
                        if (tomonth < m) {
                            tomonth = tomonth + 12;
                            toyears--;
                        }
                        Tyears = toyears - y;
                        Tmonth = tomonth - m;
                        Tdays = todays - d;

                        textView_years.setText(" " + Tyears + " Years");
                        textView_months.setText(" " + Tmonth + " Months");
                        textView_days.setText(" " + Tdays + " Days");
                    }
                    }catch(NumberFormatException e){
                        Toast.makeText(AgeCalculator.this, "Please First Put Birthday ", Toast.LENGTH_LONG).show();
                    }
                }
            private void closekeyboard() {
                view = AgeCalculator.this.getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
        });

        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText_day.setText("");
                editText_month.setText("");
                editText_year.setText("");
                textView_days.setText("");
                textView_months.setText("");
                textView_years.setText("");
                edittext_to_days.setText("");
                edittext_to_month.setText("");
                edittext_to_years.setText("");
            }
        });


    }

    public void set_current_date(View view) {
        edittext_to_days.setText(String.valueOf(current_date));
        edittext_to_month.setText(String.valueOf(current_month));
        edittext_to_years.setText(String.valueOf(current_year));
    }
}
