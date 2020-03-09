package com.demo.service;

import com.demo.domain.PageBean;
import com.demo.domain.User;

import java.util.List;
import java.util.Map;

/**
 * 用户管理业务接口
 */
public interface UserService {
    /**
     * 查询所有用户信息
     *
     * @return
     */
    List<User> findAll();

    /**
     * 保存user
     *
     * @param user
     */
    void addUser(User user);

    /**
     * 根据id删除
     *
     * @param id
     */
    void deleteUser(String id);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    User findUserById(String id);

    /**
     * 修改用户信息
     *
     * @param user
     */
    void updateUser(User user);

    /**
     * 批量删除用户
     *
     * @param uids
     */
    void delSelectedUser(String[] uids);

    /**
     * 分页/条件查询
     *
     * @param currentPage
     * @param rows
     * @param condition
     * @return
     */
    PageBean<User> findUserByPage(String currentPage, String rows, Map<String, String[]> condition);
}
