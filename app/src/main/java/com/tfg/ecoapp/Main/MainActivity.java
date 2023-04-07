import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        // Cargar el primer fragmento (HomeFragment) al iniciar la aplicación
        getSupportFragmentManager().beginTransaction().replace(R.id.contentFrameLayout, new HomeFragment()).commit();
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.navigation_map:
                    selectedFragment = new MapsFragment();
                    break;
                case R.id.navigation_recycle:
                    selectedFragment = new RecycleFragment();
                    break;
                case R.id.navigation_chat:
                    selectedFragment = new ChatFragment();
                    break;
                case R.id.navigation_others:
                    selectedFragment = new OthersFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.contentFrameLayout, selectedFragment).commit();
            return true;
        }
    };
}
