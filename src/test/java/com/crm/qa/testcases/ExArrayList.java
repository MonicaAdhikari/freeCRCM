package com.crm.qa.testcases;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

public class ExArrayList {

	@Test
	public static void printArrayList() {
       
        List<String> arrWords = new ArrayList<String>();
        
        // append
        arrWords.add("school");
        System.out.println("1> arrWords = " + arrWords);
 
        arrWords.add(0, "at");
        System.out.println("2> Insert via index : arrWords = " + arrWords);
        System.out.println(arrWords);
 
        //Upadting index 1 value
        arrWords.set(1, "work");
        System.out.println("3> Update via index : arrWords = " + arrWords);
 
        arrWords.remove(0);
        System.out.println("4> Remove via index : arrWords = " + arrWords);
  
    }

}
