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

public class EditMovies extends AppCompatActivity {

    Button update, delete, cancel;
    EditText mTitle, genre, yor, id;
    Spinner rating;
    Movie data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movies);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);
        cancel = findViewById(R.id.cancel);
        id = findViewById(R.id.editMovieID);
        mTitle = findViewById(R.id.editTitle);
        genre = findViewById(R.id.editGenre);
        yor = findViewById(R.id.editYear);
        rating = findViewById(R.id.spinner);

        Intent i = getIntent();
        data = (Movie) i.getSerializableExtra("data");
        id.setText(""+data.getId());
        mTitle.setText(data.getTitle());
        genre.setText(data.getGenre());
        yor.setText(""+data.getYear());
        int ratings = data.getRating();
        rating.setSelection(ratings);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditMovies.this);
                data.setGenre(genre.getText().toString());
                data.setTitle(mTitle.getText().toString());
                data.setYear(Integer.parseInt(yor.getText().toString()));
                data.setRating(rating.getSelectedItemPosition());
                dbh.updateMovie(data);
                dbh.close();
                Toast.makeText(EditMovies.this, "Update successful", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(EditMovies.this, List.class);
                startActivity(i);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditMovies.this);
                dbh.deleteMovie(data.getId());
                Toast.makeText(EditMovies.this, "Delete successful", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(EditMovies.this, List.class);
                startActivity(i);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EditMovies.this, List.class);
                startActivity(i);
                Toast.makeText(EditMovies.this, "Cancelled", Toast.LENGTH_SHORT).show();
            }
        });
    }
}