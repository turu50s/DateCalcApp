CREATE TABLE IF NOT EXISTS datemaster(
          dateId          varchar  (6) PRIMARY KEY
         ,dateName        varchar  (32)                                     
         ,calcYear  int      (2)    Default 0  NOT NULL               
         ,calcMonth int      (3)    Default 0  NOT NULL               
         ,calcDay   int      (4)    Default 0  NOT NULL               
) DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;