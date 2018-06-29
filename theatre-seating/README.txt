****Project Title****

Theatre Seating Application

****Getting Started****

These instructions will get you a copy of the project up and running on your local machine for executing and testing purposes. 
NOTE : The steps below are to run the application in WINDOWS. The example commands may vary for mac or UNIX

Prerequisites

1) Jdk 1.7
2) Maven 3.2 or above
3) Able to access Java and mvn from command prompt
4) Git installed

Installation

1) Download the code from Git remote repository
2) The first step should create a folder named theatre-seating in the folder where you unzipped the file
   Example : c:\<local_folder_path>\theatre-seating
   
Steps to compile the application
1) Open the command prompt
2) Navigate to the folder where you installed "theatre-seating" in local drive
   Example :
   c:> cd c:\<local_folder_path>\theatre-seating
3) Now you should be at the folder "theatre-seating" after step 2. 
   Example : c:\<local_folder_path>\theatre-seating>
4) Run the command from the prompt : 
      mvn clean install -Dmaven.test.skip=true
	Example : c:\<local_folder_path>\theatre-seating>mvn clean install -Dmaven.test.skip=true
	NOTE : 
	   - If mvn command is not recognised then please install maven and then run this step. 
	   - The above command skips the TEST
5) Steps to validate the result of Step 4 
     After Running Step 4 successfully you should able to see the following
	  - [INFO] ------------------------------------------------------------------------
           [INFO] BUILD SUCCESS
        [INFO] ------------------------------------------------------------------------
		
	  - It should create a folder name "target" under theatre-seating
	  - The "target" folder will have theatreSeating.jar which is the executable

Steps to run the application
1) After executing the above step you should have theatreSeating.jar in /target folder 
2) Open the command prompt
3) Navigate to "target" folder 
    Example : c:\<local_folder_path>\theatre-seating\target>
	NOTE : You can also copy the jar in some other local folder and run from there
4) Run the following command to start the application
   java -cp java -cp theatreSeating.jar com.assignment.theatreSeating.TheatreSeatingApplication
   
   Example : c:\<local_folder_path>\theatre-seating\target>java -cp java -cp theatreSeating.jar com.assignment.theatreSeating.TheatreSeatingApplication
   
   NOTE : If java is not recognised command then please go through the prerequisite step to install Java and then run the command
5) The above command in step 4 should execute and should able to see the following. Prompting the user to initialize the venue and start the reservation
     
	 "Do you want to see number of available seats Y/N:"

Steps to Test
1) Open the command prompt
2) Navigate to the folder where you installed "theatre-seating" in local drive
   Example :
   c:> cd c:\<local_folder_path>\theatre-seating
3) Now you should be at the folder "theatre-seating" after step 2. 
   Example : c:\<local_folder_path>\theatre-seating>
4) Run the command from the prompt : 
      mvn test
	Example : c:\<local_folder_path>\theatre-seating>mvn test
	NOTE : 
	   - If mvn command is not recognised then please install maven and then run this step. 
	   - The above command runs the test case
5) Steps to validate the result of Step 4 
     After Running Step 4 . If all test cases passed successfully the you will able to see the following in the console
	  - [INFO] Results:
        [INFO] Tests run: 6, Failures: 0, Errors: 0, Skipped: 0
		  - in com.assignment.theatreSeating.TheatreSeatingApplicationTests
	  - It should create a folder name "surefire-reports" under target folder

NOTE - Please ignore the following warning. As I have not initialized logger for Test.

"log4j:WARN No appenders could be found for logger (com.assignment.theatreSeating.impl.TheatreSeatingServiceImpl)."

NOTE : You can also run the test by giving command "mvn clean install -Dmaven.test.skip=true"
This will compile the code and create Jar only after TEST is successful

****Assumption****
1) The application is developed Considering only for single venue
2) The application is developed To support concurrency issues
3) The Setup of the venue or the theatre is done as per the Sample input from the main class

Sample input:
6 6
3 5 5 3
4 6 6 4
2 8 8 2
6 6 

****Testing****
Performance Testing:
Junit Test cases:
1) Written 11 test cases to cover various features
	

