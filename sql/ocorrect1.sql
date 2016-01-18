DROP TABLE CORPORATIONS CASCADE;
DROP TABLE DEVICES CASCADE;
DROP TABLE ADS CASCADE;
DROP TABLE DEALER_CORPORATIONS CASCADE;
DROP TABLE MARKET_CONTENTS CASCADE;
DROP TABLE PURCHASES CASCADE;
DROP TABLE NEWS CASCADE;
DROP TABLE NEWS_CONTENTS CASCADE;
DROP TABLE NEWS_DISPLAY CASCADE;
DROP TABLE TEMP_DEVICES CASCADE;
DROP TABLE AD_DISPLAY CASCADE;
DROP TABLE CATEGORIES CASCADE;
DROP TABLE AD_CATEGORY CASCADE;
DROP TABLE DEVICE_CATEGORY CASCADE;
DROP TABLE NEWS_CATEGORY CASCADE;

CREATE TABLE CORPORATIONS(ID SERIAL NOT NULL,NAME TEXT,PRIMARY KEY(ID));
CREATE TABLE DEVICES(ID SERIAL NOT NULL, PHONE_NUMBER TEXT UNIQUE NOT NULL,PASSWORD TEXT NOT NULL,TOKEN TEXT,CHARGE INT NOT NULL DEFAULT 0,PRIMARY KEY (ID));
CREATE TABLE ADS(ID SERIAL NOT NULL, PRICE TEXT,LINK TEXT, PHONE TEXT, LOCATION TEXT,
CORPORATIONID INT REFERENCES CORPORATIONS(ID),ADDRESS TEXT,COMMENTS TEXT,PUBLISH_TIME TIMESTAMP, PRIMARY KEY (ID));
create index corporation_index on ads (corporationid);
CREATE TABLE AD_DISPLAY(ID SERIAL NOT NULL,SHOWDATE TIMESTAMP NOT NULL,SHOWN_TIME INT,ADID INT REFERENCES ADS(ID) NOT NULL,DEVICEID INT REFERENCES DEVICES(ID) NOT NULL,PRIMARY KEY (ID));
CREATE INDEX AD_INDEX ON AD_DISPLAY (ADID);
CREATE INDEX AD_SHOW_DATE_INDEX ON AD_DISPLAY(SHOWDATE);
CREATE INDEX AD_SHOWN_DEVICE_INDEX ON AD_DISPLAY(DEVICEID);
CREATE TABLE AD_CONTENTS(ID SERIAL NOT NULL, CONTENT BYTEA,ADID INT REFERENCES ADS(ID)NOT NULL,CONTENT_TYPE TEXT,PRIMARY KEY(ID));
CREATE INDEX AD_CONTENT_AD_INDEX ON AD_CONTENTS(ADID);
CREATE TABLE DEALER_CORPORATIONS(ID SERIAL NOT NULL,NAME TEXT,PRIMARY KEY(ID));
CREATE TABLE MARKET_CONTENTS(ID SERIAL NOT NULL, PRICE TEXT,CONTENT BYTEA,
DEALERCORPORATIONID INT REFERENCES DEALER_CORPORATIONS(ID),CONTENT_TYPE TEXT, PRIMARY KEY (ID));
CREATE INDEX DEALER_CORPORATION_INDEX ON MARKET_CONTENTS(DEALERCORPORATIONID);

CREATE TABLE PURCHASES(ID SERIAL NOT NULL,PURCHASE_DATE TIMESTAMP NOT NULL,CONTENTID INT REFERENCES MARKET_CONTENTS(ID) NOT NULL,DEVICEID INT REFERENCES DEVICES(ID) NOT NULL,PRIMARY KEY (ID));
CREATE INDEX MARKET_CONTENT_PURCHASE_INDEX ON PURCHASES(CONTENTID);
CREATE INDEX DEVICE_ID_PURCHASE_INDEX ON PURCHASES(DEVICEID);

CREATE TABLE NEWS (ID SERIAL NOT NULL,NEWSTEXT TEXT,TITLE TEXT,PUBLISH_TIME TIMESTAMP,PRIMARY KEY (ID));
CREATE TABLE NEWS_CONTENTS(ID SERIAL NOT NULL, CONTENT BYTEA,NEWSID INT REFERENCES NEWS(ID)NOT NULL,CONTENT_TYPE TEXT,PRIMARY KEY(ID));
CREATE INDEX NEWS_CONTENT_INDEX ON NEWS_CONTENTS(NEWSID);
CREATE TABLE NEWS_DISPLAY (ID SERIAL NOT NULL, SHOWDATE TIMESTAMP NOT NULL,SHOWN_TIME INT,NEWSID INT REFERENCES NEWS(ID) NOT NULL, DEVICEID INT REFERENCES DEVICES(ID)NOT NULL,PRIMARY KEY(ID));
CREATE INDEX NEWS_INDEX ON NEWS_DISPLAY(NEWSID);
CREATE INDEX NEWS_SHOWN_DEVICE_INDEX ON NEWS_DISPLAY(DEVICEID);
CREATE INDEX NEWS_SHOWN_DATE ON NEWS_DISPLAY(SHOWDATE);

CREATE TABLE TEMP_DEVICES(ID SERIAL NOT NULL, NUMBER TEXT NOT NULL, PASSWORD TEXT, VALID_UNTILL TIMESTAMP NOT NULL, TOKEN TEXT, PRIMARY KEY(ID));

CREATE TABLE CATEGORIES(ID SERIAL NOT NULL, NAME TEXT UNIQUE,PRIMARY KEY(ID));

CREATE TABLE AD_CATEGORY(ADID INT REFERENCES ADS(ID) NOT NULL,CATEGORYID INT REFERENCES CATEGORIES(ID) NOT NULL, PRIMARY KEY(ADID,CATEGORYID));
CREATE TABLE DEVICE_CATEGORY(DEVICEID INT REFERENCES DEVICES(ID) NOT NULL, CATEGORYID INT REFERENCES CATEGORIES(ID)NOT NULL,PRIMARY KEY (DEVICEID,CATEGORYID));
CREATE TABLE NEWS_CATEGORY (NEWSID INT REFERENCES NEWS(ID) NOT NULL, CATEGORYID INT REFERENCES CATEGORIES(ID) NOT NULL, PRIMARY KEY (NEWSID,CATEGORYID));

