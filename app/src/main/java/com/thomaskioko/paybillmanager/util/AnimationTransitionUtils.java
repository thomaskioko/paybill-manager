package com.thomaskioko.paybillmanager.util;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.thomaskioko.paybillmanager.R;

/**
 * This class contains various animations & transitions
 *
 * @author Thomas Kioko
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class AnimationTransitionUtils {


    /**
     * Method to start reveal animation
     *
     * @param context     Application context
     * @param view        View to start the animation from
     * @param startRadius Radius to start the animation
     * @param color       Color of the view
     * @param x           Center on the x axis
     * @param y           Center on teh y axis
     * @param listener    Listener object
     */
    public static void animateRevealShow(final Context context, final View view, final int startRadius,
                                         @ColorRes final int color, int x, int y, final OnRevealAnimationListener listener) {
        float finalRadius = (float) Math.hypot(view.getWidth(), view.getHeight());

        Animator anim =
                ViewAnimationUtils.createCircularReveal(view, x, y, startRadius, finalRadius);
        anim.setDuration(context.getResources().getInteger(R.integer.animation_duration));
        anim.setStartDelay(100);
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                view.setBackgroundColor(context.getResources().getColor(color));
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.VISIBLE);
                listener.onRevealShow();
            }
        });
        anim.start();
    }


    /**
     * Method to hide reveal animation
     *
     * @param context  Application context
     * @param view     View to start the animation from
     * @param color    Color of the view
     * @param listener Listener object
     */
    public static void animateRevealHide(final Context context, final View view, @ColorRes final int color,
                                         final int finalRadius, final OnRevealAnimationListener listener) {
        int cx = (view.getLeft() + view.getRight()) / 2;
        int cy = (view.getTop() + view.getBottom()) / 2;
        int initialRadius = view.getWidth();

        Animator anim =
                ViewAnimationUtils.createCircularReveal(view, cx, cy, initialRadius, finalRadius);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                view.setBackgroundColor(context.getResources().getColor(color));
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                listener.onRevealHide();
                view.setVisibility(View.INVISIBLE);
            }
        });
        anim.setDuration(context.getResources().getInteger(R.integer.animation_duration));
        anim.start();
    }

    /**
     * Method to enter slide up animation
     *
     * @param context Application context
     * @param views   Views to start the animation from
     */
    public static void startEnterTransitionSlideUp(Context context, View... views) {
        Animation slideAnimationUp = AnimationUtils.loadAnimation(context, R.anim.abc_slide_in_bottom);
        slideAnimationUp.setDuration(300);
        slideAnimationUp.setInterpolator(new LinearOutSlowInInterpolator());
        slideAnimationUp.setAnimationListener(getShowAnimationListener(null, views));
        startAnimations(slideAnimationUp, views);
    }

    /**
     * Method to enter slide down animation
     *
     * @param context Application context
     * @param views   Views to start the animation from
     */
    public static void startEnterTransitionSlideDown(Context context, View... views) {
        Animation slideAnimationDown = AnimationUtils.loadAnimation(context, R.anim.abc_slide_in_top);
        slideAnimationDown.setDuration(300);
        slideAnimationDown.setInterpolator(new LinearOutSlowInInterpolator());
        slideAnimationDown.setAnimationListener(getShowAnimationListener(null, views));
        startAnimations(slideAnimationDown, views);
    }

    /**
     * Method to start return slide down animation
     *
     * @param context  Application context
     * @param listener Listener object
     * @param views    Views objects
     */
    public static void startReturnTransitionSlideDown(Context context, OnReturnAnimationFinished listener, View... views) {
        Animation slideAnimation = AnimationUtils.loadAnimation(context, R.anim.slide_out_bottom);
        slideAnimation.setDuration(300);
        slideAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        slideAnimation.setAnimationListener(getGoneAnimationListener(listener, views));
        startAnimations(slideAnimation, views);
    }

    /**
     * Method to start return slide up animation
     *
     * @param context  Application context
     * @param listener Listener object
     * @param views    Views objects
     */
    public static void startReturnTransitionSlideUp(Context context, OnReturnAnimationFinished listener, View... views) {
        Animation slideAnimationUp = AnimationUtils.loadAnimation(context, R.anim.slide_out_top);
        slideAnimationUp.setDuration(300);
        slideAnimationUp.setInterpolator(new AccelerateDecelerateInterpolator());
        slideAnimationUp.setAnimationListener(getGoneAnimationListener(listener, views));
        startAnimations(slideAnimationUp, views);
    }

    /**
     * Method to start scale up animation
     *
     * @param context Application context
     * @param views   Views to start the animation from
     */
    public static void startScaleUpAnimation(Context context, View... views) {
        Animation scaleAnimation = AnimationUtils.loadAnimation(context, R.anim.scale_up);
        scaleAnimation.setDuration(300);
        scaleAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleAnimation.setAnimationListener(getShowAnimationListener(null, views));
        startAnimations(scaleAnimation, views);
    }

    /**
     * Method to start scale down animation
     *
     * @param context Application context
     * @param views   Views to start the animation from
     */
    public static void startScaleDownAnimation(Context context, View... views) {
        Animation scaleAnimation = AnimationUtils.loadAnimation(context, R.anim.scale_down);
        scaleAnimation.setDuration(300);
        scaleAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleAnimation.setAnimationListener(getGoneAnimationListener(null, views));
        startAnimations(scaleAnimation, views);
    }

    /**
     * Listens for 'hide' animation type
     *
     * @param listener Listener
     * @param views    View objects
     * @return Animation listener
     */
    private static Animation.AnimationListener getGoneAnimationListener(final OnReturnAnimationFinished listener, final View... views) {
        return new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                for (View v : views) {
                    v.setVisibility(View.INVISIBLE);
                }
                if (listener != null) {
                    listener.onAnimationFinished();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
    }

    /**
     * Listens for 'show' animation type
     *
     * @param listener Listener
     * @param views    View objects
     * @return Animation listener
     */
    private static Animation.AnimationListener getShowAnimationListener(final OnReturnAnimationFinished listener, final View... views) {
        return new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                for (View view : views) {
                    view.setVisibility(View.VISIBLE);
                }
                if (listener != null) {
                    listener.onAnimationFinished();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
    }

    /**
     * Method to start animation
     *
     * @param animation Animation object
     * @param views     View objects
     */
    private static void startAnimations(Animation animation, View... views) {
        for (View view : views) {
            view.startAnimation(animation);
        }
    }

    interface OnReturnAnimationFinished {
        void onAnimationFinished();
    }
}
