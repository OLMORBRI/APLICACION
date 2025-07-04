package es.upv.etsit.trabajoaplicusa;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;

public class MainActivity extends AppCompatActivity {

    private Button btnmadcool, btnprimavera, btnarenal, btnviña, btnresurrect;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupToolbar();
        setupClickListeners();
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        btnmadcool = findViewById(R.id.btnmadcool);
        btnprimavera = findViewById(R.id.btnprimavera);
        btnviña = findViewById(R.id.btnviña);
        btnresurrect = findViewById(R.id.btnresurrect);
        btnarenal = findViewById(R.id.btnarenal);
        NestedScrollView scrollView = findViewById(R.id.scrollView);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
    }



    private void setupClickListeners() {
        btnmadcool.setOnClickListener(v -> openSection("mad_cool"));
        btnprimavera.setOnClickListener(v -> openSection("primavera_sound"));
        btnarenal.setOnClickListener(v -> openSection("arenal_sound"));
        btnviña.setOnClickListener(v -> openSection("viña_rock"));
        btnresurrect.setOnClickListener(v -> openSection("resurrection"));
    }

    private void openSection(String section) {
        Intent intent = new Intent(this, ArtistasActivity.class);
        intent.putExtra("section", section);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_eventos) {
            openSection("eventos");
            return true;
        } else if (id == R.id.menu_artistas) {
            openSection("artistas");
            return true;
        } else if (id == R.id.menu_lugares) {
            openSection("lugares");
            return true;
        } else if (id == R.id.menu_entradas) {
            openSection("tickets");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}