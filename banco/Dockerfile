FROM postgres

ENV POSTGRES_DB contatos
ENV POSTGRES_USER postgres
ENV POSTGRES_PASSWORD 123456

COPY create.sql /docker-entrypoint-initdb.d/
