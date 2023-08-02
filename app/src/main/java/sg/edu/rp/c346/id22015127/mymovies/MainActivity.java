package sg.edu.rp.c346.id22015127.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button insert, show;
    EditText mTitle, genre, yor;
    Spinner rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insert = findViewById(R.id.insert);
        show = findViewById(R.id.display);
        mTitle = findViewById(R.id.title);
        genre = findViewById(R.id.genre);
        yor = findViewById(R.id.year);
        rating = findViewById(R.id.spinner);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);
                db.insertMovie(mTitle.getText().toString(), genre.getText().toString(), Integer.parseInt(yor.getText().toString()), rating.getSelectedItemPosition());
                db.close();
                Toast.makeText(MainActivity.this, "New movie inserted successfully", Toast.LENGTH_SHORT).show();
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, List.class);
                startActivity(intent);
            }
        });
    }
}