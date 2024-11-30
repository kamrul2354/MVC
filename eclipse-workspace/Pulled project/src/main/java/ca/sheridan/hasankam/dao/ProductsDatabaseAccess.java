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
        String query = "INSERT INTO product (productCode, productBrand, quantityInHand, unitPrice) " +
                       "VALUES (:productCode, :productBrand, :quantityInHand, :unitPrice)";
        MapSqlParameterSource params = new MapSqlParameterSource()
            .addValue("productCode", product.getProductCode())
            .addValue("productBrand", product.getProductBrand())
            .addValue("quantityInHand", product.getQuantityInHand())
            .addValue("unitPrice", product.getUnitPrice());
        return jdbc.update(query, params);
    }

    public List<Product> selectAllProducts() {
        String query = "SELECT * FROM product";
        return jdbc.query(query, new MapSqlParameterSource(), new BeanPropertyRowMapper<>(Product.class));
    }

    public Product selectProductById(int id) {
        String query = "SELECT * FROM product WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("id", id);
        List<Product> products = jdbc.query(query, params, new BeanPropertyRowMapper<>(Product.class));
        return products.isEmpty() ? null : products.get(0);
    }

    public long updateProductById(Product product) {
        String query = "UPDATE product SET productCode = :productCode, productBrand = :productBrand, " +
                       "quantityInHand = :quantityInHand, unitPrice = :unitPrice WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource()
            .addValue("id", product.getId())
            .addValue("productCode", product.getProductCode())
            .addValue("productBrand", product.getProductBrand())
            .addValue("quantityInHand", product.getQuantityInHand())
            .addValue("unitPrice", product.getUnitPrice());
        return jdbc.update(query, params);
    }

    public long deleteProductById(int id) {
        String query = "DELETE FROM product WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("id", id);
        return jdbc.update(query, params);
    }
}
