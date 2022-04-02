fastlane documentation
----

# Installation

Make sure you have the latest version of the Xcode command line tools installed:

```sh
xcode-select --install
```

For _fastlane_ installation instructions, see [Installing _fastlane_](https://docs.fastlane.tools/#installing-fastlane)

# Available Actions

## Android

### android compile

```sh
[bundle exec] fastlane android compile
```

Compile debug and test sources

### android lint

```sh
[bundle exec] fastlane android lint
```

Execute Android lint

### android assemble

```sh
[bundle exec] fastlane android assemble
```

Assemble source and test APKs

### android unit_test

```sh
[bundle exec] fastlane android unit_test
```

Execute unit tests

### android instrumentation_test

```sh
[bundle exec] fastlane android instrumentation_test
```

Execute instrumentation test on Emulator

### android notify_teams

```sh
[bundle exec] fastlane android notify_teams
```

This is a cloud build test with github action



----

This README.md is auto-generated and will be re-generated every time [_fastlane_](https://fastlane.tools) is run.

More information about _fastlane_ can be found on [fastlane.tools](https://fastlane.tools).

The documentation of _fastlane_ can be found on [docs.fastlane.tools](https://docs.fastlane.tools).
