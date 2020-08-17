# Project Title
Project - Chinese Chess Game

## Getting Started
These instructions will lead you running project on your local machine for development and testing purposes.
<UserManual.txt> - ChineseChess folder
<readme.txt> - docs folder
<test.pdf> - tests folder

### Prerequisites
A terminal such as Linux Ubuntu(NOT WSL for this program), Command Prompt(cmd).
Client system that has JDK 8 or later installed.

## Running the tests
1. Unzips the ChineseChess.zip
2. Go the ChineseChess directory(root) and open the terminal
3. Type "cd script", go to script folder
4. Type one command argument: <script for your OS>

or:
 click on project.cmd if you have Windows
 Click on project.sh if you have Linux

************************************************************************************************************

### 
Example 1(for Operating System of Windows):
1. Go the project directory
2. For terminal 1(server), type "cd script" and go to script folder
3. Type "project.cmd"
(steps 1-3 can be replaced by click on project.cmd)
4. Click "Server" button
5. Enter a port number that is greater than 1024
6. For terminal 2(client), first repeat step 1-3
7. Click "Client" button
8. Enter the hostname and the corresponding port number
9. Server side is always "Han" side, which always goes first
10. Now you can move the chesses between client and server
11. When General "Shuai"(red side) or "Jiang"(han side) is removed by another chess, the game is over.

Example 2(for Operating System of Windows):
1. Go the project directory
2. For terminal 1(server), type "cd script" and go to script folder
3. Type "project.cmd"
(steps 1-3 can be replaced by click on project.cmd)
4. Click "Server" button
5. Enter a port number that is greater than 1024
6. For terminal 2(client), first repeat step 1-3
7. Click "Client" button
8. Enter the hostname and the corresponding port number
9. Server side is always "Han" side, which always goes first
10. Now you can move the chesses between client and server
11. Click on "Admit Defeat" button twice to lose immediately
12. Close the window to terminate the game 


Examples(for Operating System of Linux):
1. Read example for windows as reference
2. Change step 3 to "./project.sh" (cmd changed to sh)
(steps 2 can be replaced by click on project.sh)
3. Follow the following procedures

## Running the javadoc without running program
Example(for Operating System of Windows):
1. Go the project directory and open the terminal
2. Type "cd script", go to script folder
3. Type "javadoc.cmd"
4. Now you can check "index" or "index-all" in docs folder 
or:
 click javadoc.cmd if you have Windows
 Click javadoc.sh if you have Linux
### 
Example(for Operating System of Linux):
Change step 3 to "./javadoc.sh"

************************************************************************************************************


## OS and version of development
#Windows (cmd)
java:
	java version "14.0.1" 2020-04-14
	Java(TM) SE Runtime Environment (build 14.0.1+7)
	Java HotSpot(TM) 64-Bit Server VM (build 14.0.1+7, mixed mode, sharing)
javac: 
	14.0.1
#Linux
java:
	openjdk 11.0.7 2020-04-14
	OpenJDK Runtime Environment (build 11.0.7+10-post-Ubuntu-2ubuntu218.04)
	OpenJDK 64-Bit Server VM (build 11.0.7+10-post-Ubuntu-2ubuntu218.04, mixed mode, sharing)
javac: 
	11.0.7

## Authors
* Kejie Jack He * 
* Yueting Liao * 
* Yihang Wang *
* Luwei Yao *
 
## Date
* 08/17/2020
