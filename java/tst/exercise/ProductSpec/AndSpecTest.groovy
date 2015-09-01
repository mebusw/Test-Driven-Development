package exercise.ProductSpec

/**
 * Created by jacky on 15/9/1.
 */
class AndSpecTest extends groovy.util.GroovyTestCase {
    void setUp() {
        super.setUp()

    }

    void testIsSatisfiedBy() {
        3.times {
            println "server-${it}"

        }

        def reverse = [
                equals: false,
                compare: { Object[] args -> args[1].compareTo(args[0]) }
        ] as Comparator
        def list = [5,3,9]
        Collections.sort(list, reverse)
        println list
        def d = [a:1, b:2]
        println d.a

        def spec1 = new ColorSpec("red")
        def spec2 = new PriceSpec(100)
        def spec = new AndSpec(spec1, spec2)
        assertNotNull spec
        assertEquals "red", spec1.color
        //assertThat 100, equalTo(spec2.price)

        def finder = new ProductFinder(products: null)
        assertNull finder.products
    }
}
