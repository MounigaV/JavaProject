# JavaProject
IBank Application


	1.	Run ibank application. mvn spring-boot:run
	2.	This application uses maven as build and h2 as database - hibernate for DB connectivity
	3.	Create an account
	⁃	curl --header "Content-Type: application/json" \
	⁃	  --request POST \
	⁃	  --data  '{ "accountName ": " Mouniga ", "phone ": " 21234 ","email ": "xyz123 @gmail.com", "status":"ACTIVE"}'\
	⁃	 http://localhost:8080/account
	4.	Update an account
	⁃	curl --header "Content-Type: application/json" \
	⁃	  --request PUT \
	⁃	  --data  '{ "accountName ": " Mouniga ", "phone ": " 21234 ","email ": "xyz123 @gmail.com", "status":"ACTIVE"}'\
	⁃	 http://localhost:8080/account
	5.	Delete an account - curl -X "DELETE" http://localhost:8080/account/1
	6.	Create a beneficiary- pre req - Account ids should be valid
	⁃	curl --header "Content-Type: application/json" \
	⁃	  --request POST \
	⁃	  --data  '{ "accountId": " 4 ", "beneAccountId": " 5 ","beneIfsccode": "xyz123 ", "beneName":"Mm","status":"ACTIVE"}} ' \
	⁃	 http://localhost:8080/beneficiary
	7.	Update a beneficiary
	⁃	curl --header "Content-Type: application/json" \
	⁃	  --request PUT \
	⁃	  --data  '{ "accountId": " 4 ", "beneAccountId": " 5 ","beneIfsccode": "xyz123 ", "beneName":"Mm","status":"ACTIVE"}} ' \
	⁃	 http://localhost:8080/beneficiary
	8.	View beneficiary list by account id - curl http://localhost:8080/beneficiary/2
	9.	Delete a beneficiary by account id - curl -X "DELETE" http://localhost:8080/beneficiary/1
	10.	Deposit a transaction 
	⁃	
	⁃	curl --header "Content-Type: application/json" \
	⁃	  --request POST \
	⁃	  --data  '{ "accountId": " 2 ","amount": "100 ", "remarks":"Mm","status":"SUCCESS"}} ' \
	⁃	 http://localhost:8080/deposit_transaction
	11.	Withdraw a transaction - A valid account balance should be present
	⁃	curl --header "Content-Type: application/json" \
	⁃	  --request POST \
	⁃	  --data  '{ "accountId": " 2 ","amount": " 100 ", "remarks":"Mm","status":"SUCCESS"}} ' \
	⁃	 http://localhost:8080/withdrawl_transaction
	12.	View transaction by account id
	⁃	
	⁃	curl http://localhost:8080/transactions/2
