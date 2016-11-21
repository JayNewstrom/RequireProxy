Work in Progress
----------------

This was created as a proof of concept, the api will more than likely change, there's also no documentation, so you probably shouldn't use this :)

Setup
-----

```groovy
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'com.jaynewstrom:require-proxy-assist:0.9.1'
    }
}

apply plugin: 'requireproxy'

dependencies {
    compile 'com.jaynewstrom:require-proxy-runtime:0.9.1'
}
```
 
Development Gradle Tasks
------------------------
 
./gradlew :require-proxy-runtime:install
./gradlew :require-proxy-assist:install

License
-------

    Copyright 2016 Jay Newstrom

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
