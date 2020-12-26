#!/usr/bin/env python.
# -*- coding: utf-8 -*-
import riak

def addDocument(bucket, key, data):
    newDocument = bucket.new(key, data)
    newDocument.store()
    if bucket.get(key).exists:
        print("Dodano do bazy dokument: " + key)
    else:
        print("NIE udało się dodać dokumentu '%s' do bazy" % key)
        assert bucket.get(key).exists == True

def fetchDocument(bucket, key):
    return bucket.get(key)

def fetchAndPrintDocument(bucket, key):
    document = fetchDocument(bucket, key)
    if document.exists:
        print("Dokument: %s - %s" % (key, document.encoded_data))
    else:
        print("NIE udało się znaleźć dokumentu: %s (Dokument: %s)" % (key, document.encoded_data))
        assert document.exists == True

def updateDocument(bucket, key, item, newValue):
    document = fetchDocument(bucket, key)
    document.data[item] = newValue
    document.store()
    if document.data[item] == newValue:
        print("Dokument: %s - został zaktualizowany (zmieniona wartość pola '%s' na %s)" % (key, item, str(newValue)))
    else:
        print("Dokument: %s - NIE został zaktualizowany (wartość pola '%s' = %s)" % (key, item, document.data[item]))
        assert document.data[item] == newValue

def deleteDocument(bucket, key):
    document = fetchDocument(bucket, key)
    document.delete()
    if document.data == None:
        print("Dokument: %s - został usuniety" % key)
    else:
        print("Dokument: %s - NIE został usuniety" % key)
        assert document.data == None

if __name__ == "__main__":
    # Python client uses the Protocol Buffers interface ('pbc') by default
    myClient = riak.RiakClient(port = 8098)

    # bucket
    myBucket = myClient.bucket('students')

    # data
    student = {
        'number': 15444,
        'name': "Artur Shkred",
        'studiesAverage': 5.0,
        'requiredInternships': 0
    }

    # Wrzuca do bazy dokument
    addDocument(myBucket, str(student['number']), student)

    # Pobiera i wypisuje dokument
    fetchAndPrintDocument(myBucket, str(student['number']))

    # Modyfikuje dokument
    updateDocument(myBucket, str(student['number']), "requiredInternships", 320)

    # Pobiera i wypisuje dokument
    fetchAndPrintDocument(myBucket, str(student['number']))

    # Usuwa dokument
    deleteDocument(myBucket, str(student['number']))

    # Pobiera i wypisuje dokument
    fetchAndPrintDocument(myBucket, str(student['number']))
