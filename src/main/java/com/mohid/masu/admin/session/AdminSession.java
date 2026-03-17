package com.mohid.masu.admin.session;

public class AdminSession {

    private static String adminId;
    private static String username;
    private static String fullName;

    public static String getAdminId() {
        return adminId;
    }

    public static void setAdminId(String adminId) {
        AdminSession.adminId = adminId;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        AdminSession.username = username;
    }

    public static String getFullName() {
        return fullName;
    }

    public static void setFullName(String fullName) {
        AdminSession.fullName = fullName;
    }

    public static void clear() {
        adminId = null;
        username = null;
        fullName = null;
    }
}