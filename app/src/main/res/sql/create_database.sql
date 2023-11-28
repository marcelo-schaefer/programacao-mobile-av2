create table `user` (
    id integer primary key autoincrement,
    name text not null,
    email text not null,
    password text not null
);

create table kind ( 
    id integer primary key autoincrement, 
    name text not null 
);

create table animal ( 
    id integer primary key autoincrement, 
    name text not null, 
    description text, 
    kind_id integer not null, 
    user_id integer not null,  
    constraint fk_kind_animal 
        foreign key (kind_id) 
            references kind (id),  
    constraint fk_user_animal 
        foreign key (user_id) 
            references user (id)  
);

create table consultation ( 
    id integer primary key autoincrement, 
    scheduled_time timestamp not null,
    animal_id integer not null, 
    constraint fk_consultation_animal 
        foreign key (animal_id) 
            references animal (id)
);