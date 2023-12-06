DROP TABLE IF EXISTS rss_feed;

CREATE TABLE rssfeed(
                        id SERIAL PRIMARY KEY,
                        link                VARCHAR(2000) NOT NULL,
                        title               VARCHAR(2000) NOT NULL,
                        description         VARCHAR(4000) NOT NULL,
                        publication_date    DATE DEFAULT NULL,
                        updated_date        DATE DEFAULT NULL
);