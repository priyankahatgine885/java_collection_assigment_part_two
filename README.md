Q1) Out of Date Software

Consider the following data in array of strings
Server1, Database, MySQL, 5.5
Server2, Database, MySQL, 5.1
Server3, OS, Ubuntu, 12.04
Server1, OS, Ubuntu, 12.04
Server2, OS, Ubuntu, 18.04
Server3, Language, Python, 2.6.3

Server1 has version 5.5 of MySQL installed, Server2 has version 5.1 installed, and Server3 has version 12.04 of Ubuntu installed. For the purposes of this program, assume that all version numbers are of the form X.Y or X.Y.Z where X, Y, and Z are made up of only digits.

Write a java program to display a list of software package names for which an out-of-date version  (i.e. a version which is not the latest version) is installed on at least 2 different servers. 
Thus, in this case, the output of your program should be:
Ubuntu
Because Ubuntu 12.04 is an out-of-date version (the latest version is 18.04), and it is installed on two servers (Server 3, and Server 1).

Q3)At Least One Outdated Library

A company has a number of products that they sell to customers, and each product uses one or more internal libraries. All internal libraries are given version numbers like v1, v2, v3, ... ete. which are incremented each time the internal library team releases a new version of the library to the product teams

Consider the following data in array of strings which contains information about which versions of the various libraries are being used by the current release of each product of the company

Mail Server, Authentication Library, v6
Video Call Server, Authentication Library, v7
Mail Server, Data Storage Library, v10
Chat Server, Data Storage Library, v11
Mail Server, Search Library, v6
Chat Server, Authentication Library, v8
Chat Server, Presence Library, v2
Video Call Server, Data Storage Library, v10
Video Call Server, Video Compression Library, v3
Each line of the input consists of 3 comma-separated fields. The first field is a product name, the second field is a library name, and the third field is the library version number used by that product. The example given above indicates that the Mail Server uses version v6 of the Authentication Library, andthe Video Call Server uses version v7 of the Authentication Library, and the Mail Server also uses version v10 of the Data Storage Library. and so on. For the
purposes of this program, assume that all version numbers are of the form v<digits> where <digits> represents one or more decimal digits. Write a program to  processes the input data, figures out which is the latest version number of each library, and then prints the names of products which are using an outdated version (ie. a version that is not the latest version) of at least one library, to standard output (console). Thus, in case of the example input given above, the output of your program should be
Mail Server
Video Call Server
because Mail Server is using older versions of the Authentication Library and the Data
Storage Library, and Video Call Server ts using an older version of the Authentication
Library. 



