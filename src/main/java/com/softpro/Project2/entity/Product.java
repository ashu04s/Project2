package com.softpro.Project2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product {


	@Override
	public String toString() {
		return "Product [product_id=" + product_id + ", product_name=" + product_name + ", product_desc=" + product_desc
				+ ", fk_cat_id=" + fk_cat_id + ", fk_brand_id=" + fk_brand_id + ", product_qty=" + product_qty
				+ ", product_cost_price=" + product_cost_price + ", product_rating=" + product_rating
				+ ", product_selling_price=" + product_selling_price + ", product_weight=" + product_weight
				+ ", product_weight_type=" + product_weight_type + ", product_status=" + product_status + "]";
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer product_id;

    private String product_name;
    private String product_desc;
    private Integer fk_cat_id;
    private Integer fk_brand_id;
    private Integer product_qty;
    private Double product_cost_price;
    private Integer product_rating;
    private String product_image_sm;
    private String product_image_lg;
    private Double product_selling_price;
    private Double product_weight;
    private String product_weight_type;
    private String product_status;

    // Default Constructor
    public Product() {
    }

    // Parameterized Constructor
    public Product(Integer product_id, String product_name, String product_desc,
                   Integer fk_cat_id, Integer fk_brand_id, Integer product_qty,
                   Double product_cost_price, Integer product_rating,
                   String product_image_sm, String product_image_lg,
                   Double product_selling_price, Double product_weight,
                   String product_weight_type, String product_status) {

        this.product_id = product_id;
        this.product_name = product_name;
        this.product_desc = product_desc;
        this.fk_cat_id = fk_cat_id;
        this.fk_brand_id = fk_brand_id;
        this.product_qty = product_qty;
        this.product_cost_price = product_cost_price;
        this.product_rating = product_rating;
        this.product_image_sm = product_image_sm;
        this.product_image_lg = product_image_lg;
        this.product_selling_price = product_selling_price;
        this.product_weight = product_weight;
        this.product_weight_type = product_weight_type;
        this.product_status = product_status;
    }

    // Getters and Setters

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_desc() {
        return product_desc;
    }

    public void setProduct_desc(String product_desc) {
        this.product_desc = product_desc;
    }

    public Integer getFk_cat_id() {
        return fk_cat_id;
    }

    public void setFk_cat_id(Integer fk_cat_id) {
        this.fk_cat_id = fk_cat_id;
    }

    public Integer getFk_brand_id() {
        return fk_brand_id;
    }

    public void setFk_brand_id(Integer fk_brand_id) {
        this.fk_brand_id = fk_brand_id;
    }

    public Integer getProduct_qty() {
        return product_qty;
    }

    public void setProduct_qty(Integer product_qty) {
        this.product_qty = product_qty;
    }

    public Double getProduct_cost_price() {
        return product_cost_price;
    }

    public void setProduct_cost_price(Double product_cost_price) {
        this.product_cost_price = product_cost_price;
    }

    public Integer getProduct_rating() {
        return product_rating;
    }

    public void setProduct_rating(Integer product_rating) {
        this.product_rating = product_rating;
    }

    public String getProduct_image_sm() {
        return product_image_sm;
    }

    public void setProduct_image_sm(String product_image_sm) {
        this.product_image_sm = product_image_sm;
    }

    public String getProduct_image_lg() {
        return product_image_lg;
    }

    public void setProduct_image_lg(String product_image_lg) {
        this.product_image_lg = product_image_lg;
    }

    public Double getProduct_selling_price() {
        return product_selling_price;
    }

    public void setProduct_selling_price(Double product_selling_price) {
        this.product_selling_price = product_selling_price;
    }

    public Double getProduct_weight() {
        return product_weight;
    }

    public void setProduct_weight(Double product_weight) {
        this.product_weight = product_weight;
    }

    public String getProduct_weight_type() {
        return product_weight_type;
    }

    public void setProduct_weight_type(String product_weight_type) {
        this.product_weight_type = product_weight_type;
    }

    public String getProduct_status() {
        return product_status;
    }

    public void setProduct_status(String product_status) {
        this.product_status = product_status;
    }
}