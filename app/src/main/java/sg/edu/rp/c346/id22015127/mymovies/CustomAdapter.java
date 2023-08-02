package sg.edu.rp.c346.id22015127.mymovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Movie> movieList;

    public CustomAdapter(Context context, int resource, ArrayList<Movie> objects){
        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        movieList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(layout_id, parent, false);

        TextView tvTitle = rowView.findViewById(R.id.textViewTitle);
        TextView tvYear = rowView.findViewById(R.id.textViewYear);
        ImageView ivRating = rowView.findViewById(R.id.rating);
        TextView tvGenre = rowView.findViewById(R.id.textViewGenre);

        Movie detail = movieList.get(position);

        tvTitle.setText(detail.getTitle());
        tvGenre.setText(detail.getGenre());
        tvYear.setText(detail.getYear().toString());
        if (detail.getRating() == 0){
            ivRating.setImageResource(R.drawable.rating_g);
        } else if (detail.getRating() == 1) {
            ivRating.setImageResource(R.drawable.rating_pg);
        } else if (detail.getRating() == 2) {
            ivRating.setImageResource(R.drawable.rating_pg13);
        } else if (detail.getRating() == 3) {
            ivRating.setImageResource(R.drawable.rating_nc16);
        } else if (detail.getRating() == 4) {
            ivRating.setImageResource(R.drawable.rating_m18);
        } else {
            ivRating.setImageResource(R.drawable.rating_r21);
        }

        return rowView;
    }
}
