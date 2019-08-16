package tech.dsckiet.Team;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import tech.dsckiet.BaseClass;
import tech.dsckiet.Events.EventAdapter;
import tech.dsckiet.Events.ModelEvent;
import tech.dsckiet.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeamFragment extends Fragment {

    private String URL = null;
    RequestQueue mTeamQueue;
    private TeamAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private Context mContext;


    public TeamFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_team,container,false);

        //INITIALIZATION
        mRecyclerView = view.findViewById(R.id.recycler_view_team);

        setData();
        return view;
    }

    private void setData() {

        URL = BaseClass.getInstance().BASE_URL_TEAM;
        mTeamQueue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));
        final ArrayList<ModelTeam> list1 = new ArrayList<>();
        final ArrayList<ModelTeam> list2 = new ArrayList<>();
        final JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            Log.d("getxxx", URL);

                            System.out.println("Response when HTTPCONNECT" + response);

                            String jsonData = response.toString();
                            JSONObject obj = new JSONObject(jsonData);
                            JSONArray arr = obj.getJSONArray("team");

                                    for (int i = 0; i < arr.length(); i++) {

                                        String name = arr.getJSONObject(i).getString("name");
                                        String role = arr.getJSONObject(i).getString("role");
                                        String userImageURL = BaseClass.getInstance().BASE_URL + arr.getJSONObject(i).getString("photo");

                                        String status = arr.getJSONObject(i).getString("status");

                                        String email = arr.getJSONObject(i).getString("email");
                                        String linkedin = arr.getJSONObject(i).getString("linkedin");
                                        String github = arr.getJSONObject(i).getString("github");
                                        String twitter = arr.getJSONObject(i).getString("twitter");
                                        String website = arr.getJSONObject(i).getString("website");


                                        if(status.equals("1")) {
                                            list1.add(new ModelTeam(name, role, userImageURL,email,linkedin,github,twitter,website));
                                        }else if(status.equals("2")){
                                            list2.add(new ModelTeam(name, role, userImageURL,email,linkedin,github,twitter,website));
                                        }




                            }

                            list1.addAll(list2);


                            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            mRecyclerView.setNestedScrollingEnabled(true);
                            mRecyclerView.setHasFixedSize(true);
                            ((SimpleItemAnimator) mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
                            mRecyclerView.setLayoutManager(layoutManager);
                            mAdapter = new TeamAdapter(list1, getContext(),mRecyclerView);
                            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                            mRecyclerView.setAdapter(mAdapter);

                            mAdapter.notifyDataSetChanged();
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
        mTeamQueue.add(objectRequest);
    }
}
