package exercise.ProductSpec

/**
 * Created by jacky on 15/9/1.
 */
class AndSpecTest extends groovy.util.GroovyTestCase {
    void setUp() {
        super.setUp()

    }

    void testIsSatisfiedBy() {
        def spec1 = new ColorSpec("red")
        def spec2 = new PriceSpec(100)
        def spec = new AndSpec(spec1, spec2)
        assertNotNull spec
        assertEquals "red", spec1.color
        //assertThat 100, equalTo(spec2.price)

        def finder = new ProductFinder(products: null)
        assertNull finder.products
    }

    void testGroovySyntax() {
        def h="hello"
        3.times { i ->
            print "time-${it}-($h)  "
        }

        def l = [5,6,3,7,1,4,9]
        println l
        println l.sort { 
            a,b -> -a.compareTo(b)
        }
        for(ll in l) {
            print ll + ' '
        }

        def reverse = [
                equals: false,
                compare: { Object[] args -> args[1].compareTo(args[0]) }
        ] as Comparator
        def list = [5,3,9]
        Collections.sort(list, reverse)

        def d = [a:1,b:2]
        println d
        println d.a

        def func(a, b=2, c=null) {
            if (c == null) {
                'b is null'
            } else {
                a+b+c
            }
        }
        println func(1, 2, 3)
        println func(1)

        class Animal {
            String name
            BigDecimal price
            String farmer
            String toString() { name }
        }

        def animals = []
        animals << new Animal(name: "Buttercup", price: 2, farmer: "john")
        animals << new Animal(name: "Carmella", price: 5, farmer: "dick")
        animals << new Animal(name: "Cinnamon", price: 2, farmer: "dick")
        println animals
        assert 9 == animals.sum { it.price }
        println animals.collect { it.name.toUpperCase() }
        println animals.groupBy { it.farmer }
        println animals.groupBy{ it.farmer }.collectEntries { k, v -> [k, v.price.sum()] }

        import java.util.HashMap        
    }
}
