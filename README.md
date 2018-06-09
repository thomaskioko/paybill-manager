[![Build Status](https://travis-ci.org/kioko/paybill-manager.svg?branch=feature/ft-architecture-restructure)](https://travis-ci.org/kioko/paybill-manager)
[![codecov](https://codecov.io/gh/kioko/paybill-manager/branch/feature/ft-architecture-restructure/graph/badge.svg)](https://codecov.io/gh/kioko/paybill-manager)
<p align="center">
  <img src="https://github.com/kioko/paybill-manager/blob/master/app/src/main/ic_launcher_web.png?raw=true" alt="Paybill-Manager"/>
</p>


PayBill Manager (work-in-progress 👷🔧️👷‍♀️⛏)
-------------------
Paybill manger is an Android app meant to help you manage your bill.

It attempts to use the latest cutting edge libraries and tools. As a summary:

 * Entirely written in [Kotlin](https://kotlinlang.org/)
 * Uses [RxJava](https://github.com/ReactiveX/RxJava) 2
 * Uses all of the [Architecture Components](https://developer.android.com/topic/libraries/architecture/): Room, LiveData and Lifecycle-components
 * Uses [dagger-android](https://google.github.io/dagger/android.html) for dependency injection

## Project Setup

First off, you require the latest Android Studio 3.0 (or newer) to be able to build the app.

## API Keys
For the app to make requests you require a [Safaricom Dev Account](https://developer.safaricom.co.ke/ ). Once you have one, go ahead and [create an application](https://developer.safaricom.co.ke/user/1079/apps/add) in order to get your credentials

Once you have it, open `gradle.properties` file and paste your API key in `SAFARICOM_CONSUMER_KEY` and `SAFARICOM_CONSUMER_SECRET` variables respectively.

## RoadMap
- [x] Setup Travis CI
- [x] Add Kotlin Support
- [x] Create payment module
- [x] Add CodeCov
- [ ] Setup CodeQuality Checks
- [ ] Setup LeakCanary
- [ ] Release Beta Version

## Contributions

If you've found an error in this sample, please file an issue.

Patches are encouraged, and may be submitted by forking this project and submitting a pull request. Since this project is still in its very early stages, if your change is substantial, please raise an issue first to discuss it.


#### Would you like to contribute code?

1. [Fork Paybill Manager](https://github.com/kioko/paybill-manager).
2. Create a new branch ([using GitHub](https://help.github.com/articles/creating-and-deleting-branches-within-your-repository/)) or the command `git checkout -b branch-name dev`).
3. [Start a pull request](https://github.com/kioko/paybill-manager/compare). Reference [existing issues](https://github.com/kioko/paybill-manager/issues) when possible.

#### No code!
* You can [discuss a bug](https://github.com/kioko/paybill-manager/issues) or if it was not reported yet [submit a bug](https://github.com/kioko/paybill-manager/issues/new).

License
-------

    Copyright 2018 Thomas Kioko

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

