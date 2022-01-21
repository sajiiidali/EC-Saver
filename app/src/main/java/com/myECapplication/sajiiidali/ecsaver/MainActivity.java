package com.myECapplication.sajiiidali.ecsaver;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Calendar;
import java.util.Objects;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    Button btnjanuary,btnfebruary,btnmarch,btnapril,btnmay,btnjune,btnjuly,btnaugust,btnseptember,btnoctober,btnnovember,btndecember;
    int selected_date =0, Selected_month =0, getSelected_year =0;
    int c_year,selected_year;
    Button btncurrentdate,btnolddate,btnsavedayoff,btnsearchByYear;
    AlertDialog.Builder builder;
    Intent it,shareIntent;
    EditText getYear_No;
    int check_year = 0;
    Database mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        overridePendingTransition(R.anim.slid_in_left,R.anim.slid_out_right);

        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.mipmap.ec_saver_app_logo);// set app icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mydb = new Database(this);
        builder = new AlertDialog.Builder(MainActivity.this);

        btnjanuary          = (Button)findViewById(R.id.btnjanuary);
        btnfebruary         = (Button)findViewById(R.id.btnfebruary);
        btnmarch            = (Button)findViewById(R.id.btnmarch);
        btnapril            = (Button)findViewById(R.id.btnapril);
        btnmay              = (Button)findViewById(R.id.btnmay);
        btnjune             = (Button)findViewById(R.id.btnjune);
        btnjuly             = (Button)findViewById(R.id.btnjuly);
        btnaugust           = (Button)findViewById(R.id.btnaugust);
        btnseptember        = (Button)findViewById(R.id.btnseptember);
        btnoctober          = (Button)findViewById(R.id.btnoctober);
        btnnovember         = (Button)findViewById(R.id.btnnovember);
        btndecember         = (Button)findViewById(R.id.btndecember);
        btncurrentdate      = (Button)findViewById(R.id.itemcurrentdate);
        btnolddate          = (Button)findViewById(R.id.itemolddate);
        btnsavedayoff       = (Button)findViewById(R.id.savedayoff);
        btnsearchByYear     = (Button)findViewById(R.id.searchByYear);
        Calendar calendar   = Calendar.getInstance(TimeZone.getDefault());

        c_year = calendar.get(Calendar.YEAR);

        // Click Listener of Data Save in Current Date
        btncurrentdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                it = new Intent(MainActivity.this,SaveDataCurrentDay.class);
                MainActivity.this.startActivity(it);
            }
        });


        // Click Listener of Data Save in Previous Date
        btnolddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final LayoutInflater button_inflator = getLayoutInflater();
                final View thisView = button_inflator.inflate(R.layout.datepicker_layout,null);
                final DatePicker datePicker = thisView.findViewById(R.id.datepicker);

                builder.setView(thisView);
                builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        selected_date = datePicker.getDayOfMonth();
                        Selected_month = datePicker.getMonth() + 1;
                        getSelected_year = datePicker.getYear();

                        it = new Intent(getApplicationContext(), SaveOldSelecteddate.class);
                        it.putExtra("date", selected_date);
                        it.putExtra("month", Selected_month);
                        it.putExtra("year", getSelected_year);
                        startActivity(it);
                    }
                });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // No need any Action
                    }
                });
                builder.show();
            }
        });

        btnsearchByYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchByOldYear();
            }
        });

        // Click Listener of Save Dayoff and Leave
        btnsavedayoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final LayoutInflater button_inflator = getLayoutInflater();
                final View thisView = button_inflator.inflate(R.layout.datepicker_layout,null);
                final DatePicker datePicker = thisView.findViewById(R.id.datepicker);

                builder.setView(thisView);
                builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        selected_date = datePicker.getDayOfMonth();
                        Selected_month = datePicker.getMonth() + 1;
                        getSelected_year = datePicker.getYear();

                        it = new Intent(getApplicationContext(), Save_Dayoff_Leave.class);
                        it.putExtra("date", selected_date);
                        it.putExtra("month", Selected_month);
                        it.putExtra("year", getSelected_year);
                        startActivity(it);
                    }
                });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // No need any Action
                    }
                });
                builder.show();
            }
        });
        btnjanuary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( check_year != 0)
                    selected_year = check_year;
                else
                    selected_year = c_year;
                int month = 1;
                it = new Intent(MainActivity.this,Main2Activity.class);
                it.putExtra("month",month);
                it.putExtra("selected_year",selected_year);
                MainActivity.this.startActivity(it);
            }
        });
        btnfebruary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( check_year != 0)
                    selected_year = check_year;
                else
                    selected_year = c_year;
                int month = 2;
                it = new Intent(MainActivity.this,Main2Activity.class);
                it.putExtra("month",month);
                it.putExtra("selected_year",selected_year);
                MainActivity.this.startActivity(it);
            }
        });
        btnmarch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( check_year != 0)
                    selected_year = check_year;
                else
                    selected_year = c_year;
                int month = 3;
                it = new Intent(MainActivity.this,Main2Activity.class);
                it.putExtra("month",month);
                it.putExtra("selected_year",selected_year);
                MainActivity.this.startActivity(it);
            }
        });
        btnapril.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( check_year != 0)
                    selected_year = check_year;
                else
                    selected_year = c_year;
                int month = 4;
                it = new Intent(MainActivity.this,Main2Activity.class);
                it.putExtra("month",month);
                it.putExtra("selected_year",selected_year);
                MainActivity.this.startActivity(it);
            }
        });
        btnmay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( check_year != 0)
                    selected_year = check_year;
                else
                    selected_year = c_year;
                int month = 5;
                it = new Intent(MainActivity.this,Main2Activity.class);
                it.putExtra("month",month);
                it.putExtra("selected_year",selected_year);
                MainActivity.this.startActivity(it);
            }
        });
        btnjune.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ( check_year != 0)
                    selected_year = check_year;
                else
                    selected_year = c_year;

                int month = 6;
                it = new Intent(MainActivity.this,Main2Activity.class);
                it.putExtra("month",month);
                it.putExtra("selected_year",selected_year);
                MainActivity.this.startActivity(it);
            }
        });
        btnjuly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( check_year != 0)
                    selected_year = check_year;
                else
                    selected_year = c_year;
                int month = 7;
                it = new Intent(MainActivity.this,Main2Activity.class);
                it.putExtra("month",month);
                it.putExtra("selected_year",selected_year);
                MainActivity.this.startActivity(it);
            }
        });
        btnaugust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( check_year != 0)
                    selected_year = check_year;
                else
                    selected_year = c_year;
                int month = 8;
                it = new Intent(MainActivity.this,Main2Activity.class);
                it.putExtra("month",month);
                it.putExtra("selected_year",selected_year);
                MainActivity.this.startActivity(it);
            }
        });
        btnseptember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( check_year != 0)
                    selected_year = check_year;
                else
                    selected_year = c_year;
                int month = 9;
                it = new Intent(MainActivity.this,Main2Activity.class);
                it.putExtra("month",month);
                it.putExtra("selected_year",selected_year);
                MainActivity.this.startActivity(it);
            }
        });
        btnoctober.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( check_year != 0)
                    selected_year = check_year;
                else
                    selected_year = c_year;
                int month = 10;
                it = new Intent(MainActivity.this,Main2Activity.class);
                it.putExtra("month",month);
                it.putExtra("selected_year",selected_year);
                MainActivity.this.startActivity(it);
            }
        });
        btnnovember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( check_year != 0)
                    selected_year = check_year;
                else
                    selected_year = c_year;
                int month = 11;
                it = new Intent(MainActivity.this,Main2Activity.class);
                it.putExtra("month",month);
                it.putExtra("selected_year",selected_year);
                MainActivity.this.startActivity(it);
            }
        });
        btndecember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( check_year != 0)
                    selected_year = check_year;
                else
                    selected_year = c_year;
                int month = 12;
                it = new Intent(MainActivity.this,Main2Activity.class);
                it.putExtra("month",month);
                it.putExtra("selected_year",selected_year);
                MainActivity.this.startActivity(it);
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.my_menu,menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.Deletewhole:
                deletedata();
                return true;
            case R.id.age_calculator:
                return true;
            case R.id.itemabout:
                it = new Intent(MainActivity.this,ShowDataBySelectedMonth.class);
                MainActivity.this.startActivity(it);
                return true;
            case R.id.rateus:
                rateus();
                return true;
            case R.id.mshare:
               share();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void share() {
        shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
        shareIntent.putExtra(Intent.EXTRA_TEXT,"https://play.google.com/store/apps/details?id=com.myECapplication.sajiiidali.ecsaver");
        startActivity(Intent.createChooser(shareIntent,"Share Via"));
    }

    private void SearchByOldYear() {

        AlertDialog.Builder SearchYearBuilder = new AlertDialog.Builder(this);
        LayoutInflater button_inflator = getLayoutInflater();
        View thisView = button_inflator.inflate(R.layout.find_year_layout,null);
        getYear_No = thisView.findViewById(R.id.year_no1);
        SearchYearBuilder.setView(thisView).setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

               // get any years by user and show data this given years
                if (getYear_No.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Enter a year or cancel it", Toast.LENGTH_SHORT).show();
                }else if (getYear_No.getText().toString().length() != 4){
                    Toast.makeText(MainActivity.this, "Enter Correct Year", Toast.LENGTH_SHORT).show();
                } else {
                    selected_year = Integer.parseInt(getYear_No.getText().toString());
                    check_year = Integer.parseInt(getYear_No.getText().toString());
                    Toast.makeText(MainActivity.this, "Now You can check Data of "+selected_year+" Year", Toast.LENGTH_SHORT).show();
                }
            }
        }).setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // No need any Action
            }
        }).show();

    }

    private void rateus() {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
        }catch (ActivityNotFoundException e){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
        }
    }

    public void deletedata() {

        AlertDialog.Builder deletethisBuilder = new AlertDialog.Builder(this);
        try {
            deletethisBuilder.setTitle("CAUTION !!! \n All Data Would Be Deleted").setMessage("\nReally? Do You Want To Delete All Records?").setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Cursor res = mydb.showdatawithoutarguments();
                    if (res.getCount() == 0)
                        Toast.makeText(MainActivity.this, "There is No any Records", Toast.LENGTH_LONG).show();
                    else {
                        mydb.deleteall();
                        Toast.makeText(MainActivity.this, "All Data Have Deleted", Toast.LENGTH_LONG).show();
                    }
                }
            }).setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            }).show();
        } catch (IllegalStateException e) {
            Toast.makeText(MainActivity.this, "All Data Have Deleted", Toast.LENGTH_LONG).show();
        }
    }
}

