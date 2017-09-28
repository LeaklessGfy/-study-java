package fr.umlv.urm.exception;

/**
 * 
 * @author vrasquie
 * @version 1
 */
public final class URMException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * URMException constructor
	 */
	public URMException() {
		super();
	}
	
	/**
	 * URMException constructor
	 * 
	 * @param string
	 */
	public URMException(String string) {
		super(string);
	}
}
