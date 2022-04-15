package my.edu.utar.socialcookingapp.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import my.edu.utar.socialcookingapp.Model.PostTable;
import my.edu.utar.socialcookingapp.PostDetailFragment;
import my.edu.utar.socialcookingapp.R;

public class GridPostAdapter extends RecyclerView.Adapter<GridPostAdapter.ViewHolder>{
    private Context context;
    private List<PostTable> mPosts;

    public GridPostAdapter(Context context, List<PostTable> mPosts) {
        this.context = context;
        this.mPosts = mPosts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.grid_post_item, parent, false);
        return new GridPostAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final PostTable post = mPosts.get(position);
        Glide.with(context).load(post.getPostImage()).into(holder.grid_image);

        holder.grid_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = context.getSharedPreferences("SHARED_PREFERENCES", Context.MODE_PRIVATE).edit();
                editor.putString("postid", post.getPostID());
                editor.apply();

                ((FragmentActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.content1, new PostDetailFragment()).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView grid_image;

        public ViewHolder (@NonNull View itemView){
            super(itemView);

            grid_image = itemView.findViewById(R.id.grid_image);
        }
    }


}
