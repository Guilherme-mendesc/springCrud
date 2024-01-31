package controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.EmployeeRecordDto;
import jakarta.validation.Valid;
import models.EmployeeModel;
import repositorys.EmployeeRepository;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@PostMapping("/employee")
	public ResponseEntity<EmployeeModel> saveEmployee(@RequestBody @Valid EmployeeRecordDto employeeRecordDto){
		var employeeModel = new EmployeeModel();
		BeanUtils.copyProperties(employeeRecordDto, employeeModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(employeeRepository.save(employeeModel));
	}
	
	@GetMapping("/employee")
	public ResponseEntity<List<EmployeeModel>> getAllEmployees(){
		List<EmployeeModel> employeeList = employeeRepository.findAll();
		if(!employeeList.isEmpty()) {
			for(EmployeeModel employee : employeeList) {
				UUID id= employee.getIdEmployee();
				employee.add(linkTo(methodOn(EmployeeController.class).getOneEmployee(id)).withSelfRel());
			}
		}
		return ResponseEntity.status(HttpStatus.OK).body(employeeList);
	}
	
	@GetMapping("/employee/{id}")
	public ResponseEntity<Object> getOneEmployee(@PathVariable(value="id") UUID id){
		Optional<EmployeeModel> employe0= employeeRepository.findById(id);
		if(employe0.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not Found");
		}
		employe0.get().add(linkTo(methodOn(EmployeeController.class).getAllEmployees()).withSelfRel());
			return ResponseEntity.status(HttpStatus.OK).body(employe0.get());
	}
	
	@PutMapping("/employee/{id}")
	public ResponseEntity<Object> updateEmployee(@PathVariable(value="id") UUID id, @RequestBody
													@Valid EmployeeRecordDto employeeRecordDto){
		Optional<EmployeeModel> employe0= employeeRepository.findById(id);
		if(employe0.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not Found");
		}
			var employeeModel = employe0.get();
			BeanUtils.copyProperties(employeeRecordDto, employeeModel);
			return ResponseEntity.status(HttpStatus.OK).body(employe0.get());
	}
	
	@DeleteMapping("/employee/{id}")
	public ResponseEntity<Object> deleteteEmployee(@PathVariable(value="id") UUID id){
		Optional<EmployeeModel> employe0= employeeRepository.findById(id);
		if(employe0.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not Found");
		}
		employeeRepository.delete(employe0.get());
		return ResponseEntity.status(HttpStatus.OK).body("Product deleted Successfully");
	
	}
}
