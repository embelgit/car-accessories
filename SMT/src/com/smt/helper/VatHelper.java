package com.smt.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smt.dao.CarEntryDao;
import com.smt.dao.VatEntryDao;
import com.smt.hibernate.CarEntry;
import com.smt.hibernate.VatEntry;

public class VatHelper {

	public void createVat(HttpServletRequest request, HttpServletResponse response) {
		String vatName = request.getParameter("vatName");
		String vatPer = request.getParameter("vatPer");

		VatEntry vat = new VatEntry();
		
		if(vatName != null){
			vat.setVatName(vatName);
		}
		else{
			vat.setVatName(vatName);
		}
		
		if(vatPer != null){
			vat.setVatPercentage(Double.parseDouble(vatPer));
		}
		else{
			vat.setVatPercentage(0d);
		}

		VatEntryDao dao = new VatEntryDao();
		dao.registerVatEntry(vat);

	}

}
