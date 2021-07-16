package com.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.Branch;
import com.demo.model.Employee;
import com.demo.repo.EmpRepo;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class EmpController {

	@Autowired
	private EmpRepo repo;

	@GetMapping
	@RequestMapping(path = "/branch-list")
	public List<BranchResponse> getAllBranch() throws JsonParseException, JsonMappingException, IOException {
		List<Branch> allBranchList = repo.findAll();
		List<BranchResponse> bList = new ArrayList<>();
		for (Branch branch : allBranchList) {
			BranchResponse bRe = new BranchResponse();
			BeanUtils.copyProperties(branch, bRe);
			List<Employee> employeeList = branch.getEmployee();
			List<EmpResponse> eResponseList = new ArrayList<>();
			for (Employee e : employeeList) {
				EmpResponse eResponse = new EmpResponse();
				BeanUtils.copyProperties(e, eResponse);
				eResponseList.add(eResponse);
			}
			bRe.setEmpList(eResponseList);
			FilterProvider filterProvider = new SimpleFilterProvider()
					.addFilter("mainFilter", SimpleBeanPropertyFilter.filterOutAllExcept("empList"))
					.setFailOnUnknownId(true);
//			MappingJacksonValue mapper = new MappingJacksonValue(bRe);
//			mapper.setFilters(filterProvider);
			ObjectMapper mapper = new ObjectMapper();
//			mapper.setFilterProvider(filterProvider);
			ObjectWriter writer = mapper.writer(filterProvider);
			byte[] writeValueAsString = writer.writeValueAsBytes(bRe);
			BranchResponse resultCustomer = mapper.readValue(writeValueAsString, BranchResponse.class);
			bList.add(resultCustomer);
		}

		return bList;
	}
}
