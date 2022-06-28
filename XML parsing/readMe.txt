/****************************************************************************************************************
*Project: 	XML parsing															
*Author: 	Madhu Madhavan	
								
*Date: 		March 25th 2022																						
*****************************************************************************************************************
**/

Program description:  
--------------------
This program has a working version of an array list, a doubly linked list, a stack and a queue. These utility classes were used to implement the XML Parser that will read and confirm that XML files are properly formatted. Utility class MyArrayList.java was created using the supplied ListADT.java and Iterator.java interfaces. The implementation of MyArrayList.java has been tested completely for correct functionality using the set complete set  of JUnit tests in a class called MyStackTests.java

A complete set of JUnit test functions was created in a class called MyQueueTests.java using the TDD approach. Utility class MyDLL.java was created using the same supplied ListADT.java and Iterator.java interfaces and a complete set of JUnit test functions was created in a class called MyDLLTests.java

Utility class MyQueue.java was created  using the QueueADT.java and Iterator.java interfaces. Queue implementation use MyDLL.java that was implemented as the underlying data structure. I have included a complete set of JUnit test functions in a class called MyQueueTests.java, again using the TDD approach

Using the data structures created, I implemented XML document parser to parse for errors in the XML construction. The XMLParser.java prints all the lines that are not properly constructed in the order in which they occur along with their line numbers.

I have implement this project using ONLY the libraries that I developed for the MyStack, MyQueue, MyArrayList and MyDLL.


How to run the application:
---------------------------
The file needs to be run by an IDE such as Eclipse. 
This program is executable by running the AppDriver java file that is located in the application folder under 'src'. 
You can right click the AppDriver and select -Run As/Run Configurations. 
In that dialog box click on the 'Arguments' tab next to 'Main' tab and enter your arguments as per the examples below 
Example : res/sample2.xml

Before you do this please remember to check if you are on the correct assignment and select the correct folder where your unsorted text file is located to avoid problems.

The res folder contains the 2 XML files that were provided

