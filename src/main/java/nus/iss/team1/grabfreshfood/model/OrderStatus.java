package nus.iss.team1.grabfreshfood.model;

import java.math.BigDecimal;

public class OrderStatus {
    //for order status
    public static final String PROCESSING = "Processing";
    public static final String SHIPPED = "Shipped";
    public static final String DELIVERED = "Delivered";
    public static final String TOPAY = "Pending Payment";
    public static final String CANCELED = "Canceled";

    //for payment method
    public static final String CREDITCARD = "Credit Card";
    public static final String NOTPAY = "Not Pay";

    //for service fee
    public static final BigDecimal SERVICEFEE = BigDecimal.valueOf(3.60);
}
