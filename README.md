# Who Wants To Be A Millionaire

[![License](https://img.shields.io/badge/License-MIT-blue.svg)](https://gitee.com/dromara/RuoYi-Vue-Plus/blob/master/LICENSE) ![使用IntelliJ IDEA开发维护](https://img.shields.io/badge/IntelliJ%20IDEA-提供支持-blue.svg)


[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2-blue.svg)]() [![JDK-11](https://img.shields.io/badge/JDK-11-green.svg)]() [![JDK-17](https://img.shields.io/badge/JDK-17-green.svg)]() [![JDK-21](https://img.shields.io/badge/JDK-21-green.svg)]()

"Who Wants To Be A Millionaire?" is a popular television game show. This project imitates the TV show, with the intention of spreading knowledge by setting up the quiz game where players answer questions to win virtual money, using our project's Java Swing GUI and Apache Derby database for an interactive, educational experience.

- [Backend Repository](https://github.com/inwardflow/mill)
- [Frontend GUI Repository](https://github.com/JK666-BUG/mill-front)

> [!Note]
>
> To avoid installation errors, you can download our [JAR package](https://github.com/JK666-bug/mill-front/releases/tag/v1.0.0-RC1) directly.

```shell![屏幕截图 2024-11-24 203543](https://github.com/user-attachments/assets/549ef4d6-8e61-439c-96f5-6d1b74c4dc40)

java -jar .\Millionaire-1.0-RC1.jar
```

# *PDC Project Details*



## User Interface(GUI)

<u>**Clear and well-designed graphical user interface following common standards:**</u>



Login page

![image-20241124205438146](C:\Users\86177\AppData\Roaming\Typora\typora-user-images\image-20241124205438146.png)

Register page

![image-20241124205219648](C:\Users\86177\AppData\Roaming\Typora\typora-user-images\image-20241124205219648.png)

We designed a beautiful game cover and a clear and concise GUI interface to meet the standards of simplicity, consistency, accessibility and so on.

#### 

<u>**The interface is easy for users to interact with:**</u>

game page

![image-20241124212600308](C:\Users\86177\AppData\Roaming\Typora\typora-user-images\image-20241124212600308.png)

We have clearly divided the interface into several sections (score, function, dialog, interactive button, score and dollar for progress). Each round of the game, the red score and the yellow progress are changed. In addition, we set the enable logic for button to ensure that the user chooses the button according to the correct game logic.



## Database

<u>**The program contains a database element**:</u>

![Database Manager](C:\Users\86177\Desktop\PDC截图\Database Manager.png)

We created a DerbyManager class to manage our database.



<u>**Can achieve database interactions(input and output) and operations in the program**</u>:

![Operations on the Question Table](C:\Users\86177\Desktop\PDC截图\Operations on the Question Table.png)

![Operations on the User Table](C:\Users\86177\Desktop\PDC截图\Operations on the User Table.png)

They are operations on the question table and operations on the user table. We realized the basic operation of database such as inserting and deleting user information and inserting questions



## Software Functionality and Usability

<u>The program is easy to compile and run without any manual configurations</u>

Since we use a cloud-based server, we use jar packages on the back end to facilitate compilation.



<u>The users interact with the program without any errors:</u>

We use a try-catch method to log error, and we also use a prompt box to interact with the user when an error message occurs, such as when the user does not enter a password

![image-20241124220824607](C:\Users\86177\AppData\Roaming\Typora\typora-user-images\image-20241124220824607.png)



<u>The complexity of the functionalit:</u>

Our games interact directly with the AI, and in each round of the game, the user can choose the corresponding answer or the corresponding help function. Our AI can use the context to return the data we need to keep the game going. Our game features meet the requirements of the game, meet the complexity of the function



## Software Design& Implementation

<u>The program can be compiled successfully</u>

![the program can run perfectly](C:\Users\86177\Desktop\PDC截图\the program can run perfectly.png)

<u>The purpose of the code is easy to understand by reading it</u>

We are satisfied with: 

clear naming: variables, functions, classes, etc., have descriptive and easy-to-understand names.
Well-structured: Code is organized into logical blocks that are easy to follow, usually following some kind of programming paradigm or design pattern.
Follow best practices: The code follows widely accepted programming best practices, such as avoiding magic numbers, using meaningful constants, and keeping functions short and focused on a single purpose.
Consistency: The code is consistent in style, naming, and structure, which makes it easier to read and understand.

<u>The comments in the code are useful and appropriate</u>



<u>The code executes without runtime errors</u>



<u>The Git/GitHub version control is applied</u>

![image-20241124221756843](C:\Users\86177\AppData\Roaming\Typora\typora-user-images\image-20241124221756843.png)

![image-20241124221810000](C:\Users\86177\AppData\Roaming\Typora\typora-user-images\image-20241124221810000.png)

We use git tools on github and use a team approach to develop projects



<u>Design patterns are implemented correctly where appropriate</u>

We used the MVC design pattern:
Model: Responsible for encapsulating the data and processing the data. It does not depend on the View and Controller, that is, it does not care how the data will be displayed or manipulated. Our model is capable of processing user interaction data and information

View: Responsible for data display. It generally does not have procedural logic, but rather retrieves data from the Model to display to the user. Our GUI section shows the view section

Controller: Acts as an organization between the different layers to control the flow of the application. It processes and responds to events, including user behavior and changes on the Model. We defined a number of interactions before and after button event users.

<u>A good coding style is used, following appropriate coding standards</u>:

![image-20241124221634756](C:\Users\86177\AppData\Roaming\Typora\typora-user-images\image-20241124221634756.png)

Our code conforms to design specifications and is easy to read.

<u>The code design follows OO design good practice</u>

Our code uses object-oriented principles such as encapsulation inheritance polymorphic abstraction.

## Unit Testing

**<u>At least FIVE test cases are included</u>**

![Question Table Operation Test](C:\Users\86177\Desktop\PDC截图\Question Table Operation Test.png)



We passed a variety of tests.



**<u>The tests are well written</u>**

![image-20241124220008955](C:\Users\86177\AppData\Roaming\Typora\typora-user-images\image-20241124220008955.png)

Each test section conforms to the test writing specification.



**<u>The tests cover important functionality well</u>**

![User Table Operation Test](C:\Users\86177\Desktop\PDC截图\User Table Operation Test.png)

We passed the user registration, add, delete test, which is the most important basic function of the user login and registration.

**<u>The tests are well-named</u>**

![image-20241124215920105](C:\Users\86177\AppData\Roaming\Typora\typora-user-images\image-20241124215920105.png)

From the image above, we can see that tests have clear, descriptive, and easy-to-understand names. For example, we have "Add User Test" which directly expresses the specific aspects of the test.



## Contribution

Hang Peng (StudentID 24259970): Write JUnit tests,Apache Derby Database



Xiangqian Ye (StudentID 24262556):Integrate with Apache Derby Database,Collect datas (Question Bank) about `Who Wants To Be A Millionaire`,Connect chat bot with `Question Bank`



Kai Jang(StudentID 24261889) : Integrate with back-end,GUI frontend development,Test



Qingyi Zhou (StudentID 24259712) : Collate and analyze needs,GUI frontend development,draw the wireframe preliminary


