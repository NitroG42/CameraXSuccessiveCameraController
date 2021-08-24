# Using CameraController in two successives screen

## Context

An app that display the camera on Screen A, then going to Screen B also displays the camera. In this sample, authorize the camera, then
press the thumbs up button

## Issue

When using the class CameraController, the first screen will display normally, but going to the second screen, it nows displays a black
screen. The logs are joined in the logs.txt file in the repo.

## Cause

It seems that when going to the second screen, the lifecycle of first screen is destroyed and it closes the camera, which leaves the second
screen in a bad state.

## Workaround

I didn't find any way to workaround this using the CameraController class, the only way is to use directly the class CameraProvider and
don't use a controller.