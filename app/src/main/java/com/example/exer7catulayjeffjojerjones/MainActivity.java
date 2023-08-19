package com.example.exer7catulayjeffjojerjones;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> gender = new ArrayList<>();
    ArrayList<String> colleges = new ArrayList<>();
    ArrayList<String> course = new ArrayList<>();
    ArrayList<String> year = new ArrayList<>();

    Spinner genderChoice, collegesChoice, courseChoice, yearChoice;
    Button registerBtn;
    EditText studentNum_input, surname_input, firstName_input, middleName_input,
            address_input, contact_input, email_input;

    String studentNum_val, surname_val, firstName_val, middleName_val,
            address_val, contact_val, email_val,
            gender_val, college_val, course_val, year_val;

    boolean isRegisterClicked = false;

    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Spinners
        genderChoice = findViewById(R.id.genderChoice);
        collegesChoice = findViewById(R.id.collegesChoice);
        courseChoice = findViewById(R.id.courseChoice);
        yearChoice = findViewById(R.id.yearChoice);

        //Edittext
        studentNum_input = findViewById(R.id.studentNum_input);
        surname_input = findViewById(R.id.surname_input);
        firstName_input = findViewById(R.id.firstName_input);
        middleName_input = findViewById(R.id.middleName_input);
        address_input = findViewById(R.id.address_input);
        contact_input = findViewById(R.id.contact_input);
        email_input = findViewById(R.id.email_input);

        //Buttons
        registerBtn = findViewById(R.id.registerBtn);

        // On Create Functions
        bottomNavigation();
        spinnerCollection();
        //register();

        if (isNetworkAvailable()){

        }else {
            retryConnection("No Internet Connection, Please make sure you are connected first");
        }

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val = "fuck";
                root.setValue(val);
                showMessage("Success!", "Student Added!");
            }
        });
    }

    @Override
    public void onRestart(){
        super.onRestart();
        if (isNetworkAvailable()){

        }else {
            retryConnection("No Internet Connection, Please make sure you are connected first");
        }
    }

    public void saveToFirebase(){
        studentNum_val = studentNum_input.getText().toString();
        surname_val = surname_input.getText().toString();
        firstName_val = firstName_input.getText().toString();
        middleName_val = middleName_input.getText().toString();
        address_val = address_input.getText().toString();
        contact_val = contact_input.getText().toString();
        email_val = email_input.getText().toString();
        gender_val = genderChoice.getSelectedItem().toString();
        college_val = collegesChoice.getSelectedItem().toString();
        course_val = courseChoice.getSelectedItem().toString();
        year_val = yearChoice.getSelectedItem().toString();

        HashMap<String, String> studentInfo = new HashMap<>();
        studentInfo.put("Student Number", studentNum_val);
        studentInfo.put("Surname", surname_val);
        studentInfo.put("FirstName", firstName_val);
        studentInfo.put("MiddleName", middleName_val);
        studentInfo.put("Address", address_val);
        studentInfo.put("Contact", contact_val);
        studentInfo.put("Email", email_val);
        studentInfo.put("Gender", gender_val);
        studentInfo.put("College", college_val);
        studentInfo.put("Course", course_val);
        studentInfo.put("Year", year_val);

        showMessage("Success!", "Student Added!");
    }

    public void register(){
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*isRegisterClicked = true;
                if ( (studentNum_input.getText().toString().isEmpty()) || (surname_input.getText().toString().isEmpty()) || (firstName_input.getText().toString().isEmpty()) || (middleName_input.getText().toString().isEmpty())
                        || (address_input.getText().toString().isEmpty()) || (contact_input.getText().toString().isEmpty()) || (email_input.getText().toString().isEmpty())
                        || (genderChoice.getSelectedItemPosition() == 0) || (collegesChoice.getSelectedItemPosition() == 0) || (courseChoice.getSelectedItemPosition() == 0) || (yearChoice.getSelectedItemPosition() == 0)
                ){
                    FieldIsEmpty();
                } else {
                    saveToFirebase();
                }
                isValueTyped();*/

                //saveToFirebase();
            }
        });
    }

    public void isValueTyped(){
        studentNum_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() != 0){
                    studentNum_input.setBackgroundResource(R.drawable.border);
                }
                else {
                    studentNum_input.setBackgroundResource(R.drawable.emptyfield);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        surname_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() != 0){
                    surname_input.setBackgroundResource(R.drawable.border);
                }
                else {
                    surname_input.setBackgroundResource(R.drawable.emptyfield);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        firstName_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() != 0){
                    firstName_input.setBackgroundResource(R.drawable.border);
                }
                else {
                    firstName_input.setBackgroundResource(R.drawable.emptyfield);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        middleName_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() != 0){
                    middleName_input.setBackgroundResource(R.drawable.border);
                }
                else {
                    middleName_input.setBackgroundResource(R.drawable.emptyfield);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        address_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() != 0){
                    address_input.setBackgroundResource(R.drawable.border);
                }
                else {
                    address_input.setBackgroundResource(R.drawable.emptyfield);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        contact_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() != 0){
                    contact_input.setBackgroundResource(R.drawable.border);
                }
                else {
                    contact_input.setBackgroundResource(R.drawable.emptyfield);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        email_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() != 0){
                    email_input.setBackgroundResource(R.drawable.border);
                }
                else {
                    email_input.setBackgroundResource(R.drawable.emptyfield);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        if (!isRegisterClicked){
            genderChoice.setBackgroundResource(R.drawable.border);
        } else {
            changeSpinnerState();
        }
    }

    public void changeSpinnerState() {
        genderChoice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    genderChoice.setBackgroundResource(R.drawable.emptyfield);
                } else {
                    genderChoice.setBackgroundResource(R.drawable.border);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        collegesChoice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    collegesChoice.setBackgroundResource(R.drawable.emptyfield);
                } else {
                    collegesChoice.setBackgroundResource(R.drawable.border);
                }

                switch (position){
                    case 0:
                        noCollegeSelected();
                        break;
                    case 1:
                        cafaSelected();
                        break;
                    case 2:
                        casSelected();
                        break;
                    case 3:
                        cbaSelected();
                        break;
                    case 4:
                        cedSelected();
                        break;
                    case 5:
                        ceaSelected();
                        break;
                    case 6:
                        chmSelected();
                        break;
                    case 7:
                        citSelected();
                        break;
                    case 8:
                        cpacSelected();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        courseChoice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    courseChoice.setBackgroundResource(R.drawable.emptyfield);
                } else {
                    courseChoice.setBackgroundResource(R.drawable.border);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        yearChoice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    yearChoice.setBackgroundResource(R.drawable.emptyfield);
                } else {
                    yearChoice.setBackgroundResource(R.drawable.border);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void FieldIsEmpty(){
        if (studentNum_input.getText().toString().isEmpty()){
            studentNum_input.setBackgroundResource(R.drawable.emptyfield);
            studentNum_input.setHint("Student Number is Required");
        }
        if (surname_input.getText().toString().isEmpty()){
            surname_input.setBackgroundResource(R.drawable.emptyfield);
            surname_input.setHint("Surname Is Required");
        }
        if (firstName_input.getText().toString().isEmpty()){
            firstName_input.setBackgroundResource(R.drawable.emptyfield);
            firstName_input.setHint("FirstName Is Required");
        }
        if (middleName_input.getText().toString().isEmpty()){
            middleName_input.setBackgroundResource(R.drawable.emptyfield);
            middleName_input.setHint("Middle Name Is Required");
        }
        if (address_input.getText().toString().isEmpty()){
            address_input.setBackgroundResource(R.drawable.emptyfield);
            address_input.setHint("Address Is Required");
        }
        if (contact_input.getText().toString().isEmpty()){
            contact_input.setBackgroundResource(R.drawable.emptyfield);
            contact_input.setHint("Contact Is Required");
        }
        if (email_input.getText().toString().isEmpty()){
            email_input.setBackgroundResource(R.drawable.emptyfield);
            email_input.setHint("Email Is Required");
        }
        if (genderChoice.getSelectedItemPosition() == 0){
            genderChoice.setBackgroundResource(R.drawable.emptyfield);
        }
        if (collegesChoice.getSelectedItemPosition() == 0){
            collegesChoice.setBackgroundResource(R.drawable.emptyfield);
        }
        if (courseChoice.getSelectedItemPosition() == 0){
            courseChoice.setBackgroundResource(R.drawable.emptyfield);
        }
        if (yearChoice.getSelectedItemPosition() == 0){
            yearChoice.setBackgroundResource(R.drawable.emptyfield);
        }
    }

    private void spinnerCollection(){
        // Gender
        gender.add("Select A Gender");
        gender.add("Male");
        gender.add("Female");

        ArrayAdapter<String> gender_choice = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, gender );
        gender_choice.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderChoice.setAdapter(gender_choice);

        // Colleges
        colleges.add("Select A College");
        colleges.add("College Architecture and Fine Arts");
        colleges.add("College Of Arts and Sciences");
        colleges.add("College of Business Administration ");
        colleges.add("College of Education");
        colleges.add("College of Engineering");
        colleges.add("College of Hospitality Management");
        colleges.add("College of Industrial Technology");
        colleges.add("College of Public Administration and Criminology");

        ArrayAdapter<String> college_choice = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, colleges );
        college_choice.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        collegesChoice.setAdapter(college_choice);

        // Courses
        collegesChoice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        noCollegeSelected();
                        break;
                    case 1:
                        cafaSelected();
                        break;
                    case 2:
                        casSelected();
                        break;
                    case 3:
                        cbaSelected();
                        break;
                    case 4:
                       cedSelected();
                        break;
                    case 5:
                        ceaSelected();
                        break;
                    case 6:
                        chmSelected();
                        break;
                    case 7:
                        citSelected();
                        break;
                    case 8:
                        cpacSelected();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //  Year
        year.add("Select A Year");
        year.add("1st Year");
        year.add("2nd Year");
        year.add("3rd Year");
        year.add("4th Year");
        year.add("5th Year");
        year.add("Irregular");

        ArrayAdapter<String> year_choice = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, year );
        year_choice.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearChoice.setAdapter(year_choice);
    }

    public void noCollegeSelected(){
        course.clear();
        course.add("Select A Course");
        ArrayAdapter<String> course_choice = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, course );
        course_choice.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courseChoice.setAdapter(course_choice);
    }

    public void cafaSelected(){
        course.clear();
        course.add("Select A Course");
        course.add("BS Architecture");
        course.add("BS Interior Design");
        course.add("BS Fine Arts Painting");
        course.add("BS Fine Arts Visual Communication");
        ArrayAdapter<String> course_choice = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, course );
        course_choice.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courseChoice.setAdapter(course_choice);
    }

    public void casSelected(){
        course.clear();
        course.add("Select A Course");
        course.add("BS Applied Physics");
        course.add("BS Computer Science");
        course.add("BS Information Technology");
        course.add("BS Psychology");
        course.add("BS Mathematics");
        ArrayAdapter<String> course_choice = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, course );
        course_choice.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courseChoice.setAdapter(course_choice);
    }

    public void cbaSelected(){
        course.clear();
        course.add("Select A Course");
        course.add("BS Office Administration");
        course.add("BS Business Administration Marketing Management");
        course.add("BS Business Administration Human Resource Management");
        course.add("BS Entrepreneurship");
        ArrayAdapter<String> course_choice = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, course );
        course_choice.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courseChoice.setAdapter(course_choice);
    }

    public void cedSelected(){
        course.clear();
        course.add("Select A Course");
        course.add("BS Secondary Education Filipino");
        course.add("BS Secondary Education Mathematics");
        course.add("BS Secondary Education Science");
        course.add("BS Home Economics");
        course.add("BS Industrial Arts");
        course.add("BS Special Needs Education");
        ArrayAdapter<String> course_choice = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, course );
        course_choice.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courseChoice.setAdapter(course_choice);
    }

    public void ceaSelected(){
        course.clear();
        course.add("Select A Course");
        course.add("BS Chemical Engineering");
        course.add("BS Civil Engineering");
        course.add("BS Electrical Engineering");
        course.add("BS Electronics And Communication Engineering");
        course.add("BS Mechanical Engineering");
        course.add("BS Computer Engineering");
        ArrayAdapter<String> course_choice = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, course );
        course_choice.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courseChoice.setAdapter(course_choice);
    }

    public void chmSelected(){
        course.clear();
        course.add("Select A Course");
        course.add("BS Tourism Management");
        course.add("BS Hospitality Management");
        ArrayAdapter<String> course_choice = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, course );
        course_choice.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courseChoice.setAdapter(course_choice);
    }

    public void citSelected(){
        course.clear();
        course.add("Select A Course");
        course.add("BS Automotive Technology");
        course.add("BS Electrical Technology");
        course.add("BS Electronics Technology");
        course.add("BS Food Technology");
        course.add("BS Fashion Apparel Technology");
        course.add("BS Industrial Chemistry");
        course.add("BS Drafting Technology");
        course.add("BS Machine Shop Technology");
        course.add("BS Refrigeration  and Air-Conditioning");
        ArrayAdapter<String> course_choice = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, course );
        course_choice.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courseChoice.setAdapter(course_choice);
    }

    public void cpacSelected(){
        course.clear();
        course.add("Select A Course");
        course.add("BS Public Administration");
        course.add("BS Criminology");
        ArrayAdapter<String> course_choice = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, course );
        course_choice.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courseChoice.setAdapter(course_choice);
    }

    public void retryConnection(String title){
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setMessage(title)
                    .setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            if (isNetworkAvailable()){
                                showMessage("Success", "Internet Connected");
                            }else {
                                retryConnection("No Internet Connection, Please make sure you are connected first");
                            }
                        }
                    })
                    .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                        }
                    });
            builder.show();
        }catch (Exception e){
            showMessage("Error", "Something is Wrong!");
        }
    }

    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public boolean isNetworkAvailable(){
        try {
            ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = null;
            if (manager != null){
                networkInfo = manager.getActiveNetworkInfo();
                return networkInfo != null && networkInfo.isConnected();
            }
        } catch (NullPointerException e){
            return false;
        }
        return false;
    }

    private void bottomNavigation(){
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_register);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_register:
                        return true;
                    case R.id.nav_search:
                        startActivity(new Intent(getApplicationContext(),
                                searchStudent.class));
                        finish();
                        bottomNavigationView.setSelectedItemId(R.id.nav_register);
                        return true;
                }
                finish();
                return false;
            }
        });
    }
}