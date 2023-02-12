package edu.bzu.assignmentone.bzutodo.userdashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import edu.bzu.assignmentone.bzutodo.Auth.LoginActivity;
import edu.bzu.assignmentone.bzutodo.R;
import edu.bzu.assignmentone.bzutodo.models.Student;

public class UserDashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    final Student[] students = {new Student(1191207, "Mohammed Tahayna", 592130148, 21, "Palestine Ramallah", "male"),
            new Student(1192545, "Mohammed Khateeb", 593052136, 21, "Ramallah bel'een", "male"),
            new Student(1192772, "Baker Alsdeeq", 595959595, 21, "Ramallah Almasyoun", "male")};


    private DrawerLayout drawer;
    private ImageView nav_account;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.inflateHeaderView(R.layout.nav_header);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new TodayFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_today);
            toolbar.setTitle(R.string.today_title);
        }

        nav_account = headerView.findViewById(R.id.nav_account);

        nav_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AccountFragment()).commit();
                navigationView.setCheckedItem(R.id.nav_account);
                drawer.closeDrawer(GravityCompat.START);
                toolbar.setTitle(R.string.account_title);
            }
        });


    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_today:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new TodayFragment()).commit();
                toolbar.setTitle(R.string.today_title);
                break;
            case R.id.nav_inbox:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new InboxFragment()).commit();
                toolbar.setTitle(R.string.inbox_title);
                break;
            case R.id.nav_schedule:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ScheduleFragment()).commit();
                toolbar.setTitle(R.string.schedule_title);
                break;
            case R.id.nav_about_us:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AboutUsFragment()).commit();
                toolbar.setTitle(R.string.about_us_title);
                break;
            case R.id.nav_settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SettingsFragment()).commit();
                toolbar.setTitle(R.string.settings_title);
                break;
            case R.id.nav_weather:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new weatherFragment()).commit();
                toolbar.setTitle(R.string.weather_title);
                break;
            case R.id.nav_uni_map:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new mapFragment()).commit();
                toolbar.setTitle(R.string.map_title);
                break;
            case R.id.nav_signout:
                Intent closeUserDBA = new Intent(UserDashboardActivity.this, LoginActivity.class);
                startActivity(closeUserDBA);
                UserDashboardActivity.this.finish();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}