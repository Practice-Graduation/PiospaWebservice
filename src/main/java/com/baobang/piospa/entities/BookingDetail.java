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
public class BookingDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="booking_detail_id")
	private int bookingDetailId;

	//bi-directional many-to-one association to Booking
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="booking_id")
	private Booking booking;

	//bi-directional many-to-one association to ServicePrice
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="service_price_id")
	private ServicePrice servicePrice;

	public BookingDetail() {
	}

	public int getBookingDetailId() {
		return this.bookingDetailId;
	}

	public void setBookingDetailId(int bookingDetailId) {
		this.bookingDetailId = bookingDetailId;
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