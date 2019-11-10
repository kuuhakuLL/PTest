create table Directions(
   IdDirections INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
   Title VARCHAR(100) NOT NULL,
);

Insert into Directions (Title) value ("Воспитательная и развивающая деятельность");

create table Qwest(
   IdQwest INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
   idDirection INT
   Title VARCHAR(100),
   Test BOOLEAN,
   TypeQwest TINYINT UNSIGNED,
   FOREIGN KEY (idDirection) REFERENCES Directions (IdDirections) ON DELETE CASCADE
);

Insert into Qwest (idDirection, Title, Test, TypeQwest)
   value (1, "Тип развития, не определяющий стадии, через которые должен пройти ребенок называется:", true, 0);
Insert into Qwest (idDirection, Title, Test, TypeQwest) 
   value (1, "Повторение онтогенеза в филогенезе называется ....", true, 1);
Insert into Qwest (idDirection, Title, Test, TypeQwest) 
   value (1, "Представители бихевиоризма:", true, 2);

Select IdQwest, Title, TypeQwest From Qwest
   Where Test = True and idDirection = 1;

create table Answers(
   IdAnswer INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
   idQwests INT,
   Answer VARCHAR(100) NOT NULL,
   FOREIGN KEY (idQwests) REFERENCES Directions (IdQwest) ON DELETE CASCADE
);

Insert into Answers (idQwests, Answer) value (1, "непреформированным");
Insert into Answers (idQwests, Answer) value (2, "Л.Ф.Обухова");
Insert into Answers (idQwests, Answer) value (3, "рекапитуляция");
Insert into Answers (idQwests, Answer) value (4, "Б. Уотсон");
Insert into Answers (idQwests, Answer) value (4, "Б. Скиннер");

Select Answer From Answers
    Where idQwests = 1;

create Table Dstractors(
   IdDstractor INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
   idQwests INT,
   Dstractor VARCHAR(100) NOT NULL,
   FOREIGN KEY (idQwests) REFERENCES Directions (IdQwest) ON DELETE CASCADE
);

Insert into Dstractors (idQwests, Dstractor) value (1, "преформированным");
Insert into Dstractors (idQwests, Dstractor) value (1, "дискретным");
Insert into Dstractors (idQwests, Dstractor) value (2, "И.С.Кон");
Insert into Dstractors (idQwests, Dstractor) value (2, "В.С.Мухина");
Insert into Dstractors (idQwests, Dstractor) value (2, "А.А.Бодалев");
Insert into Dstractors (idQwests, Dstractor) value (4, "К. Роджерс");
Insert into Dstractors (idQwests, Dstractor) value (4, "А. Валлон");


Select Dstractor From Dstractors
    Where idQwests = 1;