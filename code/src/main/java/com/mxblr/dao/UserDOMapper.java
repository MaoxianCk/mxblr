package com.mxblr.dao;

import com.mxblr.data.dataObject.UserDO;
import com.mxblr.data.mo.user.UserMO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserDOMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(UserDO record);

    int insertSelective(UserDO record);

    UserDO selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(UserDO record);

    int updateByPrimaryKey(UserDO record);

    /**
     * 根据account查询
     */
    @Select("select * from user where account = #{account}")
    List<UserDO> selectByAccount(String account);

    /**
     * 插入用户并获取主键
     */
    @Insert("INSERT INTO USER (account, PASSWORD, salt)\n" +
            "VALUES\n" +
            "	(#{account},#{password},#{salt})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    void insertUser(UserMO userMO);

    /**
     * 通过用户id修改密码
     */
    @Update("update user set password = #{password},salt = #{salt} where user_id = #{userId}")
    void updatePasswordByUserId(Integer userId, String password, String salt);

    /**
     * 删除用户
     */
    @Delete("delete from user where user_id=#{userId}")
    void deleteUser(Integer userId);
}