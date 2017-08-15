### Access free service

http://localhost:8080/amock/user/

[ {
  "id" : 1,
  "name" : "Sam",
  "age" : 30,
  "salary" : 70000.0
}, {
  "id" : 2,
  "name" : "Tom",
  "age" : 40,
  "salary" : 50000.0
}, {
  "id" : 3,
  "name" : "Jerome",
  "age" : 45,
  "salary" : 30000.0
}, {
  "id" : 4,
  "name" : "Silvia",
  "age" : 50,
  "salary" : 40000.0
} ]

### Generate token key access

* Method : POST

localhost:8080/amock/oauth/token?grant_type=password&username=admin&password=admin

### User and Pass generate token

* Authorization Type : Basic Auth

* Username : myRestClient
* Password : P@ssw0rd

### Result tokenizer example

{
    "access_token": "1e32fb28-9e6b-4e10-95e5-c7c5b055b5a3",
    "token_type": "bearer",
    "refresh_token": "975a3077-06f3-4781-9ad3-91a1ddd25f69",
    "expires_in": 119,
    "scope": "read write trust"
}

### Test uriendpoint json results

* Method : GET

http://localhost:8080/amock/fruits/?access_token=1e32fb28-9e6b-4e10-95e5-c7c5b055b5a3


### Json Result success

[
    {
        "id": 1,
        "name": "Apple",
        "produceBy": "USA",
        "note": "Delicious flavour"
    },
    {
        "id": 2,
        "name": "Orange",
        "produceBy": "Indonesia",
        "note": "Delicious flavour"
    },
    {
        "id": 3,
        "name": "Banana",
        "produceBy": "Cuba",
        "note": "Good quality"
    }
]


### Json Result failed

{
    "error": "invalid_token",
    "error_description": "Access token expired: 1e32fb28-9e6b-4e10-95e5-c7c5b055b5a3"
}


### Refresh Token

Method: POST

* Authorization Type : Basic Auth

* Username : myRestClient
* Password : P@ssw0rd

http://localhost:8080/amock/oauth/token?grant_type=refresh_token&refresh_token=975a3077-06f3-4781-9ad3-91a1ddd25f69

{
    "access_token": "3430129a-eee8-418e-851b-60e67add6c0d",
    "token_type": "bearer",
    "refresh_token": "975a3077-06f3-4781-9ad3-91a1ddd25f69",
    "expires_in": 119,
    "scope": "read write trust"
}
	