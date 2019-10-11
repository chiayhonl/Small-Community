package com.first.community.mapper;

import com.first.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Chiayhon
 * @create 2019 - 10 - 11
 */
@Mapper
public interface UserMapper {
    @Insert("insert into user(name, account_id, token , gmt_create , gmt_modified) values( #{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})")
    void insert(User user);

    @Insert("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);
}
