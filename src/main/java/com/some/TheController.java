package com.some;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TheController {

	@Autowired
	RepositoryDAO repositoryDAO;
	
	@RequestMapping(method=RequestMethod.POST,value="/insert")
	public ResponseEntity insert(@RequestBody AliceInWords aliceInWords){
		if(aliceInWords != null && !StringUtils.isEmpty(aliceInWords.getWord()) && !StringUtils.isEmpty(aliceInWords.getWord())){
			int numberx = repositoryDAO.insert(aliceInWords);
			return ResponseEntity.ok("Successfully Inserted "+numberx+" Rows");
			
		}else{
			return ResponseEntity.ok().body("Invalid Input");
		}
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/get/{word}")
	public ResponseEntity get(@PathVariable String word){
		if(!StringUtils.isEmpty(word)){
			String meaning = repositoryDAO.getMeaning(word);
			return ResponseEntity.ok("meaning");
			
		}else{
			return ResponseEntity.ok().body("Invalid Input");
		}
	}
	
	
	@RequestMapping(method=RequestMethod.GET,value="/getAll")
	public ResponseEntity getAll(){
			List<AliceInWords> theList = repositoryDAO.getAll();
			return ResponseEntity.ok(theList);
	}
}
