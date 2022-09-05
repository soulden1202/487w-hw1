package com.example.staffs;

public class studentTimeStamp {
    private final String index;
    private final String studentId;
    private final String timeStamp;

    public studentTimeStamp(String index, String studentId, String timeStamp) {

        this.index = index;
        this.studentId = studentId;
        this.timeStamp = timeStamp;
    }

    public String getIndex() {
        return index;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }
}
