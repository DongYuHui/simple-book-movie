package com.kyletung.doubanbookmovie;

import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.kyletung.doubanbookmovie.book.BookFragment;
import com.kyletung.doubanbookmovie.home.HomeFragment;
import com.kyletung.doubanbookmovie.movie.MovieFragment;

public class MainActivity extends AppCompatActivity {

    //init drawer layout and navigation view
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    //init tab layout and fragment manager
    TabLayout tabLayout;
    FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init tab layout and home fragment
        tabLayout = (TabLayout) findViewById(R.id.fragment_tablayout);

        //init home
        fragmentManager.beginTransaction().replace(R.id.content, new HomeFragment()).commit();

        //init toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //set status bar
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setStatusBarBackground(R.color.statusbar_background);

        //set tool bar toggle
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerToggle.syncState();
        drawerLayout.setDrawerListener(drawerToggle);

        //set navigation view click events
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                onItemClickedNavigationMenu(menuItem);
                return true;
            }
        });

    }

    private void onItemClickedNavigationMenu(MenuItem menuItem) {
        menuItem.setChecked(true);
        switch (menuItem.getItemId()) {
            case R.id.navigation_menu_home:
                fragmentManager.beginTransaction().replace(R.id.content, new HomeFragment()).commit();
                drawerLayout.closeDrawer(navigationView);
                break;
            case R.id.navigation_menu_book:
                fragmentManager.beginTransaction().replace(R.id.content, new BookFragment()).commit();
                drawerLayout.closeDrawer(navigationView);
                break;
            case R.id.navigation_menu_movie:
                fragmentManager.beginTransaction().replace(R.id.content, new MovieFragment()).commit();
                drawerLayout.closeDrawer(navigationView);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
