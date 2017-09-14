
DROP TABLE HQIRSING.testmessage;

CREATE TABLE HQIRSING.testmessage (
    message_id     VARCHAR(200),
    payload	       CLOB NOT NULL,
    created_date   TIMESTAMP NOT NULL,
    CONSTRAINT HQIRSING.pk_message_id PRIMARY KEY(message_id)
);

