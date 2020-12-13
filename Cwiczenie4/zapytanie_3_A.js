// -- Listę unikalnych zawodów
//printjson(db.people.aggregate([{$group : {_id : 1,  jobs : {$addToSet : '$job'}}}]).toArray());
printjson(db.people.aggregate([{$group : {_id : "$job",  count : {$sum : 1}}}]).toArray());
