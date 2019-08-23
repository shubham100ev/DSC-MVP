package tech.dsckiet;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.bottomappbar.BottomAppBar;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import tech.dsckiet.About.AboutFragment;
import tech.dsckiet.BottomSheet.RoundedBottomSheetDialogFragment;
import tech.dsckiet.CodeConduct.CodeConductFragment;
import tech.dsckiet.Events.EventsFragment;
import tech.dsckiet.LeaderBoard.LeaderBoardFragment;
import tech.dsckiet.Projects.ProjectFragment;
import tech.dsckiet.Stories.StoriesFragment;
import tech.dsckiet.Team.TeamFragment;

public class MainActivity extends AppCompatActivity implements RoundedBottomSheetDialogFragment.NavClickListener {


    private RoundedBottomSheetDialogFragment bottomSheetDialog;
    BottomAppBar bottomAppBar;
    private CoordinatorLayout coordinatorLayoutMain;
    private int count = 1;
    private Boolean doubleBackPress = false;

    public static int selectedFragmentIndex = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomAppBar = findViewById(R.id.bottom_app_bar);
        coordinatorLayoutMain = findViewById(R.id.coordinator_main);

        setSupportActionBar(bottomAppBar);

        Intent intent = getIntent();
        System.out.println(">>>>>>>>"+intent.getStringExtra("Fragment"));
        selectedFragmentIndex =  Integer.parseInt(intent.getStringExtra("Fragment"));
        updateFragment(selectedFragmentIndex);

        bottomAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheet();
            }
        });

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    "MyNotifications","MyNotifications", NotificationManager.IMPORTANCE_DEFAULT
            );

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);


        }

        FirebaseMessaging.getInstance().subscribeToTopic("education")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Successfull";
                        if(!task.isSuccessful()){
                            msg = "failed";
                        }
                    }
                });
    }


    private void showBottomSheet(){
        bottomSheetDialog = new RoundedBottomSheetDialogFragment();
        if(getApplicationContext()!= null){
            bottomSheetDialog.navclickListener = this;
            bottomSheetDialog.show(getSupportFragmentManager(),"bottom sheet");
        }
        bottomSheetDialog.setCancelable(true);
    }

    private void updateFragment(int index){
        switch (index){
            case 1:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new ProjectFragment()).commit();
                count = 1;
                break;
            case 2:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new TeamFragment()).commit();
                count = 2;
                break;
            case 3:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new StoriesFragment()).commit();
                count = 3;
                break;
            case 4:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new EventsFragment()).commit();
                count = 4;
                break;
            case 5:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new LeaderBoardFragment()).commit();
                count = 5;
                break;
            case 6:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new CodeConductFragment()).commit();
                count = 6;
                break;
            case 7:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new AboutFragment()).commit();
                count = 7;
                break;
            default:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new ProjectFragment()).commit();
                count = 1;
                break;
        }
    }


     public void onNavItemClicked(int index){
        try {
            bottomSheetDialog.dismiss();
            selectedFragmentIndex = index;
            updateFragment(index);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {

//            finish();
      {
            if (doubleBackPress == true) {
                super.onBackPressed();
            } else {
                doubleBackPress = true;
                final CoordinatorLayout coordinatorLayout = findViewById(R.id.coordinator_main);
                Snackbar.make(coordinatorLayout, "Press back again to exit", Snackbar.LENGTH_LONG).show();
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        doubleBackPress = false;
                    }
                }, 2000);
            }
        }
    }
}
