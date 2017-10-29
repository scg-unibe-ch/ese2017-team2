package ch.unibe.eseteam2.model;

public enum TripState {
	editing("editing"), assigned("assigned"), active("active"), expired("expired"), successful("successful"), unsuccessful("unsuccessful");

	private String name;

	private TripState(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
