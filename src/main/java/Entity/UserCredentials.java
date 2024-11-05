package Entity;

public class UserCredentials {
    private String userName;
    private String password;

    // 构造方法
    public UserCredentials(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    // Getter 和 Setter 方法
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
