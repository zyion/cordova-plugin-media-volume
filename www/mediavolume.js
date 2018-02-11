
var exec = require('cordova/exec');

var PLUGIN_NAME = 'MediaVolume';


module.exports = {
    get: function () {
        return new Promise(function (resolve, reject) {
            exec(resolve, reject, PLUGIN_NAME, 'get', []);
        });
    },
    set: function (volume) {
        return new Promise (function (resolve, reject) {
            var vol = parseInt(volume);
            if (isNaN(vol)) reject('Error! supplied value is not an integer: ' + volume);
            else if (vol < 0) reject('Error! cannot set negative volume: ' + volume);
            else exec(resolve, reject, PLUGIN_NAME, 'set', [vol]);
        });
    },
    up: function () {
        return new Promise(function (resolve, reject) {
            exec(resolve, reject, PLUGIN_NAME, 'up', []);
        });
    },
    down: function () {
        return new Promise(function (resolve, reject) {
            exec(resolve, reject, PLUGIN_NAME, 'down', []);
        });
    },
    mute:  function () {
        return new Promise(function (resolve, reject) {
            exec(resolve, reject, PLUGIN_NAME, 'mute', []);
        });
    },
    unmute: function () {
        return new Promise(function (resolve, reject) {
            exec(resolve, reject, PLUGIN_NAME, 'unmute', []);
        });
    }
};
