/*
 * Author : Mohamed LEYE
 * Project : Banque Management App
 * Version : 1.3
 * Last Modified date : 19/05/2018
 * Description : "Distributed System and middelware" project's database script.
 */
CREATE DATABASE bank;

CREATE TABLE bank.agency (
	agID INT NOT NULL AUTO_INCREMENT, 
	agName VARCHAR(255) NOT NULL, 
	agAddress VARCHAR(255) NOT NULL, 
	CONSTRAINT pk_agID PRIMARY KEY (agID)) 
ENGINE = InnoDB;

CREATE TABLE bank.customer (
	cusID INT NOT NULL AUTO_INCREMENT, 
	cusName VARCHAR(255) NOT NULL, 
	cusLastname VARCHAR(255) NOT NULL, 
	agID INT NOT NULL, 
	CONSTRAINT pk_cusID PRIMARY KEY (cusID), 
	CONSTRAINT fk_agID FOREIGN KEY (agID) REFERENCES bank.agency(agID)) 
ENGINE = InnoDB;

CREATE TABLE bank.account (
	actID VARCHAR(10) NOT NULL,
	actLib VARCHAR(255) NOT NULL,
	actSens CHAR(2) NOT NULL,
	actBalance DOUBLE DEFAULT 0,
	cusID INT NOT NULL,
	CONSTRAINT pk_actID PRIMARY KEY (actID),
	CONSTRAINT fk_cusID FOREIGN KEY (cusID) REFERENCES bank.customer(cusID))
ENGINE = InnoDB;

CREATE TABLE bank.operation (
	opID INT NOT NULL AUTO_INCREMENT,
	opLib VARCHAR(25) NOT NULL,
	opDate DATE NOT NULL,
	opAmt DOUBLE NOT NULL,
	opSens CHAR(2) NOT NULL,
	actID VARCHAR(10) NOT NULL,
	CONSTRAINT pk_opID PRIMARY KEY (opID),
	CONSTRAINT fk_actID FOREIGN KEY (actID) REFERENCES bank.account(actID))
ENGINE = InnoDB;