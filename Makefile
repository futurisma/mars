.SUFFIXES: .class .java

JAVAHOME=/usr/lib/jvm/java-8-openjdk-amd64/jre
JAVAC= /usr/bin/javac
PATH=$(JAVAHOME)/bin:$PATH
CLASSPATH=.:$(JAVAHOME)/lib/rt.jar:$(JSDKHOME)/lib/rt.jar
DEST=.
DOC=.
JAVA=/usr/bin/java
RM=/bin/rm
JAR=/usr/bin/jar
JAVACFLAGS=-deprecation

.SUFFIXES: .java .class

.java.class:
	$(JAVAC) -classpath $(CLASSPATH) $(JAVACFLAGS) $<

CLASSFILES  = UDPSend.class \
          UDPReceiver.class


SOURCEFILES = UDPSend.java \
          UDPReceiver.java

PROPS = UDPReceiver.properties \
				UDPSend.properties

# begin ---- JAR support ----------
JARFILE= mars-1.0.0.jar

$(JARFILE): $(CLASSFILES) $(SOURCEFILES)
#	$(JAR) cfm0 $(JARFILE) manifest.tmp  $(CLASSFILES)
	$(JAR) cfm0 $(JARFILE) manifest.tmp  *.class *.properties

# end ---- JAR support ----------

all : $(JARFILE) $(CLASSFILES)

doc : $(CLASSFILES)
	javadoc -version -author -d $(DOC) $(SOURCEFILES)

install :
	cp $CLASSESFILE $(DEST)

clean:
	$(RM) $(CLASSFILES) $(JARFILE)

receiver:
	java -jar mars-1.0.0.jar UDPReceiver
