package sg.edu.rp.c346.id22015127.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class List extends AppCompatActivity {

    Button back, pg13;
    ListView lv;
    ArrayAdapter<String> aa;
    ArrayList<Movie> arrList;
    boolean filter;

    @Override
    protected void onResume() {
        super.onResume();
        aa = new CustomAdapter(this, R.layout.rows, arrList);
        lv.setAdapter(aa);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        back = findViewById(R.id.back);
        pg13 = findViewById(R.id.pg13);
        lv = findViewById(R.id.listview);

        filter = false;

        DBHelper db = new DBHelper(List.this);
        db.close();
        DBHelper lvdb = new DBHelper(List.this);
        arrList = lvdb.getMovieContent();
        lvdb.close();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(List.this, MainActivity.class);
                startActivity(intent);
            }
        });
        pg13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(List.this);
                arrList.clear();
                if(!filter){
                    arrList.addAll(dbh.getPG13());
                    filter = true;
                    pg13.setText("Show all movies");
                }
                else {
                    arrList.addAll(dbh.getMovieContent());
                    filter = false;
                    pg13.setText("Show All PG-13 Movies");
                }
                aa.notifyDataSetChanged();
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Movie data = arrList.get(position);
                Intent i = new Intent(List.this,
                        EditMovies.class);
                i.putExtra("data", data);
                startActivity(i);
            }
        });
    }
}