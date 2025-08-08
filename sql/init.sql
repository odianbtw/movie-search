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

create table if not exists languages (
    id serial primary key,
    name varchar(64) unique not null,
    created_at timestamp not null default CURRENT_TIMESTAMP,
    updated_at timestamp not null default CURRENT_TIMESTAMP
);

create table if not exists production_studios (
    id uuid primary key,
    name varchar(64) unique not null,
    created_at timestamp not null default CURRENT_TIMESTAMP,
    updated_at timestamp not null default CURRENT_TIMESTAMP
);

create table if not exists keywords (
    id uuid primary key,
    name varchar(64) unique not null,
    created_at timestamp not null default CURRENT_TIMESTAMP,
    updated_at timestamp not null default CURRENT_TIMESTAMP
);

create type media_type_enum as enum (
    'LOGO',
    'PROFILE',
    'POSTER',
    'PHOTO',
    'TRAILER',
    'BACKDROP',
    'BANNER',
    'TEASER',
    'FEATURETTE',
    'CLIP',
    'BLOOPER',
    'INTERVIEW',
    'AVATAR'
);

create table if not exists medias (
    id uuid primary key,
    url varchar(256) not null,
    media_type media_type_enum not null default 'PHOTO',
    created_at timestamp not null default CURRENT_TIMESTAMP,
    updated_at timestamp not null default CURRENT_TIMESTAMP
);

create table if not exists films (
    id uuid primary key,
    slug varchar(256) not null unique,
    name varchar(256) not null,
    original_name varchar(256) not null,
    imdb_url varchar(256) unique,
    tmdb_url varchar(256) not null unique,
    tagline text,
    description text,
    release_date date not null,
    runtime int not null,
    created_at timestamp not null default CURRENT_TIMESTAMP,
    updated_at timestamp not null default CURRENT_TIMESTAMP
);

create table if not exists film_media (
   film_id uuid not null,
   media_id uuid not null ,
   primary key (film_id, media_id),
   foreign key (film_id) references films(id) on delete cascade,
   foreign key (media_id) references medias(id) on delete cascade
);

create table if not exists essential_film_media (
    film_id uuid not null primary key,
    poster_id uuid not null,
    backdrop_id uuid not null,
    trailer_id uuid not null,
    foreign key (film_id) references films (id) on delete cascade,
    foreign key (poster_id) references medias (id) on delete set null,
    foreign key (backdrop_id) references medias (id) on delete set null,
    foreign key (trailer_id) references medias (id) on delete set null
);

create table if not exists film_genre (
    film_id uuid not null,
    genre_id int not null,
    primary key (film_id, genre_id),
    foreign key (film_id) references films(id) on delete cascade,
    foreign key (genre_id) references genres(id) on delete cascade
);

create table if not exists film_country (
    film_id uuid not null,
    country_id int not null,
    primary key (film_id, country_id),
    foreign key (film_id) references films(id) on delete cascade,
    foreign key (country_id) references countries(id) on delete cascade
);

create table if not exists film_studio (
    film_id uuid not null,
    studio_id uuid not null,
    primary key (film_id, studio_id),
    foreign key (film_id) references films(id) on delete cascade,
    foreign key (studio_id) references production_studios(id) on delete cascade
);

create table if not exists film_language (
    film_id uuid not null,
    language_id int not null,
    primary key (film_id, language_id),
    foreign key (film_id) references films(id) on delete cascade,
    foreign key (language_id) references languages(id) on delete cascade
);

create table if not exists film_keyword (
    film_id uuid not null,
    keyword_id uuid not null,
    primary key (film_id, keyword_id),
    foreign key (film_id) references films(id) on delete cascade,
    foreign key (keyword_id) references keywords(id) on delete cascade
);

-- todo: consider about adding popularity to this table
create table if not exists film_ratings (
    film_id uuid not null primary key,
    rating decimal(2,2) not null default 0.0 check ( rating <= 10.0 and rating >= 0.0),
    amount_of_ratings int not null default 0,
    foreign key (film_id) references films(id) on delete cascade
);

create table if not exists film_popularity (
    film_id uuid primary key,
    popularity float not null,
    updated_at timestamp default CURRENT_TIMESTAMP,
    foreign key (film_id) references films(id) on delete cascade
);

create table if not exists film_trending (
    film_id uuid primary key,
    popularity float not null,
    updated_at timestamp default CURRENT_TIMESTAMP,
    foreign key (film_id) references films(id) on delete cascade
);

create table if not exists people (
    id uuid not null primary key,
    slug varchar(256) not null unique,
    name varchar(256) not null,
    biography text not null,
    profile_photo_id uuid,
    created_at timestamp not null default CURRENT_TIMESTAMP,
    updated_at timestamp not null default CURRENT_TIMESTAMP,
    foreign key (profile_photo_id) references medias(id) on delete set null
);

