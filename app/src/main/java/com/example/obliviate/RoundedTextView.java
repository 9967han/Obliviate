package com.example.obliviate;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

public class RoundedTextView extends AppCompatTextView {

    private Context context;
    private Path path;

    public RoundedTextView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public RoundedTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public RoundedTextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        this.context = context;
    }

    @Override
    public void draw(Canvas canvas) {
        path = new Path();
        path.addRoundRect(0, 0, getWidth(), getHeight(), 50, 50, Path.Direction.CW);

        canvas.clipPath(path);
        super.draw(canvas);
    }
}