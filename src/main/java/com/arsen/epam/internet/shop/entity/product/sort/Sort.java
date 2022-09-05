package com.arsen.epam.internet.shop.entity.product.sort;


/**
 * Class represents possible sorting type of products
 *
 * @author Arsen Sydoryk
 */
public enum Sort {

    NAME_ASCEND("sortNameAsc", "shortname", "ASC"),
    NAME_DESCEND("sortNameDesc", "shortname", "DESC"),
    PRICE_ASCEND("sortPriceAsc", "price", "ASC"),
    PRICE_DESCEND("sortPriceDesc", "price", "DESC"),
    NOVELTY("sortNovelty", "start_date", "DESC"),
    UNDEFINED("", "", "");


    private final String code;
    private final String field;
    private final String order;

    Sort(String code, String field, String order){
        this.code = code;
        this.field = field;
        this.order = order;
    }

    public String getCode(){
        return code;
    }

    public String getField() {
        return field;
    }

    public String getOrder() {
        return order;
    }


    /**
     *
     * @param code of sort from query params
     * @return Sort instance
     */
    public static Sort getInstance(String code){
        Sort sort = Sort.UNDEFINED;
        for(Sort value: Sort.values()){
            if(value.getCode().equalsIgnoreCase(code)){
                sort = value;
                break;
            }
        }
        return sort;
    }

}
