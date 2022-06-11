package com.example.netflixclone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class SliderPagerAdapter extends RecyclerView.Adapter<SliderPagerAdapter.ViewHolder> {

    ArrayList<Slide> viewPagerItemArrayList;

    public SliderPagerAdapter(ArrayList<Slide> viewPagerItemArrayList) {
        this.viewPagerItemArrayList = viewPagerItemArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.slider_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Slide slide = viewPagerItemArrayList.get(position);

        holder.imageView.setImageResource(slide.imageID);
        holder.Heading.setText(slide.heading);
    }

    @Override
    public int getItemCount() {
        return viewPagerItemArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView Heading;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.ivimage);
            Heading = itemView.findViewById(R.id.tvHeading);
        }


    }

//    private Context mContext;
//    private List<Slide> mList;
//
//    public SliderPagerAdapter(Context mContext, List<Slide> mList) {
//        this.mContext = mContext;
//        this.mList = mList;
//    }
//
//    @NonNull
//    @Override
//    public Object instantiateItem(@NonNull ViewGroup container, int position) {
//        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View slideLayout = inflater.inflate(R.layout.slider_item,null);
//
//        ImageView slideImage = slideLayout.findViewById(R.id.slider_image);
//        TextView sliderText = slideLayout.findViewById(R.id.slider_title);
//        slideImage.setImageResource(mList.get(position).getImage());
//        sliderText.setText(mList.get(position).getTitle());
//
//        container.addView(slideLayout);
//        return slideLayout;
//    }
//
//    @Override
//    public int getCount() {
//        return mList.size();
//    }
//
//    @Override
//    public boolean isViewFromObject(@NonNull View view, @NonNull Object obj) {
//        return view == obj;
//    }
//
//    @Override
//    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        container.removeView((View) object);
//    }
}