create table if not exists people_keyword (
    person_id uuid not null,
    keyword_id uuid not null,
    primary key (person_id, keyword_id),
    foreign key (person_id) references people(id) on delete cascade,
    foreign key (keyword_id) references keywords(id) on delete cascade
);

create type contributor_type_enum as enum (
    'ACTOR',
    'DIRECTOR',
    'CO_DIRECTOR',
    'PRODUCER',
    'WRITER',
    'ORIGINAL_WRITER',
    'STORY',
    'CASTING',
    'EDITOR',
    'CINEMATOGRAPHY',
    'ASSISTANT_DIRECTOR',
    'ADDITIONAL_DIRECTING',
    'EXECUTIVE_PRODUCER',
    'LIGHTING',
    'CAMERA_OPERATOR',
    'ADDITIONAL_PHOTOGRAPHY',
    'PRODUCTION_DESIGN',
    'ART_DIRECTION',
    'SET_DECORATION',
    'SPECIAL_EFFECTS',
    'VISUAL_EFFECTS',
    'TITLE_DESIGN',
    'STUNTS',
    'CHOREOGRAPHY',
    'COMPOSER',
    'SONGS',
    'SOUND',
    'COSTUMES',
    'CREATOR',
    'MAKE_UP',
    'HAIRSTYLING',
    'STUDIO'
);

create table if not exists film_contributions (
    id uuid primary key,
    film_id uuid not null,
    person_id uuid not null,
    contributor_type contributor_type_enum not null default 'ACTOR',
    billing_order int,
    check (
      (contributor_type = 'ACTOR' and billing_order is not null)
          or
      (contributor_type <> 'ACTOR' and billing_order is null)
    ),
    character_name varchar(128),
    check (
        (contributor_type = 'ACTOR' and character_name is not null)
            or
        (contributor_type <> 'ACTOR' and character_name is null)
    )
);

create type user_role_enum as enum (
    'ADMIN',
    'USER'
);

create table if not exists users (
    id uuid primary key,
    username varchar(128) unique not null,
    email varchar(256) unique not null,
    password_hash varchar(128) not null,
    user_role user_role_enum not null default 'USER',
    email_verified boolean not null default false,
    created_at timestamp not null default CURRENT_TIMESTAMP,
    updated_at timestamp not null default CURRENT_TIMESTAMP
);

create table if not exists password_reset_tokens (
    id uuid primary key,
    user_id uuid not null,
    token_hash varchar(64) not null,
    expires_at timestamp not null,
    used boolean not null default false,
    created_at timestamp not null default CURRENT_TIMESTAMP
);

create table if not exists essential_user_media (
    user_id uuid primary key,
    avatar_id uuid,
    backdrop_id uuid,
    foreign key (user_id) references users(id) on delete cascade,
    foreign key (avatar_id) references medias(id) on delete set null,
    foreign key (backdrop_id) references medias(id) on delete set null
);

create table if not exists favourite_films (
    id uuid primary key,
    user_id uuid not null,
    film_id uuid not null,
    created_at timestamp not null default CURRENT_TIMESTAMP,
    updated_at timestamp not null default CURRENT_TIMESTAMP,
    foreign key (user_id) references users(id) on delete cascade,
    foreign key (film_id) references films(id) on delete cascade
);

create table if not exists film_watched (
    user_id uuid not null,
    film_id uuid not null,
    created_at timestamp not null default CURRENT_TIMESTAMP,
    updated_at timestamp not null default CURRENT_TIMESTAMP,
    primary key (user_id, film_id),
    foreign key (user_id) references users(id) on delete cascade,
    foreign key (film_id) references films(id) on delete cascade
);

create table if not exists film_liked (
    user_id uuid not null,
    film_id uuid not null,
    created_at timestamp not null default CURRENT_TIMESTAMP,
    updated_at timestamp not null default CURRENT_TIMESTAMP,
    primary key (user_id, film_id),
    foreign key (user_id) references users(id) on delete cascade,
    foreign key (film_id) references films(id) on delete cascade
);

create table if not exists film_review (
    id uuid primary key,
    user_id uuid not null,
    film_id uuid not null,
    rating decimal(2,2) not null default 0.0 check ( rating <= 10.0 and rating >= 0.0),
    contains_spoilers boolean not null default false,
    content text not null,
    created_at timestamp not null default CURRENT_TIMESTAMP,
    updated_at timestamp not null default CURRENT_TIMESTAMP,
    foreign key (user_id) references users(id) on delete cascade,
    foreign key (film_id) references films(id) on delete cascade
);

CREATE OR REPLACE FUNCTION update_film_rating_on_new_review()
    RETURNS TRIGGER AS $$
DECLARE
    old_count integer;
    old_avg decimal(4,2);
