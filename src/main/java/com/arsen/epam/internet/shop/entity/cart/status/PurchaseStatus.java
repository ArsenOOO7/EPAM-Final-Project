package com.arsen.epam.internet.shop.entity.cart.status;


/**
 *
 * Cart status
 *
 * @author Arsen Sydoryk
 */
public enum PurchaseStatus {

    UNDEFINED(0, ""),
    CART(1, "carted"),
    PURCHASED(2, "purchased"),
    CANCELLED(3, "cancelled");

    private final int id;
    private final String identifier;

    PurchaseStatus(int status, String identifier) {
        this.id = status;
        this.identifier = identifier;
    }


    public int getId() {
        return id;
    }

    public String getIdentifier() {
        return identifier;
    }


    /**
     * Get status by id
     *
     * @param id of status
     * @return PurchaseStatus
     */
    public static PurchaseStatus getValue(int id){
        for (PurchaseStatus value : PurchaseStatus.values()) {
            if(value.id == id){
                return value;
            }
        }

        return PurchaseStatus.UNDEFINED;
    }

    /**
     * Get status by identifier
     *
     * @param identifier of key
     * @return PurchaseStatus
     */
    public static PurchaseStatus getValue(String identifier){
        for (PurchaseStatus value : PurchaseStatus.values()) {
            if(value.identifier.equalsIgnoreCase(identifier)){
                return value;
            }
        }

        return PurchaseStatus.UNDEFINED;
    }
}
