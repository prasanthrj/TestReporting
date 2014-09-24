package com.qa.reporting;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.net.ssl.TrustManager;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.qa.logging.Reporter;



public class ZAPI {

	/** Status IDs enum */
	public enum Status {
		PASS(1), FAIL(2), WIP(3), BLOCKED(4);
		private final int value;

		private Status(final int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

	/** URLS */
	// private static final String BASE_URL = "http://stage-jira.lqdt.com/jira";
	// private static final String ZAPI_URL = BASE_URL + "/rest/zapi/latest/";

	private static  String BASE_URL = "http://localhost:8080/";
	private static  String ZAPI_URL = BASE_URL + "rest/zapi/latest/";
	private static String usernamepwd = "";

	/** HTTP Proxy details */
	private static final boolean USE_PROXY = false;
	private static final String PROXY_IP = "xxx.xxx.xxx.xxx";
	private static final int PROXY_PORT = 8080;

	private static final HttpHost HTTP_HOST_PROXY = new HttpHost(PROXY_IP,
			PROXY_PORT);
	private static final Proxy PROXY = new Proxy(Proxy.Type.HTTP,
			new InetSocketAddress(PROXY_IP, PROXY_PORT));

	/** JIRA credentials: format "username:password" or "" for none. */
	private static String CREDENTIALS = "prasanth.admin:Temp1234";
	
	
	
	// ================================================================================
	// setup URL and ZAPI URL
	// ================================================================================

	
    public ZAPI()
	
	{
    	
    	//System.setProperty("jsse.enableSNIExtension", "false");
    	
    	this.ZAPI_URL = new LoggingProperties()
		   .loadLoggingProperties(Reporter.LOG_PROPERTY_PATH+"//Logging.properties").getProperty("ZAPI_URL");
    	
    	this.BASE_URL = new LoggingProperties()
		   .loadLoggingProperties(Reporter.LOG_PROPERTY_PATH+"//Logging.properties").getProperty("BASE_URL");
    	
    	this.CREDENTIALS = new LoggingProperties()
		   .loadLoggingProperties(Reporter.LOG_PROPERTY_PATH+"//Logging.properties").getProperty("CREDENTIALS");
    	
		 
	}
    
    
	/* Get project ID */

	public static String getLatestIssue(String projectName) throws IOException,
			JSONException {
		String projectID = "";

		final JSONObject projectJsonObj = httpGetJSONObject(BASE_URL
				+ "rest/api/latest/issue/EM-1949");
		if (null == projectJsonObj) {
			throw new IllegalStateException("No Projects found");
		}

		JSONArray projects = (JSONArray) projectJsonObj.get("options");

		// Iterate over versions
		for (int i = 0; i < projects.length(); i++) {
			final JSONObject obj2 = projects.getJSONObject(i);
			// If label matches specified version name
			if (obj2.getString("label").equals(projectName)) {
				// Return the ID for this version
				return obj2.getString("value");
			}
		}

		return projectID;
	}
	


	// ================================================================================
	// ZAPI methods
	// ================================================================================

	/* Get project ID */

	public static String getProjectID(String projectName) throws IOException,
			JSONException {
		String projectID = "";

		final JSONObject projectJsonObj = httpGetJSONObject(ZAPI_URL
				+ "util/project-list");
		if (null == projectJsonObj) {
			throw new IllegalStateException("No Projects found");
		}

		JSONArray projects = (JSONArray) projectJsonObj.get("options");

		// Iterate over versions
		for (int i = 0; i < projects.length(); i++) {
			final JSONObject obj2 = projects.getJSONObject(i);
			// If label matches specified version name
			if (obj2.getString("label").equals(projectName)) {
				// Return the ID for this version
				return obj2.getString("value");
			}
		}

		return projectID;
	}
	
	
	/* Get Execution List */

	public static Map<String, String> getExecutions(String cycleID) throws IOException,
			JSONException {
		
		Map<String,String> executionList = new HashMap<String,String>();

		final JSONObject executionJsonObj = httpGetJSONObject(ZAPI_URL
				+ "execution?cycleId="+cycleID);
		
		
		if (null == executionJsonObj) {
			throw new IllegalStateException("No executions found");
		}

		JSONArray executions = (JSONArray) executionJsonObj.get("executions");

		// Iterate over versions
		for (int i = 0; i < executions.length(); i++) {
			final JSONObject obj2 = executions.getJSONObject(i);
			// If label matches specified version name
			if (obj2.getString("cycleId").equals(cycleID)) {
				// Return the ID for this version
				executionList.put(obj2.getString("issueKey"),obj2.getString("id"));
				
			}
		}

		return executionList;
	}

	/* Get cycle ID */

	public static String getCycleID(String versionID, String cycleName)
			throws IOException, JSONException {
		String cycleNumber = "";

		final JSONObject projectJsonObj = httpGetJSONObject(ZAPI_URL
				+ "cycle?versionId=" + versionID);
		if (null == projectJsonObj) {
			throw new IllegalStateException("No Cycles found");
		}

		String[] cycleID = projectJsonObj.getNames(projectJsonObj);

		for (int i = 0; i < cycleID.length; i++) {
			System.out.println("$$$" + cycleID[i]);
			
			try {
		
			final JSONObject obj2 = projectJsonObj.getJSONObject(cycleID[i]);	
			
		
			
			

			String actualCycleName = (String) obj2.get("name");

			if (actualCycleName.equalsIgnoreCase(cycleName)) {
				cycleNumber = cycleID[i];
			}
			
			}
			catch (Exception e)
			{
				
			}

		}

		return cycleNumber;
	}

	/**
	 * Gets the versionID for the project.
	 * 
	 * @param versionName
	 * @param projectId
	 * @throws IOException
	 * @return the ID for the specified Version in the specified Project
	 * @throws JSONException
	 */
	public static String getVersionID(final String versionName,
			final String projectId) throws IOException, JSONException {
		// Get list of versions on the specified project
		final JSONObject projectJsonObj = httpGetJSONObject(ZAPI_URL
				+ "util/versionBoard-list?projectId=" + projectId);
		if (null == projectJsonObj) {
			throw new IllegalStateException("JSONObject is null for projectId="
					+ projectId);
		}

		final JSONArray versionOptions = (JSONArray) projectJsonObj
				.get("unreleasedVersions");

		// Iterate over versions
		for (int i = 0; i < versionOptions.length(); i++) {
			final JSONObject obj2 = versionOptions.getJSONObject(i);
			// If label matches specified version name
			if (obj2.getString("label").equals(versionName)) {
				// Return the ID for this version
				return obj2.getString("value");
			}
		}

		throw new IllegalStateException("Version ID not found for versionName="
				+ versionName);
	}

	/**
	 * Updates the specified test execution
	 * 
	 * @param executionId
	 *            the ID of the execution
	 * @param status
	 *            a ZAPI.Status value
	 * @param comment
	 *            a comment for the test execution
	 * @throws IOException
	 *             put may throw IOException
	 * @throws JSONException
	 */

	public static JSONObject updateTestExecution(final String executionId,
			final Status status, final String comment) throws IOException,
			JSONException {
		// Construct JSON object
		final JSONObject obj = new JSONObject();
		obj.put("status", String.valueOf(status.getValue()));
		obj.put("comment", comment);

		return put(ZAPI_URL + "execution/" + executionId + "/execute", obj);
	}

	/**
	 * Adds attachment to an execution.
	 * 
	 * @param fileToUpload
	 *            - the file to attach
	 * @param executionId
	 * @throws RuntimeException
	 * @throws IOException
	 */
	public static void addAttachment(final File fileToUpload,
			final String executionId) throws RuntimeException, IOException {
		// set up proxy for http client
		final HttpClientBuilder clientBuilder = HttpClientBuilder.create();
		clientBuilder.useSystemProperties();
		if (USE_PROXY) {
			clientBuilder.setProxy(HTTP_HOST_PROXY);
		}
		final CloseableHttpClient httpClient = clientBuilder.build();

		final HttpPost httpPost = new HttpPost(ZAPI_URL
				+ "attachment?entityId=" + executionId
				+ "&entityType=EXECUTION");
		httpPost.setHeader("X-Atlassian-Token", "nocheck");

		if (!CREDENTIALS.isEmpty()) {
			final String encoding = new Base64().encodeToString(CREDENTIALS
					.getBytes());
			httpPost.setHeader("Authorization", "Basic " + encoding);
		}

		final MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.addBinaryBody("file", fileToUpload,
				ContentType.APPLICATION_OCTET_STREAM, fileToUpload.getName());
		final HttpEntity multipart = builder.build();
		httpPost.setEntity(multipart);

		final CloseableHttpResponse response = httpClient.execute(httpPost);
		final HttpEntity responseEntity = response.getEntity();
		if (null != responseEntity) {
			EntityUtils.consume(responseEntity);
		}

		// ensure file was uploaded correctly
		if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Error uploading file");
		}
	}

