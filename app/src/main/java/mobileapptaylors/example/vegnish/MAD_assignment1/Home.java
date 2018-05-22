package mobileapptaylors.example.vegnish.MAD_assignment1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mobileapptaylors.example.vegnish.MAD_assignment1.R.layout.activity_home);

        Toolbar toolbar =findViewById(mobileapptaylors.example.vegnish.MAD_assignment1.R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(mobileapptaylors.example.vegnish.MAD_assignment1.R.id.drawer_layout);
        NavigationView navigationView = findViewById(mobileapptaylors.example.vegnish.MAD_assignment1.R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                mobileapptaylors.example.vegnish.MAD_assignment1.R.string.navigation_drawer_open, mobileapptaylors.example.vegnish.MAD_assignment1.R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case mobileapptaylors.example.vegnish.MAD_assignment1.R.id.nav_home:
                Intent intent_home = new Intent(this, Welcome.class);
                this.startActivity(intent_home);
                break;
            case mobileapptaylors.example.vegnish.MAD_assignment1.R.id.nav_addslot:
                Intent intent = new Intent(this, AddSlots.class);
                this.startActivity(intent);
                break;
            case mobileapptaylors.example.vegnish.MAD_assignment1.R.id.nav_viewslot:
                Intent intent_viewSlots = new Intent(this, ViewSlots.class);
                this.startActivity(intent_viewSlots);
                break;

            case mobileapptaylors.example.vegnish.MAD_assignment1.R.id.nav_historyslot:
                Intent intent_historySlot = new Intent(this, HistorySlots.class);
                this.startActivity(intent_historySlot);
                break;

            case mobileapptaylors.example.vegnish.MAD_assignment1.R.id.nav_announcement:
                Intent intent_announcement = new Intent(this, Announcement.class);
                this.startActivity(intent_announcement);
                break;

            case mobileapptaylors.example.vegnish.MAD_assignment1.R.id.nav_aboutus:
                getSupportFragmentManager().beginTransaction().replace(mobileapptaylors.example.vegnish.MAD_assignment1.R.id.fragment_container,
                        new AboutusFragment()).commit();
                break;
            case mobileapptaylors.example.vegnish.MAD_assignment1.R.id.nav_help:
                getSupportFragmentManager().beginTransaction().replace(mobileapptaylors.example.vegnish.MAD_assignment1.R.id.fragment_container,
                        new HelpFragment()).commit();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else{
            super.onBackPressed();
        }
    }
}
