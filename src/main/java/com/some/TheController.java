package com.some;

import java.util.List;

import javax.sql.DataSource;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin("*")
public class TheController {

	@Autowired
	RepositoryDAO repositoryDAO;
	
	@Autowired
	ResponseBean responseBean;
	
	@Autowired
	HttpHeaders httpHeaders;
	
	@RequestMapping(method=RequestMethod.POST,value="/insert")
	public ResponseEntity insert(@RequestBody AliceInWords aliceInWords){
		if(aliceInWords != null && !StringUtils.isEmpty(aliceInWords.getWord()) && !StringUtils.isEmpty(aliceInWords.getWord())){
			int numberx = repositoryDAO.insert(aliceInWords);
			responseBean.setResponse("Successfully Inserted "+numberx+" Rows");
			
		}else{
			responseBean.setResponse("Invalid Input");
		}
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return ResponseEntity.ok().headers(httpHeaders).body(responseBean);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/get/{word}")
	public ResponseEntity get(@PathVariable String word){
		if(!StringUtils.isEmpty(word)){
			String meaning = repositoryDAO.getMeaning(word);
			responseBean.setResponse(meaning);
			
		}else{
			responseBean.setResponse("Invalid Input");
		}
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return ResponseEntity.ok().headers(httpHeaders).body(responseBean);
	}
	
	
	@RequestMapping(method=RequestMethod.GET,value="/getAll")
	public ResponseEntity getAll(){
			List<AliceInWords> theList = repositoryDAO.getAll();
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
			return ResponseEntity.ok().headers(httpHeaders).body(theList);
	}
	
	
	@RequestMapping(method=RequestMethod.POST,value="/remove/{word}")
	public ResponseEntity insert(@PathVariable String word){
		if(!StringUtils.isEmpty(word)){
			String message = repositoryDAO.remove(word);
			return ResponseEntity.ok(message);
			
		}else{
			return ResponseEntity.ok().body("Invalid Input");
		}
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/findNSave/{word}")
	public ResponseEntity<String> findNSave(@PathVariable String word){
		if(!StringUtils.isEmpty(word)){
			try{
			Document doc = Jsoup.connect("https://www.vocabulary.com/dictionary/"+word).get();
			String response = doc.getElementsByClass("short").text();
			if(!StringUtils.isEmpty(response)){
				AliceInWords aliceInWords = new AliceInWords();
				aliceInWords.setWord(StringUtils.capitalize(word));
				aliceInWords.setMeaning(response);
				int values = repositoryDAO.insert(aliceInWords);
				System.out.println("Inserted Values "+values);
			}
			return ResponseEntity.ok(response);
			}catch(Exception e){
				return ResponseEntity.ok(e.getMessage());
			}
			
		}else{
			return ResponseEntity.badRequest().body("Fuch off");
		}
		
	}
}
