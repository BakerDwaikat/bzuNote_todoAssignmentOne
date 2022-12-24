package edu.bzu.assignmentone.bzutodo.Auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ComponentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;

import edu.bzu.assignmentone.bzutodo.R;
import edu.bzu.assignmentone.bzutodo.models.Student;
import edu.bzu.assignmentone.bzutodo.userdashboard.UserDashboardActivity;

public class LoginActivity extends AppCompatActivity {

    private Button signInButton ;
    private TextInputEditText studentIDTF;
    private TextInputEditText studentPassTF;
    private HashMap<Integer,StudentAuth> studentMapAuth ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initateViews ();

        signInButton.setOnClickListener( e ->  {
            checkUserValidation ();
        });
    }

    public void initateViews () {

        studentMapAuth = new HashMap<>();
        studentMapAuth.put(1191207,new StudentAuth(1191207,"1191207","12345"));
        studentMapAuth.put(1192545,new StudentAuth(1192545,"1192545","56789"));
        studentMapAuth.put(1192772,new StudentAuth(1192772,"1192772","98765"));
        studentMapAuth.put(1234,new StudentAuth(1234,"1234","12345"));

        signInButton = findViewById(R.id.signInButton);
        studentIDTF = findViewById(R.id.userIDTextInputEditText);
        studentPassTF = findViewById(R.id.passwordTextInputEditText);
    }

    public void checkUserValidation () {

        if (!studentIDTF.getEditableText().toString().equals("") && !studentPassTF.getEditableText().toString().equals(""))  {

            StudentAuth entry = (studentMapAuth.get(Integer.parseInt(studentIDTF.getEditableText().toString())));
            if ( entry != null && studentPassTF.getEditableText().toString().equals(entry.getPassword())) {
                Intent openUserDBA = new Intent(LoginActivity.this, UserDashboardActivity.class);
                startActivity(openUserDBA);
                LoginActivity.this.finish();
            }else
                Toast.makeText(this, "Please Check student ID or Password ", Toast.LENGTH_SHORT).show();

        }else
            Toast.makeText(this, "Please Fill the Blanks", Toast.LENGTH_SHORT).show();



    }

}