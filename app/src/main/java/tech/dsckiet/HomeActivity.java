package tech.dsckiet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }

    public void cardClick(View view) {
        if (view == findViewById(R.id.project_card)) {
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            intent.putExtra("Fragment", "1");
            startActivity(intent);
//            finish();

        } else if (view == findViewById(R.id.team_card)) {
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            intent.putExtra("Fragment", "2");
            startActivity(intent);
//            finish();
        } else if (view == findViewById(R.id.stories_card)) {
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            intent.putExtra("Fragment", "3");
            startActivity(intent);
//            finish();
        } else if (view == findViewById(R.id.events_card)) {
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            intent.putExtra("Fragment", "4");
            startActivity(intent);
//            finish();
        } else if (view == findViewById(R.id.about_card)) {
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            intent.putExtra("Fragment", "7");
            startActivity(intent);
//            finish();
        } else if (view == findViewById(R.id.score_card)) {
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            intent.putExtra("Fragment", "5");
            startActivity(intent);
//            finish();
        }
    }
}
