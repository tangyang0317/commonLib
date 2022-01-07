package com.bqj.mall.library.commonlib.view.countdownview;

import android.content.Context;
import android.util.AttributeSet;

import java.util.Timer;
import java.util.TimerTask;

/**
 * <pre>
 *     author : tangyang
 *     e-mail : ty766998873@163.com
 *     time   : 2021/12/20
 *     desc   :
 *     version: 2.6.0
 * </pre>
 */
public class TyCountDownView extends CountdownView {

    public TyCountDownView(Context context) {
        super(context);
    }

    public TyCountDownView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TyCountDownView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private long time = 0;
    private boolean isStartingTask = false;
    private final Timer mTimer = new Timer();
    private final TimerTask task = new TimerTask() {
        @Override
        public void run() {
            time -= 1000;
        }
    };

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.start(time);
    }

    @Override
    public void start(long millisecond) {
        super.start(millisecond);
        time = millisecond;
        if (!isStartingTask) {
            mTimer.scheduleAtFixedRate(task, 0, 1000);
            isStartingTask = true;
        }
    }

    @Override
    public void updateShow(long ms) {
        super.updateShow(ms);
        time = ms;
    }

    public void finishTimer() {
        mTimer.cancel();
    }
}
