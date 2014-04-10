package myBooking;
// Room class with needed variables and methods
public class Room {

	private int roomNumber;
	private String capacity;
	
	public Room(){
		roomNumber = 0;
		capacity = "";
	}
	
	public Room(int roomNumber, String capacity){
		this.roomNumber= roomNumber;
		this.capacity = capacity;
	}
	public String changeCapacity(){
		return capacity;
		
	}


	public int getRoomNumber() {
		return roomNumber;
	}



}
