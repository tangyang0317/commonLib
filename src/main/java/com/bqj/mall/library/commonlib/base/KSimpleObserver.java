package com.bqj.mall.library.commonlib.base;

import com.orhanobut.logger.Logger;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/*
 * 项目名:    ThreeCash
 * 包名       com.dfjt.cash.app.base
 * 文件名:    KObserver
 * 创建者:    唐洋
 * 创建时间:  2018/6/22 on 14:05
 * 描述:     TODO
 */
public abstract class KSimpleObserver<T> implements Observer<T> {


    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        success(t);
    }

    @Override
    public void onError(Throwable e) {
        String exception = ExceptionHelper.handleException(e);
        Logger.e(exception);
    }

    @Override
    public void onComplete() {

    }

    protected abstract void success(T t);

}
