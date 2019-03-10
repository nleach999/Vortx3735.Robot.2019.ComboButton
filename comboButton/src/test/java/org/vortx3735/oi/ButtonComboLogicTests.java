package org.vortx3735.oi;

import org.junit.Test;

import org.vortx3735.oi.ButtonComboLogic;
import org.vortx3735.oi.exceptions.InvalidButtonBindingException;
import org.vortx3735.oi.exceptions.InvalidButtonException;
import junit.framework.TestCase;

public class ButtonComboLogicTests extends TestCase {

    @Test
    public void testCanary() {
        assertTrue(true);
    }

    @Test
    public void testButtonZeroCtorFails() {
        try {
            ButtonComboLogic cl = new ButtonComboLogic(5, (b) -> false, 1, 2, 0);
        } catch (InvalidButtonException ex) {
            assertTrue(true);
            return;
        } catch (InvalidButtonBindingException e) {
        }

        assertTrue(false);
    }

    @Test
    public void testButtonNegativeCtorFails() {
        try {
            ButtonComboLogic cl = new ButtonComboLogic(5, (b) -> false, 1, -2, 0);
        } catch (InvalidButtonException ex) {
            assertTrue(true);
            return;
        } catch (InvalidButtonBindingException e) {
        }

        assertTrue(false);

    }

    @Test
    public void testButtonPositiveCtorSuccess() {
        try {
            ButtonComboLogic cl = new ButtonComboLogic(5, (b) -> false, 1, 2, 3);
        } catch (InvalidButtonException ex) {
            assertTrue(false);
            return;
        } catch (InvalidButtonBindingException e) {
            assertTrue(false);
            return;
        }

        assertTrue(true);
    }

    @Test
    public void testFirstAndLastButtonCtorSuccess() {
        try {
            ButtonComboLogic cl = new ButtonComboLogic(5, (b) -> false, 1, 5);
        } catch (InvalidButtonException ex) {
            assertTrue(false);
            return;
        } catch (InvalidButtonBindingException e) {
            assertTrue(false);
            return;
        }

        assertTrue(true);
    }

    @Test
    public void testLastAndFirstButtonCtorSuccess() {
        try {
            ButtonComboLogic cl = new ButtonComboLogic(5, (b) -> false, 5, 1);
        } catch (InvalidButtonException ex) {
            assertTrue(false);
            return;
        } catch (InvalidButtonBindingException e) {
            assertTrue(false);
            return;
        }

        assertTrue(true);
    }

    @Test
    public void testRepeatButtonFails() {
        try {
            ButtonComboLogic cl = new ButtonComboLogic(5, (b) -> false, 1, 2, 1);
        } catch (InvalidButtonException ex) {
            assertTrue(true);
            return;
        } catch (InvalidButtonBindingException e) {
        }

        assertTrue(false);
    }

    @Test
    public void testBoundButtonLargerThanMaxFails() {
        try {
            ButtonComboLogic cl = new ButtonComboLogic(5, (b) -> false, 6);
        } catch (InvalidButtonException ex) {
            assertTrue(true);
            return;
        } catch (InvalidButtonBindingException e) {
        }

        assertTrue(false);

    }

    @Test
    public void testNumBoundButtonsLargerThanPossibleFails() {
        try {
            ButtonComboLogic cl = new ButtonComboLogic(2, (b) -> false, 1, 2, 3);
        } catch (InvalidButtonBindingException ex) {
            assertTrue(true);
            return;
        } catch (InvalidButtonException e) {
        }

        assertTrue(false);

    }

    @Test
    public void testNoButtonsDownIsNotPressed() {
        try {
            ButtonComboLogic cl = new ButtonComboLogic(2, (b) -> false, 1);
            assertFalse(cl.comboPressed());
            return;
        } catch (InvalidButtonBindingException ex) {
        } catch (InvalidButtonException e) {
        }

        assertTrue(false);

    }

    @Test
    public void testUnboundButtonsDownIsNotMatch() {
        try {
            ButtonComboLogic cl = new ButtonComboLogic(2, (b) -> {
                switch (b) {
                case 1:
                    return true;
                default:
                    return false;
                }
            }, 2);
            assertFalse(cl.comboPressed());
            return;
        } catch (InvalidButtonBindingException ex) {
        } catch (InvalidButtonException e) {
        }

        assertTrue(false);

    }

    @Test
    public void testBoundButtonsDownIsMatch() {
        try {
            ButtonComboLogic cl = new ButtonComboLogic(2, (b) -> {
                switch (b) {
                case 2:
                    return true;
                default:
                    return false;
                }
            }, 2);
            assertTrue(cl.comboPressed());
            return;
        } catch (InvalidButtonBindingException ex) {
        } catch (InvalidButtonException e) {
        }

        assertTrue(false);

    }

    @Test
    public void testPartialButtonsDownIsNotMatch() {
        try {
            ButtonComboLogic cl = new ButtonComboLogic(3, (b) -> {
                switch (b) {
                case 2:
                case 1:
                    return true;
                default:
                    return false;
                }
            }, 2, 1, 3);
            assertFalse(cl.comboPressed());
            return;
        } catch (InvalidButtonBindingException ex) {
        } catch (InvalidButtonException e) {
        }

        assertTrue(false);

    }

    @Test
    public void testFullButtonsDownIsMatch() {
        try {
            ButtonComboLogic cl = new ButtonComboLogic(3, (b) -> {
                switch (b) {
                case 2:
                case 1:
                case 3:
                    return true;
                default:
                    return false;
                }
            }, 2, 1, 3);
            assertTrue(cl.comboPressed());
            return;
        } catch (InvalidButtonBindingException ex) {
        } catch (InvalidButtonException e) {
        }

        assertTrue(false);
    }

    @Test
    public void testSingleButtonsDownIsMatch() {
        try {
            ButtonComboLogic cl = new ButtonComboLogic(3, (b) -> {
                switch (b) {
                case 2:
                    return true;
                default:
                    return false;
                }
            }, 2);
            assertTrue(cl.comboPressed());
            return;
        } catch (InvalidButtonBindingException ex) {
        } catch (InvalidButtonException e) {
        }

        assertTrue(false);
    }

    @Test
    public void testLastButtonsBoundAndDownIsMatch() {
        try {
            ButtonComboLogic cl = new ButtonComboLogic(3, (b) -> {
                switch (b) {
                case 3:
                    return true;
                default:
                    return false;
                }
            }, 3);
            assertTrue(cl.comboPressed());
            return;
        } catch (InvalidButtonBindingException ex) {
        } catch (InvalidButtonException e) {
        }

        assertTrue(false);
    }

    @Test
    public void testFirstButtonsBoundAndDownIsMatch() {
        try {
            ButtonComboLogic cl = new ButtonComboLogic(3, (b) -> {
                switch (b) {
                case 1:
                    return true;
                default:
                    return false;
                }
            }, 1);
            assertTrue(cl.comboPressed());
            return;
        } catch (InvalidButtonBindingException ex) {
        } catch (InvalidButtonException e) {
        }

        assertTrue(false);
    }

}