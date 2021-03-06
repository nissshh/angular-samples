	-- DROP DATABASE IF EXISTS baas;

-- CREATE DATABASE IF NOT EXISTS baas;

USE baas;

-- Payment API Client Subscriber

DROP TABLE IF EXISTS NOTIFICATIONS;

CREATE TABLE IF NOT EXISTS  NOTIFICATIONS (
    TYPE VARCHAR(200)  not null,
    STATUS VARCHAR(200) not null,
    COUNT BIGINT(5) default 1,
    PRIMARY KEY (TYPE,STATUS)
);


INSERT INTO NOTIFICATIONS(TYPE,STATUS) VALUES('Payment','Received');
INSERT INTO NOTIFICATIONS(TYPE,STATUS) VALUES('Payment','Processing');
INSERT INTO NOTIFICATIONS(TYPE,STATUS) VALUES('Payment','Scheduled');

INSERT INTO NOTIFICATIONS(TYPE,STATUS) VALUES('Location','Received');
INSERT INTO NOTIFICATIONS(TYPE,STATUS) VALUES('Location','Processing');
INSERT INTO NOTIFICATIONS(TYPE,STATUS) VALUES('Location','Scheduled');


INSERT INTO NOTIFICATIONS(TYPE,STATUS) VALUES('Foreign Exchange','Received');
INSERT INTO NOTIFICATIONS(TYPE,STATUS) VALUES('Foreign Exchange','Processing');
INSERT INTO NOTIFICATIONS(TYPE,STATUS) VALUES('Foreign Exchange','Scheduled');


UPDATE NOTIFICATIONS SET COUNT =  COUNT  + 1 WHERE TYPE=  'Foreign Exchange';
UPDATE NOTIFICATIONS SET COUNT =  COUNT  + 1 WHERE TYPE=  'Foreign Exchange';
UPDATE NOTIFICATIONS SET COUNT =  COUNT  + 1 WHERE TYPE=  'Foreign Exchange';

SELECT 
    TYPE, STATUS, COUNT
FROM
    NOTIFICATIONS;
