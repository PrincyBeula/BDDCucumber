package com.framework.core;

import static java.awt.Desktop.getDesktop;

import java.io.File;
import java.io.IOException;

public class Helper {

	public static void OpenTestExecutionReport() throws IOException {
		String reportPath = System.getProperty("user.dir") + File.separator + "target" + File.separator + "cucumber-reports" + File.separator + "html" + File.separator + "index.html";
		File htmlFile = new File(reportPath);
		getDesktop().browse(htmlFile.toURI());
	}
	
	public static void handleTestFaliure(Object exe, String className) {
		String failMessage = "";
		if (exe instanceof Error) {
			failMessage = ((Error) exe).getMessage();
			((Error) exe).printStackTrace();
		}
		if (exe instanceof Exception) {
			failMessage = ((Exception) exe).getMessage();
			((Exception) exe).printStackTrace();
		}
		LogGenerator.fatal(exe.toString());
		LogGenerator.endLog(className);
		throw new RuntimeException(failMessage);
	}
}
