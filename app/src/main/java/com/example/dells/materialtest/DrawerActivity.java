package com.example.dells.materialtest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.dells.materialtest.Adapter.DogAdapter;
import com.example.dells.materialtest.bean.Dog;

import java.util.ArrayList;
import java.util.Random;

public class DrawerActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private FloatingActionButton mFab;

    private ArrayList<Dog> dogsList = new ArrayList<>();
    private DogAdapter dogAdapter;

    private SwipeRefreshLayout swipeRefresh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        initToolbar();
        initNavigation();
        myFloatButton();

        initDogs();
        initRecycler();

        initSwipeRefresh();
    }

    private void initSwipeRefresh() {
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshDogs();
            }
        });

    }

    private void refreshDogs() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initDogs();
                        dogAdapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    private void initRecycler() {
        RecyclerView recyclerview = (RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerview.setLayoutManager(layoutManager);
        dogAdapter = new DogAdapter(this, dogsList);
        recyclerview.setAdapter(dogAdapter);

    }

    private void initDogs() {
        Dog[] dogs = {new Dog("拉布拉多", R.drawable.dog_face_wind_glasses_1920x1080), new Dog("二哈", R.drawable.husky_dog_puppy_snout_eyes_lies_1920x1080)};
        dogsList.clear();
        for(int i = 0; i <50; i++){
            Random random = new Random();
            int index = random.nextInt(dogs.length);
            dogsList.add(dogs[index]);
        }
    }

    private void myFloatButton() {
        mFab = (FloatingActionButton) findViewById(R.id.drawerlayout_fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getContext() == DrawerActivity.this){
//                    Toast.makeText(v.getContext(), "FAB clicked", Toast.LENGTH_SHORT).show();
                    Snackbar.make(v, "wtf?", Snackbar.LENGTH_SHORT).setAction("Undo?", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(v.getContext(), "restore", Toast.LENGTH_SHORT).show();
                        }
                    }).show();
                }

            }
        });
    }

    private void initNavigation() {
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setCheckedItem(R.id.nav_menu_name);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        mToolbar.setTitle("hi drawer");
        setSupportActionBar(mToolbar);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item1:
                Toast.makeText(this, "this is menu 1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_item2:
                Toast.makeText(this, "this is menu 2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_delete:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
                break;
        }
        return true;
    }

}
