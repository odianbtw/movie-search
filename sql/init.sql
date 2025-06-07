create table if not exists movies (
    id bigint auto_increment primary key,
    name varchar(512) not null,
    slogan varchar(1024) default '',
    description varchar(2048) default '',
    release_date date,
    score float default 0.0,
    duration_time int,
    budget decimal(15,2) default 0.00,
    revenue decimal(15,2) default 0.00,
    rating varchar(32) not null default '',
    created_at timestamp not null default current_timestamp,
    updated_at timestamp not null default current_timestamp,
    index idx_movies_name (name)
);

create table if not exists genres (
    id int auto_increment primary key,
    name varchar(255) not null unique,
    created_at timestamp not null default current_timestamp,
    updated_at timestamp not null default current_timestamp
);

create table if not exists production_studios (
    id int auto_increment primary key,
    name varchar(255) not null unique,
    country_id int,
    created_at timestamp not null default current_timestamp,
    updated_at timestamp not null default current_timestamp,
    foreign key (country_id) references countries(id) on delete set null,
    index idx_studios_country (country_id)
);

create table if not exists movie_genre (
    movie_id bigint not null,
    genre_id int not null,
    primary key (movie_id, genre_id),
    index idx_mg_movie (movie_id),
    index idx_mg_genre (genre_id),
    foreign key (movie_id) references movies(id) on delete cascade,
    foreign key (genre_id) references genres(id) on delete cascade
);

create table if not exists production_studio_movie (
    movie_id bigint not null,
    production_studio_id int not null,
    primary key (movie_id, production_studio_id),
    index idx_psm_movie (movie_id),
    index idx_psm_studio (production_studio_id),
    foreign key (movie_id)references movies(id) on delete cascade,
    foreign key (production_studio_id) references production_studios(id) on delete cascade
);

create table if not exists countries (
    id int auto_increment primary key,
    name varchar(255) not null unique,
    created_at timestamp not null default current_timestamp,
    updated_at timestamp not null default current_timestamp
);

create table if not exists country_movie (
    movie_id bigint not null,
    country_id int not null,
    primary key (movie_id, country_id),
    index idx_cm_movie (movie_id),
    index idx_cm_country (country_id),
    foreign key (movie_id)references movies(id) on delete cascade,
    foreign key (country_id) references countries(id) on delete cascade
);


create table if not exists persons (
    id bigint auto_increment primary key,
    name varchar(255) not null,
    country_id int,
    birth_date date,
    biography varchar(2048) default '',
    created_at timestamp not null default current_timestamp,
    updated_at timestamp not null default current_timestamp,
    foreign key (country_id) references countries(id) on delete set null,
    index idx_persons_name (name),
    index idx_persons_county (country_id)
);

create table if not exists movie_credit (
    person_id bigint not null,
    movie_id bigint not null,
    role varchar(64) not null,
    primary key (movie_id, person_id),
    index idx_mc_movie (movie_id),
    index idx_mc_person (person_id),
    foreign key (person_id) references persons(id) on delete cascade,
    foreign key (movie_id) references movies(id) on delete cascade
);

create table if not exists medias (
    id bigint auto_increment primary key,
    name varchar(255) not null,
    url varchar(255) not null unique,
    media_type varchar(64) not null,
    created_at timestamp not null default current_timestamp,
    updated_at timestamp not null default current_timestamp
);

create table if not exists movie_media (
    movie_id bigint not null,
    media_id bigint not null,
    primary key (movie_id, media_id),
    index idx_mm_movie (movie_id),
    index idx_mm_media (media_id),
    foreign key (movie_id) references movies(id) on delete cascade,
    foreign key (media_id) references medias(id) on delete cascade
);

create table if not exists person_media (
    person_id bigint not null,
    media_id bigint not null,
    primary key (person_id, media_id),
    index idx_pm_person (person_id),
    index idx_pm_media  (media_id),
    foreign key (person_id) references persons(id) on delete cascade,
    foreign key (media_id) references medias(id) on delete cascade
);


create table if not exists person_role (
    person_id bigint not null,
    role varchar(64) not null,
    primary key (person_id),
    index idx_pr_personid (person_id),
    foreign key (person_id) references persons(id) on delete cascade
);

create table if not exists awards (
    id int auto_increment primary key,
    name varchar(64) not null unique,
    description varchar(255) default '',
    awarded_at date not null,
    created_at timestamp not null default current_timestamp,
    updated_at timestamp not null default current_timestamp
);

create table if not exists movie_award (
    movie_id bigint not null,
    award_id bigint not null,
    foreign key (movie_id) references movies(id) on delete cascade,
    foreign key (award_id) references awards(id) on delete cascade
);