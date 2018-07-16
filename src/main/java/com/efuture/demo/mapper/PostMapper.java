package com.efuture.demo.mapper;

import com.efuture.demo.model.Post;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
@Mapper
public interface PostMapper {
    int add(Post post);

    Post findOne(Post param);

    List<Post> all();

    void update(Post post);

    void delete(int id);
}
