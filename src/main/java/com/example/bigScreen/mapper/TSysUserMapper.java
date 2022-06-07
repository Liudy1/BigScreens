package com.example.bigScreen.mapper;

import com.example.bigScreen.entity.TSysUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author: SuKai
 * @date: 2022/4/13
 * @time: 13:54
 * @description:
 */
@Repository
public interface TSysUserMapper {

    @Select("select * from t_sys_user where username = #{username} and status = 0")
    TSysUser findByUsername(String username);

    @Select("SELECT u.*,b.year batch_year,b.name batch_name,s.name second_name\n" +
            "FROM t_sys_user u,t_batch b,t_sys_second_company s,t_sys_second_user su\n" +
            "WHERE u.batch_id = b.id\n" +
            "AND u.id = su.user_id\n" +
            "and s.id = su.second_id\n" +
            "and u.status = 0")
    List<Map<String, Object>> findByPage();

    @Select("SELECT u.*,b.year batch_year,b.name batch_name,s.name second_name\n" +
            "FROM t_sys_user u,t_batch b,t_sys_second_company s,t_sys_second_user su\n" +
            "WHERE u.batch_id = b.id\n" +
            "AND u.id = su.user_id\n" +
            "and s.id = su.second_id\n" +
            "and u.status = 0\n" +
            "and su.second_id = (select su.second_id from t_sys_second_user su,t_sys_user u where u.id = su.user_id and u.status = 0 and u.username = #{username})")
    List<Map<String, Object>> findByPageAndSecond(String username);

    @Insert("INSERT INTO t_sys_user (id,username,cell_phone,status,account_type,sex,create_user,create_date,quota,batch_id,batch_state,third_name,thrid_quota,usernum) VALUES(#{id},#{username},#{cell_phone},0,2,#{sex},#{create_user},NOW(),0,#{batch_id},0,#{third_name},0,#{usernum})")
    int addUser(Map<String, Object> paramMap);

    @Insert("INSERT INTO t_sys_user_role (user_id,role_id) VALUES(#{id},2)")
    int addUserRole(String id);

    @Insert("INSERT INTO t_sys_second_user (second_id,user_id) VALUES(#{second_id},#{id})")
    int addUserSecond(Map<String, Object> paramMap);

    @Select("SELECT * FROM t_sys_user where usernum = #{usernum} and state = 0 and batch_id = #{batch_id}")
    List<Map<String, Object>> findById(Map<String, Object> paramMap);

    @Update("UPDATE t_sys_user set username = #{username},cell_phone = #{cell_phone},status = #{status},sex = #{sex},quota = #{quota},third_name = #{third_name},usernum = #{usernum} where id = #{id}")
    int edit(Map<String, Object> paramMap);

    @Update("UPDATE t_sys_user set status = 1 where id = #{user_id}")
    int delete(Map<String, Object> user_id);
}
