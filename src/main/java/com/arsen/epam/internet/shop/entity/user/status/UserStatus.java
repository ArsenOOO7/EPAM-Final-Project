package com.arsen.epam.internet.shop.entity.user.status;


/**
 * Class represents possible roles of User
 *
 * @author Arsen Sydoryk
 */
public enum UserStatus {

    UNDEFINED(-1),
    USER(0),
    ADMIN(1);

    UserStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    private final int status;


    /**
     * Getting status by status id
     *
     * @param status id from DB
     * @return UserStatus instance
     */
    public static UserStatus getStatus(int status){
        for(UserStatus userStatus: UserStatus.values()){
            if(userStatus.status == status){
                return userStatus;
            }
        }
        return UserStatus.UNDEFINED;
    }

}
