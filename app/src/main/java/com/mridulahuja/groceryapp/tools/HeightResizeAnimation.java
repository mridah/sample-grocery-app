package com.mridulahuja.groceryapp.tools;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by mridul ahuja on 17/9/17.
 */

/*

usage:

HeightResizeAnimation resizeAnimation = new HeightResizeAnimation(
                    view,
                    targetHeight,
                    startHeight
            );
            resizeAnimation.setDuration(500);
            view.startAnimation(resizeAnimation);

*/


public class HeightResizeAnimation extends Animation {

    final int targetHeight;
    View view;
    int startHeight;

    public HeightResizeAnimation(View view, int targetHeight, int startHeight) {
        this.view = view;
        this.targetHeight = targetHeight;
        this.startHeight = startHeight;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        int newHeight = (int) (startHeight + targetHeight * interpolatedTime);
        //to support decent animation, change new heigt as Nico S. recommended in comments
        //int newHeight = (int) (startHeight+(targetHeight - startHeight) * interpolatedTime);
        view.getLayoutParams().height = newHeight;
        view.requestLayout();
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
    }

    @Override
    public boolean willChangeBounds() {
        return true;
    }


}
