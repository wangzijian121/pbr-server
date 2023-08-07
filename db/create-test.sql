drop table `pose-management`.algorithm;
create table if not exists `pose-management`.algorithm
(
    id             int auto_increment
    primary key,
    name           varchar(255) null,
    type           int          null comment '类型(0普通算法 1专用算法  2普通数据集 3 专用数据集)',
    sport_category int          null,
    template_id    int          null,
    install_type   int          null comment '部署方式（0：云端部署 1：本地部署）',
    uploader       int          null,
    file           varchar(255) null,
    docs           text         null,
    example        text         null,
    create_time    datetime     null comment '创建时间'
    )
    comment '算法表';
drop table `pose-management`.auth_institution_alg;
create table if not exists `pose-management`.auth_institution_alg
(
    id             int auto_increment
    primary key,
    institution_id int          null comment '授权机构',
    auth_type      int          null comment '授权类型(0：算法，1：功能)',
    auth_alg_id    int          null comment '授权ID',
    auth_admin     int          null comment '授权人',
    mark           varchar(255) null comment '备注',
    auth_time      datetime     null comment '授权时间'
    )
    comment '授权机构算法表';
drop table `pose-management`.charge;
create table if not exists `pose-management`.charge
(
    id             int auto_increment
    primary key,
    type           int      null comment '收费类型（0免费 1按次付费 2按月付费 3按季付费 4 按年付费 5永久） ',
    institution_id int      null comment '机构ID',
    charge_time    datetime null comment '收款时间时间',
    confirm_people int      null comment '确认人',
    confirm_time   datetime null comment '到账确认时间',
    status         int      null comment '状态（0：已到账，1：未到账）',
    mark           text     null comment '备注信息',
    create_time    datetime null comment '录入时间'
)
    comment '付费表';
drop table `pose-management`.commission;
create table if not exists `pose-management`.commission
(
    id          int auto_increment
    primary key,
    review_id   int          not null comment '佣金项ID',
    money       double       not null comment '佣金金额',
    status      int          not null comment '状态(0:未付款，1：已付款)',
    mark        varchar(255) null comment '备注',
    create_time datetime     not null comment '添加时间'
    )
    comment '佣金发放表';

drop table `pose-management`.data_set;
create table if not exists `pose-management`.data_set
(
    id             int          auto_increment
    primary key,
    name           varchar(255) null comment '数据集名',
    type           int          null comment '数据集类型（0:普通数据集 ,1:专用数据集）',
    sport_category int          null comment '支持的体育类型',
    file           varchar(255) null comment '文件',
    demo           text         null comment '数据集样例',
    install_type   int          null comment '部署方式（0：云端部署 1：本地部署）',
    uploader       int          null comment '上传人',
    create_time    datetime     null comment '创建时间'
    )
    comment '数据集表';

drop table `pose-management`.developer_review;
create table if not exists `pose-management`.developer_review
(
    id           int auto_increment
    primary key,
    commit_name  varchar(255) null comment '提交名',
    developer_id int          null comment '开发者',
    type         int          null comment '数据类型(0普通算法 1专用算法  2普通数据集 3 专用数据集)',
    file         varchar(255) null comment '算法或数据集文件',
    demo         varchar(255) null comment '数据集样例',
    status       int          null comment '审核状态(0:未审核 1：审核通过 2：审核未通过)',
    mark         varchar(255) null comment '备注',
    create_time  datetime     null comment '创建时间'
    )
    comment '开发者审核表';


drop table `pose-management`.institution;
create table if not exists `pose-management`.institution
(
    id          int auto_increment
    primary key,
    name        varchar(255) null comment '机构名',
    type        int          null comment '机构类型（0：培训机构，1：健身场所）',
    phone       varchar(255) null comment '联系人电话',
    email       varchar(255) null comment '邮箱 ',
    address     varchar(255) null comment '地址',
    map         varchar(255) null comment '地图位置 ',
    create_time datetime     null comment '添加时间'
    )
    comment '机构表';

drop table `pose-management`.resources;
create table if not exists `pose-management`.resources
(
    id          int auto_increment
    primary key,
    full_name   varchar(255) null,
    alias       varchar(255) null,
    suffix      varchar(255) null comment '后缀',
    size        bigint       null,
    create_time datetime     null,
    update_time datetime     null
    )
    comment '资源表元数据表';
drop table `pose-management`.resources;
create table if not exists `pose-management`.sport_category
(
    id          int auto_increment
    primary key,
    type        varchar(255) null comment '体育类型(0:学校体育 1:群众体育 2:竞技体育)',
    name        varchar(255) null comment '分类名',
    user_id     int          null comment '添加人',
    mark        text         null comment '备注',
    create_time datetime     null comment '授权时间 '
    )
    comment '体育类别表';
drop table `pose-management`.system_info;
create table if not exists `pose-management`.system_info
(
    id int auto_increment
    primary key
)
    comment '系统信息表';

create table if not exists `pose-management`.template
(
    id          int auto_increment
    primary key,
    name        varchar(255) null comment '模板名',
    content     json         null comment '模板内容',
    create_time datetime     null,
    mark        text         null comment '备注'
    )
    comment '模板表';

create table if not exists `pose-management`.user
(
    id          int auto_increment
    primary key,
    type        int          null,
    nickname    varchar(255) null comment '昵称',
    username    varchar(255) null,
    password    varchar(255) null,
    mark        text         null,
    create_time datetime     null comment '授权时间',
    attr        json         null
    )
    comment '用户表';

create table if not exists `pose-management`.wechat
(
    id             int auto_increment
    primary key,
    wechat_id      varchar(255) null comment '小程序ID',
    name           varchar(255) null comment '小程序名',
    institution_id int          null comment '机构ID',
    status         int          null comment '进度(0已部署，1审核中)',
    mark           text         null comment '备注',
    create_time    datetime     null comment '授权时间'
    )
    comment '小程序表';

