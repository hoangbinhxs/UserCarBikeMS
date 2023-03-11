package com.example.userservice.repository;

import com.example.userservice.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<Usuario, Integer> {



}
