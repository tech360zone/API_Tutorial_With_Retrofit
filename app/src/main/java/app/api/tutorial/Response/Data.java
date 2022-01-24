package app.api.tutorial.Response;

import app.api.tutorial.Model.User;

public class Data {

    private User user;
    private String message;
    private int code;

    public Data(User user, String message, int code) {
        this.user = user;
        this.message = message;
        this.code = code;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
