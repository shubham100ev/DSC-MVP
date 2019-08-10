package tech.dsckiet;

import android.support.design.bottomappbar.BottomAppBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import tech.dsckiet.About.AboutFragment;
import tech.dsckiet.BottomSheet.RoundedBottomSheetDialogFragment;
import tech.dsckiet.CodeConduct.CodeConductFragment;
import tech.dsckiet.Events.EventsFragment;
import tech.dsckiet.Projects.ProjectFragment;
import tech.dsckiet.Stories.StoriesFragment;
import tech.dsckiet.Team.TeamFragment;

public class MainActivity extends AppCompatActivity implements RoundedBottomSheetDialogFragment.NavClickListener {


    private RoundedBottomSheetDialogFragment bottomSheetDialog;
    BottomAppBar bottomAppBar;


    public static int selectedFragmentIndex = 1;

    //TODO:: AttachBaseContext()

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomAppBar = findViewById(R.id.bottom_app_bar);

        setSupportActionBar(bottomAppBar);

        if(savedInstanceState == null){
            updateFragment(selectedFragmentIndex);
        }

        bottomAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheet();
            }
        });


    }


    private void showBottomSheet(){
        bottomSheetDialog = new RoundedBottomSheetDialogFragment();
        if(getApplicationContext()!= null){
            bottomSheetDialog.navclickListener = this;
            bottomSheetDialog.show(getSupportFragmentManager(),"bottom sheet");
        }
    }

    private void updateFragment(int index){
        switch (index){
            case 1:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new ProjectFragment()).commit();
                break;
            case 2:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new TeamFragment()).commit();
                break;
            case 3:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new StoriesFragment()).commit();
                break;
            case 4:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new EventsFragment()).commit();
                break;
            case 5:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new CodeConductFragment()).commit();
                break;
            case 6:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new AboutFragment()).commit();
                break;
            default:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new AboutFragment()).commit();
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
}
