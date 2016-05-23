package com.jiezhi.ruler;

import android.content.Context;
import android.graphics.Point;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.github.jiezhi.ruler_lib.R;


/**
 * Created by jiezhi on 4/8/16.
 * Function:
 */
public class RulerAdapter extends RecyclerView.Adapter<RulerAdapter.RulerViewHolder>{
    private static final String TAG = "RulerAdapter";
    private static final int BLANK_FILL = 4;
    private static final int RULER_COUNT = 10000 + 1;

    private Context context;
    private int width;

    public RulerAdapter(Context context) {
        this.context = context;
        // get device display dimensions
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        int height = size.y;
        Log.v(TAG, "width:" + width + " height:" + height);
    }

    @Override
    public RulerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        Log.d(TAG, "RulerViewHolder");
//        SimpleRulerView simpleRulerView = new SimpleRulerView(context);
        View view = LayoutInflater.from(context).inflate(R.layout.ruler_unit_horizontal, null);
        view.setLayoutParams(new RecyclerView.LayoutParams(100, RecyclerView.LayoutParams.MATCH_PARENT));
//        tv = (TextView) view.findViewById(R.id.ruler_num);
//        tv.setText(String.valueOf(10));
        return new RulerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RulerViewHolder holder, int position) {
        TextView tv = (TextView) holder.view.findViewById(R.id.ruler_num);
//        ImageView imageView = (ImageView) holder.view.findViewById(R.id.ruler_img);

        // Set blank unit for start and end ruler
        if (position < BLANK_FILL || position > RULER_COUNT + BLANK_FILL){
//            holder.view = new View(context);
//            imageView.setMinimumWidth(width / 2);
//            imageView.setVisibility(View.INVISIBLE);
//            tv.setVisibility(View.GONE);
            tv.setText(".");
        }else {
            tv.setText(String.valueOf(((position - RULER_COUNT / 2) - BLANK_FILL - 1) * 10));
        }
    }



    @Override
    public int getItemCount() {
        // add 2 half screen view
        return RULER_COUNT + BLANK_FILL * 2;
    }

    class RulerViewHolder extends RecyclerView.ViewHolder {
        private View view;

        public RulerViewHolder(View itemView) {
            super(itemView);
            view = itemView;
//            Log.d(TAG, "view holder");
        }

    }
}
