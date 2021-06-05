package com.s1dmlgus.instagram02.util;

public class Script {

    public static String back(String msg) {

        return "<script>" +
                "alert('" + msg + "');" +
                "history.back();" +
                "</script>";

    }
}
