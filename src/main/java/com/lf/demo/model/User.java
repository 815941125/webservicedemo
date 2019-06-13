package com.lf.demo.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.Date;

/**
 * @author linfei
 * @version 1.0
 * @date 2019/6/11
 * @desc
 * @see
 * @since 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "User")
@XmlType(propOrder = {
        "userId",
        "userName",
        "password",
        "birthday",
        "money",
})
public class User implements Serializable {
        private static final long serialVersionUID = 1L;

        // 用户Id
        private int userId;
        // 用户名
        private String userName;
        // 用户密码
        private String password;
        // 用户生日
        private Date birthday;
        // 用户钱包
        private double money;

        public User() {
        super();
        }

        public User(int userId, String userName, String password, Date birthday,
        double money) {
        super();
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.birthday = birthday;
        this.money = money;
        }

        public int getUserId() {
                return userId;
                }

        public void setUserId(int userId) {
                this.userId = userId;
                }

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

        public Date getBirthday() {
                return birthday;
                }

        public void setBirthday(Date birthday) {
                this.birthday = birthday;
                }

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }

        @Override
        public String toString() {
            return "User [birthday=" + birthday + ", money=" + money
            + ", password=" + password + ", userId=" + userId
            + ", userName=" + userName + "]";
        }
}
