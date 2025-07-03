package es.upv.etsit.trabajoaplicusa;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import es.upv.etsit.trabajoaplicusa.adapters.SectionContentAdapter;
import es.upv.etsit.trabajoaplicusa.models.SectionContent;
import java.util.ArrayList;
import java.util.List;

public class ArtistasActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView tvSectionTitle, tvSectionDescription;
    private RecyclerView recyclerViewContent;
    private SectionContentAdapter adapter;
    private String currentSection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artistas);

        currentSection = getIntent().getStringExtra("section");

        initViews();
        setupToolbar();
        loadSectionContent();
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        tvSectionTitle = findViewById(R.id.tituloArtistas);
        tvSectionDescription = findViewById(R.id.eslogan);
        recyclerViewContent = findViewById(R.id.recyclerViewContent);

        recyclerViewContent.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void loadSectionContent() {
        List<SectionContent> contentList = new ArrayList<>();


        adapter = new SectionContentAdapter(contentList);
        recyclerViewContent.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
