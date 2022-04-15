package my.edu.utar.socialcookingapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import my.edu.utar.socialcookingapp.Model.UserTable;
import my.edu.utar.socialcookingapp.PostActivity;
import my.edu.utar.socialcookingapp.ProfileFragment;
import my.edu.utar.socialcookingapp.R;
import my.edu.utar.socialcookingapp.ShowUserFragment;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private Context mContext;
    private List<UserTable> mUsers;

    private FirebaseUser firebaseUser;

    public UserAdapter(Context mContext, List<UserTable> mUsers) {
        this.mContext = mContext;
        this.mUsers = mUsers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.user_item, parent, false);
        return new UserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        final UserTable user = mUsers.get(position);

        holder.username.setText(user.getName());
        holder.btn_follow_search.setVisibility(View.VISIBLE);

        if(user.getImage().toString().equals("")){
            holder.search_imgprofile.setImageResource(R.drawable.ic_person_search);
        } else{
            Glide.with(mContext).load(user.getImage()).into(holder.search_imgprofile);
        }

        isFollowing(user.getUid(), holder.btn_follow_search);

        if (user.getUid().equals(firebaseUser.getUid())){
            holder.btn_follow_search.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor prefEditor = mContext.getSharedPreferences("SHARED_PREFERENCES", Context.MODE_PRIVATE).edit();
                prefEditor.putString("profileid", user.getUid());
                prefEditor.apply();
                prefEditor.commit();

                ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction().replace(R.id.content1, new ShowUserFragment()).commit();
            }
        });

        holder.btn_follow_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.btn_follow_search.getText().toString().equals("Follow")){
                    FirebaseDatabase.getInstance().getReference().child("Follow").child(firebaseUser.getUid())
                            .child("following").child(user.getUid()).setValue(true);
                    FirebaseDatabase.getInstance().getReference().child("Follow").child(user.getUid())
                            .child("followers").child(firebaseUser.getUid()).setValue(true);
                } else{
                    FirebaseDatabase.getInstance().getReference().child("Follow").child(firebaseUser.getUid())
                            .child("following").child(user.getUid()).removeValue();
                    FirebaseDatabase.getInstance().getReference().child("Follow").child(user.getUid())
                            .child("followers").child(firebaseUser.getUid()).removeValue();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView username;
        public CircleImageView search_imgprofile;
        public Button btn_follow_search;
        public ViewHolder(@NonNull View itemView){
            super(itemView);

            username = itemView.findViewById(R.id.username_search);
            search_imgprofile = itemView.findViewById(R.id.search_imgprofile);
            btn_follow_search = itemView.findViewById(R.id.btn_follow_search);

        }
    }

    private void isFollowing (final String userid, final Button btn){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference()
                .child("Follow").child(firebaseUser.getUid()).child("following");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child(userid).exists()){
                    btn.setText("Following");
                }
                else{
                    btn.setText("Follow");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
