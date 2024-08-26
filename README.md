## Cams

A simple Android mobile application for playing RTSP streams from IP cameras.

![Cams](https://raw.githubusercontent.com/vladpen/cams/main/fastlane/metadata/android/ru-RU/images/phoneScreenshots/5_cover.jpg)

Peculiarities:

- A simple Android mobile application for playing RTSP streams from IP cameras.
- View RTSP streams from any IP cameras, including H.265+.
- Simultaneous viewing of multiple streams.
- Twenty times image magnification.
- Support for dual-channel cameras.
- View videos or images via SFTP.
- Ability to configure alerts when the camera motion detector is triggered.
- High connection speed.
- Extreme ease of navigation and control.
- Maximum data security and confidentiality.
- TCP/UDP protocol switching. This option is important when viewing cameras over the Internet, where UDP may not be supported or may not work well.


<img src="https://raw.githubusercontent.com/vladpen/cams/main/fastlane/metadata/android/ru-RU/images/phoneScreenshots/1_main_ru.jpg"
alt="Main screen"
width="200">&nbsp;
<img src="https://raw.githubusercontent.com/vladpen/cams/main/fastlane/metadata/android/ru-RU/images/phoneScreenshots/2_edit_ru.jpg"
alt="Edit screen"
width="200">&nbsp;
<img src="https://raw.githubusercontent.com/vladpen/cams/main/fastlane/metadata/android/ru-RU/images/phoneScreenshots/3_files_ru.jpg"
alt="Files screen"
width="200">&nbsp;
<img src="https://raw.githubusercontent.com/vladpen/cams/main/fastlane/metadata/android/ru-RU/images/phoneScreenshots/4_video_ru.jpg"
alt="Video screen"
width="200">

The application is written for use with the python-rtsp-server server , but works great standalone thanks to the ability to connect to any IP cameras, as well as video recorders that support SFTP.

Plays most types of video streams (not just RTSP). The screenshot above shows an image from a real video camera and three test clips in Group mode.

IMPORTANT. The application is focused on data security and privacy, so it does not collect or process any information about the user. The data is not sent to any servers, including Google's technical infrastructure or camera manufacturers' cloud storage.

## Installation

The APK file can be compiled independently, downloaded from Github , installed using F-Droid or RuStore . The architecture supported is armeabi-v7a (used in most modern mobile phones), arm64-v8a, x86-64 and x86.

## Settings

To connect to the video camera, you need to enter its URL specified by the manufacturer in the "Address" field. Usually it looks like this:
```
[rtsp://][<user>:<password>@]<IP>[:<port>][/<path>]
```
Parameters in square brackets are optional (depending on camera settings).

For dual-channel cameras, you can additionally specify the address of the second channel. For example, for Hikvision cameras and their derivatives the path will look like this:
```
ISAPI/Streaming/Channels/<channel number>
```
Then the first channel (high resolution) will be numbered 101, and the second (low resolution) will be numbered 102.

Low-resolution channels can be used to speed up image loading, save bandwidth, and reduce the load on the device's processor. This is especially useful for viewing a group of cameras on a low connection speed. During playback, channels can be switched using the K1/K2 button in the lower right corner of the screen. Camera group screens use K2 by default.

Also, to reduce the load, the playback of cameras that go beyond the boundaries of the screen when the image is enlarged is paused.

The SFTP server or DVR address looks like this:
```
[sftp://]<user>:<password>@<IP>[:<port>][/<path>]
```
ATTENTION! It is strongly recommended not to use administrator access credentials. For an SFTP server, it is better to create a chroot, for example, as described [here](https://wiki.archlinux.org/title/SFTP_chroot).

**Tip**: You can use an emoji as an icon in the camera name. For example, the screenshots above use icons from the standard set of mobile phones.

## Motion Alert

Optionally, the application can notify you when the camera motion detector is triggered. The notification is triggered when a new image from the camera appears in the specified SFTP server folder. For this function to work, you need to configure the cameras and the server for storing the received images. These settings are described in detail in the parallel project [Cams-PWA](https://github.com/vladpen/cams-pwa).

Detailed discussion of the application: [habr.com/ru/post/654915](https://habr.com/ru/post/654915/)
and server: [habr.com/ru/post/597363](https://habr.com/ru/post/597363/).

*Copyright (c) 2022-2024 vladpen under MIT license. Use it with absolutely no warranty.*
