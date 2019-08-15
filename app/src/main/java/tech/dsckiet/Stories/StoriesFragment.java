package tech.dsckiet.Stories;


import android.annotation.SuppressLint;
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
public class StoriesFragment extends Fragment {
    private String URL = null;
    RequestQueue mStoryQueue;
    private StoriesAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private Context mContext;
    final ArrayList<ModelStories> list = new ArrayList<>();


    public StoriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stories, container, false);;

        mRecyclerView = view.findViewById(R.id.recycler_view_events);
        setData();


        return view;
    }


    private void setData() {

        URL = BaseClass.getInstance().BASE_URL_STORIES;
        mStoryQueue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));

        final JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            Log.d("getxxx", URL);

                            System.out.println("Response when HTTPCONNECT" + response);

                            String jsonData = response.toString();
                            JSONObject obj = new JSONObject(jsonData);

                            String jsonStory = obj.getString("story");

                            JSONObject objMain = new JSONObject(jsonStory);



                                        String title = objMain.getString("title");
                                        String desc = objMain.getString("description");
                                        //TODO:: TITLE AND DESC
//                                        list.add(new ModelStories(title, desc));
//
//
//                            mAdapter = new StoriesAdapter(list, mContext);
//                            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
//                            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//                            mRecyclerView.setNestedScrollingEnabled(true);
//                            mRecyclerView.setHasFixedSize(true);
//
//                            mRecyclerView.setLayoutManager(layoutManager);
//                            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//                            mRecyclerView.setAdapter(mAdapter);
//
//                            mAdapter.notifyDataSetChanged();
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
        mStoryQueue.add(objectRequest);
    }

}
