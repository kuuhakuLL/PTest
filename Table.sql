create table Directions(
   IdDirection INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
   Title VARCHAR(100) NOT NULL
)CHARACTER SET utf8;

Insert into Directions (Title) value ("Воспитательная и развивающая деятельность");
Insert into Directions (Title) value ("Педагогическая деятельность");
Insert into Directions (Title) value ("Самообследование личностной компетентности");

create table Tests(
   IdTest INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
   idDirection INT,
   Title VARCHAR(100) NOT NULL,
   FOREIGN KEY (idDirection) REFERENCES Directions (IdDirection) ON DELETE CASCADE
)CHARACTER SET utf8;

Insert into Tests (idDirection, Title) value (1,"Диагностика развивающей функции");
Insert into Tests (idDirection, Title) value (1,"Кейсовые задачи по  психологии для диагностики воспитательной деятельности");
Insert into Tests (idDirection, Title) value (2,"Задания по обучению");
Insert into Tests (idDirection, Title) value (2,"Тесты по профстандарту");
Insert into Tests (idDirection, Title) value (3,"Диагностика коммуникативных и организаторских склонностей");
Insert into Tests (idDirection, Title) value (3,"Методика определения уровня рефлексивности");

Select IdTest, Title From Tests
   Where idTest = 1;

create table Qwests(
   IdQwest INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
   idTest INT,
   Title VARCHAR(1500),
   TypeQwest TINYINT UNSIGNED,
   FOREIGN KEY (idtest) REFERENCES Tests (IdTest) ON DELETE CASCADE
)CHARACTER SET utf8;

Select IdQwest, Title, TypeQwest From Qwests
   Where idTest = 1;

create table Answers(
   IdAnswer INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
   idQwest INT,
   Answer VARCHAR(500) NOT NULL,
   FOREIGN KEY (idQwest) REFERENCES Qwests (IdQwest) ON DELETE CASCADE
)CHARACTER SET utf8;

Select Answer From Answers
    Where idQwest = 1;

create Table Dstractors(
   IdDstractor INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
   idQwest INT,
   Dstractor VARCHAR(500) NOT NULL,
   FOREIGN KEY (idQwest) REFERENCES Qwests (IdQwest) ON DELETE CASCADE
)CHARACTER SET utf8;

Select Dstractor From Dstractors
    Where idQwest = 1;  