package ch.unibe.eseteam2.form;

import org.hibernate.validator.constraints.Length;

public class ConfirmationForm {

	@Length(max = 1000, message = "Can be at most 1000 characters long.")
	private String feedback;

	private boolean success;

	public ConfirmationForm() {
		this.success = true;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	@Override
	public String toString() {
		return "ConfirmationForm [feedback=" + feedback + ", success=" + success + "]";
	}
}
