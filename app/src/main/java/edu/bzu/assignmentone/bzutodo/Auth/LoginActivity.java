package edu.bzu.assignmentone.bzutodo.Auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import edu.bzu.assignmentone.bzutodo.R;
import edu.bzu.assignmentone.bzutodo.userdashboard.UserDashboardActivity;

public class LoginActivity extends AppCompatActivity {

    Button signInButton ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initateViews ();

        signInButton.setOnClickListener( e ->  {

            Intent openUserDBA = new Intent(LoginActivity.this, UserDashboardActivity.class);
            startActivity(openUserDBA);
            LoginActivity.this.finish();
        });
    }

    public void initateViews () {
        signInButton = findViewById(R.id.signInButton);
    }
}