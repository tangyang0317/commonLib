package com.bqj.mall.library.commonlib.base;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.baidu.mobstat.StatService;
import com.bqj.mall.library.commonlib.view.LoadingDialog;
import com.lzy.okgo.OkGo;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 项目名:    BaseFrame
 * 包  名:    com.zhong.baselib.base
 * 文件名:    BaseActivity
 * 时  间:    2018/6/19 on 21:04
 * 描  述:    TODO
 *
 * @author: zjb
 */
public abstract class LibBaseActivity<P extends BasePresenter> extends RxAppCompatActivity implements IBaseView {
    protected P presenter;
    public Context mContext;
    private List<WeakReference<Context>> mWeakReferenceList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        presenter = initPresenter();
    }

    @Override
    protected void onPause() {
        super.onPause();
        /**MainPageActivity不加入统计**/
        if (!getClass().getSimpleName().equals("MainPageActivity")) {
            StatService.onPause(this);
        }
    }

    @Override
    protected void onResume() {
        //设置为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onResume();
        /**MainPageActivity不加入统计**/
        if (!getClass().getSimpleName().equals("MainPageActivity")) {
            StatService.onResume(this);
        }
    }


    @Override
    protected void onDestroy() {
        if (presenter != null) {
            presenter.detach();
        }
        mWeakReferenceList.clear();
        mWeakReferenceList = null;
        OkGo.getInstance().cancelTag(this);
        super.onDestroy();
    }

    public abstract P initPresenter();


    /**
     * 1秒内重复点击
     *
     * @param <T>
     * @return
     */
    protected <T> ObservableTransformer<T, T> reClick1() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream
                        .compose(LibBaseActivity.this.<T>bindToLifecycle())
                        .throttleFirst(1, TimeUnit.SECONDS)
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 1秒内重复点击
     *
     * @param <T>
     * @return
     */
    protected <T> ObservableTransformer<T, T> reClick2() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream
                        .compose(LibBaseActivity.this.<T>bindToLifecycle())
                        .throttleFirst(2, TimeUnit.SECONDS)
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    @Override
    public Context getContext() {
        if (mWeakReferenceList.size() == 0) {
            WeakReference<Context> weakReference = new WeakReference<>(mContext);
            mWeakReferenceList.add(weakReference);
            return weakReference.get();
        } else {
            WeakReference<Context> weakReference = mWeakReferenceList.get(0);
            return weakReference.get();
        }
    }

    @Override
    public <T> LifecycleTransformer<T> bindUntilEvent(FragmentEvent event) {
        return null;
    }
}
