package com.easytransac.easytransac_sdk;

/**
 * This class contains all the usable parameters for EasyTransac SDK
 * To use them, refer to the sample app.
 */
public class EasyTransacSDK
{
	public final static String EASYTRANSAC_PACKAGE_NAME = "com.movidone.easytransac";
	public final static String EASYTRANSAC_CLASS_NAME = "com.movidone.easytransac.MainActivity";

	/**
	 *  String extra. Your application api key.
	 *  <a href="https://www.easytransac.com/dashboard/application/all">Can be found here</a>
	 */
	public final static String EXTRA_API_KEY = "EXTRA_API_KEY";

	/**
	 * Double extra. Transaction's amount in double
	 * E.g : 20.10 for 20,10€
	 */
	public final static String EXTRA_AMOUNT = "EXTRA_AMOUNT";

	/**
	 * Boolean extra. Enable multiple payment
	 * Allow to slice a transaction into multiple payments
	 * E.g for an amount of 300€; by default, the deposit will be 100€;
	 * the next payment will be held 30 days later;
	 * the last payment of 100€ will be held 60 days later
	 */
	public final static String EXTRA_MULTIPLE_PAYMENT = "EXTRA_MULTIPLE_PAYMENT";

	/**
	 * Double extra. Down payment for a multiple payment
	 * 33% of the total by default, if payment in 3 times for example or amount of the first payment
	 */
	public final static String EXTRA_DOWN_PAYMENT = "EXTRA_DOWN_PAYMENT";

	/**
	 * Int extra. Total number of payment to be done for a multiple payment
	 * Down payment included (3 by default)
	 */
	public final static String EXTRA_MULTIPLE_PAYMENT_REPEAT = "EXTRA_MULTIPLE_PAYMENT_REPEAT";

	/**
	 * Boolean extra. Enable demo mode (replace detected credit card by a test card)
	 * You have to use a testing API key too
	 * Default is false
	 */
	public final static String EXTRA_DEMO = "EXTRA_DEMO";

	/**
	 * String extra. Optional. Detection method to use
	 * Default FLASH.
	 * Take care to check the availability of the the detection method on the device
	 * Methods available : FLASH, NFC, MANUAL
	 */
	public final static String EXTRA_DETECTION_METHOD = "EXTRA_DETECTION_METHOD";

	/**
	 * Boolean extra. Optional. Enable 3DS.
	 * Default is true
	 */
	public final static String EXTRA_USE_3DS = "EXTRA_USE_3DS";

	/**
	 * String extra. Optional. Your unique transaction identifier (or order number)
	 *
	 */
	public final static String EXTRA_ORDER_ID = "EXTRA_ORDER_ID";

	/**
	 * String extra. Optional. Transaction description (use \n to make a line break)
	 *
	 */
	public final static String EXTRA_ORDER_DESCRIPTION = "EXTRA_ORDER_DESCRIPTION";

	/**
	 * String extra. Optional. Your customer's lastname
	 *
	 */
	public final static String EXTRA_CUSTOMER_FIRSTNAME = "EXTRA_CUSTOMER_FIRSTNAME";

	/**
	 * String extra. Optional. Your customer's lastname
	 *
	 */
	public final static String EXTRA_CUSTOMER_LASTNAME = "EXTRA_CUSTOMER_LASTNAME";

	/**
	 * String extra. Optional. Your customer's email
	 *
	 */
	public final static String EXTRA_CUSTOMER_EMAIL = "EXTRA_CUSTOMER_EMAIL";

	/**
	 * String extra. Optional. Your customer's calling code
	 * 2 digits ISO
	 * E.g : 33
	 */
	public final static String EXTRA_CUSTOMER_CALLING_CODE = "EXTRA_CUSTOMER_CALLING_CODE";

	/**
	 * String extra. Optional. Your customer's phone
	 *
	 */
	public final static String EXTRA_CUSTOMER_PHONE = "EXTRA_CUSTOMER_PHONE";

	/**
	 * Boolean extra. Optional. Used to disable CVV.
	 * Default is true
	 * Warning: this option is depending your account configuration
	 * Contact us to get more information about this option
	 */
	public final static String EXTRA_USE_CVV = "EXTRA_USE_CVV";

	// Payment response keys
	public final static String RESULT_AMOUNT = "RESULT_AMOUNT";
	public final static String RESULT_TID = "RESULT_TID";
	public static final String RESULT_MESSAGE = "RESULT_MESSAGE";
	public static final String RESULT_STATUS = "RESULT_STATUS";
	public static final String RESULT_DATE = "RESULT_DATE";

	// Failure custom result codes
	public static final int RESULT_CODE_NFC_ERROR = -102;
	public static final int RESULT_CODE_MISSING_PARAMETERS = -103;
	public static final int RESULT_CODE_WRONG_PARAMETERS = -104;
	public static final int RESULT_CODE_UNEXPECTED_ERROR = -199;
}
