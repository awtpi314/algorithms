# (value, index, distance)
values = [1, 4, 7, 2, 5, 8, 3, 6, 9]
numbers = [(value, 0, 0) for value in values]

for i in range(len(numbers)):
    current = numbers[i]
    for j in range(i, len(numbers)):
        if numbers[j][0] > current[0] and numbers[j][2] < current[2] + 1:
            numbers[j] = (numbers[j][0], i, current[2] + 1)

current = sorted(numbers, key=lambda x: x[2])[-1]
print(current[0])
while current[1] != 0:
    print(values[current[1]])
    current = numbers[current[1]]
print(values[current[1]])