package tech.dsckiet;

import android.support.design.bottomappbar.BottomAppBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

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
        bottomSheetDialog.setCancelable(true);
    }

    private void updateFragment(int index){
        switch (index){
            case 1:
                replaceFragment(new ProjectFragment());
                break;
            case 2:
                replaceFragment(new TeamFragment());
                break;
            case 3:
                replaceFragment(new StoriesFragment());
                break;
            case 4:
                replaceFragment(new EventsFragment());
                break;
            case 5:
                replaceFragment(new CodeConductFragment());
                break;
            case 6:
                replaceFragment(new AboutFragment());
                break;
            default:
                replaceFragment(new ProjectFragment());
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

    private void replaceFragment(Fragment fragment) {
        String backStateName = fragment.getClass().getName();
        FragmentManager manager = getSupportFragmentManager();
        boolean fragmentPopped = manager.popBackStackImmediate(backStateName, 0);

        if (!fragmentPopped && manager.findFragmentByTag(backStateName) == null) {
            // fragment not in back stack, create it
            FragmentTransaction fragmentTransaction = manager.beginTransaction();
            fragmentTransaction.replace(R.id.container, fragment, backStateName);
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            fragmentTransaction.addToBackStack(backStateName);
            fragmentTransaction.commit();
        }
    }
}
