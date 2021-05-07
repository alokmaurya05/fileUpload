package com.awin.resources;

import java.time.LocalDate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.awin.buildResponse.Response;
import com.awin.entity.JobApplication;
import com.awin.services.JobApplicationService;
import com.awin.util.FileUploadUtil;


@RestController
@RequestMapping (value = "/api/v1")
public class JobApplicationResource {
	
	public static final Logger logger = LogManager.getLogger(JobApplicationResource.class);
	private static final String JOB_APPLICATION_DIRECTORY ="job-Applicant/";
	@Autowired
	private JobApplicationService service;
	
	 /**
     * This method Upload File & jobApplication
	* @return ResponseEntity<String>
	*/
	@PostMapping (value = "/upload")
    public ResponseEntity<String> Upload(JobApplication jobApplication, @RequestParam("resume") MultipartFile multipartFile) throws Exception
    {
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		String uploadDir = null ;
		logger.info("Upload started file ", fileName);
        try {
        	 service.save(jobApplication);
    		uploadDir = JOB_APPLICATION_DIRECTORY+jobApplication.getJobtitle()+"_" + LocalDate.now();
			FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		} catch (Exception e) {
		  logger.error("File upload can not be procces ", e.getMessage());
		  throw new Exception(e.getMessage());
		}
        logger.info("File Uploaded  ", fileName," in directory ",uploadDir);
		return Response.success("FileUpload Succefully", HttpStatus.CREATED);
    	
    }

}
