FROM node:10

WORKDIR /usr/src/app

COPY package*.json ./

RUN npm install

COPY . .

EXPOSE 7070
EXPOSE 7878

CMD [ "npm", "run", "dev" ]