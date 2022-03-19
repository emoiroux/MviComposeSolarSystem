## Build tools & versions used
it's a MVI & compose App

- Hilt 2.39             : Dependency injection 
- Jetpack compose 1.1.1 : UI
- Coil 2.0.0-rc01       : image and caching
- Retrofit 2.9.0        : Rest call  
- Mockito 3.3.3         : Junit test

## Steps to run the app
- From studio project, create a new Project from Github
- Build the app and run it.

## What areas of the app did you focus on?
- MVI pattern
- State management
- Di
- Testing the ViewModel

## What was the reason for your focus? What problems were you trying to solve?
- Having a clean architecture
- Use latest dev technics

## How long did you spend on this project?
- 8h, this include the time to learn MVI.

## Did you make any trade-offs for this project? What would you have done differently with more time?
- Accessibility, set content description / semantics for more accurate talk back.
- Hard coded string in the view, we should use resources instead.
- Specific Ui for Tablet / landscape, fore example : the picture height on phone landscape.
- The code has been tested on only one emulator.
- Test use deprecated coroutine context.
- Only visible item are loaded in the List, we should pre-load the adjacent items too to make the scroll smooth.
- Error handling : differentiate the type of error : internet off, 404 etc.. and display a readable dialog message for the user
- Likely we can check for malformed employee in a better way.
- More compose instrumented tests.

## What do you think is the weakest part of your project?
- Tablet / landscape compatibility

## Did you copy any code or dependencies? Please make sure to attribute them here!
- DispatchersModule.kt from official Crane sample google.
- ObserveForTesting helper in Junit test.

## Is there any other information you’d like us to know?
I could do it with MVVM classic approach without too much difficulty.
But I wanted to challenge myself so I went for compose + MVI + DI.
Witch is something partially new to me.