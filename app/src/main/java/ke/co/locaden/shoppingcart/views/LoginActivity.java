package ke.co.locaden.shoppingcart.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.text.method.SingleLineTransformationMethod;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import ke.co.locaden.R;


public class LoginActivity
        extends AppCompatActivity {

    private EditText login;
    private EditText new_member;
    private ImageView gmail_login_icon;
    private ImageView facebook_login_icon;
    private ImageView twitter_login_icon;
    private TextView forgotPass;
    private Button password;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         /* getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager
                .LayoutParams.FLAG_FULLSCREEN);*/
        setContentView(R.layout.activity_login);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();


        login = findViewById(R.id.login);
        new_member = findViewById(R.id.new_member);
        gmail_login_icon = findViewById(R.id.gmail_login_icon);
        facebook_login_icon = findViewById(R.id.facebook_login_icon);
        twitter_login_icon = findViewById(R.id.twitter_login_icon);
        forgotPass = findViewById(R.id.forgotPass);

        facebook_login_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignedInWith();
            }
        });
        twitter_login_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openContentProvider();
            }
        });
        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openForgotPass();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUserLogged();
            }
        });
        new_member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUserRegister();
            }
        });
        gmail_login_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignedInWith();
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
    public void openContentProvider(){
        Intent intent = new Intent(this,ContentProvider.class);
        startActivity(intent);
    }
    public void openSignedInWith(){
        Intent intent = new Intent(this,SignedInWith.class);
        startActivity(intent);
    }
    public void openUserRegister() {
        Intent intent = new Intent(this,UserRegister.class);
        startActivity(intent);
    }
    public void openUserLogged(){
        Intent intent = new Intent(this,UserLogged.class);
        startActivity(intent);
    }
    public void openForgotPass() {
        Intent intent = new Intent(this, ForgotPass.class);
        startActivity(intent);
    }

}