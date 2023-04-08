package com.tfg.ecoapp.Main;

import android.view.MenuItem;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.tfg.ecoapp.Fragments.Otros.AboutFragment;
import com.tfg.ecoapp.Fragments.Otros.ContactFragment;
import com.tfg.ecoapp.Fragments.Otros.HelpFragment;
import com.tfg.ecoapp.Fragments.Otros.NotificationsFragment;
import com.tfg.ecoapp.Fragments.Otros.SettingsFragment;
import com.tfg.ecoapp.R;
public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedFragment = null;

            switch (menuItem.getItemId()) {
                case R.id.navigation_home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.navigation_map:
                    selectedFragment = new MapFragment();
                    break;
                case R.id.navigation_recycle:
                    selectedFragment = new RecycleFragment();
                    break;
                case R.id.navigation_chat:
                    selectedFragment = new ChatFragment();
                    break;
                case R.id.navigation_others:
                    showOthersPopup();
                    return false;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.contentFrameLayout, selectedFragment).commit();
            return true;
        }
    };


    private void showOthersPopup() {
        PopupMenu popup = new PopupMenu(MainActivity.this, bottomNavigationView.findViewById(R.id.navigation_others));
        popup.getMenuInflater().inflate(R.menu.pop_menu, popup.getMenu());

        popup.setOnMenuItemClickListener(item -> {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.action_messages:
                    selectedFragment = new NotificationsFragment();
                    break;
                case R.id.action_settings:
                    selectedFragment = new SettingsFragment();
                    break;
                case R.id.action_help:
                    selectedFragment = new HelpFragment();
                    break;
                case R.id.action_contact:
                    selectedFragment = new ContactFragment();
                    break;
                case R.id.action_about:
                    selectedFragment = new AboutFragment();
                    break;
                default:
                    return false;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.contentFrameLayout, selectedFragment).commit();
            return true;
        });

        popup.show();
    }
}