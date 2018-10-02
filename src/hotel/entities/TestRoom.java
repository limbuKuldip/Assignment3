package hotel.entities;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import hotel.entities.Booking;
import hotel.entities.Room;
import hotel.entities.RoomType;

import static org.mockito.Mockito.*;

import java.util.ArrayList;

//import org.junit.Test;

@ExtendWith(MockitoExtension.class)

public class TestRoom {

	@Mock Booking booking;
	@Spy ArrayList<Booking> bookings;
	
	int roomId = 1;
	
	RoomType roomType = RoomType.SINGLE;
	@InjectMocks Room room = new Room(roomId, roomType);
	
	@BeforeEach
	void setUp() throws Exception{
		
	}
	
	@AfterEach
	void tearDown() throws Exception{
		
	}
	
	@Test
	void testCheckingWhenReady() {
		// arrange
		// act
		room.checkin();
		// assert
		assertTrue(room.isOccupied());
	}
	
	@Test
	void testCheckinWhenOccupied(){
		room.checkin();
		assertTrue(room.isOccupied());
		
		Executable e = () -> room.checkin();
		Throwable t = assertThrows(RuntimeException.class, e);
		assertEquals("Cannot checkin a room that is not Ready", t.getMessage());
	}
	
	@Test
	void testCheckoutWhenOccupied(){
		bookings.add(booking);
		room.checkin();
		assertEquals(1, bookings.size());
		assertTrue(room.isOccupied());
		room.checkout(booking);
		
		verify(bookings).remove(booking);
		assertTrue(room.isReady());
		assertEquals(0, bookings.size());
	}
}
