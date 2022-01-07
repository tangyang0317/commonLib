package com.bqj.mall.library.commonlib.net;

import android.content.Context;

import com.bqj.mall.library.commonlib.view.LoadingDialog;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.request.base.Request;

import java.io.File;

/**
 * <pre>
 *     author : tangyang
 *     e-mail : ty766998873@163.com
 *     time   : 2021/04/17
 *     desc   :
 *     version: 2.6.0
 * </pre>
 */
public abstract class DownLoadFileCallback extends FileCallback {

	private LoadingDialog dialog;
	private Context mContext;

	private void initDialog(Context activity) {
		dialog = new LoadingDialog(activity);
		dialog.setCanceledOnTouchOutside(false);
		dialog.setLoadingText("正在下载视频");
	}

	public DownLoadFileCallback(Context context, String dir, String path) {
		super(dir, path);
		this.mContext = context;
		initDialog(mContext);
	}

	@Override
	public void onStart(Request<File, ? extends Request> request) {
		super.onStart(request);
		if (dialog != null && !dialog.isShowing()) {
			dialog.show();
		}
	}

	@Override
	public void onFinish() {
		super.onFinish();
		if (dialog != null && dialog.isShowing()) {
			dialog.dismiss();
		}
	}
}
