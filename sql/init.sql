create table opts_online_order_param_config
(
    id int not null,
    field_key varchar(30) not null comment '字段key',
    display_value varchar(30) null comment '显示名称',
    tips_value varchar(30) null comment '提示值',
    is_must int(2) default 0 null comment '0非必输，1必输',
    is_audit_field int(1) default 0 not null comment '0是审核字段， 1不是审核字段',
    type int(2) null,
    state int(2) default 0 null comment '0可用，1不可用',
    bind_id int(11) null comment '绑定的订单id',
    regular varchar(30) null comment '正则校验',
    create_time datetime not null,
    update_time datetime null,
    extend varchar(1024) null comment '扩展字段',
    constraint table_name_pk
        primary key (id)
)comment '订单参数配置表';

create table opts_online_task
(

)
