package com.accolite.Controller;

import com.accolite.Entity.AuditInfo;
import com.accolite.Entity.RatesData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.Service.DataFetchService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class DataFetchController
{
	@Autowired
	private DataFetchService fetchService;
	
	@GetMapping("/fetch")
	public ResponseEntity<List<AuditInfo>> FetchApiData(@RequestParam Optional<String> date) {
//		AuditInfo[] in;
		List<AuditInfo> li;
		if(date.isPresent())
		{
			li=fetchService.getApiData(date.get());
		}
		else
		{
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			li=fetchService.getApiData(simpleDateFormat.format(new Date()));
		}
		return ResponseEntity.ok().body(li);

	}
}