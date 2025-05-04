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