package outer;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;

public class Outer {

    public static class RectangleAdvanced extends WinDef.RECT {

        RectangleAdvanced(int x1, int y1, int x2, int y2) {
            this.left = x1;
            this.top = y1;
            this.right = x2;
            this.bottom = y2;
        }

    }

    private static final User32 user32 = User32.INSTANCE;

    private static final WinDef.RECT DEFAULT_WINDOW = new RectangleAdvanced(0, 0, 1010, 730);

    private static final WinDef.RECT DEFAULT_CLIENT = new RectangleAdvanced(10, 40, 990, 680);

    private static final WinDef.RECT DEFAULT_FOLD_BUTTON = new RectangleAdvanced(495, 660, 640, 710);

    private static final WinDef.RECT DEFAULT_CHECK_BUTTON = new RectangleAdvanced(665, 660, 810, 710);

    private static final WinDef.RECT DEFAULT_RAISE_BUTTON = new RectangleAdvanced(835, 660, 980, 710);

    private static final WinDef.RECT DEFAULT_MIN_BUTTON = new RectangleAdvanced(660, 580, 730, 605);

    private static final WinDef.RECT DEFAULT_3BET_BUTTON = new RectangleAdvanced(745, 580, 815, 605);

    private static final WinDef.RECT DEFAULT_BANK_BUTTON = new RectangleAdvanced(830, 580, 900, 605);

    private static final WinDef.RECT DEFAULT_MAX_BUTTON = new RectangleAdvanced(910, 580, 980, 605);

    private static final WinDef.RECT DEFAULT_NUMBER_COUNTER = new RectangleAdvanced(665, 625, 730, 640);

    private static final WinDef.RECT DEFAULT_BET_SLIDER = new RectangleAdvanced(800, 625, 960, 635);


}
