package com.demo.test;

import com.demo.dao.AdminDao;
import com.demo.dao.impl.AdminDaoImpl;
import com.demo.domain.Admin;
import org.junit.Test;

public class LoginTest {
    @Test
    public void login() {
        Admin a = new Admin();
        a.setUsername("雷雪松");
        a.setPassword("000000");

        AdminDao ad = new AdminDaoImpl();
        Admin login = ad.login(a);
        System.out.println(login);
    }
}
