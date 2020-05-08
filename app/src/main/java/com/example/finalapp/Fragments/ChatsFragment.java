package com.example.finalapp.Fragments;

import android.nfc.Tag;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalapp.Adapter.UserAdapter;
import com.example.finalapp.ChatActivity;
import com.example.finalapp.Model.Chat;
import com.example.finalapp.Model.User;
import com.example.finalapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;


public class ChatsFragment extends Fragment {


    private RecyclerView recyclerView;

    private UserAdapter userAdapter;
    private List<User> mUsers;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_users, container, false);
        recyclerView=view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mUsers=new ArrayList<>();

        readUsers();



        return view;
    }



    private void readUsers() {
        final FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                    mUsers.clear();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        User user = snapshot.getValue(User.class);

                        assert user != null;
                        assert firebaseUser != null;
                        if (!user.getId().equals(firebaseUser.getUid())) {
                            mUsers.add(user);
                        }
                    }

                    userAdapter = new UserAdapter(getContext(), mUsers, true);
                    recyclerView.setAdapter(userAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

  /*  private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private List<User> mUsers;

    FirebaseUser fuser;
    DatabaseReference reference;

    private List<String> usersList;
    private Object Tag;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_chats,container,false);

        recyclerView=view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        fuser= FirebaseAuth.getInstance().getCurrentUser();

        usersList=new ArrayList<>();



        reference= FirebaseDatabase.getInstance().getReference("Chats");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                usersList.clear();

                for(DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                   Chat chat=snapshot.getValue(Chat.class);



                    if(chat.getSender().equals(fuser.getUid()))
                   {
                       usersList.add(chat.getReceiver());
                       //console.notify(usersList);
                   }
                   if(chat.getReceiver().equals(fuser.getUid()))
                   {
                       usersList.add(chat.getSender());
                       //console.log(usersList);
                   }
                }

                readChats();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }

       private void readChats () {

               mUsers = new ArrayList<>();


               final Iterator<User> it=mUsers.iterator();

                reference = FirebaseDatabase.getInstance().getReference("Users");
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        mUsers.clear();

                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                User user = snapshot.getValue(User.class);

                                for (String id : usersList) {
                                    if (user.getId().equals(id)) {
                                        if (mUsers.size() != 0) {
                                            for (User user1 : mUsers) {
                                                if (!user.getId().equals(user1.getId())) {
                                                    mUsers.add(user);
                                                }
                                            }
                                        } else {
                                            mUsers.add(user);
                                        }
                                    }
                                }
                            }

                        userAdapter = new UserAdapter(getContext(), mUsers);
                        recyclerView.setAdapter(userAdapter);
                        }



                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

        }*/

    }

