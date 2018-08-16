package com.cinemojo.home;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.cinemojo.CinemojoApplication;
import com.cinemojo.R;
import com.cinemojo.core.model.PagingResponse;
import com.cinemojo.core.model.MovieResponse;
import com.cinemojo.core.repository.LoginSessionRepository;
import com.cinemojo.core.repository.MovieRepository;
import com.cinemojo.core.retrofit.RetrofitCallback;
import com.cinemojo.core.util.RetrofitUtils;
import com.cinemojo.core.util.Router;
import com.cinemojo.core.util.ViewUtils;
import com.cinemojo.databinding.ActivityHomeBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding viewBinding;
    private LoginSessionRepository loginSessionRepository;
    private MovieRepository movieRepository;

    private RatedMovieListAdapter popularMoviesAdapter;
    private RecyclerView.LayoutManager popularMoviesLayoutManager;
    private Call<PagingResponse<MovieResponse>> findPopularMoviesApiCall;

    private RatedMovieListAdapter nowPlayingMoviesAdapter;
    private RecyclerView.LayoutManager nowPlayingMoviesLayoutManager;
    private Call<PagingResponse<MovieResponse>> findNowPlayingMoviesApiCall;

    private RatedMovieListAdapter upcomingMoviesAdapter;
    private RecyclerView.LayoutManager upcomingMoviesLayoutManager;
    private Call<PagingResponse<MovieResponse>> findUpcomingMoviesApiCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginSessionRepository = CinemojoApplication.getInstance().getLoginSessionRepository();
        movieRepository = CinemojoApplication.getInstance().getMovieRepository();
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        setSupportActionBar(viewBinding.toolbarHome);

        popularMoviesLayoutManager = new LinearLayoutManager(this);
        ((LinearLayoutManager) popularMoviesLayoutManager).setOrientation(LinearLayoutManager.HORIZONTAL);
        viewBinding.listPopularMovies.setLayoutManager(popularMoviesLayoutManager);
        Callback<PagingResponse<MovieResponse>> findPopularMoviesApiCallback =
                RetrofitCallback.<PagingResponse<MovieResponse>>builder()
                .responseCompletedCallback(() -> {})
                .httpSuccessResponseCallback((responseBody) -> {
                    List<MovieResponse> popularMovieResponses = responseBody.getResults();
                    if (popularMoviesAdapter == null) {
                        popularMoviesAdapter = new RatedMovieListAdapter(popularMovieResponses);
                        viewBinding.listPopularMovies.setAdapter(popularMoviesAdapter);
                    } else {
                        popularMoviesAdapter.addItems(popularMovieResponses);
                    }
                })
                .httpErrorResponseCallback(()-> {
                    ViewUtils.showErrorSnackBar(this, viewBinding.coordinatorLayout, "Error has occurred");
                })
                .clientErrorCallback(__ -> {
                    ViewUtils.showErrorSnackBar(this, viewBinding.coordinatorLayout, "Error has occurred");
                }).build();

        findPopularMoviesApiCall = movieRepository.findPopularMovies(1);
        findPopularMoviesApiCall.enqueue(findPopularMoviesApiCallback);

        nowPlayingMoviesLayoutManager = new LinearLayoutManager(this);
        ((LinearLayoutManager) nowPlayingMoviesLayoutManager).setOrientation(LinearLayoutManager.HORIZONTAL);
        viewBinding.listNowPlayingMovies.setLayoutManager(nowPlayingMoviesLayoutManager);
        Callback<PagingResponse<MovieResponse>> findNowPlayingMoviesApiCallback =
                RetrofitCallback.<PagingResponse<MovieResponse>>builder()
                        .responseCompletedCallback(() -> {})
                        .httpSuccessResponseCallback((responseBody) -> {
                            List<MovieResponse> popularMovieResponses = responseBody.getResults();
                            if (nowPlayingMoviesAdapter == null) {
                                nowPlayingMoviesAdapter = new RatedMovieListAdapter(popularMovieResponses);
                                viewBinding.listNowPlayingMovies.setAdapter(nowPlayingMoviesAdapter);
                            } else {
                                nowPlayingMoviesAdapter.addItems(popularMovieResponses);
                            }
                        })
                        .httpErrorResponseCallback(()-> {
                            ViewUtils.showErrorSnackBar(this, viewBinding.coordinatorLayout, "Error has occurred");
                        })
                        .clientErrorCallback(__ -> {
                            ViewUtils.showErrorSnackBar(this, viewBinding.coordinatorLayout, "Error has occurred");
                        }).build();

        findNowPlayingMoviesApiCall = movieRepository.findNowPlayingMovies(1);
        findNowPlayingMoviesApiCall.enqueue(findNowPlayingMoviesApiCallback);

        upcomingMoviesLayoutManager = new LinearLayoutManager(this);
        ((LinearLayoutManager) upcomingMoviesLayoutManager).setOrientation(LinearLayoutManager.HORIZONTAL);
        viewBinding.listUpcomingMovies.setLayoutManager(upcomingMoviesLayoutManager);
        Callback<PagingResponse<MovieResponse>> findUpcomingMoviesApiCallback =
                RetrofitCallback.<PagingResponse<MovieResponse>>builder()
                        .responseCompletedCallback(() -> {})
                        .httpSuccessResponseCallback((responseBody) -> {
                            List<MovieResponse> popularMovieResponses = responseBody.getResults();
                            if (upcomingMoviesAdapter == null) {
                                upcomingMoviesAdapter = new RatedMovieListAdapter(popularMovieResponses);
                                viewBinding.listUpcomingMovies.setAdapter(upcomingMoviesAdapter);
                            } else {
                                upcomingMoviesAdapter.addItems(popularMovieResponses);
                            }
                        })
                        .httpErrorResponseCallback(()-> {
                            ViewUtils.showErrorSnackBar(this, viewBinding.coordinatorLayout, "Error has occurred");
                        })
                        .clientErrorCallback(__ -> {
                            ViewUtils.showErrorSnackBar(this, viewBinding.coordinatorLayout, "Error has occurred");
                        }).build();

        findUpcomingMoviesApiCall = movieRepository.findUpcomingMovies(1);
        findUpcomingMoviesApiCall.enqueue(findUpcomingMoviesApiCallback);
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

    @Override
    protected void onDestroy() {
        RetrofitUtils.cancelCall(findPopularMoviesApiCall);
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }
}
