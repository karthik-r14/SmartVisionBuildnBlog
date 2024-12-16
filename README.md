# SmartVision
## This project is a mobile assistant created for Visually Impaired/Challenged individuals.
## The various use case of this app are given below :
### 1. Detect Objects
### 2. Scan Products

## 1. For proper functioning of the detect objects and summarize docs feature, set your Gemini API Key in the local.properties file that is create a variable apiKey="Enter-your-API-Key-here". You need to enter the gemini API key as the value for the apiKey.(Also, if you get gradle build errors then consider rebuilding or cleaning up the project after adding API key in the local.properties file)
## 2. For Detect objects feature a WiFi enabled camera is required like ESP32 cam module. Also, The ESP32 cam module and the mobile phone should be in the same network. So, for convenience turn on the mobile hotspot of the phone and connect the ESP32 camera module to the hotspot network.
## 3. Once camera module is connected to the hotspot, figure out the IP address of the cam module and feed the image url(for example 192.168.223.149/jpg) in the setting screen of the app.(You can go to settings screen using the navigation drawer/pane)
## 4. In case of any queries please reach out to me on my email Id : karthikr1493@gmail.com

## Blog post link :  https://medium.com/@karthikr1493/smartvision-ai-enabled-mobile-assistant-for-visually-impaired-challenged-individuals-061bc5f5260a

## How to build an AI enabled mobile assistant for visually impaired and challenged individuals

## Introduction/Overview

## In a world that revolves around sight, the visually impaired often face unique challenges that can hinder their ability to navigate and experience the world with confidence and independence. However, the relentless pursuit of technological innovation has given rise to the SmartVision App, designed specifically to empower and enhance the lives of the visually impaired.

## The target audience for this blog is for anyone who wants to learn how to integrate Gemini API with an android app and also use firebase with Google ML-Kit(Barcode scanning API).(Note : Prerequisite knowledge of building android apps in which you can create few screen using activities and fragment is necessary).
 
## By the end of this blog post you will learn to build an app with few use cases which are beneficial for visually impaired and challenged individuals.

## The use cases that we will build are given below:

## 1) Detect objects : By using this feature a visually impaired user can detect obstacles from a WiFi enabled camera(ESP32 cam module) placed on the smart cap. Images captured using the cam module will be wirelessly sent to android app & Objects present in the image will be described by Gemini-2.0-Flash API and the announced to the visually impaired user using Text to Speech API. I am using Gemini Flash because it is faster and thus has less response time compared to Gemini Pro.

## 2) Scan Products: In this feature, the user has to scan the barcode of a product using the on-device camera and the product details corresponding to the product will be fetched from the Firestore database and then announced to the user using Text to Speech API.


