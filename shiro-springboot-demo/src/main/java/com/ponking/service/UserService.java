package com.ponking.service;

import com.ponking.model.dto.UserDTO;
import com.ponking.model.entity.User;
import com.ponking.model.params.RegisterUserParam;
import com.ponking.model.params.UserParam;
import com.ponking.model.params.UserQuery;
import com.ponking.model.vo.UserListVo;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author peng
 * @since 2020-06-25
 */
public interface UserService extends BaseService<UserDTO,User> {

    UserListVo fetchList(UserQuery query);

    void create(UserParam user);

    boolean updateById(UserParam userParam);

    void assignRoles(String userId,List<String> roles);

    void signInUser(RegisterUserParam userParam);
}
