package com.javalitterboy.ibk.pojo;

public class Result {
    private int state;
    private String info;
    private Object data;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Result(int state, String info, Object data){
        this.state = state;
        this.info = info;
        this.data = data;
    }
}
