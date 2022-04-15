package my.edu.utar.socialcookingapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Comment;

import java.util.List;

import my.edu.utar.socialcookingapp.DashBoardActivity;
import my.edu.utar.socialcookingapp.Model.CommentTable;
import my.edu.utar.socialcookingapp.Model.UserTable;
import my.edu.utar.socialcookingapp.PostLayoutFragment;
import my.edu.utar.socialcookingapp.ProfileFragment;
import my.edu.utar.socialcookingapp.R;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    private Context mContext;
    private List<CommentTable> mComment;

    private FirebaseUser firebaseUser;

    public CommentAdapter(Context mContext, List<CommentTable> mComment){
        this.mContext = mContext;
        this.mComment = mComment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.comment_item, parent, false);
        return new CommentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        final CommentTable comment = mComment.get(position);

        holder.comment.setText(comment.getComment());
        getUserInfo(holder.image_comment, holder.username, comment.getPublisher());

        holder.comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DashBoardActivity.class);
                intent.putExtra("publisherid", comment.getPublisher());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mComment.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView image_comment;
        public TextView username, comment;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            image_comment = itemView.findViewById(R.id.image_comment);
            username = itemView.findViewById(R.id.username);
            comment = itemView.findViewById(R.id.comment);
        }
    }

    private void getUserInfo(final ImageView imageView, final TextView username, String publisherid){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(publisherid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserTable user = snapshot.getValue(UserTable.class);
                if(user.getImage().toString().equals("")){
                    imageView.setImageResource(R.drawable.ic_person_search);
                } else{
                    Glide.with(mContext).load(user.getImage()).into(imageView);
                }
                username.setText(user.getName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
