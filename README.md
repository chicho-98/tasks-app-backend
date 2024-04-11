# Task List App

## Run the app locally
After cloning the repo you run the app with
the following command in the terminal:
```
java -jar target/tasksApp-0.0.1-SNAPSHOT.jar
```

### Test the endpoints
Once the app is running you can visualize all the endpoints at:
http://localhost:8080/swagger-ui/index.html

#### **Registration**
POST http://localhost:8080/auth/register
with the body:
```
{
	"email": "john@mail.com",
	"password": "root123456789"
}
```
will create a new user with email `john@mail.com` and
password `root123456789`

#### **Login**
POST http://localhost:8080/auth/login
with the same body as before:
```
{
	"email": "john@mail.com",
	"password": "root123456789"
}
```
will return a `JWT` inside the response body:
```
{
	"token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJ0YXNrcy1hcHAtYXV0aC1hcGkiLCJzdWIiOiJqYWNrQG1haWwuY29tIiwiZXhwIjoxNzEyODY0MDc1fQ.xnaWJvNf7UTjRHZksPkde9HyB25N6FYOIeQv4yPPsKQ"
}
```
Using this token you should hit the upcoming tasks endpoints.

#### **Create new Task**
POST http://localhost:8080/api/tasks
with the body:
```
{
	"name": "New Task",
	"description": "This is a new Task"
}
```
And using the obtained `JWT` inside the Request Header
**Authorization** with the prefix `Bearer` will create
the new task.

#### **Obtain all created Tasks**
GET http://localhost:8080/api/tasks
with empty body and using the `JWT` as described before
will return inside the response body all tasks created by the user:
```
[
	{
		"id": 7,
		"name": "New Task",
		"description": "This is a new Task"
	}
]
```
