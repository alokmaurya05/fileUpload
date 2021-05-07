package com.awin.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.awin.entity.JobApplication;
import com.awin.repository.JobApplicationRepository;
import com.awin.services.JobApplicationService;

@Service
public class JobApplicationServiceImpl implements JobApplicationService {

	@Autowired()
	private JobApplicationRepository repository;
	
	@Override
	public JobApplication save(JobApplication jobApplication) {
		
		return repository.save(jobApplication);
	}

}
