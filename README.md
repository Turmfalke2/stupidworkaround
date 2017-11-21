# stupidworkaround
Don't do this.

compile with something like 
javac -classpath /usr/share/asm-4/lib/asm.jar *.java && jar cmf manifest.txt agent.jar *.class

and then run minecraft with -javaagent:/path/to/agent.jar
