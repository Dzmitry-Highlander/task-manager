CREATE DATABASE comment;

GRANT ALL PRIVILEGES ON DATABASE comment TO comment_manager;

CREATE TABLE comment.task
(
    task_id uuid NOT NULL,
        header character varying(255) COLLATE pg_catalog."default",
        CONSTRAINT task_pkey PRIMARY KEY (task_id)
);

CREATE TABLE comment."user"
(
    user_id uuid NOT NULL,
        full_name character varying(255) COLLATE pg_catalog."default",
        CONSTRAINT user_pkey PRIMARY KEY (user_id)
);

CREATE TABLE comment.comment
(
    comment_id uuid NOT NULL,
        dt_create timestamp(6) without time zone,
        task_id uuid,
        user_id uuid,
        text character varying(255) COLLATE pg_catalog."default",
        CONSTRAINT comment_pkey PRIMARY KEY (comment_id),
        CONSTRAINT comment_task_id_key UNIQUE (task_id),
        CONSTRAINT comment_user_id_key UNIQUE (user_id),
        CONSTRAINT fk8kcum44fvpupyw6f5baccx25c FOREIGN KEY (user_id)
            REFERENCES comment."user" (user_id) MATCH SIMPLE
            ON UPDATE NO ACTION
            ON DELETE NO ACTION,
        CONSTRAINT fkfknte4fhjhet3l1802m1yqa50 FOREIGN KEY (task_id)
            REFERENCES comment.task (task_id) MATCH SIMPLE
            ON UPDATE NO ACTION
            ON DELETE NO ACTION
);