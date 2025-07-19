create sequence country_id_seq start with 1 increment by 1 cache 20;

create table if not exists countries (
                                         id int primary key default nextval('country_id_seq'),
    name varchar(128) unique not null,
    created_at timestamp not null default CURRENT_TIMESTAMP,
    updated_at timestamp not null default CURRENT_TIMESTAMP
    );

create sequence genre_id_seq start with 1 increment by 1 cache 20;

create table if not exists genres (
                                      id int primary key default nextval('genre_id_seq'),
    name varchar(64) unique not null,
    created_at timestamp not null default CURRENT_TIMESTAMP,
    updated_at timestamp not null default CURRENT_TIMESTAMP
    );

create sequence companies_id_seq start with 1 increment by 1 cache 20;

create table if not exists production_companies (
                                                    id int primary key default nextval('companies_id_seq'),
    name varchar(64) unique not null,
    description text default 'No information given',
    country_id int not null,
    created_at timestamp not null default CURRENT_TIMESTAMP,
    updated_at timestamp not null default CURRENT_TIMESTAMP,
    foreign key (country_id) references countries(id) on delete cascade
    );

create sequence media_id_seq start with 1 increment by 1 cache 20;
create type media_type_enum as enum ('LOGO', 'PORTRAIT', 'COVER', 'PHOTO', 'TRAILER');

