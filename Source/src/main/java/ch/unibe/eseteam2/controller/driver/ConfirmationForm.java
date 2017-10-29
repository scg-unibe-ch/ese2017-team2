package ch.unibe.eseteam2.controller.driver;

public class ConfirmationForm {
	
	private String feedback;
	
	private boolean success;
	
	
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
}
