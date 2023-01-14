drop database advancedSW ;

CREATE DATABASE IF NOT EXISTS advancedSW ;
use advancedSW;

CREATE  TABLE IF NOT EXISTS user
(
  `UserID` int NOT NULL AUTO_INCREMENT ,
  `username` varchar(252) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mobile` varchar(45) DEFAULT NULL,
  `birthDate` varchar(255) DEFAULT NULL,
  `balance` varchar(255) DEFAULT NULL,
  `license` varchar(255) DEFAULT NULL,
  `nationalID` varchar(255) DEFAULT NULL,
  `role` varchar(45) NOT NULL,
  `approved` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`UserID`)
);

CREATE  TABLE IF NOT EXISTS favorite
(
 `favoriteID` int NOT NULL AUTO_INCREMENT ,
 `UserID` int NOT NULL ,
 `area` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`favoriteID`),
  CONSTRAINT `UserID` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`)
);

CREATE  TABLE IF NOT EXISTS driverLocation
(
 `driverLocation` int NOT NULL AUTO_INCREMENT ,
 `driverID` int NOT NULL ,
 `location` varchar(500) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`driverLocation`),
   CONSTRAINT `driverID` FOREIGN KEY (`driverID`) REFERENCES `user` (`driverID`)
);

CREATE  TABLE IF NOT EXISTS trip
(
 `tripID` int NOT NULL AUTO_INCREMENT ,
 `UserID` int NOT NULL ,
 `source` varchar(255) DEFAULT NULL,
 `destination` varchar(255) DEFAULT NULL,
 `passangers` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tripID`)
);
ALTER TABLE trip 
ADD CONSTRAINT fk_tripTable_userTable
FOREIGN KEY (UserID)
REFERENCES user(UserID) ;

CREATE  TABLE IF NOT EXISTS offer
(
 `offerID` int NOT NULL AUTO_INCREMENT ,
 `driverID` int NOT NULL ,
 `userID` int NOT NULL ,
 `tripID` int NOT NULL ,
 `status` varchar(255) DEFAULT NULL,
 `money` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`offerID`),
  CONSTRAINT `tripID` FOREIGN KEY (`tripID`) REFERENCES `trip` (`tripID`)
);
ALTER TABLE offer 
ADD CONSTRAINT fk_offerTable_userTable
FOREIGN KEY (driverID)
REFERENCES user(UserID) ;

ALTER TABLE offer 
ADD CONSTRAINT fk_offerTable_userTable_1
FOREIGN KEY (UserID)
REFERENCES user(UserID);


CREATE  TABLE IF NOT EXISTS rate
(
 `rateID` int NOT NULL AUTO_INCREMENT ,
 `driverID` int NOT NULL ,
 `userID` int NOT NULL ,
 `rate` int NOT NULL ,
  PRIMARY KEY (`rateID`)
);

ALTER TABLE rate 
ADD CONSTRAINT fk_rateTable_userTable
FOREIGN KEY (driverID)
REFERENCES user(UserID) ;

ALTER TABLE rate 
ADD CONSTRAINT fk_rateTable_userTable_1
FOREIGN KEY (UserID)
REFERENCES user(UserID);

CREATE  TABLE IF NOT EXISTS events
(
 `eventID` int NOT NULL AUTO_INCREMENT ,
 `eventname` varchar(255) DEFAULT NULL ,
 `time` varchar(255) DEFAULT NULL ,
 `driverID` int DEFAULT NULL ,
 `userID` int DEFAULT NULL ,
 `tripID` int NOT NULL ,
  PRIMARY KEY (`eventID`)
 -- CONSTRAINT `tripID` FOREIGN KEY (`tripID`) REFERENCES `trip` (`tripID`)
);
ALTER TABLE events 
ADD CONSTRAINT fk_eventsTable_tripTable
FOREIGN KEY (tripID)
REFERENCES trip (tripID) ;