package com.bqj.mall.library.commonlib.base;

import java.lang.ref.WeakReference;

/**
 * 项目名:    BaseFrame
 * 包  名:    com.zhong.baselib.base
 * 文件名:    BasePresenter
 * 时  间:    2018/6/19 on 21:10
 * 描  述:    TODO
 *
 * @author: zjb
 */
public abstract class BasePresenter<V extends IBaseView> {

	protected V view;
	private WeakReference<V> vWeakReference;

	public BasePresenter(V view) {
		vWeakReference = new WeakReference<V>(view);
		this.view = vWeakReference.get();
		init();
	}

	/**
	 * 解绑
	 */
	public void detach() {
		view = null;
		vWeakReference.clear();
		vWeakReference = null;
	}

	/**
	 * 用于初始化
	 */
	protected abstract void init();
}
