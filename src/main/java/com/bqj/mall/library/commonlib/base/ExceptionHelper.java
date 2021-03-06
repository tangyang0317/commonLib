package com.bqj.mall.library.commonlib.base;

import com.google.gson.JsonParseException;
import com.lzy.okgo.exception.HttpException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

/**
 * <pre>
 *     author : tangyang
 *     e-mail : ty766998873@163.com
 *     time   : 2020/06/10
 *     desc   :
 *     version: 2.6.0
 * </pre>
 */
public class ExceptionHelper {

	public static String handleException(Throwable e) {
		String error;
		if (e instanceof SocketTimeoutException) {
			//网络超时
			error = "网络连接异常";
		} else if (e instanceof ConnectException) {
			//均视为网络错误
			error = "网络连接异常";
		} else if (e instanceof JsonParseException
				|| e instanceof JSONException
				|| e instanceof ParseException) {
			//均视为解析错误
			error = "数据解析异常";
		} else if (e instanceof UnknownHostException) {
			error = "网络连接异常";
		} else if (e instanceof IllegalArgumentException) {
			error = "非法参数异常";
		} else if (e instanceof HttpException) {
			error = "网络连接异常";
		} else if (e instanceof NullPointerException) {
			error = "空指针异常";
		} else {
			error = "错误";
		}
		return error;
	}
}
