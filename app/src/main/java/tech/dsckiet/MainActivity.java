package tech.dsckiet;

import android.support.design.bottomappbar.BottomAppBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import tech.dsckiet.BottomSheet.RoundedBottomSheetDialogFragment;

public class MainActivity extends AppCompatActivity {

    private RoundedBottomSheetDialogFragment bottomSheetDialog;
    BottomAppBar bottomAppBar;
    private int selectedFragmentIndex = 1;

    //TODO:: AttachBaseContext()

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomAppBar = findViewById(R.id.bottom_app_bar);

        setSupportActionBar(bottomAppBar);

//        if(savedInstanceState == null){
//            updateFragment(selectedFragmentIndex);
//        }

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
//            BottomSheetDialog dialog = new BottomSheetDialog(getContext(),R.style.BottomSheetDialogTheme);
//            bottomSheetDialog.setStyle(R.style.BottomSheetDialogTheme);

            bottomSheetDialog.show(getSupportFragmentManager(),"bottom sheet");
        }
//        bottomSheetDialog.show(getSupportFragmentManager(),"bottom sheet");
    }
    private void updateFragment(int index){

    }
}
