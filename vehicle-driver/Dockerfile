FROM nginx:alpine

COPY nginx-stage.conf /etc/nginx/nginx.conf

WORKDIR /usr/share/nginx/html


COPY ./dist/ .


WORKDIR /usr/app

COPY ./wait-for-it.sh wait-for-it.sh 

RUN chmod +x wait-for-it.sh


CMD ./wait-for-it.sh


EXPOSE 4400
