package hello;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({EmptyTest.class, GreetingTest.class, LottoTest.class, PreemptiveBasicAuthTest.class, PriceTest.class, ProductTest.class, StoreTest.class, TitleTest.class})
public class AllTests {
}
