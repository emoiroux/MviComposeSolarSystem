## Build tools & versions used
An MVI & Jetpack Compose Application

- Hilt 2.39             : Dependency injection 
- Jetpack compose 1.1.1 : UI
- Coil 2.0.0-rc01       : Image and caching
- Retrofit 2.9.0        : Rest call  
- Mockito 3.3.3         : Junit test

## Steps to run the app
- From Android Studio, create a new Project from version control and use the Git URL
- Build the app and run it

## What areas of the app did you focus on?
- MVI pattern
- State management
- Di
- Testing the ViewModel

## What was the reason for your focus? What problems were you trying to solve?
- Having a clean architecture
- Use latest development techniques

## How long did you spend on this project?
- 8h, this include the time to learn MVI.

## Did you make any trade-offs for this project? What would you have done differently with more time?
- Hard coded string in the view, we should use resources instead.
- Specific Ui for Tablet / landscape.
- The code has been tested on Pixel 4a only.
- Test use deprecated coroutine context.
- Only visible item are loaded in the list, we should pre-load the adjacent items too to make the scroll smooth.
- Error handling : better message for the user. Check the type of error : internet off, 404 etc...
- Accessibility : set content description / semantics for the talk back.
- Likely we can have a better code to detect a malformed employee.
- More compose instrumented tests.

## What do you think is the weakest part of your project?
- Tablet / landscape compatibility

## Did you copy any code or dependencies? Please make sure to attribute them here!
- DispatchersModule.kt from official Crane sample google.
- ObserveForTesting helper in Junit test from various forum.

## Is there any other information you’d like us to know?
I could do it with MVVM classic approach without too much difficulty.
But I wanted to challenge myself with something new, so I went for Jetpack Compose + MVI + DI.