create table if not exists medias (
                                      id bigint primary key default nextval('media_id_seq'),
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

create sequence titles_id_seq start with 1 increment by 1 cache 20;
create type title_type_enum as enum ('MOVIE', 'SERIES');

create table if not exists titles (
                                      id bigint primary key default nextval('titles_id_seq'),
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

create sequence series_content_id_seq start with 1 increment by 1 cache 20;


create table if not exists series_content (
                                              id bigint primary key default nextval('series_content_id_seq'),
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


create sequence people_id_seq start with 1 increment by 1 cache 20;

create table if not exists people (
                                      id bigint primary key default nextval('people_id_seq'),
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





insert into countries (id, name)
values
    (nextval('country_id_seq'), 'United States of America'),
    (nextval('country_id_seq'), 'Afghanistan'),
    (nextval('country_id_seq'), 'Albania'),
    (nextval('country_id_seq'), 'Algeria'),
    (nextval('country_id_seq'), 'Andorra'),
    (nextval('country_id_seq'), 'Angola'),
    (nextval('country_id_seq'), 'Antigua & Deps'),
    (nextval('country_id_seq'), 'Argentina'),
    (nextval('country_id_seq'), 'Armenia'),
    (nextval('country_id_seq'), 'Australia'),
    (nextval('country_id_seq'), 'Austria'),
    (nextval('country_id_seq'), 'Azerbaijan'),
    (nextval('country_id_seq'), 'Bahamas'),
    (nextval('country_id_seq'), 'Bahrain'),
    (nextval('country_id_seq'), 'Bangladesh'),
    (nextval('country_id_seq'), 'Barbados'),
    (nextval('country_id_seq'), 'Belarus'),
    (nextval('country_id_seq'), 'Belgium'),
    (nextval('country_id_seq'), 'Belize'),
    (nextval('country_id_seq'), 'Benin'),
    (nextval('country_id_seq'), 'Bhutan'),
    (nextval('country_id_seq'), 'Bolivia'),
    (nextval('country_id_seq'), 'Bosnia Herzegovina'),
    (nextval('country_id_seq'), 'Botswana'),
    (nextval('country_id_seq'), 'Brazil'),
    (nextval('country_id_seq'), 'Brunei'),
    (nextval('country_id_seq'), 'Bulgaria'),
    (nextval('country_id_seq'), 'Burkina'),
    (nextval('country_id_seq'), 'Burma'),
    (nextval('country_id_seq'), 'Burundi'),
    (nextval('country_id_seq'), 'Cambodia'),
    (nextval('country_id_seq'), 'Cameroon'),
    (nextval('country_id_seq'), 'Canada'),
    (nextval('country_id_seq'), 'Cape Verde'),
    (nextval('country_id_seq'), 'Central African Rep'),
    (nextval('country_id_seq'), 'Chad'),
    (nextval('country_id_seq'), 'Chile'),
    (nextval('country_id_seq'), 'Republic of China'),
    (nextval('country_id_seq'), 'Colombia'),
    (nextval('country_id_seq'), 'Comoros'),
    (nextval('country_id_seq'), 'Democratic Republic of the Congo'),
    (nextval('country_id_seq'), 'Republic of the Congo'),
    (nextval('country_id_seq'), 'Costa Rica'),
    (nextval('country_id_seq'), 'Croatia'),
    (nextval('country_id_seq'), 'Cuba'),
    (nextval('country_id_seq'), 'Cyprus'),
    (nextval('country_id_seq'), 'Czech Republic'),
    (nextval('country_id_seq'), 'Danzig'),
    (nextval('country_id_seq'), 'Denmark'),
    (nextval('country_id_seq'), 'Djibouti'),
    (nextval('country_id_seq'), 'Dominica'),
    (nextval('country_id_seq'), 'Dominican Republic'),
    (nextval('country_id_seq'), 'East Timor'),
    (nextval('country_id_seq'), 'Ecuador'),
    (nextval('country_id_seq'), 'Egypt'),
    (nextval('country_id_seq'), 'El Salvador'),
    (nextval('country_id_seq'), 'Equatorial Guinea'),
    (nextval('country_id_seq'), 'Eritrea'),
    (nextval('country_id_seq'), 'Estonia'),
    (nextval('country_id_seq'), 'Ethiopia'),
    (nextval('country_id_seq'), 'Fiji'),
    (nextval('country_id_seq'), 'Finland'),
    (nextval('country_id_seq'), 'France'),
    (nextval('country_id_seq'), 'Gabon'),
    (nextval('country_id_seq'), 'Gaza Strip'),
    (nextval('country_id_seq'), 'The Gambia'),
    (nextval('country_id_seq'), 'Georgia'),
    (nextval('country_id_seq'), 'Germany'),
    (nextval('country_id_seq'), 'Ghana'),
    (nextval('country_id_seq'), 'Greece'),
    (nextval('country_id_seq'), 'Grenada'),
    (nextval('country_id_seq'), 'Guatemala'),
    (nextval('country_id_seq'), 'Guinea'),
    (nextval('country_id_seq'), 'Guinea-Bissau'),
    (nextval('country_id_seq'), 'Guyana'),
    (nextval('country_id_seq'), 'Haiti'),
    (nextval('country_id_seq'), 'Holy Roman Empire'),
    (nextval('country_id_seq'), 'Honduras'),
    (nextval('country_id_seq'), 'Hungary'),
    (nextval('country_id_seq'), 'Iceland'),
    (nextval('country_id_seq'), 'India'),
    (nextval('country_id_seq'), 'Indonesia'),
    (nextval('country_id_seq'), 'Iran'),
    (nextval('country_id_seq'), 'Iraq'),
    (nextval('country_id_seq'), 'Republic of Ireland'),
    (nextval('country_id_seq'), 'Israel'),
    (nextval('country_id_seq'), 'Italy'),
    (nextval('country_id_seq'), 'Ivory Coast'),
    (nextval('country_id_seq'), 'Jamaica'),
    (nextval('country_id_seq'), 'Japan'),
    (nextval('country_id_seq'), 'Jonathanland'),
    (nextval('country_id_seq'), 'Jordan'),
    (nextval('country_id_seq'), 'Kazakhstan'),
    (nextval('country_id_seq'), 'Kenya'),
    (nextval('country_id_seq'), 'Kiribati'),
    (nextval('country_id_seq'), 'North Korea'),
    (nextval('country_id_seq'), 'South Korea'),
    (nextval('country_id_seq'), 'Kosovo'),
    (nextval('country_id_seq'), 'Kuwait'),
    (nextval('country_id_seq'), 'Kyrgyzstan'),
    (nextval('country_id_seq'), 'Laos'),
    (nextval('country_id_seq'), 'Latvia'),
    (nextval('country_id_seq'), 'Lebanon'),
    (nextval('country_id_seq'), 'Lesotho'),
    (nextval('country_id_seq'), 'Liberia'),
    (nextval('country_id_seq'), 'Libya'),
    (nextval('country_id_seq'), 'Liechtenstein'),
    (nextval('country_id_seq'), 'Lithuania'),
    (nextval('country_id_seq'), 'Luxembourg'),
    (nextval('country_id_seq'), 'Macedonia'),
    (nextval('country_id_seq'), 'Madagascar'),
    (nextval('country_id_seq'), 'Malawi'),
    (nextval('country_id_seq'), 'Malaysia'),
    (nextval('country_id_seq'), 'Maldives'),
    (nextval('country_id_seq'), 'Mali'),
    (nextval('country_id_seq'), 'Malta'),
    (nextval('country_id_seq'), 'Marshall Islands'),
    (nextval('country_id_seq'), 'Mauritania'),
    (nextval('country_id_seq'), 'Mauritius'),
    (nextval('country_id_seq'), 'Mexico'),
    (nextval('country_id_seq'), 'Micronesia'),
    (nextval('country_id_seq'), 'Moldova'),
    (nextval('country_id_seq'), 'Monaco'),
    (nextval('country_id_seq'), 'Mongolia'),
    (nextval('country_id_seq'), 'Montenegro'),
    (nextval('country_id_seq'), 'Morocco'),
    (nextval('country_id_seq'), 'Mount Athos'),
    (nextval('country_id_seq'), 'Mozambique'),
    (nextval('country_id_seq'), 'Namibia'),
    (nextval('country_id_seq'), 'Nauru'),
    (nextval('country_id_seq'), 'Nepal'),
    (nextval('country_id_seq'), 'Newfoundland'),
    (nextval('country_id_seq'), 'Netherlands'),
    (nextval('country_id_seq'), 'New Zealand'),
    (nextval('country_id_seq'), 'Nicaragua'),
    (nextval('country_id_seq'), 'Niger'),
    (nextval('country_id_seq'), 'Nigeria'),
    (nextval('country_id_seq'), 'Norway'),
    (nextval('country_id_seq'), 'Oman'),
    (nextval('country_id_seq'), 'Ottoman Empire'),
    (nextval('country_id_seq'), 'Pakistan'),
    (nextval('country_id_seq'), 'Palau'),
    (nextval('country_id_seq'), 'Panama'),
    (nextval('country_id_seq'), 'Papua New Guinea'),
    (nextval('country_id_seq'), 'Paraguay'),
    (nextval('country_id_seq'), 'Peru'),
    (nextval('country_id_seq'), 'Philippines'),
    (nextval('country_id_seq'), 'Poland'),
    (nextval('country_id_seq'), 'Portugal'),
    (nextval('country_id_seq'), 'Prussia'),
    (nextval('country_id_seq'), 'Qatar'),
    (nextval('country_id_seq'), 'Romania'),
    (nextval('country_id_seq'), 'Rome'),
    (nextval('country_id_seq'), 'Russian Federation'),
    (nextval('country_id_seq'), 'Rwanda'),
    (nextval('country_id_seq'), 'Grenadines'),
    (nextval('country_id_seq'), 'Samoa'),
    (nextval('country_id_seq'), 'San Marino'),
    (nextval('country_id_seq'), 'Sao Tome & Principe'),
    (nextval('country_id_seq'), 'Saudi Arabia'),
    (nextval('country_id_seq'), 'Senegal'),
    (nextval('country_id_seq'), 'Serbia'),
    (nextval('country_id_seq'), 'Seychelles'),
    (nextval('country_id_seq'), 'Sierra Leone'),
    (nextval('country_id_seq'), 'Singapore'),
    (nextval('country_id_seq'), 'Slovakia'),
    (nextval('country_id_seq'), 'Slovenia'),
    (nextval('country_id_seq'), 'Solomon Islands'),
    (nextval('country_id_seq'), 'Somalia'),
    (nextval('country_id_seq'), 'South Africa'),
    (nextval('country_id_seq'), 'Spain'),
    (nextval('country_id_seq'), 'Sri Lanka'),
    (nextval('country_id_seq'), 'Sudan'),
    (nextval('country_id_seq'), 'Suriname'),
    (nextval('country_id_seq'), 'Swaziland'),
    (nextval('country_id_seq'), 'Sweden'),
    (nextval('country_id_seq'), 'Switzerland'),
    (nextval('country_id_seq'), 'Syria'),
    (nextval('country_id_seq'), 'Tajikistan'),
    (nextval('country_id_seq'), 'Tanzania'),
    (nextval('country_id_seq'), 'Thailand'),
    (nextval('country_id_seq'), 'Togo'),
    (nextval('country_id_seq'), 'Tonga'),
    (nextval('country_id_seq'), 'Trinidad & Tobago'),
    (nextval('country_id_seq'), 'Tunisia'),
    (nextval('country_id_seq'), 'Turkey'),
    (nextval('country_id_seq'), 'Turkmenistan'),
    (nextval('country_id_seq'), 'Tuvalu'),
    (nextval('country_id_seq'), 'Uganda'),
    (nextval('country_id_seq'), 'Ukraine'),
    (nextval('country_id_seq'), 'United Arab Emirates'),
    (nextval('country_id_seq'), 'United Kingdom'),
    (nextval('country_id_seq'), 'Uruguay'),
    (nextval('country_id_seq'), 'Uzbekistan'),
    (nextval('country_id_seq'), 'Vanuatu'),
    (nextval('country_id_seq'), 'Vatican City'),
    (nextval('country_id_seq'), 'Venezuela'),
    (nextval('country_id_seq'), 'Vietnam'),
    (nextval('country_id_seq'), 'Yemen'),
    (nextval('country_id_seq'), 'Zambia'),
    (nextval('country_id_seq'), 'Zimbabwe');


insert into genres (id, name)
values
    (nextval('genre_id_seq'), 'Action'),
    (nextval('genre_id_seq'), 'Adventure'),
    (nextval('genre_id_seq'), 'Animation'),
    (nextval('genre_id_seq'), 'Comedy'),
    (nextval('genre_id_seq'), 'Crime'),
    (nextval('genre_id_seq'), 'Drama'),
    (nextval('genre_id_seq'), 'Fantasy'),
    (nextval('genre_id_seq'), 'Horror'),
    (nextval('genre_id_seq'), 'Mystery'),
    (nextval('genre_id_seq'), 'Romance'),
    (nextval('genre_id_seq'), 'Science Fiction'),
    (nextval('genre_id_seq'), 'Thriller'),
    (nextval('genre_id_seq'), 'War'),
    (nextval('genre_id_seq'), 'Western'),
    (nextval('genre_id_seq'), 'Documentary'),
    (nextval('genre_id_seq'), 'Family'),
    (nextval('genre_id_seq'), 'Biography'),
    (nextval('genre_id_seq'), 'History'),
    (nextval('genre_id_seq'), 'Musical'),
    (nextval('genre_id_seq'), 'Sport');





