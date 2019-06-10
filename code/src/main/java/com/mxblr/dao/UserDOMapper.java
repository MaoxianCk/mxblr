package com.mxblr.dao;

import com.mxblr.data.dataObject.UserDO;
import com.mxblr.data.mo.UserMO;
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
     * @author Ck
     * 根据account查询
     */
    @Select("select * from user where account = #{account}")
    List<UserDO> selectByAccount(String account);

    /**
     * @author Ck
     * 插入用户并获取主键
     */
    @Insert("INSERT INTO USER (account, PASSWORD, salt)\n" +
            "VALUES\n" +
            "	(#{account},#{password},#{salt})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    void insertUser(UserMO userMO);

    /**
     * @author Kny
     * 通过用户id修改密码
     */
    @Update("update user set password = #{password},salt = #{salt} where user_id = #{userId}")
    void updatePasswordByUserId(Integer userId, String password, String salt);
}