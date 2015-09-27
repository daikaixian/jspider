package org.daikaixian.jspider.models;

/**
 * Created by kaishui on 15-9-10.
 */
public class User {

    private int id;

    private String username;

    private String pswd;

    private String lastPus;

    private String lastVersion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public String getLastPus() {
        return lastPus;
    }

    public void setLastPus(String lastPus) {
        this.lastPus = lastPus;
    }

    public String getLastVersion() {
        return lastVersion;
    }

    public void setLastVersion(String lastVersion) {
        this.lastVersion = lastVersion;
    }
}
