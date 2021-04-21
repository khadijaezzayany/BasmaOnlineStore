package ma.youcode.responses;

import java.util.Date;

public class ErrorMessage {
	private Date timestamp;
	private String messege;

	public ErrorMessage(Date timestamp, String messege) {
		super();
		this.timestamp = timestamp;
		this.messege = messege;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessege() {
		return messege;
	}

	public void setMessege(String messege) {
		this.messege = messege;
	}

}
