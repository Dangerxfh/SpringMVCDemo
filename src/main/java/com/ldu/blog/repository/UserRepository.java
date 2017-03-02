package com.ldu.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ldu.blog.model.UserEntity;



@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Modifying      // ˵���÷������޸Ĳ���
    @Transactional  // ˵���÷����������Բ���
    // �����ѯ
    // @Paramע��������ȡ����
    @Query("update UserEntity us set us.nickname=:qNickname, us.firstName=:qFirstName, us.lastName=:qLastName, us.password=:qPassword where us.id=:qId")
    public void updateUser(@Param("qNickname") String nickname, @Param("qFirstName") String firstName,
                           @Param("qLastName") String qLastName, @Param("qPassword") String password, @Param("qId") Integer id);
}