package tech.dsckiet.Projects;


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

import java.util.ArrayList;
import java.util.Objects;

import tech.dsckiet.BaseClass;
import tech.dsckiet.R;
import tech.dsckiet.Stories.ModelStories;
import tech.dsckiet.Team.ModelTeam;
import tech.dsckiet.Team.TeamAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectFragment extends Fragment {

    private String URL = null;
    RequestQueue mTeamQueue;
    private ProjectAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private Context mContext;


    public ProjectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_project,container,false);

        //INITIALIZATION
        mRecyclerView = view.findViewById(R.id.recycler_view_project);
        setData();
        return view;
    }

    private void setData() {

        URL = BaseClass.getInstance().BASE_URL_PROJECT;
        mTeamQueue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));
        final ArrayList<ModelProject> list = new ArrayList<>();
        final JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            Log.d("getxxx", URL);

                            System.out.println("Response when HTTPCONNECT" + response);

                            String jsonData = response.toString();
                            JSONObject obj = new JSONObject(jsonData);
                            JSONArray arr = obj.getJSONArray("ideas");

                            for (int i = 0; i < arr.length(); i++) {

                                String title = arr.getJSONObject(i).getString("title");
                                String desc = arr.getJSONObject(i).getString("description");

                                list.add(new ModelProject(title, desc));




                            }



                            mAdapter = new ProjectAdapter(list, getContext());
                            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            mRecyclerView.setNestedScrollingEnabled(true);
                            mRecyclerView.setHasFixedSize(true);
                            mRecyclerView.setLayoutManager(layoutManager);
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
