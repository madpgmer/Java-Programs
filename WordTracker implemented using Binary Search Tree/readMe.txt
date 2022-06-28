/****************************************************************************************************************
*Program: 	WordTracker implemented using Binary Search Tree (BST)							
*Author: 	Madhu Madhavan									
*Date: 		April 18th 2022																						
*****************************************************************************************************************
**/

Program description:  
--------------------
This application is called WordTracker and is implemented using Binary Search Tree (BST). This application reads text files and collects and stores all the unique words it finds in those files.The BST will be able to store information from multiple text files. It will also keep track of each occurrence of a word in a file and the line on which it was found in that file. This information will be stored in a list for each word object stored in the BST. This program will also produce output, specified by the user at command line, to generate reports using a variety of iterators built into the BST.

BSTree.java and BSTreeNode.java has been implemented by the provided BSTreeADT.java and Iterator.java interfaces. A JUnit test was designed to test the implementation of the Binary Search Tree. Cross-reference program, WordTracker.java was created to construct a BST with all words included from a text file (instructor provided) and records the line numbers on which these words were used. The line numbers are stored with the file names, which in turn are associated with the nodes of the tree.

Using Java serialization techniques, the tree is stored in a binary file (repository.ser). Class version UIDs are insterted to ensure the backward compatibility with our repository in case the class specification change with future enhancements. Every time the program executes, it checks if the binary file (repository.ser) exists, and if so, restores the words tree. The results of scanning the next file are  inserted in the appropriate nodes of the tree. Therefore, this repository.ser will contain all words occurred in all files scanned with the meta information about those word locations.


How to run the application:
---------------------------
- The file needs to be run by an IDE such as Eclipse. 
- This program is executable by running the AppDriver java file that is located in the application folder under 'src'. 
- You can right click the AppDriver and select -Run As/Run Configurations. 
- Please remember to check if you are on the correct assignment and select the correct folder where your unsorted text file is located to avoid problems.
- In that dialog box click on the 'Arguments' tab next to 'Main' tab and enter your arguments as per the examples below 
- Example : res/textfile.txt.txt -pf -f res/output.txt

- You can ignore -f res/output.txt if you want to just output onto the console. 

- Remember to delete repository.ser file after reading multiple files because they keep adding up and keep serializing the files.

- You can run the program via the command line as follows: java -jar WordTracker.jar <input.txt> -pf/-pl/-po [-f <output.txt>]

- <input.txt> is the path and filename of the text file to be processed by the WordTracker program.

- 3 mutually exclusive options at command line:
-> -pf to print in alphabetic order all words along with the corresponding list of files in which the words occur.
-> -pl to print in alphabetic order all words along with the corresponding list of files and numbers of the lines in which the word occur.
-> -po to print in alphabetic order all words along with the corresponding list of files, numbers of the lines in which the word occur and the frequency of occurrence of the words.

- [-f <output.txt>] - Optional argument to redirect of the report in the previous step to the path and filename specified in <output.txt.