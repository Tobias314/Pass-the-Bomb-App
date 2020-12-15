# PassTheBomb-Client

To be fully functional the app relies on a REST server. The code for the server can be found here: https://github.com/tditt/PassTheBomb-Backend
The server is hosted on an AWS elastic beanstalk instance, so there is no need to run it locally.


## Deploy Locally
- **In emulator**:  For testing the server capabilities with android emulator, the preset IP will work if the server is running on the same system as the emulator.

- **On a device**:  For testing the server capabilities with a device, the server IP has to be set manually in the code. The server has to be run in the same network. In the RestService class there is a constant with the name REST_URL, which has to be set to the server IP in the network. It runs on port 5000 on default, so the IP should include this port number.
The server is hosted on an AWS elastic beanstalk instance, so there is no need to run it locally.
