package com.zhang.shop.model;

public class User {


    /**
     * code : 1
     * msg : succeed
     * data : {"username":"123456","other":"/images/D2DE0EF4740740A698E93655D762ED6F/97E6B10FF4.jpg","nickname":"帅的被人砍","name":"yta08f2277df6d41e699f421c15755201e","uuid":"D2DE0EF4740740A698E93655D762ED6F","password":"123456"}
     */

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * username : 123456
         * other : /images/D2DE0EF4740740A698E93655D762ED6F/97E6B10FF4.jpg
         * nickname : 帅的被人砍
         * name : yta08f2277df6d41e699f421c15755201e
         * uuid : D2DE0EF4740740A698E93655D762ED6F
         * password : 123456
         */

        private String username;
        private String other;
        private String nickname;
        private String name;
        private String uuid;
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getOther() {
            return other;
        }

        public void setOther(String other) {
            this.other = other;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