	/**
	 * Deletes all of the attachments on the specified execution
	 * 
	 * @param executionId
	 *            the id of the execution
	 * @throws IOException
	 *             delete may throw IOException
	 * @throws JSONException
	 */
	public static List<JSONObject> deleteAttachments(final String executionId)
			throws IOException, JSONException {
		final ArrayList<String> fileIds = new ArrayList<String>();
		// Note the IDs for the files currently attached to the execution
		final JSONObject obj = httpGetJSONObject(ZAPI_URL
				+ "attachment/attachmentsByEntity?entityId=" + executionId
				+ "&entityType=EXECUTION");
		if (null == obj) {
			throw new IllegalStateException("Response is null");
		}

		final JSONArray data = (JSONArray) obj.get("data");
		for (int i = 0; i < data.length(); i++) {
			final JSONObject fileData = data.getJSONObject(i);
			fileIds.add(fileData.getString("fileId"));
		}

		// Iterate over attachments
		final ArrayList<JSONObject> responses = new ArrayList<JSONObject>(
				data.length());
		for (final String fileId : fileIds) {
			responses.add(delete(ZAPI_URL + "attachment/" + fileId));
		}
		return responses;
	}

	// ================================================================================
	// HTTP request methods
	// ================================================================================

	/**
	 * Send GET request to the specified URL
	 * 
	 * @param url
	 * @throws IOException
	 * @throws JSONException
	 */
	private static JSONObject httpGetJSONObject(final String url)
			throws IOException, JSONException {
		return new JSONObject(httpGetJSONString(url));
	}

