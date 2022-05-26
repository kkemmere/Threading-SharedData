# Threading-SharedData
Java program displaying 4 different threads incrementing the same counter<br/>

Operating Systems program using threading created in Java.<br/>
Created by Kevin Kemmerer<br/>


# Program being run using Eclipse IDE
- Creates 4 client threads and puts them behind a barrier such that they start relatively at the same time
- Inital thread that created 4 client threads monitors the shared counter
- Each thread does two computations (Increases its own counter + the global counter) program ends when own counter reaches 10,000 or global counter reaches 40,000. <br/><br/>
![threads](https://github.com/kkemmere/Threading-SharedData/blob/main/Screen%20Shot%202022-05-26%20at%205.57.12%20PM.png)
<br/>
