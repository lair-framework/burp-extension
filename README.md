Lair Burp Extension
===================

The Lair Burp Extension is exactly what it sounds like - a Burp Suite Extension used to import individual scan issues into a remote Lair project.

Lair (https://github.com/fishnetsecurity/Lair) is an application for collaborative management of diverse information associated with pentests. Although a Burp Drone already exists to consume Burp XML files (https://github.com/fishnetsecurity/Lair-Drones), being able to perform on-demand imports of individual issues directly from Burp makes tracking the issues a little more manageable.

## Features
* Integrates with an existing Lair MongoDB instance over plaintext or SSL
* Straightforward configuration and usage
* Burp scan results context menu allows one-click export of a Burp issue to a Lair project
* Creates new ports and vulnerabilities in Lair project if not currently present during import

## Limitations
* Cannot resolve DNS names via Burp-specific configuration (e.g. SOCKS proxy, host list)
* Setup to use SSL with MongoDB requires a few additional steps
* Project and host must exist in Lair project - they will not be created dynamically by the extension

## Usage
* Enable the extension in Burp
* Go to the "Lair Configuration" tab and enter the project id and the Mongo URL
* Perform passive/active scans in Burp
* Go to: Scanner > Results
* Right-click the desired scan issue and click "Send to Lair"
* Check the "Alerts" tab for success/fail messages

## Installation

* Download dependencies

  Lair: [GitHub Project](https://github.com/fishnetsecurity/Lair)

  Burp: [Portswigger.net](http://portswigger.net/burp/download.HTML)

  MongoDB: [Java Driver](http://central.maven.org/maven2/org/mongodb/mongo-java-driver/2.12.1/mongo-java-driver-2.12.1.jar)

  Lair Burp Extension: [Latest Release](https://github.com/djkottmann/Lair-Burp-Extension/releases)
  
* If the Lair MongoDB instance utilizes SSL (recommended) then you must perform the following to setup a Java keystore:

  - Create a client keystore if you haven’t already:

        `keytool -genkey -alias client -keyalg RSA -keystore client.jks`

  - If installing Lair from source generate the server-side SSL certificate per [Mongo instructions](http://docs.mongodb.org/manual/tutorial/configure-ssl/)

  - If installing Lair from binary, run start.sh and generate the server-side Mongo certificate. Initial execution will create a file named 'lair.pem'
  
  - Import the server certificate into the client keystore:

        `keytool -import -file /path/to/lair.pem -keystore client.jks -alias mongo`
        
  - Set the appropriate environment variable to force Java to load your keystore when launched:

        `export _JAVA_OPTIONS="-Djavax.net.ssl.trustStore=/path/to/client.jks"`
        
  - Alternative to setting an environment variable, the trust store can be set when launching Burp from the command line:
  
        `java -Djavax.net.ssl.trustStore=/path/to/client.jks -jar burpsuite_pro_vX.Y.jar`

  - **Depending on how you launch Burp you may need to tweak your environment to automatically set this parameter**

* Launch Burp

* Go to: Extender > Options

* Under "Java Environment" click "Select folder ..." and select the folder that contains your Java MongoDB driver and click "Open".
![Burp Extension Options](https://github.com/djkottmann/Lair-Burp-Extension/blob/master/images/burp_extension_options.png?raw=true)

* Go to: Extender > Extensions

* Click "Add"

* For "Extension type" select Java.

* For "Extension file" select the Lair_Burp_Extension.jar file.
![Extension Load](https://github.com/djkottmann/Lair-Burp-Extension/blob/master/images/burp_extension_load.png?raw=true)

* Click "Next" and ensure no errors were generated.
![Successful Load](https://github.com/djkottmann/Lair-Burp-Extension/blob/master/images/burp_extension_load_success.png?raw=true)

* You should see a new Burp Suite tab titled "Lair Configuration"
 
## Detailed Usage

* After loading the extension, navigate to the Lair Configuration tab and setup your Lair project ID and Mongo URL. **Depending on how you launched Burp the Mongo URL may be populated from your environment variables**
![Burp Extension Options](https://github.com/djkottmann/Lair-Burp-Extension/blob/master/images/burp_extension_lair_config.png?raw=true)

* Perform passive and active scanning

* Go To: Scanner > Results. Right-click a scan issue you wish to export and click "Send to Lair"
![Burp Extension Options](https://github.com/djkottmann/Lair-Burp-Extension/blob/master/images/burp_extension_context_menu.png?raw=true)

* Check the "Alerts" tab for success and failure messages.

## Credits

 - @packetassailant for assistance in initial dev challenges
 - @zenfosec for testing and QA
