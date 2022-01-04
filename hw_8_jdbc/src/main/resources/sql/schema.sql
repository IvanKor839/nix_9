drop table if exists `groups_students`;
drop table if exists `groups`;
drop table if exists `students`;

create table `groups`
(
    id          bigint auto_increment
        primary key,
    created     timestamp    null,
    updated     timestamp    null,
    name        varchar(100) null,
    name_mentor varchar(100) null,
    group_type  varchar(20)  null
);

create table students
(
    id         bigint auto_increment
        primary key,
    created    timestamp    null,
    updated    timestamp    null,
    first_name varchar(100) null,
    last_name  varchar(100) null,
    age        int          null
);

create table groups_students
(
    id         bigint auto_increment
        primary key,
    id_group   bigint null,
    id_student bigint null,
    constraint имяОграничения
        unique (id_student, id_group),
    constraint groups_students_groups_id_fk
        foreign key (id_group) references `groups` (id),
    constraint groups_students_students_id_fk
        foreign key (id_student) references students (id)
);
