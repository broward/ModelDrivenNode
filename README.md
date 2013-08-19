ModelDrivenNode
===============


Model Driven Node.js REST server generated via xsd

This began as a testing artifact which I developed in a couple of days.  It generates a node.js server from an xsd schema applied against velocity templates to generate the app.js,models.js, routes.js, schemas and test records. It uses mongoose ORM, mongodb and express.

The goal is a single point of control (the xsd) to account for constant flux in schema design.  The entire server is re-generated to keep everything in sync and now uses Sun's XJC schema engine.  Added ObjectGrapher which does a rough-cut on the API to remove lookup tables from REST API listing.


Remaining tasks:

1) Add projection REST API

2) Add generated angular service layer, with socket.io example

3) Add generated 0MQ queue

4) Better test schemas

6) Add post API

7) Define an xml wrapper scheme to generate specific functionality
