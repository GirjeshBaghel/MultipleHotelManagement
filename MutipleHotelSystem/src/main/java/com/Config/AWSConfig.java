package com.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import org.springframework.beans.factory.annotation.Value;
@Configuration
public class AWSConfig {
	
	 @Value("${aws.accesskey}")
	    private String accessKey;

	    @Value("${aws.secretkey}")
	    private String accessSecret;
	    
	    @Bean
	    public AmazonS3 s3Client() {
	        AWSCredentials credentials = new BasicAWSCredentials(accessKey, accessSecret);
	        return AmazonS3ClientBuilder.standard()
	                .withCredentials(new AWSStaticCredentialsProvider(credentials))
	                .withRegion(Regions.AP_SOUTH_1).build();
	    }
	    
}
