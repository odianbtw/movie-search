CREATE TABLE IF NOT EXISTS countries (
    id SERIAL PRIMARY KEY,
    name VARCHAR(64) NOT NULL UNIQUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);


CREATE TYPE rating_enum AS ENUM ('G', 'PG', 'PG-13', 'R', 'NC-17', 'UNRATED');
CREATE TYPE movie_type_enum AS ENUM ('MOVIE', 'SERIES');

CREATE TABLE IF NOT EXISTS movies (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(128) NOT NULL,
    slogan text DEFAULT '',
    description text DEFAULT '',
    release_date DATE,
    duration_time INT,
    budget BIGINT,
    revenue BIGINT,
    rating rating_enum NOT NULL DEFAULT 'UNRATED',
    movie_type movie_type_enum NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT unique_movie UNIQUE (name, slogan, release_date)
);

CREATE TABLE IF NOT EXISTS genres (
    id SERIAL PRIMARY KEY,
    name VARCHAR(64) NOT NULL UNIQUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS companies (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(64) NOT NULL UNIQUE,
    country_id INT NOT NULL,
    logo_id BIGINT NOT NULL,
    description TEXT DEFAULT '',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_company_country FOREIGN KEY (country_id) REFERENCES countries(id) ON DELETE CASCADE,
    CONSTRAINT fk_company_media FOREIGN KEY (logo_id) REFERENCES medias(id) ON DELETE CASCADE
);


CREATE TYPE media_type_enum AS ENUM ('LOGO', 'COVER', 'PHOTO', 'TRAILER');

CREATE TABLE IF NOT EXISTS medias (
    id BIGSERIAL PRIMARY KEY,
    url VARCHAR(255) NOT NULL,
    media_type media_type_enum NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

create table if not exists people (
    id bigserial primary key,
    name varchar(64) not null,
    biography text default '',
    country_id int not null,
    birth_date date,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    foreign key (country_id) references countries(id)
);

create table if not exists movie_genre (
    movie_id bigint not null,
    genre_id int not null,
    primary key (movie_id, genre_id),
    foreign key (movie_id) references movies(id),
    foreign key (genre_id) references genres(id)
);

create table if not exists movie_country (
    movie_id bigint not null,
    country_id int not null,
    primary key (movie_id, country_id),
    foreign key (movie_id) references movies(id),
    foreign key (country_id) references countries(id)
);

create table if not exists movie_company(
    movie_id bigint not null,
    company_id bigint not null,
    primary key (movie_id, company_id),
    foreign key (movie_id) references movies(id),
    foreign key (company_id) references companies(id)
);

create table if not exists movie_score (
    id bigserial primary key,
    movie_id bigint not null,
    score float default 0.0,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    foreign key (movie_id) references movies (id)
);

CREATE TYPE movie_role AS ENUM ('ACTOR', 'DIRECTOR', 'WRITER', 'COMPOSER', 'PRODUCER', 'CINEMATOGRAPHER', 'EDITOR');

create table if not exists movie_credits (
    id bigint primary key,
    person_id bigint not null,
    movie_id bigint not null,
    role movie_role not null default 'ACTOR'
);

create table if not exists movie_media (
    movie_id bigint not null,
    media_id bigint not null,
    primary key (movie_id, media_id),
    foreign key (movie_id) references movies(id),
    foreign key (media_id) references medias(id)
);

create table if not exists people_medias (
    person_id bigint not null,
    media_id bigint not null,
    primary key (person_id, media_id),
    foreign key (person_id) references people(id),
    foreign key (media_id) references medias(id)
);


CREATE TYPE award_status AS ENUM ('WINNER', 'NOMINEE');


create table if not exists movie_awards (
    id bigserial primary key,
    name varchar(128) not null,
    description text default '',
    movie_id bigint not null,
    result award_status not null default 'WINNER',
    awarded_at date not null,
    logo_id bigint,
    foreign key (movie_id) references movies(id),
    foreign key (logo_id) references medias(id)
);

create table if not exists people_awards (
    id bigserial primary key,
    name varchar(128) not null,
    description text default '',
    person_id bigint not null,
    result award_status not null default 'WINNER',
    awarded_at date not null,
    logo_id bigint,
    foreign key (person_id) references people(id),
    foreign key (logo_id) references medias(id)
);





-- create table if not exists awards (
--     id int auto_increment primary key,
--     name varchar(64) not null unique,
--     description varchar(255) default '',
--     awarded_at date not null,
--     movie_id bigint,
--     created_at timestamp not null default current_timestamp,
--     updated_at timestamp not null default current_timestamp,
--     foreign key (movie_id) references movies(id) on delete cascade,
--     index idx_awards_movie_id (movie_id)
-- );
--
-- create table if not exists users (
--     id bigint auto_increment primary key,
--     name varchar(64) not null,
--     username varchar(64) not null unique,
--     email varchar(64) not null unique,
--     password varchar(64) not null,
--     created_at timestamp not null default current_timestamp,
--     updated_at timestamp not null default current_timestamp
-- );
--
-- create table if not exists reviews (
--     id bigint auto_increment primary key,
--     score float not null default 0.0,
--     message varchar(1028) default '',
--     user_id bigint not null,
--     movie_id bigint not null,
--     status ENUM('NEW', 'OLD') not null default 'NEW',
--     created_at timestamp not null default current_timestamp,
--     updated_at timestamp not null default current_timestamp,
--     foreign key (user_id) references users(id) on delete cascade,
--     foreign key (movie_id) references movies(id) on delete cascade,
--     index idx_reviews_movie (movie_id),
--     index idx_reviews_user (user_id)
-- );
--
-- create table if not exists movie_scores (
--     id bigint auto_increment primary key,
--     movie_id bigint not null unique,
--     score float not null default 0.0,
--     created_at timestamp not null default current_timestamp,
--     updated_at timestamp not null default current_timestamp,
--     foreign key (movie_id) references movies(id),
--     index idx_movie_scores_movie (movie_id)
-- );
--
-- create table if not exists movie_genre (
--     movie_id bigint not null,
--     genre_id int not null,
--     primary key (movie_id, genre_id),
--     index idx_mg_movie (movie_id),
--     index idx_mg_genre (genre_id),
--     foreign key (movie_id) references movies(id) on delete cascade,
--     foreign key (genre_id) references genres(id) on delete cascade
-- );
--
-- create table if not exists production_studio_movie (
--     movie_id bigint not null,
--     production_studio_id int not null,
--     primary key (movie_id, production_studio_id),
--     index idx_psm_movie (movie_id),
--     index idx_psm_studio (production_studio_id),
--     foreign key (movie_id) references movies(id) on delete cascade,
--     foreign key (production_studio_id) references production_studios(id) on delete cascade
-- );
--
-- create table if not exists country_movie (
--     movie_id bigint not null,
--     country_id int not null,
--     primary key (movie_id, country_id),
--     index idx_cm_movie (movie_id),
--     index idx_cm_country (country_id),
--     foreign key (movie_id) references movies(id) on delete cascade,
--     foreign key (country_id) references countries(id) on delete cascade
-- );
--
-- create table if not exists movie_credits (
--     id bigint auto_increment primary key,
--     person_id bigint not null,
--     movie_id bigint not null,
--     role enum('ACTOR', 'DIRECTOR', 'WRITER', 'COMPOSER', 'PRODUCER', 'CINEMATOGRAPHER', 'EDITOR') not null,
--     created_at timestamp not null default current_timestamp,
--     updated_at timestamp not null default current_timestamp,
--     index idx_mc_movie (movie_id),
--     index idx_mc_person (person_id),
--     foreign key (person_id) references persons(id) on delete cascade,
--     foreign key (movie_id) references movies(id) on delete cascade,
--     unique key unique_movie_credit (person_id, movie_id, role)
-- );
--
-- create table if not exists movie_media (
--     movie_id bigint not null,
--     media_id bigint not null,
--     primary key (movie_id, media_id),
--     index idx_mm_movie (movie_id),
--     index idx_mm_media (media_id),
--     foreign key (movie_id) references movies(id) on delete cascade,
--     foreign key (media_id) references medias(id) on delete cascade
-- );
--
-- create table if not exists person_media (
--     person_id bigint not null,
--     media_id bigint not null,
--     primary key (person_id, media_id),
--     index idx_pm_person (person_id),
--     index idx_pm_media (media_id),
--     foreign key (person_id) references persons(id) on delete cascade,
--     foreign key (media_id) references medias(id) on delete cascade
-- );
--
--
--
--
--
--
--
--
--
--
--
--









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




