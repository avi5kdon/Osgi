package com.some;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TheController {

	public void show() {
	System.out.println("GAME");
	}
	
	@RequestMapping("/info")
	public String getInfo(){
		return "MF_INFO";
	}
	
	public static void main(String[] args) {
		int n = 3;
		int[] array = {3,2,6};
		int count=0;
		for(int i=0;i<n;i++){
			for(int j=0; j<n; j++){
				
				if(j==i){
					continue;
				}
				if(array[j]%array[i]==0){
					count++;
				}

				
			}
		}
	System.out.println(count);
	}
}
