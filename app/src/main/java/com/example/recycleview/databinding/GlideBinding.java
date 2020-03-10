package com.example.recycleview.databinding;

import android.content.Context;
import android.media.Image;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.recycleview.R;

public class GlideBinding {
    @BindingAdapter("imgResource")
    public static void setImageSource(ImageView view,String imgUrl){
        Context context=view.getContext();
        RequestOptions  option=new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background);
        Glide.with(context).setDefaultRequestOptions(option).load(imgUrl).into(view);

    }
}
