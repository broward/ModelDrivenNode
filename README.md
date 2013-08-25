ModelDrivenNode
===============


Model Driven Node.js REST server generated via xsd

This began as a testing artifact which I developed in a couple of days.  It generates a node.js server from an xsd schema applied against velocity templates to generate the app.js,models.js, routes.js, schemas, jade views and test records. It uses mongoose ORM, mongodb and express.

The goal is a single point of control (the xsd) to account for constant flux in schema design.  The entire server is re-generated to keep everything in sync and now uses Sun's XJC schema engine.  Added a queue manager (Kue).


Remaining tasks:

1) Add projection REST API

2) Add generated angular service layer

6) Add post API

7) Define an xml wrapper scheme to generate specific functionality

8) Generate fn(){} shells for socket.io server/client

9) Better configuration for external references, i.e. database schemas

10) Create generic jade templates which use reflection, for 'add' and 'edit'

11) Fill in how MDN tagging works, is read and applied
