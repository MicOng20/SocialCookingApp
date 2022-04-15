package my.edu.utar.socialcookingapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
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
import my.edu.utar.socialcookingapp.ChatActivity;
import my.edu.utar.socialcookingapp.Model.UserTable;
import my.edu.utar.socialcookingapp.PostActivity;
import my.edu.utar.socialcookingapp.ProfileFragment;
import my.edu.utar.socialcookingapp.R;
import my.edu.utar.socialcookingapp.ShowUserFragment;

public class UserAdapter1 extends RecyclerView.Adapter<UserAdapter1.UserViewHolder> {
    private Context sContext;
    private List<UserTable> sUsers;

    private FirebaseUser firebaseUser1;

    public UserAdapter1(Context sContext, List<UserTable> sUsers) {
        this.sContext = sContext;
        this.sUsers = sUsers;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(sContext).inflate(R.layout.user_item, parent, false);
        return new UserAdapter1.UserViewHolder(view);
    }

    /*@Override
    public void onBindViewHolder(@NonNull UserAdapter1.UserViewHolder holder, int i) {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        final UserTable user1 = sUsers.get(i);

        //holder.username1.setText(user.getName());
        //holder.username1.setText(user1.getName());

        //holder.btn_chat_list1.setVisibility(View.VISIBLE);
        holder.btn_chat_list1.setText("Chat");
        //handle display image
        if(user1.getImage().toString().equals("")){
            holder.dis_imgprofile1.setImageResource(R.drawable.ic_person_search);
        } else{
            Glide.with(sContext).load(user1.getImage()).into(holder.dis_imgprofile1);
        }

        //set the button label
        isSetting(user1.getUid(), holder.btn_chat_list1);

        //if user get the id is equal to the firebase then button disappear
        *//*if (user.getUid().equals(firebaseUser.getUid())){
            holder.btn_chat_list.setVisibility(View.VISIBLE);
        }*//*
        String hisUID = user1.getUid();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //after click the box then redirect to the chat room
                Intent intent = new Intent(sContext, ChatActivity.class);
                intent.putExtra("hisUID", hisUID);
                sContext.startActivity(intent);
            }
        });
    }*/

    @Override
    public void onBindViewHolder(@NonNull UserAdapter1.UserViewHolder holder, int position) {
        firebaseUser1 = FirebaseAuth.getInstance().getCurrentUser();
        final UserTable user = sUsers.get(position);

        if(user.getImage().toString().equals("")){
            holder.dis_imgprofile1.setImageResource(R.drawable.ic_person_search);
        } else{
            Glide.with(sContext).load(user.getImage()).into(holder.dis_imgprofile1);
        }

        holder.username1.setText(user.getName());
        holder.btn_chat_list1.setVisibility(View.VISIBLE);

        isSetting(user.getUid(), holder.btn_chat_list1);

        if (user.getUid().equals(firebaseUser1.getUid())){
            holder.btn_chat_list1.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor prefEditor = sContext.getSharedPreferences("SHARED_PREFERENCES", Context.MODE_PRIVATE).edit();
                prefEditor.putString("profileid", user.getUid());
                prefEditor.putString("fromWhere", "chartlist");
                prefEditor.apply();
                prefEditor.commit();

                ((FragmentActivity)sContext).getSupportFragmentManager().beginTransaction().replace(R.id.content1, new ShowUserFragment()).commit();
            }
        });

        holder.btn_chat_list1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.btn_chat_list1.getText().toString().equals("Follow")){
                    FirebaseDatabase.getInstance().getReference().child("Follow").child(firebaseUser1.getUid())
                            .child("following").child(user.getUid()).setValue(true);
                    FirebaseDatabase.getInstance().getReference().child("Follow").child(user.getUid())
                            .child("followers").child(firebaseUser1.getUid()).setValue(true);
                } else{
                    FirebaseDatabase.getInstance().getReference().child("Follow").child(firebaseUser1.getUid())
                            .child("following").child(user.getUid()).removeValue();
                    FirebaseDatabase.getInstance().getReference().child("Follow").child(user.getUid())
                            .child("followers").child(firebaseUser1.getUid()).removeValue();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return sUsers.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{
        public TextView username1, email1;
        public CircleImageView dis_imgprofile1;
        public Button btn_chat_list1;
        public UserViewHolder(@NonNull View itemView){
            super(itemView);

            username1 = itemView.findViewById(R.id.username_txt);
            email1 = itemView.findViewById(R.id.email_txt);
            dis_imgprofile1 = itemView.findViewById(R.id.dis_imgprofile);
            btn_chat_list1 = itemView.findViewById(R.id.btn_select_chat);
        }
    }

    private void isSetting (final String userid, final Button btn){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference()
                .child("Follow").child(firebaseUser1.getUid()).child("following");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                btn.setText("Chat");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}