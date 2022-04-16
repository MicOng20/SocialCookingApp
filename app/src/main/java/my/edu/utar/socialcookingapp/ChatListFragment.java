package my.edu.utar.socialcookingapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import my.edu.utar.socialcookingapp.Adapter.UserAdapter;
import my.edu.utar.socialcookingapp.Adapter.UserAdapter1;
import my.edu.utar.socialcookingapp.Model.UserTable;


public class ChatListFragment extends Fragment {

    private RecyclerView recyclerView;
    private UserAdapter userAdapter1;
    private List<UserTable> sUsers;

    EditText search_bar1;

    //firebase auth
    FirebaseAuth firebaseAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //View view = inflater.inflate(R.layout.fragment_chat_list, container, false);
        //recyclerView = view.findViewById(R.id.display_rv);

        View view = inflater.inflate(R.layout.fragment_chat_list, container, false);
        recyclerView = view.findViewById(R.id.display_rv);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        search_bar1 = view.findViewById(R.id.search_bar1);

        sUsers = new ArrayList<>();
        //userAdapter1 = new UserAdapter1(getContext(), sUsers);
        //recyclerView.setAdapter(userAdapter1);
        userAdapter1 = new UserAdapter(getContext(), sUsers, 1);
        recyclerView.setAdapter(userAdapter1);

        readUsers();
        search_bar1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchUsers1(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        return view;
    }

    private void searchUsers1(String s){
        Query query = FirebaseDatabase.getInstance().getReference("Users").orderByChild("name")
                .startAt(s)
                .endAt(s + "\uf8ff");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                sUsers.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    UserTable user = dataSnapshot.getValue(UserTable.class);
                    sUsers.add(user);
                }
                userAdapter1.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void readUsers(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (search_bar1.getText().toString().equals("")){
                    sUsers.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        UserTable user = dataSnapshot.getValue(UserTable.class);
                        sUsers.add(user);
                    }
                    userAdapter1.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}