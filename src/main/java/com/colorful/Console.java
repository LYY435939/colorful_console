package com.colorful;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Console {
	public static final int RESET = 0;
	public static final int BOLD = 1;
	public static final int ITALIC = 3;
	public static final int UNDERLINE = 4;
	public static final int UNDERLINE_BOLD = 21;
	public static final int REVERSE = 7;
	public static final int STRIKETHROUGH = 9;
	public static final int black = 30;
	public static final int red = 31;
	public static final int green = 32;
	public static final int yellow = 33;
	public static final int blue = 34;
	public static final int purple = 35;
	public static final int cyan = 36;
	public static final int gray = 37;
	public static final int BLACK = 40;
	public static final int RED = 41;
	public static final int GREEN = 42;
	public static final int YELLOW = 43;
	public static final int BLUE = 44;
	public static final int PURPLE = 45;
	public static final int CYAN = 46;
	public static final int GRAY = 47;
	
	public static void main(String[] args) {
		error("this is an error");
		warn("this is a warning");
		success("this is a success");
		info("this is an info");
	}
	
	private static String getAttributes(int... attributes) {
		StringBuilder buffer = new StringBuilder();
		buffer.append("\033[");
		buffer.append(0).append(";");
		for (int attr : attributes) {
			buffer.append(attr).append(";");
		}
		buffer.deleteCharAt(buffer.length() - 1);
		buffer.append("m");
		return buffer.toString();
	}
	
	private static void flush() {
		System.out.print("\033[0m");
	}
	
	public static void print(Object output, int... attributes) {
		String attr = getAttributes(attributes);
		System.out.print(attr);
		System.out.print(output);
		flush();
	}
	
	public static void println(Object output, int... attributes) {
		print(output, attributes);
		System.out.println();
	}
	
	public static void error(Object output) {
		print("ERROR:", RED, BOLD);
		print(" ");
		println(output, red);
	}
	
	public static void warn(Object output) {
		print("WARN:", YELLOW, BOLD);
		print(" ");
		println(output, yellow);
	}
	
	public static void info(Object output) {
		print("INFO:", BLUE, BOLD);
		print(" ");
		println(output, blue);
	}
	
	public static void success(Object output) {
		print("SUCCESS:", GREEN, BOLD);
		print(" ");
		println(output, green);
	}
	
	public static void printStackTrace(Exception e) {
		error(e.getMessage());
		String stackTrace = getStackTrace(e);
		println(stackTrace, red);
	}
	
	private static String getStackTrace(Exception exception) {
		if (exception == null) return "null";
		StringWriter stringWriter = new StringWriter();
		exception.printStackTrace(new PrintWriter(stringWriter));
		return stringWriter.toString();
	}
	
	
}
