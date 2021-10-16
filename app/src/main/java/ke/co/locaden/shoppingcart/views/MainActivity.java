package ke.co.locaden.shoppingcart.views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import ke.co.locaden.R;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 5000;
    private static final String TAG = "Main Activity";

    Animation topAnim, bottomAnim;
    TextView logo, quote;
    ImageView cars2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager
                .LayoutParams.FLAG_FULLSCREEN);*/
        setContentView(R.layout.activity_main);
        startBackgroundTask();


        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        cars2 = findViewById(R.id.cars2);
        logo = findViewById(R.id.logo);
        quote = findViewById(R.id.quote);

        cars2.setAnimation(topAnim);
        logo.setAnimation(bottomAnim);
        quote.setAnimation(bottomAnim);

        new Handler().postDelayed((Runnable) () -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);

            Pair[] pairs = new Pair[2];
            pairs[0] = new Pair<View, String>(cars2, "app_name");
            pairs[1] = new Pair<View, String>(logo, "app_name");

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation
                        (MainActivity.this, pairs);
                startActivity(intent, options.toBundle());
            }

        }, SPLASH_SCREEN);
    }

    private void startBackgroundTask() {
   try {
       BackgroundUtil.scheduleBackgroundTask(this);

   } catch (Exception e) {
       Log.e(TAG,"Start background Task: ",e);
   }
    }
}
