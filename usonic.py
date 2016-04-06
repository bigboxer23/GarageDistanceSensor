#!/usr/bin/python
import time
import RPi.GPIO as GPIO
GPIO_TRIGGER = 20
GPIO_ECHO = 16
GPIO.setwarnings(False)
GPIO.setmode(GPIO.BCM)
GPIO.setup(GPIO_TRIGGER, GPIO.OUT)
GPIO.setup(GPIO_ECHO, GPIO.IN)
GPIO.output(GPIO_TRIGGER, GPIO.LOW)
time.sleep(0.3)

def reading():
	GPIO.output(GPIO_TRIGGER, True)
	time.sleep(0.00001)
	GPIO.output(GPIO_TRIGGER, False)

	while GPIO.input(GPIO_ECHO) == 0:
		signaloff = time.time()

	while GPIO.input(GPIO_ECHO) == 1:
		signalon = time.time()

	timepassed = signalon - signaloff
	distance = timepassed * 17000
	return distance

myResults = []
for x in range(0, 15):
	time.sleep(0.1)
	myResults.append(reading())

#print myResults
print sum(myResults)/len(myResults)
GPIO.cleanup()