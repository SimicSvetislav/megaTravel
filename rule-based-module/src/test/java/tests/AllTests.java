package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AgentNotificationTest.class, AgentsTest.class, CancellationDiscount.class, 
	EarlyBirdEventTest.class, KlijentiTest.class, KPravilaPopustZaLojalnost.class, 
	KPravilaViseOsobaTest.class, KPravilaZabranaPorukaTest.class, LastMinuteEventTest.class,
	OtkazivanjeTest.class, PopustTest.class, PreporukaTest.class, SearchEventTest.class, SmestajTest.class })
public class AllTests {
	
}
