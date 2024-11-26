package net.heimeng.model;

/**
 * 登录验证信息
 *
 * @author InwardFlow
 */

public class LoginVO {

    /**
     * 用户认证令牌
     */
    private String token;

    /**
     * 用户 openId
     */
    private String openId;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    @Override
    public String toString() {
        return "LoginVO{" +
                "token='" + token + '\'' +
                ", openId='" + openId + '\'' +
                '}';
    }
}
