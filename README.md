Reference Application Setup Process
===================================

This page is to detail the process for setting up the parser found here: https://github.com/albertopneto/parser.git  

Pre-Requisites
------------------------  

1. Java 8 installed and JAVA_HOME configured in the path
2. Gradle 6.3 installed and environment variable configured in the path

Project Setup
---------------------------
1. Open a command line and clone the project from the git in any directory <br />
	git clone https://github.com/albertopneto/parser.git
2. Go to the cloned repository folder parser <br />
	Example: cd c:/assessment/parser
3. Run following command: gradle clean build
4. Coverage report is available on PATH_TO_EXTRACTED_FOLDER/parser/build/reports/jacoco/test/html/index.html <br />
	PATH_TO_EXTRACTED_FOLDER is the place where the parser application was cloned

Running the parser
---------------------------
1. Open a new terminal (if not opened) and go to the database folder <br />
	Example: cd c:/assessment/parser/database
2. Run following command: <br />
	java -cp hsqldb-2.6.0-jdk8.jar org.hsqldb.server.Server --database.0 file:. -dbname.0 parserdb
4. Run following command: <br />
	gradle bootRun --args "ABSOLUTE_PATH_TO_THE_FILE" <br />
    	Example for the ABSOLUTE_PATH_TO_THE_FILE: "C:\logs\logfile.txt"

Connecting to the database
---------------------------

1. Download the HSQL DB tool
2. Provide following information: <br />
    	URL: jdbc:hsqldb:hsql://localhost/database/parserdb <br />
    	username: sa
3. Go to the console and execute: <br />
	select * from EVENT; <br />
    	You should be able to see the events into the table
