package com.project.megatravel.util.errors;

@SuppressWarnings("serial")
public class UnitIsBookedException extends Exception {

	public UnitIsBookedException() {
		super("Soba je zauzeta za termin koji ste vi zeleli");
	}
}
