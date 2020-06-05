package com.tasks.webstaurantstore.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EnvironmentCleanUp {

	public static void envCleanup() {

		clearSession();
		Process p = null;
		try {
			p = Runtime.getRuntime().exec("tasklist");
		} catch (IOException e) {
			e.printStackTrace();
		}
		InputStreamReader ISR = new InputStreamReader(p.getInputStream());
		BufferedReader reader = new BufferedReader(ISR);
		String process = "chrome.exe";
		String DType = "chrome";


					try {
						Runtime.getRuntime().exec("taskkill /F /IM " + process);
						Runtime.getRuntime().exec("taskkill /im chromedriver.exe /f");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					// System.out.println(DType + " process is closed.");
	}

	public static void clearSession() {
		// System.out.println("Clearing cache");
		String FILE_NAME = "C:\\workspace\\dmles-ivv\\cleanupscripts\\chromeclr.bat";
		String path = FILE_NAME;
		try {
			Process p = Runtime.getRuntime().exec("cmd /c start " + path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
