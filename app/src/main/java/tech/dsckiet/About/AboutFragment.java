package tech.dsckiet.About;


import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsIntent;
import android.support.customtabs.CustomTabsServiceConnection;
import android.support.customtabs.CustomTabsSession;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import tech.dsckiet.R;

public class AboutFragment extends Fragment {


    public AboutFragment() {
        
    }
    private CustomTabsServiceConnection customTabsServiceConnection;
    private CustomTabsClient mClient;
    CustomTabsSession customTabsSession;

    ImageView aboutLink,aboutGithub,aboutInstagram;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        aboutLink = view.findViewById(R.id.about_link);
        aboutGithub = view.findViewById(R.id.about_github);
        aboutInstagram = view.findViewById(R.id.about_instagram);

        aboutLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customTabLinking("http://www.dsckiet.tech");
            }
        });

        aboutGithub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customTabLinking("https://github.com/dsckiet");
            }
        });

        aboutInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customTabLinking("https://www.instagram.com/dsckiet/");
            }
        });
        return view;

    }

    public void customTabLinking(String url) {
        customTabsServiceConnection = new CustomTabsServiceConnection() {
            @Override
            public void onCustomTabsServiceConnected(ComponentName componentName, CustomTabsClient customTabsClient) {
                //pre-warning means to fast the surfing
                mClient = customTabsClient;
                mClient.warmup(0L);
                customTabsSession = mClient.newSession(null);
            }


            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                mClient = null;
            }
        };
        CustomTabsClient.bindCustomTabsService(getContext(), "com.android.chrome", customTabsServiceConnection);
        Uri uri = Uri.parse(url);

        //Create an Intent Builder
        CustomTabsIntent.Builder intentBuilder = new CustomTabsIntent.Builder();
        intentBuilder.setToolbarColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        intentBuilder.setSecondaryToolbarColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));

        //Set Start and Exit Animations
        intentBuilder.setStartAnimations(getContext(), R.anim.slide_in_right, R.anim.slide_out_left);
        intentBuilder.setExitAnimations(getContext(), android.R.anim.slide_in_left, android.R.anim.slide_out_right);

        //build custom tabs intent
        CustomTabsIntent customTabsIntent = intentBuilder.build();
        customTabsIntent.intent.setPackage("com.android.chrome");
        customTabsIntent.intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        customTabsIntent.intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        intentBuilder.setShowTitle(true);
        intentBuilder.enableUrlBarHiding();

        customTabsIntent.launchUrl(getContext(), uri);

    }
}
