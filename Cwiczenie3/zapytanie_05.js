// -- Lista imion i nazwisk wszystkich osób znajdujących się w bazie oraz miast,
// w których mieszkają, ale tylko dla osób urodzonych w XXI wieku
printjson(db.people.find({birth_date : {$gte : "2001-01-01T00:00:00Z", $lte : "2100-12-31T23:59:59Z"}}, {first_name : 1, last_name : 1, "location.city" : 1}).toArray())
