#! encoding=utf8

def f(n):
	for i in range(n):
		yield i

print list(f(5))


def h():
    print 'Wen Chuan',
    m1 = yield 5  # Fighting!
    print '==', m1
    d1 = yield 12 # yielded...But not received
    print 'Red Cross', d1

c = h()
m = c.next()  #m 获取了yield 5 的参数值 5
# c.close()
d = c.send('Fighting!')  #d 获取了yield 12 的参数值12
# c.send('over')
print 'We will never forget the date', m, '.', d