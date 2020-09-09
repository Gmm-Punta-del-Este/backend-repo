package com.magicbussines.gmm.common;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.zaxxer.hikari.util.SuspendResumeLock;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
 
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
@Component
public class Helpers {
	
	//CHECKEA COMO LO MENEA
	//CHEKEA SI {DATO} ESTA ENTRE {INICIO} Y {FIN} **INCLUSIVE**
	public boolean checkRange(LocalDateTime dato, LocalDateTime inicio, LocalDateTime fin) {
		// TODO Auto-generated method stub
			boolean pertenece = false;
		
			LocalDate timestampF = dato.toLocalDate();
			LocalTime timestampT = dato.toLocalTime();
			//timestamppF es el de cada row
			if ((timestampF.equals(inicio.toLocalDate())) || (timestampF.equals(fin.toLocalDate()))){
				//o es el mismo dia de los limites
				if( (timestampT.equals(inicio.toLocalTime())) || (timestampT.equals(fin.toLocalTime())) ) {
					pertenece = true;
					return pertenece;
				}else if( (timestampT.isAfter(inicio.toLocalTime())) && (timestampT.isBefore(fin.toLocalTime())) ) {
					pertenece = true;
					return pertenece;
				}
			}else if ((timestampF.isAfter(inicio.toLocalDate())) && (timestampF.isBefore(fin.toLocalDate()))){
				pertenece = true;
				return pertenece;
			}
			return pertenece;		
		}
	
	@SuppressWarnings("unchecked")
	public JSONArray populateTable(String patinho, String fileName, String fieldName) {
		JSONParser parser = new JSONParser();
		JSONArray arrayJson = new JSONArray();
		try {
			// se le pasara la ruta via {path}
//			Path path = Paths.get("F:/CURE/JavaEEE/Utils/Routes.txt");
			Object obj = parser.parse(new FileReader(patinho+"/"+fileName+".json"));
			//Object obj = parser.parse(new FileReader("F:/CURE/PHP/Practicos/esevendb/database/"+fileName+".json"));
			// A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
			JSONObject jsonObject = (JSONObject) obj;
			arrayJson = (JSONArray) jsonObject.get(fieldName);
			System.out.println(">> -----------------------------------------------");
			System.out.println(">> -----------------------------------------------");
			System.out.println(">> JSON FOUNDED");
			System.out.println(">> JSON DECODING");
			System.out.println(">> OBJECT CREATED");
			System.out.println(">> OBJECT ASSIGNED");
			System.out.println(">> RETURN: JSONARRAY - OK");
			System.out.println(">> -----------------------");
			System.out.println(">> FileName: "+fileName+".json || "+" FieldName: "+fieldName+" || Array has been created. Peace OUT.");
			
			return arrayJson;
		}catch (Exception e){
			System.out.println(e.toString());
			return arrayJson;
		}
	}
}
