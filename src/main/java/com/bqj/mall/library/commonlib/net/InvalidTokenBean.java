package com.bqj.mall.library.commonlib.net;

import java.io.Serializable;

/**
 * <pre>
 *     author : tangyang
 *     e-mail : ty766998873@163.com
 *     time   : 2020/12/30
 *     desc   :
 *     version: 2.6.0
 * </pre>
 */
public class InvalidTokenBean implements Serializable {
	private String error_description;
	private String error;

	public String getError_description() {
		return error_description;
	}

	public void setError_description(String error_description) {
		this.error_description = error_description;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
