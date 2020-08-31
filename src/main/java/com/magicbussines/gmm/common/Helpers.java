package com.magicbussines.gmm.common;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
@Service
public class Helpers {

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
}
