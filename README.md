# cordova-plugin-media-volume
A Cordova plugin to control the device's media volume. Plugin only supports the Android Platform.


## Install
```bash
cordova plugin add net.zyion.mediavolume
```
## Uninstall
```bash
cordova plugin rm net.zyion.mediavolume
```

## Usage

```js
// get the current volume properties
MediaVolume.get().then(function (res) {
    console.log('Volume info', res);
});

// set the volume to 5
MediaVolume.set(5).then(function (res) {
    console.log('Volume', res.volume);
});

// set the volume to 50%
MediaVolume.setVolume(0.5).then(function (res) {
    console.log('Volume', res.volume);
});

// mute the device's media volume
MediaVolume.mute().then(function (res) {
    console.log('Volume muted', res.muted);
});
```


## API

### MediaVolume.get()

Get the media volume from the device, the function returns a promise which on success receives a json abject with the following properties
* **volume**: *integer* the device's media volume
* **max**: *integer* the device's maximum volume
* **muted**: *boolean* if the device volume is mute
* **fixed**: *boolean* if the device has a fixed volume


### MediaVolume.set(value)

Set the media volume for the device requires the following parameter
* **value**: *integer* the volume level to set

The function returns a promise which on success receives a json abject with the following properties
* **volume**: *integer* the device's media volume
* **muted**: *boolean* if the device volume is mute


### MediaVolume.setVolume(value)

Set the media volume for the device requires the following parameter
* **value**: *float* the volume level to set as a float from *0.0 - 1.0*

The function returns a promise which on success receives a json abject with the following properties
* **volume**: *integer* the device's media volume
* **muted**: *boolean* if the device volume is mute


### MediaVolume.up()

Increase the media volume, the function returns a promise which on success receives a json abject with the following properties
* **volume**: *integer* the device's media volume
* **muted**: *boolean* if the device volume is mute


### MediaVolume.down()

Decrease the media volume, the function returns a promise which on success receives a json abject with the following properties
* **volume**: *integer* the device's media volume
* **muted**: *boolean* if the device volume is mute


### MediaVolume.mute()

Mute the device's media volume, the function returns a promise which on success receives a json abject with the following properties
* **volume**: *integer* the device's media volume
* **muted**: *boolean* if the device volume is mute


### MediaVolume.unmute()

Unmute the device's media volume, the function returns a promise which on success receives a json abject with the following properties
* **volume**: *integer* the device's media volume
* **muted**: *boolean* if the device volume is mute


## Maintainers

- [Zyion](https://github.com/zyion)


## License

Apache 2.0 License
