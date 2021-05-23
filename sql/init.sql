create table opts_online_order_param_config
(
    id             int auto_increment primary key,
    field_key      varchar(30)      not null comment '字段key',
    filed_value    varchar(30)      not null comment '字段value',
    display_value  varchar(30)      null comment '显示名称',
    tips_value     varchar(30)      null comment '提示值',
    is_must        int    default 0 null comment '0非必输，1必输',
    is_audit_field int(1) default 0 not null comment '0是审核字段， 1不是审核字段',
    type           int              null,
    state          int    default 0 null comment '1可用，0不可用',
    bind_id        int(11)          null comment '绑定的任务id',
    regular        varchar(60)      null comment '正则校验',
    create_time    datetime         not null,
    update_time    datetime         null,
    extend         varchar(1024)    null comment '扩展字段'
) comment '订单参数配置表';

create table opts_online_task
(
    id                  int auto_increment primary key,
    task_name           varchar(60)                 not null comment '任务名称',
    status              int            default 0    not null comment '0待使用, 1使用中,2停用',
    bounty              decimal(10, 2) default 0.00 not null comment '赏金',
    task_num_type       int            default 0    not null comment '0无限制，1有限制',
    task_num            int            default 0    not null comment '任务次数',
    selling_point       varchar(512)                null comment '卖点',
    label               varchar(60)                 null comment '标签',
    deadline            datetime                    null comment '截止日期',
    limit_type          int            default -1   not null comment '每日限制提交次数,-1不限制',
    is_suggest          int            default 0    not null comment '是否推荐 0不推荐 1推荐',
    step_description    varchar(255)                null comment '步骤流程',
    process_description varchar(255)                null comment '流程描述',
    process_url         varchar(255)                null comment '流程图片',
    tip_content         varchar(255)                null comment '提示文案',
    remark              varchar(100)                null comment '备注',
    review_time_value   varchar(60)                 null comment '审核时间',
    create_time         datetime                    null,
    update_time         datetime                    null,
    bm_user_id          int                         null,
    bm_user_name        varchar(255)                null
)comment '订单任务表';


create table opts_online_order
(
    id                  int auto_increment primary key,
    task_id   int not null comment '任务id',
    merchant_id int not null comment '',
    merchant_phone varchar(20) not null comment '手机号',
    merchant_name varchar(20)  null comment '名称',
    order_no  varchar(60) not null comment '订单号',
    sub_filed_json json not null comment '提交字段',
    review_filed_json json not null comment '审核字段',
    create_time datetime null ,
    update_time datetime null ,
    finish_time datetime null comment '审核通过时间',
    predict_income decimal(10,2) default 0.00 not null comment '应结算金额',
    real_income decimal(10, 2) default 0.00 not null comment '实际结算金额',
    status  int default 0 not null comment '状态 0审核中, 1成功，2失败，3撤销',
    work_date varchar(10) not null comment '日期',
    fail_message varchar(120) null comment '失败原因',
    remark varchar(120) null comment '备注'
)comment '订单表';
create index idx_task_id on opts_online_order(task_id);

create table opts_online_order_filed_detail
(
    id int auto_increment primary key,
    order_id int not null comment '订单id',
    task_id int not null comment '任务id',
    filed_key varchar(30) not null comment '提交字段的key',
    filed_value varchar(60) not null comment '提交的值',
    status int default 0 not null comment '状态 0审核中, 1成功，2失败，3撤销'
)comment '订单提交字段';
create index idx_order_id on opts_online_order_filed_detail(order_id);