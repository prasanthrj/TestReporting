package com.qa.test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

import org.json.JSONException;
import org.testng.annotations.Test;

import com.qa.logging.Results;
import com.qa.reporting.ZAPI;
import com.qa.reporting.ZAPI.Status;

public class testZapi {

	ZAPI objZ = new ZAPI();

	public String testZapii(String projectName, String projectVersion,
			String cycleName, String zypTestCaseID) throws IOException,
			JSONException {
		// System.setProperty("jsse.enableSNIExtension", "false");

		String projectID = objZ.getProjectID(projectName);

		System.out.println("Project ID is " + projectID);

		String versionID = objZ.getVersionID(projectVersion, projectID);
		
		System.out.println("version ID is " + versionID);

		String cycleID = objZ.getCycleID(versionID, cycleName);
		
		System.out.println("cycleID ID is " + cycleID);

		Map<String, String> totalTestcases = objZ.getExecutions(cycleID);

		String testExecutionID = totalTestcases.get(zypTestCaseID);

		return testExecutionID;

	}

	@Test
	public void updateZapiReport() throws IOException, JSONException {

		String texeid = testZapii("TestAutomationProject", "V1.0",
				"Test Prasanth", "TES-3");

		objZ.updateTestExecution(texeid, Status.PASS, "Actual execution result");

	}
}
