package com.example.parameter.verification.demo.controller;

import com.example.parameter.verification.demo.common.result.CommonResult;
import com.example.parameter.verification.demo.entity.dto.UserInfoDto;
import com.example.parameter.verification.demo.entity.dto.PasswordParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户相关接口
 *
 * @author aaron
 * @since 2021-01-19
 */

@Validated
@RestController
@RequestMapping("/user")
public class UserController {


    /**
     * 正则校验
     * 设置密码
     *
     * @param passwordParam 密码+
     * @return CommonResult<Boolean>
     */
    @PostMapping("/setPassword")
    CommonResult<String> create(@RequestBody @Validated PasswordParam passwordParam) {
        String password = passwordParam.getPassword();
        return CommonResult.success(password);
    }


    /**
     * 分组校验 group 包含 Update的 方可生效
     * 修改用户信息
     * @param userInfoDto 用户信息对象
     * @return CommonResult<String>
     */
    @PutMapping("/updateUserInfo")
    CommonResult<String> updateUserInfo(@RequestBody @Validated(UserInfoDto.Update.class) UserInfoDto userInfoDto) {
        String name = userInfoDto.getName();
        return CommonResult.success(name);
    }


}
