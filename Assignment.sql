create database hospital;
use hospital;
create table Mobile(
  C_name varchar(30),
  Mobile varchar(30),
  p_data date
);

insert Mobile values("ravi","I-phone",'2018-09-23');

select * from table1;
CREATE TABLE table1 LIKE mobile;
INSERT INTO table1 SELECT * FROM mobile WHERE mobile='poco';

create table Main(
Name  VARCHAR(255) NOT NULL,
Cust_I VARCHAR(18) NOT NULL,
Open_Dt Date NOT NULL, 
Consul_Dt Date,
VAC_ID CHAR(5),
DR_Name CHAR(255),
State CHAR(5),
Country CHAR(5),
Post_Code INT(5),
DOB DATE,
FLAG CHAR(1),
PRIMARY KEY (Cust_I) 
);
insert main values("Satyaprakash","1234247",'20101012','20121013',"MVD","Paul","SA","PAK",76677,'20200603','A');


