package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class catagery_class extends Fragment {
    private ImageView fab,zab;
    private FirebaseAuth mAuth;
    private FirebaseUser muser;
    private ValueEventListener query;
    category_adapter ca;
    private List<catagery_item_obj> dataList;
    private RecyclerView list1,list2;
    private FirebaseDatabase mdata;
    private DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference().child("profitcat");
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.category,container,false);
        fab = view.findViewById(R.id.addcategory_button1);
        zab = view.findViewById(R.id.addcategory_button2);

        list1 = view.findViewById(R.id.recyclerView2);
        list2 = view.findViewById(R.id.recyclerView3);
        list1.setHasFixedSize(true);
        list1.setLayoutManager(new LinearLayoutManager(getContext()));
        dataList = new ArrayList<>();
        ca = new category_adapter(dataList);
        ArrayAdapter<catagery_item_obj> adapter=new ArrayAdapter<>(view.getContext(), R.layout.category_item);
        list1.setAdapter(ca);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("profitcat");
        try {
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    catagery_item_obj item = snapshot.getValue(catagery_item_obj.class);
                    dataList.add(item);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getContext(), "data not available", Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e){
            Toast.makeText(getContext(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        recyclerView.setAdapter(adapter);
        mdata = FirebaseDatabase.getInstance();
        databaseReference = mdata.getReference().child("category");
        DatabaseReference finalDatabaseReference = databaseReference;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finalDatabaseReference.child("profitcat");
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Add Category");
                // Set up the input fields
                final EditText nameInput = new EditText(getContext());
                final EditText idInput = new EditText(getContext());
                nameInput.setHint("Category Name");
                idInput.setHint("Category ID");
                LinearLayout layout = new LinearLayout(getContext());
                layout.setOrientation(LinearLayout.VERTICAL);
                layout.addView(nameInput);
                layout.addView(idInput);
                builder.setView(layout);
                builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String categoryName = nameInput.getText().toString().trim();
                                String categoryId = idInput.getText().toString().trim();

                                if (!TextUtils.isEmpty(categoryName) && !TextUtils.isEmpty(categoryId)) {
                                    // Call a method to add the category to the Firebase Realtime Database
                                    addCategoryToFirebase(categoryName, categoryId);
                                } else {
                                    Toast.makeText(getContext(), "Name and ID cannot be empty", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                builder.show();
            }
        });
        zab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "item deleted", Toast.LENGTH_SHORT).show();
            }
        });
        //getChildFragmentManager().beginTransaction().replace(R.id.frameLayout,new records_activity()).commit();
        return view;
    }

    private void addCategoryToFirebase(String categoryName, String categoryId) {
        catagery_item_obj currentItem = null;
        currentItem = new catagery_item_obj();

        // Set the values for the current item
        currentItem.setCatid(categoryId);
        currentItem.setCatname(categoryName);

//        cat = null;
//        cat.setCatid(categoryId);
//        cat.setCatname(categoryName);
        databaseReference.child(categoryId).setValue(currentItem);
    }
}
