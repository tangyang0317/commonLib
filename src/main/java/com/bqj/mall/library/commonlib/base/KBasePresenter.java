package com.bqj.mall.library.commonlib.base;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/*
 * 项目名:    ThreeCash
 * 包名       com.dfjt.cash.app.base
 * 文件名:    KBasePresenter
 * 创建者:    唐洋
 * 创建时间:  2018/6/21 on 18:06
 * 描述:     TODO
 */
public abstract class KBasePresenter<V extends IKBaseView> extends BasePresenter<V> {


    public KBasePresenter(V view) {
        super(view);
    }

    /**
     * rx切换到主线程
     *
     * @param <T>
     * @return
     */
    public <T> ObservableTransformer<T, T> toMainThread() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

}
