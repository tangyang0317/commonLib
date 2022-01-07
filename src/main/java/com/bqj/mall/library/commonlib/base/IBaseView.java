package com.bqj.mall.library.commonlib.base;

import android.content.Context;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;

import io.reactivex.annotations.NonNull;

/**
 * 项目名:    BaseFrame
 * 包  名:    com.zhong.baselib.base
 * 文件名:    IBaseView
 * 时  间:    2018/6/19 on 21:10
 * 描  述:    TODO
 *
 * @author: zjb
 */
public interface IBaseView {

	/**
	 * 绑定生命周期
	 *
	 * @param <T>
	 * @return
	 */
	<T> LifecycleTransformer<T> bindToLifecycle();

	/**
	 * 绑定指定生命周期
	 *
	 * @param event
	 * @param <T>
	 * @return
	 */
	<T> LifecycleTransformer<T> bindUntilEvent(@NonNull ActivityEvent event);

	/**
	 * 绑定生命周期
	 *
	 * @param event
	 * @param <T>
	 * @return
	 */
	<T> LifecycleTransformer<T> bindUntilEvent(@NonNull FragmentEvent event);

	/**
	 * 获取context
	 *
	 * @return
	 */
	Context getContext();
}
