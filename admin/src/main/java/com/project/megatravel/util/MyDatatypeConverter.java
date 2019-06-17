package com.project.megatravel.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * Konverter klasa za java.utilDate, parse odnosno 
 * print metode moraju biti statičke.
 * Klasa je preuzeta iz materijala sa četvrtih vežbi.
 *
 */
public class MyDatatypeConverter {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * Metoda parsira tekstualnu vrednost datuma.
	 * @param value tekstualni reprezent datuma
	 * @return Vraća datumsku vrednost tekstualnog reprezenta.
	 */
	public static Date parseDate(String value) {
		try {
			return dateFormat.parse(value);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * Metoda štampa datum u definisanom formatu.
	 * @param value datumska vrednost
	 * @return Vraća tekstualni reprezent datuma.
	 */
	public static String printDate(Date value) {
		if (value != null)
			return dateFormat.format(value);
		else
			return null;

	}
	
	/**
	 * Metoda parsira tekstualnu vrednost long broja.
	 * @param value tekstualni reprezent long broja
	 * @return Vraća long vrednost tekstualnog reprezenta.
	 */
	public static Long parseLong(String value) {
		try {
			return Long.parseLong(value);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * Metoda štampa long broj.
	 * @param value long vrednost
	 * @return Vraća tekstualni reprezent long broja.
	 */
	public static String printLong(Long value) {
		if (value != null)
			return value.toString();
		else
			return null;

	}
	
	/**
	 * Metoda štampa ceo broj.
	 * @param value vrednost celog broja
	 * @return Vraća tekstualni reprezent celog broja.
	 */
	public static String printInteger(Integer value) {
		if (value != null)
			return value.toString();
		else
			return null;

	}
	
	/**
	 * Metoda parsira tekstualnu vrednost celog broja.
	 * @param value tekstualni reprezent celog broja
	 * @return Vraća celobrojnu vrednost tekstualnog reprezenta.
	 */
	public static Integer parseInteger(String value) {
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		}

	}
	
	/**
	 * Metoda štampa razlomljeni broj.
	 * @param value vrednost razlomljenog
	 * @return Vraća tekstualni reprezent razlomljenog broja.
	 */
	public static String printDouble(Double value) {
		if (value != null)
			return value.toString();
		else
			return null;

	}
	
	/**
	 * Metoda parsira tekstualnu vrednost razlomljenog broja.
	 * @param value tekstualni reprezent razlomljenog broja
	 * @return Vraća razlomljenu vrednost tekstualnog reprezenta.
	 */
	public static Double parseDouble(String value) {
		try {
			return Double.parseDouble(value);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		}

	}
}
