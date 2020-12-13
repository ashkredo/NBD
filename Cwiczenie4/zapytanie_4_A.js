// -- Średnie, minimalne i maksymalne BMI (waga/wzrost^2) dla osób w bazie, w podziale na narodowości
printjson(db.people.aggregate([
  {$addFields: {
      bmi : {$divide : [{$toDouble:"$weight"}, {$pow : [{$divide : [{$toDouble:"$height"}, 100]}, 2]}]}
    }
  },
  {$group : {
      _id : "$nationality",
      avgBMI : {$avg : "$bmi"},
      minBMI : {$min : "$bmi"},
      maxBMI : {$max : "$bmi"}
    }
  }
]).toArray());
