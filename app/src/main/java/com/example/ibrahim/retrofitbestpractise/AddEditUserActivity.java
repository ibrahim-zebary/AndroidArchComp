package com.example.ibrahim.retrofitbestpractise;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;

import com.example.ibrahim.retrofitbestpractise.arch.rest.model.User;

import static com.example.ibrahim.retrofitbestpractise.MainActivity.AGE;
import static com.example.ibrahim.retrofitbestpractise.MainActivity.EMAIL;
import static com.example.ibrahim.retrofitbestpractise.MainActivity.FIRST_NAME;
import static com.example.ibrahim.retrofitbestpractise.MainActivity.ID;
import static com.example.ibrahim.retrofitbestpractise.MainActivity.LAST_NAME;

public class AddEditUserActivity extends AppCompatActivity {

    public static final String EXTRA_USER = "extra_user";
    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private NumberPicker age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_user);

        firstName = findViewById(R.id.add_edit_user_activity_first_name);
        lastName = findViewById(R.id.add_edit_user_activity_last_name);
        email = findViewById(R.id.add_edit_user_activity_email);
        age = findViewById(R.id.add_edit_user_activity_age);
        age.setMaxValue(100);
        age.setMinValue(1);

        Intent intent = getIntent();
        if (intent.hasExtra(ID)) {
            String firstNameStr = intent.getStringExtra(FIRST_NAME);
            String lastNameStr = intent.getStringExtra(LAST_NAME);
            String emailStr = intent.getStringExtra(EMAIL);
            int ageInt = intent.getIntExtra(AGE, 0);

            firstName.setText(firstNameStr);
            lastName.setText(lastNameStr);
            email.setText(emailStr);
            age.setValue(ageInt);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_edit_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_user_menu:
                saveUser();
                break;
        }
        return true;
    }

    private void saveUser() {
        String firstNameStr = firstName.getText().toString().trim();
        if (firstNameStr.isEmpty()) {
            firstName.setError("Required");
            firstName.requestFocus();
            return;
        }

        String lastNameStr = lastName.getText().toString().trim();
        if (lastNameStr.isEmpty()) {
            lastName.setError("Required");
            lastName.requestFocus();
            return;
        }

        String emailStr = email.getText().toString().trim();
        if (emailStr.isEmpty()) {
            email.setError("Required");
            email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailStr).matches()) {
            email.setError("Required email");
            email.requestFocus();
            return;
        }

        int ageInt = age.getValue();

        User user = new User(firstNameStr, lastNameStr, emailStr, ageInt, 2);
        Intent intent = new Intent();
        intent.putExtra(EXTRA_USER, user);
        setResult(RESULT_OK, intent);
        finish();
    }
}
