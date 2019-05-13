package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmpController {

	@RequestMapping("/emp")
	public List<Emp> hello() {
		return new ArrayList<Emp>(Arrays.asList( 
				new Emp("java",Arrays.asList(910,1102,1103)),
				new Emp("php",Arrays.asList(804,1150,806)),
				new Emp("dot",Arrays.asList(1170,888,1109))	
			));
	}
	
	@RequestMapping("/emp2")
	public Emp hello2() {
		return new Emp("java",Arrays.asList(1111,1112,113));
	}
	
	
}
