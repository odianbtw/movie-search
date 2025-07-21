create table if not exists countries (
    id serial primary key,
    name varchar(128) unique not null,
    created_at timestamp not null default CURRENT_TIMESTAMP,
    updated_at timestamp not null default CURRENT_TIMESTAMP
);

create table if not exists genres (
    id serial primary key,
    name varchar(64) unique not null,
    created_at timestamp not null default CURRENT_TIMESTAMP,
    updated_at timestamp not null default CURRENT_TIMESTAMP
);

create table if not exists production_companies (
    id serial primary key,
    name varchar(64) unique not null,
    description text default 'No information given',
    country_id int not null,
    created_at timestamp not null default CURRENT_TIMESTAMP,
    updated_at timestamp not null default CURRENT_TIMESTAMP,
    foreign key (country_id) references countries(id) on delete cascade
);

create type media_type_enum as enum ('LOGO', 'PORTRAIT', 'COVER', 'PHOTO', 'TRAILER');

create table if not exists medias (
    id bigserial primary key,
    url varchar(128) not null,
    media_type media_type_enum not null default 'LOGO',
    created_at timestamp not null default CURRENT_TIMESTAMP,
    updated_at timestamp not null default CURRENT_TIMESTAMP
);

create table if not exists production_company_media (
    company_id int not null,
    media_id bigint not null ,
    primary key (company_id, media_id),
    foreign key (company_id) references production_companies(id) on delete cascade,
    foreign key (media_id) references medias(id) on delete cascade
);

create type title_type_enum as enum ('MOVIE', 'SERIES');

create table if not exists titles (
    id bigserial primary key,
    imdb_id varchar(32) unique,
    title varchar(128) not null,
    title_type title_type_enum not null default 'MOVIE',
    created_at timestamp not null default CURRENT_TIMESTAMP,
    updated_at timestamp not null default CURRENT_TIMESTAMP,
    unique (imdb_id, title)
);

create index idx_titles on titles(title);

create table if not exists title_scores (
    title_id bigint primary key,
    score decimal(2,2) not null default 0.0,
    created_at timestamp not null default CURRENT_TIMESTAMP,
    updated_at timestamp not null default CURRENT_TIMESTAMP,
    foreign key (title_id) references titles(id)
);

create table if not exists title_genre (
    title_id int not null,
    genre_id bigint not null ,
    primary key (title_id, genre_id),
    foreign key (title_id) references titles(id) on delete cascade,
    foreign key (genre_id) references genres(id) on delete cascade
);

create table if not exists title_country (
    title_id int not null,
    country_id bigint not null ,
    primary key (title_id, country_id),
    foreign key (title_id) references titles(id) on delete cascade,
    foreign key (country_id) references countries(id) on delete cascade
);

create table if not exists title_company (
    title_id int not null,
    company_id bigint not null ,
    primary key (title_id, company_id),
    foreign key (title_id) references titles(id) on delete cascade,
    foreign key (company_id) references production_companies(id) on delete cascade
);


create table if not exists title_media (
    title_id int not null,
    media_id bigint not null ,
    primary key (title_id, media_id),
    foreign key (title_id) references titles(id) on delete cascade,
    foreign key (media_id) references medias(id) on delete cascade
);



create type age_rating_enum as enum ('G', 'PG', 'PG-13', 'R', 'NC_17', 'UNRATED');

create table if not exists movies_info (
    movie_id bigint primary key,
    slogan varchar(255) default 'No slogan given',
    description text default 'No description given',
    age_rating age_rating_enum not null default 'UNRATED',
    budget int,
    revenue int,
    release_date date not null,
    duration_minutes int,
    foreign key (movie_id) references titles(id) on delete cascade
);

create table if not exists series_info (
    series_id bigint primary key,
    slogan varchar(255) default 'No slogan given',
    description text default 'No description given',
    age_rating age_rating_enum not null default 'UNRATED',
    budget int,
    revenue int,
    foreign key (series_id) references titles(id) on delete cascade
);




