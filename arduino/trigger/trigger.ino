int outpin = 4;
byte incomingByte = 0;
volatile int flag = 1;
int time = 0;

void setup()
{
  Serial.begin(1200);
  attachInterrupt(0,zero_crossing,CHANGE);
  pinMode(outpin,OUTPUT);
}

void loop()
{
  if(flag == 0)
  {
    delay(time);
    digitalWrite(outpin,HIGH);
    // Serial.println("Trigger");    
    flag = 1;
  }
  
  if (Serial.available() > 0) {
    incomingByte = Serial.read();
    if (incomingByte == (byte)'0') {
      time = 4;
    } else if (incomingByte == (byte)'1') {
      time = 2;
    }
  }
    
}


void zero_crossing()
{
  digitalWrite(outpin,LOW);
  flag = 0;
}
