package com.entity;

public class BookingCreate {

    Customer customer;
    Booking booking;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    @Override
    public String toString() {
        return "BookingCreate{" +
                "customer=" + customer +
                ", booking=" + booking +
                '}';
    }

    public BookingCreate() {
        super();
    }

}
