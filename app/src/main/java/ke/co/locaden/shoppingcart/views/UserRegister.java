package ke.co.locaden.shoppingcart.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import ke.co.locaden.R;

public class UserRegister extends AppCompatActivity {
    private FirebaseAuth mAuth;
    TextInputLayout email1, passwordRegister1;
    private ProgressBar progressBar;
    Button register;
    TextView have_account2;
    private static final String TAG = "UserRegister";

    DatabaseReference databaseUserDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        databaseUserDetails = FirebaseDatabase.getInstance().getReference("users");

// Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        register = findViewById(R.id.register);
        have_account2 = findViewById(R.id.have_account2);
        email1 = findViewById(R.id.email);
        passwordRegister1 = findViewById(R.id.passwordRegister);
        progressBar = findViewById(R.id.progressBar);


        have_account2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginActivity();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
    }


    private void createUser() {

        String email = email1.getEditText().getText().toString();
        String passwordRegister = passwordRegister1.getEditText().getText().toString();


        if (email.isEmpty()) {
            email1.setError("Email is Required");
            email1.requestFocus();
            return;

        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            email1.setError("Invalid Email Address");
            email1.requestFocus();
            return;

        }

        if (passwordRegister.isEmpty()) {
            passwordRegister1.setError("Password is needed");
            passwordRegister1.requestFocus();
            return;

        }
        if (passwordRegister.length() < 6) {
            passwordRegister1.setError("Min password length 6 characters");
            passwordRegister1.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, passwordRegister)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(UserRegister.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });


        /*progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, passwordRegister)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            UserData userData = new UserData(email,passwordRegister);
                            FirebaseDatabase.getInstance().getReference("Users").child
                                    (FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(userData).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(UserRegister.this, "User has been " +
                                                "registered successfully!", Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(UserRegister.this,UserLogged.class));
                                        progressBar.setVisibility(View.VISIBLE);
                                    } else {
                                        Toast.makeText(UserRegister.this, "Failed to Register",
                                                Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });

                        } else {
                            Toast.makeText(UserRegister.this, "Failed to Register",
                                    Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);

                        }
                    }
                });*/
    }

    private void updateUI(FirebaseUser account ) {
        if(account != null){
            Toast.makeText(this,"U Signed In successfully",Toast.LENGTH_LONG).show();
            startActivity(new Intent(this,UserLogged.class));

        }else {
            Toast.makeText(this,"U Didnt signed in",Toast.LENGTH_LONG).show();
        }
    }

    public void openLoginActivity() {
                     Intent intent = new Intent(this, LoginActivity.class);
                        startActivity(intent);
}
}










