/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2019/6/7 2:00:17                             */
/*==============================================================*/


drop table if exists article;

drop table if exists article_comment;

drop table if exists article_content;

drop table if exists blog_option;

drop table if exists image;

drop table if exists login_record;

drop table if exists notice_email;

drop table if exists tag;

drop table if exists user;

drop table if exists user_info;

/*==============================================================*/
/* Table: article                                               */
/*==============================================================*/
create table article
(
   article_id           int(10) not null auto_increment,
   title                varchar(50) not null,
   image_id             int comment '页头大图的路径',
   summary              varchar(300) comment '文章简介',
   user_id              int(10) not null,
   tag_id               int(10),
   created_time         datetime not null comment '创建时间',
   created_month        tinyint(2) not null comment '创建该文章时的月份信息的冗余项，用于归档',
   modified_time        datetime not null comment '修改时间',
   status               tinyint(1) not null comment '0表示未发表，1表示已发表，2表示删除',
   primary key (article_id)
);

alter table article comment '该表存放文章的头部信息，包含题目、简介、日期信息等';

/*==============================================================*/
/* Table: article_comment                                       */
/*==============================================================*/
create table article_comment
(
   comment_id           int(10) not null auto_increment,
   article_id           int(10),
   name                 varchar(16) not null,
   email                varchar(64),
   site                 varchar(100),
   content              text not null,
   status               tinyint(1) not null comment '0表示未批准，1表示已批准，2表示删除',
   ip                   int(10),
   created_time         datetime not null,
   primary key (comment_id)
);

/*==============================================================*/
/* Table: article_content                                       */
/*==============================================================*/
create table article_content
(
   article_content_id   int(10) not null auto_increment,
   article_id           int(10),
   content              text not null,
   primary key (article_content_id)
);

alter table article_content comment '文章的实体内容';

/*==============================================================*/
/* Table: blog_option                                           */
/*==============================================================*/
create table blog_option
(
   option_id            int(10) not null,
   option_key           varchar(32) not null,
   option_val           varchar(32) not null,
   primary key (option_id)
);

alter table blog_option comment '只存放设置项，数据存好后不应进行增删';

/*==============================================================*/
/* Table: image                                                 */
/*==============================================================*/
create table image
(
   image_id             int not null auto_increment,
   image                text not null,
   name                 varchar(50),
   primary key (image_id)
);

/*==============================================================*/
/* Table: login_record                                          */
/*==============================================================*/
create table login_record
(
   login_record_id      int(10) unsigned not null auto_increment comment '登录记录表id',
   user_id              int(10) unsigned not null comment '用户id',
   login_time           datetime not null comment '登录时间',
   ip                   int(10) default NULL comment '登录ip',
   primary key (login_record_id)
);

/*==============================================================*/
/* Table: notice_email                                          */
/*==============================================================*/
create table notice_email
(
   email_id             int(10) not null auto_increment,
   email                varchar(64) not null,
   status               tinyint(1) not null comment '0表示已删除1表示正常',
   primary key (email_id)
);

alter table notice_email comment '自动发送通知的邮箱表';

/*==============================================================*/
/* Table: tag                                                   */
/*==============================================================*/
create table tag
(
   tag_id               int(10) not null auto_increment,
   name                 varchar(10) not null,
   view                 tinyint(1) not null comment '0不显示',
   primary key (tag_id)
);

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   user_id              int(10) unsigned not null auto_increment comment '用户id',
   account              varchar(20) character set latin1,
   password             varchar(32) character set latin1,
   salt                 varchar(64) character set latin1,
   primary key (user_id)
);

/*==============================================================*/
/* Table: user_info                                             */
/*==============================================================*/
create table user_info
(
   user_info_id         int(10) unsigned not null auto_increment comment '用户信息表id',
   user_id              int(10) comment '用户id',
   role                 tinyint(1) not null comment '用户角色 0表示超级管理员，1表示编辑，2表示写手',
   email                varchar(64) character set latin1,
   phone_number         varchar(11) character set latin1,
   name                 varchar(16) character set latin1,
   count                int(11) not null comment '总登录次数',
   created_time         datetime not null comment '账号创建时间',
   modified_time        datetime not null comment '账号信息修改时间',
   primary key (user_info_id)
);

