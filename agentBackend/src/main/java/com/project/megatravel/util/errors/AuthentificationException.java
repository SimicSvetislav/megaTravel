package com.project.megatravel.util.errors;

@SuppressWarnings("serial")
public class AuthentificationException extends Exception {

	public AuthentificationException() {
		super("Nemate prava da pristupate glavnoj aplikaciji");
	}
}
