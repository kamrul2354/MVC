/*
 * Author: Kamrul Hasan
 * Student ID: 991769795
 * Date: [03-11-2024]
 * 
 * Description:
 * Data Access Object (DAO) class for performing database operations on products.
 * Includes methods for adding, updating, deleting, and querying products.
 */

package ca.sheridan.hasankam.dao;

import ca.sheridan.hasankam.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductsDatabaseAccess {
    @Autowired
    protected NamedParameterJdbcTemplate jdbc;

    public long addProduct(Product product) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String insert = "INSERT INTO product (productCode, productBrand, quantityInHand, unitPrice) "
                + "VALUES (:productCode, :productBrand, :quantityInHand, :unitPrice)";
        namedParameters.addValue("productCode", product.getProductCode());
        namedParameters.addValue("productBrand", product.getProductBrand());
        namedParameters.addValue("quantityInHand", product.getQuantityInHand());
        namedParameters.addValue("unitPrice", product.getUnitPrice());
        int rowsAffected = jdbc.update(insert, namedParameters);
        return rowsAffected;
    }

    public List<Product> selectProducts() {
        String query = "SELECT * FROM product";
        List<Product> products = jdbc.query(query, new MapSqlParameterSource(),
                new BeanPropertyRowMapper<>(Product.class));
        return products;
    }

    public Product getProductById(int id) {
        String query = "SELECT * FROM product WHERE id = :id";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id", id);
        List<Product> products = jdbc.query(query, namedParameters,
                new BeanPropertyRowMapper<>(Product.class));
        if (!products.isEmpty()) {
            return products.get(0);
        } else {
            return null;
        }
    }

    public int updateProduct(Product product) {
        String update = "UPDATE product SET productCode = :productCode, productBrand = :productBrand, "
                + "quantityInHand = :quantityInHand, unitPrice = :unitPrice WHERE id = :id";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("productCode", product.getProductCode());
        namedParameters.addValue("productBrand", product.getProductBrand());
        namedParameters.addValue("quantityInHand", product.getQuantityInHand());
        namedParameters.addValue("unitPrice", product.getUnitPrice());
        namedParameters.addValue("id", product.getId());
        int rowsAffected = jdbc.update(update, namedParameters);
        return rowsAffected;
    }

    public int deleteProduct(int id) {
        String delete = "DELETE FROM product WHERE id = :id";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id", id);
        int rowsAffected = jdbc.update(delete, namedParameters);
        return rowsAffected;
    }

    // Method to select products by brand
    public List<Product> selectProductsByBrand(String productBrand) {
        String query = "SELECT * FROM product WHERE productBrand = :productBrand";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("productBrand", productBrand);
        List<Product> products = jdbc.query(query, namedParameters,
                new BeanPropertyRowMapper<>(Product.class));
        return products;
    }

    // Method to select products by quantity threshold
    public List<Product> selectProductsByQuantity(int quantityInHand) {
        String query = "SELECT * FROM product WHERE quantityInHand < :quantityInHand";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("quantityInHand", quantityInHand);
        List<Product> products = jdbc.query(query, namedParameters,
                new BeanPropertyRowMapper<>(Product.class));
        return products;
    }
}
