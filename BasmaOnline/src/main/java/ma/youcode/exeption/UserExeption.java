package ma.youcode.exeption;

public class UserExeption extends RuntimeException {

	private static final long serialVersionUID = 6935467431721129576L;

	public UserExeption(String message) {
		super(message);
	}
}
