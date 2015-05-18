# FreeSwitchEventLogger
Java application to log all the events of FreeSwitch server socket.

Pre-requisites:

1) Since it's a JAVA application, JRE is needed to execute the code.

2) FreeSwitch should be installed as it is meant to log events of FreeSwitch Server socket.

3) Any client software which can communicate to FreeSwitch server to verify if all kinds of events are logged.



Configuration:

The default settings allow socket connections only from the local host. This should be good enough to test this application.
Otherwise to allow connections from any host on the network, use 0.0.0.0 instead of the default 127.0.0.1 in /freeswitch/conf/autoload_configs/event_socket.conf.xml file.
<configuration name="event_socket.conf" description="Socket Client">
  <settings>
    <!-- Allow socket connections from any host -->
    <param name="listen-ip" value="0.0.0.0"/>
    <param name="listen-port" value="8021"/>
    <param name="password" value="ClueCon"/>
  </settings>
</configuration>



Running the Java Logger:

1)	This application was coded in Windows Eclipse IDE. I have exported the complete project in a zip file FreeSwitchJavaEventLogger.zip. 
To run, import this zip file in eclipse IDE (present on any platform) and then try to compile and run file called JavaClient.java

2)	Once application is up and running, it will try and connect to FreeSwitch server on default host localhost:8021.  If the FreeSwitch server is up and running, the application will start logging all the events and related information in a file called EventLogs.txt

3)	The application will continue to log events till QUIT is recieved on console.

Please let me know if you are finding any issues in running this Java application. I will happily entertain all the queries.

mail id: sunil.chhablani@gmail.com
