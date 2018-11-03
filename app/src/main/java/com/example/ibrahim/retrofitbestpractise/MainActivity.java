package com.example.ibrahim.retrofitbestpractise;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.ibrahim.retrofitbestpractise.adapter.UserAdapter;
import com.example.ibrahim.retrofitbestpractise.arch.rest.api.JsonPlaceHolderApi;
import com.example.ibrahim.retrofitbestpractise.arch.rest.model.User;
import com.example.ibrahim.retrofitbestpractise.arch.rest.singleton.JsonPlaceHolderRest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.ibrahim.retrofitbestpractise.AddEditUserActivity.EXTRA_USER;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private static final int REQUEST_ADD_USER = 100;
    private static final int REQUEST_EDIT_USER = 101;
    public static final String ID = "id";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String EMAIL = "email";
    public static final String AGE = "age";
    public static final String COMPANY_ID = "company_id";

    private FloatingActionButton addUser;
    private RecyclerView recyclerView;
    private UserAdapter adapter;
    private JsonPlaceHolderApi api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        api = JsonPlaceHolderRest.getInstance().create(JsonPlaceHolderApi.class);

        addUser = findViewById(R.id.add_user);
        addUser.setOnClickListener(this);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new UserAdapter();
        getUsers();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == addUser.getId()) {
            Intent intent = new Intent(this, AddEditUserActivity.class);
            startActivityForResult(intent, REQUEST_ADD_USER);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ADD_USER && resultCode == RESULT_OK && data != null) {
            User user = data.getParcelableExtra(EXTRA_USER);
            createUser(user);

        } else if (requestCode == REQUEST_EDIT_USER && resultCode == RESULT_OK && data != null) {


        }

    }

    private void getUsers() {
        Call<List<User>> call = api.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (call != null) {
                    if (response.isSuccessful()) {
                        List<User> users = response.body();
                        adapter.setItems(users);
                        recyclerView.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed to load", Toast.LENGTH_SHORT).show();
                Log.e(TAG, t.getMessage());
            }
        });
    }

    private void createUser(User user) {
        Call<User> call = api.createUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (call != null) {
                    if (response.isSuccessful()) {
                        // TODO: 11/2/2018 create user and set user to SQL room database

                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed to create user", Toast.LENGTH_SHORT).show();
                Log.e(TAG, t.getMessage());
            }
        });
    }

}
