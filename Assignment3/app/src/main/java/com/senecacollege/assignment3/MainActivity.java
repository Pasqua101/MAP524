package com.senecacollege.assignment3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {


    private static ArrayList<MovieModel> movieList;
    private static RecyclerView recyclerView;
    private static RecyclerAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         movieList = new ArrayList<>(Arrays.asList(
                new MovieModel("Grave of the Fireflies", 1988, "tt0095327", "War/Animation", "https://image.tmdb.org/t/p/original/wcNkHDbyc290hcWk7KXbBZUuXpq.jpg"),
                new MovieModel("Gran Turismo", 2023, "tt4495098", "Action/Sport", "https://juksun.com/wp-content/uploads/2023/02/Gran-Turismo-Movie-Poster-1.jpg"),
                 new MovieModel("Oppenheimer", 2023, "tt15398776", "Thriller", "https://assets-prd.ignimgs.com/2022/07/21/oppenheimer-poster-1658411601593.jpeg"),
                 new MovieModel("Five Nights at Freddy's", 2023, "tt4589218", "Horror/Adaptation", "https://m.media-amazon.com/images/M/MV5BMDM5NmQyNGYtZmRiMS00NDQwLTkzZGMtYWQyZjBkMmI3MGI2XkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_.jpg"),
                new MovieModel("Spider-Man: Across the Spider-Verse", 2023, "tt9362722", "Animation/Action", "https://www.brickfanatics.com/wp-content/uploads/2022/12/Spider-Man-Across-the-Spider-Verse-poster-712x1024.jpg"),
                new MovieModel("Spider-Man", 2002, "tt0145487", "Action/Adventure", "https://th.bing.com/th/id/OIP.8YVxHY_sNaqKXFK-uIOkmgHaLB?pid=ImgDet&rs=1"),
                new MovieModel("Spider-Man 2", 2004, "tt0316654", "Action/Adventure", "https://www.themoviedb.org/t/p/original/olxpyq9kJAZ2NU1siLshhhXEPR7.jpg"),
                new MovieModel("Spider-Man 3", 2007, "tt0413300", "Action/Adventure", "https://th.bing.com/th/id/R.46e97616057921b003312ca8e2bda6bd?rik=t9opCyQOg4A2eQ&pid=ImgRaw&r=0"),
                new MovieModel("All Quiet on the Western Front", 2022, "tt1016150", "Action/Drama", "https://www.naijaprey.com/wp-content/uploads/2022/10/All-Quiet-On-The-Western-Front.jpeg"),
                new MovieModel("Asteroid City", 2023, "tt14230388", "Comedy/Drama", "https://www.syfy.com/sites/syfy/files/styles/scale_640_no_scale/public/2023/05/asteroid_poster_ig.jpg")
        ));

        adapter = new RecyclerAdapter(movieList);

        recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = findViewById(R.id.addFab);
        fab.setOnClickListener(view -> {
            launchAddMovieActivity(view);
        });

        adapter.setOnItemClickListener(movie -> {
            Intent intent = new Intent(MainActivity.this, MovieDetailsActivity.class);
            intent.putExtra("movie", movie);
            startActivity(intent);
        });


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                return makeMovementFlags(0, ItemTouchHelper.RIGHT);
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // Function gets called when swiping right. Line below gets the item deleted at a certain position
                MovieModel deletedMovie = movieList.get(viewHolder.getAdapterPosition());

                // Get position of the item
                int position = viewHolder.getAdapterPosition();


                movieList.remove(viewHolder.getAdapterPosition());

                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());

                Snackbar.make(recyclerView, "Deleted Movie: " + deletedMovie.getTitle(), Snackbar.LENGTH_LONG).setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        movieList.add(position, deletedMovie);
                        adapter.notifyItemInserted(position);
                    }
                }).show();
            }
        }).attachToRecyclerView(recyclerView);
    }


    public void launchAddMovieActivity(View view){
        Intent intent = new Intent(this, AddMovieActivity.class);
        startActivity(intent);

    }

    public static void addMovie(MovieModel movie){
        movieList.add(movie);
        adapter.notifyItemInserted(movieList.size() - 1);
        recyclerView.scrollToPosition(movieList.size() - 1);
    }
}