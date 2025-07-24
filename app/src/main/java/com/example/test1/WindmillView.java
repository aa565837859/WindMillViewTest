package com.example.test1;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

public class WindmillView extends View {
    private Paint paint;
    private float rotateAngle = 0f;

    public WindmillView(Context context) {
        super(context);
        init();
    }

    public WindmillView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        // 启动旋转动画
        ValueAnimator animator = ValueAnimator.ofFloat(0, 360);
        animator.setDuration(2000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(animation -> {
            rotateAngle = (float) animation.getAnimatedValue();
            invalidate();
        });
        animator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        int radius = Math.min(width, height) / 2 - 20;
        int centerX = width / 2;
        int centerY = height / 2;

        canvas.save();
        canvas.translate(centerX, centerY);
        canvas.rotate(rotateAngle);

        // 画三片扇叶
        for (int i = 0; i < 3; i++) {
            switch (i) {
                case 0:
                    paint.setColor(Color.RED);
                    break;
                case 1:
                    paint.setColor(Color.YELLOW);
                    break;
                case 2:
                    paint.setColor(Color.BLUE);
                    break;
            }
            canvas.drawArc(-radius, -radius, radius, radius, i * 120, 80, true, paint);
        }
        // 画风车中心圆
        paint.setColor(Color.GRAY);
        canvas.drawCircle(0, 0, radius / 6, paint);

        canvas.restore();
    }
} 