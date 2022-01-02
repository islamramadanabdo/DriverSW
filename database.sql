
CREATE DATABASE advancedsw;
use advancedsw;

CREATE TABLE `driver` (
  `UserID` int(11) NOT NULL,
  `username` varchar(252) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mobile` varchar(45) DEFAULT NULL,
  `license` varchar(255) DEFAULT NULL,
  `nationalID` varchar(255) DEFAULT NULL,
  `role` varchar(45) NOT NULL,
  `approved` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `driver`
--

INSERT INTO `driver` (`UserID`, `username`, `password`, `email`, `mobile`, `license`, `nationalID`, `role`, `approved`) VALUES
(3, 'admin', 'adminadmin345', NULL, NULL, NULL, NULL, 'Admin', '1'),
(6, 'eslam', 'eslameslam345', 'islamramadan345543@gamil.com', '', '', '01118405750', 'User', '1'),
(7, 'driver', 'driverdriver345', 'mahasenmohamed290@gmail.com', '01118405750', '5151515', '01223333', 'Driver', '1'),
(8, 'driv', 'dridri345', 'islamramdan345543@gmail.com', '01118405750', '1224', '0111', 'Driver', '1');

-- --------------------------------------------------------

--
-- Table structure for table `favorite`
--

CREATE TABLE `favorite` (
  `favoriteID` int(11) NOT NULL,
  `UserID` int(11) NOT NULL,
  `area` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `favorite`
--

INSERT INTO `favorite` (`favoriteID`, `UserID`, `area`) VALUES
(4, 7, 'doki');

-- --------------------------------------------------------

--
-- Table structure for table `offer`
--

CREATE TABLE `offer` (
  `offerID` int(11) NOT NULL,
  `driverID` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `tripID` int(11) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `money` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `offer`
--

INSERT INTO `offer` (`offerID`, `driverID`, `userID`, `tripID`, `status`, `money`) VALUES
(7, 7, 6, 9, 'Confirmed', 4000),
(8, 7, 6, 9, NULL, 900),
(9, 7, 6, 9, NULL, 300),
(10, 7, 6, 9, NULL, 600);

-- --------------------------------------------------------

--
-- Table structure for table `rate`
--

CREATE TABLE `rate` (
  `rateID` int(11) NOT NULL,
  `driverID` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `rate` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `rate`
--

INSERT INTO `rate` (`rateID`, `driverID`, `userID`, `rate`) VALUES
(3, 7, 6, 4);

-- --------------------------------------------------------

--
-- Table structure for table `trip`
--

CREATE TABLE `trip` (
  `tripID` int(11) NOT NULL,
  `UserID` int(11) NOT NULL,
  `source` varchar(255) DEFAULT NULL,
  `destination` varchar(255) DEFAULT NULL,
  `confirmed` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `trip`
--

INSERT INTO `trip` (`tripID`, `UserID`, `source`, `destination`, `confirmed`) VALUES
(9, 6, 'doki', 'ramses', 1),
(10, 6, 'abc', 'bca', 0);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `UserID` int(11) NOT NULL,
  `username` varchar(252) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mobile` varchar(45) DEFAULT NULL,
  `role` varchar(45) NOT NULL,
  `approved` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`UserID`, `username`, `password`, `email`, `mobile`, `role`, `approved`) VALUES
(1, 'eslam', 'eslamfatma345', 'islamramadan345543@gmail.com', '01118405750', 'User', '1'),
(2, 'eslam', 'dvsvdsv2525', 'islamramadan345543@gmail.com', '01118405750', 'User', '1');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `driver`
--
ALTER TABLE `driver`
  ADD PRIMARY KEY (`UserID`);

--
-- Indexes for table `favorite`
--
ALTER TABLE `favorite`
  ADD PRIMARY KEY (`favoriteID`),
  ADD KEY `UserID` (`UserID`);

--
-- Indexes for table `offer`
--
ALTER TABLE `offer`
  ADD PRIMARY KEY (`offerID`),
  ADD KEY `tripID` (`tripID`),
  ADD KEY `fk_offerTable_userTable` (`driverID`),
  ADD KEY `fk_offerTable_userTable_1` (`userID`);

--
-- Indexes for table `rate`
--
ALTER TABLE `rate`
  ADD PRIMARY KEY (`rateID`),
  ADD KEY `fk_rateTable_userTable` (`driverID`),
  ADD KEY `fk_rateTable_userTable_1` (`userID`);

--
-- Indexes for table `trip`
--
ALTER TABLE `trip`
  ADD PRIMARY KEY (`tripID`),
  ADD KEY `fk_tripTable_userTable` (`UserID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`UserID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `driver`
--
ALTER TABLE `driver`
  MODIFY `UserID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `favorite`
--
ALTER TABLE `favorite`
  MODIFY `favoriteID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `offer`
--
ALTER TABLE `offer`
  MODIFY `offerID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `rate`
--
ALTER TABLE `rate`
  MODIFY `rateID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `trip`
--
ALTER TABLE `trip`
  MODIFY `tripID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `UserID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `favorite`
--
ALTER TABLE `favorite`
  ADD CONSTRAINT `UserID` FOREIGN KEY (`UserID`) REFERENCES `driver` (`UserID`);

--
-- Constraints for table `offer`
--
ALTER TABLE `offer`
  ADD CONSTRAINT `fk_offerTable_userTable` FOREIGN KEY (`driverID`) REFERENCES `driver` (`UserID`),
  ADD CONSTRAINT `fk_offerTable_userTable_1` FOREIGN KEY (`userID`) REFERENCES `driver` (`UserID`),
  ADD CONSTRAINT `tripID` FOREIGN KEY (`tripID`) REFERENCES `trip` (`tripID`);

--
-- Constraints for table `rate`
--
ALTER TABLE `rate`
  ADD CONSTRAINT `fk_rateTable_userTable` FOREIGN KEY (`driverID`) REFERENCES `driver` (`UserID`),
  ADD CONSTRAINT `fk_rateTable_userTable_1` FOREIGN KEY (`userID`) REFERENCES `driver` (`UserID`);

--
-- Constraints for table `trip`
--
ALTER TABLE `trip`
  ADD CONSTRAINT `fk_tripTable_userTable` FOREIGN KEY (`UserID`) REFERENCES `driver` (`UserID`);
COMMIT;


use advancedsw;
Alter TABLE  `user` Add birthday varchar(255);
Alter TABLE  `user` Add balance Double;

Alter TABLE  `driver` Add birthday varchar(255);
Alter TABLE  `driver` Add balance Double;

Alter TABLE `trip` Add passengers int;

CREATE  TABLE IF NOT EXISTS driverLocation
(
 `locationID` int NOT NULL AUTO_INCREMENT ,
 `driverID` int NOT NULL ,
 `location` varchar(500) DEFAULT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`locationID`),
   CONSTRAINT `driverID` FOREIGN KEY (`driverID`) REFERENCES `driver` (`UserID`)
);

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
 
use advancedsw;
CREATE TABLE discount_area
(
 `discount_area_id` int NOT NULL AUTO_INCREMENT ,
 `area` varchar(255),
 primary key(`discount_area_id`)
);

use advancedsw;
ALTER TABLE `trip`
Add price Double