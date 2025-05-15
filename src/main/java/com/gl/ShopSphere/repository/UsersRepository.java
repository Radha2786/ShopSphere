package com.gl.ShopSphere.repository;

import com.gl.ShopSphere.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users,Integer> {

//    Query approach (jpql query)
//    @Query("select u from Users u where email =:email")
//    public Users findByEmail(String email);

    // for all the select operations
//    public Users findByEmail(String email);
//    public Users getByEmail(String email);
    List<Users> findByRole(String role);
}
