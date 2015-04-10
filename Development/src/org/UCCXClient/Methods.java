package org.UCCXClient;

import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;

public class Methods {

	public static String getKeys(String which) {
		int[] case_ = { 1, 2, 3, 4, 5, 6 };
		String[] which_ = { "ip", "user", "pass", "puship", "pushuser",
				"pushpass" };
		int use = 0;
		for (int i = 0; i < which_.length; i++) {
			if (which == which_[i]) {
				use = case_[i];
			}
		}
		switch (use) {
		case 1:
			return Variables.ip;
		case 2:
			return Variables.user;
		case 3:
			return Variables.pass;
		case 4:
			return Variables.puship;
		case 5:
			return Variables.pushuser;
		case 6:
			return Variables.pushpass;
		}
		return null;
	}

	public static void listFilesForFolder(final File folder) throws IOException {
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry);
			} else {
				MsgLog.write(fileEntry.getName());
			}
		}
	}

	public static void writeFile(String fileName, String xmlData) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
			out.write(xmlData);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
	}

	public static boolean testConnection() throws IOException {
		int responseCode = 0;
		String responseMessage = "";
		String authString = Methods.getKeys("user") + ":"
				+ Methods.getKeys("pass");
		final byte[] authEncBytes = authString.getBytes(StandardCharsets.UTF_8);
		String Base64String = Base64.getEncoder().encodeToString(authEncBytes);
		SSLContext sc = null;
		try {
			sc = SSLContext.getInstance("TLS");
		} catch (NoSuchAlgorithmException e3) {

			e3.printStackTrace();
		}
		try {
			sc.init(null,
					new TrustManager[] { new TrustAllX509TrustManager() },
					new java.security.SecureRandom());
		} catch (KeyManagementException e2) {

			e2.printStackTrace();
		}
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
			@Override
			public boolean verify(String string, SSLSession ssls) {
				return true;
			}
		});
		String https_url = "https://" + Methods.getKeys("ip")
				+ "/adminapi/team";
		URL url;
		try {

			url = new URL(https_url);
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
			con.setDoInput(true);
			con.setDoOutput(true);

			con.setRequestProperty("Authorization", "Basic " + Base64String);
			// Response Log
			BufferedReader br = new BufferedReader(new InputStreamReader(
					con.getInputStream()));

			while ((br.readLine()) != null) {
			}
			br.close();

			responseCode = con.getResponseCode();
			responseMessage = con.getResponseMessage();
			con.disconnect();
		} catch (MalformedURLException e1) {
			MsgLog.write("Connection failed! \n");
			e1.printStackTrace();
		} catch (IOException e1) {
			MsgLog.write("Connection failed! \n");
			e1.printStackTrace();
		}
		if (responseCode == HttpURLConnection.HTTP_ACCEPTED
				|| responseCode == HttpURLConnection.HTTP_CREATED
				|| responseCode == HttpURLConnection.HTTP_OK) {
			MsgLog.write("Connection successfully established! \n");
			UCCXLogGUI.setLogText(Variables.logString);
			MsgLog.write("___________________________________________ \n");
			UCCXLogGUI.setLogText(Variables.logString);
			return true;
		} else {
			MsgLog.write(responseMessage + " \n");
			UCCXLogGUI.setLogText(Variables.logString);
			return false;
		}
	}

	public static boolean testPushConnection() throws IOException {
		int responseCode = 0;
		String responseMessage = "";
		String authString = Methods.getKeys("pushuser") + ":"
				+ Methods.getKeys("pushpass");
		final byte[] authEncBytes = authString.getBytes(StandardCharsets.UTF_8);
		String Base64String = Base64.getEncoder().encodeToString(authEncBytes);
		SSLContext sc = null;
		try {
			sc = SSLContext.getInstance("TLS");
		} catch (NoSuchAlgorithmException e3) {

			e3.printStackTrace();
		}
		try {
			sc.init(null,
					new TrustManager[] { new TrustAllX509TrustManager() },
					new java.security.SecureRandom());
		} catch (KeyManagementException e2) {

			e2.printStackTrace();
		}
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
			@Override
			public boolean verify(String string, SSLSession ssls) {
				return true;
			}
		});
		String https_url = "https://" + Methods.getKeys("puship")
				+ "/adminapi/team";
		URL url;
		try {

			url = new URL(https_url);
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setRequestProperty("Authorization", "Basic " + Base64String);
			// Response Log
			BufferedReader br = new BufferedReader(new InputStreamReader(
					con.getInputStream()));

			while ((br.readLine()) != null) {
			}
			br.close();

			responseCode = con.getResponseCode();
			responseMessage = con.getResponseMessage();
			con.disconnect();
		} catch (MalformedURLException e1) {
			MsgLog.write("Connection failed! \n");
			e1.printStackTrace();
		} catch (IOException e1) {
			MsgLog.write("Connection failed! \n");
			e1.printStackTrace();
		}
		if (responseCode == HttpURLConnection.HTTP_ACCEPTED
				|| responseCode == HttpURLConnection.HTTP_CREATED
				|| responseCode == HttpURLConnection.HTTP_OK) {
			MsgLog.write("Connection successfully established! \n");
			UCCXLogGUI.setLogText(Variables.logString);
			MsgLog.write("___________________________________________ \n");
			UCCXLogGUI.setLogText(Variables.logString);
			return true;
		} else {
			MsgLog.write(responseMessage + " \n");
			UCCXLogGUI.setLogText(Variables.logString);
			return false;
		}
	}
	
	public static boolean getResourceRefs() throws IOException {
		int responseCode = 0;
		String responseMessage = "";
		String authString = Methods.getKeys("user") + ":"
				+ Methods.getKeys("pass");
		final byte[] authEncBytes = authString.getBytes(StandardCharsets.UTF_8);
		String Base64String = Base64.getEncoder().encodeToString(authEncBytes);
		String input = "";
		String response = "";
		SSLContext sc = null;
		try {
			sc = SSLContext.getInstance("TLS");
		} catch (NoSuchAlgorithmException e3) {

			e3.printStackTrace();
		}
		try {
			sc.init(null,
					new TrustManager[] { new TrustAllX509TrustManager() },
					new java.security.SecureRandom());
		} catch (KeyManagementException e2) {

			e2.printStackTrace();
		}
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
			@Override
			public boolean verify(String string, SSLSession ssls) {
				return true;
			}
		});
		String https_url = "https://" + Methods.getKeys("ip")
				+ "/adminapi/resource";
		URL url;
		try {

			url = new URL(https_url);
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
			con.setDoInput(true);
			con.setDoOutput(true);

			/*
			 * con.setRequestProperty("Authorization",
			 * "Basic YXBwYWRtaW46TGluZWNvZGViOHpz");
			 */
			con.setRequestProperty("Authorization", "Basic " + Base64String);
			// Response Log
			BufferedReader br = new BufferedReader(new InputStreamReader(
					con.getInputStream()));

			while ((input = br.readLine()) != null) {
				//
				response = input;
			}
			br.close();
			Variables.resourceRefLinks = Methods.substringsBetween(response,
					"<self>", "</self>");
			MsgLog.write("Total Resources: " + Variables.resourceRefLinks.length
					+ " \n");
			for (int i = 0; i < Variables.resourceRefLinks.length; i++) {
				MsgLog.write("Resource " + (i + 1) + " Link: "
						+ Variables.resourceRefLinks[i] + " \n");
				UCCXLogGUI.setLogText(Variables.logString);
			}
			responseCode = con.getResponseCode();
			responseMessage = con.getResponseMessage();
			con.disconnect();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if (responseCode == HttpURLConnection.HTTP_ACCEPTED
				|| responseCode == HttpURLConnection.HTTP_CREATED
				|| responseCode == HttpURLConnection.HTTP_OK) {
			MsgLog.write("___________________________________________ \n");
			UCCXLogGUI.setLogText(Variables.logString);
			return true;
		} else {
			MsgLog.write(responseMessage + " \n");
			UCCXLogGUI.setLogText(Variables.logString);
			return false;
		}
	}

	public static boolean getDetailedResourceData() throws IOException {
		int responseCode = 0;
		String responseMessage = "";
		//Variables.teamTableRows = new String[Variables.teamRefLinks.length][7];
		String authString = Methods.getKeys("user") + ":"
				+ Methods.getKeys("pass");
		final byte[] authEncBytes = authString.getBytes(StandardCharsets.UTF_8);
		String Base64String = Base64.getEncoder().encodeToString(authEncBytes);
		String input = "";
		String response = "";
		SSLContext sc = null;
		try {
			sc = SSLContext.getInstance("TLS");
		} catch (NoSuchAlgorithmException e3) {

			e3.printStackTrace();
		}
		try {
			sc.init(null,
					new TrustManager[] { new TrustAllX509TrustManager() },
					new java.security.SecureRandom());
		} catch (KeyManagementException e2) {

			e2.printStackTrace();
		}
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
			@Override
			public boolean verify(String string, SSLSession ssls) {
				return true;
			}
		});

		if (Variables.resourceRefLinks.length == 0) {
			MsgLog.write("No Refrence Links, kill thread!");
		} else {

			for (int r = 0; r < Variables.resourceRefLinks.length; r++) {

				String https_url = Variables.resourceRefLinks[r]
						.replace("+", "%20");
				URL url;
				try {

					url = new URL(https_url);
					HttpsURLConnection con = (HttpsURLConnection) url
							.openConnection();
					con.setDoInput(true);
					con.setDoOutput(true);

					con.setRequestProperty("Authorization", "Basic "
							+ Base64String);
					// Response Log
					BufferedReader br = new BufferedReader(
							new InputStreamReader(con.getInputStream()));

					while ((input = br.readLine()) != null) {
						response = input;
					}
					br.close();

					Variables.rUserId = Methods.substringsBetween(response,
							"<userID>", "</userID>");
					String workingDir = System.getProperty("user.dir");
					String absoluteFilePath = "";
					String your_os = System.getProperty("os.name")
							.toLowerCase();
					if (your_os.indexOf("win") >= 0) {
						absoluteFilePath = workingDir + "\\resources\\"
								+ Variables.rUserId[0] + ".xml";
					} else if (your_os.indexOf("nix") >= 0
							|| your_os.indexOf("nux") >= 0
							|| your_os.indexOf("mac") >= 0) {
						absoluteFilePath = workingDir + "/resources/"
								+ Variables.rUserId[0] + ".xml";
					} else {
						absoluteFilePath = workingDir + "/resources/"
								+ Variables.rUserId[0] + ".xml";
					}
					File myFile = new File(absoluteFilePath);
					File parentDir = myFile.getParentFile();
					if (!parentDir.exists())
						parentDir.mkdirs();
					Writer w = new OutputStreamWriter(new FileOutputStream(
							myFile), "UTF-8");
					w.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
							+ response);
					w.flush();
					w.close();

					MsgLog.write("Successfully retreieved data for resource "
							+ Integer.toString(r + 1) + " ["
							+ Variables.rUserId[0]
							+ "]" + "\n");
					UCCXLogGUI.setLogText(Variables.logString);
					responseCode = con.getResponseCode();
					responseMessage = con.getResponseMessage();
					con.disconnect();
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}

		}

		if (responseCode == HttpURLConnection.HTTP_ACCEPTED
				|| responseCode == HttpURLConnection.HTTP_CREATED
				|| responseCode == HttpURLConnection.HTTP_OK) {
			MsgLog.write("___________________________________________ \n");
			UCCXLogGUI.setLogText(Variables.logString);
			return true;
		} else {
			MsgLog.write(responseMessage + " \n");
			UCCXLogGUI.setLogText(Variables.logString);
			return false;
		}
	}

	public static boolean getTeamRefs() throws IOException {
		int responseCode = 0;
		String responseMessage = "";
		String authString = Methods.getKeys("user") + ":"
				+ Methods.getKeys("pass");
		final byte[] authEncBytes = authString.getBytes(StandardCharsets.UTF_8);
		String Base64String = Base64.getEncoder().encodeToString(authEncBytes);
		String input = "";
		String response = "";
		SSLContext sc = null;
		try {
			sc = SSLContext.getInstance("TLS");
		} catch (NoSuchAlgorithmException e3) {

			e3.printStackTrace();
		}
		try {
			sc.init(null,
					new TrustManager[] { new TrustAllX509TrustManager() },
					new java.security.SecureRandom());
		} catch (KeyManagementException e2) {

			e2.printStackTrace();
		}
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
			@Override
			public boolean verify(String string, SSLSession ssls) {
				return true;
			}
		});
		String https_url = "https://" + Methods.getKeys("ip")
				+ "/adminapi/team";
		URL url;
		try {

			url = new URL(https_url);
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
			con.setDoInput(true);
			con.setDoOutput(true);

			/*
			 * con.setRequestProperty("Authorization",
			 * "Basic YXBwYWRtaW46TGluZWNvZGViOHpz");
			 */
			con.setRequestProperty("Authorization", "Basic " + Base64String);
			// Response Log
			BufferedReader br = new BufferedReader(new InputStreamReader(
					con.getInputStream()));

			while ((input = br.readLine()) != null) {
				//
				response = input;
			}
			br.close();
			Variables.teamRefLinks = Methods.substringsBetween(response,
					"<self>", "</self>");
			MsgLog.write("Total Teams: " + Variables.teamRefLinks.length
					+ " \n");
			for (int i = 0; i < Variables.teamRefLinks.length; i++) {
				MsgLog.write("Team " + (i + 1) + " Link: "
						+ Variables.teamRefLinks[i] + " \n");
				UCCXLogGUI.setLogText(Variables.logString);
			}
			responseCode = con.getResponseCode();
			responseMessage = con.getResponseMessage();
			con.disconnect();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if (responseCode == HttpURLConnection.HTTP_ACCEPTED
				|| responseCode == HttpURLConnection.HTTP_CREATED
				|| responseCode == HttpURLConnection.HTTP_OK) {
			MsgLog.write("___________________________________________ \n");
			UCCXLogGUI.setLogText(Variables.logString);
			return true;
		} else {
			MsgLog.write(responseMessage + " \n");
			UCCXLogGUI.setLogText(Variables.logString);
			return false;
		}
	}

	public static boolean getDetailedTeamData() throws IOException {
		int responseCode = 0;
		String responseMessage = "";
		Variables.teamTableRows = new String[Variables.teamRefLinks.length][7];
		String authString = Methods.getKeys("user") + ":"
				+ Methods.getKeys("pass");
		final byte[] authEncBytes = authString.getBytes(StandardCharsets.UTF_8);
		String Base64String = Base64.getEncoder().encodeToString(authEncBytes);
		String input = "";
		String response = "";
		SSLContext sc = null;
		try {
			sc = SSLContext.getInstance("TLS");
		} catch (NoSuchAlgorithmException e3) {

			e3.printStackTrace();
		}
		try {
			sc.init(null,
					new TrustManager[] { new TrustAllX509TrustManager() },
					new java.security.SecureRandom());
		} catch (KeyManagementException e2) {

			e2.printStackTrace();
		}
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
			@Override
			public boolean verify(String string, SSLSession ssls) {
				return true;
			}
		});

		if (Variables.teamRefLinks.length == 0) {
			MsgLog.write("No Refrence Links, kill thread!");
		} else {

			for (int r = 0; r < Variables.teamRefLinks.length; r++) {

				String https_url = Variables.teamRefLinks[r]
						.replace("+", "%20");
				URL url;
				try {

					url = new URL(https_url);
					HttpsURLConnection con = (HttpsURLConnection) url
							.openConnection();
					con.setDoInput(true);
					con.setDoOutput(true);

					/*
					 * con.setRequestProperty("Authorization",
					 * "Basic YXBwYWRtaW46TGluZWNvZGViOHpz");
					 */
					con.setRequestProperty("Authorization", "Basic "
							+ Base64String);
					// Response Log
					BufferedReader br = new BufferedReader(
							new InputStreamReader(con.getInputStream()));

					while ((input = br.readLine()) != null) {
						response = input;
					}
					br.close();

					Variables.teamNames = Methods.substringsBetween(response,
							"<teamname>", "</teamname>");
					String workingDir = System.getProperty("user.dir");
					String absoluteFilePath = "";
					String your_os = System.getProperty("os.name")
							.toLowerCase();
					if (your_os.indexOf("win") >= 0) {
						absoluteFilePath = workingDir + "\\teams\\"
								+ Variables.teamNames[0] + ".xml";
					} else if (your_os.indexOf("nix") >= 0
							|| your_os.indexOf("nux") >= 0
							|| your_os.indexOf("mac") >= 0) {
						absoluteFilePath = workingDir + "/teams/"
								+ Variables.teamNames[0] + ".xml";
					} else {
						absoluteFilePath = workingDir + "/teams/"
								+ Variables.teamNames[0] + ".xml";
					}
					File myFile = new File(absoluteFilePath);
					File parentDir = myFile.getParentFile();
					if (!parentDir.exists())
						parentDir.mkdirs();
					Writer w = new OutputStreamWriter(new FileOutputStream(
							myFile), "UTF-8");
					w.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
							+ response);
					w.flush();
					w.close();

					// writeFile("/Teams/" + Variables.teamNames[0] + ".xml",
					// "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
					// + response);

					if (!response.contains("<primarySupervisor>")) {
						response = response
								.replace("</teamname>",
										"</teamname><primarySupervisor name=\"NONE\"></primarySupervisor>");
					}
					if (!response.contains("<secondarySupervisors>")) {
						response = response
								.replace(
										"</primarySupervisor>",
										"</primarySupervisor><secondarySupervisors><secondrySupervisor name=\"NONE\"></secondrySupervisor></secondarySupervisors>");
					}
					if (!response.contains("<resources>")) {
						response = response
								.replace(
										"</secondarySupervisors>",
										"</secondarySupervisors><resources><resource name=\"NONE\"></resource></resources>");
					}
					if (!response.contains("<csqs>")) {
						response = response
								.replace("</resources>",
										"</resources><csqs><csq name=\"NONE\"></csq></csqs>");
					}
					Variables.teamIds = Methods.substringsBetween(response,
							"<teamId>", "</teamId>");
					Variables.teamPSupv = Methods.substringsBetween(response,
							"<primarySupervisor name=\"", "\">");
					Variables.teamSSupv = Methods.substringsBetween(response,
							"<secondrySupervisor name=\"", "\">");
					Variables.teamResources = Methods.substringsBetween(
							response, "<resource name=\"", "\">");
					Variables.teamCSQs = Methods.substringsBetween(response,
							"<csq name=\"", "\">");
					Variables.teamTableRows[r][0] = Integer.toString(r + 1);
					Variables.teamTableRows[r][1] = Variables.teamNames[0];
					Variables.teamTableRows[r][2] = Variables.teamIds[0];
					Variables.teamTableRows[r][3] = Variables.teamPSupv[0];
					Variables.teamTableRows[r][4] = Variables.teamSSupv[0];
					Variables.teamTableRows[r][5] = Variables.teamResources[0];
					Variables.teamTableRows[r][6] = Variables.teamCSQs[0];
					MsgLog.write("Successfully retreieved data for team "
							+ Integer.toString(r + 1) + " ["
							+ Variables.teamTableRows[r][1]
							+ "] with an id of "
							+ Variables.teamTableRows[r][2] + "\n");
					UCCXLogGUI.setLogText(Variables.logString);
					responseCode = con.getResponseCode();
					responseMessage = con.getResponseMessage();
					con.disconnect();
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}

		}

		if (responseCode == HttpURLConnection.HTTP_ACCEPTED
				|| responseCode == HttpURLConnection.HTTP_CREATED
				|| responseCode == HttpURLConnection.HTTP_OK) {
			MsgLog.write("___________________________________________ \n");
			UCCXLogGUI.setLogText(Variables.logString);
			return true;
		} else {
			MsgLog.write(responseMessage + " \n");
			UCCXLogGUI.setLogText(Variables.logString);
			return false;
		}
	}

	public static boolean getAppRefs() throws IOException {
		int responseCode = 0;
		String responseMessage = "";
		String authString = Methods.getKeys("user") + ":"
				+ Methods.getKeys("pass");
		final byte[] authEncBytes = authString.getBytes(StandardCharsets.UTF_8);
		String Base64String = Base64.getEncoder().encodeToString(authEncBytes);
		String input = "";
		String response = "";
		SSLContext sc = null;
		try {
			sc = SSLContext.getInstance("TLS");
		} catch (NoSuchAlgorithmException e3) {

			e3.printStackTrace();
		}
		try {
			sc.init(null,
					new TrustManager[] { new TrustAllX509TrustManager() },
					new java.security.SecureRandom());
		} catch (KeyManagementException e2) {

			e2.printStackTrace();
		}
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
			@Override
			public boolean verify(String string, SSLSession ssls) {
				return true;
			}
		});
		String https_url = "https://" + Methods.getKeys("ip")
				+ "/adminapi/application";
		URL url;
		try {

			url = new URL(https_url);
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
			con.setDoInput(true);
			con.setDoOutput(true);

			/*
			 * con.setRequestProperty("Authorization",
			 * "Basic YXBwYWRtaW46TGluZWNvZGViOHpz");
			 */
			con.setRequestProperty("Authorization", "Basic " + Base64String);
			// Response Log
			BufferedReader br = new BufferedReader(new InputStreamReader(
					con.getInputStream()));

			while ((input = br.readLine()) != null) {
				//
				response = input;
			}
			br.close();

			// MsgLog.write(response);
			Variables.appRefLinks = Methods.substringsBetween(response,
					"<self>", "</self>");
			MsgLog.write("Total Apps: " + Variables.appRefLinks.length + " \n");
			for (int i = 0; i < Variables.appRefLinks.length; i++) {
				MsgLog.write("Application " + (i + 1) + " Link: "
						+ Variables.appRefLinks[i] + " \n");
			}
			responseCode = con.getResponseCode();
			responseMessage = con.getResponseMessage();
			con.disconnect();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if (responseCode == HttpURLConnection.HTTP_ACCEPTED
				|| responseCode == HttpURLConnection.HTTP_CREATED
				|| responseCode == HttpURLConnection.HTTP_OK) {
			MsgLog.write("___________________________________________ \n");
			UCCXLogGUI.setLogText(Variables.logString);
			return true;
		} else {
			MsgLog.write(responseMessage + " \n");
			UCCXLogGUI.setLogText(Variables.logString);
			return false;
		}

	}

	public static boolean getDetailedAppData() throws IOException {
		int responseCode = 0;
		String responseMessage = "";
		Variables.applicationTableRows = new String[Variables.appRefLinks.length][9];
		String authString = Methods.getKeys("user") + ":"
				+ Methods.getKeys("pass");
		final byte[] authEncBytes = authString.getBytes(StandardCharsets.UTF_8);
		String Base64String = Base64.getEncoder().encodeToString(authEncBytes);
		String input = "";
		String response = "";
		SSLContext sc = null;
		try {
			sc = SSLContext.getInstance("TLS");
		} catch (NoSuchAlgorithmException e3) {

			e3.printStackTrace();
		}
		try {
			sc.init(null,
					new TrustManager[] { new TrustAllX509TrustManager() },
					new java.security.SecureRandom());
		} catch (KeyManagementException e2) {

			e2.printStackTrace();
		}
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
			@Override
			public boolean verify(String string, SSLSession ssls) {
				return true;
			}
		});

		if (Variables.appRefLinks.length == 0) {
			MsgLog.write("No Refrence Links, kill thread!");
		} else {

			for (int r = 0; r < Variables.appRefLinks.length; r++) {

				String https_url = Variables.appRefLinks[r].replace("+", "%20");
				URL url;
				try {

					url = new URL(https_url);
					HttpsURLConnection con = (HttpsURLConnection) url
							.openConnection();
					con.setDoInput(true);
					con.setDoOutput(true);
					/*
					 * con.setRequestProperty("Authorization",
					 * "Basic YXBwYWRtaW46TGluZWNvZGViOHpz");
					 */
					con.setRequestProperty("Authorization", "Basic "
							+ Base64String);
					// Response Log
					BufferedReader br = new BufferedReader(
							new InputStreamReader(con.getInputStream()));

					while ((input = br.readLine()) != null) {
						//
						response = input;
					}
					br.close();
					Variables.appNames = Methods.substringsBetween(response,
							"<applicationName>", "</applicationName>");
					String workingDir = System.getProperty("user.dir");
					String absoluteFilePath = "";
					String your_os = System.getProperty("os.name")
							.toLowerCase();
					if (your_os.indexOf("win") >= 0) {
						absoluteFilePath = workingDir + "\\applications\\"
								+ Variables.appNames[0] + ".xml";
					} else if (your_os.indexOf("nix") >= 0
							|| your_os.indexOf("nux") >= 0
							|| your_os.indexOf("mac") >= 0) {
						absoluteFilePath = workingDir + "/applications/"
								+ Variables.appNames[0] + ".xml";
					} else {
						absoluteFilePath = workingDir + "/applications/"
								+ Variables.appNames[0] + ".xml";
					}
					File myFile = new File(absoluteFilePath);
					File parentDir = myFile.getParentFile();
					if (!parentDir.exists())
						parentDir.mkdirs();
					Writer w = new OutputStreamWriter(new FileOutputStream(
							myFile), "UTF-8");
					w.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
							+ response);
					w.flush();
					w.close();
					if (!response.contains("<defaultScript>")) {
						response = response
								.replace("</script>",
										"</script><defaultScript>System-Default</defaultScript>");
					}

					Variables.appTypes = Methods.substringsBetween(response,
							"<type>", "</type>");
					Variables.appScripts = Methods.substringsBetween(response,
							"<script>", "</script>");
					Variables.appDScripts = Methods.substringsBetween(response,
							"<defaultScript>", "</defaultScript>");
					Variables.appIds = Methods.substringsBetween(response,
							"<id>", "</id>");
					Variables.appDescs = Methods.substringsBetween(response,
							"<description>", "</description>");
					Variables.appMaxSess = Methods.substringsBetween(response,
							"<maxsession>", "</maxsession>");
					Variables.appEnableds = Methods.substringsBetween(response,
							"<enabled>", "</enabled>");
					Variables.applicationTableRows[r][0] = Integer
							.toString(r + 1);
					Variables.applicationTableRows[r][1] = Variables.appNames[0];
					Variables.applicationTableRows[r][2] = Variables.appTypes[0];
					Variables.applicationTableRows[r][3] = Variables.appScripts[0];
					Variables.applicationTableRows[r][4] = Variables.appDScripts[0];
					Variables.applicationTableRows[r][5] = Variables.appIds[0];
					Variables.applicationTableRows[r][6] = Variables.appDescs[0];
					Variables.applicationTableRows[r][7] = Variables.appMaxSess[0];
					Variables.applicationTableRows[r][8] = Variables.appEnableds[0];
					MsgLog.write("Successfully retreieved data for application "
							+ Integer.toString(r + 1)
							+ " ["
							+ Variables.applicationTableRows[r][1]
							+ "] with an id of "
							+ Variables.applicationTableRows[r][5] + "\n");
					UCCXLogGUI.setLogText(Variables.logString);
					responseCode = con.getResponseCode();
					responseMessage = con.getResponseMessage();
					con.disconnect();

				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}

		}

		if (responseCode == HttpURLConnection.HTTP_ACCEPTED
				|| responseCode == HttpURLConnection.HTTP_CREATED
				|| responseCode == HttpURLConnection.HTTP_OK) {
			MsgLog.write("___________________________________________ \n");
			UCCXLogGUI.setLogText(Variables.logString);
			return true;
		} else {
			MsgLog.write(responseMessage + " \n");
			UCCXLogGUI.setLogText(Variables.logString);
			return false;
		}
	}

	public static boolean getSkillRefs() throws IOException {
		int responseCode = 0;
		String responseMessage = "";
		String authString = Methods.getKeys("user") + ":"
				+ Methods.getKeys("pass");
		final byte[] authEncBytes = authString.getBytes(StandardCharsets.UTF_8);
		String Base64String = Base64.getEncoder().encodeToString(authEncBytes);
		String input = "";
		String response = "";
		SSLContext sc = null;
		try {
			sc = SSLContext.getInstance("TLS");
		} catch (NoSuchAlgorithmException e3) {

			e3.printStackTrace();
		}
		try {
			sc.init(null,
					new TrustManager[] { new TrustAllX509TrustManager() },
					new java.security.SecureRandom());
		} catch (KeyManagementException e2) {

			e2.printStackTrace();
		}
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
			@Override
			public boolean verify(String string, SSLSession ssls) {
				return true;
			}
		});
		String https_url = "https://" + Methods.getKeys("ip")
				+ "/adminapi/skill";
		URL url;
		try {

			url = new URL(https_url);
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
			con.setDoInput(true);
			con.setDoOutput(true);

			/*
			 * con.setRequestProperty("Authorization",
			 * "Basic YXBwYWRtaW46TGluZWNvZGViOHpz");
			 */
			con.setRequestProperty("Authorization", "Basic " + Base64String);
			// Response Log
			BufferedReader br = new BufferedReader(new InputStreamReader(
					con.getInputStream()));

			while ((input = br.readLine()) != null) {
				//
				response = input;
			}
			br.close();

			// MsgLog.write(response);
			Variables.skillRefLinks = Methods.substringsBetween(response,
					"<self>", "</self>");
			MsgLog.write("Total Skills: " + Variables.skillRefLinks.length
					+ " \n");
			String[] skillNames = null;
			skillNames = Methods.substringsBetween(response, "<skillName>",
					"</skillName>");
			// Used for 'new' skills later on
			Variables.newSkillRows = new String[Variables.skillRefLinks.length][3];
			for (int i = 0; i < Variables.skillRefLinks.length; i++) {
				MsgLog.write("Skill " + (i + 1) + " Link: "
						+ Variables.skillRefLinks[i] + " \n");
				Variables.newSkillRows[i][0] = skillNames[i];
				Variables.newSkillRows[i][1] = Variables.skillRefLinks[i];
			}
			responseCode = con.getResponseCode();
			responseMessage = con.getResponseMessage();
			con.disconnect();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if (responseCode == HttpURLConnection.HTTP_ACCEPTED
				|| responseCode == HttpURLConnection.HTTP_CREATED
				|| responseCode == HttpURLConnection.HTTP_OK) {
			MsgLog.write("___________________________________________ \n");
			UCCXLogGUI.setLogText(Variables.logString);
			return true;
		} else {
			MsgLog.write(responseMessage + " \n");
			UCCXLogGUI.setLogText(Variables.logString);
			return false;
		}

	}

	public static boolean getDetailedSkillData() throws IOException {
		int responseCode = 0;
		String responseMessage = "";
		Variables.skillTableRows = new String[Variables.skillRefLinks.length][3];
		String authString = Methods.getKeys("user") + ":"
				+ Methods.getKeys("pass");
		final byte[] authEncBytes = authString.getBytes(StandardCharsets.UTF_8);
		String Base64String = Base64.getEncoder().encodeToString(authEncBytes);
		String input = "";
		String response = "";
		SSLContext sc = null;
		try {
			sc = SSLContext.getInstance("TLS");
		} catch (NoSuchAlgorithmException e3) {

			e3.printStackTrace();
		}
		try {
			sc.init(null,
					new TrustManager[] { new TrustAllX509TrustManager() },
					new java.security.SecureRandom());
		} catch (KeyManagementException e2) {

			e2.printStackTrace();
		}
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
			@Override
			public boolean verify(String string, SSLSession ssls) {
				return true;
			}
		});

		if (Variables.skillRefLinks.length == 0) {
			MsgLog.write("No Refrence Links, kill thread!");
		} else {

			for (int r = 0; r < Variables.skillRefLinks.length; r++) {

				String https_url = Variables.skillRefLinks[r].replace("+",
						"%20");
				URL url;
				try {

					url = new URL(https_url);
					HttpsURLConnection con = (HttpsURLConnection) url
							.openConnection();
					con.setDoInput(true);
					con.setDoOutput(true);

					/*
					 * con.setRequestProperty("Authorization",
					 * "Basic YXBwYWRtaW46TGluZWNvZGViOHpz");
					 */
					con.setRequestProperty("Authorization", "Basic "
							+ Base64String);
					// Response Log
					BufferedReader br = new BufferedReader(
							new InputStreamReader(con.getInputStream()));

					while ((input = br.readLine()) != null) {
						//
						response = input;
					}
					br.close();
					Variables.skillNames = Methods.substringsBetween(response,
							"<skillName>", "</skillName>");
					String workingDir = System.getProperty("user.dir");
					String absoluteFilePath = "";
					String your_os = System.getProperty("os.name")
							.toLowerCase();
					if (your_os.indexOf("win") >= 0) {
						absoluteFilePath = workingDir + "\\skills\\"
								+ Variables.skillNames[0] + ".xml";
					} else if (your_os.indexOf("nix") >= 0
							|| your_os.indexOf("nux") >= 0
							|| your_os.indexOf("mac") >= 0) {
						absoluteFilePath = workingDir + "/skills/"
								+ Variables.skillNames[0] + ".xml";
					} else {
						absoluteFilePath = workingDir + "/skills/"
								+ Variables.skillNames[0] + ".xml";
					}
					File myFile = new File(absoluteFilePath);
					File parentDir = myFile.getParentFile();
					if (!parentDir.exists())
						parentDir.mkdirs();
					Writer w = new OutputStreamWriter(new FileOutputStream(
							myFile), "UTF-8");
					w.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
							+ response);
					w.flush();
					w.close();
					Variables.skillIds = Methods.substringsBetween(response,
							"<skillId>", "</skillId>");
					Variables.skillTableRows[r][0] = Integer.toString(r + 1);
					Variables.skillTableRows[r][1] = Variables.skillNames[0];
					Variables.skillTableRows[r][2] = Variables.skillIds[0];
					MsgLog.write("Successfully retreieved data for skill "
							+ Integer.toString(r + 1) + " ["
							+ Variables.skillTableRows[r][1] + "] \n");
					UCCXLogGUI.setLogText(Variables.logString);
					responseCode = con.getResponseCode();
					responseMessage = con.getResponseMessage();
					con.disconnect();
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}

		}

		if (responseCode == HttpURLConnection.HTTP_ACCEPTED
				|| responseCode == HttpURLConnection.HTTP_CREATED
				|| responseCode == HttpURLConnection.HTTP_OK) {
			MsgLog.write("___________________________________________ \n");
			UCCXLogGUI.setLogText(Variables.logString);
			return true;
		} else {
			MsgLog.write(responseMessage + " \n");
			UCCXLogGUI.setLogText(Variables.logString);
			return false;
		}
	}

	public static boolean getCSQRefs() throws IOException {
		int responseCode = 0;
		String responseMessage = "";
		String authString = Methods.getKeys("user") + ":"
				+ Methods.getKeys("pass");
		final byte[] authEncBytes = authString.getBytes(StandardCharsets.UTF_8);
		String Base64String = Base64.getEncoder().encodeToString(authEncBytes);
		String input = "";
		String response = "";
		SSLContext sc = null;
		try {
			sc = SSLContext.getInstance("TLS");
		} catch (NoSuchAlgorithmException e3) {

			e3.printStackTrace();
		}
		try {
			sc.init(null,
					new TrustManager[] { new TrustAllX509TrustManager() },
					new java.security.SecureRandom());
		} catch (KeyManagementException e2) {

			e2.printStackTrace();
		}
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
			@Override
			public boolean verify(String string, SSLSession ssls) {
				return true;
			}
		});
		String https_url = "https://" + Methods.getKeys("ip") + "/adminapi/csq";
		URL url;
		try {

			url = new URL(https_url);
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
			con.setDoInput(true);
			con.setDoOutput(true);

			/*
			 * con.setRequestProperty("Authorization",
			 * "Basic YXBwYWRtaW46TGluZWNvZGViOHpz");
			 */
			con.setRequestProperty("Authorization", "Basic " + Base64String);
			// Response Log
			BufferedReader br = new BufferedReader(new InputStreamReader(
					con.getInputStream()));

			while ((input = br.readLine()) != null) {
				//
				response = input;
			}
			br.close();

			// MsgLog.write(response);
			Variables.csqRefLinks = Methods.substringsBetween(response,
					"<self>", "</self>");
			String[] csqNames = null;
			csqNames = Methods.substringsBetween(response, "<name>", "</name>");
			// used for new csqs
			Variables.newCSQRows = new String[Variables.csqRefLinks.length][3];
			MsgLog.write("Total CSQs: " + Variables.csqRefLinks.length + " \n");
			for (int i = 0; i < Variables.csqRefLinks.length; i++) {
				MsgLog.write("CSQ " + (i + 1) + " Link: "
						+ Variables.csqRefLinks[i] + " \n");
				Variables.newCSQRows[i][0] = csqNames[i];
				Variables.newCSQRows[i][1] = Variables.csqRefLinks[i];
			}
			responseCode = con.getResponseCode();
			responseMessage = con.getResponseMessage();
			con.disconnect();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if (responseCode == HttpURLConnection.HTTP_ACCEPTED
				|| responseCode == HttpURLConnection.HTTP_CREATED
				|| responseCode == HttpURLConnection.HTTP_OK) {
			MsgLog.write("___________________________________________ \n");
			UCCXLogGUI.setLogText(Variables.logString);
			return true;
		} else {
			MsgLog.write(responseMessage + " \n");
			UCCXLogGUI.setLogText(Variables.logString);
			return false;
		}
	}

	public static boolean getDetailedCSQData() throws IOException {
		int responseCode = 0;
		String responseMessage = "";
		Variables.csqTableRows = new String[Variables.csqRefLinks.length][3];
		String authString = Methods.getKeys("user") + ":"
				+ Methods.getKeys("pass");
		final byte[] authEncBytes = authString.getBytes(StandardCharsets.UTF_8);
		String Base64String = Base64.getEncoder().encodeToString(authEncBytes);
		String input = "";
		String response = "";
		SSLContext sc = null;
		try {
			sc = SSLContext.getInstance("TLS");
		} catch (NoSuchAlgorithmException e3) {

			e3.printStackTrace();
		}
		try {
			sc.init(null,
					new TrustManager[] { new TrustAllX509TrustManager() },
					new java.security.SecureRandom());
		} catch (KeyManagementException e2) {

			e2.printStackTrace();
		}
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
			@Override
			public boolean verify(String string, SSLSession ssls) {
				return true;
			}
		});

		if (Variables.csqRefLinks.length == 0) {
			MsgLog.write("No Refrence Links, kill thread!");
		} else {

			for (int r = 0; r < Variables.csqRefLinks.length; r++) {

				String https_url = Variables.csqRefLinks[r].replace("+", "%20");
				URL url;
				try {

					url = new URL(https_url);
					HttpsURLConnection con = (HttpsURLConnection) url
							.openConnection();
					con.setDoInput(true);
					con.setDoOutput(true);

					/*
					 * con.setRequestProperty("Authorization",
					 * "Basic YXBwYWRtaW46TGluZWNvZGViOHpz");
					 */
					con.setRequestProperty("Authorization", "Basic "
							+ Base64String);
					// Response Log
					BufferedReader br = new BufferedReader(
							new InputStreamReader(con.getInputStream()));

					while ((input = br.readLine()) != null) {
						//
						response = input;
					}
					br.close();
					Variables.csqNames = Methods.substringsBetween(response,
							"<name>", "</name>");
					String workingDir = System.getProperty("user.dir");
					String absoluteFilePath = "";
					String your_os = System.getProperty("os.name")
							.toLowerCase();
					if (your_os.indexOf("win") >= 0) {
						absoluteFilePath = workingDir + "\\csqs\\"
								+ Variables.csqNames[0] + ".xml";
					} else if (your_os.indexOf("nix") >= 0
							|| your_os.indexOf("nux") >= 0
							|| your_os.indexOf("mac") >= 0) {
						absoluteFilePath = workingDir + "/csqs/"
								+ Variables.csqNames[0] + ".xml";
					} else {
						absoluteFilePath = workingDir + "/csqs/"
								+ Variables.csqNames[0] + ".xml";
					}
					File myFile = new File(absoluteFilePath);
					File parentDir = myFile.getParentFile();
					if (!parentDir.exists())
						parentDir.mkdirs();
					Writer w = new OutputStreamWriter(new FileOutputStream(
							myFile), "UTF-8");
					w.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
							+ response);
					w.flush();
					w.close();

					Variables.csqIds = Methods.substringsBetween(response,
							"<id>", "</id>");
					Variables.csqTableRows[r][0] = Integer.toString(r + 1);
					Variables.csqTableRows[r][1] = Variables.csqNames[0];
					Variables.csqTableRows[r][2] = Variables.csqIds[0];
					MsgLog.write("Successfully retreieved data for csq "
							+ Integer.toString(r + 1) + " ["
							+ Variables.csqTableRows[r][1] + "] with an id of "
							+ Variables.csqTableRows[r][2] + "\n");
					UCCXLogGUI.setLogText(Variables.logString);
					responseCode = con.getResponseCode();
					responseMessage = con.getResponseMessage();
					con.disconnect();

				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}

		}

		if (responseCode == HttpURLConnection.HTTP_ACCEPTED
				|| responseCode == HttpURLConnection.HTTP_CREATED
				|| responseCode == HttpURLConnection.HTTP_OK) {
			MsgLog.write("___________________________________________ \n");
			UCCXLogGUI.setLogText(Variables.logString);
			return true;
		} else {
			MsgLog.write(responseMessage + " \n");
			UCCXLogGUI.setLogText(Variables.logString);
			return false;
		}
	}

	public static boolean getCCGRefs() throws IOException {
		int responseCode = 0;
		String responseMessage = "";
		String authString = Methods.getKeys("user") + ":"
				+ Methods.getKeys("pass");
		final byte[] authEncBytes = authString.getBytes(StandardCharsets.UTF_8);
		String Base64String = Base64.getEncoder().encodeToString(authEncBytes);
		String input = "";
		String response = "";
		SSLContext sc = null;
		try {
			sc = SSLContext.getInstance("TLS");
		} catch (NoSuchAlgorithmException e3) {

			e3.printStackTrace();
		}
		try {
			sc.init(null,
					new TrustManager[] { new TrustAllX509TrustManager() },
					new java.security.SecureRandom());
		} catch (KeyManagementException e2) {

			e2.printStackTrace();
		}
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
			@Override
			public boolean verify(String string, SSLSession ssls) {
				return true;
			}
		});
		String https_url = "https://" + Methods.getKeys("ip")
				+ "/adminapi/callControlGroup";
		URL url;
		try {

			url = new URL(https_url);
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
			con.setDoInput(true);
			con.setDoOutput(true);

			/*
			 * con.setRequestProperty("Authorization",
			 * "Basic YXBwYWRtaW46TGluZWNvZGViOHpz");
			 */
			con.setRequestProperty("Authorization", "Basic " + Base64String);
			// Response Log
			BufferedReader br = new BufferedReader(new InputStreamReader(
					con.getInputStream()));

			while ((input = br.readLine()) != null) {
				//
				response = input;
			}
			br.close();

			// MsgLog.write(response);
			Variables.ccgRefLinks = Methods.substringsBetween(response,
					"<self type=\"callControlGroup\" href=\"",
					"\" rel=\"self\"/>");
			String[] ccgNames = null;
			ccgNames = Methods.substringsBetween(response,
					"<deviceNamePrefix>", "</deviceNamePrefix>");
			// used for new csqs
			Variables.newCCGRows = new String[Variables.ccgRefLinks.length][3];
			MsgLog.write("Total CCGs: " + Variables.ccgRefLinks.length + " \n");
			for (int i = 0; i < Variables.ccgRefLinks.length; i++) {
				MsgLog.write("CCG " + (i + 1) + " Link: "
						+ Variables.ccgRefLinks[i] + " \n");
				Variables.newCCGRows[i][0] = ccgNames[i];
				Variables.newCCGRows[i][1] = Variables.ccgRefLinks[i];
			}
			responseCode = con.getResponseCode();
			responseMessage = con.getResponseMessage();
			con.disconnect();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if (responseCode == HttpURLConnection.HTTP_ACCEPTED
				|| responseCode == HttpURLConnection.HTTP_CREATED
				|| responseCode == HttpURLConnection.HTTP_OK) {
			MsgLog.write("___________________________________________ \n");
			UCCXLogGUI.setLogText(Variables.logString);
			return true;
		} else {
			MsgLog.write(responseMessage + " \n");
			UCCXLogGUI.setLogText(Variables.logString);
			return false;
		}
	}

	public static boolean getDetailedCCGData() throws IOException {
		int responseCode = 0;
		String responseMessage = "";
		Variables.ccgTableRows = new String[Variables.ccgRefLinks.length][3];
		String authString = Methods.getKeys("user") + ":"
				+ Methods.getKeys("pass");
		final byte[] authEncBytes = authString.getBytes(StandardCharsets.UTF_8);
		String Base64String = Base64.getEncoder().encodeToString(authEncBytes);
		String input = "";
		String response = "";
		SSLContext sc = null;
		try {
			sc = SSLContext.getInstance("TLS");
		} catch (NoSuchAlgorithmException e3) {

			e3.printStackTrace();
		}
		try {
			sc.init(null,
					new TrustManager[] { new TrustAllX509TrustManager() },
					new java.security.SecureRandom());
		} catch (KeyManagementException e2) {

			e2.printStackTrace();
		}
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
			@Override
			public boolean verify(String string, SSLSession ssls) {
				return true;
			}
		});

		if (Variables.ccgRefLinks.length == 0) {
			MsgLog.write("No Refrence Links, kill thread!");
		} else {

			for (int r = 0; r < Variables.ccgRefLinks.length; r++) {

				String https_url = Variables.ccgRefLinks[r].replace("+", "%20");
				URL url;
				try {

					url = new URL(https_url);
					HttpsURLConnection con = (HttpsURLConnection) url
							.openConnection();
					con.setDoInput(true);
					con.setDoOutput(true);

					/*
					 * con.setRequestProperty("Authorization",
					 * "Basic YXBwYWRtaW46TGluZWNvZGViOHpz");
					 */
					con.setRequestProperty("Authorization", "Basic "
							+ Base64String);
					// Response Log
					BufferedReader br = new BufferedReader(
							new InputStreamReader(con.getInputStream()));

					while ((input = br.readLine()) != null) {
						//
						response = input;
					}
					br.close();
					Variables.ccgName = Methods.substringsBetween(response,
							"<deviceNamePrefix>", "</deviceNamePrefix>");
					String workingDir = System.getProperty("user.dir");
					String absoluteFilePath = "";
					String your_os = System.getProperty("os.name")
							.toLowerCase();
					if (your_os.indexOf("win") >= 0) {
						absoluteFilePath = workingDir + "\\ccgs\\"
								+ Variables.ccgName[0] + ".xml";
					} else if (your_os.indexOf("nix") >= 0
							|| your_os.indexOf("nux") >= 0
							|| your_os.indexOf("mac") >= 0) {
						absoluteFilePath = workingDir + "/ccgs/"
								+ Variables.ccgName[0] + ".xml";
					} else {
						absoluteFilePath = workingDir + "/ccgs/"
								+ Variables.ccgName[0] + ".xml";
					}
					File myFile = new File(absoluteFilePath);
					File parentDir = myFile.getParentFile();
					if (!parentDir.exists())
						parentDir.mkdirs();
					Writer w = new OutputStreamWriter(new FileOutputStream(
							myFile), "UTF-8");
					w.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
							+ response);
					w.flush();
					w.close();

					Variables.ccgId = Methods.substringsBetween(response,
							"<id>", "</id>");
					Variables.ccgTableRows[r][0] = Integer.toString(r + 1);
					Variables.ccgTableRows[r][1] = Variables.ccgName[0];
					Variables.ccgTableRows[r][2] = Variables.ccgId[0];
					MsgLog.write("Successfully retreieved data for ccg "
							+ Integer.toString(r + 1) + " ["
							+ Variables.ccgTableRows[r][1] + "] with an id of "
							+ Variables.ccgTableRows[r][2] + "\n");
					UCCXLogGUI.setLogText(Variables.logString);
					responseCode = con.getResponseCode();
					responseMessage = con.getResponseMessage();
					con.disconnect();

				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}

		}

		if (responseCode == HttpURLConnection.HTTP_ACCEPTED
				|| responseCode == HttpURLConnection.HTTP_CREATED
				|| responseCode == HttpURLConnection.HTTP_OK) {
			MsgLog.write("___________________________________________ \n");
			UCCXLogGUI.setLogText(Variables.logString);
			return true;
		} else {
			MsgLog.write(responseMessage + " \n");
			UCCXLogGUI.setLogText(Variables.logString);
			return false;
		}
	}

	public static boolean getRGRefs() throws IOException {
		int responseCode = 0;
		String responseMessage = "";
		String authString = Methods.getKeys("user") + ":"
				+ Methods.getKeys("pass");
		final byte[] authEncBytes = authString.getBytes(StandardCharsets.UTF_8);
		String Base64String = Base64.getEncoder().encodeToString(authEncBytes);
		String input = "";
		String response = "";
		SSLContext sc = null;
		try {
			sc = SSLContext.getInstance("TLS");
		} catch (NoSuchAlgorithmException e3) {

			e3.printStackTrace();
		}
		try {
			sc.init(null,
					new TrustManager[] { new TrustAllX509TrustManager() },
					new java.security.SecureRandom());
		} catch (KeyManagementException e2) {

			e2.printStackTrace();
		}
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
			@Override
			public boolean verify(String string, SSLSession ssls) {
				return true;
			}
		});
		String https_url = "https://" + Methods.getKeys("ip")
				+ "/adminapi/resourceGroup";
		URL url;
		try {

			url = new URL(https_url);
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
			con.setDoInput(true);
			con.setDoOutput(true);

			/*
			 * con.setRequestProperty("Authorization",
			 * "Basic YXBwYWRtaW46TGluZWNvZGViOHpz");
			 */
			con.setRequestProperty("Authorization", "Basic " + Base64String);
			// Response Log
			BufferedReader br = new BufferedReader(new InputStreamReader(
					con.getInputStream()));

			while ((input = br.readLine()) != null) {
				//
				response = input;
			}
			br.close();

			// MsgLog.write(response);
			Variables.rgRefLinks = Methods.substringsBetween(response,
					"<self>", "</self>");
			MsgLog.write("Total RGs: " + Variables.rgRefLinks.length + " \n");
			for (int i = 0; i < Variables.rgRefLinks.length; i++) {
				MsgLog.write("Resource Group " + (i + 1) + " Link: "
						+ Variables.rgRefLinks[i] + " \n");
			}
			responseCode = con.getResponseCode();
			responseMessage = con.getResponseMessage();
			con.disconnect();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if (responseCode == HttpURLConnection.HTTP_ACCEPTED
				|| responseCode == HttpURLConnection.HTTP_CREATED
				|| responseCode == HttpURLConnection.HTTP_OK) {
			MsgLog.write("___________________________________________ \n");
			UCCXLogGUI.setLogText(Variables.logString);
			return true;
		} else {
			MsgLog.write(responseMessage + " \n");
			UCCXLogGUI.setLogText(Variables.logString);
			return false;
		}
	}

	public static boolean getDetailedRGData() throws IOException {
		int responseCode = 0;
		String responseMessage = "";
		Variables.rgTableRows = new String[Variables.rgRefLinks.length][3];
		String authString = Methods.getKeys("user") + ":"
				+ Methods.getKeys("pass");
		final byte[] authEncBytes = authString.getBytes(StandardCharsets.UTF_8);
		String Base64String = Base64.getEncoder().encodeToString(authEncBytes);
		String input = "";
		String response = "";
		SSLContext sc = null;
		try {
			sc = SSLContext.getInstance("TLS");
		} catch (NoSuchAlgorithmException e3) {

			e3.printStackTrace();
		}
		try {
			sc.init(null,
					new TrustManager[] { new TrustAllX509TrustManager() },
					new java.security.SecureRandom());
		} catch (KeyManagementException e2) {

			e2.printStackTrace();
		}
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
			@Override
			public boolean verify(String string, SSLSession ssls) {
				return true;
			}
		});

		if (Variables.rgRefLinks.length == 0) {
			MsgLog.write("No Refrence Links, kill thread!");
		} else {

			for (int r = 0; r < Variables.rgRefLinks.length; r++) {

				String https_url = Variables.rgRefLinks[r].replace("+", "%20");
				URL url;
				try {

					url = new URL(https_url);
					HttpsURLConnection con = (HttpsURLConnection) url
							.openConnection();
					con.setDoInput(true);
					con.setDoOutput(true);

					/*
					 * con.setRequestProperty("Authorization",
					 * "Basic YXBwYWRtaW46TGluZWNvZGViOHpz");
					 */
					con.setRequestProperty("Authorization", "Basic "
							+ Base64String);
					// Response Log
					BufferedReader br = new BufferedReader(
							new InputStreamReader(con.getInputStream()));

					while ((input = br.readLine()) != null) {
						//
						response = input;
					}
					br.close();
					Variables.rgNames = Methods.substringsBetween(response,
							"<name>", "</name>");
					String workingDir = System.getProperty("user.dir");
					String absoluteFilePath = "";
					String your_os = System.getProperty("os.name")
							.toLowerCase();
					if (your_os.indexOf("win") >= 0) {
						absoluteFilePath = workingDir + "\\resource groups\\"
								+ Variables.rgNames[0] + ".xml";
					} else if (your_os.indexOf("nix") >= 0
							|| your_os.indexOf("nux") >= 0
							|| your_os.indexOf("mac") >= 0) {
						absoluteFilePath = workingDir + "/resource groups/"
								+ Variables.rgNames[0] + ".xml";
					} else {
						absoluteFilePath = workingDir + "/resource groups/"
								+ Variables.rgNames[0] + ".xml";
					}
					File myFile = new File(absoluteFilePath);
					File parentDir = myFile.getParentFile();
					if (!parentDir.exists())
						parentDir.mkdirs();
					Writer w = new OutputStreamWriter(new FileOutputStream(
							myFile), "UTF-8");
					w.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
							+ response);
					w.flush();
					w.close();

					Variables.rgIds = Methods.substringsBetween(response,
							"<id>", "</id>");
					Variables.rgTableRows[r][0] = Integer.toString(r + 1);
					Variables.rgTableRows[r][1] = Variables.rgNames[0];
					Variables.rgTableRows[r][2] = Variables.rgIds[0];
					MsgLog.write("Successfully retreieved data for resource group "
							+ Integer.toString(r + 1)
							+ " ["
							+ Variables.rgTableRows[r][1]
							+ "] with an id of "
							+ Variables.rgTableRows[r][2] + "\n");
					UCCXLogGUI.setLogText(Variables.logString);
					responseCode = con.getResponseCode();
					responseMessage = con.getResponseMessage();
					con.disconnect();

				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}

		}

		if (responseCode == HttpURLConnection.HTTP_ACCEPTED
				|| responseCode == HttpURLConnection.HTTP_CREATED
				|| responseCode == HttpURLConnection.HTTP_OK) {
			MsgLog.write("___________________________________________ \n");
			UCCXLogGUI.setLogText(Variables.logString);
			return true;
		} else {
			MsgLog.write(responseMessage + " \n");
			UCCXLogGUI.setLogText(Variables.logString);
			return false;
		}
	}

	public static boolean getTriggerRefs() throws IOException {
		int responseCode = 0;
		String responseMessage = "";
		String authString = Methods.getKeys("user") + ":"
				+ Methods.getKeys("pass");
		final byte[] authEncBytes = authString.getBytes(StandardCharsets.UTF_8);
		String Base64String = Base64.getEncoder().encodeToString(authEncBytes);
		String input = "";
		String response = "";
		SSLContext sc = null;
		try {
			sc = SSLContext.getInstance("TLS");
		} catch (NoSuchAlgorithmException e3) {

			e3.printStackTrace();
		}
		try {
			sc.init(null,
					new TrustManager[] { new TrustAllX509TrustManager() },
					new java.security.SecureRandom());
		} catch (KeyManagementException e2) {

			e2.printStackTrace();
		}
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
			@Override
			public boolean verify(String string, SSLSession ssls) {
				return true;
			}
		});
		String https_url = "https://" + Methods.getKeys("ip")
				+ "/adminapi/trigger";
		URL url;
		try {

			url = new URL(https_url);
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
			con.setDoInput(true);
			con.setDoOutput(true);

			/*
			 * con.setRequestProperty("Authorization",
			 * "Basic YXBwYWRtaW46TGluZWNvZGViOHpz");
			 */
			con.setRequestProperty("Authorization", "Basic " + Base64String);
			// Response Log
			BufferedReader br = new BufferedReader(new InputStreamReader(
					con.getInputStream()));

			while ((input = br.readLine()) != null) {
				//
				response = input;
			}
			br.close();

			// MsgLog.write(response);
			Variables.trigRefLinks = Methods.substringsBetween(response,
					"<self type=\"trigger\" href=\"", "\" rel=\"self\"/>");
			MsgLog.write("Total Triggers: " + Variables.trigRefLinks.length
					+ " \n");
			for (int i = 0; i < Variables.trigRefLinks.length; i++) {
				MsgLog.write("Trigger " + (i + 1) + " Link: "
						+ Variables.trigRefLinks[i] + " \n");
			}
			responseCode = con.getResponseCode();
			responseMessage = con.getResponseMessage();
			con.disconnect();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if (responseCode == HttpURLConnection.HTTP_ACCEPTED
				|| responseCode == HttpURLConnection.HTTP_CREATED
				|| responseCode == HttpURLConnection.HTTP_OK) {
			MsgLog.write("___________________________________________ \n");
			UCCXLogGUI.setLogText(Variables.logString);
			return true;
		} else {
			MsgLog.write(responseMessage + " \n");
			UCCXLogGUI.setLogText(Variables.logString);
			return false;
		}
	}

	public static boolean getDetailedTriggerData() throws IOException {
		int responseCode = 0;
		String responseMessage = "";
		Variables.trigTableRows = new String[Variables.trigRefLinks.length][6];
		String authString = Methods.getKeys("user") + ":"
				+ Methods.getKeys("pass");
		final byte[] authEncBytes = authString.getBytes(StandardCharsets.UTF_8);
		String Base64String = Base64.getEncoder().encodeToString(authEncBytes);
		String input = "";
		String response = "";
		SSLContext sc = null;
		try {
			sc = SSLContext.getInstance("TLS");
		} catch (NoSuchAlgorithmException e3) {

			e3.printStackTrace();
		}
		try {
			sc.init(null,
					new TrustManager[] { new TrustAllX509TrustManager() },
					new java.security.SecureRandom());
		} catch (KeyManagementException e2) {

			e2.printStackTrace();
		}
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
			@Override
			public boolean verify(String string, SSLSession ssls) {
				return true;
			}
		});

		if (Variables.trigRefLinks.length == 0) {
			MsgLog.write("No Refrence Links, kill thread!");
		} else {

			for (int r = 0; r < Variables.trigRefLinks.length; r++) {

				String https_url = Variables.trigRefLinks[r]
						.replace("+", "%20");
				URL url;
				try {

					url = new URL(https_url);
					HttpsURLConnection con = (HttpsURLConnection) url
							.openConnection();
					con.setDoInput(true);
					con.setDoOutput(true);

					/*
					 * con.setRequestProperty("Authorization",
					 * "Basic YXBwYWRtaW46TGluZWNvZGViOHpz");
					 */
					con.setRequestProperty("Authorization", "Basic "
							+ Base64String);
					// Response Log
					BufferedReader br = new BufferedReader(
							new InputStreamReader(con.getInputStream()));

					while ((input = br.readLine()) != null) {
						//
						response = input;
					}
					br.close();
					Variables.trigNums = Methods.substringsBetween(response,
							"<directoryNumber>", "</directoryNumber>");
					String workingDir = System.getProperty("user.dir");
					String absoluteFilePath = "";
					String your_os = System.getProperty("os.name")
							.toLowerCase();
					if (your_os.indexOf("win") >= 0) {
						absoluteFilePath = workingDir + "\\triggers\\"
								+ Variables.trigNums[0] + ".xml";
					} else if (your_os.indexOf("nix") >= 0
							|| your_os.indexOf("nux") >= 0
							|| your_os.indexOf("mac") >= 0) {
						absoluteFilePath = workingDir + "/triggers/"
								+ Variables.trigNums[0] + ".xml";
					} else {
						absoluteFilePath = workingDir + "/triggers/"
								+ Variables.trigNums[0] + ".xml";
					}
					File myFile = new File(absoluteFilePath);
					File parentDir = myFile.getParentFile();
					if (!parentDir.exists())
						parentDir.mkdirs();
					Writer w = new OutputStreamWriter(new FileOutputStream(
							myFile), "UTF-8");
					w.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
							+ response);
					w.flush();
					w.close();

					Variables.trigAppNames = Methods.substringsBetween(
							response, "<application name=\"", "\">");
					Variables.trigDevNames = Methods.substringsBetween(
							response, "<deviceName>", "</deviceName>");
					Variables.trigDesc = Methods.substringsBetween(response,
							"<description>", "</description>");
					Variables.trigEnabled = Methods.substringsBetween(response,
							"<triggerEnabled>", "</triggerEnabled>");
					Variables.trigTableRows[r][0] = Integer.toString(r + 1);
					Variables.trigTableRows[r][1] = Variables.trigNums[0];
					Variables.trigTableRows[r][2] = Variables.trigAppNames[0];
					Variables.trigTableRows[r][3] = Variables.trigDevNames[0];
					Variables.trigTableRows[r][4] = Variables.trigDesc[0];
					Variables.trigTableRows[r][5] = Variables.trigEnabled[0];
					MsgLog.write("Successfully retreieved data for trigger "
							+ Integer.toString(r + 1) + " ["
							+ Variables.trigTableRows[r][1] + "] \n");
					UCCXLogGUI.setLogText(Variables.logString);
					responseCode = con.getResponseCode();
					responseMessage = con.getResponseMessage();
					con.disconnect();

				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}

		}

		if (responseCode == HttpURLConnection.HTTP_ACCEPTED
				|| responseCode == HttpURLConnection.HTTP_CREATED
				|| responseCode == HttpURLConnection.HTTP_OK) {
			MsgLog.write("___________________________________________ \n");
			UCCXLogGUI.setLogText(Variables.logString);
			return true;
		} else {
			MsgLog.write(responseMessage + " \n");
			UCCXLogGUI.setLogText(Variables.logString);
			return false;
		}
	}

	/*
	 * public static boolean pushAllTeams() throws HeadlessException,
	 * IOException { // new String workingDir = System.getProperty("user.dir");
	 * String absoluteFilePath = ""; String absoluteFilePathB = ""; String
	 * your_os = System.getProperty("os.name").toLowerCase(); if
	 * (your_os.indexOf("win") >= 0) { absoluteFilePath = workingDir +
	 * "\\teams\\"; absoluteFilePathB = absoluteFilePath + "\\finished teams\\";
	 * } else if (your_os.indexOf("nix") >= 0 || your_os.indexOf("nux") >= 0 ||
	 * your_os.indexOf("mac") >= 0) { absoluteFilePath = workingDir + "/teams/";
	 * absoluteFilePathB = absoluteFilePath + "/finished teams/"; } else {
	 * absoluteFilePath = workingDir + "/teams/"; absoluteFilePathB =
	 * absoluteFilePath + "/finished teams/"; } File fileDir = new
	 * File(absoluteFilePath); for (final File fileEntry : fileDir.listFiles())
	 * {
	 * 
	 * String fileContents = null; if (fileEntry.isDirectory()) {
	 * listFilesForFolder(fileEntry); } else { try { fileContents = readFile(
	 * absoluteFilePath + fileEntry.getName(), Charset.defaultCharset()); }
	 * catch (IOException e) {
	 * 
	 * e.printStackTrace(); } if (push("Team", fileContents) == true) { //
	 * MsgLog.write("Pushed: " + fileEntry.getName()); try {
	 * 
	 * File afile = new File(absoluteFilePath + fileEntry.getName()); File bfile
	 * = new File(absoluteFilePathB + fileEntry.getName()); File parentDir =
	 * bfile.getParentFile(); if (!parentDir.exists()) parentDir.mkdirs(); if
	 * (afile.renameTo(new File(absoluteFilePathB + afile.getName()))) {
	 * MsgLog.write("File moved successfully! \n"); } else {
	 * MsgLog.write("Failed to move file! \n"); }
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } } else { //
	 * JOptionPane.showMessageDialog( // UCCXTable.uccxTable, //
	 * "Something bad happened with: " // + fileEntry.getName(), "Oh crap!", 0);
	 * MsgLog.write("Something went wrong, check team [" + fileEntry.getName() +
	 * "] ERROR: 265 \n"); }
	 * 
	 * }
	 * 
	 * }
	 * 
	 * return true; }
	 * 
	 * public static boolean pushAllApplications() throws HeadlessException,
	 * IOException { // new String workingDir = System.getProperty("user.dir");
	 * String absoluteFilePath = ""; String absoluteFilePathB = ""; String
	 * your_os = System.getProperty("os.name").toLowerCase(); if
	 * (your_os.indexOf("win") >= 0) { absoluteFilePath = workingDir +
	 * "\\applications\\"; absoluteFilePathB = absoluteFilePath +
	 * "\\finished applications\\"; } else if (your_os.indexOf("nix") >= 0 ||
	 * your_os.indexOf("nux") >= 0 || your_os.indexOf("mac") >= 0) {
	 * absoluteFilePath = workingDir + "/applications/"; absoluteFilePathB =
	 * absoluteFilePath + "/finished applications/"; } else { absoluteFilePath =
	 * workingDir + "/applications/"; absoluteFilePathB = absoluteFilePath +
	 * "/finished applications/"; } File fileDir = new File(absoluteFilePath);
	 * for (final File fileEntry : fileDir.listFiles()) {
	 * 
	 * String fileContents = null; if (fileEntry.isDirectory()) {
	 * listFilesForFolder(fileEntry); } else {
	 * 
	 * try { fileContents = readFile( absoluteFilePath + fileEntry.getName(),
	 * Charset.defaultCharset()); } catch (IOException e) {
	 * 
	 * e.printStackTrace(); } if (push("App", fileContents) == true) { //
	 * MsgLog.write("Pushed: " + fileEntry.getName()); try {
	 * 
	 * File afile = new File(absoluteFilePath + fileEntry.getName()); File bfile
	 * = new File(absoluteFilePathB + fileEntry.getName()); File parentDir =
	 * bfile.getParentFile(); if (!parentDir.exists()) parentDir.mkdirs(); if
	 * (afile.renameTo(new File(absoluteFilePathB + afile.getName()))) {
	 * MsgLog.write("File moved successfully! \n"); } else {
	 * MsgLog.write("Failed to move file! \n"); }
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } } else { //
	 * JOptionPane.showMessageDialog( // UCCXTable.uccxTable, //
	 * "Something bad happened with: " // + fileEntry.getName(), "Oh crap!", 0);
	 * MsgLog.write("Something went wrong, check application [" +
	 * fileEntry.getName() + "] ERROR: 266 \n"); }
	 * 
	 * }
	 * 
	 * }
	 * 
	 * return true; }
	 * 
	 * public static boolean pushAllCSQs() throws HeadlessException, IOException
	 * { // new String workingDir = System.getProperty("user.dir"); String
	 * absoluteFilePath = ""; String absoluteFilePathB = ""; String your_os =
	 * System.getProperty("os.name").toLowerCase(); if (your_os.indexOf("win")
	 * >= 0) { absoluteFilePath = workingDir + "\\csqs\\"; absoluteFilePathB =
	 * absoluteFilePath + "\\finished csqs\\"; } else if (your_os.indexOf("nix")
	 * >= 0 || your_os.indexOf("nux") >= 0 || your_os.indexOf("mac") >= 0) {
	 * absoluteFilePath = workingDir + "/csqs/"; absoluteFilePathB =
	 * absoluteFilePath + "/finished csqs/"; } else { absoluteFilePath =
	 * workingDir + "/csqs/"; absoluteFilePathB = absoluteFilePath +
	 * "/finished csqs/"; } File fileDir = new File(absoluteFilePath); for
	 * (final File fileEntry : fileDir.listFiles()) {
	 * 
	 * String fileContents = null; if (fileEntry.isDirectory()) {
	 * listFilesForFolder(fileEntry); } else {
	 * 
	 * try { fileContents = readFile( absoluteFilePath + fileEntry.getName(),
	 * Charset.defaultCharset()); } catch (IOException e) {
	 * 
	 * e.printStackTrace(); } if (push("CSQ", fileContents) == true) { //
	 * MsgLog.write("Pushed: " + fileEntry.getName()); try {
	 * 
	 * File afile = new File(absoluteFilePath + fileEntry.getName()); File bfile
	 * = new File(absoluteFilePathB + fileEntry.getName()); File parentDir =
	 * bfile.getParentFile(); if (!parentDir.exists()) parentDir.mkdirs(); if
	 * (afile.renameTo(new File(absoluteFilePathB + afile.getName()))) {
	 * MsgLog.write("File moved successfully! \n"); } else {
	 * MsgLog.write("Failed to move file! \n"); }
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } } else { //
	 * JOptionPane.showMessageDialog( // UCCXTable.uccxTable, //
	 * "Something bad happened with: " // + fileEntry.getName(), "Oh crap!", 0);
	 * MsgLog.write("Something went wrong, check csq [" + fileEntry.getName() +
	 * "] ERROR: 267 \n"); }
	 * 
	 * }
	 * 
	 * }
	 * 
	 * return true; }
	 * 
	 * public static boolean pushAllCCGs() throws HeadlessException, IOException
	 * { // new String workingDir = System.getProperty("user.dir"); String
	 * absoluteFilePath = ""; String absoluteFilePathB = ""; String your_os =
	 * System.getProperty("os.name").toLowerCase(); if (your_os.indexOf("win")
	 * >= 0) { absoluteFilePath = workingDir + "\\ccgs\\"; absoluteFilePathB =
	 * absoluteFilePath + "\\finished ccgs\\"; } else if (your_os.indexOf("nix")
	 * >= 0 || your_os.indexOf("nux") >= 0 || your_os.indexOf("mac") >= 0) {
	 * absoluteFilePath = workingDir + "/ccgs/"; absoluteFilePathB =
	 * absoluteFilePath + "/finished ccgs/"; } else { absoluteFilePath =
	 * workingDir + "/ccgs/"; absoluteFilePathB = absoluteFilePath +
	 * "/finished ccgs/"; } File fileDir = new File(absoluteFilePath); for
	 * (final File fileEntry : fileDir.listFiles()) {
	 * 
	 * String fileContents = null; if (fileEntry.isDirectory()) {
	 * listFilesForFolder(fileEntry); } else {
	 * 
	 * try { fileContents = readFile( absoluteFilePath + fileEntry.getName(),
	 * Charset.defaultCharset()); } catch (IOException e) {
	 * 
	 * e.printStackTrace(); } if (push("CCG", fileContents) == true) { //
	 * MsgLog.write("Pushed: " + fileEntry.getName()); try {
	 * 
	 * File afile = new File(absoluteFilePath + fileEntry.getName()); File bfile
	 * = new File(absoluteFilePathB + fileEntry.getName()); File parentDir =
	 * bfile.getParentFile(); if (!parentDir.exists()) parentDir.mkdirs(); if
	 * (afile.renameTo(new File(absoluteFilePathB + afile.getName()))) {
	 * MsgLog.write("File moved successfully! \n"); } else {
	 * MsgLog.write("Failed to move file! \n"); }
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } } else { //
	 * JOptionPane.showMessageDialog( // UCCXTable.uccxTable, //
	 * "Something bad happened with: " // + fileEntry.getName(), "Oh crap!", 0);
	 * MsgLog.write("Something went wrong, check ccg [" + fileEntry.getName() +
	 * "] ERROR: 268 \n"); }
	 * 
	 * }
	 * 
	 * }
	 * 
	 * return true; }
	 * 
	 * public static boolean pushAllRGs() throws HeadlessException, IOException
	 * { // new String workingDir = System.getProperty("user.dir"); String
	 * absoluteFilePath = ""; String absoluteFilePathB = ""; String your_os =
	 * System.getProperty("os.name").toLowerCase(); if (your_os.indexOf("win")
	 * >= 0) { absoluteFilePath = workingDir + "\\resource groups\\";
	 * absoluteFilePathB = absoluteFilePath + "\\finished resource groups\\"; }
	 * else if (your_os.indexOf("nix") >= 0 || your_os.indexOf("nux") >= 0 ||
	 * your_os.indexOf("mac") >= 0) { absoluteFilePath = workingDir +
	 * "/resource groups/"; absoluteFilePathB = absoluteFilePath +
	 * "/finished resource groups/"; } else { absoluteFilePath = workingDir +
	 * "/resource groups/"; absoluteFilePathB = absoluteFilePath +
	 * "/finished resource groups/"; } File fileDir = new
	 * File(absoluteFilePath); for (final File fileEntry : fileDir.listFiles())
	 * {
	 * 
	 * String fileContents = null; if (fileEntry.isDirectory()) {
	 * listFilesForFolder(fileEntry); } else {
	 * 
	 * try { fileContents = readFile( absoluteFilePath + fileEntry.getName(),
	 * Charset.defaultCharset()); } catch (IOException e) {
	 * 
	 * e.printStackTrace(); } if (push("RGroup",fileContents) == true) { //
	 * MsgLog.write("Pushed: " + fileEntry.getName()); try {
	 * 
	 * File afile = new File(absoluteFilePath + fileEntry.getName()); File bfile
	 * = new File(absoluteFilePathB + fileEntry.getName()); File parentDir =
	 * bfile.getParentFile(); if (!parentDir.exists()) parentDir.mkdirs(); if
	 * (afile.renameTo(new File(absoluteFilePathB + afile.getName()))) {
	 * MsgLog.write("File moved successfully! \n"); } else {
	 * MsgLog.write("Failed to move file! \n"); }
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } } else { //
	 * JOptionPane.showMessageDialog( // UCCXTable.uccxTable, //
	 * "Something bad happened with: " // + fileEntry.getName(), "Oh crap!", 0);
	 * MsgLog.write("Something went wrong, check resource group [" +
	 * fileEntry.getName() + "] ERROR: 269 \n"); }
	 * 
	 * }
	 * 
	 * }
	 * 
	 * return true; }
	 * 
	 * public static boolean pushAllSkills() throws HeadlessException,
	 * IOException { // new String workingDir = System.getProperty("user.dir");
	 * String absoluteFilePath = ""; String absoluteFilePathB = ""; String
	 * your_os = System.getProperty("os.name").toLowerCase(); if
	 * (your_os.indexOf("win") >= 0) { absoluteFilePath = workingDir +
	 * "\\skills\\"; absoluteFilePathB = absoluteFilePath +
	 * "\\finished skills\\"; } else if (your_os.indexOf("nix") >= 0 ||
	 * your_os.indexOf("nux") >= 0 || your_os.indexOf("mac") >= 0) {
	 * absoluteFilePath = workingDir + "/skills/"; absoluteFilePathB =
	 * absoluteFilePath + "/finished skills/"; } else { absoluteFilePath =
	 * workingDir + "/skills/"; absoluteFilePathB = absoluteFilePath +
	 * "/finished skills/"; } File fileDir = new File(absoluteFilePath); for
	 * (final File fileEntry : fileDir.listFiles()) {
	 * 
	 * String fileContents = null; if (fileEntry.isDirectory()) {
	 * listFilesForFolder(fileEntry); } else {
	 * 
	 * try { fileContents = readFile( absoluteFilePath + fileEntry.getName(),
	 * Charset.defaultCharset()); } catch (IOException e) {
	 * 
	 * e.printStackTrace(); } if (push("Skill", fileContents) == true) { //
	 * MsgLog.write("Pushed: " + fileEntry.getName()); try {
	 * 
	 * File afile = new File(absoluteFilePath + fileEntry.getName()); File bfile
	 * = new File(absoluteFilePathB + fileEntry.getName()); File parentDir =
	 * bfile.getParentFile(); if (!parentDir.exists()) parentDir.mkdirs(); if
	 * (afile.renameTo(new File(absoluteFilePathB + afile.getName()))) {
	 * MsgLog.write("File moved successfully! \n"); } else {
	 * MsgLog.write("Failed to move file! \n"); }
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } } else { //
	 * JOptionPane.showMessageDialog( // UCCXTable.uccxTable, //
	 * "Something bad happened with: " // + fileEntry.getName(), "Oh crap!", 0);
	 * MsgLog.write("Something went wrong, check skill [" + fileEntry.getName()
	 * + "] ERROR: 270 \n"); }
	 * 
	 * }
	 * 
	 * }
	 * 
	 * return true; }
	 * 
	 * public static boolean pushAllTriggers() throws HeadlessException,
	 * IOException { // new String workingDir = System.getProperty("user.dir");
	 * String absoluteFilePath = ""; String absoluteFilePathB = ""; String
	 * your_os = System.getProperty("os.name").toLowerCase(); if
	 * (your_os.indexOf("win") >= 0) { absoluteFilePath = workingDir +
	 * "\\triggers\\"; absoluteFilePathB = absoluteFilePath +
	 * "\\finished triggers\\"; } else if (your_os.indexOf("nix") >= 0 ||
	 * your_os.indexOf("nux") >= 0 || your_os.indexOf("mac") >= 0) {
	 * absoluteFilePath = workingDir + "/triggers/"; absoluteFilePathB =
	 * absoluteFilePath + "/finished triggers/"; } else { absoluteFilePath =
	 * workingDir + "/triggers/"; absoluteFilePathB = absoluteFilePath +
	 * "/finished triggers/"; } File fileDir = new File(absoluteFilePath); for
	 * (final File fileEntry : fileDir.listFiles()) {
	 * 
	 * String fileContents = null; if (fileEntry.isDirectory()) {
	 * listFilesForFolder(fileEntry); } else {
	 * 
	 * try { fileContents = readFile( absoluteFilePath + fileEntry.getName(),
	 * Charset.defaultCharset()); } catch (IOException e) {
	 * 
	 * e.printStackTrace(); } if (push("Trigger",fileContents) == true) { //
	 * MsgLog.write("Pushed: " + fileEntry.getName()); try {
	 * 
	 * File afile = new File(absoluteFilePath + fileEntry.getName()); File bfile
	 * = new File(absoluteFilePathB + fileEntry.getName()); File parentDir =
	 * bfile.getParentFile(); if (!parentDir.exists()) parentDir.mkdirs(); if
	 * (afile.renameTo(new File(absoluteFilePathB + afile.getName()))) {
	 * MsgLog.write("File moved successfully! \n"); } else {
	 * MsgLog.write("Failed to move file! \n"); }
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } } else { //
	 * JOptionPane.showMessageDialog( // UCCXTable.uccxTable, //
	 * "Something bad happened with: " // + fileEntry.getName(), "Oh crap!", 0);
	 * MsgLog.write("Something went wrong, check trigger [" +
	 * fileEntry.getName() + "] ERROR: 271 \n"); }
	 * 
	 * }
	 * 
	 * }
	 * 
	 * return true; }
	 */

	public static boolean push(String type, String xmlData) throws IOException {
		String https_url = "";
		String workingDir = System.getProperty("user.dir");
		String absoluteFilePath = "";
		String your_os = System.getProperty("os.name").toLowerCase();
		String csqRefLink = "";
		String ccgRefLink = "";
		String skillRefLink = "";
		int responseCode = 0;
		String responseMessage = "";
		String response = "";
		String input = "";
		String authString = Methods.getKeys("pushuser") + ":"
				+ Methods.getKeys("pushpass");
		if (type.equalsIgnoreCase("App")) {
			https_url = "https://" + Methods.getKeys("puship")
					+ "/adminapi/application";
		} else if (type.equalsIgnoreCase("CSQ")) {
			https_url = "https://" + Methods.getKeys("puship")
					+ "/adminapi/csq";
			csqRefLink = Methods.substringBetween(xmlData, "<self>", "</self>");
			for (int i = 0; i < Variables.skillRefLinks.length; i++) {
				if (xmlData.contains("<refURL>" + Variables.newSkillRows[i][1]
						+ "</refURL>")) {
					xmlData = xmlData.replace("<refURL>"
							+ Variables.newSkillRows[i][1] + "</refURL>",
							"<refURL>" + Variables.newSkillRows[i][2]
									+ "</refURL>");
				}
			}
		} else if (type.equalsIgnoreCase("CCG")) {
			https_url = "https://" + Methods.getKeys("puship")
					+ "/adminapi/callControlGroup";
			ccgRefLink = Methods.substringBetween(xmlData,
					"<self rel=\"self\" href=\"",
					"\" type=\"callControlGroup\"/>");
		} else if (type.equalsIgnoreCase("RGroup")) {
			https_url = "https://" + Methods.getKeys("puship")
					+ "/adminapi/resourceGroup";
		} else if (type.equalsIgnoreCase("Skill")) {
			https_url = "https://" + Methods.getKeys("puship")
					+ "/adminapi/skill";
			skillRefLink = Methods.substringBetween(xmlData, "<self>",
					"</self>");
		} else if (type.equalsIgnoreCase("Team")) {
			https_url = "https://" + Methods.getKeys("puship")
					+ "/adminapi/team";
			if (!xmlData.contains("<primarySupervisor name=\"")) {
				xmlData = xmlData
						.replace(
								"</teamname>",
								"</teamname><primarySupervisor name=\"Scott Fenech\"><refURL>https://"
										+ Methods.getKeys("puship")
										+ "/adminapi/resource/204087</refURL></primarySupervisor>");
			}
			for (int i = 0; i < Variables.newCSQRows.length; i++) {
				if (xmlData.contains("<csq name=\""
						+ Variables.newCSQRows[i][0] + "\"><refURL>"
						+ Variables.newCSQRows[i][1] + "</refURL></csq>")) {
					xmlData = xmlData.replace("<csq name=\""
							+ Variables.newCSQRows[i][0] + "\"><refURL>"
							+ Variables.newCSQRows[i][1] + "</refURL></csq>",
							"<csq name=\"" + Variables.newCSQRows[i][0]
									+ "\"><refURL>"
									+ Variables.newCSQRows[i][2]
									+ "</refURL></csq>");
				}
			}
		} else if (type.equalsIgnoreCase("Trigger")) {
			https_url = "https://" + Methods.getKeys("puship")
					+ "/adminapi/trigger";
		} else {
			https_url = "<NULL>";
		}
		//XXX: Why do this only in teams? Change it globally
		if (xmlData.contains("<refURL>https://" + Methods.getKeys("ip")
				+ "/")) {
			xmlData = xmlData.replace(
					"<refURL>https://" + Methods.getKeys("ip") + "/",
					"<refURL>https://" + Methods.getKeys("puship") + "/");
		}
		if (your_os.indexOf("win") >= 0) {
			if (type.equalsIgnoreCase("App")) {
				absoluteFilePath = workingDir
						+ "\\finished applications\\"
						+ "\\parsed applications\\"
						+ Methods.substringBetween(xmlData,
								"<applicationName>", "</applicationName>")
						+ ".xml";
			} else if (type.equalsIgnoreCase("CSQ")) {
				absoluteFilePath = workingDir
						+ "\\finished csqs\\"
						+ "\\parsed csqs\\"
						+ Methods
								.substringBetween(xmlData, "<name>", "</name>")
						+ ".xml";
			} else if (type.equalsIgnoreCase("CCG")) {
				absoluteFilePath = workingDir
						+ "\\finished ccg\\"
						+ "\\parsed ccg\\"
						+ Methods
								.substringBetween(xmlData, "<name>", "</name>")
						+ ".xml";
			} else if (type.equalsIgnoreCase("RGroup")) {
				absoluteFilePath = workingDir
						+ "\\finished resource groups\\"
						+ "\\parsed resource groups\\"
						+ Methods
								.substringBetween(xmlData, "<name>", "</name>")
						+ ".xml";
			} else if (type.equalsIgnoreCase("Skill")) {
				absoluteFilePath = workingDir
						+ "\\finished skills\\"
						+ "\\parsed skills\\"
						+ Methods.substringBetween(xmlData, "<skillName>",
								"</skillName>") + ".xml";
			} else if (type.equalsIgnoreCase("Team")) {
				absoluteFilePath = workingDir
						+ "\\finished teams\\"
						+ "\\parsed teams\\"
						+ Methods.substringBetween(xmlData, "<teamname>",
								"</teamname>") + ".xml";
			} else if (type.equalsIgnoreCase("Trigger")) {
				absoluteFilePath = workingDir
						+ "\\finished triggers\\"
						+ "\\parsed triggers\\"
						+ Methods.substringBetween(xmlData,
								"<directoryNumber>", "</directoryNumber>")
						+ ".xml";
			} else {
				absoluteFilePath = "<NULL>";
			}
		} else if (your_os.indexOf("nix") >= 0 || your_os.indexOf("nux") >= 0
				|| your_os.indexOf("mac") >= 0) {
			if (type.equalsIgnoreCase("App")) {
				absoluteFilePath = workingDir
						+ "/finished applications/"
						+ "/parsed applications/"
						+ Methods.substringBetween(xmlData,
								"<applicationName>", "</applicationName>")
						+ ".xml";
			} else if (type.equalsIgnoreCase("CSQ")) {
				absoluteFilePath = workingDir
						+ "/finished csqs/"
						+ "/arsed csqs/"
						+ Methods
								.substringBetween(xmlData, "<name>", "</name>")
						+ ".xml";
			} else if (type.equalsIgnoreCase("CCG")) {
				absoluteFilePath = workingDir
						+ "/finished ccg/"
						+ "/parsed ccg/"
						+ Methods
								.substringBetween(xmlData, "<name>", "</name>")
						+ ".xml";
			} else if (type.equalsIgnoreCase("RGroup")) {
				absoluteFilePath = workingDir
						+ "/finished resource groups/"
						+ "/parsed resource groups/"
						+ Methods
								.substringBetween(xmlData, "<name>", "</name>")
						+ ".xml";
			} else if (type.equalsIgnoreCase("Skill")) {
				absoluteFilePath = workingDir
						+ "/finished skills/"
						+ "/parsed skills/"
						+ Methods.substringBetween(xmlData, "<skillName>",
								"</skillName>") + ".xml";
			} else if (type.equalsIgnoreCase("Team")) {
				absoluteFilePath = workingDir
						+ "/finished teams/"
						+ "/parsed teams/"
						+ Methods.substringBetween(xmlData, "<teamname>",
								"</teamname>") + ".xml";
			} else if (type.equalsIgnoreCase("Trigger")) {
				absoluteFilePath = workingDir
						+ "/finished triggers/"
						+ "/parsed triggers/"
						+ Methods.substringBetween(xmlData,
								"<directoryNumber>", "</directoryNumber>")
						+ ".xml";
			} else {
				absoluteFilePath = "<NULL>";
			}
		} else {
			if (type.equalsIgnoreCase("App")) {
				absoluteFilePath = workingDir
						+ "/finished applications/"
						+ "/parsed applications/"
						+ Methods.substringBetween(xmlData,
								"<applicationName>", "</applicationName>")
						+ ".xml";
			} else if (type.equalsIgnoreCase("CSQ")) {
				absoluteFilePath = workingDir
						+ "/finished csqs/"
						+ "/arsed csqs/"
						+ Methods
								.substringBetween(xmlData, "<name>", "</name>")
						+ ".xml";
			} else if (type.equalsIgnoreCase("CCG")) {
				absoluteFilePath = workingDir
						+ "/finished ccg/"
						+ "/parsed ccg/"
						+ Methods
								.substringBetween(xmlData, "<name>", "</name>")
						+ ".xml";
			} else if (type.equalsIgnoreCase("RGroup")) {
				absoluteFilePath = workingDir
						+ "/finished resource groups/"
						+ "/parsed resource groups/"
						+ Methods
								.substringBetween(xmlData, "<name>", "</name>")
						+ ".xml";
			} else if (type.equalsIgnoreCase("Skill")) {
				absoluteFilePath = workingDir
						+ "/finished skills/"
						+ "/parsed skills/"
						+ Methods.substringBetween(xmlData, "<skillName>",
								"</skillName>") + ".xml";
			} else if (type.equalsIgnoreCase("Team")) {
				absoluteFilePath = workingDir
						+ "/finished teams/"
						+ "/parsed teams/"
						+ Methods.substringBetween(xmlData, "<teamname>",
								"</teamname>") + ".xml";
			} else if (type.equalsIgnoreCase("Trigger")) {
				absoluteFilePath = workingDir
						+ "/finished triggers/"
						+ "/parsed triggers/"
						+ Methods.substringBetween(xmlData,
								"<directoryNumber>", "</directoryNumber>")
						+ ".xml";
			} else {
				absoluteFilePath = "<NULL>";
			}
		}
		final byte[] authEncBytes = authString.getBytes(StandardCharsets.UTF_8);
		String Base64String = Base64.getEncoder().encodeToString(authEncBytes);
		SSLContext sc = null;
		try {
			sc = SSLContext.getInstance("TLS");
		} catch (NoSuchAlgorithmException e3) {

			e3.printStackTrace();
		}
		try {
			sc.init(null,
					new TrustManager[] { new TrustAllX509TrustManager() },
					new java.security.SecureRandom());
		} catch (KeyManagementException e2) {
			e2.printStackTrace();
		}
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
			@Override
			public boolean verify(String string, SSLSession ssls) {
				return true;
			}
		});
		xmlData = xmlData.replace("+", "%20");
		if (xmlData.contains(Methods.getKeys("ip"))) {
			xmlData = xmlData.replace(Methods.getKeys("ip"),
					Methods.getKeys("puship"));
		}
		URL url = new URL(https_url);
		HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "text/xml");
		con.setRequestProperty("Content-Length",
				Integer.toString(xmlData.getBytes().length));
		con.setRequestProperty("Authorization", "Basic " + Base64String);
		OutputStream os = con.getOutputStream();// To make the request
		os.write(xmlData.getBytes());
		try {
			File myFile = new File(absoluteFilePath);
			File parentDir = myFile.getParentFile();
			if (!parentDir.exists())
				parentDir.mkdirs();
			Writer w = new OutputStreamWriter(new FileOutputStream(myFile),
					"UTF-8");
			w.write(xmlData);
			w.flush();
			w.close();
			// Response Log
			BufferedReader br = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			while ((input = br.readLine()) != null) {
				response = input;
			}
			br.close();
			responseCode = con.getResponseCode();
			responseMessage = con.getResponseMessage();
			con.disconnect();
			if (type.equalsIgnoreCase("CSQ")) {
				for (int i = 0; i < Variables.csqRefLinks.length; i++) {
					if (csqRefLink.equalsIgnoreCase(Variables.newCSQRows[i][1])) {
						Variables.newCSQRows[i][2] = response;
					}
				}
			} else if (type.equalsIgnoreCase("CCG")) {
				for (int i = 0; i < Variables.ccgRefLinks.length; i++) {
					if (ccgRefLink.equalsIgnoreCase(Variables.newCCGRows[i][1])) {
						Variables.newCCGRows[i][2] = response;
					}
				}
			} else if (type.equalsIgnoreCase("Skill")) {
				for (int i = 0; i < Variables.skillRefLinks.length; i++) {
					if (skillRefLink
							.equalsIgnoreCase(Variables.newSkillRows[i][1])) {
						Variables.newSkillRows[i][2] = response;
					}
				}
			} else if (type.equalsIgnoreCase("Trigger")) {
				if (Variables.replaceCCGVariables != null) {
					for (int i = 0; i < Variables.replaceCCGVariables.length; i++) {
						String oldValue = Variables.replaceCCGVariables[i][0];
						String newValue = Variables.replaceCCGVariables[i][1];
						if (xmlData.contains("<callControlGroup name=\""
								+ oldValue + "\">")) {
							xmlData = xmlData.replace(
									"<callControlGroup name=\"" + oldValue
											+ "\">",
									"<callControlGroup name=\"" + newValue
											+ "\">");
						}
						if (xmlData.contains("https://" + Methods.getKeys("ip")
								+ "/adminapi/callControlGroup/" + oldValue)) {
							xmlData = xmlData.replace(
									"https://" + Methods.getKeys("ip")
											+ "/adminapi/callControlGroup/"
											+ oldValue,
									"https://" + Methods.getKeys("puship")
											+ "/adminapi/callControlGroup/"
											+ newValue);
						}
					}
				}
				if (xmlData.contains("> ") || xmlData.contains(" <")) {
					xmlData = xmlData.replace("> ", ">");
					xmlData = xmlData.replace(" <", "<");
				}
				if (xmlData.contains("<partition>Internal-PT</partition>")) {
					xmlData = xmlData.replace(
							"<partition>Internal-PT</partition>",
							"<partition>GBL-Internal-PT</partition>");
				}
			}
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if (responseCode == HttpURLConnection.HTTP_ACCEPTED
				|| responseCode == HttpURLConnection.HTTP_CREATED
				|| responseCode == HttpURLConnection.HTTP_OK) {
			if (type.equalsIgnoreCase("Trigger")) {
				MsgLog.write("Successfully pushed trigger ["
						+ Methods.substringBetween(xmlData,
								"<directoryNumber>", "</directoryNumber>")
						+ "] \n");
			} else if (type.equalsIgnoreCase("Skill")) {
				MsgLog.write("Successfully pushed skill ["
						+ Methods.substringBetween(xmlData, "<skillName>",
								"</skillName>") + "] \n");
			} else if (type.equalsIgnoreCase("RGroup")) {
				MsgLog.write("Successfully pushed resource group ["
						+ Methods
								.substringBetween(xmlData, "<name>", "</name>")
						+ "] \n");
			} else if (type.equalsIgnoreCase("CCG")) {
				MsgLog.write("Successfully pushed ccg ["
						+ Methods
								.substringBetween(xmlData, "<name>", "</name>")
						+ "] \n");
			} else if (type.equalsIgnoreCase("CSQ")) {
				MsgLog.write("Successfully pushed csq ["
						+ Methods
								.substringBetween(xmlData, "<name>", "</name>")
						+ "] \n");
			} else if (type.equalsIgnoreCase("App")) {
				MsgLog.write("Successfully pushed application ["
						+ Methods.substringBetween(xmlData,
								"<applicationName>", "</applicationName>")
						+ "] \n");
			} else if (type.equalsIgnoreCase("Team")) {
				MsgLog.write("Successfully pushed team ["
						+ Methods.substringBetween(xmlData, "<teamname>",
								"</teamname>") + "] \n");
			} else {
				MsgLog.write("Successfully pushed something! \n");
			}
			UCCXLogGUI.setLogText(Variables.logString);
			return true;
		} else {
			MsgLog.write(responseMessage + "\n");
			UCCXLogGUI.setLogText(Variables.logString);
			return false;
		}
	}

	public static boolean pushAll(String type) throws HeadlessException,
			IOException {
		String workingDir = System.getProperty("user.dir");
		String absoluteFilePath = "";
		String absoluteFilePathB = "";
		String your_os = System.getProperty("os.name").toLowerCase();
		if (your_os.indexOf("win") >= 0) {
			if (type.equalsIgnoreCase("App")) {
				absoluteFilePath = workingDir + "\\applications\\";
				absoluteFilePathB = absoluteFilePath
						+ "\\finished applications\\";
			} else if (type.equalsIgnoreCase("CSQ")) {
				absoluteFilePath = workingDir + "\\csqs\\";
				absoluteFilePathB = absoluteFilePath + "\\finished csqs\\";
			} else if (type.equalsIgnoreCase("CCG")) {
				absoluteFilePath = workingDir + "\\ccgs\\";
				absoluteFilePathB = absoluteFilePath + "\\finished ccgs\\";
			} else if (type.equalsIgnoreCase("RGroup")) {
				absoluteFilePath = workingDir + "\\resource groups\\";
				absoluteFilePathB = absoluteFilePath
						+ "\\finished resource groups\\";
			} else if (type.equalsIgnoreCase("Skill")) {
				absoluteFilePath = workingDir + "\\skills\\";
				absoluteFilePathB = absoluteFilePath + "\\finished skills\\";
			} else if (type.equalsIgnoreCase("Team")) {
				absoluteFilePath = workingDir + "\\teams\\";
				absoluteFilePathB = absoluteFilePath + "\\finished teams\\";
			} else if (type.equalsIgnoreCase("Trigger")) {
				absoluteFilePath = workingDir + "\\triggers\\";
				absoluteFilePathB = absoluteFilePath + "\\finished triggers\\";
			}
		} else if (your_os.indexOf("nix") >= 0 || your_os.indexOf("nux") >= 0
				|| your_os.indexOf("mac") >= 0) {
			if (type.equalsIgnoreCase("App")) {
				absoluteFilePath = workingDir + "/applications/";
				absoluteFilePathB = absoluteFilePath
						+ "/finished applications/";
			} else if (type.equalsIgnoreCase("CSQ")) {
				absoluteFilePath = workingDir + "/csqs/";
				absoluteFilePathB = absoluteFilePath + "/finished csqs/";
			} else if (type.equalsIgnoreCase("CCG")) {
				absoluteFilePath = workingDir + "/ccgs/";
				absoluteFilePathB = absoluteFilePath + "/finished ccgs/";
			} else if (type.equalsIgnoreCase("RGroup")) {
				absoluteFilePath = workingDir + "/resource groups/";
				absoluteFilePathB = absoluteFilePath
						+ "/finished resource groups/";
			} else if (type.equalsIgnoreCase("Skill")) {
				absoluteFilePath = workingDir + "/skills/";
				absoluteFilePathB = absoluteFilePath + "/finished skills/";
			} else if (type.equalsIgnoreCase("Team")) {
				absoluteFilePath = workingDir + "/teams/";
				absoluteFilePathB = absoluteFilePath + "/finished teams/";
			} else if (type.equalsIgnoreCase("Trigger")) {
				absoluteFilePath = workingDir + "/triggers/";
				absoluteFilePathB = absoluteFilePath + "/finished triggers/";
			}
		} else {
			if (type.equalsIgnoreCase("App")) {
				absoluteFilePath = workingDir + "/applications/";
				absoluteFilePathB = absoluteFilePath
						+ "/finished applications/";
			} else if (type.equalsIgnoreCase("CSQ")) {
				absoluteFilePath = workingDir + "/csqs/";
				absoluteFilePathB = absoluteFilePath + "/finished csqs/";
			} else if (type.equalsIgnoreCase("CCG")) {
				absoluteFilePath = workingDir + "/ccgs/";
				absoluteFilePathB = absoluteFilePath + "/finished ccgs/";
			} else if (type.equalsIgnoreCase("RGroup")) {
				absoluteFilePath = workingDir + "/resource groups/";
				absoluteFilePathB = absoluteFilePath
						+ "/finished resource groups/";
			} else if (type.equalsIgnoreCase("Skill")) {
				absoluteFilePath = workingDir + "/skills/";
				absoluteFilePathB = absoluteFilePath + "/finished skills/";
			} else if (type.equalsIgnoreCase("Team")) {
				absoluteFilePath = workingDir + "/teams/";
				absoluteFilePathB = absoluteFilePath + "/finished teams/";
			} else if (type.equalsIgnoreCase("Trigger")) {
				absoluteFilePath = workingDir + "/triggers/";
				absoluteFilePathB = absoluteFilePath + "/finished triggers/";
			}
		}
		File fileDir = new File(absoluteFilePath);
		for (final File fileEntry : fileDir.listFiles()) {
			String fileContents = null;
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry);
			} else {
				try {
					fileContents = readFile(
							absoluteFilePath + fileEntry.getName(),
							Charset.defaultCharset());
				} catch (IOException e) {

					e.printStackTrace();
				}
				int successCode = 0; // 0 = No change, 1 = Success, 2 = Fail
				if (push(type, fileContents)) {
					successCode = 1;
				} else {
					successCode = 2;
				}
				if (successCode == 1) {
					try {
						File afile = new File(absoluteFilePath
								+ fileEntry.getName());
						File bfile = new File(absoluteFilePathB
								+ fileEntry.getName());
						File parentDir = bfile.getParentFile();
						if (!parentDir.exists())
							parentDir.mkdirs();
						if (afile.renameTo(new File(absoluteFilePathB
								+ afile.getName()))) {
							MsgLog.write("File moved successfully! \n");
						} else {
							MsgLog.write("Failed to move file! \n");
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					if (type.equalsIgnoreCase("App")) {
						MsgLog.write("Something went wrong, check Application ["
								+ fileEntry.getName() + "] ERROR: 200 \n");
					} else if (type.equalsIgnoreCase("CSQ")) {
						MsgLog.write("Something went wrong, check CSQ ["
								+ fileEntry.getName() + "] ERROR: 210 \n");
					} else if (type.equalsIgnoreCase("CCG")) {
						MsgLog.write("Something went wrong, check CCG ["
								+ fileEntry.getName() + "] ERROR: 220 \n");
					} else if (type.equalsIgnoreCase("RGroup")) {
						MsgLog.write("Something went wrong, check Resource Group ["
								+ fileEntry.getName() + "] ERROR: 230 \n");
					} else if (type.equalsIgnoreCase("Skill")) {
						MsgLog.write("Something went wrong, check Skill ["
								+ fileEntry.getName() + "] ERROR: 240 \n");
					} else if (type.equalsIgnoreCase("Team")) {
						MsgLog.write("Something went wrong, check Team ["
								+ fileEntry.getName() + "] ERROR: 250 \n");
					} else if (type.equalsIgnoreCase("Trigger")) {
						MsgLog.write("Something went wrong, check Trigger ["
								+ fileEntry.getName() + "] ERROR: 260 \n");
					}
				}

			}

		}
		return true;
	}

	public static String getFileContents() {
		String authString = Methods.getKeys("user") + ":"
				+ Methods.getKeys("pass");
		final byte[] authEncBytes = authString.getBytes(StandardCharsets.UTF_8);
		String Base64String = Base64.getEncoder().encodeToString(authEncBytes);
		String input = "";
		String response = "";
		SSLContext sc = null;
		try {
			sc = SSLContext.getInstance("TLS");
		} catch (NoSuchAlgorithmException e3) {

			e3.printStackTrace();
		}
		try {
			sc.init(null,
					new TrustManager[] { new TrustAllX509TrustManager() },
					new java.security.SecureRandom());
		} catch (KeyManagementException e2) {

			e2.printStackTrace();
		}
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
			@Override
			public boolean verify(String string, SSLSession ssls) {
				return true;
			}
		});
		String https_url = "https://10.0.0.93:8080/adminapi/script/download/default/%5BMIVR_10.109.3.112%5DCSO-800SeagateKorea.aef";
		URL url;
		try {

			url = new URL(https_url);
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
			con.setDoInput(true);
			con.setDoOutput(true);

			/*
			 * con.setRequestProperty("Authorization",
			 * "Basic YXBwYWRtaW46TGluZWNvZGViOHpz");
			 */
			con.setRequestProperty("Authorization", "Basic " + Base64String);
			// Response Log
			BufferedReader br = new BufferedReader(new InputStreamReader(
					con.getInputStream()));

			while ((input = br.readLine()) != null) {
				//
				response = input;
			}
			br.close();

			MsgLog.write(response);
			con.disconnect();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return response;
	}

	public static String readFile(String path, Charset encoding)
			throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

	// Method: substringBetween
	public static String substringBetween(String str, String open, String close) {
		if ((str == null) || (open == null) || (close == null)) {
			return null;
		}
		int start = str.indexOf(open);
		if (start != -1) {
			int end = str.indexOf(close, start + open.length());
			if (end != -1) {
				return str.substring(start + open.length(), end);
			}
		}
		return null;
	}

	// Method: substringsBetween
	public static String[] substringsBetween(String str, String open,
			String close) {
		if ((str == null) || (open == null) || (close == null)) {
			return null;
		}
		int strLen = str.length();
		if (strLen == 0) {
			return null;
		}
		int closeLen = close.length();
		int openLen = open.length();
		List<String> list = new ArrayList<String>();
		int pos = 0;
		while (pos < strLen - closeLen) {
			int start = str.indexOf(open, pos);
			if (start < 0) {
				break;
			}
			start += openLen;
			int end = str.indexOf(close, start);
			if (end < 0) {
				break;
			}
			list.add(str.substring(start, end));
			pos = end + closeLen;
		}
		if (list.isEmpty()) {
			return null;
		}
		return list.toArray(new String[list.size()]);
	}

	// Method: substringsBetween (Delimiter)
	public static String[] substringsBetween(String str, String open,
			String close, String delimiter) {
		if ((str == null) || (open == null) || (close == null)) {
			return null;
		}
		int strLen = str.length();
		if (strLen == 0) {
			return null;
		}
		int closeLen = close.length();
		int openLen = open.length();
		List<String> list = new ArrayList<String>();
		int pos = 0;
		while (pos < strLen - closeLen) {
			int start = str.indexOf(open, pos);
			if (start < 0) {
				break;
			}
			start += openLen;
			int end = str.indexOf(close, start);
			if (end < 0) {
				break;
			}
			if (!str.substring(start, end).contains(delimiter)) {
				list.add(str.substring(start, end));

			}
			pos = end + closeLen;
		}
		if (list.isEmpty()) {
			return null;
		}
		return list.toArray(new String[list.size()]);
	}

}
