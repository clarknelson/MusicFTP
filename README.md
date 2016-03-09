# MusicFTP
*Authors:* Clark Nelson, Chaz Stulginskas, Eleanor Holley, Raul Vasquez

Final project for Distributed Systems CSC376 at DePaul University. An application designed to share music over the internet. The server program designed to accept incoming clients and parse file requests. The client program is designed to request and search files.

**Goal:** Provide users a way to access music from a home computer which they can not fit on their phone or mobile device.

## Usage

From MusicFTP folder run `./src/musicftp-server`

After the server is running, connect a client using `./src/musicftp-client`

## Timeline

**Feb.27th**

- [X] Create server that listens for connections [clark]
- [X] Client connects to server [clark]
- [X] Client can send messages to the server [clark]
- [X] Server can send messages to the client [clark]
- [X] Client can read messages from the Server [clark]
- [X] Server can read messages from the Client [clark]

**Mar.2nd**

- [X] Create Bash file for easy execution of program (`musicftp-server` and `musicftp-client` to start the programs, `compile` to build java files) [raul]
- [X] Start of program for music [raul]

**Mar.4th**

- [X] Convert BufferedReader and PrintWriter to DataInputStream and DataOutputStream [clark]
- [X] Added ClientQueue and ServerQueue classes to manage messages sent from the other end [clark]
- [X] Server and client recognize shutdown messages and close the socket [clark]

**Mar.7th**

- [X] Server and Client send welcome messages and print connection information [clark]
- [X] Basic listing for files [eleanor]
- [X] Add metadata library [clark]

**Mar.8th**

- [X] Write Song class to pull and store metadata [clark]
- [X] Write "list artists" command [clark]

**Mar.9th**

*Program should be finished*

**Mar.16th**

*FINAL PRESENTATIONS: 11:30am - 1:45pm*

## Functionality

#### General

- [ ] Support a JSON config file to store authentication information, song directory, song information, etc [Mid priority]
- [ ] Allow program to be run when computer does not have an internet connection. Useful for debugging and working offline. [Low priority]

#### List songs

- [X] Client sends request to server for list of songs [High priority]
- [X] Server reads list of files from directory
- [X] Server sends list of files to the client
- [X] Client receives list of available songs

#### Download song

- [ ] Client sends name of file to server [High priority]
- [ ] Server receives song request
- [ ] Server checks whether file exists and can be read
- [ ] Server responds with either the number of bytes in the file or a negative number indicating an error
- [ ] Server attempts to send requested file to Client

#### Multiple Clients

- [ ] Server launches new thread for each connection accepted [Mid priority]
- [ ] Write code in Server.java that starts ServerSocket.accept() in a new thread
- [ ] This thread is in charge of doing the normal server/client management stuff (open output/input)
- [ ] Maintain list of clients (Sockets) in an array for cleanup

#### Search songs

- [ ] Parse song metadata (artist, song, album, length) for searching [Mid priority]
- [ ] Allow client to search song list [Mid priority]

#### Cleanup

- [ ] Shutdown server/client without exceptions [low priority]
- [ ] Exceptions are being thrown because the Socket is being closed without the stream being closed first
- [ ] The cheap way to fix this is to remove the print messages

#### Extras

- [ ] Play or launch song from client [mid priority]
- [ ] Authentication between client and server [low priority]

## Detailed Description

The goal of our application is to access music from our phone on the go. Most cell phones are limited by the amount of space available on the device. This usually varies between 16gb and 64gb. Serious music listeners will have upwards of 100gb of music files in their possession, usually on a desktop PC at home connected to the internet. One advantage of mobile devices which was not available years ago is the ability to connect to the internet. We can leverage this advantage by providing access to music files stored at home through an internet connection.

Currently there are many solutions on the market that provide such functionality, but these solutions are very expensive or require a dedicated operating system to run. [Synology](https://www.synology.com/en-us/products/DS115j) offers a high quality solution that streams all types of files for about $200-300 including HDD. [FreeNAS](http://www.freenas.org/) is a free open source network attached storage operating system. This OS will serve your files over the internet provided you are technical enough to install a new operating system and have a dedicated PC to run the software.

There are limitations on our program due to its classification as a school project and thus requiring lower level networking technology. An obvious limitation is that we can not use HTTP or an established web server such as Apache. This technology would allow us to host a website that any device can access. There are several Java frameworks such as [Spring](https://spring.io/) or [Play](https://www.playframework.com/) that provide the HTTP foundation for forward facing sites. By using one of these frameworks it would be easy to create an API and website to serve the music files.

Since we can not use a web server, we will be serving the files from a specified port number on the server. This makes it simple for command lines to connect and download files, but limits clients to devices that have a CLI. Also the client will need to have a copy of the client application software from github.

We can get around this issue by developing a native Android application that knows which server and port number to connect to. This is outside the scope of the project, but by developing such an application we will solve the original problem of accessing music on the go.
