import org.example.movie.core.MovieController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes= MovieController.class)
public class TestMovieController {

    @Autowired
    MovieController movieController;

    @Test
    public void contextLoads() {
        assertNotNull(movieController);
    }

}
