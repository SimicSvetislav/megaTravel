require('@google/cloud-debug')

const MongoClient = require('mongodb').MongoClient
const uri = 'mongodb+srv://admin:admin@megatravel-kszv1.gcp.mongodb.net/test?retryWrites=true&w=majority'
const dbName = 'megatravel'
const colName = 'ratings'

exports.postRating = function postRating(req, res) {
	
	const client = new MongoClient(uri, { useNewUrlParser: true })
	client.connect(err => {

		if (err) {
			client.close()
			res.status(500).send(err)
			return;
		}

		const collection = client.db(dbName).collection(colName)
			
		if (!req.body.grade || !req.body.room || !req.body.user || !req.body.object) {
			client.close()
			res.status(400).send('Grade, room, object and user must be specified in request body!')
			return;
		}
		
		if (!req.body.comment) {
			req.body.comment = ''
		}

		
		collection.insertOne({grade: req.body.grade, object: req.body.object, room: req.body.room, comment: req.body.comment, user: req.body.user, approved: (req.body.approved!==null)?req.body.approved:false}, (err, result) => {
			
			client.close()
		
			if (err) {
				res.status(500).send('Error')
				return
			}
		
			res.status(200).send('Rate saved as ' + result.ops[0]._id)
		})
	  
	})
	  
}

exports.putRating = function putRating(req, res) {
  
  res.status(200).send('OK')
  
}

exports.deleteRating = function deleteRating(req, res) {
  
  res.status(200).send('OK')
  
}

exports.getRatingsByRoom = function getRatingsByRoom(req, res) {
  
	// Plus konvertuje string u broj
	let room = +req.query.room;
  
	if (!room) {
		room = req.body.room;
		if (!room) {
			res.status(400).send('Room must be specified in request body or as query parameter!')
			return;
		}
	}
	
	const client = new MongoClient(uri, { useNewUrlParser: true })
	client.connect(err => {
		
		if (err) {
			client.close()
			res.status(500).send(err)
		}			
		const collection = client.db(dbName).collection(colName)

		collection.find({room: room}, { projection: { _id: 0 } }).toArray(function(err, result) {
			
			client.close()
			
			if (err) { 
				throw err
			}

			//res.status(200).send('Fetched ratings\nLength: ' + result.length + '\n' + JSON.stringify(result))
			res.status(200).send(JSON.stringify(result))
		})
		
	})
  
}

exports.getRatingsByObject = function getRatingsByObject(req, res) {
  
	// Plus konvertuje string u broj
	let object = +req.query.object;
	
	if (!object) {
		object = req.body.object;
		if (!object) {
			res.status(400).send('Object must be specified in request body or as query parameter!')
			return;
		}
	}

	const client = new MongoClient(uri, { useNewUrlParser: true })
	client.connect(err => {
		
		if (err) {
			client.close()
			res.status(500).send(err)
		}			
		const collection = client.db(dbName).collection(colName)

		collection.find({object: object}, { projection: { _id: 0 } }).toArray(function(err, result) {
			
			client.close()
			
			if (err) throw err

			//res.status(200).send('Fetched ratings\nLength: ' + result.length + '\n' + JSON.stringify(result))
			res.status(200).send(JSON.stringify(result))
		})
		
	})
  
}

exports.getRatingsByUser = function getRatingsByUser(req, res) {
  
	let user = +req.query.user;
  
	if (!user) {
		user = req.body.user;
		if (!user) {
			res.status(400).send('User must be specified in request body or as query parameter!')
			return;
		}
	}

	const client = new MongoClient(uri, { useNewUrlParser: true })
	client.connect(err => {
		
		if (err) {
			client.close()
			res.status(500).send(err);
			return;
		}			
		const collection = client.db(dbName).collection(colName)

		collection.find({user: user}, { projection: { _id: 0 } }).toArray(function(err, result) {
			
			client.close()
			
			if (err) throw err

			//res.status(200).send('Fetched ratings\nLength: ' + result.length + '\n' + JSON.stringify(result))
			res.status(200).send(JSON.stringify(result))
		})
		
	})
  
}

