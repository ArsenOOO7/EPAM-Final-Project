package com.arsen.epam.internet.shop.entity.user;

import com.arsen.epam.internet.shop.entity.Entity;
import com.arsen.epam.internet.shop.entity.user.ban.UserBan;
import com.arsen.epam.internet.shop.entity.user.status.UserStatus;
import com.arsen.epam.internet.shop.repository.HeadRepository;
import com.arsen.epam.internet.shop.repository.ban.specification.BanUserIdSpecification;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * User entity
 *
 * @author Arsen Sydoryk
 */
public class User implements Entity {

    private int id;

    private String name;
    private String surname;

    private String email;

    private String login;
    private String password;

    private Date birthDate;

    private double balance;

    private int avatarId;

    private UserStatus status;
    private UserBan userBan;


    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void increaseBalance(double money){
        balance += money;
    }

    public void decreaseBalance(double money){
        balance -= money;
        if(balance < 0){
            balance = 0;
        }
    }

    public boolean hasEnoughMoney(double needle){
        return balance >= needle;
    }

    public int getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(int avatarId) {
        this.avatarId = avatarId;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = UserStatus.getStatus(status);
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public UserBan getUserBan() {
        return userBan;
    }

    public void setUserBan(UserBan userBan) {
        this.userBan = userBan;
    }

    public boolean isBanned(){
        return userBan != null;
    }

    @Override
    public void extract(ResultSet resultSet) throws SQLException {

        id = resultSet.getInt("id");

        name = resultSet.getString("name");
        surname = resultSet.getString("surname");

        email = resultSet.getString("email");
        login = resultSet.getString("login");
        password = resultSet.getString("password");

        birthDate = resultSet.getDate("birth_date");

        balance = resultSet.getDouble("balance");
        avatarId = resultSet.getInt("avatar");

        setStatus(resultSet.getInt("status"));

        int banned = resultSet.getInt("blocked");
        if(banned > 0){
            userBan = HeadRepository.getBanRepository().findOne(new BanUserIdSpecification(id));
        }
    }
}
