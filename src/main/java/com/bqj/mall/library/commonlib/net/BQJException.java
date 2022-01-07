package com.bqj.mall.library.commonlib.net;

/**
 * <pre>
 *     author : tangyang
 *     e-mail : ty766998873@163.com
 *     time   : 2020/12/30
 *     desc   :
 *     version: 2.6.0
 * </pre>
 */
public class BQJException extends RuntimeException {
	private int code;
	private String message;

	public BQJException(int code, String message) {
		super(message);
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
