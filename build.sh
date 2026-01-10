keytool -importcert -trustcacerts -noprompt -keystore "$JAVA_HOME/lib/security/cacerts" \
    -storepass changeit   -alias http_ca   \
    -file $PWD/src/main/resources/http_ca.crt
mvn compile exec:java -Dexec.mainClass=com.example.elasticsearch.DemoApplication
