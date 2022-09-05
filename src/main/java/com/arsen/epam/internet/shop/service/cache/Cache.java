package com.arsen.epam.internet.shop.service.cache;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * Cache class
 * Caching...
 *
 * @author Arsen Sydoryk
 */
public class Cache {

    private static final Logger log = LogManager.getLogger(Cache.class);

    /**
     * WeakHashMap object.
     * I use this because garbage collector sometimes clears it
     */
    private final Map<String, Object> map = new WeakHashMap<>();

    /**
     * Cache instance
     */
    private static Cache instance;

    /**
     * Gets Cache instance
     *
     * @return Cache instance
     */
    public static synchronized Cache getInstance(){
        if(instance == null){
            instance = new Cache();
            log.info("Creating new instance of cache");
        }
        log.debug("Returning Cache instance");
        return instance;
    }

    /**
     * Adding new attribute
     *
     * @param key string
     * @param object content
     */
    public void setAttribute(String key, Object object){
        log.debug("Setting new attribute: " + key);
        map.put(key, object);
    }

    /**
     * Check whether attribute exists
     *
     * @param key string
     * @return boolean
     */
    public boolean hasAttribute(String key){
        log.debug("Checking whether attribute " + key + " exists");
        return map.containsKey(key);
    }

    /**
     * Getting attribute from cache
     *
     * @param key string
     * @return Object value
     */
    public Object getAttribute(String key){
        log.debug("Getting attribute " + key);
        if(!map.containsKey(key)){
            log.error("Map does not contain key " + key);
            return null;
        }
        return map.get(key);
    }


    /**
     * Removing attribute from cache
     *
     * @param key string
     * @return Object value
     */
    public Object removeAttribute(String key){
        Object obj = map.get(key);

        log.debug("Removing attribute " + key);
        map.remove(key);

        return obj;
    }

}
