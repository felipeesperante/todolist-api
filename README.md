# todolist-api
Project developed by fnunes to build a REST API with purpose of organizing and managing a list of tasks to be done daily.

# User Story
- Tasks can be scheduled to be done on another date
- Tasks can be edited or deleted if they are not completed
- It must to be possible to report the spend time on each task
- Tasks can have a list of comments (Optional)

# Deployment

As a springboot application, you can clone the project and type:

```mvn clean install```

To build de project. After building you can run:

```mvn spring-boot:run```

#Documentation
Description: Retrieve a valid JWT token based on user created.
- endpoint: /authenticate
- method
	POST
- params
	login/passwd
- return
	jwt token

Description: Retrieve all user data.
- endpoint: /user
- method
	GET
- params
- return
	UserRET

Description: Retrieve user data by user ID.
- endpoint: /user/{id}
- method
	GET
- params
- return
	UserRET

Description: Save a user data.
- endpoint: /user
- method
	POST
- params
	UserSENT
- return
	DefaultRET

Description: Update a user data.
- endpoint: /user
- method
	PUT
- params
	UserSENT
- return
	DefaultRET

Description: Retrieve a task by its ID.
- endpoint: /task/{id}
- method
	GET
- params
- return
	TaskRET

Description: Retrieve all taks by user ID.
- endpoint: /task/user/{id}
- method
	GET
- params
- return
	TaskRET[]
	
Description: Save a new task.
- endpoint: /task
- method
	POST
- params
	TaskSENT
- return
	DefaultRET

Description: Update a task.
- endpoint: /task
- method
	PUT
- params
	TaskSENT
- return
	DefaultRET

Description: Delete a new task.
- endpoint: /task
- method
	DELETE
- params
	TaskSENT
- return
	DefaultRET

Description: Save a user's comment at a task.
- endpoint: /comment
- method
	POST
- params
	CommentSENT
- return
	DefaultRET

Description: Edit a user's comment at a task.
- endpoint: /comment
- method
	PUT
- params
	CommentSENT
- return
	DefaultRET
	
