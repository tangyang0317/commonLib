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
public class BQJResponse<T> implements Serializable {

	private int status;
	private String message;
	private int code;
	private T data;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "LzyResponse{\n" +//
				"\tcode=" + code + "\n" +//
				"\tmsg='" + message + "\'\n" +//
				"\tstatus='" + status + "\'\n" +//
				"\tdata=" + data + "\n" +//
				'}';
	}
}
