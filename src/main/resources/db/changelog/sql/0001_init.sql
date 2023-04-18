CREATE TABLE session
(
    id IDENTITY NOT NULL PRIMARY KEY,
    title     VARCHAR NOT NULL,
    deck_type VARCHAR NOT NULL
);

CREATE TABLE user_story
(
    id IDENTITY NOT NULL PRIMARY KEY,
    description VARCHAR NOT NULL,
    status      VARCHAR NOT NULL,
    session_id  BIGINT  NOT NULL,
    CONSTRAINT user_story_session_fk FOREIGN KEY (session_id) REFERENCES session (id)
);

CREATE TABLE member
(
    id IDENTITY NOT NULL PRIMARY KEY,
    nickname VARCHAR NOT NULL
);

CREATE TABLE session_member
(
    session_id BIGINT NOT NULL,
    member_id  BIGINT NOT NULL,
    CONSTRAINT session_session_member_fk FOREIGN KEY (session_id) REFERENCES session (id),
    CONSTRAINT member_session_member_fk FOREIGN KEY (member_id) REFERENCES member (id),
    CONSTRAINT session_member_key UNIQUE (session_id, member_id)
);
