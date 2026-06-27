package org.lollipop.controller;

/**
 * @Author: lollipop
 * @Date: 2026/3/19 20:38
 * @Description:
 **/

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.lollipop.entity.User;
import org.lollipop.mapper.extend.UserExtendMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserController extends BaseController {

    @Autowired
    private UserExtendMapper userExtendMapper;

    @RequestMapping("/getAllUser")
    public Object getAllUser() {
        // 查询所有用户（排除已删除的）
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", 0);
        return userExtendMapper.selectList(queryWrapper);
    }

    @RequestMapping("/getUserById")
    public Object getUserById(Long id) {
        return userExtendMapper.selectById(id);
    }
}
