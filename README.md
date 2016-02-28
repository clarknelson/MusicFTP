# MusicFTP
*Authors:* Clark Nelson, Chaz Stulginskas, Eleanor Holley, Raul Vasquez

Final project for Distributed Systems CSC376 at DePaul University. An application designed to share music over the internet. The server program designed to accept incoming clients and parse file requests. The client program is designed to request and search files.

**Goal:** Provide users a way to access music on a home computer which they can not fit on their phone or mobile device.

## Build process

1. Change directory to `MusicFTP/src`
2. Run `javac **/*.java` to compile source code
3. Run `java main.MusicFTP` to execute the program

## General Todos

- [ ] Create Bash file for easy execution of program (`musicftp-server` and `musicftp-client` to start the programs, `compile` to build java files)
- [ ] Find out if [Java Servlets](https://en.wikipedia.org/wiki/Java_servlet)  would be helpful in creating a frontend

## Timeline

**Feb.27th**

- [X] Create server that listens for connections
- [ ] Client connects to server
- [ ] Server sends welcome message to client
- [ ] Client and server shutdown

**Feb.28th**
- [ ] Server asks client for authentication
- [ ] Client sends to server username and password
- [ ] Server checks authentication
- [ ] Server launches new thread for each connection accepted  

**Feb.29th**

- [ ] Client connects to server using command line arguments or config file
- [ ] Client receives welcome message from server
- [ ] Client sends username / password to server
- [ ] Client receives list of available songs
- [ ] Client sends name of file to server

**Mar.5th**

- [ ] Thread receives client song request
- [ ] Thread reads name of file requested from remote client
- [ ] Thread checks whether file exists and can be read
- [ ] Thread responds with either the number of bytes in the file or a negative number indicating an error
- [ ] Thread attempts to send requested file

**Mar.7th**

- [ ] Client reads response from server
- [ ] If client receives error message, the client will respond appropriately
- [ ] If the client receives a file size, it will open a new music file to read bytes into

**Mar.12th**

- [ ] Finish any remaining server functionality
- [ ] Add ability to search music file names sent by server
- [ ] Finish any remaining client functionality

## Documentation

### Server

This is a general summary of how to run the server. Starts server on whatever computer the program is run on.

### Client

This is a general summary of how to run the client.

@TODO What does the help menu look like?

## Detailed Description

The goal of our application is to access music from our phone on the go. Most cell phones are limited by the amount of space available on the device. This usually varies between 16gb and 64gb. Serious music listeners will have upwards of 100gb of music files in their possession, usually on a desktop PC at home connected to the internet. One advantage of mobile devices which was not available years ago is the ability to connect to the internet. We can leverage this advantage by providing access to music files stored at home through an internet connection.

Currently there are many solutions on the market that provide such functionality, but these solutions are very expensive or require a dedicated operating system to run. [Synology](https://www.synology.com/en-us/products/DS115j) offers a high quality solution that streams all types of files for about $200-300 including HDD. [FreeNAS](http://www.freenas.org/) is a free open source network attached storage operating system. This OS will serve your files over the internet provided you are technical enough to install a new operating system and have a dedicated PC to run the software.

There are limitations on our program due to its classification as a school project and thus requiring lower level networking technology. An obvious limitation is that we can not use HTTP or an established web server such as Apache. This technology would allow us to host a website that any device can access. There are several Java frameworks such as [Spring](https://spring.io/) or [Play](https://www.playframework.com/) that provide the HTTP foundation for forward facing sites. By using one of these frameworks it would be easy to create an API and website to serve the music files.

Since we can not use a web server, we will be serving the files from a specified port number on the server. This makes it simple for command lines to connect and download files, but limits clients to devices that have a CLI. Also the client will need to have a copy of the client application software from github.

We can get around this issue by developing a native Android application that knows which server and port number to connect to. This is outside the scope of the project, but by developing such an application we will solve the original problem of accessing music on the go.
