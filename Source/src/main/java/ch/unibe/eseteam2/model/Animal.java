package ch.unibe.eseteam2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import ch.unibe.eseteam2.InputUtils;

@Entity
@Table(name = "animal")
public class Animal {

	@Id
	@Column(name = "name")
	private String name;

	@Column(name = "length")
	private int length;

	@Column(name = "width")
	private int width;

	public Animal() {

	}

	public Animal(String name, int length, int width) {
		setName(name);
		setLength(length);
		setWidth(width);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		InputUtils.checkNull(name, "name");
		InputUtils.checkStringRange(name, "name", 2, 100);
		this.name = name;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		if (length < 1) {
			throw new IllegalArgumentException("length has to be at least 1.");
		}
		this.length = length;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		if (width < 1) {
			throw new IllegalArgumentException("width has to be at least 1.");
		}
		this.width = width;
	}
}
