package type;

public class FileException extends RuntimeException {
	
	String msg;

	public FileException(String msg) {
		this.msg= msg;
	}
	
	public String getMessage(){return msg;}
}
