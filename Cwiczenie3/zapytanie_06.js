// -- Dodaj siebie do bazy, zgodnie z formatem danych użytych dla innych osób
// (dane dotyczące karty kredytowej, adresu zamieszkania i wagi mogą być fikcyjne
printjson(db.people.insertOne(
  {
		"sex" : "Male",
		"first_name" : "Artur",
		"last_name" : "Shkred",
		"job" : "Full Stack Developer",
		"email" : "s15444@pjwstk.edu.pl",
		"location" : {
			"city" : "Warsaw",
			"address" : {
				"streetname" : "Ulica",
				"streetnumber" : "19"
			}
		},
		"description" : "Programista",
		"height" : "187.4",
		"weight" : "90.5",
		"birth_date" : "1999-05-12T07:39:24Z",
		"nationality" : "Poland",
		"credit" : [
			{
				"type" : "visa",
				"number" : "1234876501236789",
				"currency" : "PLN",
				"balance" : "1005.53"
			}
		]
	}
))
