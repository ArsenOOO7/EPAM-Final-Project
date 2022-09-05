package com.arsen.epam.internet.shop.entity.image;

import com.arsen.epam.internet.shop.entity.Entity;
import com.arsen.epam.internet.shop.entity.image.mime.ImageContentType;

import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Image entity
 *
 * @author Arsen Sydoryk
 */
public class Image implements Entity {

    private int id;

    private String uuid;
    private ImageContentType contentType;

    private InputStream data;

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public ImageContentType getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        setContentType(ImageContentType.getContentType(contentType));
    }

    public void setContentType(ImageContentType contentType) {
        this.contentType = contentType;
    }

    public InputStream getData() {
        return data;
    }

    public void setData(InputStream data) {
        this.data = data;
    }

    @Override
    public void extract(ResultSet resultSet) throws SQLException {

        id = resultSet.getInt("id");
        uuid = resultSet.getString("uuid");
        setContentType(resultSet.getString("mime"));

        data = resultSet.getBlob("data").getBinaryStream();

    }
}
