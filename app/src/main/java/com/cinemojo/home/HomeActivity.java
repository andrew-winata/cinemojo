package com.cinemojo.home;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.cinemojo.CinemojoApplication;
import com.cinemojo.R;
import com.cinemojo.core.model.MoviePagingResponse;
import com.cinemojo.core.model.PopularMovieResponse;
import com.cinemojo.core.repository.LoginSessionRepository;
import com.cinemojo.core.repository.MovieRepository;
import com.cinemojo.core.util.Router;
import com.cinemojo.databinding.ActivityHomeBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding viewBinding;
    private LoginSessionRepository loginSessionRepository;
    private MovieRepository movieRepository;
    private PopularMoviesAdapter popularMoviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginSessionRepository = CinemojoApplication.getInstance().getLoginSessionRepository();
        movieRepository = CinemojoApplication.getInstance().getMovieRepository();
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        setSupportActionBar(viewBinding.toolbarHome);

        movieRepository.findPopularMovies(1).enqueue(new Callback<MoviePagingResponse<PopularMovieResponse>>() {
            @Override
            public void onResponse(Call<MoviePagingResponse<PopularMovieResponse>> call, Response<MoviePagingResponse<PopularMovieResponse>> response) {

            }

            @Override
            public void onFailure(Call<MoviePagingResponse<PopularMovieResponse>> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sign_in:
                if (!loginSessionRepository.isAuthenticated()) {
                    Router.goToSignIn(this);
                } else {
                    Router.goToProfile(this);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
