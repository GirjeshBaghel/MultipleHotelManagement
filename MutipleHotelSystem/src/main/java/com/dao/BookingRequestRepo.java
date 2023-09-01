package com.dao;

import com.entity.BookingRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRequestRepo  extends JpaRepository<BookingRequest,Long> {

    BookingRequest findByBookingRequestId(Long bookingId);
}
