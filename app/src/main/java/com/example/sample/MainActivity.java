package com.example.sample;

import android.animation.ValueAnimator;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ValueAnimator mAnimator;
    private ValueAnimator mAnimator2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button but = (Button) findViewById(R.id.button);
        Button buttonReverse = (Button) findViewById(R.id.buttonReverse);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View View = findViewById(R.id.filling);
                LayerDrawable layer = (LayerDrawable) View.getBackground();
                final ClipDrawable draw = (ClipDrawable) layer.findDrawableByLayerId(R.id.clipping);
                draw.setLevel(5000);
                mAnimator = ValueAnimator.ofInt(0, 10000);
                mAnimator.setRepeatMode(ValueAnimator.REVERSE);

                mAnimator.setDuration(1000);
                mAnimator.setInterpolator(new DecelerateInterpolator());
                mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        Integer value = (Integer) valueAnimator.getAnimatedValue();
                        draw.setLevel(value);

                    }
                });
                mAnimator.start();
            }
        });
        buttonReverse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View View = findViewById(R.id.filling);
                LayerDrawable layer = (LayerDrawable) View.getBackground();
                final ClipDrawable draw = (ClipDrawable) layer.findDrawableByLayerId(R.id.clipping);

                mAnimator2 = ValueAnimator.ofInt(10000, 0);


                mAnimator2.setDuration(1000);
                mAnimator2.setInterpolator(new AccelerateInterpolator());
                mAnimator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        Integer value = (Integer) valueAnimator.getAnimatedValue();
                        draw.setLevel(value);

                    }
                });
                mAnimator2.start();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Здесь, перед удалением Activity, зачистим аниматор во избежание утечек памяти
        mAnimator.cancel();
        mAnimator.removeAllUpdateListeners();
        mAnimator = null;
    }
}
