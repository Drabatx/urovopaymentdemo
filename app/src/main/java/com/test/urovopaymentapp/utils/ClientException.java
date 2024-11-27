package com.test.urovopaymentapp.utils;
/**Customized exception, used to save error code
 *
 */
public class ClientException extends Exception{
	private static final long serialVersionUID = -2155382375819767054L;

	private String errorCode;

	public ClientException(String errorCode) {
		super(errorCode);
		this.errorCode = errorCode;
	}

	public ClientException(Throwable cause, String errorCode) {
		super(cause);
		this.errorCode = errorCode;
	}

	/**Get exception code
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}
}
