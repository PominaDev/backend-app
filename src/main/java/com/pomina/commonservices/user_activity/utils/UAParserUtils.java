package com.pomina.commonservices.user_activity.utils;

import ua_parser.Client;
import ua_parser.Parser;

public class UAParserUtils {

    private static final Parser parser = new Parser();

    public static String parse(String userAgent) {
        try {
            Client c = parser.parse(userAgent);
            String browser = c.userAgent.family + " " + c.userAgent.major;
            String os = c.os.family + " " + c.os.major;
            String device = c.device.family;
            return browser + " | " + os + " | " + device;
        } catch (Exception e) {
            return userAgent;
        }
    }
}
