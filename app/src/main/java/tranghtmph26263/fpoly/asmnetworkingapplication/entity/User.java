package tranghtmph26263.fpoly.asmnetworkingapplication.entity;

public class User {
    private String fullname, email, passwork, username, _id;
    private int __v;

    public User(String fullname, String email, String passwork, String username, String _id) {
        this.fullname = fullname;
        this.email = email;
        this.passwork = passwork;
        this.username = username;
        this._id = _id;
    }

    public User(String passwork, String username) {
        this.passwork = passwork;
        this.username = username;
    }

    public User() {
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswork() {
        return passwork;
    }

    public void setPasswork(String passwork) {
        this.passwork = passwork;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String get_id() {
        return _id;
    }

}
