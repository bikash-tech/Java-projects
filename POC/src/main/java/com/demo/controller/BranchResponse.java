package com.demo.controller;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("mainFilter")
public class BranchResponse {
	private Integer id;
	private String name;
	private List<EmpResponse> empList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<EmpResponse> getEmpList() {
		return empList;
	}

	public void setEmpList(List<EmpResponse> empList) {
		this.empList = empList;
	}

}
