package tech.dsckiet.Team;

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

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.MyViewHolder> {

    private List<ModelTeam> mList;
    private Context mContext;
    private ModelTeam mModel;
    private CustomTabsServiceConnection customTabsServiceConnection;
    private CustomTabsClient mClient;
    CustomTabsSession customTabsSession;

    class MyViewHolder extends RecyclerView.ViewHolder {

       TextView tvTeamName,tvTeamRole;
       ImageView ivUserImage,emailIcon,linkedinIcon,githubIcon,twitterIcon,websiteIcon;
       RelativeLayout layoutTeamCard;
       LinearLayout layoutSubItem;

       public MyViewHolder(@NonNull View itemView) {
           super(itemView);
           tvTeamName = itemView.findViewById(R.id.tv_team_name);
           tvTeamRole = itemView.findViewById(R.id.tv_team_role);
           ivUserImage = itemView.findViewById(R.id.iv_team_user_image);
           layoutTeamCard = itemView.findViewById(R.id.layout_team_card);
           layoutSubItem = itemView.findViewById(R.id.sub_item);

           emailIcon = itemView.findViewById(R.id.mail_icon);
           linkedinIcon = itemView.findViewById(R.id.linkedin_icon);
           githubIcon = itemView.findViewById(R.id.github_icon);
           twitterIcon = itemView.findViewById(R.id.twitter_icon);
           websiteIcon = itemView.findViewById(R.id.link_icon);

       }


   }

   TeamAdapter(List<ModelTeam> list, Context context){
       this.mList = list;
       this.mContext = context;
   }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_team_card, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        mModel = mList.get(position);
        holder.tvTeamName.setText(mModel.getmName());
        holder.tvTeamRole.setText(mModel.getmRole());

        if(mModel.getmEmail().equals("")){
            holder.emailIcon.setVisibility(View.GONE);
        }
        if(mModel.getmLinkedin().equals("")){
            holder.linkedinIcon.setVisibility(View.GONE);
        }
        if(mModel.getmGithub().equals("")){
            holder.githubIcon.setVisibility(View.GONE);
        }
        if(mModel.getmTwitter().equals("")){
            holder.twitterIcon.setVisibility(View.GONE);
        }
        if(mModel.getmWebsite().equals("")){
            holder.websiteIcon.setVisibility(View.GONE);
        }

        holder.emailIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto: " + mList.get(position).getmEmail()));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Talk to DSC Core :)");
                if (intent.resolveActivity(mContext.getPackageManager()) != null) {
                    mContext.startActivity(intent);
                }
            }
        });

        holder.linkedinIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customTabLinking(mList.get(position).getmLinkedin());
            }
        });

        holder.githubIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customTabLinking(mList.get(position).getmGithub());
            }
        });

        holder.twitterIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customTabLinking(mList.get(position).getmTwitter());
            }
        });

        holder.websiteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customTabLinking(mList.get(position).getmWebsite());
            }
        });



        Picasso.get().load(mModel.getmImage()).fit().centerInside().into(holder.ivUserImage);


        holder.layoutTeamCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean expanded = mModel.isExpanded();
                holder.layoutSubItem.setVisibility(expanded ? View.VISIBLE : View.GONE);

                mModel.setExpanded(!expanded);
//                notifyItemChanged(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
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
        CustomTabsClient.bindCustomTabsService(mContext, "com.android.chrome", customTabsServiceConnection);
        Uri uri = Uri.parse(url);

        //Create an Intent Builder
        CustomTabsIntent.Builder intentBuilder = new CustomTabsIntent.Builder();
        intentBuilder.setToolbarColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
        intentBuilder.setSecondaryToolbarColor(ContextCompat.getColor(mContext, R.color.colorPrimaryDark));

        //Set Start and Exit Animations
        intentBuilder.setStartAnimations(mContext, R.anim.slide_in_right, R.anim.slide_out_left);
        intentBuilder.setExitAnimations(mContext, android.R.anim.slide_in_left, android.R.anim.slide_out_right);

        //build custom tabs intent
        CustomTabsIntent customTabsIntent = intentBuilder.build();
        customTabsIntent.intent.setPackage("com.android.chrome");
        customTabsIntent.intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        customTabsIntent.intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        intentBuilder.setShowTitle(true);
        intentBuilder.enableUrlBarHiding();

        customTabsIntent.launchUrl(mContext, uri);

    }
}
