package Service;

import Entity.AdvertisementEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AdvertisementServiceTest extends BaseIntegrationTest {
	UserService userService;
	AdvertisementService advertisementService;
	CommentService commentService;

	@BeforeAll
	void init() {
		userService = new UserService(factory);
		advertisementService = new AdvertisementService(factory);
		commentService = new CommentService(factory);
	}

	@Test
	void testAll() {
		AdvertisementEntity newAdvertisement = new AdvertisementEntity();
		newAdvertisement.setCreatedAt(LocalDateTime.now());
		newAdvertisement.setDescription("Some description");
		newAdvertisement.setTitle("Some title");
		newAdvertisement.setUser(userService.getById(1));
		newAdvertisement.setComments(commentService.getAll());
		Integer id = advertisementService.add(newAdvertisement);
		assertNotNull(id);
		assertTrue(id > 0);

		newAdvertisement.setTitle("New Title");
		advertisementService.update(newAdvertisement);
		AdvertisementEntity advertisementById = advertisementService.getById(id);
		assertNotNull(advertisementById);
		assertEquals(advertisementById.getTitle(), "New Title");

		List<AdvertisementEntity> allAdvertisements = advertisementService.getAll();
		assertNotNull(allAdvertisements);
		int size = allAdvertisements.size();

		advertisementService.deleteById(id);
		allAdvertisements = advertisementService.getAll();
		assertEquals(size - allAdvertisements.size(), 1);
	}
}
