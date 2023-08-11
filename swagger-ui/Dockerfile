FROM swaggerapi/swagger-ui:latest
MAINTAINER ITAcademy

ADD /audit-service/spec/audit-service.yml /usr/share/nginx/html/doc/audit-service.yaml
ADD /task-service/spec/task-service.yml /usr/share/nginx/html/doc/task-service.yml
ADD /user-service/spec/user-service.yml /usr/share/nginx/html/doc/user-service.yaml

ENV URLS="[{ url: 'doc/audit-service.yaml', name: 'Audit'},{ url: 'doc/task-service.yml', name: 'Task'}, { url: 'doc/user-service.yaml', name: 'User'}]"
ENV URLS_PRIMARY_NAME="User"