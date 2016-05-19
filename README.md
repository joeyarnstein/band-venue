# Band_Tracker



#### By _**Joey Arnstein**_

## Description

Exercise in many-to-many relationships for SQL integration in java web app.

## Setup/Installation Requirements

* _Clone this repository_
* _Install the [Java SDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) and [Java SRE](http://www.java.com/en/)._
* _[Install gradle](http://codetutr.com/2013/03/23/how-to-install-gradle/)_
* _Open a terminal and run Postgres_
```
$ postgres
```
* _Open a new tab in terminal and create the `band_tracker` database:_
```
$ psql
# CREATE DATABASE band_tracker;
#\c band_tracker
# CREATE TABLE bands (id serial PRIMARY KEY, name varchar); CREATE TABLE venues (id serial PRIMARY KEY, name varchar); CREATE TABLE bands_venues (id serial PRIMARY KEY, band_id int, venue_id int);
# CREATE DATABASE band_tracker_test WITH TEMPLATE band_tracker; //for testing

```
* _Navigate back to the directory where this repository has been cloned (in Terminal) and run gradle:_
```
$ gradle run
```
* _Open localhost:4567 in a browser._

## Alternative Database Setup

* _Alternatively, rather than creating the database and tables from scratch and and entering data from the running web app, you can restore an example database from this repo by running the following commands instead._
* _Open a terminal and run Postgres_
```
$ postgres
```
* _Open a new tab in terminal and create the `band_tracker` database:_
```
$ psql
# CREATE DATABASE band_tracker;
```
* _Open a new (third) tab in terminal._
* _Navigate to the local directory of this repo_
* _Run the following command to create the tables and dump example data:_
```
$ psql band_tracker < band_tracker.sql;
```
* _Then in your Terminal tab that's running psql, create the test database for testing._
```
# CREATE DATABASE band_tracker_test WITH TEMPLATE band_tracker; //for testing

```
* _Navigate back to the directory where this repository has been cloned (in Terminal) and run gradle:_
```
$ gradle run
```
* _Open localhost:4567 in a browser._

## Known Bugs

_No current known bugs._

## Support and contact details

_To contact, leave a comment on Github._

## Technologies Used

* _Java_
* _JUnit_
* _FluentLenium_
* _Gradle_
* _Spark_
* _SQL_

### License

*MIT License*

Copyright (c) 2016 **_Joey_Arnstein_**
