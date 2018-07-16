package com.efuture.demo.api;

import com.efuture.demo.annotation.CurrentUser;
import com.efuture.demo.annotation.LoginRequired;
import com.efuture.demo.model.User;
import com.efuture.demo.service.UserService;
import com.efuture.demo.util.JsonResponse;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 实现用户注册接口
 */
@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserApi {
    private UserService userService;

    @Autowired
    public UserApi(UserService userService) {
        this.userService = userService;
    }
    @ApiOperation(value = "注册用户", notes = "注册用户")
    @ApiImplicitParam(name = "用户信息", value = "用户信息", required = true, dataType = "User")
    @PostMapping(value="/add")
    public JsonResponse add(@RequestBody User user) {
        try {
            if (userService.findByName(user.getName()) != null) {
                return JsonResponse.notOk("用户名已被使用");
            }
            User add = userService.add(user);
            log.info("------------add----------- {}",user);
            return JsonResponse.ok(add);
        } catch (Exception e) {
            log.debug(e.toString());
            return JsonResponse.notOk(500, "查询失败");
        }
    }
   /* @LoginRequired
    @GetMapping("/test")
    public Object testLogin() {
        return "success";
    }*/

    @GetMapping("/test")
    @LoginRequired
    public Object testCurrentUser(@CurrentUser User user) {
        return user;
    }

    @ApiOperation(value = "查询", notes = "根据id查询用户信息")
    @GetMapping(value="/findById/{id}")
    public Object findById(@PathVariable int id) {
        try {
            User byId = userService.findById(id);
            log.info("------------findById----------- {}",id);
            return JsonResponse.ok(byId);
        } catch (Exception e) {
            log.debug(e.toString());
            return JsonResponse.notOk(500, "查询失败");
        }

    }
}
