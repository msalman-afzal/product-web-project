package com.spring.mvc.project.product.rpo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.spring.mvc.project.productdao.ProductDao;

@Repository
public class ProductRepoClass {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ProductRepoClass(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //  Insert product data into database
    public int insertData(ProductDao product) {
        String query = "insert into product(pid,pname,descirption,price,ltime) values(?,?,?,?,?)";
        int insertData = jdbcTemplate.update(query,
                product.getPid(),
                product.getPname(),
                product.getDescription(),
                product.getPrice(),
                product.getTime());
        System.out.println(insertData);
        return insertData;
    }

    //  Get a single product by ID
    public ProductDao getById(int pid) {
        String query = "SELECT * FROM product WHERE pid = ?";
        return jdbcTemplate.queryForObject(query, new RowPaper(), pid);
    }

    
    //  Update product data by ID
    public int updataData(ProductDao product) {
        String query = "UPDATE product SET pname=?, descirption=?, price=?, ltime=? WHERE pid=?";
        int rows = jdbcTemplate.update(query,
                product.getPname(),
                product.getDescription(),
                product.getPrice(),
                product.getTime(),
                product.getPid()); // condition by pid
        return rows;
    }

    
    //  Fetch all products from database
    public List<ProductDao> getAllProduct() {
        String query = "select * from product";
        RowPaper obj = new RowPaper();
        List<ProductDao> list = jdbcTemplate.query(query, obj);
        return list;
    }

    //   Delete a product by ID
    public int deleteById(int pid) {
        String query = "delete from product where pid = ?";
        int deleteId = jdbcTemplate.update(query, pid);
        return deleteId;
    }

    //   RowMapper implementation for mapping ResultSet → ProductDao object
    class RowPaper implements RowMapper<ProductDao> {
        @Override
        public ProductDao mapRow(ResultSet rs, int rowNum) throws SQLException {
            ProductDao obj = new ProductDao();
            obj.setPid(rs.getInt("pid"));
            obj.setPname(rs.getString("pname"));
            obj.setDescription(rs.getString("descirption"));
            obj.setPrice(rs.getLong("price"));
            obj.setTime(rs.getTimestamp("ltime").toLocalDateTime());
            return obj;
        }
    }
}
