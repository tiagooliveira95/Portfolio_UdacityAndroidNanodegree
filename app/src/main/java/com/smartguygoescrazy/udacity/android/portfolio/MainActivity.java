package com.smartguygoescrazy.udacity.android.portfolio;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_activity_recyclerview)
    RecyclerView recyclerView;
    RecyclerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        adapter = new RecyclerAdapter(this, getItems());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onClick(Item item, View... views) {
                Intent intent = new Intent(MainActivity.this, MyAppActivity.class);
                intent.putExtra(Item.KEY_TITLE, item);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                    Pair<View, String> p1 = Pair.create(views[0], "transition1");
                    Pair<View, String> p2 = Pair.create(views[1], "transaction_title");
                    Pair<View, String> p3 = Pair.create(views[2], "overlay");


                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation(MainActivity.this, p1, p2, p3);
                    startActivity(intent, options.toBundle());
                } else {
                    startActivity(intent);
                }
            }
        });
    }

    private ArrayList<Item> getItems() {
        ArrayList<Item> items = new ArrayList<>();


        String[] titles = {
                getString(R.string.popular_movies_app_name),
                getString(R.string.baking_app_app_name),
                getString(R.string.gradle__app_name),
                getString(R.string.material_app_name),
                getString(R.string.smartwatch_app_name),
                getString(R.string.capstone_app_name)
        };

        String[] shortDescription = {
                getString(R.string.popular_movies_short_description),
                getString(R.string.baking_short_description),
                getString(R.string.gradle_short_description),
                getString(R.string.material_short_description),
                getString(R.string.go_ubiquitous_short_description),
                getString(R.string.capstone_short_description)
        };

        String[] fullDescription = {
                getString(R.string.popular_movies_full_description),
                getString(R.string.baking_app_full_description),
                getString(R.string.build_it_bigger_full_description),
                getString(R.string.material_full_description),
                getString(R.string.sunchine_smart_watch_full_description),
                getString(R.string.capstone_full_description)
        };

        int[] imgResIds = {
                R.drawable.movie_wallpaper,
                R.drawable.baking,
                R.drawable.gradle,
                R.drawable.material,
                R.drawable.ubiquitous,
                R.drawable.capstone
        };

        for (int k = 0; k < titles.length; k++) {
            items.add(new Item(titles[k], shortDescription[k], fullDescription[k], imgResIds[k]));
        }
        return items;
    }
}
