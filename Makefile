JAVAC=javac
sources = Iperfer.java\
          IperferClient.java\
          IperferServer.java
classes = $(sources:.java=.class)

all: $(classes)

clean:
	rm -f *.class

%.class: %.java
	$(JAVAC) $<