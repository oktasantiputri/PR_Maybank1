package com.example.project04;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.project04.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ActionBarDrawerToggle toggle;
    //private Button btn_instagram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //btn_instagram = findViewById(R.id.btn_instagram);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        initView();
    }

    private void initView()
    {
        //custom toolbar
        setSupportActionBar(binding.toolbar);

        //default fragment dibuka pertama kali
        getSupportActionBar().setTitle("Home");
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new HomeFragment())
                .commit();
        binding.navView.setCheckedItem(R.id.nav_home);

        //membuka drawer
        toggle = new ActionBarDrawerToggle(this, binding.drawer, binding.toolbar,
                R.string.open, R.string.close);

        //drawer back button
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));

        //sinkronisasi drawer
        toggle.syncState();

        //salah satu menu navigasi dipilih
        binding.navView.setNavigationItemSelectedListener
                (new NavigationView.OnNavigationItemSelectedListener()
        {
            Fragment fragment = null;

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
            switch (item.getItemId())
            {
                case R.id.nav_home:
                    fragment = new HomeFragment();
                    binding.drawer.closeDrawer(GravityCompat.START);
                    callFragment(fragment);
                    break;
                case R.id.nav_contacy_us:
                    fragment = new ContactUsFragment();
                    getSupportActionBar().setTitle("Contact Us");
                    binding.drawer.closeDrawer(GravityCompat.START);
                    callFragment(fragment);
                    break;
                case R.id.nav_about_us:
                    fragment = new AboutUsFragment();
                    getSupportActionBar().setTitle("About Us");
                    binding.drawer.closeDrawer(GravityCompat.START);
                    callFragment(fragment);
                    break;

            }
                return true;
            }
        });

    }

    private void callFragment(Fragment fragment)
    {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(android.R.anim.slide_in_left,
                android.R.anim.slide_out_right);
        transaction.replace(R.id.frameLayout, fragment);
        transaction.commit();
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.right_option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    Fragment fragment = null;
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {

        switch (item.getItemId()) {
            case R.id.menu_home:
                fragment = new HomeFragment();
                binding.drawer.closeDrawer(GravityCompat.START);
                callFragment(fragment);
                break;
            case R.id.menu_contact_us:
                fragment = new ContactUsFragment();
                getSupportActionBar().setTitle("Contact Us");
                binding.drawer.closeDrawer(GravityCompat.START);
                callFragment(fragment);
                break;
            case R.id.menu_about_us:
                fragment = new AboutUsFragment();
                getSupportActionBar().setTitle("About Us");
                binding.drawer.closeDrawer(GravityCompat.START);
                callFragment(fragment);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}