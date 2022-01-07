package com.bqj.mall.library.commonlib.base;

/*
 * 项目名:    ThreeCash
 * 包名       com.dfjt.cash.app
 * 文件名:    IKBaseView
 * 创建者:    唐洋
 * 创建时间:  2018/6/21 on 17:54
 * 描述:     TODO
 */
public interface IKBaseView extends IBaseView {
	void showLoadingDialog(String msg);

	void dismissLoadingDialog();
}
