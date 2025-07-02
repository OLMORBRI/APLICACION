package es.upv.etsit.trabajoaplicusa;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    private TextView tituloevento;
    private Button enterevento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        initViews();
        setupClickListeners();
    }

    private void initViews() {
        tituloevento = findViewById(R.id.welcome_titulo);
        enterevento = findViewById(R.id.enter_evento);

        tituloevento.setText("FESTIVAL 2025");
    }

    private void setupClickListeners() {
        enterevento.setOnClickListener(v -> {
            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
