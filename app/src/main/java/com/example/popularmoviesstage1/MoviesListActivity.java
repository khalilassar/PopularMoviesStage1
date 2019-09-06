package com.example.popularmoviesstage1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.popularmoviesstage1.Utils.NetworksUtils;

import java.util.List;

public class MoviesListActivity extends AppCompatActivity {
    private MoviesListViewModel mMoviesListViewModel;
    //i was thinking if i can save the menu object in view model or something like that
    //pleasse see on crate options menue and give me feedback
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_movies);
        RecyclerView mMoviesListRv = mMoviesListRv = findViewById(R.id.list_movies_rv);
        final MoviesListAdapter adapter = new MoviesListAdapter(this);
        adapter.setOnRvItemClickListener(new MoviesListAdapter.onRvItemClickListener() {
            @Override
            public void onItemClicked(Movie movie) {
                //LAUNCH DETAILS ACTIVITY
                Intent intent = new Intent(getBaseContext(), DetailsActivity.class);
                intent.putExtra(DetailsActivity.POSTER_IMAGE_PATH, movie.getPoster_path());
                intent.putExtra(DetailsActivity.TITLE, movie.getTitle());
                intent.putExtra(DetailsActivity.RELEASE_DATE, movie.getRelease_date());
                intent.putExtra(DetailsActivity.RATING, movie.getVote_average());
                intent.putExtra(DetailsActivity.SYNPOSIS, movie.getOverview());
                startActivity(intent);
            }
        });
        mMoviesListRv.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        mMoviesListRv.setLayoutManager(layoutManager);
        mMoviesListRv.setHasFixedSize(true);
        mMoviesListViewModel = ViewModelProviders.of(this).get(MoviesListViewModel.class);
        mMoviesListViewModel.getMoviesListLiveData().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                adapter.submitList(movies);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movie_list_menu, menu);
        this.menu = menu;
        //to sync between list and current menu options"when rotating"
        //please give feedback if there is a better way
        //        if(mMenu ==null){
//            getMenuInflater().inflate(R.menu.movie_list_menu, menu);
//            mMoviesListViewModel.setMenu(menu);
//        }
//        else{
//            getMenuInflater().inflate(mMenu, menu);
//
//        }
        //i was thinking if i can save the menu object in view model or something like that
        if (menu.findItem(R.id.popular_btn).isChecked()) {
            mMoviesListViewModel.seFilter(NetworksUtils.POPULAR_MOVIES_BASE_URL);
        } else if (menu.findItem(R.id.top_rated_btn).isChecked()) {
            mMoviesListViewModel.seFilter(NetworksUtils.TOP_RATED_MOVIES_BASE_URL);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.popular_btn) {
            boolean f = item.isChecked();
            item.setChecked(true);
            menu.findItem(R.id.top_rated_btn).setChecked(false);
            Log.d("dddd", "mMoviesListViewModel.seFilter(NetworksUtils.POPULAR_MOVIES_BASE_URL);");
            mMoviesListViewModel.seFilter(NetworksUtils.POPULAR_MOVIES_BASE_URL);
            return true;
        } else if (id == R.id.top_rated_btn) {
            item.setChecked(true);
            menu.findItem(R.id.popular_btn).setChecked(false);
            mMoviesListViewModel.seFilter(NetworksUtils.TOP_RATED_MOVIES_BASE_URL);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
