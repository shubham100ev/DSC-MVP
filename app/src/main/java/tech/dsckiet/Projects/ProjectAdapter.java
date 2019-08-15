package tech.dsckiet.Projects;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsIntent;
import android.support.customtabs.CustomTabsServiceConnection;
import android.support.customtabs.CustomTabsSession;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import tech.dsckiet.R;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.MyViewHolder> {

    private List<ModelProject> mList;
    private Context mContext;
    private ModelProject mModel;

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle,tvDesc;


       public MyViewHolder(@NonNull View itemView) {
           super(itemView);

         tvTitle = itemView.findViewById(R.id.tv_project_title);
         tvDesc = itemView.findViewById(R.id.tv_project_desc);

       }


   }

   ProjectAdapter(List<ModelProject> list, Context context){
       this.mList = list;
       this.mContext = context;
   }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_project_card, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        mModel = mList.get(position);

        holder.tvTitle.setText(mModel.getmTitle());
        holder.tvDesc.setText(mModel.getmDesc());
    }
    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

}
