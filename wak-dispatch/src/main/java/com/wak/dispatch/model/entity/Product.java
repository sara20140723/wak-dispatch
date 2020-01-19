package com.wak.dispatch.model.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Role
 *
 * @author sophy
 * @date 2020/02/02 14:42
 */
@Data
public class Product implements Serializable {
    private Integer id;

    private String productName;
    private String title;
    private String cover;
    private String norms;
    private String productCode;

}