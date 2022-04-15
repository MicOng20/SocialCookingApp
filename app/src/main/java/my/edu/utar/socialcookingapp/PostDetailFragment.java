package my.edu.utar.socialcookingapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import my.edu.utar.socialcookingapp.Adapter.PostAdapter;
import my.edu.utar.socialcookingapp.Model.PostTable;
import my.edu.utar.socialcookingapp.Model.UserTable;


public class PostDetailFragment extends Fragment {
    String postid;
    private RecyclerView rv_postfragment;
    private PostAdapter postAdapter;
    private List<PostTable> postList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post_detail, container, false);

        SharedPreferences pref = getContext().getSharedPreferences("SHARED_PREFERENCES", Context.MODE_PRIVATE);
        postid = pref.getString("postid", "none");

        rv_postfragment = view.findViewById(R.id.rv_postfragment);
        rv_postfragment.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager (getContext());
        rv_postfragment.setLayoutManager(linearLayoutManager);

        postList = new ArrayList<>();
        postAdapter = new PostAdapter(getContext(), postList);
        rv_postfragment.setAdapter(postAdapter);

        readPost();

        return view;
    }

    private void readPost() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Posts").child(postid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                postList.clear();
                PostTable post = snapshot.getValue(PostTable.class);
                postList.add(post);

                postAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}