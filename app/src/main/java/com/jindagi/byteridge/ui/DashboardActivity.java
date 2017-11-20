package com.jindagi.byteridge.ui;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;
import com.jindagi.byteridge.R;
import com.jindagi.byteridge.data.FetchData;
import com.jindagi.byteridge.data.RetrofitClient;
import com.jindagi.byteridge.ui.adapters.DividerItemDecoration;
import com.jindagi.byteridge.ui.adapters.VerticalScrollAdapter;
import com.jindagi.byteridge.ui.adapters.HorizontalScrollAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private StaggeredGridLayoutManager staggeredLayoutVertical, staggeredLayoutHorizontal;
    RecyclerView rvVertical, rvHorizontal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        rvVertical = (RecyclerView)findViewById(R.id.vertical_recycler_view);
        rvVertical.setHasFixedSize(true);

        rvHorizontal = (RecyclerView)findViewById(R.id.horizontal_recycler_view);
        rvHorizontal.setHasFixedSize(true);

        staggeredLayoutHorizontal = new StaggeredGridLayoutManager(1, 0);
        rvHorizontal.setLayoutManager(staggeredLayoutHorizontal);

        staggeredLayoutVertical = new StaggeredGridLayoutManager(2, 1);
        rvVertical.setLayoutManager(staggeredLayoutVertical);

        getListItemData();

        //This is to add a view in between the listitems
        rvHorizontal.addItemDecoration(
                new DividerItemDecoration(40));

    }

    private void getListItemData(){

        FetchData apiService =
                RetrofitClient.getClient().create(FetchData.class);

        Call<Object> call = apiService.getStaggeredLayoutData();
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {

                Gson gson = new Gson();

                Object lstResult = ((LinkedTreeMap) response.body()).get("result");

                Object lstHorizontalResults = ((LinkedTreeMap) lstResult).get("horizontal_scroll_layout");
                Object lstVerticalResults = ((LinkedTreeMap) lstResult).get("swaggered_layout");

                JsonArray verticalList = gson.toJsonTree(lstVerticalResults).getAsJsonArray();

                JsonArray horizontalList = gson.toJsonTree(lstHorizontalResults).getAsJsonArray();


                List<JsonObject> verticalItemsList =  new ArrayList<JsonObject>();

                for(int i=0; i<verticalList.size(); i++){
                    verticalItemsList.add(verticalList.get(i).getAsJsonObject());
                }

                List<JsonObject> horizontalItemsList =  new ArrayList<JsonObject>();

                for(int i=0; i<horizontalList.size(); i++){
                    horizontalItemsList.add(horizontalList.get(i).getAsJsonObject());
                }

                VerticalScrollAdapter rcVerticalAdapter = new VerticalScrollAdapter(getApplicationContext(), verticalItemsList);
                rvVertical.setAdapter(rcVerticalAdapter);

                HorizontalScrollAdapter rcHorizontalAdapter = new HorizontalScrollAdapter(getApplicationContext(), horizontalItemsList);
                rvHorizontal.setAdapter(rcHorizontalAdapter);
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                // Log error here since request failed
                Log.d("DashboardActivity","Error while fetching data from server");
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_gallery) {

        }else if(id == R.id.nav_logout){
            //To sign out from the app

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
