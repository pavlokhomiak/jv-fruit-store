# fruit-storage

HW 19 - JUnit practice
Your task is to implement the following requirements and cover at least 90% of your code with JUnit tests. All input data will be passed in the file in CSV format.

Fruit Store
Let’s imagine you have a fruit store. There are three type of operations in your fruit store:

    s - supply, means you are receiving a new fruits from suppliers
    b - buy, means someone bought a fruit
    r - return, means someone who have bought the fruits now returns you it
Let’s check in details all types of operations:

Supply. You are accepting a new fruit from suppliers. The following line in the file will look like:

    s,banana,100,2020-10-17     
The line above means you receive a 100 bananas, and their expiration date is 17-th October 2020.

Buy. Buyers can visit your shop and buy some fruits. In this case you will have the following line in the file:

    b,banana,13,2020-10-15     
The line above means someone bought a 13 bananas. Date of purchase is 15-th October 2020.

Return. Buyers can return you some fruits. In this case you will have the following line in the file:

    r,banana,10,2020-10-17     
The line above means someone return you a 10 bananas, and their expiration date is 17-th October 2020.

Input file example
    type,fruit,quantity,date
    s,banana,100,2020-10-17
    b,banana,13,2020-10-15
    r,banana,10,2020-10-17 
Expecting output file example
We are expecting to see how many fruits are available in your Fruit store after the file processing. For example:

    fruit,quantity
    banana,97
    orange,147
    apple,29
The line above means you have 97 bananas, 147 oranges and 29 apples in your Fruit store.

Expiration date
expiration date property allows you to control whether user can buy some fruits or not For example, if you have 10 bananas with expiration date = 2020-10-17 and 15 bananas with expiration date = 2020-10-19 and user wants buy 20 bananas at 2020-10-18 he will not be allowed to buy them.

How to use expiration date in the output file? Don’t care about expiration date in this case. Just print all fruits you have in your storage.

ADVANCED:
Keep in mind Open-Closed Principle (from SOLID) when you will do this task    