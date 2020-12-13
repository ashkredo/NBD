// -- Średnie, minimalne i maksymalne BMI (waga/wzrost^2) dla osób w bazie, w podziale na narodowości
printjson(db.people.mapReduce(
  function() {
    var bmi = parseFloat(this.weight) / Math.pow(parseFloat(this.height) / 100, 2); // BMI (waga/wzrost^2)
    emit(this.nationality, {bmi, minBMI : bmi, maxBMI : bmi, count : 1});
  },
  function(key, values) {
    reducedVal = { count : 0, bmi : 0, minBMI : values[0].minBMI, maxBMI : values[0].maxBMI };
      for (var idx = 0; idx < values.length; idx++) {
        reducedVal.count += values[idx].count;
        reducedVal.bmi += values[idx].bmi;
        reducedVal.minBMI = Math.min(reducedVal.minBMI, values[idx].minBMI);
        reducedVal.maxBMI = Math.max(reducedVal.maxBMI, values[idx].maxBMI);
      }
    return reducedVal;
  },
  {
    out: "bmi",
    finalize: function (key, reducedValue) {
      return {
        avgBMI : reducedValue.bmi / reducedValue.count,
        minBMI : reducedValue.minBMI,
        maxBMI : reducedValue.maxBMI
      };
    }
  }
).find().toArray());
