package sg.edu.rp.c346.reservation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etTelephone;
    EditText etSize;
    EditText etDay;
    EditText etTime;
    CheckBox checkBox;
    Button btReserve;
    Button btReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.editTextName);
        etTelephone = findViewById(R.id.editTextTelephone);
        etSize = findViewById(R.id.editTextSize);
        etDay = findViewById(R.id.editTextDay);
        etTime = findViewById(R.id.editTextTime);
        checkBox = findViewById(R.id.checkBox);
        btReserve = findViewById(R.id.buttonReserve);
        btReset = findViewById(R.id.buttonReset);

        etDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String date = dayOfMonth + "/" + (month+1) + "/" + year;
                        etDay.setText(date);
                    }
                };
                Calendar calendar = Calendar.getInstance();
                int currentYear = calendar.get(Calendar.YEAR);
                int currentMonth = calendar.get(Calendar.MONTH);
                int currentDay = calendar.get(Calendar.DATE);

                DatePickerDialog myDateDialog = new DatePickerDialog(MainActivity.this, myDateListener, currentYear, currentMonth, currentDay);
                myDateDialog.show();
            }
        });

        etTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String time = hourOfDay + ":" + minute;
                        etTime.setText(time);
                    }
                };
                Calendar calendar = Calendar.getInstance();
                int currentHour = calendar.get(Calendar.HOUR);
                int currentMin = calendar.get(Calendar.MINUTE);

                TimePickerDialog myTimeDialog = new TimePickerDialog(MainActivity.this, myTimeListener, currentHour, currentMin, false);
                myTimeDialog.show();
            }
        });

        btReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String isSmoke = "";
                if (checkBox.isChecked()) {
                    isSmoke = "smoking";
                }
                else {
                    isSmoke = "non-smoking";
                }

                String theEntireMessage = String.format("New Reservation\nName: %s\nSmoking: %s\nSize: %s\nDate: %s\nTime: %s", etName.getText().toString(), isSmoke, etSize.getText().toString(), etDay.getText().toString(), etTime.getText().toString());

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(MainActivity.this);
                myBuilder.setTitle("Confirm Your Order");
                myBuilder.setMessage(theEntireMessage);
//                myBuilder.setMessage("New Reservation");
//                myBuilder.setMessage("Name: " + etName.getText().toString());
//                myBuilder.setMessage("Smoking: " + isSmoke);
//                myBuilder.setMessage("Size: " + etSize.getText().toString());
//                myBuilder.setMessage("Date: " + etDay.getText().toString());
//                myBuilder.setMessage("Time: " + etTime.getText().toString());
                myBuilder.setCancelable(false);

                myBuilder.setPositiveButton("Confirm", null);

                myBuilder.setNeutralButton("Cancel", null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etName.setText("");
                etTelephone.setText("");
                etSize.setText("");
                etDay.setText("");
                etTime.setText("");
                checkBox.setChecked(false);
            }
        });
    }
}