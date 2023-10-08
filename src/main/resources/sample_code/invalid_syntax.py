
# Invalid expression with a string and number
result = "Hello" + 42  # Invalid expression

# Invalid expression with different types
value = 10
text = "Value is: " + value  # Invalid expression

# Invalid expression with a dictionary
my_dict = {"a": 1, "b": 2}
result = my_dict + {"c": 3}  # Invalid expression

# Invalid expression with a set
my_set = {1, 2, 3}
result = my_set - 2  # Invalid expression

# Invalid expression with a tuple
my_tuple = (1, 2, 3)
result = my_tuple * "Invalid"  # Invalid expression

# Invalid expression with boolean
is_valid = True
result = is_valid / 2  # Invalid expression

# Invalid expression with a group
my_group = {1, 2, 3}
result = my_group | "Invalid"  # Invalid expression


# Missing colon after function declaration
def multiply(a, b)
    result = a * b
    return result

# If-else with missing indentation
x = 10
if x < 5:
print("x is less than 5")
else:
print("x is greater than or equal to 5")

# Incorrect order of statements in if-elif-else
y = 7
elif y == 5:  # 'elif' before 'if'
    print("y is equal to 5")
if y < 5:  # 'if' after 'elif'
    print("y is less than 5")
else:
    for i in range(3):
        print("y is greater than 5 (" + y + "), iteration " + (i + 1) )

# Missing expression in ternary operator
z = 8
result = "Even" if else "Odd"
print("z is " + result)

# Missing colon after while loop
a = 3
while a > 0
    print(f"a is {a}")
    for j in range(a):
        print(f"  j is {j}")
    a -= 1

# Missing indentation in for loop
for num in range(2, 10):
if num % 2 == 0:
    print(f"{num} is an even number")
else:
    print(f"{num} is an odd number")
else:
    print("Loop finished")

# Incorrect indentation in nested structures
if x > 5:
print("x is greater than 5")
if y < 5:
print("y is less than 5 within x > 5 block")

# Invalid indentation for function call
multiply(4, 7)
    print("Result:", result)

# Missing 'pass' to define an empty block
if True:

# Unclosed string literal
print("Unclosed string literal
