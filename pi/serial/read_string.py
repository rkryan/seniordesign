#!/usr/bin/python

# Simple script to send an input string out of the raspberry pi over UART.
# A string is sent as a series of ASCII bytes and can be of arbitrary length.
# The signal is sent over GPIO 14 (Tx) at 1200 baud.  The string send is a command line argument.
# This script opens up the appropriate connection, sets the settings, 
# writes the word, and closes the connection.

import serial, sys

ser = serial.Serial("/dev/ttyAMA0") # opens serial link over UART Tx pin
ser.baudrate = 1200 # predetermined baud rate
while 1:
    print ser.readline()
