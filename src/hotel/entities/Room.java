package hotel.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hotel.credit.CreditCard;
import hotel.utils.IOUtils;

public class Room {
	
	private enum State {READY, OCCUPIED}
	
	int id;
	RoomType roomType;
	List<Booking> bookings;
	State state;

	
	public Room(int id, RoomType roomType) {
		this.id = id;
		this.roomType = roomType;
		bookings = new ArrayList<>();
		state = State.READY;
	}
	

	public String toString() {
		return String.format("Room : %d, %s", id, roomType);
	}


	public int getId() {
		return id;
	}
	
	public String getDescription() {
		return roomType.getDescription();
	}
	
	
	public RoomType getType() {
		return roomType;
	}
	
	public boolean isAvailable(Date arrivalDate, int stayLength) {
		IOUtils.trace("Room: isAvailable");
		for (Booking b : bookings) {
			if (b.doTimesConflict(arrivalDate, stayLength)) {
				return false;
			}
		}
		return true;
	}
	
	public boolean isReady() {
		return state == State.READY;
	}
	
	public boolean isOccupied() {
		return state == state.OCCUPIED;
	}


	public Booking book(Guest guest, Date arrivalDate, int stayLength, int numberOfOccupants, CreditCard creditCard) {
		// TODO Auto-generated method stub
		return null;		
	}


	public void checkin() {
		if(state != State.READY){
			throw new RuntimeException("Cannot checkin a room that is not Ready");
		}
		state = state.OCCUPIED;
	}


	public void checkout(Booking booking) {
		if(state != State.OCCUPIED){
			throw new RuntimeException("Cannot checkin to a room that is not OCCUPIED");
		}
		if(!bookings.contains(booking)) {
			throw new RuntimeException("Cannot checkin to a room that is not related to the booking");
		}
		bookings.remove(booking);
		state = State.READY;
	}


}
