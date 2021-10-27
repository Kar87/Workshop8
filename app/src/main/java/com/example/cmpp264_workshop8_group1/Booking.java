package com.example.cmpp264_workshop8_group1;

public class Booking {
    private Integer BookingId;
    private String BookingDate;
    private String BookingNo;
    private String TravelerCount;
    private Integer CustomerId;
    private String TripTypeId;
    private Integer PackageId;

    public Booking(Integer bookingId, String bookingDate, String bookingNo, String travelerCount, Integer customerId, String tripTypeId, Integer packageId) {
        BookingId = bookingId;
        BookingDate = bookingDate;
        BookingNo = bookingNo;
        TravelerCount = travelerCount;
        CustomerId = customerId;
        TripTypeId = tripTypeId;
        PackageId = packageId;
    }

    public Integer getBookingId() {
        return BookingId;
    }

    public void setBookingId(Integer bookingId) {
        BookingId = bookingId;
    }

    public String getBookingDate() {
        return BookingDate;
    }

    public void setBookingDate(String bookingDate) {
        BookingDate = bookingDate;
    }

    public String getBookingNo() {
        return BookingNo;
    }

    public void setBookingNo(String bookingNo) {
        BookingNo = bookingNo;
    }

    public String getTravelerCount() {
        return TravelerCount;
    }

    public void setTravelerCount(String travelerCount) {
        TravelerCount = travelerCount;
    }

    public Integer getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(Integer customerId) {
        CustomerId = customerId;
    }

    public String getTripTypeId() {
        return TripTypeId;
    }

    public void setTripTypeId(String tripTypeId) {
        TripTypeId = tripTypeId;
    }

    public Integer getPackageId() {
        return PackageId;
    }

    public void setPackageId(Integer packageId) {
        PackageId = packageId;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "BookingId=" + BookingId +
                ", BookingDate='" + BookingDate + '\'' +
                ", BookingNo=" + BookingNo +
                ", TravelerCount=" + TravelerCount +
                ", CustomerId=" + CustomerId +
                ", TripTypeId='" + TripTypeId + '\'' +
                ", PackageId=" + PackageId +
                '}';
    }
}
