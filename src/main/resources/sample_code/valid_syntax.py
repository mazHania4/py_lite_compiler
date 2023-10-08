
# If-else structure
x = 10
if x < 5:
    print("x is less than 5")
else:
    print("x is greater than or equal to 5")

# If-elif-else structure with nested for loop
y = 7
if y < 5:
    print("y is less than 5")
elif y == 5:
    print("y is equal to 5")
else:
    for i in range(3):
        print("y is greater than 5 (" + y + "), iteration " + (i + 1))

# Ternary operator
z = 8
result = "Even" if z % 2 == 0 else "Odd"
print("z is " + result)

# While loop with a for loop inside
a = 3
while a > 0:
    print("a is " + a)
    for j in range(a):
        print(" j is " + j)
    a -= 1

# function with if condition with multiple comparisons
# Function declaration
def multiply(a, b):
    b = 15
    c = 10
    d = 5
    if (b > c and c > d) or (b == 15 and (c + d) != 13):
        print("condition is true")
    result = a * b
    return result

# For-else structure
for num in range(2, 10):
    if num % 2 == 0:
        print(num + " is an even number")
    else:
        print(num + " is an odd number")
else:
    print("Loop finished")

# Nested structures
if x > 5:
    print("x is greater than 5")
    if y < 5:
        print("y is less than 5 within x > 5 block")

print("End of code")
