JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
		SocialInterface.java \
		SocialSim.java \
		Graph.java \
		LnkList.java \
		Post.java
default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class

