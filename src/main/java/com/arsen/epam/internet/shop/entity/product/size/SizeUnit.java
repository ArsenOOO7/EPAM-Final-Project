package com.arsen.epam.internet.shop.entity.product.size;


/**
 *
 * Class represents possible units of size of product
 *
 * @author Arsen Sydoryk
 */
public enum SizeUnit {

    UNDEFINED(0, ""),
    MILLIMETRE(1, "mm"),
    CENTIMETRE(2, "sm"),
    METRE(3, "m");

    private final int id;
    private final String unit;

    SizeUnit(int id, String unit) {
        this.id = id;
        this.unit = unit;
    }

    public int getId() {
        return id;
    }

    public String getUnit() {
        return unit;
    }

    /**
     * Getting unit by name
     *
     * @param unit from request
     * @return SizeUnit instance
     */
    public static SizeUnit getValue(String unit){
        for(SizeUnit sizeUnit: SizeUnit.values()){
            if(sizeUnit.unit.equals(unit)){
                return sizeUnit;
            }
        }

        return SizeUnit.UNDEFINED;
    }


    /**
     * Getting unit by id
     *
     * @param id from request
     * @return SizeUnit instance
     */
    public static SizeUnit getValue(int id){
        for(SizeUnit sizeUnit: SizeUnit.values()){
            if(sizeUnit.id == id){
                return sizeUnit;
            }
        }

        return SizeUnit.UNDEFINED;
    }
}
