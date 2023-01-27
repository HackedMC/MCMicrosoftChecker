package cn.hackedmc.tender.utils.accound;

public class MCAccount {

    private final String email, password;
    private int failCount;

    private String name;
    private String accessToken;
    private String uuid;

    public MCAccount(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void incFailCount() {
        setFailCount(getFailCount() + 1);
    }

    public int getFailCount() {
        return failCount;
    }

    public void setFailCount(int failCount) {
        this.failCount = failCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return getEmail() + ":" + getPassword() + (getName() != null ? ":" + getName() : "");
    }
}