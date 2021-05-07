package com.awin.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "jobApplication")
@NoArgsConstructor
@AllArgsConstructor
public class JobApplication {
	@Id
	@GeneratedValue
	private Long id;


	@NotEmpty
	private String firstName;

	@NotEmpty
	private String lastName;
	
	@NotEmpty
	private String email;
	@NotEmpty
	private String jobtitle;
	
	private String source;
}
