// -- Średnia i łączna ilość środków na kartach kredytowych kobiet narodowości polskiej w podziale na waluty
printjson(db.people.mapReduce(
  function() {
    this.credit.forEach(credit => {
      emit(credit.currency, parseFloat(credit.balance))
    });
  },
  function(key, values) {
    // totalBalance = 0;
    // for (var idx = 0; idx < values.length; idx++) {
    //   totalBalance += values[idx];
    // }
    return {
      "avgBalance" : Array.sum(values) / values.length,
      "totalBalance" : Array.sum(values)
    }
  },
  {
    out: "avg_and_total_female_balance",
    query: {
      sex : "Female",
      nationality : "Poland"
    }
  }
).find().toArray());
