package com.bqj.mall.library.commonlib.base;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.baidu.mobstat.StatService;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.support.RxFragment;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/*
 * 项目名:    BaseFrame
 * 包名       com.zhong.baselib.base
 * 文件名:    BaseFragment
 * 创建者:    唐洋
 * 创建时间:  2018/6/20 on 11:58
 * 描述:     TODO
 */
public abstract class BaseFragment<P extends BasePresenter> extends RxFragment implements IBaseView {
    protected P presenter;
    private boolean isViewCreate = false;//view是否创建
    private boolean isViewVisible = false;//view是否可见
    public Context mContext;
    private boolean isFirst = true;//是否第一次加载
    private List<WeakReference<Context>> mWeakReferenceList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = initPresenter();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewCreate = true;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
        StatService.onPageStart(context, this.getClass().getCanonicalName());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        StatService.onPageEnd(getActivity(), this.getClass().getCanonicalName());
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isViewVisible = isVisibleToUser;
        if (isVisibleToUser && isViewCreate) {
            visibleToUser();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isViewVisible) {
            visibleToUser();
        }
    }

    /**
     * viewpager适用
     * 懒加载
     * 让用户可见
     * 第一次加载
     */
    protected void firstLoad() {

    }

    /**
     * viewpager适用
     * 懒加载
     * 让用户可见
     */
    protected void visibleToUser() {
        if (isFirst) {
            firstLoad();
            isFirst = false;
        }
    }


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
                        .compose(BaseFragment.this.<T>bindToLifecycle())
                        .throttleFirst(1, TimeUnit.SECONDS)
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 2秒内重复点击
     *
     * @param <T>
     * @return
     */
    protected <T> ObservableTransformer<T, T> reClick2() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream
                        .compose(BaseFragment.this.<T>bindToLifecycle())
                        .throttleFirst(2, TimeUnit.SECONDS)
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    @Override
    public void onDestroyView() {
        if (presenter != null) {
            presenter.detach();
        }
        isViewCreate = false;
        super.onDestroyView();
    }


    public abstract P initPresenter();

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
    public <T> LifecycleTransformer<T> bindUntilEvent(ActivityEvent event) {
        return null;
    }
}

