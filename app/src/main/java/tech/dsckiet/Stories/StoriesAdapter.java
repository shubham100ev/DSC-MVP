package tech.dsckiet.Stories;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import tech.dsckiet.R;

public class StoriesAdapter extends RecyclerView.Adapter<StoriesAdapter.MyViewHolder> {

        private List<ModelStories> mList;
        private Context mContext;
        private ModelStories mModel;


        class MyViewHolder extends RecyclerView.ViewHolder {

            TextView tvTitle,tvDesc;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                tvTitle = itemView.findViewById(R.id.tv_story_title);
                tvDesc = itemView.findViewById(R.id.tv_story_description);

            }
        }

        StoriesAdapter(List<ModelStories> list, Context context){
            this.mList = list;
            this.mContext = context;
        }

        @NonNull
        @Override
        public StoriesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
            View itemView = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.layout_story_card, viewGroup, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            mModel = mList.get(position);
            holder.tvTitle.setText(mModel.getmTitle());
            holder.tvDesc.setText(mModel.getmDesc());
        }

        @Override
        public int getItemCount() {
            return mList == null ? 0 : mList.size();
        }

}
