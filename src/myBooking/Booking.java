package myBooking;

import java.util.Date;

public class Booking {
	//Booking class with variables and methods needed for a booking
	private int bookingNum;
	private String name;
	private Date date;
	private int time;
	
	public Booking(){
		setBookingNum(0);
		setName("");
		setDate(null);
		setTime(0);
	}
	public Booking(int bookingNum, String bookedBy,int time, Date date ){
		this.setBookingNum(bookingNum);
		this.setName(bookedBy);
		this.setDate(date);
		this.setTime(time);
		
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getBookingNum() {
		return bookingNum;
	}
	public void setBookingNum(int bookingNum) {
		this.bookingNum = bookingNum;
	}
	public void AddBooking() throws Exception {
		try {
			MysqlAction newMysqlAction = new MysqlAction();	
			newMysqlAction.addBooking(this.name, this.date, this.time);
		
		} catch (Exception e) {
		      throw e;
	}

	}

	

	


	}
	

	
