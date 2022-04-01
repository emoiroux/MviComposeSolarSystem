fastlane documentation
================
# Installation

Make sure you have the latest version of the Xcode command line tools installed:

```
xcode-select --install
```

Install _fastlane_ using
```
[sudo] gem install fastlane -NV
```
or alternatively using `brew cask install fastlane`

# Available Actions
## Android
### android preview
```
fastlane android preview
```
Distribute a new preview version.


### android QA
```
fastlane android QA
```
Distribute a new QA version.


### android beta
```
fastlane android beta
```
Distribute a new beta version.


### android release
```
fastlane android release
```
Distribute a new release version.


### android distribute
```
fastlane android distribute
```
Build, sign and upload specified schemes to Hockeyapp.

 * **`flavors`**: List of scheme to distribute. Accepts list of: `crt` `dev` `preview` `prod` Default: all

 * **`teams_url`**: Teams webhook url used to report the builds.


### android lint
```
fastlane android lint
```
Run linter


### android test
```
fastlane android test
```
Run tests


### android localized_copy_generation
```
fastlane android localized_copy_generation
```
Create localized strings from the copy google doc


### android build
```
fastlane android build
```
Build specified flavor

####Options

 * **`flavor`**: The environment to build. Accepts: `dev` `crt` `preview` `prod` `prod_aab` `beta_aab`  Default: `dev`


### android appcenter
```
fastlane android appcenter
```
Upload to AppCenter

####Options

 * **`flavor`**: The environment to build. Accepts: `dev` `crt` `preview` `prod` `prod_aab` `beta_aab`  Default: `dev`


### android dynatrace_dsym_upload
```
fastlane android dynatrace_dsym_upload
```
Upload DSYM to Dynatrace

####Options

 * **`flavor`**: The flavor to upload. Accepts: `crt` `dev` `preview` `prod` Default: `dev`


### android bump_build_number
```
fastlane android bump_build_number
```
Bump build number


### android bump_version
```
fastlane android bump_version
```
Bump or set version

####Options

 * **`type`**: Type of bump. Accepts: `patch` `minor` `major`  Default: `patch`

 * **`version`**: Version number to set directly.



----

This README.md is auto-generated and will be re-generated every time [fastlane](https://fastlane.tools) is run.
More information about fastlane can be found on [fastlane.tools](https://fastlane.tools).
The documentation of fastlane can be found on [docs.fastlane.tools](https://docs.fastlane.tools).
