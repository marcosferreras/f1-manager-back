package com.MADG2.security.dao;

import java.util.Optional;

import com.MADG2.security.entity.Rol;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.MADG2.security.entity.User;
import org.springframework.data.jpa.repository.Query;

public interface UserDao extends JpaRepository<User, Integer>{
    Optional<User> findByUserName(String username);
    boolean existsByUserName(String username);
    boolean existsByEmail(String email);
    User findById(Long id);

    User findByTeamId(Long id);
    @Query("select u from User u where u.role is null and (:name is null or upper(u.userName) like upper(concat('%', :name, '%')))")
    Page<User> findAllByRolesIsNullAndUserNameContainingIgnoreCase(Pageable pageable, String name);

    @Query("select u from User u where u.team is null and u.role.id = :rolId and (:name is null or upper(u.userName) like upper(concat('%', :name, '%')))")
    Page<User> findAllByRolesIsAndUserNameContainingIgnoreCase(Pageable pageable, String name, int rolId);
}
