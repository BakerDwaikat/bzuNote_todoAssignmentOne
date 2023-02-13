package edu.bzu.assignmentone.bzutodo.Auth;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ComponentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

import edu.bzu.assignmentone.bzutodo.R;
import edu.bzu.assignmentone.bzutodo.models.Student;
import edu.bzu.assignmentone.bzutodo.userdashboard.UserDashboardActivity;

public class LoginActivity extends AppCompatActivity {

    private Button signInButton ;
    private TextInputEditText studentIDTF;
    private TextInputEditText studentPassTF;
    private HashMap<Integer,StudentAuth> studentMapAuth ;
    private FirebaseFirestore firebasedb ;
    private CollectionReference userAuthCollection ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initateViews ();
        FirebaseApp.initializeApp(this);
        firebasedb  = FirebaseFirestore.getInstance();
        signInButton.setOnClickListener( e -> checkUserValidation ());
        userAuthCollection = firebasedb.collection("userAuth");
    }

    public void initateViews () {

        signInButton = findViewById(R.id.signInButton);
        studentIDTF = findViewById(R.id.userIDTextInputEditText);
        studentPassTF = findViewById(R.id.passwordTextInputEditText);
    }

    public void checkUserValidation () {

        if (!studentIDTF.getEditableText().toString().equals("") && !studentPassTF.getEditableText().toString().equals(""))  {

            DocumentReference userDoc =  userAuthCollection.document(studentIDTF.getEditableText().toString().trim());
            userDoc.get()
                    .addOnSuccessListener(documentSnapshot -> {
                        if (documentSnapshot.exists()) {

                            String userId = documentSnapshot.getId();
                            String userPass = documentSnapshot.getString(studentIDTF.getEditableText().toString());

                            if (studentIDTF.getEditableText().toString().equals(userId)
                                && studentPassTF.getEditableText().toString().equals(userPass) ) {
                                Intent openUserDBA = new Intent(LoginActivity.this, UserDashboardActivity.class);
                                startActivity(openUserDBA);
                                LoginActivity.this.finish();
                            }
                        }
                    }).addOnFailureListener(e -> {
                        Toast.makeText(this, "Please Check student ID or Password ", Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "error loggin in");
                    });
        }else
            Toast.makeText(this, "Please Fill the Blanks", Toast.LENGTH_SHORT).show();

    }

}