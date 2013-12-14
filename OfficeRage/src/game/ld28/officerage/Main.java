package game.ld28.officerage;

import game.ld28.officerage.gfx.Renderable;
import game.ld28.officerage.gfx.Renderer;
import game.ld28.officerage.gfx.TestRenderable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.testRenderer(640, 480);
    }

    private void testRenderer(int gx, int gy) {
        List<Renderable> renderables = new ArrayList<Renderable>();
        Random ran = new Random();

        for (int i = 0; i < 100; i++) {
            int w = ran.nextInt(50) + 1;
            int h = ran.nextInt(50) + 1;
            int x = ran.nextInt(gx - w);
            int y = ran.nextInt(gy - h);
            int col = (ran.nextInt(256) << 16) | (ran.nextInt(256) << 8)
                    | (ran.nextInt(256));
            TestRenderable box = new TestRenderable(x, y, w, h, col);
            renderables.add(box);
        }

        Renderer gfx = new Renderer("Hello World!", gx, gy);
        while (true) {
            gfx.render(renderables);
            try {
                Thread.sleep(20);
            } catch (Exception e) {
            }
        }
    }
}
