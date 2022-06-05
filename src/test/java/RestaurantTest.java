import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class RestaurantTest {
	Restaurant restaurant;

	@BeforeEach
	public void init() {
		LocalTime openingTime = LocalTime.parse("09:00:00");
		LocalTime closingTime = LocalTime.parse("21:00:00");
		restaurant = new Restaurant("Ashok", "benaglore", openingTime, closingTime);
		restaurant.addToMenu("idly", 30);
		restaurant.addToMenu("dosa", 40);
	}

	@Test
	public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time() {
		Restaurant spyRestaurant = Mockito.spy(restaurant);
		Mockito.lenient().when(spyRestaurant.getCurrentTime()).thenReturn(LocalTime.parse("15:00:00"));
		boolean result = spyRestaurant.isRestaurantOpen();
		assertTrue(result);
	}

	@Test
	public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time() {
		Restaurant spyRestaurant = Mockito.spy(restaurant);
		Mockito.lenient().when(spyRestaurant.getCurrentTime()).thenReturn(LocalTime.parse("08:00:00"));
		boolean result = spyRestaurant.isRestaurantOpen();
		assertFalse(result);
	}

	// <<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	@Test
	public void adding_item_to_menu_should_increase_menu_size_by_1() {
		int initialMenuSize = restaurant.getMenu().size();
		restaurant.addToMenu("chapathi", 50);
		assertEquals(initialMenuSize + 1, restaurant.getMenu().size());
	}

	@Test
	public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
		int initialMenuSize = restaurant.getMenu().size();
		restaurant.removeFromMenu("dosa");
		assertEquals(initialMenuSize - 1, restaurant.getMenu().size());
	}

	@Test
	public void removing_item_that_does_not_exist_should_throw_exception() {
		assertThrows(itemNotFoundException.class, () -> restaurant.removeFromMenu("poori"));
	}

	// <<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}