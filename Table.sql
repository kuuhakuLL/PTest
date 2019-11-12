create table Directions(
   IdDirections INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
   Title VARCHAR(100) NOT NULL,
)CHARACTER SET utf8;

Insert into Directions (Title) value ("Воспитательная и развивающая деятельность");
Insert into Directions (Title) value ("Педагогическая деятельность");
Insert into Directions (Title) value ("Самообследование личностной компетентности");

create table Qwest(
   IdQwest INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
   idDirection INT,
   Title VARCHAR(255),
   Test BOOLEAN,
   TypeQwest TINYINT UNSIGNED,
   FOREIGN KEY (idDirection) REFERENCES Directions (IdDirections) ON DELETE CASCADE
)CHARACTER SET utf8;

Select IdQwest, Title, TypeQwest From Qwest
   Where Test = True and idDirection = 1;

create table Answers(
   IdAnswer INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
   idQwests INT,
   Answer VARCHAR(255) NOT NULL,
   FOREIGN KEY (idQwests) REFERENCES Qwest (IdQwest) ON DELETE CASCADE
)CHARACTER SET utf8;

Select Answer From Answers
    Where idQwests = 1;

create Table Dstractors(
   IdDstractor INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
   idQwests INT,
   Dstractor VARCHAR(255) NOT NULL,
   FOREIGN KEY (idQwests) REFERENCES Qwest (IdQwest) ON DELETE CASCADE
)CHARACTER SET utf8;

Select Dstractor From Dstractors
    Where idQwests = 1;