	/**
	 * Send GET request to the specified URL
	 * 
	 * @param url
	 * @throws IOException
	 * @throws JSONException
	 */
	private static JSONArray httpGetJSONArray(final String url)
			throws IOException, JSONException {
		return new JSONArray(httpGetJSONString(url));
	}

	/**
	 * Get a string from a url.
	 * 
	 * @param url
	 *            the URL to perform the GET method on
	 * @return a String representing the body of the http response
	 * @throws IOException
	 */
	private static String httpGetJSONString(final String url)
			throws IOException {
		final HttpURLConnection httpCon = createHttpCon(url, "GET");
		final BufferedReader br = new BufferedReader(new InputStreamReader(
				httpCon.getInputStream()));

		final StringBuffer httpResponse = new StringBuffer();
		String line = "";
		while (null != (line = br.readLine())) {
			httpResponse.append(line);
		}

		return httpResponse.toString();
	}

	/**
	 * Send a request with JSON content with the specified method
	 * 
	 * @param url
	 *            - the URL to send the request to
	 * @param obj
	 *            - the JSON content to send
	 * @param method
	 *            - e.g. PUT
	 * @throws IOException
	 * @throws JSONException
	 */
	private static JSONObject sendRequest(final String url,
			final JSONObject obj, final String method) throws IOException,
			JSONException {
		final HttpURLConnection httpCon = createHttpCon(url, method);

		if (null != obj) {
			final OutputStreamWriter out = new OutputStreamWriter(
					httpCon.getOutputStream());
			out.write(obj.toString());
			out.close();
		}

		final BufferedReader rd = new BufferedReader(new InputStreamReader(
				httpCon.getInputStream()));
		final StringBuffer result = new StringBuffer();
		String line = "";
		while (null != (line = rd.readLine())) {
			result.append(line);
		}
		return new JSONObject(result.toString());
	}

	/**
	 * Send PUT request to the specified URL
	 * 
	 * @param url
	 *            - the URL to send the request to
	 * @param obj
	 *            - the JSON content to send
	 * @throws IOException
	 * @throws JSONException
	 */
	private static JSONObject put(final String url, final JSONObject obj)
			throws IOException, JSONException {
		return sendRequest(url, obj, "PUT");
	}

	/**
	 * Send POST request to the specified URL
	 * 
	 * @param url
	 *            - the URL to send the request to
	 * @param obj
	 *            - the JSON content to send
	 * @throws IOException
	 * @throws JSONException
	 */
	private static JSONObject post(final String url, final JSONObject obj)
			throws IOException, JSONException {
		return sendRequest(url, obj, "POST");
	}

	/**
	 * Send DELETE request to the specified URL
	 * 
	 * @param url
	 *            - the URL to send the request to
	 * @throws IOException
	 * @throws JSONException
	 */
	private static JSONObject delete(final String url) throws IOException,
			JSONException {
		return sendRequest(url, null, "DELETE");
	}

	/**
	 * Return a HttpURLConnection object for the specified URL and request
	 * method
	 * 
	 * @param url
	 *            the URL to connect to
	 * @param method
	 *            - e.g. GET
	 */
	private static HttpURLConnection createHttpCon(final String url,
			final String method) throws IOException {

		final HttpURLConnection httpCon;
		if (USE_PROXY) {
			httpCon = (HttpURLConnection) new URL(url).openConnection(PROXY);
		} else {
			httpCon = (HttpURLConnection) new URL(url).openConnection();
		}

		httpCon.setDoOutput(true);
		httpCon.setRequestMethod(method);

		if (!CREDENTIALS.isEmpty()) {
			final String encoding = new Base64().encodeToString(CREDENTIALS
					.getBytes());
			httpCon.setRequestProperty("Authorization", "Basic " + encoding);
		}

		httpCon.setRequestProperty("Content-type", "application/json");

		return httpCon;
	}
}
