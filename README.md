# Binding Button Combinations To Robot Commands


## How to Use ComboButton

It works like a `Button` class except that it allows a combination of buttons to be used.  An example of making buttons in the `OI` constructor
of a default command-based Robot project:

```
  public OI() {
    Joystick j1 = new Joystick(1);

    try {

      // Turns on "ExampleCommand" when buttons 1 & 6 are pressed.
      ComboButton c1 = new ComboButton("First Combo", j1, 1, 6);
      c1.toggleWhenActive(new ExampleCommand());

      // Turns on "ExampleCommand" when button 1 is pressed, turns it off when
      // button 1 is pressed again.
      ComboButton c2 = new ComboButton("Single Button", j1, 1);
      c2.toggleWhenPressed(new ExampleCommand());

    } catch (InvalidButtonException | InvalidButtonBindingException e) {

      // If trying to bind to a HID with different/less buttons, an exception is
      // thrown. Is the joystick plugged in?
      e.printStackTrace();
    }

  }
```

## NOTE: Combining Instances of `ComboButton` with other `Button` Derivatives

All buttons will need to be of type `ComboButton` if you have cases where you want to have a button bound in more than one combination (including a single button binding) and
don't want more than one command to execute for a button.  Example:

Instance `A` of type `ComboButton` is bound to buttons 1 & 2 and instance `B` of type `Button` is bound to button 1 only => pressing button 1 results in commands bound to `A` and `B` executing.

Instance `A` of type `ComboButton` is bound to buttons 1 & 2 and instance `B` of type `ComboButton` is bound to button 1 only => pressing button 1 results in 
commands bound to `B` executing.

## NOTE: Performance

If you have a joystick with a large number of buttons OR a large number of bindings, this may not perform all that well.  This iterates through the joystick's buttons to determine state each time the robot code iterates through each binding.  Your mileage may vary.  There may be better ways to make this work more pre-emptively.

## NOTE: Testing

As of this writing, this code has not actually been tested in a robot due to lack of a RoboRIO for direct testing.  Any live testing results will be published
as available.


# Including `ComboButton` in your Robot Code

1. Add the "comboButton" folder at the root level of your Robot project
2. Append the following to the end of `build.gradle`:

```
sourceSets
{
    test {
        java {
            srcDir "comboButton/src/test/java"
        }
    }

    main {
        java {
            srcDir "comboButton/src/main/java"
        }
    }
}
```

Executing the `test` build task will run unit tests on the button handling logic.  The unit tests are also a good way to learn how the button logic works.
