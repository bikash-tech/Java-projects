package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.repo.EmpRepo;

@Service
public class EmpService {
	@Autowired
	private EmpRepo repo;
}
