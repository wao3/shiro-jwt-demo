create database shirojwt;

use shirojwt;

create table user (
    id int primary key auto_increment,
    username varchar(40) not null,
    password varchar(40) not null,
    salt varchar(255) not null
);

create table role (
    id int primary key auto_increment,
    name varchar(40) not null
);

create table perm (
    id int primary key auto_increment,
    name varchar(80) not null,
    url varchar(255) default null
);

create table user_role (
    id int primary key auto_increment,
    userid int not null,
    roleid int not null
);

create table role_perm (
    id int primary key auto_increment,
    roleid int not null,
    permid int not null
);

insert into user(id, username, password, salt)
values (1, 'super', 'd0df45e279f9cde32d529a7617e43b18', '02(3QkRU'), -- 账号：super 密码：super123
       (2, 'aaaa', 'b0b3afa32d4f3545f8ce83ce83802707', '_RV!y7HH'),  -- 账号：aaaa 密码：aaaa123
       (3, 'xiaoming', 'a852e94c2b5b145ce8c9b4c8b6c709a7', 'sUi7N6*x'); -- 账号：xiaoming 密码：xiaoming123

insert into role(id, name)
values (1, 'user'), (2, 'admin'), (3, 'super');

-- 忽略权限的 URL
insert into perm(id, name)
values (1, 'user:create'),
       (2, 'user:delete'),
       (3, 'user:update'),
       (4, 'user:select'),
       (5, 'user:*'),

       (6, 'blog:create'),
       (7, 'blog:delete'),
       (8, 'blog:update'),
       (9, 'blog:select'),
       (10, 'blog:*'),

       (11, 'admin:create'),
       (12, 'admin:delete'),
       (13, 'admin:update'),
       (14, 'admin:select'),
       (15, 'admin:*');

insert into user_role(userid, roleid)
values (1, 3), -- super 是超级管理员
       (2, 2), -- aaaa 是管理员
       (3, 1); -- xiaoming 是普通用户

insert into role_perm(roleid, permid)
VALUES (1, 9),  -- 普通用户拥有查看文章权限
       (1, 4),  -- 普通用户拥有查看用户的权限

       (2, 10), -- 管理员拥有文章增删改查权限
       (2, 5),  -- 管理员拥有普通用户增删改查权限

       (3, 10), -- 超级管理员拥有文章增删改查权限
       (3, 15), -- 超级管理员拥有管理员增删改查权限
       (3, 5)   -- 超级管理员拥有普通用户增删改查权限
;