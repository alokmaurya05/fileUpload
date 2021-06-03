package com.awin.dto;

import java.io.Serializable;

import com.awin.entity.JobApplication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobApplicationRequest implements Serializable {

	private static final long serialVersionUID = -6671624138998950104L;
	private JobApplication jobApplication;

}
