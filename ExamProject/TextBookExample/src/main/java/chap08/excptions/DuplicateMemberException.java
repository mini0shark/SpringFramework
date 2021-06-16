package chap08.excptions;

public class DuplicateMemberException extends RuntimeException{
	public DuplicateMemberException(String message) {
		super(message);		
	}
}
