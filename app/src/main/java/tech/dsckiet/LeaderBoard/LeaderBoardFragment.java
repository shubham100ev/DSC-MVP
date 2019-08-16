package tech.dsckiet.LeaderBoard;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

import tech.dsckiet.BaseClass;
import tech.dsckiet.Projects.ModelProject;
import tech.dsckiet.Projects.ProjectAdapter;
import tech.dsckiet.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class LeaderBoardFragment extends Fragment {

    private String URL = null;
    RequestQueue mLeaderboardQueue;
    private LeaderBoardAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private Context mContext;
    private com.wang.avi.AVLoadingIndicatorView progressBar;


    public LeaderBoardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_leader_board,container,false);

        //INITIALIZATION
        mRecyclerView = view.findViewById(R.id.recycler_view_leaderboard);
        progressBar = view.findViewById(R.id.progress_leaderboard);

        setData();
        return view;
    }


    private void setData() {

        URL = BaseClass.getInstance().BASE_URL_LEADERBOARD;
        mLeaderboardQueue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));
        final ArrayList<ModelLeaderBoard> list = new ArrayList<>();
        final JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            Log.d("getxxx", URL);

                            System.out.println("Response when HTTPCONNECT" + response);

                            String jsonData = response.toString();
                            JSONObject obj = new JSONObject(jsonData);
                            JSONArray arr = obj.getJSONArray("leaderboard");

                            for (int i = 0; i < arr.length(); i++) {

                                String rank = arr.getJSONObject(i).getString("rank");
                                String score = arr.getJSONObject(i).getString("score");
                                String handle = arr.getJSONObject(i).getString("hacker");

                                list.add(new ModelLeaderBoard(rank,score,handle));




                            }



                            mAdapter = new LeaderBoardAdapter(list, getContext());
                            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            mRecyclerView.setNestedScrollingEnabled(true);
                            mRecyclerView.setHasFixedSize(true);
                            mRecyclerView.setLayoutManager(layoutManager);
                            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                            mRecyclerView.setAdapter(mAdapter);

                            mAdapter.notifyDataSetChanged();
                            progressBar.setVisibility(View.GONE);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        mLeaderboardQueue.add(objectRequest);
    }
}
