package com.myECapplication.sajiiidali.ecsaver;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DecimalFormat;


public class BMI_Calculator extends AppCompatActivity {

    public static final String MFILE_NAME = "Bmi_file" ;
    public static final String mweight = "my_weight" ;
    public static final String mfeet = "my_feet" ;
    public static final String minch = "my_inch" ;

    AutoCompleteTextView edittext1_weight,edittext2_feet,edittext3_inch;
    Button btncalculate,btnclear;
    TextView textView;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi__calculator);

        edittext1_weight = (AutoCompleteTextView) findViewById(R.id.edittext1_weight);
        edittext2_feet = (AutoCompleteTextView) findViewById(R.id.edittext2_feet);
        edittext3_inch = (AutoCompleteTextView) findViewById(R.id.edittext3_inch);
        btncalculate = (Button) findViewById(R.id.btncalculate);
        btnclear = (Button)findViewById(R.id.button2);
        textView = (TextView) findViewById(R.id.showresult);
        view = new View(BMI_Calculator.this);

        SharedPreferences sharedPreferences = getSharedPreferences(MFILE_NAME,MODE_PRIVATE);
        String save_preference1 = sharedPreferences.getString(mweight,"80");
        String save_preference2 = sharedPreferences.getString(mfeet,"5");
        String save_preference3 = sharedPreferences.getString(minch,"10");

        edittext1_weight.setText(save_preference1);
        edittext2_feet.setText(save_preference2);
        edittext3_inch.setText(save_preference3);

        btncalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor editor = getSharedPreferences(MFILE_NAME,MODE_PRIVATE).edit();
                editor.putString(mweight,edittext1_weight.getText().toString());
                editor.putString(mfeet,edittext2_feet.getText().toString());
                editor.putString(minch,edittext3_inch.getText().toString());
                editor.apply();

                closekeyboard();
                String category = "";
                int total_weight=0,wait=0;
                try {
                    float a = Float.parseFloat(edittext2_feet.getText().toString());
                    float convert_ft_into_meter = (float) (a / 3.28);

                    float b = Float.parseFloat(edittext3_inch.getText().toString());
                    float inch_into_ft = (float) (b * 0.0833);

                    float convert_inch_into_meter = (float) (inch_into_ft/3.28);
                    float total_hieght_in_meter = convert_ft_into_meter + convert_inch_into_meter;

                    float weight = Float.parseFloat(edittext1_weight.getText().toString());
                    double BMI = weight / (total_hieght_in_meter * total_hieght_in_meter);
                    DecimalFormat df = new DecimalFormat("##.#");
                    float bmiValue = Float.parseFloat(df.format(BMI));

                    int w = Integer.valueOf(edittext1_weight.getText().toString());
                    int f = Integer.valueOf(edittext2_feet.getText().toString());
                    int i = Integer.valueOf(edittext3_inch.getText().toString());
                    if ( f == 4 && i == 9 ) {
                        wait = 52;
                    }
                    if ( f == 4 && i == 10 ) {
                        wait = 54;
                    }
                    if ( f == 4 && i == 11 ) {
                        wait = 56;
                    }
                    if ( f == 4 && i == 12 ) {
                        wait = 58;
                    }
                    if ( f == 5 && i == 0 ) {
                        wait = 58;
                    }
                    if ( f == 5 && i == 1 ) {
                        wait = 60;
                    }
                    if ( f == 5 && i == 2 ) {
                        wait = 62;
                    }
                    if ( f == 5 && i == 3 ) {
                        wait = 64;
                    }
                    if ( f == 5 && i == 4 ) {
                        wait = 66;
                    }
                    if ( f == 5 && i == 5 ) {
                        wait = 68;
                    }
                    if ( f == 5 && i == 6 ) {
                        wait = 70;
                    }
                    if ( f == 5 && i == 7 ) {
                        wait = 72;
                    }
                    if ( f == 5 && i == 8 ) {
                        wait = 74;
                    }
                    if ( f == 5 && i == 9 ) {
                        wait = 76;
                    }
                    if ( f == 5 && i == 10 ) {
                        wait = 78;
                    }
                    if ( f == 5 && i == 11 ) {
                        wait = 81;
                    }
                    if ( f == 5 && i == 12 ) {
                        wait = 83;
                    }
                    if ( f == 6 && i == 0 ) {
                        wait = 83;
                    }
                    if ( f == 6 && i == 1 ) {
                        wait = 86;
                    }
                    if ( f == 6 && i == 2 ) {
                        wait = 88;
                    }
                    if ( f == 6 && i == 3 ) {
                        wait = 91;
                    }
                    if ( f == 6 && i == 4 ) {
                        wait = 93;
                    }
                    if ( f == 6 && i == 5 ) {
                        wait = 95;
                    }
                    if ( f == 6 && i == 6 ) {
                        wait = 98;
                    }
                    if ( f == 6 && i == 7 ) {
                        wait = 101;
                    }


                    if (Float.compare(bmiValue, (float) 18.5) <= 0)
                        category = "underweight";
                    else if (Float.compare(bmiValue, (float) 18.6) >= 0  &&  Float.compare(bmiValue, (float) 24.9) <= 0)
                        category = "Normal";
                    else if (Float.compare(bmiValue, (float) 25.0) >= 0  &&  Float.compare(bmiValue, (float) 29.9) <= 0)
                    {
                        category = "Overweight";
                        total_weight = w - wait;
                    }
                    else if (Float.compare(bmiValue, (float) 30) >= 0) {
                        category = "Obese";
                        total_weight = w - wait;
                    }
                    textView.setText("Your BMI is " + bmiValue+"\n\nYou are "+category+"\n\nYour Over-Weight is "+total_weight+" KG");

                } catch (NumberFormatException e) {
                    Toast.makeText(BMI_Calculator.this,"Please First Put Weight and Height ",Toast.LENGTH_LONG).show();
                }
            }

            private void closekeyboard() {
                view = BMI_Calculator.this.getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
        });
        btnclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edittext1_weight.setText("");
                edittext2_feet.setText("");
                edittext3_inch.setText("");
                textView.setText("");
            }
        });
    }
}