create table if not exists series_content (
    id bigserial primary key,
    imdb_id varchar(32) unique,
    series_id bigint not null,
    season_number smallint not null default 1,
    episode_number smallint not null default 1,
    episode_name varchar(128) not null default '',
    release_date date not null,
    duration_minutes int,
    created_at timestamp not null default CURRENT_TIMESTAMP,
    updated_at timestamp not null default CURRENT_TIMESTAMP,
    unique (series_id, season_number, episode_number),
    foreign key (series_id) references titles(id) on delete cascade
);


create table if not exists episode_scores (
    episode_id bigint primary key,
    score decimal(2,2) not null default 0.0,
    created_at timestamp not null default CURRENT_TIMESTAMP,
    updated_at timestamp not null default CURRENT_TIMESTAMP,
    foreign key (episode_id) references series_content(id)
);

create table if not exists series_content_media (
    series_content_id bigint not null,
    media_id bigint not null,
    primary key (series_content_id, media_id),
    foreign key (series_content_id) references series_content(id),
    foreign key (media_id) references medias(id)
);


create table if not exists people (
    id bigserial primary key,
    imdb_id varchar(32) unique,
    name varchar(64) not null,
    biography text not null default 'No information given',
    country_id int not null,
    created_at timestamp not null default CURRENT_TIMESTAMP,
    updated_at timestamp not null default CURRENT_TIMESTAMP,
    unique (imdb_id, name),
    foreign key (country_id) references countries(id)
);

create table if not exists people_media (
    person_id bigint not null,
    media_id bigint not null,
    primary key (person_id, media_id),
    foreign key (person_id) references people(id),
    foreign key (media_id) references medias(id)
);

create type title_role_enum as enum ('ACTOR', 'DIRECTOR', 'WRITER', 'COMPOSER', 'PRODUCER', 'CINEMATOGRAPHER', 'EDITOR');

create table if not exists movie_cast (
    movie_id bigint not null,
    person_id bigint not null,
    role title_role_enum not null default 'ACTOR',
    primary key (movie_id, person_id),
    foreign key (movie_id) references titles(id),
    foreign key (person_id) references people(id)
);

create table if not exists series_cast (
    episode_id bigint not null,
    person_id bigint not null,
    role title_role_enum not null default 'ACTOR',
    primary key (episode_id, person_id),
    foreign key (episode_id) references series_content(id),
    foreign key (person_id) references people(id)
);





