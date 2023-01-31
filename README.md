# CRUD Android School App.

A CRUD android developed while learning android. It is student system, allows one to manage term dates,  assignments, notes and even get notified of any dues dates.

## Getting Started

### Setup

* Android studio
* Java > v11


### Dependecies
The app uses two external dependencies for android room.

```bash
    def lifecycle_version = "2.1.0"
    def room_version = "2.2.3"

    implementation "androidx.lifecycle:lifecycle-extensions:$lifecycle_version"
    annotationProcessor "androidx.lifecycle:lifecycle-compiler:$lifecycle_version"

    implementation "androidx.room:room-runtime:$room_version"
    annotationProcessor "androidx.room:room-compiler:$room_version"
```


### Building 

Build and emulate the app with android studio's generated run configuration.


## Demo

![demo video](./docs/demo.gif)
