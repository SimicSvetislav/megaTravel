import express = require('express');
import * as WebSocket from 'ws';
var bodyParser = require('body-parser')

const app: express.Application = express();
// Request parser
app.use(bodyParser.json())

const wss = new WebSocket.Server({ port: 7878 });

// MongoDB
const MongoClient = require('mongodb').MongoClient;
const uri = 'mongodb://localhost:27017/'
const dbName = 'megatravel';
const colName = 'messages';

class Message {
  timestamp: Date = new Date();
  sender: number = -1;
  receiver: number = -1;
  text: string = '[INFO]';
  payload: any = null;
  reservation: number = -1;
}

wss.on('connection', (ws: WebSocket) => {

  ws.on('message', (message: string) => {
    console.log('received: %s', message);

    let msg = JSON.parse(message);

    wss.clients.forEach(client => {
      if (client != ws) {
        client.send(JSON.stringify(msg));
      }
    });
  });

  let greeting: Message = new Message();
  greeting.text += 'WebSocket server is ready';

  ws.send(JSON.stringify(greeting));
});

app.get('/messages/:res', function(req, res) {
  // Plus convert string to number
  let reservation = +req.params.res;

  const client = new MongoClient(uri, { useNewUrlParser: true })
	client.connect((err: any) => {
		
		if (err) {
			client.close()
			res.status(500).send(err)
    }

		const collection = client.db(dbName).collection(colName)

		collection.find({reservation: reservation}, { projection: { _id: 0 } }).toArray(function(err: any, result: any) {
			
			client.close()
			
			if (err) {
        throw err;
      }

      // Sort messages by timestamp
      result.sort(function(a: { timestamp: string | number | Date; },b: { timestamp: string | number | Date; }){
        return new Date(b.timestamp).getTime() - new Date(a.timestamp).getTime();
      });

			res.status(200).send(JSON.stringify(result))
		})
		
	})

});

app.get('/messages', function (req, res) {

  const client = new MongoClient(uri, { useNewUrlParser: true })
  client.connect((err: any) => {

    if (err) {
      client.close()
      res.status(500).send(err)
      return;
    }

    const collection = client.db(dbName).collection(colName);

    collection.find({}, { projection: { _id: 0 } }).toArray(function (err: any, result: any) {

      client.close()

      if (err) {
        throw err;
      }

      res.status(200).send(JSON.stringify(result));
    })

  })

});

app.post('/messages', function (req, res) {
  const client = new MongoClient(uri, { useNewUrlParser: true })
  client.connect((err: any) => {

    if (err) {
      client.close()
      res.status(500).send(err)
    }

    const collection = client.db(dbName).collection(colName)

    console.log(req.body);

    collection.insertOne({ sender: req.body.sender, receiver: req.body.receiver, timestamp: req.body.timestamp, reservation: req.body.reservation, text: req.body.text }, (err: any, result: { ops: { _id: string; }[]; }) => {

      client.close()

      if (err) {
        res.status(500).send('Error')
        return
      }

      res.status(200).send('Message saved as ' + result.ops[0]._id)
    })

  })
});


app.get('/', function (req, res) {
  res.send('Chat app is running');
});

app.listen(7070, function () {
  console.log('Chat app listening on port 7070!');
});