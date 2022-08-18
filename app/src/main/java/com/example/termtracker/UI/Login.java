package com.example.termtracker.UI;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.termtracker.Database.Repository;
import com.example.termtracker.Entity.Term;
import com.example.termtracker.Entity.User;
import com.example.termtracker.R;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity {

    public static int alertNum;
    EditText userName;
    EditText password;
    Button loginButton;
    TextView error;

    @Override
    public void onBackPressed() {
        // Exit app if pressing back from the login screen
        moveTaskToBack(true);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        System.out.println("Login Screen initiated");

        userName = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);
        error = findViewById(R.id.error);


    }

    public void loginButtonClicked(View view) {
        Repository repo = new Repository(getApplication());


        String uName = userName.getText().toString();
        String uPass = password.getText().toString();


        if (!validateInputs(uName, uPass)) {
            error.setText(R.string.required_regex);
            error.setVisibility(TextView.VISIBLE);
            return;
        }
        if (uName.isEmpty()) {
            error.setText(R.string.username_empty_error_msg);
            error.setVisibility(TextView.VISIBLE);
            return;
        }

        User user = repo.getUserByUserNane(uName);
        if(user != null && user.validateCredentials(uPass)) {
            error.setVisibility(TextView.INVISIBLE);
            repo.inOrUp(user);
            logIn(user);

        } else {
            error.setText(R.string.invalid_login_attempt_msg);
            error.setVisibility(TextView.VISIBLE);
        }
    }

    private boolean validateInputs(String name, String pass) {


        Pattern pattern = Pattern.compile("[^a-z|0-9]", Pattern.CASE_INSENSITIVE);
        Matcher mName = pattern.matcher(name);
        Matcher mPass = pattern.matcher(pass);
        boolean fName = mName.find();
        boolean fPass = mPass.find();

        System.out.printf(Locale.US, "%b , %b%n", !fName, !fPass);
        return !fName && !fPass;
    }
    public void registerButtonClicked(View view) {

        Repository repo = new Repository(getApplication());

        String uName = userName.getText().toString();
        String uPass = password.getText().toString();

        if (uName.isEmpty()) {
            error.setText(R.string.username_empty_error_msg);
            error.setVisibility(TextView.VISIBLE);
            return;
        }

        User user = repo.getUserByUserNane(uName);

        if(user == null) {

            if(uName.isEmpty() || uPass.isEmpty()) {
                error.setText(R.string.username_empty_error_msg);
                error.setVisibility(TextView.VISIBLE);
            } else {

                error.setVisibility(TextView.INVISIBLE);
                user = new User(uName, uPass);
                repo.inOrUp(user);
                user = repo.getUserByUserNane(uName);

                createDemoForUser(user);

                logIn(user);
            }


        } else {
            error.setText(R.string.account_exists);
            error.setVisibility(TextView.VISIBLE);
        }
    }

    private void createDemoForUser(User user) {

        String termDemo = "Term Demo";
        String courseDemo = "Course Demo";
        Repository repo = new Repository(getApplication());
        repo.inOrUp(new Term(termDemo, user.getId()));

    }

    private void logIn(User user) {
        error.setText(R.string.login_success);
        error.setTextColor(ColorStateList.valueOf(Color.BLUE));
        error.setVisibility(TextView.VISIBLE);
        Intent intent = new Intent(this, TermList.class);
        intent.putExtra(User.ID_KEY, user.getId());
        startActivity(intent);
    }

}
