package es.upv.etsit.trabajoaplicusa;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.core.widget.NestedScrollView;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;

public class MainActivity extends AppCompatActivity {

    private Button btnmadcool, btnprimavera, btnarenal, btnviña, btnresurrect;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupToolbar();
        setupNavigation();
        setupClickListeners();
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        btnmadcool = findViewById(R.id.btnmadcool);
        btnprimavera = findViewById(R.id.btnprimavera);
        btnviña = findViewById(R.id.btnviña);
        btnresurrect = findViewById(R.id.btnresurrect);
        btnarenal = findViewById(R.id.btnarenal);
        NestedScrollView scrollView = findViewById(R.id.scrollView);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        drawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }



    private void setupClickListeners() {
        btnmadcool.setOnClickListener(v -> openSection("mad_cool"));
        btnprimavera.setOnClickListener(v -> openSection("primavera_sound"));
        btnarenal.setOnClickListener(v -> openSection("arenal_sound"));
        btnviña.setOnClickListener(v -> openSection("viña_rock"));
        btnresurrect.setOnClickListener(v -> openSection("resurrection"));
    }

    private void openSection(String section) {
        Intent intent;
        switch (section) {
            case "eventos":
                intent = new Intent(this, EventosActivity.class);
                break;
            case "tickets":
                intent = new Intent(this, EntradasActivity.class);
                break;
            case "lugares":
                intent = new Intent(this, LugaresActivity.class);
                break;
            default:
                intent = new Intent(this, ArtistasActivity.class);
                intent.putExtra("section", section);
                break;
        }
        startActivity(intent);
    }

    private void setupNavigation() {
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();


            if (id == R.id.nav_eventos) {
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            } else if (id == R.id.nav_artistas) {
                openSection("artistas");
            } else if (id == R.id.nav_lugares) {
                startActivity(new Intent(this, LugaresActivity.class));
            } else if (id == R.id.nav_entradas) {
                startActivity(new Intent(this, EntradasActivity.class));
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }
}