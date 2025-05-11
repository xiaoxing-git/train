/**
  会员表
 */
create table member
(
    id     bigint      not null comment 'id'
        primary key,
    mobile varchar(16) null comment '手机号',
    constraint train_pk_2
        unique (mobile)
)
    comment '会员表';

/**
  乘车人表
 */
create table passenger
(
    id          bigint      not null comment 'id',
    member_id   bigint      not null comment '会员id',
    name        varchar(20) not null comment '姓名',
    id_code     varchar(18) not null comment '身份证号',
    type        char        not null comment '旅客类型',
    create_time datetime(3) null comment '创建时间',
    update_time datetime(3) null comment '修改时间',
    constraint passenger_pk
        primary key (id)
)
    comment '乘车人';

create index member_id_index
    on passenger (member_id)
    comment '会员id索引';