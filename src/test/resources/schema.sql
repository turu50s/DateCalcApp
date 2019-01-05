CREATE TABLE IF NOT EXISTS datecalc(
          dateId          varchar  (6)               NOT NULL  PRIMARY KEY  
         ,dateName        varchar  (32)                                     
         ,calcYear  int      (2)    Default 0  NOT NULL               
         ,calcMonth int      (3)    Default 0  NOT NULL               
         ,calcDay   int      (4)    Default 0  NOT NULL               
);