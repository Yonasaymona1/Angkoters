package com.example.angkoters;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.angkoters.model.RootAngkotModel;
import com.example.angkoters.model.RuteAngkotItem;
import com.example.angkoters.rest.ApiConfig;
import com.example.angkoters.rest.ApiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    private ArrayList<RuteAngkotItem> ruteAngkotItems;
    private adapter adapter;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        ruteAngkotItems = new ArrayList<>();
        getData();
    }

    private void getData() {
        ApiService apiService = ApiConfig.getApiService();
        apiService.getData()
                .enqueue(new Callback<RootAngkotModel>() {
                    @Override
                    public void onResponse(Call<RootAngkotModel> call, Response<RootAngkotModel> response) {
                        if (response.isSuccessful()){
                            ruteAngkotItems = response.body().getRuteAngkot();
                            adapter = new adapter(ruteAngkotItems, getApplicationContext());
                            adapter.notifyDataSetChanged();
                            rv.setAdapter(adapter);
                            rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        }
                    }

                    @Override
                    public void onFailure(Call<RootAngkotModel> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void initView() {
        rv = findViewById(R.id.rv);
    }
}