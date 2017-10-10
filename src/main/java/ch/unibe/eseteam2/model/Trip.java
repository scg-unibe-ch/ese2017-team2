package ch.unibe.eseteam2.model;

public class Trip {
	private String startAddress;
	private String endAddress;
	private float duration;

	private String animal;
	private int animalCount;

	public String getStartAddress() {
		return startAddress;
	}

	public void setStartAddress(String startAddress) {
		this.startAddress = startAddress;
	}

	public String getEndAddress() {
		return endAddress;
	}

	public void setEndAddress(String endAddress) {
		this.endAddress = endAddress;
	}

	public float getDuration() {
		return duration;
	}

	public void setDuration(float duration) {
		this.duration = duration;
	}

	public String getAnimal() {
		return animal;
	}

	public void setAnimal(String animal) {
		this.animal = animal;
	}

	public int getAnimalCount() {
		return animalCount;
	}

	public void setAnimalCount(int animalCount) {
		this.animalCount = animalCount;
	}

	@Override
	public String toString() {
		return "Trip [startAddress=" + startAddress + ", endAddress=" + endAddress + ", duration=" + duration + ", animal=" + animal + ", animalCount=" + animalCount + "]";
	}

}
