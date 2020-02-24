package cn.net.sybt.springboot.bean;

public class Admin {
    private String username;
    private String password;
    private String identifyCode;
    private String name;
    private String type;

    public Admin() {
    }

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdentifyCode() {
        return identifyCode;
    }

    public void setIdentifyCode(String identifyCode) {
        this.identifyCode = identifyCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Admin(String username, String password, String identifyCode, String name, String type) {
        this.username = username;
        this.password = password;
        this.identifyCode = identifyCode;
        this.name = name;
        this.type = type;
    }
}
