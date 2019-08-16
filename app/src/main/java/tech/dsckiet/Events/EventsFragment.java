package tech.dsckiet.Events;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

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
import tech.dsckiet.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventsFragment extends Fragment {


    private String URL = null;
    RequestQueue mEventQueue;
    private EventAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private Context mContext;


    private RadioGroup mRadioGroup;
    private RadioButton mRadioButton,mRadioButton1;


    private LinearLayout mLayoutNoItem,mLayoutItem;


    public EventsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events,container,false);





        //INITIALIZATION
        mRecyclerView = view.findViewById(R.id.recycler_view_events);
        mRadioGroup = view.findViewById(R.id.radio_group);
        mRadioButton1 = view.findViewById(R.id.radio_upcoming);
        mRadioButton1.setChecked(true);
        mLayoutItem = view.findViewById(R.id.layout_events);
        mLayoutNoItem = view.findViewById(R.id.layout_no_events);

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                mRadioButton = group.findViewById(checkedId);
                if(mRadioButton != null && checkedId > -1){
                    if(mRadioButton.getText().equals("Upcoming")){
                        setData(1); //1 for upcoming
                    }else if(mRadioButton.getText().equals("Past Events")){
                        setData(2); //2 for past events
                    }
                }
            }
        });




        setData(1);

        return view;
    }

    private void setData(final int pos) {

            URL = BaseClass.getInstance().BASE_URL_EVENTS;
            mEventQueue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));
        final ArrayList<ModelEvent> list = new ArrayList<>();
            final JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, URL, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {

                                Log.d("getxxx", URL);

                                System.out.println("Response when HTTPCONNECT" + response);

                                String jsonData = response.toString();
                                JSONObject obj = new JSONObject(jsonData);
                                JSONArray arr1 = obj.getJSONArray("upcomingevents");
                                JSONArray arr2 = obj.getJSONArray("pastevents");

                                System.out.println("Length of array 2" +  ">>" +arr2.length() +"& Length of array1 :" + arr1.length());

                                if(pos == 1) {
                                    //UPCOMING
                                    if(arr1.length() == 0) {
                                        mLayoutItem.setVisibility(View.GONE);
                                        mLayoutNoItem.setVisibility(View.VISIBLE);
                                    }else{
                                        mLayoutItem.setVisibility(View.VISIBLE);
                                        mLayoutNoItem.setVisibility(View.GONE);
                                        for (int i = 0; i < arr1.length(); i++) {

                                            String title = arr1.getJSONObject(i).getString("title");
                                            String startDate = arr1.getJSONObject(i).getString("startDate");
                                            String venue = arr1.getJSONObject(i).getString("venue");
                                            String startTime = arr1.getJSONObject(i).getString("startTime");

                                            //2019-02-20T05:00:00.000Z
                                            String[] part = startDate.split("T");
                                            String date = part[0];

                                            @SuppressLint("SimpleDateFormat") Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
                                            @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd,yyyy");
                                            String time = startTime + ", " + dateFormat.format(date1);

                                            list.add(new ModelEvent(title, time, venue));
                                        }
                                    }


                                }else if(pos == 2){
                                    //PAST
                                    if(arr2.length() == 0) {
                                        mLayoutItem.setVisibility(View.GONE);
                                        mLayoutNoItem.setVisibility(View.VISIBLE);
                                    }else{
                                        mLayoutItem.setVisibility(View.VISIBLE);
                                        mLayoutNoItem.setVisibility(View.GONE);
                                        for (int i = 0; i < arr2.length(); i++) {

                                            String title = arr2.getJSONObject(i).getString("title");
                                            String startDate = arr2.getJSONObject(i).getString("startDate");
                                            String venue = arr2.getJSONObject(i).getString("venue");
                                            String startTime = arr2.getJSONObject(i).getString("startTime");

                                            //2019-02-20T05:00:00.000Z
                                            String[] part = startDate.split("T");
                                            String date = part[0];

                                            @SuppressLint("SimpleDateFormat") Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
                                            @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd,yyyy");
                                            String time = startTime + ", " + dateFormat.format(date1);

                                            list.add(new ModelEvent(title, time, venue));
                                        }
                                    }

                                }



                                mAdapter = new EventAdapter(list, mContext);
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
            mEventQueue.add(objectRequest);
        }

}
