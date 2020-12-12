// -- Jedna kobieta narodowości chińskiej
// printjson(db.people.find({"sex" : "Female", "nationality" : "China"}).limit(1).toArray())
printjson(db.people.findOne({sex : "Female", nationality : "China"}))
