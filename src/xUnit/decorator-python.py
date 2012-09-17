def anna(fn):
    def new_func(*args):
        print 'by anna args=%s' % args
        return fn(*args)
    return new_func

def annie(ar):
    print 'by annie1 ar=%s' % ar
    def _A(fn):
        def new_func(*args):
            print 'by annie2 args=%s' % args
            return fn(*args)
        return new_func
    return _A
    
class ccc():
    @anna
    def __init__(self):
        print 'ccc'
    @anna
    def ff(self, a):
        print a



@anna
def test1(a):
    print a

@annie('hi')
def test2(a):
    print a

test1((1,2))
test2((3,4))
