package com.baobang.piospa.utils;

/**
 * @author BaoBang
 * @Created Apr 25, 2018
 * 
 */
public interface RequestPath {

	public static final String STAFF_PATH = "/staff";
	public static final String STAFF_TITLE_PATH = STAFF_PATH + "/title";
	public static final String STAFF_DEPARTMENT_PATH = STAFF_PATH + "/departmentx`";

	// ------------------------PRODUCT-----------------------------

	public static final String PRODUCT_PATH = "/product";
	public static final String PRODUCT_GROUP_PATH = PRODUCT_PATH + "/group";
	public static final String PRODUCT_LABEL_PATH = PRODUCT_PATH + "/label";
	public static final String PRODUCT_ORIGIN_PATH = PRODUCT_PATH + "/origin";
	public static final String PRODUCT_UNIT_PATH = PRODUCT_PATH + "/unit";

	public static final String PRODUCT_ATTRIBUTE_PATH = PRODUCT_PATH + "/product-attribute";
	public static final String ATTRIBUTE_VALUE_PATH = PRODUCT_PATH + "/attributeValue-value";
	public static final String ATTRIBUTE_PATH = PRODUCT_PATH + "/attribute";

	// ------------------------CUSTOMER-------------------------------------

	public static final String CUSTOMER_PATH = "/customer";
	public static final String CUSTOMER_GROUP_PATH = CUSTOMER_PATH + "/group";
	public static final String CUSTOMER_SOURCE_PATH = CUSTOMER_PATH + "/source";

	// ------------------------ORDER-------------------------------------
	public static final String ORDER_PATH = "/order";
	public static final String ORDER_STATUS_PATH = ORDER_PATH + "/status";
	public static final String ORDER_PAYMENT_TYPE_PATH = ORDER_PATH + "/payment-type";
	public static final String ORDER_DELIVERY_TYPE_PATH = ORDER_PATH + "/delivery-type";
	public static final String ORDER_DELIVERY_STATUS_PATH = ORDER_PATH + "/delivery-status";
	public static final String ORDER_REASON_CANCEL_PATH = ORDER_PATH + "/reasion-cancel";
	public static final String ORDER_PRODUCT_PATH = ORDER_PATH + "/products";

	public static final String PROVINCE_PATH = "/province";
	public static final String DISTRICT_PATH = "/district";
	public static final String WARD_PATH = "/ward";

	// ------------------------SERVICE-------------------------------------

	public static final String SERVICE_PATH = "/service";
	public static final String SERVICE_GROUP_PATH = SERVICE_PATH + "/group";
	public static final String SERVICE_PACKAGE_PATH = SERVICE_PATH + "/package";
	public static final String SERVICE_PRICE_PATH = SERVICE_PATH + "/price";
	public static final String SERVICE_TIME_PATH = SERVICE_PATH + "/time";
	public static final String SERVICE_TYPE_PATH = SERVICE_PATH + "/type";
	
	// ------------------------BOOKING-------------------------------------
	public static final String BOOKING_PATH = "/booking";
	public static final String BOOKING_DETAIL_PATH = "/detail";

 	// ------------------------ROOM-------------------------------------
	public static final String ROOM_PATH = "/room";
}
