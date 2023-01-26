create table public.url
(
    id                  bigint              not null primary key,
    long_url            varchar(256)            not null,
    short_url           varchar(7)              not null,
    date_created                timestamp           not null,
    date_expired                timestamp           not null
);

create unique index
    if not exists unique_short_url
    on public.url (short_url);