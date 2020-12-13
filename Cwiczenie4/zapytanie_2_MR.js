// -- Łączną ilość środków pozostałych na kartach kredytowych osób w bazie, w podziale na waluty
printjson(db.people.mapReduce(
  function() {
    this.credit.forEach(credit => {
      emit(credit.currency, parseFloat(credit.balance))
    });
  },
  function(key, values) {
    // return Array.sum(values)
    totalBalance = 0;
	  for (var idx = 0; idx < values.length; idx++) {
		  totalBalance += values[idx];
	  }
    return totalBalance;
  },
	{
		out: "total_balance"
	}
).find().toArray());