insert into countries (name)
values
    ('United States of America'),
    ('Afghanistan'),
    ('Albania'),
    ('Algeria'),
    ('Andorra'),
    ('Angola'),
    ('Antigua & Deps'),
    ('Argentina'),
    ('Armenia'),
    ('Australia'),
    ('Austria'),
    ('Azerbaijan'),
    ('Bahamas'),
    ('Bahrain'),
    ('Bangladesh'),
    ('Barbados'),
    ('Belarus'),
    ('Belgium'),
    ('Belize'),
    ('Benin'),
    ('Bhutan'),
    ('Bolivia'),
    ('Bosnia Herzegovina'),
    ('Botswana'),
    ('Brazil'),
    ('Brunei'),
    ('Bulgaria'),
    ('Burkina'),
    ('Burma'),
    ('Burundi'),
    ('Cambodia'),
    ('Cameroon'),
    ('Canada'),
    ('Cape Verde'),
    ('Central African Rep'),
    ('Chad'),
    ('Chile'),
    ('Republic of China'),
    ('Colombia'),
    ('Comoros'),
    ('Democratic Republic of the Congo'),
    ('Republic of the Congo'),
    ('Costa Rica'),
    ('Croatia'),
    ('Cuba'),
    ('Cyprus'),
    ('Czech Republic'),
    ('Danzig'),
    ('Denmark'),
    ('Djibouti'),
    ('Dominica'),
    ('Dominican Republic'),
    ('East Timor'),
    ('Ecuador'),
    ('Egypt'),
    ('El Salvador'),
    ('Equatorial Guinea'),
    ('Eritrea'),
    ('Estonia'),
    ('Ethiopia'),
    ('Fiji'),
    ('Finland'),
    ('France'),
    ('Gabon'),
    ('Gaza Strip'),
    ('The Gambia'),
    ('Georgia'),
    ('Germany'),
    ('Ghana'),
    ('Greece'),
    ('Grenada'),
    ('Guatemala'),
    ('Guinea'),
    ('Guinea-Bissau'),
    ('Guyana'),
    ('Haiti'),
    ('Holy Roman Empire'),
    ('Honduras'),
    ('Hungary'),
    ('Iceland'),
    ('India'),
    ('Indonesia'),
    ('Iran'),
    ('Iraq'),
    ('Republic of Ireland'),
    ('Israel'),
    ('Italy'),
    ('Ivory Coast'),
    ('Jamaica'),
    ('Japan'),
    ('Jonathanland'),
    ('Jordan'),
    ('Kazakhstan'),
    ('Kenya'),
    ('Kiribati'),
    ('North Korea'),
    ('South Korea'),
    ('Kosovo'),
    ('Kuwait'),
    ('Kyrgyzstan'),
    ('Laos'),
    ('Latvia'),
    ('Lebanon'),
    ('Lesotho'),
    ('Liberia'),
    ('Libya'),
    ('Liechtenstein'),
    ('Lithuania'),
    ('Luxembourg'),
    ('Macedonia'),
    ('Madagascar'),
    ('Malawi'),
    ('Malaysia'),
    ('Maldives'),
    ('Mali'),
    ('Malta'),
    ('Marshall Islands'),
    ('Mauritania'),
    ('Mauritius'),
    ('Mexico'),
    ('Micronesia'),
    ('Moldova'),
    ('Monaco'),
    ('Mongolia'),
    ('Montenegro'),
    ('Morocco'),
    ('Mount Athos'),
    ('Mozambique'),
    ('Namibia'),
    ('Nauru'),
    ('Nepal'),
    ('Newfoundland'),
    ('Netherlands'),
    ('New Zealand'),
    ('Nicaragua'),
    ('Niger'),
    ('Nigeria'),
    ('Norway'),
    ('Oman'),
    ('Ottoman Empire'),
    ('Pakistan'),
    ('Palau'),
    ('Panama'),
    ('Papua New Guinea'),
    ('Paraguay'),
    ('Peru'),
    ('Philippines'),
    ('Poland'),
    ('Portugal'),
    ('Prussia'),
    ('Qatar'),
    ('Romania'),
    ('Rome'),
    ('Russian Federation'),
    ('Rwanda'),
    ('Grenadines'),
    ('Samoa'),
    ('San Marino'),
    ('Sao Tome & Principe'),
    ('Saudi Arabia'),
    ('Senegal'),
    ('Serbia'),
    ('Seychelles'),
    ('Sierra Leone'),
    ('Singapore'),
    ('Slovakia'),
    ('Slovenia'),
    ('Solomon Islands'),
    ('Somalia'),
    ('South Africa'),
    ('Spain'),
    ('Sri Lanka'),
    ('Sudan'),
    ('Suriname'),
    ('Swaziland'),
    ('Sweden'),
    ('Switzerland'),
    ('Syria'),
    ('Tajikistan'),
    ('Tanzania'),
    ('Thailand'),
    ('Togo'),
    ('Tonga'),
    ('Trinidad & Tobago'),
    ('Tunisia'),
    ('Turkey'),
    ('Turkmenistan'),
    ('Tuvalu'),
    ('Uganda'),
    ('Ukraine'),
    ('United Arab Emirates'),
    ('United Kingdom'),
    ('Uruguay'),
    ('Uzbekistan'),
    ('Vanuatu'),
    ('Vatican City'),
    ('Venezuela'),
    ('Vietnam'),
    ('Yemen'),
    ('Zambia'),
    ('Zimbabwe');

insert into genres (name)
values
    ('Action'),
    ('Adventure'),
    ('Animation'),
    ('Comedy'),
    ('Crime'),
    ('Drama'),
    ('Fantasy'),
    ('Horror'),
    ('Mystery'),
    ('Romance'),
    ('Science Fiction'),
    ('Thriller'),
    ('War'),
    ('Western'),
    ('Documentary'),
    ('Family'),
    ('Biography'),
    ('History'),
    ('Musical'),
    ('Sport');



