create table garagemodel
(
    id         bigint not null
        constraint garagemodel_pkey
            primary key,
    status     varchar(255),
    created_at timestamp,
    updated_at timestamp,
    name       varchar(255)
);

alter table garagemodel
    owner to postgres;

create table vehiclemodel
(
    id          bigint not null
        constraint vehiclemodel_pkey
            primary key,
    status      varchar(255),
    created_at  timestamp,
    updated_at  timestamp,
    colour      varchar(255),
    plate       varchar(255),
    ticket      varchar(255),
    vehicletype varchar(255)
);

alter table vehiclemodel
    owner to postgres;

create table slotmodel
(
    id         bigint not null
        constraint slotmodel_pkey
            primary key,
    status     varchar(255),
    created_at timestamp,
    updated_at timestamp,
    slotnumber integer,
    garage_id  bigint
        constraint fkj8dhhh7lm063ockpv86l1b0lm
            references garagemodel,
    vehicle_id bigint
        constraint fkdbuw7wvqxgddevogk60s5rbxv
            references vehiclemodel
);

alter table slotmodel
    owner to postgres;

