// -- Usuń z bazy osoby o wzroście przekraczającym 190
printjson(db.people.remove({height : {$gt : "190"}}))
