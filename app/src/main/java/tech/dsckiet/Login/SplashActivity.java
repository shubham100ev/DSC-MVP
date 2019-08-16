package tech.dsckiet.Login;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import tech.dsckiet.HomeActivity;
import tech.dsckiet.MainActivity;
import tech.dsckiet.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        ImageView logolayout = findViewById(R.id.logo);
        logolayout.animate().alpha(1.0f).scaleX(1.1f).scaleY(1.1f).setDuration(2000);
        Boolean isFirstRun = getSharedPreferences("PREFERENCES", MODE_PRIVATE)
                .getBoolean("isfirstrun", true);
        logolayout.animate().alpha(1.0f).scaleX(1.1f).scaleY(1.1f).setDuration(2500);
        if (isFirstRun) {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(getApplicationContext(), IntroActivity.class);
                    startActivity(i);
                    finish();
                }
            }, 3000);

            getSharedPreferences("PREFERENCES", MODE_PRIVATE).edit()
                    .putBoolean("isfirstrun", false).commit();
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {


                        Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(i);
                        finish();
                        finishActivity(0);
                }
            }, 2600);
        }
    }
}
