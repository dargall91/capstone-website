package com.capstone.wea.Util;

/**
 * Utility functions that may be used by various classes across the project
 */
public class Util {
    /**
     * Checks if a string is null or blank
     * @param value the string to check
     * @return true if the value is null or blank
     */
    public static boolean isNullOrBlank(String value) {
        return (value == null || value.isBlank());
    }
}