exports.getRatings = function getRatings(req, res) {
  
  	const client = new MongoClient(uri, { useNewUrlParser: true })
	client.connect(err => {
		
		if (err) {
			client.close()
			res.status(500).send(err)
			return;
		}			
		const collection = client.db(dbName).collection(colName)

		collection.find({}, { projection: { _id: 0 } }).toArray(function(err, result) {
			
			client.close()
			
			if (err) throw err

			//res.status(200).send('Fetched ratings\nLength: ' + result.length + '\n' + JSON.stringify(result))
			res.status(200).send(JSON.stringify(result))
		})
		
	})
  
}

exports.getComment = function getComment(req, res) {
  
  res.status(200).send('OK')
  
}

exports.getApprovedCommentsForRoom = function getApprovedCommentsForRoom(req, res) {
  
	let room = +req.query.room;
  
	if (!room) {
		room = req.body.room
		if (!room) {
			res.status(400).send('Room must be specified in request body or as query parameter!');
			return;
		}
	}

	const client = new MongoClient(uri, { useNewUrlParser: true })
	client.connect(err => {
		
		if (err) {
			client.close()
			res.status(500).send(err)
		}			
		const collection = client.db(dbName).collection(colName)

		collection.find({room: room, approved: true}, { projection: { comment: 1, _id: 0} }).toArray(function(err, result) {
			
			client.close()
			
			if (err) throw err

			//res.status(200).send('Fetched ratings\nLength: ' + result.length + '\n' + JSON.stringify(result))
			res.status(200).send(JSON.stringify(result))
		})
		
	})
  
}

exports.getCommentsByGrade = function getCommentsByGrade(req, res) {
  
	let grade = +req.query.grade;
  
	if (!req.body.grade) {
		grade = req.body.grade
		if (!grade) {
			res.status(400).send('Grade must be specified in request body or as query parameter!');
			return;
		}
	}
	
	const client = new MongoClient(uri, { useNewUrlParser: true })
	client.connect(err => {
		
		if (err) {
			client.close()
			res.status(500).send(err)
		}

		const collection = client.db(dbName).collection(colName)

		collection.find({grade: grade}).toArray(function(err, result) {
			
			client.close()
			
			if (err) throw err
			
			if (result.length==0) {
				res.status(200).send(result);
				return;
			}

			let comments = result
				.map((item) => {
					return item.comment
				})

			res.status(200).send(JSON.stringify(comments))
		})

	})
  
}

exports.averageGrade = function averageGrade(req, res) {
  
	let room = +req.query.room;
  
	if (!room) {
		room = req.body.room;
		if (!room) {
			res.status(400).send('Room must be specified in request body!');
			return;
		}
	}

	const client = new MongoClient(uri, { useNewUrlParser: true })
	client.connect(err => {
		
		if (err) {
			client.close()
			res.status(500).send(err)
		}

		const collection = client.db(dbName).collection(colName)

		collection.find({room: room}, { projection: { _id: 0 } }).toArray(function(err, result) {
			
			client.close()
			
			if (err) throw err
			
			if (result.length==0) {
				res.status(200).send(result);
				return;
			}

			// Traditional method
			/*let total = 0
			result.forEach(function(item) {
				total += item.grade
			})
			let avg = total / result.length*/
			
			// Functional programming style
			let avg = result
				.map((item) => {
					return item.grade
				})
				.reduce( (x, y) => {
					return x + y
				}) / result.length

			//res.status(200).send('Average grade for room ' + room + ' is ' + JSON.stringify(total/result.length))
			res.status(200).send(JSON.stringify(avg))

		})

	}) 
}

exports.averageGradeObject = function averageGradeObject(req, res) {
  
	let object = +req.query.object;
  
	if (!object) {
		object = req.body.object;
		if (!object) {
			res.status(400).send('Object ID must be specified in request body or as query parameter!');
			return;
		}
	}

	const client = new MongoClient(uri, { useNewUrlParser: true })
	client.connect(err => {
		
		if (err) {
			client.close()
			res.status(500).send(err)
		}

		const collection = client.db(dbName).collection(colName)

		collection.find({object: object}, { projection: { _id: 0 } }).toArray(function(err, result) {
			
			client.close()
			
			if (err) throw err
			
			if (result.length==0) {
				res.status(200).send(result);
				return;
			}

			let avg = result
				.map((item) => {
					return item.grade
				})
				.reduce( (x, y) => {
					return x + y
				}) / result.length

			//res.status(200).send('Average grade for object ' + object + ' is ' + JSON.stringify(total/result.length))
			res.status(200).send(JSON.stringify(avg))

		})

	}) 
}