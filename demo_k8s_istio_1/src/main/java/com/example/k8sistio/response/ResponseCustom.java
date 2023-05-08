package com.example.k8sistio.response;

public class ResponseCustom {
    private String micro1;
    private String micro2;
    private String micro3;

    public ResponseCustom(String micro1, String micro2, String micro3) {
        this.micro1 = micro1;
        this.micro2 = micro2;
        this.micro3 = micro3;
    }

    public String getMicro1() {
        return micro1;
    }

    public void setMicro1(String micro1) {
        this.micro1 = micro1;
    }

    public String getMicro2() {
        return micro2;
    }

    public void setMicro2(String micro2) {
        this.micro2 = micro2;
    }

    public String getMicro3() {
        return micro3;
    }

    public void setMicro3(String micro3) {
        this.micro3 = micro3;
    }
}
