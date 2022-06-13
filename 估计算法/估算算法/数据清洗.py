# filename = '4.txt'
# list_level = {[], [], [], [], []}
list_level1 = []
list_level2 = []
list_level3 = []
list_level4 = []
list_level5 = []


def in_4():
    with open('localdata/4.txt', encoding='utf-8') as f:
        for i in f:
            j = i.split('---')  # 缩进符
            if j[3] == '1':
                list_level1.append(i)
            elif j[3] == '2':
                list_level2.append(i)
            elif j[3] == '3':
                list_level3.append(i)
            elif j[3] == '4':
                list_level4.append(i)
            elif j[5] == '5':
                list_level5.append(i)


def in_other(filename):
    with open(filename, encoding='utf-8') as f:
        for i in f:
            j = i.split('---')  # 缩进符
            if j[3] == '1':
                if i not in list_level1:
                    list_level1.append(i)
            elif j[3] == '2':
                if i not in list_level2:
                    list_level2.append(i)
            elif j[3] == '3':
                if i not in list_level3:
                    list_level3.append(i)
            elif j[3] == '4':
                if i not in list_level4:
                    list_level4.append(i)
            elif j[3] == '5':
                if i not in list_level5:
                    list_level5.append(i)


in_4()
in_other('localdata/6.txt')
in_other('localdata/ky.txt')
file_goal = open('localdata/4_6_ky.txt', 'a')
for i in range(0, len(list_level1), 1):
    file_goal.write(list_level1[i])
for i in range(0, len(list_level2), 1):
    file_goal.write(list_level2[i])
for i in range(0, len(list_level3), 1):
    file_goal.write(list_level3[i])
for i in range(0, len(list_level4), 1):
    file_goal.write(list_level4[i])
for i in range(0, len(list_level5), 1):
    file_goal.write(list_level5[i])
file_goal.close()

'''下面是测试'''
# in_4()
# print('---4---')
# print(len(list_level1))
# print(len(list_level2))
# print(len(list_level3))
# print(len(list_level4))
# print(len(list_level5))
# print(len(list_level1)+len(list_level2)+len(list_level3)+len(list_level4)+len(list_level5))
# in_other('6.txt')
# print('---4+6---')
# print(len(list_level1))
# print(len(list_level2))
# print(len(list_level3))
# print(len(list_level4))
# print(len(list_level5))
# print(len(list_level1)+len(list_level2)+len(list_level3)+len(list_level4)+len(list_level5))
# in_other('ky.txt')
# print('---4+6+ky---')
# print(len(list_level1))
# print(len(list_level2))
# print(len(list_level3))
# print(len(list_level4))
# print(len(list_level5))
# print(len(list_level1)+len(list_level2)+len(list_level3)+len(list_level4)+len(list_level5))
