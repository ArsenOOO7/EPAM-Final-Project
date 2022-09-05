package com.arsen.epam.internet.shop.entity.user.ban;

import com.arsen.epam.internet.shop.entity.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * UserBan entity
 * Table: users_block
 *
 * @author Arsen Sydoryk
 */
public class UserBan implements Entity {

    private int id = 0;
    private int userId;

    private String reason;

    private int startTime;
    private int endTime;


    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    @Override
    public void extract(ResultSet resultSet) throws SQLException {

        id = resultSet.getInt("id");
        userId = resultSet.getInt("user_id");

        reason = resultSet.getString("reason");

        startTime = resultSet.getInt("start_time"); //seconds
        endTime = resultSet.getInt("end_time"); //seconds

    }

}
