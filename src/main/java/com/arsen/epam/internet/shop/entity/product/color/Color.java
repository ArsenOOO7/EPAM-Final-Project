package com.arsen.epam.internet.shop.entity.product.color;

/**
 *
 * Class represents possible colors of product
 *
 * @author Arsen Sydoryk
 */
public enum Color {

    BLACK(1, "black"),
    RED(2, "red"),
    ORANGE(3, "orange"),
    BLUE(4, "blue"),
    WHITE(5, "white"),
    PINK(6, "pink"),
    VIOLET(7, "violet"),
    GOLD(8, "gold"),
    YELLOW(9, "yellow");

    private final int id;
    private final String identifier;

    Color(int id, String identifier) {
        this.id = id;
        this.identifier = identifier;
    }

    public int getId() {
        return id;
    }

    public String getIdentifier() {
        return identifier;
    }


    /**
     * Getting color by id
     *
     * @param id from request
     * @return Color instance
     */
    public static Color getInstance(int id){
        Color color = Color.BLACK;
        for(Color c: Color.values()){
            if(c.getId() == id){
                color = c;
            }
        }
        return color;
    }


    /**
     * Getting color by identifier
     *
     * @param identifier from request
     * @return Color instance
     */
    public static Color getInstance(String identifier){
        Color color = Color.BLACK;
        for(Color c: Color.values()){
            if(c.identifier.equals(identifier)){
                color = c;
            }
        }
        return color;
    }

}
