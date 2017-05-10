# KickOff
##A soccer scorekeeping app by Group 10
**We have divided our repository into three separate folders, one for each version of the app.**

TESTING FORK PULL REQUEST

###1. JAVA Desktop App
The jar file(KickOff.jar) compiled from the source code can be found in the jar folder of the java desktop app. You can download it and run it to test the program on any computer running Java 1.8 or later. To download the jar directly from github you can click on it then select raw in the right corner. Included is a sample database: "soccerscores.xml" that can be downloaded and used locally as an alternative to using the online database. To use the app with the online database, simply select download scores under the file menu bar and upload scores after inputing new data.

**Summary:**  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1.  Download KickOff.jar  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2.  Run KickOff.jar  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.  File > Download Scores  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.  Use app (view teams and/or modify data)  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5.  File > Upload Scores  

**Additional Important Information:**  
To login to the Manager mode, you can use the following credentials:  
**Username:** *walter*  
**Password:** *white*  
  
To login as a Scorekeeper, you can use the following credentials:  
**Username:** *mcintosh*  
**Password:** *bluejays*

###2. ANDROID App
All the files necessary to build the android app are contained in this directory. Simply copy the contents of the folder, set up all the dependencies (such as jar libraries) and build them in Android Studio using Java 1.8 or later. Much like the dekstop app, this version of the app allows the user to download and push up to date scores from the server.

To download the android app on your android device follow the given steps:

**Summary:**   
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1.  Go to this repository on GitHub on your phone.  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2.  Go to ANDROID App  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.  app > build > outputs > apk > app-debug.apk  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.  Click on RAW to download the app and then install it on your device.  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5.  Run the app.  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;6.  From the options in the top right corner you can choose to download/upload the database.  

The usernames and passwords for both modes are the same as the desktop app listed above.  

###3. PHP Web App
Our web app is hosted on the mcgill computer science servers. The folder *PHP Web App* contains an identical copy of the files found on the server(cs.mcgill.ca/~jselwy). You can access and use the webApp at : http://www.cs.mcgill.ca/~jselwy/Soccer.html. The credentials for the input section of the web app are the same as the java app (user: walter, password: white)   
**NOTE** Entering player names or team names that have spaces in them will cause problems in the analysis mode because of the way php parses the xml file.
    
**NOTE:** This server is also the location of the http://cs.mcgill.ca/~jselwy/soccer.xml that ensures the persistence of the data across all 3 versions of the app 

**NOTE:** The above links do not work properly on Mozilla Firefox. They work fine on Google Chrome.
