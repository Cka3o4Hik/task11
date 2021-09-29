package Service;

import Entity.CommentEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CommentServiceTest extends BaseIntegrationTest {
	UserService userService;
	CommentService commentService;
	AdvertisementService advertisementService;

	@BeforeAll
	void init() {
		userService = new UserService(factory);
		commentService = new CommentService(factory);
		advertisementService = new AdvertisementService(factory);
	}

	@Test
	void testAll() {
		CommentEntity newComments = new CommentEntity();
		newComments.setCreatedAt(LocalDateTime.now());
		newComments.setText("Some Text");
		newComments.setUser(userService.getById(1));
		newComments.setAdvertisement(advertisementService.getById(1));
		Integer id = commentService.add(newComments);
		assertNotNull(id);
		assertTrue(id > 0);

		newComments.setText("New Text");
		commentService.update(newComments);
		CommentEntity advertisementById = commentService.getById(id);
		assertNotNull(advertisementById);
		assertEquals(advertisementById.getText(), "New Text");

		List<CommentEntity> allCommentsList = commentService.getAll();
		assertNotNull(allCommentsList);
		int size = allCommentsList.size();

		commentService.deleteById(id);
		allCommentsList = commentService.getAll();
		assertEquals(size - allCommentsList.size(), 1);
	}
}
