package com.dztzb00t3.j2t.mapper;

import com.dztzb003.j2t.common.domain.DTO.UserInfoQueryDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.dztzb003.j2t.common.domain.entity.UserInfo;

import java.util.List;

/**
 * 自定义用户表 mapper
 *
 * @author j2t
 * @date 2024/09/14 15:52
 */
@Mapper
public interface UserInfoMapper {




    /**
     * @param username 用户名
     * @return userinfo
     */
    @Select("select * from user_info where username = #{username} ")
    UserInfo queryUserInfoByUsername(@Param("username") String username);




    /**
     * @return userinfo集合
     */
    List<UserInfo> queryUserInfoListAll(@Param("query") UserInfoQueryDTO queryDTO);




}
