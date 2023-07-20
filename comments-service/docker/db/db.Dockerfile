FROM postgres:alpine3.18
COPY ddl/init.sql /docker-entrypoint-initdb.d/