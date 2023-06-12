package com.bjpowernode.pojo;

public class Record {
    private String recordId;
    private String recordBookname;
    private String recordBookisbn;
    private String recordBorrower;
    private String recordBorrowtime;
    private String recordRemandtime;

    public Record() {
    }

    public Record(String recordId, String recordBookname, String recordBookisbn, String recordBorrower, String recordBorrowtime, String recordRemandtime) {
        this.recordId = recordId;
        this.recordBookname = recordBookname;
        this.recordBookisbn = recordBookisbn;
        this.recordBorrower = recordBorrower;
        this.recordBorrowtime = recordBorrowtime;
        this.recordRemandtime = recordRemandtime;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getRecordBookname() {
        return recordBookname;
    }

    public void setRecordBookname(String recordBookname) {
        this.recordBookname = recordBookname;
    }

    public String getRecordBookisbn() {
        return recordBookisbn;
    }

    public void setRecordBookisbn(String recordBookisbn) {
        this.recordBookisbn = recordBookisbn;
    }

    public String getRecordBorrower() {
        return recordBorrower;
    }

    public void setRecordBorrower(String recordBorrower) {
        this.recordBorrower = recordBorrower;
    }

    public String getRecordBorrowtime() {
        return recordBorrowtime;
    }

    public void setRecordBorrowtime(String recordBorrowtime) {
        this.recordBorrowtime = recordBorrowtime;
    }

    public String getRecordRemandtime() {
        return recordRemandtime;
    }

    public void setRecordRemandtime(String recordRemandtime) {
        this.recordRemandtime = recordRemandtime;
    }

    @Override
    public String toString() {
        return "record{" +
                "recordId='" + recordId + '\'' +
                ", recordBookname='" + recordBookname + '\'' +
                ", recordBookisbn='" + recordBookisbn + '\'' +
                ", recordBorrower='" + recordBorrower + '\'' +
                ", recordBorrowtime='" + recordBorrowtime + '\'' +
                ", recordRemandtime='" + recordRemandtime + '\'' +
                '}';
    }
}
