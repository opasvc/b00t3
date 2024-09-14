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

# ===================================================