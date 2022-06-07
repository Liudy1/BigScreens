package com.example.bigScreen.mapper;

import com.example.bigScreen.entity.TSysRes;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: SuKai
 * @date: 2022/4/13
 * @time: 13:49
 * @description:
 */
@Repository
public interface TSysResMapper {

    @Select("select * from t_sys_res")
    List<TSysRes> selectList();

    @Select("select e.* from t_sys_res e,t_sys_role_res re,t_sys_role r,t_sys_user u,t_sys_user_role ur\n" +
            "where re.res_id = e.id\n" +
            "and re.role_id = r.id\n" +
            "and ur.user_id = u.id\n" +
            "and ur.role_id = r.id\n" +
            "and u.status = 0\n" +
            "and u.id = #{id}")
    List<TSysRes> findResByUserId(String id);
}
