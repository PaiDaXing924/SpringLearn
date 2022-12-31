drop table T_PERMISSION;
CREATE TABLE T_PERMISSION (
   ID int NOT NULL ,
   URL VARCHAR(256) NULL ,
   NAME VARCHAR(64) NULL 
);
INSERT INTO T_PERMISSION VALUES ('1', '/user', 'user:user');
INSERT INTO T_PERMISSION VALUES ('2', '/user/add', 'user:add');
INSERT INTO T_PERMISSION VALUES ('3', '/user/delete', 'user:delete');


drop table T_ROLE;
CREATE TABLE T_ROLE (
   ID int NOT NULL ,
   NAME VARCHAR(32) NULL ,
   MEMO VARCHAR(32) NULL 
);
INSERT INTO T_ROLE VALUES ('1', 'admin', '超级管理员');
INSERT INTO T_ROLE VALUES ('2', 'test', '测试账户');

drop TABLE T_ROLE_PERMISSION;
CREATE TABLE T_ROLE_PERMISSION (
   RID int(10) NULL ,
   PID int(10) NULL 
);
-- ----------------------------
-- Records of T_ROLE_PERMISSION
-- ----------------------------
INSERT INTO T_ROLE_PERMISSION VALUES ('1', '2');
INSERT INTO T_ROLE_PERMISSION VALUES ('1', '3');
INSERT INTO T_ROLE_PERMISSION VALUES ('2', '1');
INSERT INTO T_ROLE_PERMISSION VALUES ('1', '1');
-- ----------------------------
-- Table structure for T_USER
-- ----------------------------
drop TABLE T_USER;
CREATE TABLE T_USER (
   ID int NOT NULL ,
   USERNAME VARCHAR(20) NOT NULL ,
   PASSWD VARCHAR(128) NOT NULL ,
   CREATE_TIME DATE NULL ,
   STATUS CHAR(1) NOT NULL 
);
-- ----------------------------
-- Records of T_USER
-- ----------------------------
INSERT INTO T_USER VALUES ('2', 'tester', '243e29429b340192700677d48c09d992', '2017-12-11 17:20:21', '1');
INSERT INTO T_USER VALUES ('1', 'mrbird', '42ee25d1e43e9f57119a00d0a39e5250', '2017-12-11 10:52:48', '1');
-- ----------------------------
-- Table structure for T_USER_ROLE
-- ----------------------------
drop TABLE T_USER_ROLE;
CREATE TABLE T_USER_ROLE (
   USER_ID int(10) NULL ,
   RID int(10) NULL 
);
INSERT INTO T_USER_ROLE VALUES ('1', '1');
INSERT INTO T_USER_ROLE VALUES ('2', '2');