package chap03.exceptions;

public class DuplicateMemberException extends RuntimeException{
	public DuplicateMemberException(String message) {
		super(message);		
	}
}
