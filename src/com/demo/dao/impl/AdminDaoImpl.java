package com.demo.dao.impl;

import com.demo.dao.AdminDao;
import com.demo.domain.Admin;
import com.demo.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class AdminDaoImpl implements AdminDao {
    private JdbcTemplate jt = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public Admin login(Admin admin) {
        try {
            String sql = "select * from admin where username=? and password=?";
            Admin admin1 = jt.queryForObject(sql, new BeanPropertyRowMapper<>(Admin.class), admin.getUsername(), admin.getPassword());
            return admin1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
