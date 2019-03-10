package org.vortx3735.oi;

import java.util.function.Function;

import org.vortx3735.oi.exceptions.InvalidButtonBindingException;
import org.vortx3735.oi.exceptions.InvalidButtonException;

public class ButtonComboLogic {

    private Boolean[] _buttonArray;
    private Function<Integer, Boolean> _buttonStateSupplier;

    public ButtonComboLogic(int numButtons, Function<Integer, Boolean> buttonStateSupplier, Integer... boundButtons)
            throws InvalidButtonException, InvalidButtonBindingException {

        if (boundButtons.length > numButtons)
            throw new InvalidButtonBindingException(
                    "The number of bound buttons is larger than the number of buttons.");

        _buttonArray = new Boolean[numButtons];

        _buttonStateSupplier = buttonStateSupplier;

        for (Integer buttonIndex : boundButtons)
            if (buttonIndex > 0 && buttonIndex - 1 < numButtons && _buttonArray[buttonIndex - 1] == null)
                _buttonArray[buttonIndex - 1] = true;
            else
                throw new InvalidButtonException(buttonIndex);
    }

    public Boolean comboPressed() {
        boolean result = false;
        boolean init = false;

        for (int buttonIndex = 0; buttonIndex < _buttonArray.length; buttonIndex++) {

            // If the button is not bound but it is pressed, then this is not a matching
            // binding.
            if (_buttonArray[buttonIndex] == null && _buttonStateSupplier.apply(buttonIndex + 1))
                return false;

            // If the button is not bound, we don't bother checking.
            if (_buttonArray[buttonIndex] == null)
                continue;

            if (!init && _buttonArray[buttonIndex]) {
                // If this is a bound button, initialize the comparison by setting the state
                // of the result according to if the bound button is pressed.
                init = true;
                result = _buttonStateSupplier.apply(buttonIndex + 1);
            } else if (_buttonArray[buttonIndex]) {
                // The next button that is bound should be pressed if this binding is to match
                result = result && _buttonStateSupplier.apply(buttonIndex + 1);
            }

            // If any button in the binding is not pressed, the binding is not a match.
            if (!result && init)
                break;

        }

        return result;
    }

}