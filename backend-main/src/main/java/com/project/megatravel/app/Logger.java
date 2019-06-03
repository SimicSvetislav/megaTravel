package com.project.megatravel.app;

public class Logger {
	
	private Logger() { }
	
	public static void info(String text) {
		
		System.out.print("[");
		System.out.print("\033[32mINFO");
		System.out.print("\033[0m");
		System.out.print("] : ");
		System.out.println(text);
		
	}
	
	public static void error(String text) {
		
		System.out.print("[");
		System.out.print("\033[31mERROR");
		System.out.print("\033[0m");
		System.out.print("] : ");
		System.out.println(text);
		
	}
	
	public static void ok(String text) {
		
		System.out.print("[");
		System.out.print("\033[34mOK");
		System.out.print("\033[0m");
		System.out.print("] : ");
		System.out.println(text);
		
	}
	
	public static void warning(String text) {
		
		System.out.print("[");
		System.out.print("\033[33mWARNING");
		System.out.print("\033[0m");
		System.out.print("]\t : ");
		System.out.println(text);
		
	}

}
