package com.mxblr.dao;

import com.mxblr.data.dataObject.UserInfoDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserInfoDOMapper {
    int deleteByPrimaryKey(Integer userInfoId);

    int insert(UserInfoDO record);

    int insertSelective(UserInfoDO record);

    UserInfoDO selectByPrimaryKey(Integer userInfoId);

    int updateByPrimaryKeySelective(UserInfoDO record);

    int updateByPrimaryKey(UserInfoDO record);

    @Select("select * from user_info where user_id = #{id}")
    UserInfoDO selectByUserId(Integer id);

    @Update("update user_info set email = #{email}," +
            "phone_number = #{phoneNumber}," +
            "name = #{name}" +
            "where user_id = #{userId}")
    void updateUserInfo(UserInfoDO userInfoDO);

    /**
     * @author Kny
     * 通过userId修改用户权限
     */
    @Update("update user_info set role = #{role} where user_id = #{userId}")
    void updateRole(Integer userId, byte role);
}