package com.ponking.model.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Peng
 * @date 2020/6/26--20:25
 **/
@Data
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String username;
    private String nickName;
    private String avatar;
    private List<String> roles;
}
