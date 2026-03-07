package in.bharat.curd.model;

import java.util.Date;


public class UserInfoRequest {


    private String name;

    private String email;

    private String mobileNo;

    private String password;

    private Date createdAt = new Date();

    public Date getDate() {
        return createdAt;
    }
    public void setDate(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
