## Configure Apache

Download and install xampp from https://www.apachefriends.org/download.html (win32, version 5.6.30).
Let's assume that it is installed to `d:\Tools\xampp\`

Download `mod_jk` from http://archive.apache.org/dist/tomcat/tomcat-connectors/jk/binaries/windows/tomcat-connectors-1.2.40-windows-i386-httpd-2.4.x.zip
and copy `mod_jk.so` to `d:\Tools\xampp\apache\modules\`

Add follow configuration to the end of `httpd.config`:

````apacheconfig
LoadModule jk_module modules/mod_jk.so
<IfModule jk_module>

# Where to find workers.properties
JkWorkersFile jk/workers.properties
# Where to put jk shared memory
JkShmFile     jk/mod_jk.shm
# Where to put jk logs
JkLogFile     jk/mod_jk.log
# Set the jk log level [debug/error/info]
JkLogLevel    debug
# Send requests for context /jk to worker named worker1
JkMount  /jk/* worker1

</IfModule>
````


Create `d:\Tools\xampp\apache\jk\workers.properties` with follow content:

````properties
worker.list=worker1,jkstatus

#Set properties for worker19 (ajp13)
worker.worker1.type=ajp13
worker.worker1.host=localhost
worker.worker1.port=8009
worker.worker1.ping_timeout=1000
worker.worker1.connect_timeout=10000
worker.worker1.prepost_timeout=10000
worker.worker1.socket_timeout=10
worker.worker1.connection_pool_timeout=60
worker.worker1.connection_pool_size=90
worker.worker1.retries=2
worker.worker1.reply_timeout=300000

# status worker
worker.jkstatus.type=status
````

### Configure Tomcat

Configure Tomcat Manager app. In `d:\Tools\xampp\tomcat\conf\tomcat-users.xml` add follow configuration:

````xml
<role rolename="manager-gui"/>
<role rolename="manager-script"/>

<user username="admin" password="admin" roles="manager-gui"/>
<user username="autodeploy" password="autodeploy" roles="manager-script"/>
````

Start Apache and Tomcat servers.

## Publish web project

Copy files from `src/main/static` to `d:\Tools\xampp\htdocs\simplewebapp_static\`

Compile and deploy web application to Tomcat:

````
mvn clean compile tomcat7:deploy
````

## Check that it works

Go to http://localhost/simplewebapp and see that it is content of `src/main/webapp/index.jsp`

Go to http://localhost/simplewebapp_static/index.html and see that it is content from `d:\Tools\xampp\htdocs\simplewebapp_static\index.html`

You can stop Tomcat server and see that http://localhost/simplewebapp_static/index.html is still accessible, but http://localhost/simplewebapp 
returns Apache error page (503 Service unavailable).

