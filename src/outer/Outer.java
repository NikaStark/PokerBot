package outer;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinDef.HWND;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;

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

    public static HWND getForegroundWindow() {
        return user32.GetForegroundWindow();
    }

    public void pushButton(HWND hwnd, WinDef.RECT buttonRECT) {
        try {
            Robot robot = new Robot(); // TODO It's worst way of creating instance, i must change this at first
            WinDef.RECT windowRect = new WinDef.RECT();
            user32.GetWindowRect(hwnd, windowRect);
            robot.mouseMove(windowRect.left + buttonRECT.left + (int)(Math.random() * (buttonRECT.right - buttonRECT.left)),
                    windowRect.top + buttonRECT.top + (int)(Math.random() * (buttonRECT.bottom - buttonRECT.top)));
            robot.mousePress(MouseEvent.BUTTON1_MASK);
            robot.delay(100);
            robot.mouseRelease(MouseEvent.BUTTON1_MASK);
        } catch (AWTException exc) {
            throw new RuntimeException(("AWTException - " + exc));
        }
    }

    public int getCurrentBank(HWND hwnd) {
        try {
            Robot robot = new Robot();
            WinDef.RECT windowRect = new WinDef.RECT();
            user32.GetWindowRect(hwnd, windowRect);
            robot.mouseMove(windowRect.left + DEFAULT_NUMBER_COUNTER.left, windowRect.top + DEFAULT_NUMBER_COUNTER.top);
            robot.mousePress(MouseEvent.BUTTON1_MASK);
            robot.mouseMove(windowRect.left + DEFAULT_NUMBER_COUNTER.right, windowRect.top + DEFAULT_NUMBER_COUNTER.bottom);
            robot.mouseRelease(MouseEvent.BUTTON1_MASK);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_X);
            robot.delay(100);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_X);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            Object text = clipboard.getData(DataFlavor.stringFlavor);
            return Integer.parseInt((String)text);
        } catch (AWTException | IOException | UnsupportedFlavorException exc) {
            throw new RuntimeException("Exception - " + exc);
        }

    }

}
