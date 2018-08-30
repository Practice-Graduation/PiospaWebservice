package com.baobang.piospa.entities;

import java.io.Serializable;
import javax.persistence.*;



/**
 * The persistent class for the booking_detail database table.
 * 
 */
@Entity
@Table(name="booking_detail")
@NamedQuery(name="BookingDetail.findAll", query="SELECT b FROM BookingDetail b")
@NamedNativeQueries({
	@NamedNativeQuery(
			name = "getBookingDetailById",
			query = "SELECT * FROM booking_detail WHERE date_booking = ?1", resultClass=BookingDetail.class)
})
public class BookingDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	@Column(name="booking_detail_id")
	private int bookingDetailId;

	@Column(name="date_booking")
	private String dateBooking;

	@Column(name="time_start")
	private String timeStart;
	
	private int number;
	
	@Column(name="served_status")
	private int servedStatus = 0;


	//bi-directional many-to-one association to Booking
	@ManyToOne
	@JoinColumn(name="booking_id")
	private Booking booking;

	//bi-directional many-to-one association to ServicePrice
	@ManyToOne
	@JoinColumn(name="service_price_id")
	private ServicePrice servicePrice;
	
	@ManyToOne
	@JoinColumn(name="room_id")
	private Room room;

	public BookingDetail() {
	}

	public Room getRoom() {
		return this.room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public int getServedStatus() {
		return servedStatus;
	}



	public void setServedStatus(int servedStatus) {
		this.servedStatus = servedStatus;
	}



	public int getNumber() {
		return number;
	}



	public void setNumber(int number) {
		this.number = number;
	}



	public int getBookingDetailId() {
		return this.bookingDetailId;
	}

	public void setBookingDetailId(int bookingDetailId) {
		this.bookingDetailId = bookingDetailId;
	}

	public String getDateBooking() {
		return this.dateBooking;
	}

	public void setDateBooking(String dateBooking) {
		this.dateBooking = dateBooking;
	}

	public String getTimeStart() {
		return this.timeStart;
	}

	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}

	public Booking getBooking() {
		return this.booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public ServicePrice getServicePrice() {
		return this.servicePrice;
	}

	public void setServicePrice(ServicePrice servicePrice) {
		this.servicePrice = servicePrice;
	}

}