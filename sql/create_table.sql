-- auto-generated definition
create table admin
(
    id           int auto_increment
        primary key,
    username     varchar(20)   not null comment '用户名',
    password     varchar(512)  not null comment '密码',
    role         int default 0 not null comment '权限(1-管理员）',
    address_IP   varchar(20)   null comment '上一次访问IP',
    address_name varchar(20)   null comment '上一次访问IP位置',
    name         varchar(20)   null
);

create table class
(
    id  int auto_increment
        primary key,
    cid int not null comment '班级号'
);

create table course
(
    id           int auto_increment
        primary key,
    name         varchar(20) not null comment '课程名称',
    English_name varchar(20) not null comment '英文名称',
    major        int         not null comment '专业',
    teacher_id   int         not null comment '授课教师'
)
    comment '课程';

create table evaluation
(
    id          int auto_increment
        primary key,
    name        varchar(20)   not null comment '评测名称',
    create_date datetime      not null comment '创建时间',
    start_date  datetime      not null comment '发布时间',
    end_date    datetime      not null comment '截至时间',
    status      int default 0 not null comment '发布状态(1-开启，0-关闭）'
)
    comment '评测';

create table evaluation_student
(
    id            int auto_increment
        primary key,
    student_id    int           not null,
    evaluation_id int           not null,
    is_evaluate   int default 0 not null comment '是否完成(0-未完成，1-已完成）'
);

create table evaluation_system_body
(
    id        int auto_increment
        primary key,
    body_name varchar(20) not null comment '二级',
    head_id   int         not null
)
    comment '二级';

create table evaluation_system_head
(
    id         int auto_increment
        primary key,
    head_name  varchar(20)   not null comment '一级',
    proportion decimal(3, 2) not null comment '比例',
    party      int           not null comment '中方俄方'
)
    comment '评价体系';

create table opinion
(
    id            int auto_increment
        primary key,
    send_time     datetime     not null comment '发送时间',
    send_name     varchar(20)  not null comment '发送人',
    teacher_id    int          not null comment '教师名字',
    evaluation_id int          not null,
    content       varchar(512) null comment '内容'
)
    comment '意见';

create table position
(
    id            int auto_increment
        primary key,
    position_name varchar(20) not null comment '职位名称'
)
    comment '职位';

create table red_line
(
    id    int auto_increment
        primary key,
    score int not null comment '红线分数'
)
    comment '红线分数指标';

create table red_line_name
(
    id                   int auto_increment
        primary key,
    evaluation_system_id int not null comment '测评名称',
    teacher_id           int not null comment '教师名称',
    scores               int not null comment '分数'
);

create table scores
(
    id            int auto_increment
        primary key,
    teacher_id    int not null,
    evaluation_id int not null,
    head_id       int not null,
    score         int not null
)
    comment '分数记录';

create table student
(
    id       int auto_increment
        primary key,
    hdu_id   varchar(20) not null comment '学号',
    name     varchar(20) not null comment '姓名',
    gender   int         not null comment '性别',
    age      int         not null comment '年龄',
    class_id int         not null comment '班级',
    major    int         not null comment '专业'
)
    comment '学生';

create table teacher
(
    id          int auto_increment
        primary key,
    name        varchar(20) not null,
    gender      int         not null,
    age         int         not null,
    position_id int         null,
    title_id    int         null,
    major       int         null,
    email       varchar(20) null,
    party       int         null
)
    comment '教师';

create table title
(
    id   int auto_increment
        primary key,
    name varchar(20) not null comment '职称名'
)
    comment '职称';