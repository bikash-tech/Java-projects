package com.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.model.Branch;

@Repository
public interface EmpRepo extends JpaRepository<Branch, Integer>{

}
