package org.vortx3735.oi;

import org.vortx3735.oi.exceptions.InvalidButtonBindingException;
import org.vortx3735.oi.exceptions.InvalidButtonException;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.Button;

public class ComboButton extends Button {

    private ButtonComboLogic _combo;

    public ComboButton(String buttonName, GenericHID joystick, Integer... buttonNumbers) throws InvalidButtonException, InvalidButtonBindingException {
        _hid = joystick;

        setName (buttonName);

        _combo = new ButtonComboLogic (_hid.getButtonCount(), (i) -> _hid.getRawButton(i), buttonNumbers);
    }

    private GenericHID _hid = null;

    @Override
    public boolean get() {
        return _combo.comboPressed();
    }

}