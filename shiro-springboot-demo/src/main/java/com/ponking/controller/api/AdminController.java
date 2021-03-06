package com.ponking.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ponking.aop.annotation.Log;
import com.ponking.model.entity.User;
import com.ponking.model.params.LoginParam;
import com.ponking.model.params.RegisterUserParam;
import com.ponking.model.result.Result;
import com.ponking.service.UserService;
import com.ponking.utils.JwtUtil;
import com.ponking.utils.LoginUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Peng
 * @date 2020/6/25--14:34
 **/
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Log
    @PostMapping("login")
    @ApiOperation("登录")
    public Result login(@RequestBody LoginParam loginParam) {
        Assert.notNull(loginParam, "loginParam is null");
        LoginUtil.login(loginParam);
        String token = jwtUtil.createToken(userService.getOne(
                new QueryWrapper<User>().eq("username", loginParam.getUsername())));
        HashMap<String, String> data = new HashMap<>();
        data.put("token", token);
        return Result.success().data(data);
    }


    @PostMapping("register")
    @ApiOperation("注册")
    public Result login(@RequestBody RegisterUserParam userParam) {
        userService.signInUser(userParam);
        return Result.success();
    }

    @GetMapping("info")
    @ApiOperation("获取用户信息")
    public Result login(@RequestParam("token") String token) {
        Assert.notNull(token, "This token is empty");
        return Result.success().data(getPermissions(token));

    }

    @PostMapping("logout")
    @ApiOperation("注销")
    public Result logout() {
        LoginUtil.logout();
        return Result.success();
    }

    /**
     * 模拟数据
     * @param token
     * @return
     */
    private Map<String, Object> getPermissions(String token) {
        HashMap<String, Object> res = new HashMap<>();
        String username = jwtUtil.getUsername(token);
        List<String> roles = jwtUtil.getRoles(token);
        User user = userService.getOne(new QueryWrapper<User>().eq("username", username));
        res.put("roles", roles);
        res.put("introduction", "I am a " + user.getNickName());
        res.put("avatar", user.getAvatar());
        res.put("name", user.getNickName());
        return res;
    }
}
