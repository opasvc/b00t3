# ===================================================
-- 自定义用户表 -- auto-generated definition
create table user_info
(
    id          bigint      not null comment '唯一id'
        primary key,
    name        varchar(32) null comment '用户名',
    username    varchar(32) not null comment '账号',
    password    varchar(64) null comment '密码',
    email       varchar(64) null comment '邮箱',
    roles       varchar(32) null comment '角色',
    create_time datetime    null comment '创建时间',
    login_time  datetime    null comment '最近登录时间',
    login_ip    varchar(16) null comment '登录ip',
    create_ip   varchar(16) null comment '注册ip'
) comment '自定义用户表';
INSERT INTO javatechie.user_info (id, name, username, password, email, roles, create_time, login_time, login_ip, create_ip) VALUES (1, 'admin', 'admin', '$2a$10$198hYaxtoIHsTz8v2LoUdOEEH9hm/ZD6sDuemjv1Cgyvoc241R4Tu', null, '1', null, null, null, null);
INSERT INTO javatechie.user_info (id, name, username, password, email, roles, create_time, login_time, login_ip, create_ip) VALUES (2, 'user', 'user', '$2a$10$198hYaxtoIHsTz8v2LoUdOEEH9hm/ZD6sDuemjv1Cgyvoc241R4Tu', null, '2', null, null, null, null);

# ===================================================