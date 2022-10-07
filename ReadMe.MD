
# ######################################################################################
#          STARTING POINT
# ######################################################################################

1) Open the api-docs JSON as follow:
http://127.0.0.1:8080/v2/api-docs

2) Open the Swagger UI as follow:
http://127.0.0.1:8080/swagger-ui.html



# ######################################################################################
#          API CALLS
# ######################################################################################

#)  Add Human 1:
curl --location --request POST 'http://localhost:8080/v1/humans' \
--data-raw '{
    "id": 1,
    "name": "Yossi",
    "age": 43
}'


#)  Add Human 2:
curl --location --request POST 'http://localhost:8080/v1/humans' \
--data-raw '{
    "id": 2,
    "name": "Daniel",
    "age": 44
}'


#)  Edit Human 1:
curl --location --request PUT 'http://localhost:8080/v1/humans/1' \
--data-raw '{
    "id": 1,
    "name": "Sami",
    "age": 20
}'

#)  Get All Humans:
curl --location --request GET 'http://localhost:8080/v1/humans' \
--data-raw ''


#)  Remove Human:
curl --location --request DELETE 'http://localhost:8080/v1/humans/1' \
--data-raw ''






