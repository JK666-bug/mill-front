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



## User Interface(GUI) (10 mark)

**Clear and well-designed graphical user interface following common standards:**



Login page

![image](https://github.com/user-attachments/assets/870c4ff6-5122-45cb-b5b1-80643bf632bc)


Register page

![image](https://github.com/user-attachments/assets/24ff7c87-1859-46d9-ab40-bfd8e7a573c6)


We designed a beautiful game cover and a clear and concise GUI interface to meet the standards of simplicity, consistency, accessibility and so on.

#### 

**The interface is easy for users to interact with:**

game page

![image](https://github.com/user-attachments/assets/c07ace2c-ac98-4f2b-96d5-54aae12c1e9e)


We have clearly divided the interface into several sections (score, function, dialog, interactive button, score and dollar for progress). Each round of the game, the red score and the yellow progress are changed. In addition, we set the enable logic for button to ensure that the user chooses the button according to the correct game logic.



## Database (20 mark)

<u>**The program contains a database element**:</u>

![image](https://github.com/user-attachments/assets/8780ac71-5b8b-42d4-8b4a-482a4a42e160)


We created a DerbyManager class to manage our database.



<u>**Can achieve database interactions(input and output) and operations in the program**</u>:

![image](https://github.com/user-attachments/assets/e6db1c34-88a0-44d2-ba4f-a63ba82c7438)


![image](https://github.com/user-attachments/assets/3a89a6db-0c01-49b9-ae33-2812a9615887)


They are operations on the question table and operations on the user table. We realized the basic operation of database such as inserting and deleting user information and inserting questions



## Software Functionality and Usability (30 mark)

<u>The program is easy to compile and run without any manual configurations</u>

Since we use a cloud-based server, we use jar packages on the back end to facilitate compilation.



**The users interact with the program without any errors:**

We use a try-catch method to log error, and we also use a prompt box to interact with the user when an error message occurs, such as when the user does not enter a password

![image](https://github.com/user-attachments/assets/3dd5fc9f-b7eb-487f-9b3c-39863c21bc1b)



**The complexity of the functionalit:**

Our games interact directly with the AI, and in each round of the game, the user can choose the corresponding answer or the corresponding help function. Our AI can use the context to return the data we need to keep the game going. Our game features meet the requirements of the game, meet the complexity of the function



## Software Design& Implementation (30 mark)

**The program can be compiled successfully**

![image](https://github.com/user-attachments/assets/ec6971fb-65df-48c3-9dd5-71727a85a3ee)


**The purpose of the code is easy to understand by reading it**

We are satisfied with: 

clear naming: variables, functions, classes, etc., have descriptive and easy-to-understand names.
Well-structured: Code is organized into logical blocks that are easy to follow, usually following some kind of programming paradigm or design pattern.
Follow best practices: The code follows widely accepted programming best practices, such as avoiding magic numbers, using meaningful constants, and keeping functions short and focused on a single purpose.
Consistency: The code is consistent in style, naming, and structure, which makes it easier to read and understand.

**The comments in the code are useful and appropriate**



**The code executes without runtime errors**



**The Git/GitHub version control is applied**

![image](https://github.com/user-attachments/assets/a6ba1e14-18a5-4d55-a6a6-62da172e85c8)


![image](https://github.com/user-attachments/assets/439ae6e2-9c67-498c-af28-bea8836507a9)


We use git tools on github and use a team approach to develop projects



**Design patterns are implemented correctly where appropriate**

We used the MVC design pattern:
Model: Responsible for encapsulating the data and processing the data. It does not depend on the View and Controller, that is, it does not care how the data will be displayed or manipulated. Our model is capable of processing user interaction data and information

View: Responsible for data display. It generally does not have procedural logic, but rather retrieves data from the Model to display to the user. Our GUI section shows the view section

Controller: Acts as an organization between the different layers to control the flow of the application. It processes and responds to events, including user behavior and changes on the Model. We defined a number of interactions before and after button event users.

**A good coding style is used, following appropriate coding standards**

![image](https://github.com/user-attachments/assets/130a9173-b3f6-49e8-9005-59b49f9c45fb)



Our code conforms to design specifications and is easy to read.

<u>The code design follows OO design good practice</u>

Our code uses object-oriented principles such as encapsulation inheritance polymorphic abstraction.

## Unit Testing (10 mark)

**<u>At least FIVE test cases are included</u>**

![image](https://github.com/user-attachments/assets/d8442794-5e28-4781-b8a5-63532a443fe7)




We passed a variety of tests.



**<u>The tests are well written</u>**




Each test section conforms to the test writing specification.



**<u>The tests cover important functionality well</u>**

![image](https://github.com/user-attachments/assets/a7512e92-7d45-4143-974b-0d97c6704936)


We passed the user registration, add, delete test, which is the most important basic function of the user login and registration.

**The tests are well-named**

![image](https://github.com/user-attachments/assets/b29477ad-c061-4de4-afbb-8db310ecacd4)

From the image above, we can see that tests have clear, descriptive, and easy-to-understand names. For example, we have "Add User Test" which directly expresses the specific aspects of the test.



## Contribution

Hang Peng (StudentID 24259970): Write JUnit tests, Apache Derby Database



Xiangqian Ye (StudentID 24262556):Integrate with Apache Derby Database, Collect datas (Question Bank) about `Who Wants To Be A Millionaire, Connect chat bot with `Question Bank`



Kai Jang(StudentID 24261889) : Integrate with back-end, GUI frontend development,Test



Qingyi Zhou (StudentID 24259712) : Collate and analyze needs,GUI frontend development,draw the wireframe preliminary


😊 The code in this repository is inspired by the following open-source projects. Thanks to the work of these open-source projects.
* [JFormDesigner / FlatLaf](https://github.com/JFormDesigner/FlatLaf)
* [Dromara / RuoYi-Vue-Plus](https://github.com/dromara/RuoYi-Vue-Plus)
* [Spring-Projects / Spring-AI](https://github.com/spring-projects/spring-ai)
* [DJ-Raven / Swing-Modal-Dialog](https://github.com/DJ-Raven/swing-modal-dialog)


