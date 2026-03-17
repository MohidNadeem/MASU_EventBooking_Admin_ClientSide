package com.mohid.masu.admin.model;

public class EventRow {

    private String id;
    private String title;
    private String type;
    private String date;
    private String startTime;
    private String endTime;
    private String venueName;
    private String status;
    private String cost;
    private String maxParticipants;
    private String alumniReservedSlots;

    public EventRow(String id, String title, String type, String date, String startTime, String endTime,
                    String venueName, String status, String cost,
                    String maxParticipants, String alumniReservedSlots) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.venueName = venueName;
        this.status = status;
        this.cost = cost;
        this.maxParticipants = maxParticipants;
        this.alumniReservedSlots = alumniReservedSlots;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }
    
    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getVenueName() {
        return venueName;
    }

    public String getStatus() {
        return status;
    }

    public String getCost() {
        return cost;
    }

    public String getMaxParticipants() {
        return maxParticipants;
    }

    public String getAlumniReservedSlots() {
        return alumniReservedSlots;
    }
}