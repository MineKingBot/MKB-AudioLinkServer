# MineKingBot AudioLinkServer
This projects contains an implementation of [AudioLinkServer](https://github.com/MineKing9534/AudioLinkServer) that is used by the [MineKingBot](https://github.com/MineKingBot). This can be used to host your own audio server on possibly better hardware than the default nodes.

## Natives
This repository contains the required natives for lavadsp in the `src/main/resources/natives` folder. These are taken from [here](https://github.com/aikaterna/lavadsp-natives). 

## Config
Here is an example for a configuration file:
```json
{
    "port": 29534,

    "password": "My very secure password",

    "cleanupThreshold": 10000,
    "bufferDuration": 1000,

    "equalizer": true,

    "youtube": {
        "id": "email",
        "secret": "password"
    },

    "spotify": {
        "id": "client id",
        "secret": "client secret"
    }
}
```