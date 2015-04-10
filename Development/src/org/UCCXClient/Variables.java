package org.UCCXClient;

public class Variables {
	public static String ip = null, user = null, pass = null, puship = null,
			pushuser = null, pushpass = null, logString = "";
	public static boolean connected, connectionFailed, pushconnected, pushconnectionFailed, showLog = false;

	public static String[] appNames = null, appTypes = null, appScripts = null,
			appDScripts = null, appIds = null, appDescs = null,
			appMaxSess = null, appEnableds = null;
	public static String[][] applicationTableRows;
	public static final String[] applicationTableColumns = { "#", " App Name",
			"App Type", "Script", "D Script", "App ID", "Description",
			"Max Sessions", "Enabled" };

	public static String[] teamNames = null, teamIds = null, teamPSupv = null,
			teamSSupv = null, teamResources = null, teamCSQs = null,
			teamNewRefLinks = null;
	public static String[][] teamTableRows;
	public static final String[] teamTableColumns = { "#", " Team Name",
			"Team ID", "Prim Supv", "Secd Supv", "Resources", "CSQs" };
	
	public static String[] rUserId = null;

	public static String[] skillNames = null, skillIds = null/*
															 * , skillNewRefLink
															 * = null
															 *//*
																 * Use this when
																 * setting csqs
																 */;
	public static String[][] skillTableRows, newSkillRows;
	public static final String[] skillTableColumns = { "#", "Skill Name",
			"Skill ID" };

	public static String[] csqNames = null, csqIds = null,
			csqQueueTypes = null, csqRoutingType = null,
			csqQueueAlgorithm = null, csqAutoworks = null,
			csqWrapupTimes = null, csqResourcePoolTypes = null,
			csqServiceLevels = null, csqServiceLevelPercentages = null,
			csqSelectionCriterias = null, csqPollingInts = null,
			csqSnapAges = null;
	public static String[][] csqTableRows, newCSQRows;
	public static final String[] csqTableColumns = { "#", "CSQ Name", "CSQ ID"/*
																			 * ,
																			 * "CSQ Q Type"
																			 * ,
																			 * "CSQ Route Type"
																			 * ,
																			 * "CSQ Q Alg."
																			 * ,
																			 * "CSQ Autowork"
																			 * ,
																			 * "CSQ Wrapup"
																			 * ,
																			 * "CSQ RP Type"
																			 * ,
																			 * "CSQ Serv. Lvl"
																			 * ,
																			 * "CSQ Serv. Lvl %"
																			 * ,
																			 * "CSQ Sel. Criteria"
																			 * ,
																			 * "CSQ Polling Int."
																			 * ,
																			 * "CSQ SnapS Age"
																			 */};

	public static String[] rgNames = null, rgIds = null;
	public static String[][] rgTableRows;
	public static final String[] rgTableColumns = { "#", "RG Name", "RG ID" };

	public static String[] ccgName = null, ccgId = null;
	public static String[][] ccgTableRows, newCCGRows, replaceCCGVariables = null;
	public static final String[] ccgTableColumns = { "#", "CCG Name", "CCG ID" };

	public static String[] trigNums = null, trigAppNames = null,
			trigDevNames = null, trigDesc = null, trigEnabled = null;
	public static String[][] trigTableRows;
	public static final String[] trigTableColumns = { "#", "Directory Number",
			"Application Name", "Device Name", "Description", "Enabled" };

	public static String[] teamRefLinks, resourceRefLinks, appRefLinks, skillRefLinks,
			csqRefLinks, rgRefLinks, trigRefLinks, ccgRefLinks;

}
