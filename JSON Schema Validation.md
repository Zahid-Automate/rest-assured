While testing an API, is possible that one of the data type of the field received in response is changed to integer from String or vice-versa. Or there is a new field/Object added in the response.
Thanks to JSON Schema Validation, these changes can now be caught easily and can save lot of your efforts and time in debugging and finding the issue which leads to the failure of your system.
Before we begin with discussing the JSON Schema validation, let’s first understand what JSON is, and then continue with the JSON Schema Validation.

### What is JSON?
JSON stands for JavaScript Object Notation. It was originally specified by Douglas Crockford. It is a lightweight format for storing and transporting data and often used when data is sent from server to webpages. It is self describing and easy to understand.
The following are important syntax rules for JSON:
- Data is in name/value pairs.
- Data is separated by commas.
- Curly braces hold objects.
- Square brackets hold arrays.
To understand further, let's take the following JSON file as an example:

```json
  {
     "page": 1,
     "total_pages": 2,
     "employee_data": [{
       "id": 5,
       "first_name": "Michael",
       "last_name": "Doe",
       "designation": "QA",
       "location": "Remote"
      },
      {
       "id": 6,
       "first_name": "Johnny",
       "last_name": "Ford",
       "designation": "QA",
       "location": "NY,US"
      }
     ],
     "company": {
      "name": "QA Inc",
      "Address": "New Jersey, US"
     }
    }
```
Understanding the JSON File:
The above mentioned file begins with a curly brace {which means the file holds JSON object. Inside the JSON object, data is stored in multiple data types as follows:
1.	Root level itself is JSON Object as it has curly bracket to start with and has data is stored in key/value pair :
```json
{
 "page": 1,
 "total_pages": 2
}
```
2. JSON Array
JSON Array stores data inside the JSON file in a block with square bracket []. If we take the example of the JSON file mentioned above,employee_data JSON array has 2 JSON Objects inside it.
"employee_data":
```json 
[{
   "id": 5,
   "first_name": "Michael",
   "last_name": "Doe",
   "designation": "QA",
   "location": "Remote"
  },
  {
   "id": 6,
   "first_name": "Johnny",
   "last_name": "Ford",
   "designation": "QA",
   "location": "NY,US"
  }
 ]
 ```
 
3. JSON Object.
As mentioned earlier, data stored within curly braces are JSON Objects and has multiple key/value pairs in it.
company JSON Object holds the data for company details:
```json
"company": {
  "name": "QA Inc",
  "Address": "New Jersey, US"
 }
 ```
 
It can also be referred as company key holding the company details record in its value.
###What is JSON Schema?
JSON Schema is a specification for JSON based format for defining the structure of JSON data.
JSON Schema helps us in describing the existing data format and providing clear, human and machine readable documentation.
As JSON Schema provides complete structural validation, it helps in automated tests and also validating the client-submitted data for verification.
How do I generate JSON Schema for the JSON Request of an API?
Consider the following example of Post Response from restful-booker website where the following data is returned in response once user hits the post API for creating a new booking:
```json
{
    "bookingid": 1,
    "booking": {
        "firstname": "Jim",
        "lastname": "Brown",
        "totalprice": 111,
        "depositpaid": true,
        "bookingdates": {
            "checkin": "2018-01-01",
            "checkout": "2019-01-01"
        },
        "additionalneeds": "Breakfast"
    }
}
```
To generate the JSON Schema, we would be using an online JSON schema generator tool from extendsclass.com. Using this tool is very simple, you just need to copy paste the JSON data for which you need to generate the JSON schema and click on the Generate Schema from JSON button on the web page and it will provide you with the JSON schema for the respective JSON data provided.
 
 ![image](https://user-images.githubusercontent.com/45691238/217439547-2796572d-b0ae-4e58-a521-7c1026c42b01.png)


Here is the JSON Schema generated for the above JSON data for creating a new booking:
```json
{
 "definitions": {},
 "$schema": "http://json-schema.org/draft-07/schema#", 
 "$id": "https://example.com/object1661496173.json", 
 "title": "Root", 
 "type": "object",
 "required": [
  "bookingid",
  "booking"
 ],
 "properties": {
  "bookingid": {
   "$id": "#root/bookingid", 
   "title": "Bookingid", 
   "type": "integer",
   "examples": [
    1
   ],
   "default": 0
  },
  "booking": {
   "$id": "#root/booking", 
   "title": "Booking", 
   "type": "object",
   "required": [
    "firstname",
    "lastname",
    "totalprice",
    "depositpaid",
    "bookingdates",
    "additionalneeds"
   ],
   "properties": {
    "firstname": {
     "$id": "#root/booking/firstname", 
     "title": "Firstname", 
     "type": "string",
     "default": "",
     "examples": [
      "Jim"
     ],
     "pattern": "^.*$"
    },
    "lastname": {
     "$id": "#root/booking/lastname", 
     "title": "Lastname", 
     "type": "string",
     "default": "",
     "examples": [
      "Brown"
     ],
     "pattern": "^.*$"
    },
    "totalprice": {
     "$id": "#root/booking/totalprice", 
     "title": "Totalprice", 
     "type": "integer",
     "examples": [
      111
     ],
     "default": 0
    },
    "depositpaid": {
     "$id": "#root/booking/depositpaid", 
     "title": "Depositpaid", 
     "type": "boolean",
     "examples": [
      true
     ],
     "default": true
    },
    "bookingdates": {
     "$id": "#root/booking/bookingdates", 
     "title": "Bookingdates", 
     "type": "object",
     "required": [
      "checkin",
      "checkout"
     ],
     "properties": {
      "checkin": {
       "$id": "#root/booking/bookingdates/checkin", 
       "title": "Checkin", 
       "type": "string",
       "default": "",
       "examples": [
        "2018-01-01"
       ],
       "pattern": "^.*$"
      },
      "checkout": {
       "$id": "#root/booking/bookingdates/checkout", 
       "title": "Checkout", 
       "type": "string",
       "default": "",
       "examples": [
        "2019-01-01"
       ],
       "pattern": "^.*$"
      }
     }
    }
,
    "additionalneeds": {
     "$id": "#root/booking/additionalneeds", 
     "title": "Additionalneeds", 
     "type": "string",
     "default": "",
     "examples": [
      "Breakfast"
     ],
     "pattern": "^.*$"
    }
   }
  }
}
}
```

Understanding the JSON Schema
If you check the JSON data, following two fields are main records:
-	bookingid
- Object of bookingdata
The following block generated in JSON Schema talks about these 2 fields that in root, these two fields are required as an Object type.
```json
"title": "Root", 
"type": "object",
 "required": [
  "bookingid",
  "booking"
 ]
 ```
 
Next, let's talk about the properties block inside the JSON Schema. The following block states that bookingid should be in root object and its type should be integer . Hence, in response it is expected that value in this field should be an integer only. So, in case if this type is changed to any other data type like String ,Object ,longor float, schema validation will fail and we would be able to identify the issue in the schema right away.
```json
"properties": {
  "bookingid": {
   "$id": "#root/bookingid", 
   "title": "Bookingid", 
   "type": "integer",
   "examples": [
    1
   ],
   "default": 0
  },
  "booking": {
   "$id": "#root/booking", 
   "title": "Booking", 
   "type": "object",
   "required": [
    "firstname",
    "lastname",
    "totalprice",
    "depositpaid",
    "bookingdates",
    "additionalneeds"
   ],
   "properties": {
    "firstname": {
     "$id": "#root/booking/firstname", 
     "title": "Firstname", 
     "type": "string",
     "default": "",
     "examples": [
      "Jim"
     ],
     "pattern": "^.*$"
    },
    "lastname": {
     "$id": "#root/booking/lastname", 
     "title": "Lastname", 
     "type": "string",
     "default": "",
     "examples": [
      "Brown"
     ],
     "pattern": "^.*$"
    },
    "totalprice": {
     "$id": "#root/booking/totalprice", 
     "title": "Totalprice", 
     "type": "integer",
     "examples": [
      111
     ],
     "default": 0
    },
    "depositpaid": {
     "$id": "#root/booking/depositpaid", 
     "title": "Depositpaid", 
     "type": "boolean",
     "examples": [
      true
     ],
     "default": true
    },
    "bookingdates": {
     "$id": "#root/booking/bookingdates", 
     "title": "Bookingdates", 
     "type": "object",
     "required": [
      "checkin",
      "checkout"
     ],
     "properties": {
      "checkin": {
       "$id": "#root/booking/bookingdates/checkin", 
       "title": "Checkin", 
       "type": "string",
       "default": "",
       "examples": [
        "2018-01-01"
       ],
       "pattern": "^.*$"
      },
      "checkout": {
       "$id": "#root/booking/bookingdates/checkout", 
       "title": "Checkout", 
       "type": "string",
       "default": "",
       "examples": [
        "2019-01-01"
       ],
       "pattern": "^.*$"
      }
     }
    }
,
    "additionalneeds": {
     "$id": "#root/booking/additionalneeds", 
     "title": "Additionalneeds", 
     "type": "string",
     "default": "",
     "examples": [
      "Breakfast"
     ],
     "pattern": "^.*$"
    }
   }
  }
}
}
```

Likewise, you can notice the data types and required field values mentioned for the other fields in the JSON Schema.
Performing the JSON Schema validation using Rest-Assured
### What is rest-assured?
REST-Assured is a Java library that provides a domain-specific language (DSL) for writing powerful, maintainable tests for RESTful APIs. One thing I really like about rest-assured is its BDD style of writing tests and one can read the tests very easily in a human readable language.
#### Getting Started
The project is created using Maven. Once the project is created we need to add the dependency for rest-assured in pom.xml file. TestNG is used as test runner.
Following dependencies are mandatorily required to be added in pom.xml rest-assured dependency is required for running the API tests and json-schema-validator dependency is required for validating the JSON Schema.

dependencies — pom.xml
 ![image](https://user-images.githubusercontent.com/45691238/217461945-e2deff62-0954-416d-a62f-d3b8941e98f9.png)


The versions of these dependencies can be added in the properties block inside the pom.xml as it is best practice and helps in maintaining the versions with ease as all versions are available at one place.

### Validating the JSON Schema
Step 1: First of all, we need to generate the JSON Schema for the response JSON data which we need to validate.
As we are using the restful-booker create booking API, we would be copy pasting the JSON response and generate the JSON schema using the JSON Schema Validator. In the screenshot below, on the left hand side we have the JSON response. On clicking the Generate Schema from JSON button , JSON Schema would be generated on the right hand section.
Response received from Create booking API in restful-booker
```json
{
    "bookingid": 1,
    "booking": {
        "firstname": "Jim",
        "lastname": "Brown",
        "totalprice": 111,
        "depositpaid": true,
        "bookingdates": {
            "checkin": "2018-01-01",
            "checkout": "2019-01-01"
        },
        "additionalneeds": "Breakfast"
    }
}
```
#### JSON Schema for the above response:
```json
{
 "definitions": {},
 "$schema": "http://json-schema.org/draft-07/schema#", 
 "$id": "https://example.com/object1661586892.json", 
 "title": "Root", 
 "type": "object",
 "required": [
  "bookingid",
  "booking"
 ],
 "properties": {
  "bookingid": {
   "$id": "#root/bookingid", 
   "title": "Bookingid", 
   "type": "integer",
   "examples": [
    1
   ],
   "default": 0
  },
  "booking": {
   "$id": "#root/booking", 
   "title": "Booking", 
   "type": "object",
   "required": [
    "firstname",
    "lastname",
    "totalprice",
    "depositpaid",
    "bookingdates",
    "additionalneeds"
   ],
   "properties": {
    "firstname": {
     "$id": "#root/booking/firstname", 
     "title": "Firstname", 
     "type": "string",
     "default": "",
     "examples": [
      "Jim"
     ],
     "pattern": "^.*$"
    },
    "lastname": {
     "$id": "#root/booking/lastname", 
     "title": "Lastname", 
     "type": "string",
     "default": "",
     "examples": [
      "Brown"
     ],
     "pattern": "^.*$"
    },
    "totalprice": {
     "$id": "#root/booking/totalprice", 
     "title": "Totalprice", 
     "type": "integer",
     "examples": [
      111
     ],
     "default": 0
    },
    "depositpaid": {
     "$id": "#root/booking/depositpaid", 
     "title": "Depositpaid", 
     "type": "boolean",
     "examples": [
      true
     ],
     "default": true
    },
    "bookingdates": {
     "$id": "#root/booking/bookingdates", 
     "title": "Bookingdates", 
     "type": "object",
     "required": [
      "checkin",
      "checkout"
     ],
     "properties": {
      "checkin": {
       "$id": "#root/booking/bookingdates/checkin", 
       "title": "Checkin", 
       "type": "string",
       "default": "",
       "examples": [
        "2018-01-01"
       ],
       "pattern": "^.*$"
      },
      "checkout": {
       "$id": "#root/booking/bookingdates/checkout", 
       "title": "Checkout", 
       "type": "string",
       "default": "",
       "examples": [
        "2019-01-01"
       ],
       "pattern": "^.*$"
      }
     }
    }
,
    "additionalneeds": {
     "$id": "#root/booking/additionalneeds", 
     "title": "Additionalneeds", 
     "type": "string",
     "default": "",
     "examples": [
      "Breakfast"
     ],
     "pattern": "^.*$"
    }
   }
  }}
}
```
Writing JSON Schema validation test 
![image](https://user-images.githubusercontent.com/45691238/217463577-90eebb1f-db56-48b0-843b-0ba1ea8c97c2.png)

We need to write the assertion for validating the JSON Schema inside the body() method after the assertThat() method. But before we move to the assertion, we need to read the JSON file we posted inside the src\test\resources folder. For doing that we would be using the InputStream class. The following line of code will help us in reading the JSON Schema file createbookingjsonschema.json

```java
InputStream createBookingJsonSchema = getClass ().getClassLoader ()
    .getResourceAsStream ("createbookingjsonschema.json");
```

Next, we need to hit the post API and check the JSON Schema in response by using JsonSchemaValidator.matchesJsonSchema() method and pass the createBookingJsonSchema InputStream instance in it.

The following lines of code will help us in validating the JSON Schema in the response. Interpreting the lines of code given below, we are sending a post request with the body as required for Post API after which we are checking the status code returned in response is 200 and that the body has the JSON Schema as provided in the createBookingJsonSchema instance.

```java
given ().body (newBooking)
    .when ()
    .post ("/booking")
    .then ()
    .statusCode (200)
    .and()
    .assertThat ()
    .body (JsonSchemaValidator.matchesJsonSchema (createBookingJsonSchema));
```

Tests passed successfully as the JSON Schema received in the Response matches with the JSON Schema provided in the src\test\resources folder.

Now, let’s make some changes in the JSON Schema in the createbookingjsonschema.json file provided in the src\test\resources

In bookngid field, value of type integer is required however it has been updated to string just to check if the validation is actually working fine.

```json
"properties": {
  "bookingid": {
    "$id": "#root/bookingid",
    "title": "Bookingid",
    "type": "string",
    "examples": [
      1
    ],
    "default": 0
  },
 ```
 Run the test again by right clicking on the testng.xml file.
 
Following error log will be generated and displayed in the console, which says that value received in response was an integer whereas value expected was string.

```java
error: instance type (integer) does not match any allowed primitive type (allowed: ["string"])
    level: "error"
    schema: {"loadingURI":"#","pointer":"/properties/bookingid"}
    instance: {"pointer":"/bookingid"}
    domain: "validation"
    keyword: "type"
    found: "integer"
    expected: ["string"]
```
