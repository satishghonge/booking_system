package com.demo.bookingsystem.utilities;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @author Satish Ghonge
 *
 */
public class CommonUtils {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CommonUtils.class);

	public static boolean isNull(Object object) {
		return (object == null) ? Boolean.TRUE : Boolean.FALSE;
	}
	
	public static String getUUID() {
        UUID idOne = UUID.randomUUID();
        Long date = System.currentTimeMillis();
        String unquieId = idOne.toString() + date;
        return unquieId;
    }
	
	public static boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }

    public static boolean isNotNullOrEmpty(String value) {
        return !isNullOrEmpty(value);
    }
    
    public static boolean isEmptyString(String stringToCheck) {
		if (stringToCheck == null || stringToCheck.trim().length() <= 0)
			return true;
		
		return false;
	}

}
