import com.codecool.citySim.model.lights.Light;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class LightTests {

    @Test
    public void testLight() throws InterruptedException {
        Light light = new Light();
        Thread thread = new Thread(light);
        thread.start();

        int i = 0;
        while ( i < 3 ) {
            Assert.assertTrue(light.isGreenHorizontal() != light.isGreenVertical());
            TimeUnit.SECONDS.sleep(light.getTimeOfLight() / 1000);
            i++;
        }
    }
}
