package cn.sevendream.study.springmvcdemo.repository;

import cn.sevendream.study.springmvcdemo.model.UserloginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * ${DESCRIPTION}
 *
 * @author zhangxue
 * @date 2018-08-23 下午1:51
 */
@Repository
public interface UserRepository extends JpaRepository<UserloginEntity, Integer>{
    @Modifying // 说明该方法是修改操作
    @Transactional// 说明该方法是事务性操作
    @Query("UPDATE UserloginEntity u SET u.userName =:userName ,u.password =:password where u.userId =:id")
    void updateUser(@Param("userName") String userName,
                    @Param("password") String password,
                    @Param("id") Integer id) throws Exception;
}
