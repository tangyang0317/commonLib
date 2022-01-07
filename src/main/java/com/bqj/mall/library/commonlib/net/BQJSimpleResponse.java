package com.bqj.mall.library.commonlib.net;

import java.io.Serializable;

/**
 * <pre>
 *     author : tangyang
 *     e-mail : ty766998873@163.com
 *     time   : 2020/06/03
 *     desc   :
 *     version: 2.6.0
 * </pre>
 */
public class BQJSimpleResponse implements Serializable {

	private int code;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private String message;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public BQJResponse toBQJResponse() {
		BQJResponse bqjResponse = new BQJResponse();
		bqjResponse.setCode(code);
		bqjResponse.setMessage(message);
		return bqjResponse;
	}


}
