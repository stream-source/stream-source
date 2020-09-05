CREATE TABLE `class` (
`class_id`  int NOT NULL ,
`class_name`  varchar(255) NULL ,
`class_no`  varchar(255) NULL ,
`student_count`  varchar(255) NULL ,
PRIMARY KEY (`class_id`),
UNIQUE INDEX `class_no_index` (`class_no`) USING BTREE
)
;

