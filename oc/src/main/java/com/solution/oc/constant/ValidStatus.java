package com.solution.oc.constant;

public enum ValidStatus {
    VALID(0,"valid"),
    INVALID(1,"invalid");

    private int status;
    private String des;

    private ValidStatus(int status, String des){
        this.status = status;
        this.des = des;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
