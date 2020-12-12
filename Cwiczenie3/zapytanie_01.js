// -- Jedna osoba znajdująca się w bazie
// printjson(db.people.find().limit(1).toArray())
printjson(db.people.findOne())
