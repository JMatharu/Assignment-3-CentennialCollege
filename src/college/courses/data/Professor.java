package college.courses.data;

import college.courses.exceptions.InvalidDataException;

public class Professor {
	private String firstName;
	private String lastName;
	private int profId;

	public Professor( int profId, String firstName, String lastName)
			throws InvalidDataException {
		setProfId(profId);
		setFirstName(firstName);
		setLastName(lastName);
	}

	private void setProfId( int profId) throws InvalidDataException {
		if ( profId < 1000 || profId > 1999) {
			throw new InvalidDataException("Proressor must have 4-digit ID starting 1ddd");
		}
		this.profId = profId;
	}
	
	public int getProfId () {
		return profId;
	}
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) throws InvalidDataException {
		if (firstName == null || firstName.isEmpty()) {
			throw new InvalidDataException("Proressor must have a first name");
		}
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) throws InvalidDataException {
		if (lastName == null || lastName.isEmpty()) {
			throw new InvalidDataException("Proressor must have a last name");
		}
		this.lastName = lastName;
	}
	public String toString() {
		return firstName + " " + lastName;
	}

}
