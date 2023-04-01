# Insurance management micro-service
## Description
- APIs to manage insurance service clients, policies and claims

## Tech stack used
- Spring Data JPA, Spring Boot, Lombok, H2 database

## Schema
- Client: id, name, address, dob, contact_number.
- InsurancePolicy: id, type, amount, premium, start date, end date. 
Each policy is associated with a client through client id.
- Claim: id, description, claim date, and claim status. Each claim is associated with
an insurance policy through policy id.

## How To run a project
- Hit the below APIs in Postman or other services

### Clients:
#### [POST] localhost:8080/api/clients: Create a new client.
- Request Body
```
{
    "name": "Pooja",
    "dob": "2000-09-25",
    "address": "Nashik, MH, India",
    "contactNumber": 8998989098
}
```
- Result
```
{
    "code": 200,
    "message": "Request is successful"
}
```
#### [GET] localhost:8080/api/clients/{id}: Fetch a specific client by ID.
- Result
```
{
    "code": 200,
    "result": {
        "id": 1,
        "name": "Pooja",
        "dob": "2000-09-25T00:00:00.000+00:00",
        "address": "Nashik, MH, India",
        "contactNumber": 8998989098
    }
}
```

#### [GET] localhost:8080/api/clients: Fetch all clients.
- Result
```
{
    "code": 200,
    "result": [
        {
            "id": 1,
            "name": "Pooja",
            "dob": "2000-09-25T00:00:00.000+00:00",
            "address": "Nashik, MH, India",
            "contactNumber": 7653421111
        },
        {
            "id": 2,
            "name": "Vrushali",
            "dob": "2000-02-11T00:00:00.000+00:00",
            "address": "Pune, MH, India",
            "contactNumber": 7568790431
        }
    ]
}
```
#### [PUT] localhost:8080/api/clients/{id}: Update a client's information.
- Request body
```
{
    "name": "Pooja",
    "dob": "2000-09-25",
    "address": "Nashik, MH, India",
    "contactNumber": 7653421111
}
```
- Result
```
{
    "code": 200,
    "result": {
        "id": 1,
        "name": "Pooja",
        "dob": "2000-09-25T00:00:00.000+00:00",
        "address": "Nashik, MH, India",
        "contactNumber": 7653421111
    }
}
```
#### [DELETE] localhost:8080/api/clients/{id}: Delete a client.
- Result
```
{
    "code": 200,
    "message": "Resource has been deleted successfully"
}
```
### Insurance Policies:
#### [POST] localhost:8080/api/policies: Create a new insurance policy.
- Request body
```
{
    "type": "life insurance",
    "amount": 100000.0,
    "premium": 20000.00,
    "startDate": "2023-01-01",
    "endDate": "2023-12-31",
    "client": {
        "id":1
    }
}
```
- Result
```
{
    "code": 200,
    "message": "Request is successful"
}
```
#### [GET] localhost:8080/api/policies/{id}: Fetch a specific insurance policy by ID.
- Result
```
{
    "code": 200,
    "result": {
        "id": 1,
        "type": "life insurance",
        "amount": 100000.0,
        "premium": 20000.0,
        "startDate": "2023-01-01T00:00:00.000+00:00",
        "endDate": "2023-12-31T00:00:00.000+00:00",
        "client": {
            "id": 1,
            "name": "Pooja",
            "dob": "2000-09-25T00:00:00.000+00:00",
            "address": "Nashik, MH, India",
            "contactNumber": 7653421111
        }
    }
}
```
#### [GET] localhost:8080/api/policies: Fetch all insurance policies.
-Result
```
{
    "code": 200,
    "result": [
        {
            "id": 1,
            "type": "life insurance",
            "amount": 100000.0,
            "premium": 20000.0,
            "startDate": "2023-01-01T00:00:00.000+00:00",
            "endDate": "2023-12-31T00:00:00.000+00:00",
            "client": {
                "id": 1,
                "name": "Pooja",
                "dob": "2000-09-25T00:00:00.000+00:00",
                "address": "Nashik, MH, India",
                "contactNumber": 7653421111
            }
        },
        {
            "id": 2,
            "type": "health insurance",
            "amount": 100000.0,
            "premium": 20000.0,
            "startDate": "2023-01-01T00:00:00.000+00:00",
            "endDate": "2023-12-31T00:00:00.000+00:00",
            "client": {
                "id": 2,
                "name": "Vrushali",
                "dob": "2000-02-11T00:00:00.000+00:00",
                "address": "Pune, MH, India",
                "contactNumber": 7568790431
            }
        }
    ]
}
```
#### [PUT] localhost:8080/api/policies/{id}: Update an insurance policy.
- Request body
```
{
    "type": "Accidental insurance",
    "amount": 300000.0,
    "premium": 20000.00,
    "startDate": "2023-01-01",
    "endDate": "2023-12-31",
    "client": {
        "id":1
    }
}
```
- Result
```
{
    "code": 200,
    "result": {
        "id": 1,
        "type": "Accidental insurance",
        "amount": 300000.0,
        "premium": 20000.0,
        "startDate": "2023-01-01T00:00:00.000+00:00",
        "endDate": "2023-12-31T00:00:00.000+00:00",
        "client": {
            "id": 1,
            "name": "Pooja",
            "dob": "2000-09-25T00:00:00.000+00:00",
            "address": "Nashik, MH, India",
            "contactNumber": 7653421111
        }
    }
}
```
#### [DELETE] localhost:8080/api/policies/{id}: Delete an insurance policy.
- Result
```
{
    "code": 200,
    "message": "Resource has been deleted successfully"
}
```
### Claims:
#### POST localhost:8080/api/claims: Create a new claim.
- Request body
```
{
    "description": "poor health condition",
    "claimDate": " 2023-03-31",
    "claimStatus": "In progress",
    "insurancePolicy": {
        "id": 1
    }
}
```
- Result
```
{
    "code": 200,
    "message": "Request is successful"
}
```
#### [GET] localhost:8080/api/claims/{id}: Fetch a specific claim by ID.
- Result
```
{
    "code": 200,
    "result": {
        "id": 1,
        "description": "poor health condition",
        "claimDate": "2023-03-31T00:00:00.000+00:00",
        "claimStatus": "In progress",
        "insurancePolicy": {
            "id": 1,
            "type": "Accidental insurance",
            "amount": 300000.0,
            "premium": 20000.0,
            "startDate": "2023-01-01T00:00:00.000+00:00",
            "endDate": "2023-12-31T00:00:00.000+00:00",
            "client": {
                "id": 1,
                "name": "Pooja",
                "dob": "2000-09-25T00:00:00.000+00:00",
                "address": "Nashik, MH, India",
                "contactNumber": 7653421111
            }
        }
    }
}
```
#### [GET] localhost:8080/api/claims: Fetch all claims.
- Result
```
{
    "code": 200,
    "result": [
        {
            "id": 1,
            "description": "poor health condition",
            "claimDate": "2023-03-31T00:00:00.000+00:00",
            "claimStatus": "In progress",
            "insurancePolicy": {
                "id": 1,
                "type": "Accidental insurance",
                "amount": 300000.0,
                "premium": 20000.0,
                "startDate": "2023-01-01T00:00:00.000+00:00",
                "endDate": "2023-12-31T00:00:00.000+00:00",
                "client": {
                    "id": 1,
                    "name": "Pooja",
                    "dob": "2000-09-25T00:00:00.000+00:00",
                    "address": "Nashik, MH, India",
                    "contactNumber": 7653421111
                }
            }
        },
        {
            "id": 2,
            "description": "tenure completed",
            "claimDate": "2023-03-31T00:00:00.000+00:00",
            "claimStatus": "In progress",
            "insurancePolicy": {
                "id": 2,
                "type": "health insurance",
                "amount": 100000.0,
                "premium": 20000.0,
                "startDate": "2023-01-01T00:00:00.000+00:00",
                "endDate": "2023-12-31T00:00:00.000+00:00",
                "client": {
                    "id": 2,
                    "name": "Vrushali",
                    "dob": "2000-02-11T00:00:00.000+00:00",
                    "address": "Pune, MH, India",
                    "contactNumber": 7568790431
                }
            }
        }
    ]
}
```
#### [PUT] localhost:8080/api/claims/{id}: Update a claim's information.
- Request body
```
{
    "description":"poor health condition",
    "claimDate":" 2023-03-31",
    "claimStatus": "Processed",
    "insurancePolicy":{
        "id": 1
    }
}
```
- Result
```
{
    "code": 200,
    "result": {
        "id": 1,
        "description": "poor health condition",
        "claimDate": "2023-03-31T00:00:00.000+00:00",
        "claimStatus": "Processed",
        "insurancePolicy": {
            "id": 1,
            "type": null,
            "amount": null,
            "premium": null,
            "startDate": null,
            "endDate": null,
            "client": null
        }
    }
}
```
#### [DELETE] localhost:8080/api/claims/{id}: Delete a claim.
- Result
```
{
    "code": 200,
    "message": "Resource has been deleted successfully"
}
```




