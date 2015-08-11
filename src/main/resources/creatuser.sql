CREATE USER 'drivebird'@'127.0.0.1' IDENTIFIED BY '123456';
flush privileges;
 create database drivebird character set utf8;
 grant all privileges on drivebird.* to drivebird@localhost identified by '123456';
 flush privileges;
 
 ---GRANT USAGE ON *.* TO 'appmonitor'@'localhost' IDENTIFIED BY '123456' WITH GRANT OPTION;