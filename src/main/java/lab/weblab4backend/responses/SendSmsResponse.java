package lab.weblab4backend.responses;

public class SendSmsResponse {
    private boolean status;
    private long resendAfter;
    private boolean isCorrectPhone;

    public SendSmsResponse() {}

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public long getResendAfter() {
        return resendAfter;
    }

    public void setResendAfter(long resendAfter) {
        this.resendAfter = resendAfter;
    }

    public boolean isCorrectPhone() {
        return isCorrectPhone;
    }

    public void setCorrectPhone(boolean correctPhone) {
        isCorrectPhone = correctPhone;
    }
}
