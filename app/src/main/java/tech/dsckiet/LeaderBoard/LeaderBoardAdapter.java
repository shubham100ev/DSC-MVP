package tech.dsckiet.LeaderBoard;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import tech.dsckiet.Projects.ModelProject;
import tech.dsckiet.R;

public class LeaderBoardAdapter extends RecyclerView.Adapter<LeaderBoardAdapter.MyViewHolder> {

    private List<ModelLeaderBoard> mList;
    private Context mContext;
    private ModelLeaderBoard mModel;

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvHandle,tvRank,tvScore;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvHandle = itemView.findViewById(R.id.leaderboard_handle);
            tvRank = itemView.findViewById(R.id.leaderboard_rank);
            tvScore = itemView.findViewById(R.id.leaderboard_score);


        }


    }

    LeaderBoardAdapter(List<ModelLeaderBoard> list, Context context){
        this.mList = list;
        this.mContext = context;
    }

    @NonNull
    @Override
    public LeaderBoardAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_contest_card, viewGroup, false);

        return new LeaderBoardAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final LeaderBoardAdapter.MyViewHolder holder, final int position) {
        mModel = mList.get(position);

        holder.tvHandle.setText("@"+mModel.getmHandle());
        holder.tvScore.setText("Score: "+mModel.getmScore());
        holder.tvRank.setText(mModel.getmRank());
    }
    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

}
