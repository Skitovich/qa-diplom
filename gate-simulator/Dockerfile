FROM node:latest
WORKDIR /app

COPY package.json /usr/src/app/

RUN npm install

COPY . /usr/src/app

EXPOSE 9999

CMD [ "npm", "start" ]