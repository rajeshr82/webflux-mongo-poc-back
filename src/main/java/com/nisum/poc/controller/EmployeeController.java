package com.nisum.poc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.poc.dto.EmployeeDto;
import com.nisum.poc.service.EmployeeService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/employees")
@Validated
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/")
	public ResponseEntity<Flux<EmployeeDto>> findAll() {
		return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
	}

	@PostMapping("/save")
	public ResponseEntity<Mono<Void>> saveEmployee(@Valid @RequestBody EmployeeDto emp) {
		return new ResponseEntity<>(employeeService.save(emp), HttpStatus.OK);
	}

	@GetMapping("/empId/{empId}")
	public ResponseEntity<Mono<EmployeeDto>> findByEmpId(@Valid @PathVariable Long empId) {
		return new ResponseEntity<>(employeeService.findByEmpId(empId), HttpStatus.OK);
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<Mono<EmployeeDto>> findById(@Valid @PathVariable String id) {
		return new ResponseEntity<>(employeeService.findById(id), HttpStatus.OK);
	}

	@GetMapping(path = "/sal/{sal}")
	public ResponseEntity<Flux<EmployeeDto>> findBySal(@Valid @PathVariable Double sal) {
		return new ResponseEntity<>(employeeService.findBySalary(sal), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{empId}")
	public ResponseEntity<Mono<Long>> deleteByEmpId(@PathVariable Long empId) {
		return new ResponseEntity<>(employeeService.deleteByEmpId(empId), HttpStatus.OK);
	}

}
