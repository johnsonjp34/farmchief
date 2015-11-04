# farmchief
Planter Monitor Source

This is the code necessary to interact with the IOIO
board. (Please see circuit diagram http://investordirection.com/plantermonitorconstruction). Also included is a basic UI to monitor your row units. The Farm Chief monitor module is Patent protected. IT MAY BE REPRODUCED FOR INDIVIDUAL USE. FOR COMMERCIAL REPRODUCTION please email investordirection@gmail.com for licensing. 

To build a project using this code you will need to create an Android Project with the following libraries:

1) IOIOlib -- Contains IOIO API. See https://github.com/ytai/ioio/wiki for cloning this library.

2) IOIObt  -- Required for Bluetooth interaction with Planter Monitor. See https://github.com/ytai/ioio/wiki for cloning this library.

IF USING GOOGLE MAPS BACKGROUND you will need:

3) Google Play Services Library -- See https://developers.google.com/android/guides/setup

These libraries require you to register your Application and obtain an API key from the Google Developer Console.

4) Google Geo Calculator Library -- See https://developers.google.com/maps/documentation/android-api/utility/?hl=en

**IMPORTANT METHODS & CLASSES**

--The BaseIOIOLooper method is where you will do most of your calls to the IOIO board. You will OPEN your digital input or output channels here.

--Create a Looper class that extends the BaseIOIOLooper. Preferably you would want to make a thread for each input that you plan on using. In this example there are 8 to represent each row being monitored.

--setup() - You can open your channels. For example: 
PulseInput input1_ = ioio_.openPulseInput(new DigitalInput.Spec(1,Mode.PULL_DOWN), ClockRate.RATE_62KHz,  PulseMode.FREQ, false);

Please see IOIO documentation for specific parameters. The biggest thing to note is that setting PulseMode to FREQ allows the channels to monitor the frequency of your input. This is key to determining planting rates and population. The PULL_DOWN mode is required because the board can't indicate a frequency of 0 when there is no input. Because of the pull of the board to 0V in this mode you will need to **MAKE SURE that you use the diode in the schematic or your op amp will not work correctly.**

