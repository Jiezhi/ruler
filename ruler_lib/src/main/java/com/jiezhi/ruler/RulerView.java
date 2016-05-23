package com.jiezhi.ruler;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import com.github.jiezhi.ruler_lib.R;


/**
 * Created by jiezhi on 4/8/16.
 * Function:
 */
public class RulerView extends RecyclerView {
    private static final String TAG = "RulerView";
    private RulerResultCallback callback;
    private int scrollX = 0;

    public RulerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RulerView);
            setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            ta.recycle();
            init(context);
        }
    }

    private void init(Context context) {
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        setLayoutManager(manager);
        RulerAdapter adapter = new RulerAdapter(context);
        setAdapter(adapter);

        Log.d(TAG, "child view count: " + manager.getChildCount() / 2);
        Log.d(TAG, "child item count: " + manager.getItemCount() / 2);
//        manager.scrollToPositionWithOffset(manager.getChildCount() / 2, 0);

        // get the screen size
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;

        addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
//                Log.d(TAG, "scrolled x:" + dx + " y:" + dy);

                scrollX += dx;
                Log.d(TAG, "scrollX = " + scrollX / 10);
                callback.setResult(scrollX / 10);
            }
        });

        // Set the middle view of adapter to the screen center
        manager.scrollToPositionWithOffset((adapter.getItemCount() + 1) / 2, width / 2);
    }

    public void setCallback(RulerResultCallback callback) {
        this.callback = callback;
    }

    /**
     * Called when Ruler view changed
     */
    public interface RulerResultCallback{
        void setResult(int result);
    }
}
