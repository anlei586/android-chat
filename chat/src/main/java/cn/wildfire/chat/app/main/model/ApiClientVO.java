package cn.wildfire.chat.app.main.model;

import cn.wildfire.chat.app.Config;

public class ApiClientVO {
    public String autor = "";
    public String getAutor() {
        return autor;
    }
    public void setAutor(String v) {
        this.autor = v;
    }

    public String homeUrl = "";
    public String getHomeUrl() {
        return homeUrl;
    }
    public void setHomeUrl(String v) {
        this.homeUrl = v;
    }

    public String isOpenAdmin = "";
    public String getIsOpenAdmin() {
        return isOpenAdmin;
    }
    public void setIsOpenAdmin(String v) {
        this.isOpenAdmin = v;
    }

    public String apiAdmin = "";
    public String getApiAdmin() {
        return Config.APP_SERVER_PHP + "/yh/" + apiAdmin;
    }
    public void setApiAdmin(String v) {
        this.apiAdmin = v;
    }

    public String passwdsoupprt = "";
    public String getPasswdsoupprt() {
        return Config.APP_SERVER_PHP + "/yh/" + passwdsoupprt;
    }
    public void setPasswdsoupprt(String v) {
        this.passwdsoupprt = v;
    }

    public String onfgroupchat = "";
    public String getOnfgroupchat() {
        return onfgroupchat;
    }
    public void setOnfgroupchat(String v) {
        this.onfgroupchat = v;
    }

    public String onfadduser = "";
    public String getOnfadduser() {
        return onfadduser;
    }
    public void setOnfadduser(String v) {
        this.onfadduser = v;
    }

    public String searchfriendfuzzy = "";
    public String getSearchFriendFuzzy() {
        return searchfriendfuzzy;
    }
    public void setSearchFriendFuzzy(String v) {
        this.searchfriendfuzzy = v;
    }

    public String onfchatwinnoti = "";
    public String getOnfchatwinnoti() {
        return onfchatwinnoti;
    }
    public void setOnfchatwinnoti(String v) {
        this.onfchatwinnoti = v;
    }

    public String onfgroupuname = "";
    public String getOnfgroupuname() {
        return onfgroupuname;
    }
    public void setOnfgroupuname(String v) {
        this.onfgroupuname = v;
    }

    public String onfgrouplinshi = "";
    public String getOnfgrouplinshi() {
        return onfgrouplinshi;
    }
    public void setOnfgrouplinshi(String v) {
        this.onfgrouplinshi = v;
    }

    public String sendredpack = "";
    public String getSendredpack() {
        return sendredpack;
    }
    public void setSendredpack(String v) {
        this.sendredpack = v;
    }

    public String openredpack = "";
    public String getOpenredpack() {
        return openredpack;
    }
    public void setOpenredpack(String v) {
        this.openredpack = v;
    }

    public String dladmin = "";
    public String getDladmin() {
        return dladmin;
    }
    public void setDladmin(String v) {
        this.dladmin = v;
    }




}
