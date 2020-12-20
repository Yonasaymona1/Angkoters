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
    private String sharedPrefFile = "com.example.angkoters";
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        ruteAngkotItems = new ArrayList<>();
        getData();
        preferences = getSharedPreferences(sharedPrefFile,MODE_PRIVATE);

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

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.alarm:
                Intent intent = new Intent(MainActivity.this, AlarmActivity.class);
                String mOrderMessage = null;
                intent.putExtra(EXTRA_MESSAGE, mOrderMessage);
                startActivity(intent);
                return true;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences.Editor preferencesEditor = preferences.edit();
        preferencesEditor.apply();
    }
}