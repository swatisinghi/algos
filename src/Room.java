
public class Room {
	
	int id;
	String name;
	int capacity;
	
	Room(int id, String name, int cap) {
		this.id = id;
		this.name = name;
		this.capacity = cap;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	
	
}
