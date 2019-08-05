package com.haiph.assignmentandroidnw;

import android.os.Bundle;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.FoldingCube;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.haiph.assignmentandroidnw.fragment.AboutFragment;
import com.haiph.assignmentandroidnw.fragment.CatergoryFragment;
import com.haiph.assignmentandroidnw.fragment.FavoriteFragment;
import com.haiph.assignmentandroidnw.fragment.LatestFragment;
import com.haiph.assignmentandroidnw.fragment.PostInCateFragment;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);

        frameLayout=findViewById(R.id.frameLayout);


        setSupportActionBar(toolbar);
        if (toolbar != null){
            toolbar.setBackgroundDrawable(getResources().getDrawable(R.drawable.gradient_toolbar));
           getSupportActionBar().setTitle("Wallpaper");
        }



        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        LatestFragment latestFragment=new LatestFragment();
        loadFragment(latestFragment);
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_lastest) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new LatestFragment()).commit();

        } else if (id == R.id.nav_category) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new CatergoryFragment()).commit();

        } else if (id == R.id.nav_gifs) {

        } else if (id == R.id.nav_favorite) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new FavoriteFragment()).commit();
        } else if (id == R.id.nav_rate) {

        } else if (id == R.id.nav_moreapp) {

        }else if (id == R.id.nav_about) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new AboutFragment()).commit();
        }
        else if (id == R.id.nav_setting) {

        }
        else if (id == R.id.nav_privacy) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
