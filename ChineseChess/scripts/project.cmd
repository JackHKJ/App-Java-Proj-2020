@ECHO OFF
cd ..
javac -encoding UTF-8 .\src\edu\rpi\cs\csci4963\u20\hek2liaoy3wangy58yaol4\project\ChineseChess\*.java -d .\classpath
java -classpath .\classpath\ edu.rpi.cs.csci4963.u20.hek2liaoy3wangy58yaol4.project.ChineseChess.ChineseChess
cd scripts