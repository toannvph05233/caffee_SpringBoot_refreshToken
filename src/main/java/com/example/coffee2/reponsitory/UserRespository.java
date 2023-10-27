package com.example.coffee2.reponsitory;

import com.example.coffee2.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

//????
@EnableJpaRepositories
@Repository
public interface UserRespository extends JpaRepository<UserEntity, Long> {
    @Query(
            value = "SELECT * FROM user1",
            nativeQuery = true
    )
    List<UserEntity> findAllUser();

    @Query(
            value = "select e.user_name from user1 e where e.user_name = :userName",
            nativeQuery = true
    )
    List<String> findByUserName(@RequestParam String userName);

    @Query(
            value = "select user_name from user1",
            nativeQuery = true
    )
    List<String> findAllUserName();

//    List<String> getByUserName(@RequestParam String userName);
}
