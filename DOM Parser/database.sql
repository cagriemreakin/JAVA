create table exam (
  eno numeric(5),
  etitle varchar(50),
  timeAllowed  numeric(8),  
  numberOfQuestionsPerPage numeric(3), 
  primary key (eno)
);

create table question (
  eno numeric(5),
  qno numeric(5),
  qtext nvarchar(2048), 
  correctAnswer char(1),   
  foreign key (eno) references exam(eno),
  primary key (eno,qno)
);

create table answerOption (
  eno numeric(5), 
  qno numeric(5),
  ono char(1) check (ono in ('A','B','C','D','E')),
  optionText nvarchar(256),
  foreign key (eno,qno) references question (eno,qno),
  primary key (eno,qno,ono)
);

insert into exam values (3,'Elementary History',10,3);

insert into question values(3,1,'The Battle of Gettysburg was fought during which war?','C');
insert into answerOption values (3,1,'A','World War II'); 
insert into answerOption values (3,1,'B','The Revolutionary War');
insert into answerOption values (3,1,'C','The Civil War');
insert into answerOption values (3,1,'D','World War I');
 
insert into question values (3,2,concat('Neil Armstrong and Buzz Aldrin walked how many \n','minutes on the moon in 1696?'),'B') ;
insert into answerOption values (3,2,'A','123'); 
insert into answerOption values (3,2,'B','None'); 
insert into answerOption values (3,2,'C','10'); 
insert into answerOption values (3,2,'D','51'); 
 
insert into question values  (3,3,'Which Presidents held office during World War II?','D'); 
insert into answerOption values (3,3,'A','Franklin D. Roosevelt'); 
insert into answerOption values (3,3,'B','Dwight D. Eisenhower'); 
insert into answerOption values (3,3,'C','Harry Truman'); 
insert into answerOption values (3,3,'D','Both A and C'); 
 
insert into question values  (3,4,'In a communist economic system, people:','B'); 
insert into answerOption values (3,4,'A','Are forced to work as slaves'); 
insert into answerOption values (3,4,'B','Work for the common good'); 
insert into answerOption values (3,4,'C','Work from home computers'); 
insert into answerOption values (3,4,'D','Don''t work'); 
 
 
insert into question values  (3,5,'Which president did not die while in office?','D'); 
insert into answerOption values (3,5,'A','John F. Kennedy'); 
insert into answerOption values (3,5,'B','Franklin D. Roosevelt'); 
insert into answerOption values (3,5,'C','Abraham Lincoln'); 
insert into answerOption values (3,5,'D','Ronald Reagan'); 
insert into answerOption values (3,5,'E','James A. Garfield'); 
 
insert into question values  (3,6,concat('Which state refused to attend the Constitutional Convention \n' ,
       'in 1787 because it didn''t want the United States government \n' ,
       'to interfere with already established state affairs?'),'A'); 
insert into answerOption values (3,6,'A','Rhode Island'); 
insert into answerOption values (3,6,'B','New Hampshire'); 
insert into answerOption values (3,6,'C','New Jersey'); 
insert into answerOption values (3,6,'D','New York'); 

insert into question values  (3,7,'Who founded Buddhism?','A'); 
insert into answerOption values (3,7,'A','Siddharta Gautama'); 
insert into answerOption values (3,7,'B','Jesus Christ'); 
insert into answerOption values (3,7,'C','Mahatma Gandhi'); 
insert into answerOption values (3,7,'D','Muhammad'); 
 
insert into question values  (3,8,'Where is India?','D'); 
insert into answerOption values (3,8,'A','Australia'); 
insert into answerOption values (3,8,'B','America'); 
insert into answerOption values (3,8,'C','Africa'); 
insert into answerOption values (3,8,'D','Asia'); 
 
 
insert into question values  (3,9,'What is the dominant religion in India?','B'); 
insert into answerOption values (3,9,'A','Islam'); 
insert into answerOption values (3,9,'B','Hinduism'); 
insert into answerOption values (3,9,'C','Christianity'); 
insert into answerOption values (3,9,'D','Buddhism'); 
 
insert into question values  (3,10,concat('Near which river did archaeologists find India''s \n' ,
        'first civilization?'),'B'); 
insert into answerOption values (3,10,'A','The Tiber River'); 
insert into answerOption values (3,10,'B','The Indus River'); 
insert into answerOption values (3,10,'C','The Yellow River'); 
insert into answerOption values (3,10,'D','The Nile River'); 
