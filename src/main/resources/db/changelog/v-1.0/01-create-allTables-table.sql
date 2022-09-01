create table hibernate_sequence (
       next_val bigint
    ) engine=InnoDB
GO

    insert into hibernate_sequence values ( 1 )
GO

    create table pet_message (
       id integer not null,
        date datetime(6),
        filename varchar(255),
        string_date varchar(255),
        tag varchar(255),
        text varchar(3000),
        user_id bigint,
        primary key (id)
    ) engine=InnoDB
GO

    create table pet_notes (
       id integer not null,
        date datetime(6),
        string_date varchar(255),
        tag varchar(255),
        text varchar(3000),
        user_id bigint,
        primary key (id)
    ) engine=InnoDB
GO

    create table pet_user (
       id bigint not null,
        active bit,
        avatar varchar(255),
        name varchar(255),
        password varchar(255),
        surname varchar(255),
        username varchar(255),
        primary key (id)
    ) engine=InnoDB
GO

    create table pet_users_role (
       user_id bigint not null,
        roles varchar(255)
    ) engine=InnoDB
GO

    create table users_wallet (
       wallet_id bigint not null,
        balance double precision,
        currency varchar(255) not null,
        type varchar(255) not null,
        wallet_name varchar(255),
        id bigint,
        primary key (wallet_id)
    ) engine=InnoDB
GO

    create table wallet_finance (
       id integer not null,
        amount double precision,
        balance double precision,
        date datetime(6),
        operation varchar(255) not null,
        string_date varchar(255),
        wallet_id bigint,
        primary key (id)
    ) engine=InnoDB
GO

    alter table pet_message
       add constraint FKettrg217y0w73l13ek20ekjdm
       foreign key (user_id)
       references pet_user (id)
GO

    alter table pet_notes
       add constraint FK6bgt22xokk10h3t6btnf109ey
       foreign key (user_id)
       references pet_user (id)
GO

    alter table pet_users_role
       add constraint FKgcfa7899kjug2vx2727tfd4o7
       foreign key (user_id)
       references pet_user (id)
GO

    alter table users_wallet
       add constraint FKgahc2l7skx7hm9kmnr10elf2a
       foreign key (id)
       references pet_user (id)
GO

    alter table wallet_finance
       add constraint FKpy685yny0w86063dxh06au4xi
       foreign key (wallet_id)
       references users_wallet (wallet_id)