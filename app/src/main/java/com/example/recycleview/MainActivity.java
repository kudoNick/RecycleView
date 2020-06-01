package com.example.recycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvData,rvData1;
    DataAdapter dataAdapter;
    List<Data> dataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvData = findViewById(R.id.rvData);
        rvData1 = findViewById(R.id.rvData1);

        //hiện thị theo chiều ngang
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        rvData.setLayoutManager(layoutManager);
        //hiện thị theo chiều dọc
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvData1.setLayoutManager(layoutManager1);
        //hiện thì theo dạng lưới
        rvData.setHasFixedSize(true);
//        rvData.setLayoutManager(new GridLayoutManager(this, 2));

        //Chèn 1 đường kẻ dọc
        DividerItemDecoration dividerVertical =
                new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL);
        rvData.addItemDecoration(dividerVertical);

        //chèn 1 đường kẻ ngang
        DividerItemDecoration dividerHorizontal =
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rvData.addItemDecoration(dividerHorizontal);

        //lấy data từ json
        getData();

    }

    private void getData() {
        dataList = new ArrayList<>();
        AndroidNetworking.get("https://simplifiedcoding.net/demos/marvel/")
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i <response.length() ; i++) {
                            try {
                                Data data = new Data(response.getJSONObject(i));
                                dataList.add(data);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        dataAdapter = new DataAdapter(dataList,MainActivity.this);
                        rvData.setAdapter(dataAdapter);
                        rvData1.setAdapter(dataAdapter);
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(MainActivity.this, "Faill", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
