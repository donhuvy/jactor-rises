CREATE TABLE T_USER (
    ID            BIGINT GENERATED BY DEFAULT AS IDENTITY,
    USER_NAME     VARCHAR(50),
    PASSWORD      VARCHAR(250),
    EMAIL         VARCHAR(50),
    PROFILE_ID    BIGINT,
    EMAIL_AS_NAME BOOLEAN
);

CREATE TABLE T_PROFILE (
    ID          BIGINT GENERATED BY DEFAULT AS IDENTITY,
    DESCRIPTION VARCHAR(250),
    USER_ID     BIGINT
);

ALTER TABLE T_USER
    ADD CONSTRAINT upid UNIQUE (ID);

ALTER TABLE T_USER
    ADD CONSTRAINT uname UNIQUE (USER_NAME);

ALTER TABLE T_PROFILE
    ADD CONSTRAINT uprid UNIQUE (ID);

ALTER TABLE T_USER
    ADD CONSTRAINT fk_prof FOREIGN KEY (PROFILE_ID)
    REFERENCES T_PROFILE (ID);
