package es.upv.etsit.trabajoaplicusa;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;

public class MainActivity extends AppCompatActivity {

    private TextView tvEventTitle, tvEventEslogan;
    private Button btnmadcool, btnprimavera, btnarenal, btnviña, btnresurrect;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupToolbar();
        setupEventInfo();
        setupClickListeners();
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        tvEventTitle = findViewById(R.id.tvEventTitle);
        tvEventEslogan = findViewById(R.id.eslogan);
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

    @SuppressLint("SetTextI18n")
    private void setupEventInfo() {
        tvEventTitle.setText("FESTIVAL DE VERANO 2025");
        tvEventEslogan.setText("Connected with your soul");
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