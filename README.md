Lair Burp Extension
===================

The Lair Burp Extension is exactly what it sounds like - a Burp Suite Extension used to import individual scan issues into a remote Lair project.

Lair (https://github.com/fishnetsecurity/Lair) is an application for collaborative management of diverse information associated with pentests. Although a Burp Drone already exists to consume Burp XML files (https://github.com/fishnetsecurity/Lair-Drones), being able to perform on-demand imports of individual issues directly from Burp makes tracking the issues a little more manageable.

## Features
* Integrates with an existing Lair API Server instance
* Straightforward configuration and usage
* Burp scan results context menu allows one-click export of a Burp issue to a Lair project
* Creates new ports and vulnerabilities in Lair project if not currently present during import

## Limitations
* Cannot resolve DNS names via Burp-specific configuration (e.g. SOCKS proxy, host list)
* Currently only allows import of issues by IP address (see above point)
* Project must exist in Lair project - it will not be created dynamically by the extension

## Usage
* Enable the extension in Burp
* Go to the "Lair Configuration" tab and enter the project id and the API Server URL
* Perform passive/active scans in Burp
* Go to: Scanner > Results
* Right-click the desired scan issue and click "Send to Lair"
* Check the "Alerts" tab for success/fail messages

## Installation

* Download dependencies

  Lair [version 2.0]: [GitHub Project](https://github.com/lair-framework)

  Burp: [Portswigger.net](http://portswigger.net/burp/download.HTML)

  Lair Burp Extension: [Latest Release](https://github.com/lair-framework/burp-extension/releases/latest)
  
* Download dependencies (use the download_deps.sh script for simplicity)

~~~~bash
$ download_deps.sh
[!] Directory does not exist
Usage: ./download_deps.sh DESTINATION_DIRECTORY

$ ./download_deps.sh /tmp/lair_burp

<output snipped>

[+] Complete. Add /tmp/lair_burp/lib to your Java CLASSPATH or Burp Extender options
~~~~

* Launch Burp

* Go to: Extender > Options

* Under "Java Environment" click "Select folder ..." and select the folder that contains the dependencies (see ```download_deps.sh``` 
above, your path should match the output from that script).
![Burp Extension Options](https://github.com/lair-framework/burp-extension/blob/2.0/images/burp_extension_options.png?raw=true)

* Go to: Extender > Extensions

* Click "Add"

* For "Extension type" select Java.

* For "Extension file" select the Lair_Burp_Extension-<version>.jar file.
![Extension Load](https://github.com/lair-framework/burp-extension/blob/2.0/images/burp_extension_load.png?raw=true)

* Click "Next" and ensure no errors were generated.
![Successful Load](https://github.com/lair-framework/burp-extension/blob/2.0/images/burp_extension_load_success.png?raw=true)

* You should see a new Burp Suite tab titled "Lair Configuration"
 
## Detailed Usage

* After loading the extension, navigate to the Lair Configuration tab and setup your Lair project ID and Lair API Server. **Depending on how you launched Burp the API Server may be populated from your environment variables**
![Burp Extension Options](https://github.com/lair-framework/burp-extension/blob/2.0/images/burp_extension_lair_config.png?raw=true)

* Perform passive and active scanning

* Find a scan issue you wish to export. Right-click and select "Send to Lair"
![Context Menu](https://github.com/lair-framework/burp-extension/blob/2.0/images/burp_extension_context_menu.png?raw=true)

* Check the "Alerts" tab for error messages.

* Check your Lair project, your finding should be imported
![Lair Data](https://github.com/lair-framework/burp-extension/blob/2.0/images/burp_extension_success.png?raw=true)

## Credits

 - @packetassailant for assistance in initial dev challenges
 - @zenfosec for testing and QA
