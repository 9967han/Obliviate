package com.example.obliviate;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class RoundedRelativeLayout extends RelativeLayout {

    private Context context;
    private Path path;

    public RoundedRelativeLayout(Context context) {
        super(context);
        init(context);
    }

    public RoundedRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public RoundedRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public RoundedRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public void init(Context context) {
        this.context = context;
    }

    @Override
    public void draw(Canvas canvas) {
        path = new Path();
        path.addRoundRect(0, 0, getWidth(), getHeight(), 75, 75, Path.Direction.CW);

        canvas.clipPath(path);
        super.draw(canvas);
    }
}