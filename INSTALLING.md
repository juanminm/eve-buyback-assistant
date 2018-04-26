# Installing EVE Buyback Assistant

Thanks for downloading EVE Buyback Assistant.

To install it you have two ways, from source or binaries:

## Build from source

### Requirements

JDK (tested JDK 8)
Maven (tested 3.5.3)

### Compiling

Download the sources zipped file and extract the contents in any folder you
want, then open your shell of choice and access the previous folder. Lastly,
type the next command:

```
mvn[.exe] clean package
```

If you do not have Maven executable in your PATH, you will have to type the full
path to mvn.exe (in Windows) or mvn (in Unix-like) like this

The JAR executable and the "libs" folder will be located in the newly-created
"target" folder.

## From binaries

### REquirements

JRE (tested JRE 8)

### Installation

Download the binaries zipped file and extract the contents (JAR file and "libs"
folder) in any folder you want, then execute eve-buyback-assistant-x.y.z.jar by
double clicking or using the command below:

```
java[.exe] -jar eve-buyback-assistant-x.y.z.jar
```

If you do not have Java binary directory in your PATH, you will have to
type the full path to the Java executable.