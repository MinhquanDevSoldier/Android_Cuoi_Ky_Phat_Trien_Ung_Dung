package com.example.appqlquanoc.doituong;

public class DMTP {
    private int iddmtp;
    private String tenDMTP;

    public DMTP() {
        super();
    }

    public DMTP(int iddmtp, String tenDMTP) {
        super();
        this.iddmtp = iddmtp;
        this.tenDMTP = tenDMTP;
    }

    public int getIddmtp() {
        return iddmtp;
    }

    public void setIddmtp(int iddmtp) {
        this.iddmtp = iddmtp;
    }

    public String getTenDMTP() {
        return tenDMTP;
    }

    public void setTenDMTP(String tenDMTP) {
        this.tenDMTP = tenDMTP;
    }
}
