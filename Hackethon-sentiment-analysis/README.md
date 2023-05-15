Steps to run
1) Add below VM arguments in configuration and run the server

  VM argument : 

  -Dpcln.app.id=ccancelsvcs -Dpcln.profiles=dev -DBOOTSTRAP_ID=OU=bootstrap,OU=GCP-QAB,OU=G-CSHOTELSVCS,OU=static-config -DLDAP_ENV=QA -DCARESENV=QA -DLauncher=CSHOTELSVCS -DinstanceID=1 -DREM.port=-1 -Dserver.port=8080 -Djavax.net.ssl.trustStore=/apps/home/eng/ssl/pcln_cacerts -DConsoleEcho=true -Duser.timezone="+05:30" 

2) Application will run on http://localhost:8080
