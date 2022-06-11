package com.example.netflixclone.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.app.ActivityOptions;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.content.Intent;

import com.example.netflixclone.Movie;
import com.example.netflixclone.MovieAdapter;
import com.example.netflixclone.MovieItemClickListener;
import com.example.netflixclone.R;
import com.example.netflixclone.Slide;
import com.example.netflixclone.SliderPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements MovieItemClickListener {

    ArrayList<Slide> viewPagerItemArrayList;
    ViewPager2 sliderpager;
    TabLayout indicator;
    RecyclerView MovieRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Actionbar logo
        ActionBar actionBar = getSupportActionBar();


        actionBar.setDisplayShowCustomEnabled(true);
        LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.custom_image,null);
        actionBar.setCustomView(v);






        iniViews();

        String[] place = iniSlider();

        iniPopularMovies();

        SliderPagerAdapter adapter = new SliderPagerAdapter(viewPagerItemArrayList);

        sliderpager.setAdapter(adapter);

        sliderpager.setClipToPadding(false);

        sliderpager.setClipChildren(false);

        sliderpager.setOffscreenPageLimit(2);

        sliderpager.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MainActivity.SliderTimer(),4000,6000);

        new TabLayoutMediator(indicator,sliderpager,
                (tab, position) -> tab.setText(place[position])).attach();
    }

    private void iniPopularMovies() {
        //Recyclerview Setup
        //ini data

        List<Movie> lstMovies = new ArrayList<>();
        lstMovies.add(new Movie("Dark",R.drawable.dark,R.drawable.slider1));
        lstMovies.add(new Movie("Thor Love and Thunder",R.drawable.thor,R.drawable.slider4));
        lstMovies.add(new Movie("Narcos",R.drawable.narcos,R.drawable.slider2));
        lstMovies.add(new Movie("Vikings",R.drawable.vikings,R.drawable.slider2));
        lstMovies.add(new Movie("Breaking Bad",R.drawable.breakingbad,R.drawable.slider4));
        lstMovies.add(new Movie("Legion",R.drawable.legion,R.drawable.slider3));
        lstMovies.add(new Movie("300",R.drawable.threehundred,R.drawable.slider3));
        lstMovies.add(new Movie("Black Mirror",R.drawable.blackmirror,R.drawable.slider4));

        MovieAdapter movieAdapter = new MovieAdapter(this,lstMovies,this);
        MovieRV.setAdapter(movieAdapter);
        MovieRV.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
    }

    @NonNull
    private String[] iniSlider() {
        int[] images = {R.drawable.slider1,R.drawable.slider2,R.drawable.slider3,R.drawable.slider4};
        String[] heading = {"Blade Runner 2049","Dunkirk","The King","Daredevil"};
        String[] place = {"","","",""};

        viewPagerItemArrayList = new ArrayList<>();
        for (int i =0; i< images.length ; i++){

            Slide slide = new Slide(images[i],heading[i]);
            viewPagerItemArrayList.add(slide);

        }
        return place;
    }

    private void iniViews() {
        indicator = findViewById(R.id.indicator);
        MovieRV = findViewById(R.id.Rv_movies);
        sliderpager = findViewById(R.id.slider_pager);
    }

    @Override
    public void OnMovieClick(Movie movie, ImageView movieImageView) {

        Intent intent = new Intent(this, MovieDetailActivity.class);

        intent.putExtra("title",movie.getTitle());
        intent.putExtra("imgURL",movie.getThumbnail());
        intent.putExtra("imgCover",movie.getCoverPhoto());


        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, movieImageView,"sharedName");
        startActivity(intent,options.toBundle());

        Toast.makeText(this, "item clicked:" + movie.getTitle(), Toast.LENGTH_SHORT).show();
    }

    class SliderTimer extends TimerTask {

        @Override
        public void run() {

            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (sliderpager.getCurrentItem() < viewPagerItemArrayList.size() - 1){
                        sliderpager.setCurrentItem(sliderpager.getCurrentItem() + 1);
                    }
                    else
                        sliderpager.setCurrentItem(0);
                }
            });
        }
    }
}