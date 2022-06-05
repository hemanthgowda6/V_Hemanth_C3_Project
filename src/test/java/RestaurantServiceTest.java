import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RestaurantServiceTest {

	RestaurantService service = new RestaurantService();
	Restaurant restaurant;

	// REFACTOR ALL THE REPEATED LINES OF CODE

	@BeforeEach
	public void init() {
		LocalTime openingTime = LocalTime.parse("09:00:00");
		LocalTime closingTime = LocalTime.parse("21:00:00");
		restaurant = service.addRestaurant("Ashok", "Banglore", openingTime, closingTime);
		restaurant.addToMenu("idly", 30);
		restaurant.addToMenu("dosa", 40);
	}
	// >>>>>>>>>>>>>>>>>>>>>>SEARCHING<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Test
	public void searching_for_existing_restaurant_should_return_expected_restaurant_object()
			throws restaurantNotFoundException {
		restaurant = service.findRestaurantByName("Ashok");
		assertNotNull(restaurant);
		// WRITE UNIT TEST CASE HERE
	}

	@Test
	public void searching_for_non_existing_restaurant_should_throw_exception() throws restaurantNotFoundException {
		assertThrows(restaurantNotFoundException.class, () -> {
			service.findRestaurantByName("uTurn");
		});
	}

	// >>>>>>>>>>>>>>>>>>>>>>ADMIN: ADDING & REMOVING
	// RESTAURANTS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	@Test
	public void remove_restaurant_should_reduce_list_of_restaurants_size_by_1() throws restaurantNotFoundException {
		int initialNumberOfRestaurants = service.getRestaurants().size();
		service.removeRestaurant("Ashok");
		assertEquals(initialNumberOfRestaurants - 1, service.getRestaurants().size());
	}

	@Test
	public void removing_restaurant_that_does_not_exist_should_throw_exception() throws restaurantNotFoundException {
		assertThrows(restaurantNotFoundException.class, () -> service.removeRestaurant("bigBull"));
	}

	@Test
	public void add_restaurant_should_increase_list_of_restaurants_size_by_1() {
		int initialNumberOfRestaurants = service.getRestaurants().size();
		service.addRestaurant("sherlocks", "hebbal", LocalTime.parse("12:00:00"), LocalTime.parse("23:00:00"));
		assertEquals(initialNumberOfRestaurants + 1, service.getRestaurants().size());
	}
	// <<<<<<<<<<<<<<<<<<<<ADMIN: ADDING & REMOVING
	// RESTAURANTS>>>>>>>>>>>>>>>>>>>>>>>>>>
}