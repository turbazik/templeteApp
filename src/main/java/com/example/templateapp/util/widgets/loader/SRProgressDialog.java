package com.example.templateapp.util.widgets.loader;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import androidx.core.view.ViewCompat;

import com.example.templateapp.R;

import java.util.Objects;

public class SRProgressDialog extends AlertDialog {

    // Default background for the progress spinner
    private static final int CIRCLE_BG_LIGHT = 0xFFFAFAFA;
    private static final int MAX_ALPHA = 255;
    private Context mContext;
    private CircleImageView mCircleView;
    private MaterialProgressDrawable mProgress;
    private Animation.AnimationListener mListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

        @SuppressLint("NewApi")
        @Override
        public void onAnimationEnd(Animation animation) {
            mProgress.setAlpha(MAX_ALPHA);
            mProgress.start();
        }
    };


    private SRProgressDialog(Context context) {
        super(context);
        init(context);
    }


    public SRProgressDialog(Context context, int theme) {
        super(context, theme);
        init(context);
    }

    public static SRProgressDialog show(Context context, boolean cancelable, OnCancelListener cancelListener) {
        SRProgressDialog dialog = new SRProgressDialog(context);
        dialog.setCancelable(cancelable);
        dialog.setOnCancelListener(cancelListener);
        dialog.show();
        return dialog;
    }

    private void init(Context context) {
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.sr_alert_dialog_layout, null);
        ViewGroup parent = view.findViewById(R.id.sr_container);
        createProgressView(parent);

        Objects.requireNonNull(getWindow()).setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
    }

    private void createProgressView(ViewGroup parent) {
        mCircleView = new CircleImageView(getContext(), CIRCLE_BG_LIGHT);
        mProgress = new MaterialProgressDrawable(getContext(), parent);
        mProgress.setBackgroundColor(CIRCLE_BG_LIGHT);
        mCircleView.setImageDrawable(mProgress);
        parent.addView(mCircleView);
        setContentView(parent);
    }

    @Override
    public void onStart() {
        super.onStart();
        startScaleUpAnimation(mListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mProgress.stop();
    }

    @SuppressLint("NewApi")
    private void startScaleUpAnimation(Animation.AnimationListener listener) {
        mCircleView.setVisibility(View.VISIBLE);
        mProgress.setAlpha(MAX_ALPHA);
        Animation mScaleAnimation = new Animation() {
            @Override
            public void applyTransformation(float interpolatedTime, Transformation t) {
                setAnimationProgress(interpolatedTime);
            }
        };
        int mMediumAnimationDuration = 50;
        mScaleAnimation.setDuration(mMediumAnimationDuration);
        if (listener != null) {
            mCircleView.setAnimationListener(listener);
        }
        mCircleView.clearAnimation();
        mCircleView.startAnimation(mScaleAnimation);
    }

    private void setAnimationProgress(float progress) {
        if (isAlphaUsedForScale()) {
            setColorViewAlpha((int) (progress * MAX_ALPHA));
        } else {
            ViewCompat.setScaleX(mCircleView, progress);
            ViewCompat.setScaleY(mCircleView, progress);
        }
    }

    @SuppressLint("NewApi")
    private void setColorViewAlpha(int targetAlpha) {
        mCircleView.getBackground().setAlpha(targetAlpha);
        mProgress.setAlpha(targetAlpha);
    }

    private boolean isAlphaUsedForScale() {
        return false;
    }


}