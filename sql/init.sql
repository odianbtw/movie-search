create table if not exists countries (
    id int auto_increment primary key,
    name varchar(64) not null unique,
    index idx_countries_name (name),
    created_at timestamp not null default current_timestamp,
    updated_at timestamp not null default current_timestamp
);

create table if not exists movies (
    id bigint auto_increment primary key,
    name varchar(128) not null,
    slogan varchar(255) default '',
    description varchar(1028) default '',
    release_date date,
    duration_time int,
    budget decimal(15,2),
    revenue decimal(15,2),
    rating enum('G', 'PG', 'PG-13', 'R', 'NC-17', 'UNRATED') not null default 'UNRATED',
    movie_type enum('MOVIE', 'SERIES') not null,
    created_at timestamp not null default current_timestamp,
    updated_at timestamp not null default current_timestamp,
    index idx_movies_name (name),
    unique key unique_movie (name, slogan, release_date)
);

create table if not exists genres (
    id int auto_increment primary key,
    name varchar(64) not null unique,
    index idx_genres_name (name),
    created_at timestamp not null default current_timestamp,
    updated_at timestamp not null default current_timestamp
);

create table if not exists production_studios (
    id int auto_increment primary key,
    name varchar(64) not null unique,
    country_id int not null,
    created_at timestamp not null default current_timestamp,
    updated_at timestamp not null default current_timestamp,
    foreign key (country_id) references countries(id) on delete cascade,
    index idx_studios_country (country_id)
);

create table if not exists persons (
    id bigint auto_increment primary key,
    name varchar(64) not null,
    country_id int not null,
    birth_date date,
    biography varchar(512) default '',
    created_at timestamp not null default current_timestamp,
    updated_at timestamp not null default current_timestamp,
    foreign key (country_id) references countries(id) on delete cascade,
    index idx_persons_name (name),
    index idx_persons_country (country_id),
    unique key unique_person (name, biography, country_id, birth_date)
);

create table if not exists medias (
    id bigint auto_increment primary key,
    name varchar(128) not null,
    url varchar(255) not null unique,
    media_type enum('POSTER', 'AVATAR', 'THUMBNAIL', 'TRAILER', 'TEASER', 'LOGO', 'PHOTO', 'COVER') not null,
    created_at timestamp not null default current_timestamp,
    updated_at timestamp not null default current_timestamp
);

create table if not exists awards (
    id int auto_increment primary key,
    name varchar(64) not null unique,
    description varchar(255) default '',
    awarded_at date not null,
    movie_id bigint,
    created_at timestamp not null default current_timestamp,
    updated_at timestamp not null default current_timestamp,
    foreign key (movie_id) references movies(id) on delete cascade,
    index idx_awards_movie_id (movie_id)
);

create table if not exists users (
    id bigint auto_increment primary key,
    name varchar(64) not null,
    username varchar(64) not null unique,
    email varchar(64) not null unique,
    password varchar(64) not null,
    created_at timestamp not null default current_timestamp,
    updated_at timestamp not null default current_timestamp
);

create table if not exists reviews (
    id bigint auto_increment primary key,
    score float not null default 0.0,
    message varchar(1028) default '',
    user_id bigint not null,
    movie_id bigint not null,
    status ENUM('NEW', 'OLD') not null default 'NEW',
    created_at timestamp not null default current_timestamp,
    updated_at timestamp not null default current_timestamp,
    foreign key (user_id) references users(id) on delete cascade,
    foreign key (movie_id) references movies(id) on delete cascade,
    index idx_reviews_movie (movie_id),
    index idx_reviews_user (user_id)
);

create table if not exists movie_scores (
    id bigint auto_increment primary key,
    movie_id bigint not null unique,
    score float not null default 0.0,
    created_at timestamp not null default current_timestamp,
    updated_at timestamp not null default current_timestamp,
    foreign key (movie_id) references movies(id),
    index idx_movie_scores_movie (movie_id)
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
    foreign key (movie_id) references movies(id) on delete cascade,
    foreign key (production_studio_id) references production_studios(id) on delete cascade
);

create table if not exists country_movie (
    movie_id bigint not null,
    country_id int not null,
    primary key (movie_id, country_id),
    index idx_cm_movie (movie_id),
    index idx_cm_country (country_id),
    foreign key (movie_id) references movies(id) on delete cascade,
    foreign key (country_id) references countries(id) on delete cascade
);

create table if not exists movie_credits (
    id bigint auto_increment primary key,
    person_id bigint not null,
    movie_id bigint not null,
    role enum('ACTOR', 'DIRECTOR', 'WRITER', 'COMPOSER', 'PRODUCER', 'CINEMATOGRAPHER', 'EDITOR') not null,
    created_at timestamp not null default current_timestamp,
    updated_at timestamp not null default current_timestamp,
    index idx_mc_movie (movie_id),
    index idx_mc_person (person_id),
    foreign key (person_id) references persons(id) on delete cascade,
    foreign key (movie_id) references movies(id) on delete cascade,
    unique key unique_movie_credit (person_id, movie_id, role)
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
    index idx_pm_media (media_id),
    foreign key (person_id) references persons(id) on delete cascade,
    foreign key (media_id) references medias(id) on delete cascade
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




