#!/usr/bin/python

import serial, sys

output = sys.argv[1]

ser = serial.Serial("/dev/ttyAMA0")
ser.baudrate = 1200
ser.write(output)
ser.close()
