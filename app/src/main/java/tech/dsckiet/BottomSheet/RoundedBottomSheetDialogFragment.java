package tech.dsckiet.BottomSheet;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import tech.dsckiet.R;

import static tech.dsckiet.MainActivity.selectedFragmentIndex;

public class RoundedBottomSheetDialogFragment extends BottomSheetDialogFragment {


    LinearLayout layoutnavMain, layoutnavProjects, layoutnavTeam, layoutnavStories, layoutnavEvents,layoutnavLeaderboard, layoutnavCodeConduct, layoutnavAbout;
    TextView textnavMain, textnavProjects, textnavTeam, textnavStories, textnavEvents,textnavLeaderboard, textnavCodeConduct, textnavAbout;

    public NavClickListener navclickListener = null;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_sheet_nav, container, false);
        layoutnavMain = (LinearLayout) view.findViewById(R.id.layout_nav_main);
        layoutnavProjects = (LinearLayout) view.findViewById(R.id.layout_nav_projects);
        layoutnavTeam = (LinearLayout) view.findViewById(R.id.layout_nav_team);
        layoutnavStories = (LinearLayout) view.findViewById(R.id.layout_nav_stories);
        layoutnavEvents = (LinearLayout) view.findViewById(R.id.layout_nav_events);
        layoutnavLeaderboard = (LinearLayout) view.findViewById(R.id.layout_nav_leader_board);
        layoutnavCodeConduct = (LinearLayout) view.findViewById(R.id.layout_nav_code_conduct);
        layoutnavAbout = (LinearLayout) view.findViewById(R.id.layout_nav_about);

        textnavMain = (TextView) view.findViewById(R.id.text_nav_main);
        textnavProjects = (TextView) view.findViewById(R.id.text_nav_projects);
        textnavTeam = (TextView) view.findViewById(R.id.text_nav_team);
        textnavStories = (TextView) view.findViewById(R.id.text_nav_stories);
        textnavEvents = (TextView) view.findViewById(R.id.text_nav_events);
        textnavLeaderboard = (TextView) view.findViewById(R.id.text_nav_leader_board);
        textnavCodeConduct = (TextView) view.findViewById(R.id.text_nav_code_conduct);
        textnavAbout = (TextView) view.findViewById(R.id.text_nav_about);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupNavListViews();
        updateNavList(selectedFragmentIndex);
    }

    private void setupNavListViews() {
        layoutnavMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navclickListener.onNavItemClicked(0);
            }
        });

        layoutnavProjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navclickListener.onNavItemClicked(1);
            }
        });

        layoutnavTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navclickListener.onNavItemClicked(2);
            }
        });

        layoutnavStories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navclickListener.onNavItemClicked(3);
            }
        });

        layoutnavEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navclickListener.onNavItemClicked(4);
            }
        });

        layoutnavLeaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navclickListener.onNavItemClicked(5);
            }
        });

        layoutnavCodeConduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navclickListener.onNavItemClicked(6);
            }
        });

        layoutnavAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navclickListener.onNavItemClicked(7);
            }
        });

        resetBackgrounds();
    }

    private void resetBackgrounds() {
        textnavMain.setBackgroundResource(0);
        textnavProjects.setBackgroundResource(0);
        textnavTeam.setBackgroundResource(0);
        textnavStories.setBackgroundResource(0);
        textnavEvents.setBackgroundResource(0);
        textnavLeaderboard.setBackgroundResource(0);
        textnavCodeConduct.setBackgroundResource(0);
        textnavAbout.setBackgroundResource(0);

    }

    private void updateNavList(int index) {
        int bgResourceId = R.drawable.bg_nav_sheet_selection_gradient;
        switch (index) {
            case 0:
                textnavMain.setBackgroundResource(bgResourceId);
                break;
            case 1:
                textnavProjects.setBackgroundResource(bgResourceId);
                break;
            case 2:
                textnavTeam.setBackgroundResource(bgResourceId);
                break;
            case 3:
                textnavStories.setBackgroundResource(bgResourceId);
                break;
            case 4:
                textnavEvents.setBackgroundResource(bgResourceId);
                break;
            case 5:
                textnavLeaderboard.setBackgroundResource(bgResourceId);
                break;
            case 6:
                textnavCodeConduct.setBackgroundResource(bgResourceId);
                break;
            case 7:
                textnavAbout.setBackgroundResource(bgResourceId);
                break;
        }
    }


    public interface NavClickListener {
        void onNavItemClicked(int index);

    }
}