package tech.dsckiet.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import tech.dsckiet.HomeActivity;
import tech.dsckiet.MainActivity;
import tech.dsckiet.R;

public class IntroActivity extends AppCompatActivity {

    CardView continueBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        continueBtn = findViewById(R.id.continue_btn_intro);
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
