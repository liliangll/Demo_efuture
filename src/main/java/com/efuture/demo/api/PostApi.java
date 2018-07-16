package com.efuture.demo.api;

import com.efuture.demo.annotation.CurrentUser;
import com.efuture.demo.annotation.LoginRequired;
import com.efuture.demo.model.Post;
import com.efuture.demo.model.User;
import com.efuture.demo.service.PostService;
import com.efuture.demo.util.JsonResponse;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文章接口
 */
@Slf4j
@RestController
@RequestMapping("/api/post")
public class PostApi {
    private PostService postService;

    @Autowired
    public PostApi(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/add")
    @ApiOperation(value="新增文章",notes = "根据Post对象添加作者信息")
    @LoginRequired
    public JsonResponse add(@RequestBody Post post, @CurrentUser User user) {
        try {
            post.setAuthorId(user.getId()); // 添加作者信息
            post = postService.add(post);
            log.info("------------add----------- {}", user.getId());
            return JsonResponse.ok(post);
        } catch (Exception e) {
            log.debug(e.toString());
            return JsonResponse.notOk(500, "新增失败");
        }
    }
    @ApiOperation(value="查询",notes = "根据ID查看文章信息")
    @GetMapping("/findById/{id}")
    @LoginRequired
    public JsonResponse findById(@PathVariable int id, @CurrentUser User user) {
        try {
            Post post = postService.findById(id);
            post.setAuthorId(user.getId());
            log.info("------------findById----------- {}", user.getId());
            return JsonResponse.ok(post);
        } catch (Exception e) {
            log.debug(e.toString());
            return JsonResponse.notOk(500, "查询失败");
        }
    }
    @ApiOperation(value="查询",notes = "查看文章信息")
    @GetMapping("/all")
    public JsonResponse all() {
        try {
            List<Post> all = postService.all();
            log.info("------------all----------- {}", all);
            return JsonResponse.ok(all);
        } catch (Exception e) {
            log.debug(e.toString());
            return JsonResponse.notOk(500, "查询失败");
        }
    }

    /**
     *  更新文章，需要登录
     * @param post  需要修改的内容
     * @param id    文章 id
     * @param currentUser  当前用户
     * @return 更新之后的文章
     */
    @LoginRequired
    @ApiOperation(value="修改",notes = "修改文章信息")
    @PutMapping("/update/{id}")
    public JsonResponse update(@RequestBody Post post, @PathVariable int id, @CurrentUser User currentUser) {
        try {
            post.setId(id);
            Post update = postService.update(post, currentUser);
            log.info("------------update----------- {}", post.getId());
            return JsonResponse.ok(update);
        } catch (Exception e) {
            log.debug(e.toString());
            return JsonResponse.notOk(500, "修改失败");
        }
    }

    /**
     * 删除文章，需要登录
     * @param id 文章 id
     * @param currentUser 当前登录用户
     * @return 提示信息
     */
    @LoginRequired
    @ApiOperation(value="删除",notes = "根据ID删除文章信息")
    @DeleteMapping("/delete/{id}")
    public Object delete(@PathVariable int id, @CurrentUser User currentUser) {
        try {
            postService.delete(id, currentUser);
            log.info("------------doDel----------- {}", id);
            return JsonResponse.ok("删除成功");
        } catch (Exception e) {
            log.debug(e.toString());
            return JsonResponse.notOk(500, "删除失败");
        }
    }
}
