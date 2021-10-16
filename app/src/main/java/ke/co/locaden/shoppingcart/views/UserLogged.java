package ke.co.locaden.shoppingcart.views;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.data.model.User;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import ke.co.locaden.R;

public class UserLogged extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "UserLogged";
    private static final String ARG_NAME = "username";
    public static void startActivity(Context context, String    username) {
        Intent intent = new Intent(context, UserLogged.class);
        intent.putExtra(ARG_NAME, username);
        context.startActivity(intent);
    }
    FirebaseAuth firebaseAuth;
    EditText fullName,username,age;
    Button update;
    GoogleSignInClient googleSignInClient;
    DatabaseReference databaseUser;

@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_logged);

        databaseUser = FirebaseDatabase.getInstance().getReference("users");

        findViewById(R.id.buttonLogout).setOnClickListener(this);
        findViewById(R.id.fullName).setOnClickListener(this);
        findViewById(R.id.username).setOnClickListener(this);
        findViewById(R.id.age).setOnClickListener(this);
        findViewById(R.id.update).setOnClickListener(this);
        findViewById(R.id.addCar).setOnClickListener(this);
        findViewById(R.id.goShop).setOnClickListener(this);
        findViewById(R.id.buttonDisconnect).setOnClickListener(this);
        googleSignInClient = GoogleSignIn.getClient(this, GoogleSignInOptions.DEFAULT_SIGN_IN);
        firebaseAuth = FirebaseAuth.getInstance();
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.goShop:
            startActivity(new Intent(this,ShopActivity.class));
            break;
            case R.id.buttonLogout:
                signOut();
                break;
            case R.id.buttonDisconnect:
                revokeAccess();
                break;
            case R.id.addCar:
                startActivity(new Intent(this,ContentProvider.class));
                break;
            case R.id.update:
                update();
                break;
        }
    }

    private void update() {
    String  name = fullName.getText().toString().trim();
    String usernam = username.getText().toString();
    String ageUser = age.getText().toString();
    if (!TextUtils.isEmpty(name)){

        String id = databaseUser.push().getKey();
        UpdateInfo updateinfo =  new UpdateInfo(id,name,usernam,ageUser);
        databaseUser.child(id).setValue(updateinfo);
        Toast.makeText(this,"User info update",Toast.LENGTH_LONG).show();
    }else {
        Toast.makeText(this,"You Should Enter name",Toast.LENGTH_LONG).show();
    }
        if (!TextUtils.isEmpty(usernam)){

        }else {
            Toast.makeText(this,"You Should Enter username",Toast.LENGTH_LONG).show();
        }
        if (!TextUtils.isEmpty(ageUser)){

        }else {
            Toast.makeText(this,"You Should Enter your Age",Toast.LENGTH_LONG).show();
        }
    }

    private void signOut() {
        // Firebase sign out
        firebaseAuth.signOut();
        // Google sign out
        // Google sign out
        // Google sign out
        googleSignInClient.signOut().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // Google Sign In failed, update UI appropriately
                        Log.w(TAG, "Signed out of google");
                    }
                });
    }
    private void revokeAccess() {
        // Firebase sign out
        firebaseAuth.signOut();
        // Google revoke access
        googleSignInClient.revokeAccess().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // Google Sign In failed, update UI appropriately
                        Log.w(TAG, "Revoked Access");
                    }
                });
    }
}