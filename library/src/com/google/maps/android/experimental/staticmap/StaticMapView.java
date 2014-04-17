package com.google.maps.android.experimental.staticmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.google.android.gms.maps.MapView;

public class StaticMapView extends FrameLayout {
    private ImageView mImageView;
    private StaticMapOptions mOptions;
    private StaticMapManager mManager;

    public StaticMapView(Context context) {
        super(context);
        init();
    }

    public StaticMapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public StaticMapView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        mImageView = new ImageView(getContext());
        addView(mImageView);
        reset();
    }

    public void reset() {
        mOptions = null;
        mImageView.setImageDrawable(new ColorDrawable(Color.GRAY));
        if (mManager != null) {
            mManager.remove(this);
        }
    }

    public void setManager(StaticMapManager manager) {
        if (mManager != null) {
            mManager.remove(this);
        }
        mManager = manager;
        if (mOptions != null) {
            mManager.add(this);
        }
    }

    public void setOptions(StaticMapOptions options) {
        mOptions = options;
        if (mManager != null) {
            mManager.add(this);
        }
    }

    public StaticMapOptions getOptions() {
        return mOptions;
    }

    void setMapView(MapView mMapView) {
        // TODO: Push to the back
        // Resize the map?
        mMapView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        addView(mMapView, 0);
    }

    void setBitmap(Bitmap bitmap) {
        mImageView.setImageBitmap(bitmap);
        removeViewAt(0);
    }
}