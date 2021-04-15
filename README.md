Reference Application Setup Process
===================================

This page is to detail the process for setting up the parser found here: https://github.com/albertopneto/parser.git  

Pre-Requisites
------------------------  

1. Java 8 installed and JAVA_HOME configured in the path
2. Gradle 6.3 installed and environment variable configured in the path

Project Setup
---------------------------
1. Open a command line and clone the project from the git in any directory
    git clone https://github.com/albertopneto/parser.git
2. Go to the cloned repository folder: parser
	Example: cd c:/assessment/parser
3. Run following command: gradle clean build
4. Coverage report is available, to access, type following URL in to the browser: 
	    file:///PATH_TO_EXTRACTED_FOLDER/parser/build/reports/jacoco/test/html/index.html	
	    PATH_TO_EXTRACTED_FOLDER is the place where the parser application was cloned

Running the parser
---------------------------
1. Open a new terminal (if not opened) and go to the database folder
	Example: cd c:/assessment/parser/database
2. Run following command: java -cp hsqldb-2.6.0-jdk8.jar org.hsqldb.server.Server --database.0 file:. -dbname.0 parserdb
3. Run following command: gradle bootRun --args "ABSOLUTE_PATH_TO_THE_FILE"
    Example: "C:\logs\logfile.txt"

Connecting to the database
---------------------------

1. Download the HSQL DB tool
2. Provide following information:
    URL: jdbc:hsqldb:hsql://localhost/database/parserdb
    username: sa
3. Go to the console and execute: select * from EVENT;
    You should be able to see the events into the table