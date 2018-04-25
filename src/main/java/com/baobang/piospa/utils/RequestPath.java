package com.baobang.piospa.utils;

/**
 * @author BaoBang
 * @Created Apr 25, 2018
 * 
 */
public interface RequestPath {

	public static final String USER_PATH = "/user";

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
	public static final String CUSTOMER_GROUP_PATH = CUSTOMER_PATH +  "/group";
	public static final String CUSTOMER_SOURCE_PATH = CUSTOMER_PATH + "/source";

	public static final String PROVINCE_PATH = "/province";
	public static final String DISTRICT_PATH = "/district";
	public static final String WARD_PATH = "/ward";
}
