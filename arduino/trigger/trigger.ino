/*
  Collin Reiman and Ryan King
  ECE 257 - Senior Project
  Plug and Play Home Automation System
  Trigger
  
  This code takes in data sent from the Pi via UART
  and changes its time delay accordingly.  The time
  delay sets when the trigger pulse is sent to the
  gate of a TRIAC.  It does this by looking first
  for an interrupt at pin 2.  When this interrupt is
  received a flag is reset.  In the main loop, if the
  flag is reset, then the Arduino waits the defined
  amount of time before sending out the pulse.
*/

int outpin = 4;                             //Pin 4 is output pulse
byte incomingByte = 0;                      //Do not initially read serial
volatile int flag = 1;                      //Pulse flag can be changed in
                                            //multiple loops
int time = 0;                               //Bulb is initially on

void setup() {
  Serial.begin(1200);                       //1200 baud
  attachInterrupt(0,zero_crossing,CHANGE);  //Pin 2 is interrupt pin
  pinMode(outpin,OUTPUT);
}

void loop() {
  if(flag == 0)
  {
    delay(time);                            //Wait defined amount of time
    digitalWrite(outpin,HIGH);              //Send out trigger pulse
    delay(1);                               //Wait 1 ms
    digitalWrite(outpin,LOW);               //End trigger pulse
    // Serial.println("Trigger");    
    flag = 1;                               //Set flag
  }
  
  if (Serial.available() > 0) {
    incomingByte = Serial.read();           //If new data is in, read data
    if (incomingByte == (byte)'0') {        
      time = 6;
    } else if (incomingByte == (byte)'1') {
      time = 2;
    }
  }
    
}

void zero_crossing() {
  digitalWrite(outpin,LOW);                 //If interrupt is recieved
  flag = 0;                                 //Reset flag
}
