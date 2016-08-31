package com.ylmall.at.exception;

/**
 * @author  作者 E-mail: 
 * @date 创建时间：2016年8月12日 上午9:04:17
 * @version 1.0
 * @parameter 
 * @since 
 * @return 
 */
public class AtException extends Exception{

	private static final long serialVersionUID = 1L;

	/** errorCode */
    private String errorCode = null;
    
    /** rootCause */
    private Throwable rootCause = null;
    
    
    public AtException() {
        super();
    }

 
    public AtException(String errorCode) throws AtException {
        super(errorCode);
    }


    public AtException(String errorCode, Throwable rootCause) {
        super(errorCode, rootCause);
    }


	public String getErrorCode() {
		return errorCode;
	}


	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}


	public Throwable getRootCause() {
		return rootCause;
	}


	public void setRootCause(Throwable rootCause) {
		this.rootCause = rootCause;
	}

  
	
	
}
