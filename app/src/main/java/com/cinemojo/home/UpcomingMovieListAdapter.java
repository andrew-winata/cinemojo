package com.cinemojo.home;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cinemojo.R;
import com.cinemojo.core.ApiPath;
import com.cinemojo.core.adapter.BaseRecyclerViewAdapter;
import com.cinemojo.core.glide.GlideApp;
import com.cinemojo.core.model.MovieResponse;
import com.cinemojo.databinding.ItemListUpcomingMovieBinding;

import java.util.List;

public class UpcomingMovieListAdapter extends
        BaseRecyclerViewAdapter<MovieResponse, UpcomingMovieListAdapter.ViewHolder> {

    public UpcomingMovieListAdapter(List<MovieResponse> items) {
        super(items);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_upcoming_movie, parent, false);
        return new UpcomingMovieListAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MovieResponse movie = items.get(position);
        holder.itemBinding.textReleaseDate.setText(movie.getReleaseDate());
        Context context = holder.itemBinding.layoutItemContainer.getContext();
        GlideApp.with(context)
                .load(ApiPath.TMDB_IMAGE_BASE_URL + movie.getPosterPath())
                .placeholder(ContextCompat.getDrawable(context, R.drawable.film_poster_placeholder))
                .into(holder.itemBinding.imgMoviePoster);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemListUpcomingMovieBinding itemBinding;

        public ViewHolder(View itemView) {
            super(itemView);
            itemBinding = DataBindingUtil.bind(itemView);
        }
    }
}
