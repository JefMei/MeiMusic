package com.meimei.meimusic.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.renderscript.RenderScript;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.meimei.meimusic.utils.FastBlurUtil;

/**
 * Created by 梅梅 on 2017/5/7.
 */
public class BlurTransformation extends BitmapTransformation{

    private RenderScript rs;
    private Context context;
    private String url;


    public BlurTransformation(Context context,String url) {
        super( context );
        this.context = context;
        this.url = url;
        rs = RenderScript.create( context );
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
       /* Bitmap blurredBitmap = toTransform.copy( Bitmap.Config.ARGB_8888, true );

        // Allocate memory for Renderscript to work with
        Allocation input = Allocation.createFromBitmap(
                rs,
                blurredBitmap,
                Allocation.MipmapControl.MIPMAP_FULL,
                Allocation.USAGE_SHARED
        );
        Allocation output = Allocation.createTyped(rs, input.getType());

        // Load up an instance of the specific script that we want to use.
        ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        script.setInput(input);

        // Set the blur radius
        script.setRadius(25);

        // Start the ScriptIntrinisicBlur
        script.forEach(output);

        // Copy the output to the blurred bitmap
        output.copyTo(blurredBitmap);

        toTransform.recycle();

        *//*Drawable drawable = new BitmapDrawable(blurredBitmap);
        drawable.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);*//*


        return *//*((BitmapDrawable)drawable).getBitmap();*//*blurredBitmap;*/
        Drawable drawable = new BitmapDrawable(FastBlurUtil.GetUrlBitmap(url,10));
        drawable.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);

        return ((BitmapDrawable)drawable).getBitmap();
    }

    @Override
    public String getId() {
        return "blur";
    }

}
