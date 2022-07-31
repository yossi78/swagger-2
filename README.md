
#!/usr/bin/env bash

printf  "\n--------------------------------------------------------------------------------------------------------------\n"
printf  "        START  SCRIPT  "
printf  "\n--------------------------------------------------------------------------------------------------------------\n"

url=http://localhost:8080/v1/humans

printf  "API: Add to:  $url\n"
AddOutput=$(curl -X POST "$url" -H "accept: application/json" -H "Content-Type: application/json" -d "{
\"id\": 1,
\"name\": \"Yossi\",
\"age\": 43
}")
printf  "\nThe url is: %s\n"  "$url"
printf  "\nThe AddOutput is: %s\n\n"  "$AddOutput"



printf  "API: Add to:  $url\n"
AddOutput=$(curl -X POST "$url" -H "accept: application/json" -H "Content-Type: application/json" -d "{
\"id\": 2,
\"name\": \"Daniel\",
\"age\": 30
}")
printf  "\nThe url is: %s\n"  "$url"
printf  "\nThe AddOutput is: %s\n\n"  "$AddOutput"



printf  "API: Get All Humans from:  $url\n"
GetAllOutput=$(curl -X GET "$url" -H "accept: application/json" -H "Content-Type: application/json" )
printf  "\nThe url is: %s\n"  "$url"
printf  "\nThe GetAllOutput is: %s\n\n"  "$GetAllOutput"




printf  "\n--------------------------------------------------------------------------------------------------------------\n"
printf  "          END  SCRIPT  "
printf  "\n--------------------------------------------------------------------------------------------------------------\n"