BEGIN
    SELECT amount_of_ratings, rating
    INTO old_count, old_avg
    FROM film_ratings
    WHERE film_id = NEW.film_id;

    UPDATE film_ratings
    SET
        amount_of_ratings = old_count + 1,
        rating = ((old_avg * old_count) + NEW.rating) / (old_count + 1)
    WHERE film_id = NEW.film_id;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_update_film_rating
    AFTER INSERT ON film_review
    FOR EACH ROW
EXECUTE FUNCTION update_film_rating_on_new_review();

create table if not exists review_likes (
    review_id uuid not null,
    user_id uuid not null,
    primary key (review_id, user_id),
    foreign key (user_id) references users(id) on delete cascade,
    foreign key (review_id) references film_review(id) on delete cascade
);

create table if not exists watchlist (
    id uuid primary key,
    user_id uuid not null,
    private boolean not null default false,
    created_at timestamp not null default CURRENT_TIMESTAMP,
    updated_at timestamp not null default CURRENT_TIMESTAMP,
    foreign key (user_id) references users(id) on delete cascade
);

create table if not exists watchlist_film (
    watchlist_id uuid not null,
    film_id uuid not null,
    created_at timestamp not null default CURRENT_TIMESTAMP,
    updated_at timestamp not null default CURRENT_TIMESTAMP,
    primary key (watchlist_id, film_id),
    foreign key (watchlist_id) references watchlist(id),
    foreign key (film_id) references films(id)
);

create table if not exists collections (
    id uuid primary key,
    user_id uuid not null,
    name varchar(128) not null,
    description text not null,
    created_at timestamp not null default CURRENT_TIMESTAMP,
    updated_at timestamp not null default CURRENT_TIMESTAMP,
    foreign key (user_id) references users(id) on delete cascade
);

create table if not exists essential_collection_media (
    collection_id uuid primary key,
    poster_id uuid,
    backdrop_id uuid,
    foreign key (collection_id) references collections(id) on delete cascade,
    foreign key (poster_id) references medias(id) on delete set null,
    foreign key (backdrop_id) references medias(id) on delete set null
);

create table if not exists collection_film (
    collection_id uuid not null,
    film_id uuid not null,
    created_at timestamp not null default CURRENT_TIMESTAMP,
    updated_at timestamp not null default CURRENT_TIMESTAMP,
    primary key (collection_id, film_id),
    foreign key (collection_id) references collections(id) on delete cascade,
    foreign key (film_id) references films(id) on delete cascade
);

create table if not exists collection_keywords (
    collection_id uuid not null,
    keyword_id uuid not null,
    primary key (collection_id, keyword_id),
    foreign key (collection_id) references collections(id) on delete cascade,
    foreign key (keyword_id) references keywords(id) on delete cascade
);

create table if not exists collection_likes (
    user_id uuid not null,
    collection_id uuid not null,
    created_at timestamp not null default CURRENT_TIMESTAMP,
    updated_at timestamp not null default CURRENT_TIMESTAMP,
    primary key (user_id, collection_id),
    foreign key (user_id) references users(id) on delete cascade,
    foreign key (collection_id) references collections(id) on delete cascade
);

create or replace procedure recalculate_ratings ()
language plpgsql
as $$
begin
    update film_ratings fr
    set
        rating = sub.avg_rating,
        amount_of_ratings = sub.rating_count
    from (
        select
            film_id,
            round(avg(rating)::numeric, 2) as avg_rating,
            count(*)::int as rating_count
        from film_review
        group by film_id
    ) as sub
    where fr.film_id = sub.film_id;
end;
$$;

CREATE OR REPLACE PROCEDURE recalculate_film_popularity()
    LANGUAGE plpgsql
AS $$
BEGIN
    UPDATE film_popularity fp
    SET
        popularity = sub.popularity_score,
        updated_at = CURRENT_TIMESTAMP
    FROM (
             SELECT
                 f.id AS film_id,
                 COALESCE(w.views, 0) * 1.0 +
                 COALESCE(l.likes, 0) * 3.0 +
                 COALESCE(r.reviews, 0) * 4.0
                      AS popularity_score
             FROM films f
                      LEFT JOIN (
                 SELECT film_id, COUNT(*) AS views
                 FROM film_watched
                 GROUP BY film_id
             ) w ON w.film_id = f.id
                      LEFT JOIN (
                 SELECT film_id, COUNT(*) AS likes
                 FROM film_liked
                 GROUP BY film_id
             ) l ON l.film_id = f.id
                      LEFT JOIN (
                 SELECT film_id, COUNT(*) AS reviews
                 FROM film_review
                 GROUP BY film_id
             ) r ON r.film_id = f.id
         ) AS sub
    WHERE fp.film_id = sub.film_id;
END;
$$;

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



