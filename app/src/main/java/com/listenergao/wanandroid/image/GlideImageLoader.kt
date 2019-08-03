package com.listenergao.wanandroid.image

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.youth.banner.loader.ImageLoader

/**
 * create on 19/07/31
 * Banner 使用图片加载框架
 *
 * @author ListenerGao
 */
class GlideImageLoader : ImageLoader() {

    override fun displayImage(context: Context, path: Any, imageView: ImageView) {
        Glide.with(context).load(path).into(imageView);
    }

}