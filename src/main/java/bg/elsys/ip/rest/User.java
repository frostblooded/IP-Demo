package bg.elsys.ip.rest;

public class User {
    private String email;
    private String accessToken;

    public User(String email, String accessToken) {
        this.email = email;
        this.accessToken = accessToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public String toString() {
        return "Email: " + getEmail() + " Access token: " + getAccessToken();
    }
}
